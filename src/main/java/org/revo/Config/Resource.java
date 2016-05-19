package org.revo.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

/**
 * Created by revo on 5/7/16.
 */
@EnableResourceServer
@Configuration
public class Resource extends ResourceServerConfigurerAdapter {
    @Autowired
    private Http401UnauthorizedEntryPoint authenticationEntryPoint;

    @Autowired
    private LogoutHandler logoutHandler;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .exceptionHandling().authenticationEntryPoint(authenticationEntryPoint)
                .and().logout().logoutSuccessHandler(logoutHandler)
                .and().authorizeRequests().antMatchers("/api/admin/**").hasRole("ADMIN")

                //WEB SOCKET
                .and().authorizeRequests().antMatchers("/hello/**").hasRole("USER")

                .and().authorizeRequests().antMatchers("/api/user/**").hasRole("USER")
                .and().authorizeRequests().antMatchers("/api/image/**").hasRole("USER")
                .and().authorizeRequests().antMatchers("/api/notification/**").hasRole("USER")

                .and().headers().frameOptions().sameOrigin()
                .and().csrf().disable();
    }
}
