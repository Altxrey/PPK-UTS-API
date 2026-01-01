package e_surat.stis.Project.UTS.controller;

import e_surat.stis.Project.UTS.model.Role;
import e_surat.stis.Project.UTS.model.User;
import e_surat.stis.Project.UTS.security.JwtUtil;
import e_surat.stis.Project.UTS.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    /**
     * REGISTER USER
     * Contoh JSON body:
     * {
     *   "username": "mahasiswa1",
     *   "password": "123456",
     *   "role": "MAHASISWA"
     * }
     */
    @PostMapping("/register")
    public Map<String, Object> register(@RequestBody Map<String, String> req) {
        String username = req.get("username");
        String password = req.get("password");
        String roleStr = req.get("role").toUpperCase();

        Role role;
        try {
            role = Role.valueOf(roleStr);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Invalid role. Use: ADMIN, DOSEN, or MAHASISWA");
        }

        User user = userService.registerUser(username, password, role);

        Map<String, Object> res = new LinkedHashMap<>();
        res.put("message", "User registered successfully");
        res.put("username", user.getUsername());
        res.put("role", user.getRole());
        return res;
    }

    /**
     * LOGIN USER
     * Contoh JSON body:
     * {
     *   "username": "mahasiswa1",
     *   "password": "123456"
     * }
     */
    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody Map<String, String> req) {
        String username = req.get("username");
        String password = req.get("password");

        // Cek apakah user ada di database
        User user = userService.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Validasi password
        if (!userService.validatePassword(password, user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        // Generate JWT token
        String token = jwtUtil.generateToken(username, user.getRole().name());

        // Response
        Map<String, Object> res = new LinkedHashMap<>();
        res.put("message", "Login successful");
        res.put("token", token);
        res.put("username", username);
        res.put("role", user.getRole());
        return res;
    }
}
