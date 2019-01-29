package com.erasmus.authentication;

import com.erasmus.db.model.Admin;
import com.erasmus.db.model.User;
import com.erasmus.db.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserDetailsServiceConfig userDetailsService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/", "/login-submit", "/login", "/logout").permitAll();

        http.csrf().disable();
        http.authorizeRequests().and().formLogin()//
                .loginProcessingUrl("/login-submit") // Submit URL
                .loginPage("/login").successHandler((request, response, authentication) -> {
            User user = userRepository.findByUsername(authentication.getName());
            if (user instanceof Admin) {
                response.addHeader("Authentication", AuthenticationType.ADMIN.toString());
            } else {
                response.addHeader("Authentication", AuthenticationType.VOTER.toString());
            }
        })
                .failureHandler((HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) -> {
                    response.addHeader("Authentication", AuthenticationType.GUEST.toString());
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                })
                .usernameParameter("username")
                .passwordParameter("password")
                .and().logout().logoutUrl("/logout").deleteCookies("JSESSIONID").invalidateHttpSession(true).clearAuthentication(true).logoutSuccessHandler((new HttpStatusReturningLogoutSuccessHandler(HttpStatus.OK)))
                .and().rememberMe();
    }

}