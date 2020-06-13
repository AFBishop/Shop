package com.bishop.web.servlet;

import com.bishop.utils.VerifyCodeUtils;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;

public class VerifyCodeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String verifyCode = VerifyCodeUtils.getVerifyCode();
        BufferedImage image = VerifyCodeUtils.getImage(verifyCode);
        request.getSession().setAttribute("verifyCode", verifyCode);
        ImageIO.write(image, "jpg", response.getOutputStream());
    }
}
