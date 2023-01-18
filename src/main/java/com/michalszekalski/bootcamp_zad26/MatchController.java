package com.michalszekalski.bootcamp_zad26;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class MatchController {

    private MatchRepository matchRepository;
    private ResultTypeRepository resultTypeRepository;

    public MatchController(MatchRepository matchRepository, ResultTypeRepository resultTypeRepository) {
        this.matchRepository = matchRepository;
        this.resultTypeRepository = resultTypeRepository;
    }

    @PostMapping("/addBetForm")
    public String addBetForm(@RequestParam Long id, Model model) {
        Match matchToBet = matchRepository.findByIdIs(id);
        model.addAttribute("matchToBet", matchToBet);
        model.addAttribute("type", new ResultType());
        return "addBet";
    }

    @PostMapping("/addBet/{id}")
    public String addBetForm(@PathVariable Long id, ResultType type) {
        Match matchToType = matchRepository.findByIdIs(id);
        type.setMatch(matchToType);
        type.setId(null);
        resultTypeRepository.save(type);
        return "redirect:/";
    }

    @PostMapping("/checkResultsForm")
    public String checkResultsForm(@RequestParam Long id, Model model) {
        Match match = matchRepository.findByIdIs(id);
        List<ResultType> results = resultTypeRepository.findAllByMatch(match);
        model.addAttribute("match", match);
        model.addAttribute("results", results);
        return "resultsList";
    }
}
