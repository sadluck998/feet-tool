package org.example.feettool.util;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class MyObjectMapper extends ObjectMapper {
    private static final long serialVersionUID = -5690970482116344972L;

    public MyObjectMapper() {
        super();

        Include incl = Include.NON_NULL;
        setSerializationInclusion(incl);

        configure(SerializationFeature.WRITE_BIGDECIMAL_AS_PLAIN, true);
        configure(SerializationFeature.WRITE_DATE_TIMESTAMPS_AS_NANOSECONDS, true);
        configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }
}
