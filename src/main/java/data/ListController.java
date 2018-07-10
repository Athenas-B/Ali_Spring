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
public class ListController {

    @Autowired
    private ItemDao itemDao;
    @Autowired
    private UserDao userDao;

    @RequestMapping(value = "/list")
    public ModelAndView registerItem(HttpServletRequest request) {

        HashMap<String, Object> map = new HashMap();

        if (request.getSession().getAttribute("login") == null) {
            return new ModelAndView("forward:/user.html", map);
        }
        map.put("userDao", userDao);

        map.put("itemDao", itemDao);
        map.put("message", new Message(""));

        String url = request.getParameter("url");
        String title = request.getParameter("title");
        String count = request.getParameter("count");
        String note = request.getParameter("note");
        String submit = request.getParameter("submit");
        String author = request.getParameter("author");
        if (submit != null && submit.equalsIgnoreCase("insert") && author != null && note != null && count != null && title != null && url != null) {
            Item item = new Item(url);
            item.setChecked(false);
            item.setNote(note);
            item.setTitle(title);
            item.setCount(Integer.parseInt(count));
            item.setAuthor(userDao.getUser(Integer.parseInt(author)));
            itemDao.persist(item);
        }
        String id = request.getParameter("id");
        if (submit != null && submit.equalsIgnoreCase("delete") && id != null) {
            itemDao.delete(Integer.parseInt(id));
        }
        if (submit != null && submit.equalsIgnoreCase("check") && id != null) {
            int il =Integer.parseInt(id);
            Item item = itemDao.getItem(il);
            if (item.getAuthor().getId() == request.getSession().getAttribute("login")) {
                map.put("message", new Message("You cannot check your own item!"));
                System.out.println("Nope");
            } else {
                itemDao.Check(Integer.parseInt(id));
            }

        }
        return new ModelAndView("list.jsp", map);

    }

}
