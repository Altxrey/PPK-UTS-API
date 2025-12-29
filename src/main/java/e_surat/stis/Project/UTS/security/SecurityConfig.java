package e_surat.stis.Project.UTS.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    // Daftar endpoint yang boleh diakses tanpa autentikasi (whitelist)
    private static final String[] WHITE_LIST = {
            "/v3/api-docs/**",
            "/swagger-ui/**",
            "/swagger-ui.html",
            "/swagger-resources/**",
            "/webjars/**",
            "/api/auth/**" // endpoint login/register tetap terbuka
    };

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // nonaktifkan CSRF untuk API berbasis token
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(WHITE_LIST).permitAll() // Swagger & Auth bebas diakses
                        .anyRequest().authenticated() // endpoint lain butuh JWT
                );

        return http.build();
    }
}
