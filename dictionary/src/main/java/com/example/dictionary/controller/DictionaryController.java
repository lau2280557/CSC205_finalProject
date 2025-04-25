package com.example.dictionary.controller;


import com.example.dictionary.exception.WordNotFoundException;
import com.example.dictionary.model.Entry;
import com.example.dictionary.service.DictionaryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DictionaryController {

    private final DictionaryService dictionaryService;
    private static final Logger logger = LoggerFactory.getLogger(DictionaryController.class.getName());

    public DictionaryController(DictionaryService dictionaryService) {
        this.dictionaryService = dictionaryService;
    }

    @GetMapping("/getWord/{word}")
    public Entry getWord(@PathVariable String word) throws WordNotFoundException{
        StopWatch sw = new StopWatch();
        sw.start();

        Entry entry = this.dictionaryService.getWord(word);
        sw.stop();

        long nanoseconds = sw.getLastTaskTimeNanos();
        String message = new StringBuilder().append("Retrieved entry for ")
                .append(word)
                .append(" found in ")
                .append(nanoseconds / 1000000.0)
                .append(" ms").toString();

        logger.info(message);

        return entry;
    }

    @GetMapping("/getWordsStartingWith/{s}")
    public List<Entry> getWordsStartingWith(@PathVariable String s) {
        StopWatch sw = new StopWatch();
        sw.start();

        List<Entry> entries = this.dictionaryService.getWordsStartingWith(s);
        sw.stop();

        long nanoseconds = sw.getLastTaskTimeNanos();
        String message = new StringBuilder().append("Retrieved ")
                .append(entries.size())
                .append(" entries starting with ")
                .append(s)
                .append(" in ")
                .append(nanoseconds / 1000000.0)
                .append(" ms").toString();

        logger.info(message);

        return entries;
    }

    @GetMapping("/getWordsThatContain/{s}")
    public List<Entry> getWordsThatContain(@PathVariable String s) {
        StopWatch sw = new StopWatch();
        sw.start();

        List<Entry> entries = this.dictionaryService.getWordsThatContain(s);
        sw.stop();

        long nanoseconds = sw.getLastTaskTimeNanos();
        String message = new StringBuilder().append("Retrieved ")
                .append(entries.size())
                .append(" entries containing ")
                .append(s)
                .append(" in ")
                .append(nanoseconds / 1000000.0)
                .append(" ms").toString();

        logger.info(message);

        return entries;
    }

    @GetMapping("/getWordsThatContainConsecutiveLetters")
    public List<Entry> getWordsThatContainConsecutiveLetters() {
        StopWatch sw = new StopWatch();
        sw.start();

        List<Entry> entries = this.dictionaryService.getWordsThatContainConsecutiveDoubleLetters();
        sw.stop();

        long nanoseconds = sw.getLastTaskTimeNanos();
        String message = new StringBuilder().append("Retrieved ")
                .append(entries.size())
                .append(" entries containing consecutive letters in ")
                .append(nanoseconds / 1000000.0)
                .append(" ms").toString();

        logger.info(message);

        return entries;
    }

}
