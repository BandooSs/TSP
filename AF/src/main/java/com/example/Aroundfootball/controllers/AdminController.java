package com.example.Aroundfootball.controllers;

import com.example.Aroundfootball.models.Team;
import com.example.Aroundfootball.models.Tournament;
import com.example.Aroundfootball.models.User;
import com.example.Aroundfootball.models.enums.Role;
import com.example.Aroundfootball.repositories.UserRepository;
import com.example.Aroundfootball.services.TeamService;
import com.example.Aroundfootball.services.TournamentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminController {
    private final UserRepository userRepository;
    private final TeamService teamService;
    private final TournamentService tournamentService;
    @GetMapping("/user")
    public String userList(Model model){
        model.addAttribute("users",userRepository.findAll());
        return "userList";
    }
    @PostMapping("/team/create")
    public String createTeam(@RequestParam("file") MultipartFile file, Team team)throws IOException {
        if(!tournamentService.getTourByID(team.getTournament().getId())||teamService.getTById(team.getId())) return "redirect:/team";
        teamService.saveTeam(team,file);
        return "redirect:/team";
    }
    @PostMapping("/team/delete")
    public String deleteTeam(@RequestParam(name="id",required = false) Long id, Model model){
        teamService.deleteTeam(id);
        return "redirect:/team";
    }
    @GetMapping("/team/{id}/edit")
    public String editTeam(@PathVariable Long id, Model model) throws IOException {
        Team team=teamService.getTeamById(id);
        model.addAttribute("team",team);
        model.addAttribute("images",team.getImages());
        return "team-edit";
    }
    @PostMapping("/team/{id}/edit/ee")
    public String editTeame(@PathVariable  Long id,@RequestParam("file")MultipartFile file, Team team, Model model) throws IOException {
        if(!tournamentService.getTourByID(team.getTournament().getId())) return "redirect:/team";
        if(id!=team.getId()){
            if(teamService.getTById(team.getId())){
                return "redirect:/team";
            }
        }
        teamService.deleteTeam(id);
        teamService.saveTeam(team,file);
        return "redirect:/team";
    }
    @PostMapping("/tournament/create")
    public String createTournament(Tournament tournament){
        if(tournamentService.getTourByID(tournament.getId())) return "redirect:/tournament";
        tournamentService.saveTournament(tournament);
        return "redirect:/tournament";
    }
    @PostMapping("/tournament/delete")
    public String deleteTournament(@RequestParam(name="id",required = false) Long id, Model model){
        tournamentService.deleteTournament(id);
        return "redirect:/tournament";
    }
    @PostMapping("/tournament/{id}/edit/ee")
    public String editTournamente(@PathVariable  Long id, Tournament tournament, Model model) throws IOException {
        if(id!=tournament.getId()) return "redirect:/tournament";
        tournamentService.deleteTournament(id);
        tournamentService.saveTournament(tournament);
        return "redirect:/tournament";
    }
    @GetMapping("/tournament/{id}/edit")
    public String editTournament(@PathVariable  Long id, Model model) throws IOException {

        Tournament tournament=tournamentService.getTournamentById(id);
        model.addAttribute("tout",tournament);
        return "tournament-edit";
    }
    @GetMapping("/user/{user}")
    public String infoUser(@PathVariable User user, Model model){
        model.addAttribute("us", user);
        model.addAttribute("roles", Role.values());
        return "user-info";
    }
    @PostMapping("/user")
    public String userSave(
            @RequestParam String username,
            @RequestParam Map<String, String> form,
            @RequestParam("userId") User user
    ) {
        user.setUsername(username);

        Set<String> roles = Arrays.stream(Role.values())
                .map(Role::name)
                .collect(Collectors.toSet());

        user.getRoles().clear();

        for (String key : form.keySet()) {
            if (roles.contains(key)) {
                user.getRoles().add(Role.valueOf(key));
            }
        }

        userRepository.save(user);

        return "redirect:/user";
    }

}
