package com.study.admin.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
// @Table
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @Column(name = "account")
    private String account;

    private String password;
    private String status;
    private String email;
    private String phoneNumber; // camelCase로

    private LocalDateTime registeredAt;
    private LocalDateTime unregisteredAt;

    private LocalDateTime createdAt;
    private String createdBy;
    private LocalDateTime updatedAt;
    private String updatedBy;


}
