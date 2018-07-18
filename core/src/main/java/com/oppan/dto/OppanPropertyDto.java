package com.oppan.dto;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 */
@Document(collection = "oppan_property")
public class OppanPropertyDto {

    private String name;
    private String value;
    private boolean enabled;

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }

    public boolean isEnabled() {
        return enabled;
    }
}
