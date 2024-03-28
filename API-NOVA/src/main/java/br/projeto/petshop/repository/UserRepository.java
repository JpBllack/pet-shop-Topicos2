package br.projeto.petshop.repository;

import br.projeto.petshop.model.User;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.NoResultException;

@ApplicationScoped
public class UserRepository implements PanacheRepository<User>{
    public User findByUsername(String username) {
        try{
            return find("UPPER(username) LIKE UPPER(?1) ", "%"+username+"%").singleResult();
        } catch(NoResultException e){
            e.printStackTrace();
            return null;
        }
    }

    public User findByEmail(String email){
        try{
            return find("UPPER(email) = UPPER(?1)", email).singleResult();
        } catch(NoResultException e){
            e.printStackTrace();
            return null;
        }
    }

    public User findByEmailAndPassword(String email, String password) {
        try {
            return find("UPPER(email) = UPPER(?1) AND password = ?2 ", email, password).singleResult();
        } catch (NoResultException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean existsByEmail(String email) {
        return find("email", email).count() > 0;
    }

    public boolean existsByUsername(String username){
        return find("username", username).count() > 0;
    }
    
}
