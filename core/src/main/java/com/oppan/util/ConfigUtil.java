package com.oppan.util;

import com.oppan.cache.CacheManager;
import com.oppan.cache.OppanPropertyCache;
import com.oppan.exception.GeneralException;

/**
 * Class for accessing the score property value, if not found then return the default value.
 */
public class ConfigUtil {

    private static final CacheManager cacheManager = CacheManager.getInstance();

    public static <T> T getPropertyValue(OppansourcePropertyEnum oppansourcePropertyEnum, Class<T> type)
            throws GeneralException {

        OppanPropertyCache oppanPropertyCache = cacheManager.getCache(OppanPropertyCache.class);
        String value = oppanPropertyCache.getValue(oppansourcePropertyEnum.getPropertyName());

        if (value == null) {
            //return default value
            return type.cast(oppansourcePropertyEnum.getValue());
        }
        T t = null;

        if (type == Boolean.class) {
            t = type.cast(Boolean.valueOf(value));
        } else if (type == String.class) {
            t = type.cast(String.valueOf(value));
        } else if (type == Integer.class){
            t = type.cast(Integer.valueOf(value));
        }
        return t;
    }
}
