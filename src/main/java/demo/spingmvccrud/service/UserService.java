package demo.spingmvccrud.service;

import demo.spingmvccrud.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    User saveUser(User user);
    List<User> getAllUsers();
    List<User> deleteUser(Long id);
    User getUserById(Long id);
    boolean isLoginIdExists(String loginId);
    boolean isMobileNoExists(String mobileNo);
    boolean isUserExists(Long id);
}
