package io.flexdelim.api;

public interface Row {
    String get(int column);

    String get(String column);

    Integer getInt(String column);

    Double getDouble(String column);

    Integer length();
}