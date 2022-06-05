package com.example.Aroundfootball.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="tournament")
public class Tournament {
    @Id
    @Column(name="id")
    private Long id;
    private String name;
    private String Country;






    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }
}
