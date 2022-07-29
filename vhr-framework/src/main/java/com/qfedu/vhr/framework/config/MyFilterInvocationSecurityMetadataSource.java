package com.qfedu.vhr.framework.config;

import com.qfedu.vhr.framework.entity.Role;
import com.qfedu.vhr.framework.entity.vo.MenuRoleVO;
import com.qfedu.vhr.framework.service.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * 这个接口的作用，就是提取出请求地址 /employee/basic/hello，服务端去分析这个请求地址需要哪些角色才能访问。分析的思路，就是拿着这个地址，去 menu 数据表中比对，看和哪个菜单能够匹配上，找到对应的菜单后，再去 menu_role 这个表中查看这个菜单需要哪些角色才能访问
 */
@Component
public class MyFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

    @Autowired
    IMenuService menuService;

    AntPathMatcher pathMatcher = new AntPathMatcher();

    /**
     * 这是最核心的判断方法
     * @param object 这个参数其实就是拦截下来的对象，这个对象中包含着当前的请求地址
     * @return 返回值就是当前这个请求地址所需要的角色
     * @throws IllegalArgumentException
     */
    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        //获取当前的请求地址
        String requestUrl = ((FilterInvocation) object).getRequestUrl();
        List<MenuRoleVO> allMenuRoles = menuService.getAllMenuRoles();
        for (MenuRoleVO allMenuRole : allMenuRoles) {
            if (pathMatcher.match(allMenuRole.getUrl(), requestUrl)) {
                //如果匹配上了，说明这个请求的地址找到了对应的菜单项，进而就能找到这个请求所需要的角色
                List<Role> roles = allMenuRole.getRoles();
                String[] rolesArr = new String[roles.size()];
                int index = 0;
                for (Role role : roles) {
                    rolesArr[index++] = role.getName();
                }
                return SecurityConfig.createList(rolesArr);
            }
        }
        //如果一个请求地址和数据库中的所有菜单项都没有匹配上，那么就说明这个地址登录就可以访问
        return SecurityConfig.createList("ROLE_LOGIN");
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }
}
