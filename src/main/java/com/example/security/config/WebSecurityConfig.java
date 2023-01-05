package com.example.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


/**
 * 创建过滤器
 * @author lilei
 * @classname WebSecurityConfig
 * @description TODO
 * @date 2021/9/10 16:45
 */
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("user").password("password").roles("USER");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll();
    }
}
/**
 * 这段代码内容很少，但事实上已经做了很多的默认安全验证，包括：
 * 1、访问应用中的每个URL都需要进行验证
 * 2、生成一个登陆表单
 * 3、允许用户使用username和password来登陆
 * 4、允许用户注销
 * 5、CSRF攻击拦截
 * 6、 Session Fixation攻击
 * 7、 安全Header集成
 * 	* HTTP Strict Transport Security for secure requests
 * 	* X-Content-Type-Options integration
 * 	* 缓存控制 (can be overridden later by your application to allow caching of your static resources)
 * 	* X-XSS-Protection integration
 * 	* X-Frame-Options integration to help prevent Clickjacking
 * Integrate with the following Servlet API methods
 * 	* HttpServletRequest#getRemoteUser()
 * 	* HttpServletRequest.html#getUserPrincipal()
 * 	* HttpServletRequest.html#isUserInRole(java.lang.String)
 * 	* HttpServletRequest.html#login(java.lang.String, java.lang.String)
 * 	* HttpServletRequest.html#logout()
 */
