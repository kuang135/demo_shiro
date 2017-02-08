package com.demo.shiro.spring.jcaptcha;

import com.demo.shiro.spring.util.SpringUtil;
import com.octo.captcha.service.CaptchaService;
import com.octo.captcha.service.CaptchaServiceException;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * @Author yaowk
 * @Date 2017/1/4 16:53
 */
public class JCaptchaServlet extends HttpServlet{

    private static final long serialVersionUID = -6692098475105909206L;

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setHeader("Cache-Control", "no-store");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");
        ServletOutputStream out = response.getOutputStream();
        try {
            String captchaId = request.getSession(true).getId();
            CaptchaService captchaService=(CaptchaService) SpringUtil.getBean("captchaService");
            BufferedImage jpg = (BufferedImage) captchaService.getChallengeForID(captchaId, request.getLocale());
            ImageIO.write(jpg, "jpg", out);
            out.flush();
        } catch (CaptchaServiceException e) {
            e.printStackTrace();
        } finally {
            out.close();
        }
    }

}
