package com.example.votingsystem.controller;

import com.example.votingsystem.dto.VoteRequest;
import com.example.votingsystem.dto.VoteResult;
import com.example.votingsystem.service.VoteService;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class VoteController {

    private final VoteService voteService;
    private final SimpMessagingTemplate messagingTemplate;

    public VoteController(VoteService voteService,
                          SimpMessagingTemplate messagingTemplate) {
        this.voteService = voteService;
        this.messagingTemplate = messagingTemplate;
    }

    @MessageMapping("/vote")
    public void vote(VoteRequest request) {

        // 1. Save vote to DB
        voteService.saveVote(request.getOption());

        // 2. Fetch updated result
        VoteResult result =
                new VoteResult(voteService.getResults());

        // 3. Broadcast to all users
        messagingTemplate.convertAndSend("/topic/results", result);
    }
}
