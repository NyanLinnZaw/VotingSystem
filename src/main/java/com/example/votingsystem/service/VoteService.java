package com.example.votingsystem.service;

import com.example.votingsystem.entity.Vote;
import com.example.votingsystem.repository.VoteRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class VoteService {

    private final VoteRepository voteRepository;

    public VoteService(VoteRepository voteRepository) {
        this.voteRepository = voteRepository;
    }

    public void saveVote(String option) {
        voteRepository.save(new Vote(option));
    }

    public Map<String, Long> getResults() {

        Map<String, Long> result = new HashMap<>();

        for (Object[] row : voteRepository.countVotes()) {
            result.put((String) row[0], (Long) row[1]);
        }

        return result;
    }
}
