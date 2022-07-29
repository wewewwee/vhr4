package com.qfedu.vhr.framework.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qfedu.vhr.framework.entity.Hr;
import com.qfedu.vhr.framework.entity.RespBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

import java.io.PrintWriter;


/**
 * 动态接口访问权限控制：
 *
 * 1. 客户端发送请求 http://localhost:8080/employee/basic/hello
 * 2. 服务端提取出请求地址 /employee/basic/hello，服务端去分析这个请求地址需要哪些角色才能访问。分析的思路，就是拿着这个地址，去 menu 数据表中比对，看和哪个菜单能够匹配上，找到对应的菜单后，再去 menu_role 这个表中查看这个菜单需要哪些角色才能访问
 * 3. 判断当前登录用户是否具备所需要的角色
 *
 */
@Configuration
public class SecurityConfig {
    @Autowired
    MyAccessDecisionManager myAccessDecisionManager;
    @Autowired
    MyFilterInvocationSecurityMetadataSource myFilterInvocationSecurityMetadataSource;

    @Bean
    WebSecurityCustomizer webSecurityCustomizer() {
        return new WebSecurityCustomizer() {
            @Override
            public void customize(WebSecurity web) {
                web.ignoring().antMatchers("/**/*.css", "/**/*.ttf", "/**/*.woff", "/**/*.eot", "/**/*.woff2", "/**/*.svg", "/**/*.js", "*.ico", "/index.html");
            }
        };
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
                    @Override
                    public <O extends FilterSecurityInterceptor> O postProcess(O object) {
                        object.setSecurityMetadataSource(myFilterInvocationSecurityMetadataSource);
                        object.setAccessDecisionManager(myAccessDecisionManager);
                        return object;
                    }
                })
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .successHandler((req, resp, auth) -> {
                    resp.setContentType("application/json;charset=utf-8");
                    PrintWriter out = resp.getWriter();
                    Hr hr = (Hr) auth.getPrincipal();
                    hr.setPassword(null);
                    RespBean respBean = RespBean.ok("登录成功", hr);
                    out.write(new ObjectMapper().writeValueAsString(respBean));
                })
                .failureHandler((req, resp, e) -> {
                    resp.setContentType("application/json;charset=utf-8");
                    PrintWriter out = resp.getWriter();
                    RespBean respBean = RespBean.error("登录失败");
                    if (e instanceof BadCredentialsException) {
                        respBean.setMsg("账户名或者密码输入错误，登录失败");
                    } else if (e instanceof DisabledException) {
                        respBean.setMsg("账户被禁用，登录失败");
                    }
                    out.write(new ObjectMapper().writeValueAsString(respBean));
                })
                .permitAll()
                .and()
                //配置注销登录
                .logout()
                //注销登录地址
                .logoutUrl("/logout")
                .logoutSuccessHandler((req, resp, auth) -> {
                    resp.setContentType("application/json;charset=utf-8");
                    PrintWriter out = resp.getWriter();
                    RespBean respBean = RespBean.ok("注销成功");
                    out.write(new ObjectMapper().writeValueAsString(respBean));
                })
                .and()
                .csrf().disable()
                .exceptionHandling().authenticationEntryPoint((req, resp, e) -> {
            //设置响应状态码为 401
            resp.setStatus(HttpStatus.UNAUTHORIZED.value());
            resp.setContentType("application/json;charset=utf-8");
            PrintWriter out = resp.getWriter();
            RespBean respBean = RespBean.error("尚未登录请登录");
            out.write(new ObjectMapper().writeValueAsString(respBean));
        });
        return http.build();
    }
}
