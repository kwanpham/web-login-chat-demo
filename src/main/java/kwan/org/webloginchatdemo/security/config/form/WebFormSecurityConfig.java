package kwan.org.webloginchatdemo.security.config.form;

import kwan.org.webloginchatdemo.security.CustomeUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.session.HttpSessionEventPublisher;

/**
 * Created by https://github.com/kwanpham
 */
@Configuration
@EnableWebSecurity
@Order(1)
public class WebFormSecurityConfig extends WebSecurityConfigurerAdapter {



    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private HttpSessionEventPublisher httpSessionEventPublisher;


    @Autowired
    private CustomeUserDetailService userDetailServiceSec;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailServiceSec)
                .passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {


        http
                .cors()
                .and()
                .csrf()
                .disable();


        http

                .authorizeRequests()
                .antMatchers("/login").permitAll()
                .antMatchers("/signup").permitAll()
                .antMatchers("/logout").permitAll()
                .anyRequest().authenticated();

               http
                .formLogin()
                .loginProcessingUrl("/j_spring_security_check")
                .loginPage("/login")
                .usernameParameter("j_username")
                .passwordParameter("j_password")
                .defaultSuccessUrl("/chat")
                .failureUrl("/login?message=error");


        http
                .logout()
                .logoutUrl("/logout")
                .deleteCookies("JSESSIONID")
                .logoutSuccessUrl("/")
                .and()
                .exceptionHandling();

        // Cấu hình concurrent session
        http.sessionManagement().sessionFixation().newSession()
                .invalidSessionUrl("/login?message=timeout")
                .maximumSessions(1).expiredUrl("/login?message=max_session").maxSessionsPreventsLogin(true);

    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers(
                "/resources/**", "/static/**", "/css/**", "/js/**", "/images/**",
                "/resources/static/**", "/css/**", "/js/**", "/img/**", "/fonts/**",
                "/images/**", "/scss/**", "/vendor/**", "/favicon.ico", "/auth/**", "/favicon.png",
                "/v2/api-docs", "/configuration/ui", "/configuration/security",
                "/webjars/**", "/swagger-resources/**", "/swagger-ui.html", "/actuator",
                "/actuator/**", "/assets/**", "/j_spring_security_check/", "/vendor/**", "/dist/**",
                "/plugins/**", "/pages/**" ,"/socket/**");
    }
}
