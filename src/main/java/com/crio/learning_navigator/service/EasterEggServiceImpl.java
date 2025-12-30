package com.crio.learning_navigator.service;

import org.springframework.stereotype.Service;

@Service
public class EasterEggServiceImpl implements EasterEggService {

    @Override
    public String getNumberFact(int number) {
        return "This is a hidden number fact for number " + number;
    }
}
