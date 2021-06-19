package com.kqtlt.config;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    //ShiroFilterFactoryBean
    @Bean
    public ShiroFilterFactoryBean getShiroFactoryBean(@Qualifier("defaultWebSecurityManager") DefaultWebSecurityManager defaultWebSecurityManager){
        ShiroFilterFactoryBean bean=new ShiroFilterFactoryBean();

        //设置安全管理器
        bean.setSecurityManager(defaultWebSecurityManager);

        //添加shiro的内置过滤器 //拦截
        Map<String,String> map=new LinkedHashMap<>();
        map.put("/single","authc");
        map.put("/batch","authc");
        map.put("/statistic","authc");
        map.put("/operation","authc");

        map.put("/single/news/upload","authc");

        map.put("/upload/news/file","authc");
        map.put("/upload/news/filepe","authc");

        map.put("/operation/all","authc");

        map.put("/statistic/news","authc");
        bean.setFilterChainDefinitionMap(map);

        //设置登录页面
        bean.setLoginUrl("/login");

        return bean;
    }

    //DefaultWebSecurityManager
    @Bean
    public DefaultWebSecurityManager defaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm){
        DefaultWebSecurityManager securityManager=new DefaultWebSecurityManager();
        securityManager.setRealm(userRealm);//关联UserRealm
        return securityManager;
    }

    //realm对象，需要自定义
    @Bean
    public UserRealm userRealm(){
        return new UserRealm();
    }
}

