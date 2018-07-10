/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author olda9
 */
@Controller
public class ManageController {

    @Autowired
    private UserDao userDao;

    @RequestMapping(value = "/manage")
    public ModelAndView manage(HttpServletRequest request) {

        HashMap<String, Object> map = new HashMap();

        if (request.getSession().getAttribute("login") == null) {
            return new ModelAndView("forward:/list.html", map);
        }
        long d = ((Long) request.getSession().getAttribute("login"));
        int id = (int) d;
        if (!userDao.getUser(id).isAdmin()) {
            return new ModelAndView("forward:/list.html", map);
        }

        map.put("userDao", userDao);

        map.put("message", new Message(""));

        String submit = request.getParameter("submit");
        String idD = request.getParameter("id");
        if (submit != null && submit.equalsIgnoreCase("delete") && idD != null) {
            userDao.delete(Integer.parseInt(idD));
        }
        return new ModelAndView("manage.jsp", map);

    }

}
