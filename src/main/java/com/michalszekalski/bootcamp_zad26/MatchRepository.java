package com.michalszekalski.bootcamp_zad26;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface MatchRepository extends JpaRepository<Match, Long> {

    Match findByIdIs(Long id);

    @Query("SELECT m FROM Match m ORDER BY m.date")
    List<Match> findAllOrderByDate();

    List<Match> findByResultIsNullAndDateIsAfterOrderByDate(LocalDate localDate);
}
