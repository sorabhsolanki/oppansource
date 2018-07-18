package com.oppan.cache;

import com.oppan.annotation.Cache;
import com.oppan.dto.OppanPropertyDto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 */
@Cache(name = "oppanPropertyCache")
public class OppanPropertyCache {

    private static final Map<String, OppanPropertyDto> map = new HashMap<>();

    public void insertAll(List<OppanPropertyDto> propertyDtoList) {
        propertyDtoList.stream().forEach(oppanPropertyDto -> map.put(oppanPropertyDto.getName(), oppanPropertyDto));
    }

    public String getValue(String name) {
        return map.get(name).getValue();
    }

    public boolean isEnabled(String name) {
        return map.get(name).isEnabled();
    }
}
