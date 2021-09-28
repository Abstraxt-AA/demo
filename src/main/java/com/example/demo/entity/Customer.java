package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.UUID;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Customer {

    @Id
    @Column(name = "user_id", columnDefinition = "uuid")
    private UUID userId;

    @NotEmpty
    private String firstName;

    private String lastName;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @MapsId
    @JoinColumn(name = "user_id", columnDefinition = "uuid")
    @JsonIgnore
    private User user;
}