package e_surat.stis.Project.UTS.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import e_surat.stis.Project.UTS.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
