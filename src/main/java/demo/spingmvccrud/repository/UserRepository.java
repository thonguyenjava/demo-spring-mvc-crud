package demo.spingmvccrud.repository;

import demo.spingmvccrud.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
public interface UserRepository extends JpaRepository<User, Long> {
    User findUserById(Long id);
    boolean existsUserByLoginId(String loginId);
    boolean existsUserByMobileNo(String mobileNo);
    boolean existsUserById(Long id);
}
