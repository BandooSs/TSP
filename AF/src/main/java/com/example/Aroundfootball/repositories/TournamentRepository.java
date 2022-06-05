package com.example.Aroundfootball.repositories;

import com.example.Aroundfootball.models.Tournament;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TournamentRepository extends JpaRepository<Tournament,Long> {
    List<Tournament> findTournamentById(Long idTournament);
}
