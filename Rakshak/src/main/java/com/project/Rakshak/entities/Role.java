package com.project.Rakshak.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "roles")
public class Role {

    @Id
    @Column(name = "roleName", nullable = false, unique = true)
    private String roleName;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "role")
    private List<User> users;

}
