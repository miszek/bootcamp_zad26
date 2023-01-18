package com.michalszekalski.bootcamp_zad26;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ResultTypeRepository extends JpaRepository<ResultType, Long> {
    List<ResultType> findAllByMatch(Match match);
}
