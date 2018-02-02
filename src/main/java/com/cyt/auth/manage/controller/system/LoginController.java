package com.cyt.auth.manage.controller.system;

import com.cyt.auth.manage.common.constant.ImageConstants;
import com.cyt.auth.manage.common.exception.BizServiceException;
import com.cyt.auth.manage.common.util.WebResponse;
import com.cyt.auth.manage.controller.BaseController;
import com.cyt.auth.manage.shiro.ShiroUtils;
import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * 用户登录Controller
 *
 * @author CaoYangTao
 * @date 2018年01月23日 20:22
 * @desc
 */
@Controller
public class LoginController extends BaseController {
    /**
     * 验证码生成器
     */
    @Autowired
    private Producer producer;

    @RequestMapping("verificationCode.jpg")
    public void getVerificationCode(HttpServletResponse response) throws IOException {
        response.setHeader("Cache-Control", "no-store, no-cache");
        response.setContentType("image/jpeg");

        //生成验证码文字
        String text = producer.createText();
        //生成验证码图片
        BufferedImage image = producer.createImage(text);

        //将文字保存到session中
        ShiroUtils.setSessionAttribute(Constants.KAPTCHA_SESSION_KEY, text);

        ImageIO.write(image, ImageConstants.JPG, response.getOutputStream());
    }

    /**
     * 用户登录验证
     *
     *  @param userName 用户名
     * @param password 密码
     * @param verificationCode 验证码
     */
    @ResponseBody
    @RequestMapping(value = "/sys/login", method = RequestMethod.POST)
    public WebResponse login(String userName, String password, String verificationCode) {
        try {
            //1、验证验证码是否匹配
            String verificationText = ShiroUtils.getVerificationCode(Constants.KAPTCHA_SESSION_KEY);
            if (!verificationCode.equals(verificationText)) {
                return WebResponse.error("验证码不正确");
            }

            //2、验证用户名密码等
            UsernamePasswordToken token = new UsernamePasswordToken(userName, password);
            Subject subject = ShiroUtils.getSubject();
            subject.login(token);
        } catch (BizServiceException e) {
           return WebResponse.error(e.getMessage());
        } catch (UnknownAccountException e){
            return WebResponse.error(e.getMessage());
        } catch (IncorrectCredentialsException e) {
            return WebResponse.error("用户名或密码不正确");
        } catch (LockedAccountException e) {
            return WebResponse.error("账号被锁定，请联系管理员");
        } catch (AuthenticationException e) {
            return WebResponse.error("用户验证失败");
        } catch (Exception e) {
            return WebResponse.error("系统异常");
        }
        return WebResponse.success();
    }

    /**
     * 退出登录
     *
     * @return
     */
    @RequestMapping(value = "logout")
    public String logout(){
        ShiroUtils.logout();
        return "login";
    }
}
