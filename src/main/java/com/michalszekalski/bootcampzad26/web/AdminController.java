package com.michalszekalski.bootcampzad26.web;

import com.michalszekalski.bootcampzad26.match.Match;
import com.michalszekalski.bootcampzad26.match.MatchRepository;
import com.michalszekalski.bootcampzad26.user.UserRegistrationDto;
import com.michalszekalski.bootcampzad26.user.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/admin")
class AdminController {

    private MatchRepository matchRepository;
    private UserService userService;

    public AdminController(MatchRepository matchRepository, UserService userService) {
        this.matchRepository = matchRepository;
        this.userService = userService;
    }

    @GetMapping("/addMatchForm")
    public String addMatchForm(Model model) {
        model.addAttribute("minDate", LocalDate.now().plusDays(1));
        model.addAttribute("newMatch", new Match());
        return "addMatch";
    }

    @PostMapping("/addMatch")
    public String addMatch(Match match) {
        matchRepository.save(match);
        return "redirect:/user/panel";
    }

    @GetMapping("/deleteModifyMatchList")
    public String deleteModifyMatchForm(Model model) {
        List<Match> matchesList = matchRepository.findAllOrderByDate();
        model.addAttribute("matchesList", matchesList);
        model.addAttribute("modifiedMatch", new Match());
        model.addAttribute("localDateNow", LocalDate.now());
        return "delModMatchList";
    }

    @PostMapping("/modifyMatchForm")
    public String modifyMatch(Long matchId, String matchResult) {
        Match matchToEdit = matchRepository.findByIdIs(matchId);
        if (matchResult.equals("")) {
            matchResult = null;
        }
        matchToEdit.setResult(matchResult);
        matchRepository.save(matchToEdit);
        return "redirect:/admin/deleteModifyMatchList";
    }

    @PostMapping("/deleteMatch")
    public String deleteMatch(Long matchId) {
        matchRepository.deleteById(matchId);
        return "redirect:/admin/deleteModifyMatchList";
    }

    @GetMapping("/viewUsers")
    public String usersList(Model model) {
        List<UserRegistrationDto> usersList = userService.findAllUsersExceptCurrent();
        model.addAttribute("userList", usersList);
        return "usersListForm";
    }

    @GetMapping("/manageRoles")
    public String manageRoles(@RequestParam String email) {
        userService.addRemoveAdminRole(email);
        return "redirect:viewUsers";
    }
}
