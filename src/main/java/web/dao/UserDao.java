package web.dao;

import web.model.Role;
import web.model.User;
import java.util.List;

public interface UserDao {

    void saveUser(User user);

    void removeUser(User user);

    List<User> getAllUsers();

    User getUserById(Long id);

    void removeUserById(Long id);

    User getUserByName(String name);

    List<Role> getAllRoles ();

    Role getAdminRole();

    Role getUserRole();

}