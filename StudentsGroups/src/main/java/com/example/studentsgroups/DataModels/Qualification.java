package com.example.studentsgroups.DataModels;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PUBLIC, force = true)
public class Qualification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Квалификация не может быть пустой!")
    private String qualification_name;
}
