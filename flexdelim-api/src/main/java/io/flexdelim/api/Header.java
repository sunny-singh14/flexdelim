package io.flexdelim.api;

public interface Header {
    String[] getColumns();

    int getLength();

    Integer getColumnIndex(String column);
}
