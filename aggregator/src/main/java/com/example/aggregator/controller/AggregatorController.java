package com.example.aggregator.controller;


import com.example.aggregator.model.Entry;
import com.example.aggregator.service.AggregatorService;
import io.github.resilience4j.core.StopWatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AggregatorController {

    private static final Logger logger = LoggerFactory.getLogger(AggregatorController.class.getName());
    private final AggregatorService aggregatorService;

    public AggregatorController(AggregatorService aggregatorService) {
        this.aggregatorService = aggregatorService;
    }

    @GetMapping
    public List<Entry> helloWorld() {
        List<Entry> entries = List.of(
                aggregatorService.getDefinitionFor("hello"),
                aggregatorService.getDefinitionFor("world"));
        return entries;
    }
    @GetMapping("getDefinitionFor/{word}")
    public Entry getDefinitionFor(@PathVariable String word) {

        Entry entry = aggregatorService.getDefinitionFor(word);
        return entry;
    }
}
