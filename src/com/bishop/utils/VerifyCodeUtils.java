package com.bishop.utils;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Random;

/**
 * @ClassName VerifyCodeUtils
 * @Author Bishop
 */
public class VerifyCodeUtils {
    public static String getVerifyCode(){
        Random random = new Random();
        String data = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        StringBuffer verifyCode = new StringBuffer();
        for (int i = 0; i < 4; i++) {
            int index = random.nextInt(data.length());
            String str = data.substring(index, index + 1);
            verifyCode.append(str);

        }
        return verifyCode.toString();
    }
    public static BufferedImage getImage(String verifyCode){

        int height = 40;
        int width = 100;
        Random random = new Random();

        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics g = image.getGraphics();
        g.setColor(Color.BLACK);
        g.fillRect(1, 1, width - 2, height - 2);
        g.setFont(new Font("宋体", Font.BOLD | Font.ITALIC, 25));
        for (int i = 0; i < 4; i++) {
            g.setColor(new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255)));
            String str = verifyCode.substring(i, i + 1);
            g.drawString(str, width / 6 * (i + 1), 20);

        }
        for (int i = 0; i < 3; i++) {
            g.setColor(new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255)));
            g.drawLine(random.nextInt(width), random.nextInt(height), random.nextInt(width), random.nextInt(height));
            g.drawOval(random.nextInt(width), random.nextInt(height), 2, 2);
        }


        return image;
    }
}
