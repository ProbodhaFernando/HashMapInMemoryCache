package com.example.demo.cache;

import com.example.demo.constants.CurrencyCode;

import java.math.BigDecimal;

public class CacheNode {

    CurrencyCode key;
    BigDecimal value;
    CacheNode prev;
    CacheNode next;

}
