package com.home.gerenciadordeprojetos.model;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.springframework.data.annotation.ReadOnlyProperty;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Project {
    
    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 60)
    private String name;

    @ManyToOne
    private User user;

    @JsonIgnore
    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    List<Technology> technologies = new ArrayList<>();

    @ReadOnlyProperty
    private OffsetDateTime dateInit;

    @ReadOnlyProperty
    private OffsetDateTime dateFinal;


    public void initProject(){
        dateInit = OffsetDateTime.now();
    }

    public Project finalProject(){
        dateFinal = OffsetDateTime.now();
        return this;
    }

    public Technology addTechnologies(String nameTechnology){
        Technology technology = new Technology();
        technology.setName(nameTechnology);
        technology.setProject(this);

        this.getTechnologies().add(technology);
        return technology;
    }
}
 