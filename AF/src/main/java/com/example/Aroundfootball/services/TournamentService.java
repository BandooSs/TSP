package com.example.Aroundfootball.services;

import com.example.Aroundfootball.models.Team;
import com.example.Aroundfootball.models.Tournament;
import com.example.Aroundfootball.repositories.TournamentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class TournamentService {
    private final TournamentRepository tournamentRepository;
    public List<Tournament> listTournament(Long idTournament){
        if(idTournament!=null) return tournamentRepository.findTournamentById(idTournament);
        return  tournamentRepository.findAll();
    }
    public void saveTournament(Tournament tournament){
        log.info("Saving new {}",tournament);
        tournamentRepository.save(tournament);
    }
    public void deleteTournament(Long ID){
        tournamentRepository.deleteById(ID);
    }
    public Tournament getTournamentById(Long ID){
        return tournamentRepository.findById(ID).orElse(null);
    }
    public boolean getTourByID(Long Id){
        return tournamentRepository.existsById(Id);
    }
}
