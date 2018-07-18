package com.oppan.user.impl;

import com.oppan.dto.OppanPropertyDto;
import com.oppan.user.OppanPropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository class of Collection oppan_property
 */
@Repository
public class OppanPropertyRepositoryImpl implements OppanPropertyRepository{

    private final MongoTemplate mongoTemplate;

    @Autowired
    public OppanPropertyRepositoryImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public List<OppanPropertyDto> getAll() {
        List<OppanPropertyDto> propertyDtoList = mongoTemplate.findAll(OppanPropertyDto.class);
        return propertyDtoList;
    }
}
