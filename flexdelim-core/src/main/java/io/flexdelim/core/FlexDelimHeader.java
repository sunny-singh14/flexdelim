package io.flexdelim.core;

import io.flexdelim.api.Header;

import java.util.HashMap;
import java.util.Map;

public class FlexDelimHeader implements Header {

    private final String[] header;
    private final Map<String, Integer> indexByName;

    public FlexDelimHeader(String[] header) {
        this.header = header;
        this.indexByName = new HashMap<>(getLength());
        for (int i = 0; i < header.length; i++) {
            indexByName.put(header[i], i);
        }
    }

    @Override
    public String[] getColumns() {
        return header;
    }

    @Override
    public int getLength() {
        return header.length;
    }

    @Override
    public Integer getColumnIndex(String column) {
        return indexByName.get(column);
    }
}
