package com.example.studentsgroups.Forms;

import com.example.studentsgroups.DataModels.UserModel;
import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;

@Data
public class RegistrationForm {
    private String username;
    private String password;
    private String email;

    public UserModel postUser(PasswordEncoder passwordEncoder) {
        return new UserModel(username, passwordEncoder.encode(password), email);
    }
}
