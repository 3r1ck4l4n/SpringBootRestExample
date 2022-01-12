package mx.com.erick.usercontroll.usercontrol.controller;

import mx.com.erick.usercontroll.usercontrol.dao.UserDao;
import mx.com.erick.usercontroll.usercontrol.models.User;
import mx.com.erick.usercontroll.usercontrol.util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController()
public class AuthController {
    @Autowired
    private UserDao userDao;
    @Autowired
    private JWTUtil jwtUtil;

    @RequestMapping(value = "api/login", method = RequestMethod.POST)
    public String initSession(@RequestBody User user) {

        User userLoggin = userDao.obtainUser(user);

        if(userLoggin != null){
            String token = jwtUtil.createJWT(userLoggin.getId().toString(), userLoggin.getEmail());
            return token;
        }
        return "ERROR";

    }
}
