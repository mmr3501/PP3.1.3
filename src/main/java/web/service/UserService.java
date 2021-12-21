package web.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import web.model.Role;
import web.model.User;

import java.util.List;

public interface UserService extends UserDetailsService {

    void saveUser(User user);

    void removeUser(User user);

    List<User> getAllUsers();

    void removeUserById(Long id);

    User getUserById(Long id);

    User getUserByName(String name);

    List<Role> getAllRoles();

    Role getAdminRole();

    Role getUserRole();

}
