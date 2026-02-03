package com.example.votingsystem.repository;

import com.example.votingsystem.entity.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VoteRepository extends JpaRepository<Vote, Long> {


    @Query("SELECT v.option, COUNT(v) FROM Vote v GROUP BY v.option")
    List<Object[]> countVotes();
}
