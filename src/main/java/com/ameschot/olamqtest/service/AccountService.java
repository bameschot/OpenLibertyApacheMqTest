package com.ameschot.olamqtest.service;

import lombok.Getter;

import javax.ejb.Singleton;
import java.util.HashMap;
import java.util.Map;

@Singleton
public class AccountService {
    @Getter
    private Map<String, Float> accounts = new HashMap<>();
}
