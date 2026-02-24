
package com.aditech.expensetracker.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Table(name="Users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;

    private String password;

    private String role;
}
