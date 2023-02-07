package com.michalszekalski.bootcampzad26.bet;

import com.michalszekalski.bootcampzad26.match.Winner;
import com.michalszekalski.bootcampzad26.match.Match;

public class PrizeCounter {

    public static Double checkPrize(Match match, Bet bet) {
        Double course = getCourse(match);
        if (checkWinner(match.getResult()) == bet.getWinner()) {
            return course * bet.getBetValue();
        }
        return 0D;
    }

    private static Double getCourse(Match match) {
        Winner winner = checkWinner(match.getResult());
        switch (winner) {
            case WINA -> {
                return match.getWinACourse();
            }
            case WINB -> {
                return match.getWinBCourse();
            }
            case DRAW -> {
                return match.getDrawCourse();
            }
        }
        return 0D;
    }

    private static Winner checkWinner(String result) {
        String[] scores = result.split(":");
        int teamAScore = Integer.parseInt(scores[0]);
        int teamBScore = Integer.parseInt(scores[1]);
        if (teamAScore > teamBScore) {
            return Winner.WINA;
        } else if (teamAScore == teamBScore) {
            return Winner.DRAW;
        } else {
            return Winner.WINB;
        }
    }
}
