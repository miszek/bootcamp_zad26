package com.michalszekalski.bootcamp_zad26;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

@Controller
public class HomeController {

    private MatchRepository matchRepository;

    public HomeController(MatchRepository matchRepository) {
        this.matchRepository = matchRepository;
    }

    @GetMapping("/")
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
        return "home";
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
            Match mostPopularMatch = matchRepository.findByIdIs(maxId);
            return mostPopularMatch;
        } else {
            return null;
        }
    }
}
