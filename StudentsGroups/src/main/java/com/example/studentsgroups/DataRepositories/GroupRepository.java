package com.example.studentsgroups.DataRepositories;

import com.example.studentsgroups.DataModels.GroupStudents;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRepository extends CrudRepository<GroupStudents, Long> {
}
