package pl.kurs.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

public enum ObjectMapperHolder {
    INSTANCE;

    private final ObjectMapper mapper;

    ObjectMapperHolder() {
        mapper = create();
    }

    private static ObjectMapper create() {
        ObjectMapper mapper = new ObjectMapper();
        return mapper;
    }

    public ObjectMapper getMapper() {
        return mapper;
    }
}
