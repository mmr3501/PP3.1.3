package web.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import web.model.Role;
import web.model.User;
import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Component
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    public UserDaoImpl() {
    }

    @Override
    public void saveUser(User user) {
        Set<Role> set = new HashSet<>();
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(user.getPassword()));
        Set <Role> setOfRoles = user.getRoles();
        for (Role role : setOfRoles) {
            if (role.equals(getUserRole())) {
                set.add(getUserRole());
            } else if (role.equals(getAdminRole())) {
                set.add(getAdminRole());
            }
        }
        user.setRoles(set);
        em.merge(user);
    }

    @Override
    public void removeUserById(Long id) {
        User user = getUserById(id);
        em.remove(user);
    }

    @Override
    public void removeUser(User user) {
        em.remove(user);
    }

    @Override
    public List<User> getAllUsers() {
        return em.createQuery("FROM User", User.class).getResultList();
    }

    @Override
    public User getUserById(Long id){
        return em.createQuery("FROM User WHERE id =:id", User.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    @Override
    public User getUserByName(String username) {
        return em.createQuery("FROM User WHERE username = :username", User.class)
                .setParameter("username", username)
                .getSingleResult();
    }

    @Override
    public List<Role> getAllRoles() {
        return em.createQuery("FROM Role", Role.class).getResultList();
    }

    @Override
    public Role getAdminRole() {
        return em.createQuery("FROM Role WHERE role = :role", Role.class)
                .setParameter("role", "ROLE_ADMIN")
                .getSingleResult();
    }

    @Override
    public Role getUserRole() {
        return em.createQuery("FROM Role WHERE role = :role", Role.class)
                .setParameter("role", "ROLE_USER")
                .getSingleResult();
    }

}