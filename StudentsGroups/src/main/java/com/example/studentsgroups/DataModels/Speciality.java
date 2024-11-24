package com.example.studentsgroups.DataModels;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import jakarta.persistence.Entity;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PUBLIC, force = true)
public class Speciality {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Направление подготовки не должно быть пустым!")
    private String speciality_name;

    @NotEmpty(message = "Профиль подготовки не должен быть пустым!")
    private String profile;
}
