package springSecurityJwt.springSecurityJwt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import springSecurityJwt.springSecurityJwt.entity.User;

public interface UserRepository extends JpaRepository<Long, User> {
}
