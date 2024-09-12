package springSecurityJwt.springSecurityJwt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import springSecurityJwt.springSecurityJwt.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    public Optional<User> findOneWithAuthoritiesByUsername(String username);
}
