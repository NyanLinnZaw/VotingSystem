package com.example.votingsystem.controller;

import com.example.votingsystem.service.VoteService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/votes")
public class VoteResultController {

    private final VoteService voteService;

    public VoteResultController(VoteService voteService) {
        this.voteService = voteService;
    }

    @GetMapping("/count")
    public Map<String, Long> getVoteCount() {
        return voteService.getResults();
    }
}
