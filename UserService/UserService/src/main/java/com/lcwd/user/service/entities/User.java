package com.lcwd.user.service.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "micro_User")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @Column(name = "Id")
    private String userId;

    @Column(name = "Name")
    private String name;

    @Column(name="Email")
    private String email;

    @Column(name = "About")
    private String about;


    @Transient
    private List<Rating> ratings =new ArrayList<>();

}
