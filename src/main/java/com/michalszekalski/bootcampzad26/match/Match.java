package com.michalszekalski.bootcampzad26.match;

import com.michalszekalski.bootcampzad26.bet.Bet;
import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

@Entity
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    private String teamA;

    private String teamB;

    private String result;

    private Double winACourse;

    private Double drawCourse;

    private Double winBCourse;

    public Double getWinACourse() {
        return winACourse;
    }

    public void setWinACourse(Double winACourse) {
        this.winACourse = winACourse;
    }

    public Double getDrawCourse() {
        return drawCourse;
    }

    public void setDrawCourse(Double drawCourse) {
        this.drawCourse = drawCourse;
    }

    public Double getWinBCourse() {
        return winBCourse;
    }

    public void setWinBCourse(Double winBCourse) {
        this.winBCourse = winBCourse;
    }

    @OneToMany(mappedBy = "match", cascade = CascadeType.REMOVE)
    private List<Bet> betList;

    public List<Bet> getResultTypeList() {
        return betList;
    }

    public void setResultTypeList(List<Bet> betList) {
        this.betList = betList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getTeamA() {
        return teamA;
    }

    public void setTeamA(String teamA) {
        this.teamA = teamA;
    }

    public String getTeamB() {
        return teamB;
    }

    public void setTeamB(String teamB) {
        this.teamB = teamB;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
