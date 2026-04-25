package io.flexdelim.core;

import java.io.InputStream;

public class FlexDelim {
    private FlexDelim() {
    }

    public static FlexDelimBuilder read(InputStream inputStream) {
        return new FlexDelimBuilder(inputStream);
    }
}
