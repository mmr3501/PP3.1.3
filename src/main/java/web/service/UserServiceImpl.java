package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import web.dao.UserDao;
import web.model.Role;
import web.model.User;
import java.util.List;

@Component
public class UserServiceImpl implements UserService {

    private final UserDao dao;

    @Autowired
    public UserServiceImpl(UserDao dao) {
        this.dao = dao;
    }

    @Transactional
    @Override
    public void removeUserById(Long id) {
        dao.removeUserById(id);
    }

    @Override
    public User getUserById(Long id) {
        return dao.getUserById(id);
    }

    @Transactional
    @Override
    public User getUserByName(String name) {
        return dao.getUserByName(name);
    }

    @Transactional
    @Override
    public List<Role> getAllRoles() {
        return dao.getAllRoles();
    }

    @Override
    public Role getAdminRole() {
        return dao.getAdminRole();
    }

    @Override
    public Role getUserRole() {
        return dao.getUserRole();
    }

    @Transactional
    @Override
    public void saveUser(User user) {
        dao.saveUser(user);
    }

    @Transactional
    @Override
    public void removeUser(User user) {
        dao.removeUser(user);
    }

    @Transactional
    @Override
    public List<User> getAllUsers() {
        return dao.getAllUsers();
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = dao.getUserByName(username);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("User '%s' не найден", username));
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), user.getRoles());
    }
}