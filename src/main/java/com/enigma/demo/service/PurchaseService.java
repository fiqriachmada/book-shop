package com.enigma.demo.service;

import com.enigma.demo.entity.Purchase;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface PurchaseService {
    void transaction(Purchase purchase) throws JsonProcessingException;
}
