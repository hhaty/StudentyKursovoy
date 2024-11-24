package com.example.studentsgroups.DataModels;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PUBLIC, force = true)
public class GroupStudents {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Факультет не может быть пустым")
    private String faculty;

    @NotEmpty(message = "Номер группы не может быть пустым!")
    private String name;

    @Min(value = 1, message = "Курс может быть в пределах от 1 до 5!")
    private int course;

    @Min(value = 10, message = "Количество студентов не может быть меньше 10!")
    private int student_count;

    @Min(value = 1, message = "Количество подгрупп не может быть меньше 1!")
    private int student_subgroups;

    @ManyToOne
    @NotNull(message = "Необходимо выбрать квалификацию!")
    private Qualification qualification;

    @ManyToOne
    @NotNull(message = "Необходимо выбрать форму обучения!")
    private FormEducation formEducation;

    @ManyToOne
    @NotNull(message = "Необходимо выбрать направление подготовки!")
    private Speciality speciality;
}
