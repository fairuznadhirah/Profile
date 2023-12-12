package com.fairuz.profile.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="Profile")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Profile {

    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private String age;

    @Column(name = "DOB")
    private String DOB;

    @Column(name = "state")
    private String state;
}
