package com.example.demo.cache;

import com.example.demo.constants.CurrencyCode;

import java.math.BigDecimal;

public interface CachingService {

    void writeToCache(CurrencyCode key, BigDecimal val);
}
