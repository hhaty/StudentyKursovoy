package com.example.studentsgroups.DataRepositories;

import com.example.studentsgroups.DataModels.Qualification;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QualificationRepository extends CrudRepository<Qualification, Long> {
}
