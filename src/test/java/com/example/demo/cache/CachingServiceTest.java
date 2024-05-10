package com.example.demo.cache;

import com.example.demo.constants.CurrencyCode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CachingServiceTest {

    private CachingService cachingService;
    private LRUMapCache cache;


    @BeforeEach
    public void setup() {
        cache = new LRUMapCacheImpl();
        cachingService = new CachingServiceImpl(cache);
    }


    @Test
    public void insertValueToEmptyCacheTest() {
        CurrencyCode key = CurrencyCode.MYR;
        BigDecimal val = new BigDecimal("62.3");

        cachingService.writeToCache(key, val);

        Map<CurrencyCode, CacheNode> resultCache = cache.getCache();

        assertNotNull(resultCache);
        assertEquals(1, resultCache.size());
        assertEquals(val, resultCache.get(key).value);
        assertEquals(key, cache.getHead().key);
    }

    @Test
    public void insertMultipleValuesToEmptyCacheTest() {
        CurrencyCode key1 = CurrencyCode.MYR;
        BigDecimal val1 = new BigDecimal("62.3");

        CurrencyCode key2 = CurrencyCode.LKR;
        BigDecimal val2 = new BigDecimal("1");

        CurrencyCode key3 = CurrencyCode.USD;
        BigDecimal val3 = new BigDecimal("104.5");

        cachingService.writeToCache(key1, val1);
        cachingService.writeToCache(key2, val2);
        cachingService.writeToCache(key3, val3);

        Map<CurrencyCode, CacheNode> resultCache = cache.getCache();

        assertNotNull(resultCache);
        assertEquals(3, resultCache.size());
        assertEquals(val1, resultCache.get(key1).value);
        assertEquals(val2, resultCache.get(key2).value);
        assertEquals(val3, resultCache.get(key3).value);
        assertEquals(key3, cache.getHead().key);
        assertEquals(key1, cache.getTail().key);
    }

    @Test
    public void cacheEvictionTest() {
        CurrencyCode key1 = CurrencyCode.MYR;
        BigDecimal val1 = new BigDecimal("62.3");

        CurrencyCode key2 = CurrencyCode.LKR;
        BigDecimal val2 = new BigDecimal("1");

        CurrencyCode key3 = CurrencyCode.USD;
        BigDecimal val3 = new BigDecimal("104.5");

        cachingService.writeToCache(key1, val1);
        cachingService.writeToCache(key2, val2);
        cachingService.writeToCache(key3, val3);

        Map<CurrencyCode, CacheNode> resultCache = cache.getCache();

        assertNotNull(resultCache);
        assertEquals(3, resultCache.size());
        assertEquals(val1, resultCache.get(key1).value);
        assertEquals(val2, resultCache.get(key2).value);
        assertEquals(val3, resultCache.get(key3).value);
        assertEquals(key3, cache.getHead().key);
        assertEquals(key1, cache.getTail().key);

        CurrencyCode key4 = CurrencyCode.SGD;
        BigDecimal val4 = new BigDecimal("90.5");

        cachingService.writeToCache(key4, val4);

        resultCache = cache.getCache();

        assertEquals(3, resultCache.size());
        assertEquals(key4, cache.getHead().key);
        assertEquals(key2, cache.getTail().key);

        CurrencyCode key5 = CurrencyCode.RYL;
        BigDecimal val5 = new BigDecimal("84.5");

        cachingService.writeToCache(key5, val5);

        resultCache = cache.getCache();

        assertEquals(3, resultCache.size());
        assertEquals(key5, cache.getHead().key);
        assertEquals(key3, cache.getTail().key);
    }

    @Test
    public void readCacheTest() {

    }

}
