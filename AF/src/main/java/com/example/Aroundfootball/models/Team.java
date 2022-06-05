package com.example.Aroundfootball.models;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="team")
public class Team {
    @Id
    @Column(name="id")
    private Long id;
    @Column(name="name")
    private String name;
    @Column(name="city")
    private String city;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    Tournament tournament;
    public Team(Long id, String name, String city) {
        this.id = id;
        this.name = name;
        this.city = city;

    }
    public Team() {

    }
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "team")
    private List<Image> images=new ArrayList<>();

    public Tournament getTournament() {
        return tournament;
    }
    public void addImageToTeam(Image image){
        image.setTeam(this);
        images.add(image);
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    public void setTournament(Tournament tournament) {
        this.tournament = tournament;
    }

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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "Team{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}
