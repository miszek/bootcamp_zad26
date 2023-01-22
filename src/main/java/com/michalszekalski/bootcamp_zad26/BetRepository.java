package com.michalszekalski.bootcamp_zad26;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BetRepository extends JpaRepository<Bet, Long> {
    List<Bet> findAllByMatch(Match match);

    Optional<Bet> findByIdString(String idString);
}
