package e_surat.stis.Project.UTS.service;

import e_surat.stis.Project.UTS.model.Role;
import e_surat.stis.Project.UTS.model.User;
import e_surat.stis.Project.UTS.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public User registerUser(String username, String password, Role role) {
        User user = new User(username, passwordEncoder.encode(password), role);
        return userRepository.save(user);
    }

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public boolean validatePassword(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}