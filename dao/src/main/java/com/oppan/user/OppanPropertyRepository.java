package com.oppan.user;

import com.oppan.dto.OppanPropertyDto;

import java.util.List;

/**
 */
public interface OppanPropertyRepository {

    List<OppanPropertyDto> getAll();
}
