package demo.spingmvccrud.serviceIml;

import demo.spingmvccrud.entity.User;
import demo.spingmvccrud.repository.UserRepository;
import demo.spingmvccrud.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
@Slf4j
public class UserServiceIml implements UserService {
    private UserRepository rp;

    @Override
    public User saveUser(User user) {
        return rp.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return (List<User>) rp.findAll();
    }

    @Override
    public List<User> deleteUser(Long id) {
        rp.delete(getUserById(id));
        return getAllUsers();
    }

    @Override
    public User getUserById(Long id) {
        return rp.findUserById(id);
    }

    @Override
    public boolean isLoginIdExists(String loginId) {
        return rp.existsUserByLoginId(loginId);
    }

    @Override
    public boolean isMobileNoExists(String mobileNo) {
        return rp.existsUserByMobileNo(mobileNo);
    }

    @Override
    public boolean isUserExists(Long id) {
        return rp.existsUserById(id);
    }

}
