package com.michalszekalski.bootcampzad26.bet;

import com.michalszekalski.bootcampzad26.match.Winner;
import com.michalszekalski.bootcampzad26.match.Match;
import jakarta.persistence.*;

@Entity
public class Bet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private long betValue;

    private String typerName;

    @Enumerated(EnumType.STRING)
    private Winner winner;

    @ManyToOne
    private Match match;

    private String idString;

    public Bet() {
    }

    public String getIdString() {
        return idString;
    }

    public void setIdString(String idString) {
        this.idString = idString;
    }

    public Winner getWinner() {
        return winner;
    }

    public long getBetValue() {
        return betValue;
    }

    public void setBetValue(long betValue) {
        this.betValue = betValue;
    }

    public void setWinner(Winner winner) {
        this.winner = winner;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTyperName() {
        return typerName;
    }

    public void setTyperName(String typerName) {
        this.typerName = typerName;
    }

    public Match getMatch() {
        return match;
    }

    public void setMatch(Match match) {
        this.match = match;
    }
}
