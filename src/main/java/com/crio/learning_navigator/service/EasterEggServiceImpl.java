package com.crio.learning_navigator.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class EasterEggServiceImpl implements EasterEggService {

    private final RestTemplate restTemplate;

    public EasterEggServiceImpl(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    @Override
    public String getNumberFact(int number){
        String url = UriComponentsBuilder.fromHttpUrl("http://numbersapi.com/" + number)
                .queryParam("json", "").toUriString();

        NumberApiResponse response = restTemplate.getForObject(url, NumberApiResponse.class);
        if(response == null || response.getText() == null){
            return "Great! You have found the hidden number fact, but no fact is available.";
        }
        return "Great! You have found the hidden number fact, " + response.getText();
    }

    private static class NumberApiResponse {
        private String text;
        public String getText(){ return text; }
        public void setText(String text){
             this.text = text;  
            }
    }
}
