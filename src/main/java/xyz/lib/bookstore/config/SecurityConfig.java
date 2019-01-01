package xyz.lib.bookstore.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.session.web.http.HeaderHttpSessionStrategy;
import org.springframework.session.web.http.HttpSessionStrategy;
import xyz.lib.bookstore.service.impl.UserDetailsServiceImpl;

import static xyz.lib.bookstore.constants.Constants.PUBLIC_MATCHERS;

/**
 * PROJECT   : bookstore
 * PACKAGE   : net.lib.bookstore.config
 * USER      : sean
 * DATE      : 28-Fri-Dec-2018
 * TIME      : 21:14
 * E-MAIL    : kudzai@bcs.org
 * CELL      : +27-64-906-8809
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private Environment env;
    private UserDetailsServiceImpl userSecurityServiceImpl;
    private EncryptionConfig securityUtility;


    private BCryptPasswordEncoder passwordEncoder() {
        return securityUtility.passwordEncoder();
    }

    @Autowired
    public void setSecurityUtility(EncryptionConfig securityUtility) {
        this.securityUtility = securityUtility;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().cors().disable().httpBasic().and().authorizeRequests()
                .antMatchers(PUBLIC_MATCHERS).permitAll().anyRequest().authenticated();
    }

    @Autowired
    public void setEnv(Environment env) {
        this.env = env;
    }

    @Autowired
    public void setUserSecurityServiceImpl(UserDetailsServiceImpl userSecurityServiceImpl) {
        this.userSecurityServiceImpl = userSecurityServiceImpl;
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userSecurityServiceImpl).passwordEncoder(passwordEncoder());
    }


    @Bean
    public HttpSessionStrategy httpSessionStrategy(){
        return new HeaderHttpSessionStrategy();
    }
}
