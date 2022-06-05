package com.example.Aroundfootball.repositories;

import com.example.Aroundfootball.models.Team;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeamRepository extends JpaRepository<Team,Long> {
    List<Team> findTeamByid(Long idTournament);
}
