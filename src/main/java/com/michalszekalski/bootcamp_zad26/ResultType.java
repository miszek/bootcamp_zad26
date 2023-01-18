package com.michalszekalski.bootcamp_zad26;

import jakarta.persistence.*;

@Entity
public class ResultType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String typedResult;

    private String typerName;

    @ManyToOne
    private Match match;

    public ResultType() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTypedResult() {
        return typedResult;
    }

    public void setTypedResult(String typedResult) {
        this.typedResult = typedResult;
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
