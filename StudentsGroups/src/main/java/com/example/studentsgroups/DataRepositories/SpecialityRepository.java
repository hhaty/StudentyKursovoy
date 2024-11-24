package com.example.studentsgroups.DataRepositories;

import com.example.studentsgroups.DataModels.Speciality;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpecialityRepository extends CrudRepository<Speciality, Long> {
}
