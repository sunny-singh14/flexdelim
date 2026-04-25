package io.flexdelim.api;

import java.util.stream.Stream;

public interface DelimReader {
    Stream<Row> stream();
}