package mx.com.erick.usercontroll.usercontrol.dao;

import mx.com.erick.usercontroll.usercontrol.models.User;

import java.util.List;

public interface UserDao {

    public List<User> getUsers();

    void deleteUser(Long id);

    void registerUser(User user);


    User obtainUser(User user);
}
