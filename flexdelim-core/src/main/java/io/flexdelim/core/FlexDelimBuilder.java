package io.flexdelim.core;

import java.io.InputStream;

public class FlexDelimBuilder {

    private final InputStream input;
    private char delimiter = ',';
    private char quote = '"';
    private boolean hasHeader = true;

    FlexDelimBuilder(InputStream input) {
        if (input == null) {
            throw new IllegalArgumentException("InputStream cannot be null");
        }
        this.input = input;
    }

    public FlexDelimBuilder delimiter(Character delimiter) {
        if (delimiter != null)
            this.delimiter = delimiter;
        return this;
    }

    public FlexDelimBuilder header(boolean hasHeader) {
        this.hasHeader = hasHeader;
        return this;
    }

    public FlexDelimBuilder quote(Character quote) {
        if (quote != null)
            this.quote = quote;
        return this;
    }

    public FlexDelimReader build() {
        return new FlexDelimReader(input, delimiter, quote, hasHeader);
    }
}