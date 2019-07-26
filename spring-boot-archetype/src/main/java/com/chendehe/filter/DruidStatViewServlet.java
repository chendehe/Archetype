package com.chendehe.filter;

import com.alibaba.druid.support.http.StatViewServlet;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;

/**
 * 配置druid监控界面的配置.
 * 例如 http:127.0.0.1:1111/druid
 */
@WebServlet(urlPatterns = "/druid/*",
    initParams = {@WebInitParam(name = "allow", value = ""),// IP白名单 (没有配置或者为空，则允许所有访问)
        @WebInitParam(name = "deny", value = ""),// IP黑名单 (存在相同时，deny优先于allow)
        @WebInitParam(name = "loginUsername", value = "admin"),// 用户名
        @WebInitParam(name = "loginPassword", value = "admin"),// 密码
        @WebInitParam(name = "resetEnable", value = "false")// 禁用HTML页面上的“Reset All”功能
    })
public final class DruidStatViewServlet extends StatViewServlet {
}
