package io.flexdelim.core;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.InputStream;

class AppTest {

    @Test
    void testBase() {
        InputStream is = AppTest.class
                .getClassLoader()
                .getResourceAsStream("data.csv");
        FlexDelimReader reader = FlexDelim
                .read(is)
                .delimiter(',')
                .quote('"')
                .header(true)
                .build();

        Assertions.assertNotNull(reader);
        reader.stream().forEach(System.out::println);
    }
}