package com.example.studentsgroups.DataRepositories;

import com.example.studentsgroups.DataModels.FormEducation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FormEducationRepository extends CrudRepository<FormEducation, Long> {
}
