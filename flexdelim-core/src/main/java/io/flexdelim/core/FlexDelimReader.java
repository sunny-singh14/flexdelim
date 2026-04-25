package io.flexdelim.core;

import io.flexdelim.api.DelimReader;
import io.flexdelim.api.Row;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class FlexDelimReader implements DelimReader {

    private final BufferedReader reader;
    private final char delimiter;
    private final char quote;
    private FlexDelimHeader header;

    FlexDelimReader(InputStream inputStream, Character delimiter, Character quote, boolean hasHeader) {
        if (inputStream == null) {
            throw new IllegalArgumentException("InputStream can not be null");
        }
        this.reader = new BufferedReader(new InputStreamReader(inputStream));
        this.delimiter = delimiter;
        this.quote = quote;
        if (hasHeader)
            initHeader();
    }

    @Override
    public Stream<Row> stream() {
        return reader.lines()
                .map(line -> new FlexDelimRow(parse(line), header));
    }

    private void initHeader() {
        String firstLine;
        try {
            firstLine = reader.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        String[] columns = parse(firstLine);

        if (columns.length == 0)
            return;

        this.header = new FlexDelimHeader(columns);
    }

    private String[] parse(String line) {
        List<String> row = new ArrayList<>();
        StringBuilder column = new StringBuilder();
        boolean stringBoundry = false;
        for (char c : line.toCharArray()) {
            if (c == delimiter && !stringBoundry) {
                row.add(column.toString());
                column = new StringBuilder();
            } else if (c == quote) {
                stringBoundry = !stringBoundry;
            } else {
                column.append(c);
            }
        }
        if (!column.isEmpty()) {
            row.add(column.toString());
        }
        return row.toArray(new String[0]);
    }
}