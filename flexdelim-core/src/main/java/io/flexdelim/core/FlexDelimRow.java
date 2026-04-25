package io.flexdelim.core;

import io.flexdelim.api.Row;

public class FlexDelimRow implements Row {

    private final String[] values;
    private final FlexDelimHeader header;

    FlexDelimRow(String[] values, FlexDelimHeader header) {
        this.values = values;
        this.header = header;
    }

    @Override
    public String get(int column) {
        if(column < 0 || column >= values.length)
            return null;
        return values[column];
    }

    @Override
    public String get(String column) {
        if(header == null)
            throw new IllegalStateException("Header is not initialized");
        return values[header.getColumnIndex(column)];
    }

    @Override
    public Integer getInt(String column) {
        return Integer.parseInt(get(column));
    }

    @Override
    public Double getDouble(String column) {
        return Double.parseDouble(get(column));
    }

    @Override
    public Integer length() {
        return values.length;
    }

    @Override
    public String toString() {

        StringBuilder table = new StringBuilder();

        for (String value : values) {
            table.append(String.format("%-20s ", value));
        }

        return table.toString();
    }
}