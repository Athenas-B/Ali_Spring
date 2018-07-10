/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import javax.persistence.NoResultException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author olda9
 */
@Controller
public class UserController {

    @Autowired
    private UserDao userDao;

    @RequestMapping(value = "/user")
    public ModelAndView registerUser(
            @CookieValue(value = "hitCounter", defaultValue = "0") Long hitCounter,
            HttpServletResponse response, HttpServletRequest request) {

        // increment hit counter
        hitCounter++;

        // create cookie and set it in response
        Cookie cookie = new Cookie("hitCounter", hitCounter.toString());
        cookie.setMaxAge(999999);
        response.addCookie(cookie);
        
        request.getSession().invalidate();
        

        // Handle a new guest (if any):
        String login = request.getParameter("login");
        String pass = request.getParameter("pass");
        String submit = request.getParameter("submit");
        HashMap<String, Object> map = new HashMap();
        map.put("userDao", userDao);
        map.put("message", new Message(""));
        try {

            if (submit != null && login != null && pass != null && !login.isEmpty() && !pass.isEmpty()) {
                map.put("message", new Message("Wrong login"));
                if (submit.equalsIgnoreCase("Register") && !userDao.exists(login)) {
                    User user = new User(login, hash(pass.trim()));
                    userDao.persist(user);
                    map.put("user", user);
                    map.put("message", new Message("Login registered"));
                    request.getSession().setAttribute("login", user.getId());
                    request.getSession().setMaxInactiveInterval(600);
                    return new ModelAndView("forward:/list.html", map);
                } else if (submit.equalsIgnoreCase("Login") && userDao.exists(login)) {
                    if (userDao.getUser(login).getPass().equals(hash(pass.trim()))) {
                        System.out.println("Login");
                        map.put("user", userDao.getUser(login));
                        map.put("message", new Message("Loging in"));
                        request.getSession().setAttribute("login", userDao.getUser(login).getId());
                        request.getSession().setMaxInactiveInterval(600);
                        return new ModelAndView("forward:/list.html", map);
                    } else {
                        map.put("message", new Message("Wrong password"));
                    }
                } else {
                    map.put("message", new Message("Wrong login"));
                }

            }
        } catch (NoResultException | NullPointerException e) {
            map.put("message", new Message("Wrong login"));
        }

        // Prepare the result view (guest.jsp):
        return new ModelAndView("user.jsp", map);
    }

    private String hash(String heslo) {
        heslo = heslo.trim();
        String generatedPassword = null;
        try {
            // Create MessageDigest instance for MD5
            MessageDigest md = MessageDigest.getInstance("MD5");
            //Add password bytes to digest
            md.update(heslo.getBytes());
            //Get the hash's bytes
            byte[] bytes = md.digest();
            //This bytes[] has bytes in decimal format;
            //Convert it to hexadecimal format
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            //Get complete hashed password in hex format
            generatedPassword = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return generatedPassword;
    }
}
