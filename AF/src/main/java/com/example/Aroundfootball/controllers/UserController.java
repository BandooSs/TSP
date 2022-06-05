package com.example.Aroundfootball.controllers;

import com.example.Aroundfootball.models.Team;
import com.example.Aroundfootball.services.TeamService;
import com.example.Aroundfootball.services.TournamentService;
import com.example.Aroundfootball.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final TeamService teamService;
    private final TournamentService tournamentService;
    private final UserService userService;
    @GetMapping("/")
    public String mai(Model model,Principal principal){
        model.addAttribute("us",userService.getUserByPrincipal(principal));
        return"main";
    }
    @GetMapping("/main")
    public String t(@RequestParam(name="id",required = false) Long id, Model model, Principal principal){
        model.addAttribute("teams",teamService.listTeam(id));
        model.addAttribute("us",userService.getUserByPrincipal(principal));
        return "teams";
    }
    @GetMapping("/team")
    public String teams(@RequestParam(name="id",required = false ) Long id, Model model,Principal principal){
        model.addAttribute("teams",teamService.listTeam(id));
        model.addAttribute("us",userService.getUserByPrincipal(principal));
        return "teams";
    }
    @PostMapping("/team")
    public String teamsq(@RequestParam(name="id",required = false ) Long id, Model model,Principal principal){
        model.addAttribute("teams",teamService.listTeam(id));
        model.addAttribute("us",userService.getUserByPrincipal(principal));
        return "teams";
    }
    @GetMapping("/team/{id}")
    public String teamInfo(@PathVariable Long id, Model model,Principal principal){
        Team team=teamService.getTeamById(id);
        model.addAttribute("team",team);
        model.addAttribute("images",team.getImages());
        model.addAttribute("us",userService.getUserByPrincipal(principal));
        return "team-info";
    }
    @GetMapping("/tournament")
    public String Tournament(@RequestParam(name="idTournament",required = false ) Long idTournament, Model model,Principal principal){
        model.addAttribute("tout",tournamentService.listTournament(idTournament));
        model.addAttribute("us",userService.getUserByPrincipal(principal));
        return "tournaments";
    }

    @GetMapping("/tournament/{id}")
    public String tournamentInfo(@PathVariable Long id, Model model,Principal principal){
        model.addAttribute("tout",tournamentService.getTournamentById(id));
        model.addAttribute("us",userService.getUserByPrincipal(principal));
        return "tournament-info";
    }
//    @PostMapping("/tournament/delete/{id}")
//    public String deletetournament(@PathVariable Long id){
//        tournamentService.deleteTournament(id);
//        return "redirect:/tournament";
//    }

}
