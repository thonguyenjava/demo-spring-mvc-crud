package demo.spingmvccrud.serviceIml;

import demo.spingmvccrud.entity.User;
import demo.spingmvccrud.repository.UserRepository;
import demo.spingmvccrud.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
@CacheConfig(cacheNames = {"user"})
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
    @CacheEvict(value = "user", key = "#id")
    public List<User> deleteUser(Long id) {
        rp.delete(getUserById(id));
        return getAllUsers();
    }

    @Override
    @Cacheable(key = "#id")
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
