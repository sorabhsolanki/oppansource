package com.oppan.cache;

import com.oppan.annotation.Cache;
import com.oppan.exception.GeneralException;
import com.oppan.util.ErrorCodeConstants;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/*
CacheManager class for maintaining different caches inside it and will hold the @cache(name)
i.e. annotation name as key, and value as its object.
 */
public class CacheManager {

    private static volatile CacheManager cacheManager = null;
    private final Map<String, Object> caches = new ConcurrentHashMap<>();

    private CacheManager() {
    }

    public static CacheManager getInstance() {
        if (cacheManager == null) {
            synchronized (CacheManager.class) {
                if (cacheManager == null) {
                    cacheManager = new CacheManager();
                }
            }
        }
        return cacheManager;
    }

    public <T> T getCache(Class<T> cacheClass) throws GeneralException {
        if (cacheClass.isAnnotationPresent(Cache.class)) {
            return (T) getCache(cacheClass.getAnnotation(Cache.class).name());
        }
        throw new GeneralException(ErrorCodeConstants.INTERNAL_SERVER_ERROR.getErrorCode(), "@Cache annotation should be present at the class");
    }

    public void setCache(Object cache) throws GeneralException {
        Class<?> cacheClass = cache.getClass();
        if (cacheClass.isAnnotationPresent(Cache.class)) {
            Cache annotation = cacheClass.getAnnotation(Cache.class);
            caches.put(annotation.name(), cache);
            return;
        }
        throw new GeneralException(ErrorCodeConstants.INTERNAL_SERVER_ERROR.getErrorCode(), "@Cache annotation should be present at the class");
    }

    private Object getCache(String name) {
        return caches.get(name);
    }

}
