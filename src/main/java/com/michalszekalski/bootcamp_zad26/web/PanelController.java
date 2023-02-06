package com.michalszekalski.bootcamp_zad26.web;

import com.michalszekalski.bootcamp_zad26.bet.Bet;
import com.michalszekalski.bootcamp_zad26.bet.BetRepository;
import com.michalszekalski.bootcamp_zad26.bet.IdGenerator;
import com.michalszekalski.bootcamp_zad26.bet.PrizeCounter;
import com.michalszekalski.bootcamp_zad26.match.Match;
import com.michalszekalski.bootcamp_zad26.match.MatchRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/user")
class PanelController {

    private MatchRepository matchRepository;
    private final BetRepository betRepository;

    public PanelController(MatchRepository matchRepository,
                           BetRepository betRepository) {
        this.matchRepository = matchRepository;
        this.betRepository = betRepository;
    }

    @GetMapping
    public String home(@RequestParam(required = false, defaultValue = "false") Boolean toBetMatches, Model model) {
        List<Match> matchesList;
        if (!toBetMatches) {
            matchesList = matchRepository.findAllOrderByDate();
        } else {
            matchesList = matchRepository.findByResultIsNullAndDateIsAfterOrderByDate(LocalDate.now());
        }

        model.addAttribute("matchesList", matchesList);
        model.addAttribute("localDateNow", LocalDate.now());
        model.addAttribute("toBetMatches", toBetMatches);
        Match mostPopularMatch = getMostPopularMatch(matchesList);
        model.addAttribute("mostPopularMatch", mostPopularMatch);
        return "panel";
    }

    @RequestMapping("/enterBetIdForm")
    public String enterBetId() {
        return "enterBetIdForm";
    }

    @GetMapping("/checkPrize")
    public String checkPrize(@RequestParam String betId, Model model) {
        Optional<Bet> betOptional = betRepository.findByIdString(betId);
        Bet bet;
        if (betOptional.isPresent()) {
            bet = betOptional.get();
        } else {
            model.addAttribute("betId", betId);
            return "betNotFound";
        }

        Match match = bet.getMatch();
        if (match.getResult() == null || match.getResult().equals("")) {
            model.addAttribute("match", match.getTeamA() + " vs " + match.getTeamB());
            model.addAttribute("betId", betId);
            return "resultNotPresent";
        }

        Double prize = PrizeCounter.checkPrize(match, bet);

        model.addAttribute("bet", bet);
        model.addAttribute("match", match);
        model.addAttribute("prize", prize);
        return "displayPrize";
    }

    private Match getMostPopularMatch(List<Match> matchesList) {
        Long maxId = 0L;
        int noOfResults = 0;
        for (Match match : matchesList) {
            if (match.getDate().isAfter(LocalDate.now()) && (match.getResult() == null || match.getResult().equals("")) && match.getResultTypeList().size() > noOfResults) {
                maxId = match.getId();
                noOfResults = match.getResultTypeList().size();
            }
        }
        if (maxId != 0) {
            return matchRepository.findByIdIs(maxId);
        } else {
            return null;
        }
    }

    @PostMapping("/addBetForm")
    public String addBetForm(@RequestParam Long id, Model model) {
        Match matchToBet = matchRepository.findByIdIs(id);
        model.addAttribute("matchToBet", matchToBet);
        model.addAttribute("bet", new Bet());
        return "addBet";
    }

    @PostMapping("/addBet/{id}")
    public String addBetForm(@PathVariable Long id, Bet bet, Model model) {
        Match matchToBet = matchRepository.findByIdIs(id);
        bet.setMatch(matchToBet);
        bet.setId(null);
        bet.setIdString(IdGenerator.generate(id));
        betRepository.save(bet);
        model.addAttribute("matchToBet", matchToBet);
        model.addAttribute("bet", bet);
        return "betDisplay";
    }
}
