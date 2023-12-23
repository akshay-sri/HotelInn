package com.user.UserService.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User {
    @Id
    private String userId;
    private String name;
    private String email;
    private String about;
    private String password;
    @Transient
    private List<Rating> ratings=new ArrayList<>();
}
