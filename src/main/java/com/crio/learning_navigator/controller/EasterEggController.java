package com.crio.learning_navigator.controller;

import com.crio.learning_navigator.service.EasterEggService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/easter-egg")
public class EasterEggController {

    private final EasterEggService easterEggService;

    public EasterEggController(EasterEggService easterEggService) {
        this.easterEggService = easterEggService;
    }

    @GetMapping("/hidden-feature/{number}")
    public ResponseEntity<Map<String, String>> hiddenFeature(
            @PathVariable int number) {

        return ResponseEntity.ok(Map.of(
                "message", "Great! You have found the hidden number fact",
                "response", easterEggService.getNumberFact(number)
        ));
    }
}
