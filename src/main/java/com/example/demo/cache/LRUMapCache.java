package com.example.demo.cache;

import com.example.demo.constants.CurrencyCode;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Getter
public abstract class LRUMapCache {

    private Map<CurrencyCode, CacheNode> cache = new HashMap<>();

    @Setter
    private CacheNode head;

    @Setter
    private CacheNode tail;
    private final int maxCapacity = 3;


    abstract void write(CurrencyCode key, BigDecimal value);
}
