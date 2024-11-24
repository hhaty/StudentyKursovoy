package com.example.studentsgroups.DataRepositories;

import com.example.studentsgroups.DataModels.UserModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserModelRepository extends CrudRepository<UserModel, Long> {
    UserModel findByUsername(String username);
}
