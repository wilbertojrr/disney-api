package com.alkemy.disneyApi.auth.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Getter
@Setter
@Entity
@Table(name = "user")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Email
    private String username;
    @Size(min = 8)
    private String password;
    private boolean accountNonExpire;
    private boolean accountNonLocked;
    private boolean credentialNonExpire;
    private boolean enabled;

    public UserEntity() {
        this.accountNonExpire = true;
        this.accountNonLocked = true;
        this.credentialNonExpire = true;
        this.enabled = true;
    }
}
