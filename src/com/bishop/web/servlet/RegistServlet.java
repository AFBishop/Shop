package com.bishop.web.servlet;

import com.bishop.domain.User;
import com.bishop.service.UserService;
import com.bishop.utils.CommonsUtils;
import com.bishop.utils.MailUtils;
import org.apache.commons.beanutils.BeanUtils;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

public class RegistServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        User user = new User();
        UserService userService = new UserService();
        Map<String, String[]> properties = request.getParameterMap();
        try {
            BeanUtils.populate(user, properties);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        /**
         private String uid;
         private String username;
         private String password;
         private String name;
         private String email;
         private String telephone;
         private String birthday;
         private String sex;
         private int state;
         private String code;
         **/
        user.setUid(CommonsUtils.getUUID());
        user.setState(0);
        String activeCode = CommonsUtils.getUUID();
        user.setCode(activeCode);
        boolean isRegistSuccess = userService.regist(user);
        if(isRegistSuccess){
            String emailMsg = "恭喜您注册成功，请点击下面的连接进行激活账户\"\n" +
                   "<a href='http://localhost:8080/HeimaShop/active?activeCode="+activeCode+"'>" +
                    "http://localhost:8080/HeimaShop/active?activeCode="+activeCode+"</a>";
            try {
                MailUtils.sendMail(user.getEmail(), emailMsg);
            } catch (MessagingException e) {
                e.printStackTrace();
            }
            response.sendRedirect(request.getContextPath()+"/registerSuccess.jsp");
        }else {
            response.sendRedirect(request.getContextPath()+"/registerFail.jsp");
        }
    }
}
