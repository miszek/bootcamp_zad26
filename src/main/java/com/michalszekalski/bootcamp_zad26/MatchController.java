package com.michalszekalski.bootcamp_zad26;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MatchController {

    private MatchRepository matchRepository;
    private BetRepository betRepository;

    public MatchController(MatchRepository matchRepository, BetRepository betRepository) {
        this.matchRepository = matchRepository;
        this.betRepository = betRepository;
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
