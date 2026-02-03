package com.example.votingsystem.dto;

import java.util.Map;

public class VoteResult {

    private Map<String, Long> results;

    public VoteResult(Map<String, Long> results) {
        this.results = results;
    }

    public Map<String, Long> getResults() {
        return results;
    }
}
