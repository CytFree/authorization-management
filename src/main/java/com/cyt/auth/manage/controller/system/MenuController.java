package com.cyt.auth.manage.controller.system;

import com.cyt.auth.manage.common.util.WebResponse;
import com.cyt.auth.manage.controller.BaseController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 菜单信息Controller
 *
 * @author CaoYangTao
 * @date 2018年01月23日 22:06
 * @desc
 */
@RestController
@RequestMapping(value = "/sys/menu")
public class MenuController  extends BaseController{
    /**
     * 导航菜单
     *
     * @return
     */
    @RequestMapping(value = "/nav")
    public WebResponse menuNav() {
        WebResponse response = WebResponse.success().put("menuList", null);
        return response;
    }

}
