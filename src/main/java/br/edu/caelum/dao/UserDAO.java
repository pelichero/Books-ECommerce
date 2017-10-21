package br.edu.caelum.dao;

import br.edu.caelum.models.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDAO implements UserDetailsService {

    public static final String USER_BY_LOGIN_QUERY = "SELECT u FROM User u WHERE u.login = :login";
    @PersistenceContext
    private EntityManager em;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        List<User> users = em.createQuery(USER_BY_LOGIN_QUERY, User.class)
        .setParameter("login", username).getResultList();

        if(users.isEmpty()){
            throw new UsernameNotFoundException("O usuário "+username+" não existe");
        }

        return users.get(0);
    }
}
