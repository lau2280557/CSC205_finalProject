package com.example.aggregator.service;

import com.example.aggregator.client.AggregatorRestClient;
import com.example.aggregator.model.Entry;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AggregatorService {

    private final AggregatorRestClient aggregatorRestClient;

    public AggregatorService(AggregatorRestClient aggregatorRestClient) {
        this.aggregatorRestClient = aggregatorRestClient;
    }

    public Entry getDefinitionFor(String word) {

        return aggregatorRestClient.getDefinitionFor(word);
    }

    public List<Entry> getWordsThatContainSuccessiveLettersAndStartsWith(String chars) {
        List<Entry> wordsThatStartWith = aggregatorRestClient.getWordsStartingWith(chars);
        List<Entry> wordsThatContainConsecutiveLetters = aggregatorRestClient.getWordsThatContainConsecutiveLetters();

        List<Entry> common = wordsThatStartWith.stream()
                .filter(wordsThatContainConsecutiveLetters::contains)
                .collect(Collectors.toList());
        return common;
    }

    public List<Entry> getWordsThatContain(String chars) {
        return aggregatorRestClient.getWordsThatContain(chars);
    }

}
