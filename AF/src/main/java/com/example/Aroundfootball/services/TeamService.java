package com.example.Aroundfootball.services;

import com.example.Aroundfootball.models.Image;
import com.example.Aroundfootball.models.Team;
import com.example.Aroundfootball.repositories.TeamRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class TeamService {
    private  final TeamRepository teamRepository;



    public List<Team> listTeam(Long idTournament){
        if(idTournament!=null) return teamRepository.findTeamByid(idTournament);
        return  teamRepository.findAll();
    }
    public  void saveTeam(Team team, MultipartFile file) throws IOException {
        Image image;
        if(file.getSize()!=0){
            image=toImageEntity(file);
            image.setPreviewImage(true);
            team.addImageToTeam(image);
        }
        log.info("Saving new Team. Id: {}; Name: {}", team.getId(),team.getName());
        teamRepository.save(team);
    }
    private Image toImageEntity(MultipartFile file) throws IOException {
        Image image=new Image();
        image.setName(file.getName());
        image.setOriginalFileNAme(file.getOriginalFilename());
        image.setContentType(file.getContentType());
        image.setSize(file.getSize());
        image.setBytes(file.getBytes());
        return image;
    }
    public void deleteTeam(Long ID){
        teamRepository.deleteById(ID);
    }
    public Team getTeamById(Long ID){
        return teamRepository.findById(ID).orElse(null);
    }
    public boolean getTById(Long id){
        return teamRepository.existsById(id);
    }
}
