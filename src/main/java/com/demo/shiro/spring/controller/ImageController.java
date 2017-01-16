package com.demo.shiro.spring.controller;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * Created by Administrator on 2017/1/15.
 */
@Controller
public class ImageController {

    @RequestMapping("/admin/image/{id}")
    public void image(@PathVariable("id")String id,
                      HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            System.out.println("-------------- image id : " + id + "---------------");
            String path = "F:\\图片\\地震\\11.jpg";
            response.setHeader("Content-type", "text/html;charset=UTF-8");
            response.setCharacterEncoding("UTF-8");
            response.setContentType("image/png");
            BufferedInputStream buff = new BufferedInputStream(new FileInputStream(new File(path)));
            OutputStream out = response.getOutputStream();
            /*byte[] bytes = IOUtils.toByteArray(buff);
            IOUtils.write(bytes, out);*/
            int bytesRead = 0;
            byte[] buffer = new byte[8 * 1024];
            while ((bytesRead = buff.read(buffer)) != -1) {
                out.write(buffer, 0, bytesRead);
            }
            buff.close();
            out.close();
            out.flush();
        } catch (Exception e) {
            response.getOutputStream().write("显示图片异常".getBytes("UTF-8"));
        }
    }
}
