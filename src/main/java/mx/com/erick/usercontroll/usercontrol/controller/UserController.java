package mx.com.erick.usercontroll.usercontrol.controller;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import mx.com.erick.usercontroll.usercontrol.dao.UserDao;
import mx.com.erick.usercontroll.usercontrol.models.User;
import mx.com.erick.usercontroll.usercontrol.util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {

    private final UserDao userDao;

    @Autowired
    private JWTUtil jwtUtil;

    public UserController(UserDao userDao) {
        this.userDao = userDao;
    }

    @RequestMapping(value = "api/user/{id}")
    public User getUser(@PathVariable Long id) {
        User userTest = new User("Erick", "eluna@gmail.com", "3r1ck4l4n");

        return userTest;
    }

    @RequestMapping(value = "/users")
    public List<User> getUsers(@RequestHeader(value = "Authorization")String token) {

        String userId = jwtUtil.getKey(token);

        if(userId != null){
            return userDao.getUsers();
        }
        return null;
    }

    @RequestMapping(value = "api/user/{id}", method = RequestMethod.DELETE)
    public void deleteUser(@PathVariable Long id, @RequestHeader(value = "Authorization")String token) {
        userDao.deleteUser(id);
    }

    @RequestMapping(value = "api/user", method = RequestMethod.POST)
    public void setUser(@RequestBody User user) {
        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2d);
        String hash = argon2.hash(1,1024,1,user.getPass() );
        user.setPass(hash);

        userDao.registerUser(user);
    }


}
