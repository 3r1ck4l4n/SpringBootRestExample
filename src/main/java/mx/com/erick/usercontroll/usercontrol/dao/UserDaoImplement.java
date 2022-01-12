package mx.com.erick.usercontroll.usercontrol.dao;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import mx.com.erick.usercontroll.usercontrol.models.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional
public class UserDaoImplement implements UserDao {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<User> getUsers() {
        String query = "FROM User";

        return entityManager.createQuery(query).getResultList();
    }

    @Override
    public void deleteUser(Long id) {
        User user = entityManager.find(User.class, id);
        entityManager.remove(user);
    }

    @Override
    public void registerUser(User user) {
        User userSave = entityManager.merge(user);
    }

    @Override
    public User obtainUser(User user) {
        String query = "FROM User WHERE email_user= :email ";

         List<User> users = entityManager.createQuery(query).
                 setParameter("email", user.getEmail()).
                 getResultList();

         if(!users.isEmpty()){

             String passHashed = users.get(0).getPass();
             Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2d);
             if(argon2.verify(passHashed, user.getPass())) {
                return users.get(0);
             }
         }else {
             return null;
         }


        return null;
    }
}
