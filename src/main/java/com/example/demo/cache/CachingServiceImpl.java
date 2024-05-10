package com.example.demo.cache;

import com.example.demo.constants.CurrencyCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class CachingServiceImpl implements CachingService {

    private final LRUMapCache cache;

    @Override
    public void writeToCache(CurrencyCode key, BigDecimal val) {
        cache.write(key, val);
    }
}
