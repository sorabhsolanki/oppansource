package com.oppan.startup;

import com.oppan.cache.CacheManager;
import com.oppan.cache.OppanPropertyCache;
import com.oppan.controller.OppanController;
import com.oppan.dto.OppanPropertyDto;
import com.oppan.exception.GeneralException;
import com.oppan.user.OppanPropertyRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 */
@Configuration
public class StartupService {

    private static final Logger LOG = LoggerFactory.getLogger(OppanController.class);

    private final OppanPropertyRepository oppanPropertyRepository;

    @Autowired
    public StartupService(OppanPropertyRepository oppanPropertyRepository) {
        this.oppanPropertyRepository = oppanPropertyRepository;
    }

    @PostConstruct
    public void init() {
        reloadCache();
    }


    private void reloadCache() {
        CacheManager cacheManager = CacheManager.getInstance();
        try {
            populateOppanPropertyCache(cacheManager);
        } catch (GeneralException e) {
            LOG.error("Error while initializing cache : " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    private void populateOppanPropertyCache(CacheManager cacheManager) throws GeneralException {
        LOG.info("Populating oppan property in-memory local cache.");
        List<OppanPropertyDto> propertyDtoList = oppanPropertyRepository.getAll();
        OppanPropertyCache oppanPropertyCache = new OppanPropertyCache();
        oppanPropertyCache.insertAll(propertyDtoList);
        cacheManager.setCache(oppanPropertyCache);
    }

}
