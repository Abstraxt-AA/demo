package com.example.demo.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue
    @Column(columnDefinition = "uuid")
    private UUID id;


    @NotEmpty
    @Column(unique = true)
    private String username;


    @NotEmpty
    @Column
    private String password;

    @Column
    @NotNull
    private Roles role = Roles.CUSTOMER;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Customer customer;

    public User(String username, String password, Roles role, Customer customer) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.customer = customer;
    }

    public enum Roles {
        ADMIN,
        CUSTOMER
    }
}
