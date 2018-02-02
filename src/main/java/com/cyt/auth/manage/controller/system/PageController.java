package com.cyt.auth.manage.controller.system;

import com.cyt.auth.manage.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 直接跳转至系统页面Controller
 *
 * @author CaoYangTao
 * @date 2018年01月23日 20:30
 * @desc
 */
@Controller
public class PageController  extends BaseController {
    @RequestMapping("{url}.html")
    public String login(@PathVariable("url") String url){
        return url + ".html";
    }

    @RequestMapping("{module}/{url}.html")
    public String login(@PathVariable("module") String module,
            @PathVariable("url") String url){
        return module + "/" + url + ".html";
    }

    @RequestMapping("/")
    public String index() {
        return "index.html";
    }
}
