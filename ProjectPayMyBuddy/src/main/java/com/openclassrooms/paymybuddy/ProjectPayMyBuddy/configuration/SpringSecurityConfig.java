package com.openclassromms.paymybuddy.ProjectPayMyBuddy.configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;

import javax.sql.DataSource;


@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {


     @Autowired
     protected void configure(AuthenticationManagerBuilder auth, DataSource dataSource) throws Exception {
     auth.jdbcAuthentication()
     .dataSource(dataSource)
     .usersByUsernameQuery("SELECT email, password, true FROM user WHERE email = ? ")
     .authoritiesByUsernameQuery("SELECT email, 'ROLE_USER' FROM user WHERE email = ?");


     }

  /**
    @Override
    protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception{
        authenticationManagerBuilder.inMemoryAuthentication()
                .withUser("spring").password(passwordEncoder().encode("test")).roles("ADMIN");
    }
**/

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((requests) -> requests
                        .antMatchers("/", "/newUser").permitAll()
                        .anyRequest().authenticated()

                )

                .formLogin((form) -> {
                            try {
                                form
                                        .loginPage("/login")
                                        .permitAll()
                                        .defaultSuccessUrl("/home")
                                        .and()
                                        .rememberMe().tokenValiditySeconds(7*24*60*60);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }

                )

                .logout((logout) -> logout.permitAll())
        ;

    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }



/**
 @Override
 protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception{
 authenticationManagerBuilder.inMemoryAuthentication()
 .withUser("admin").password(passwordEncoder().encode("test")).roles("ADMIN");
 }
 @Override
 public void configure(HttpSecurity http) throws Exception {
 http.httpBasic()
 .and()
 .authorizeRequests()
 .anyRequest().authenticated()
 .and()
 .formLogin();
 }
 @Bean
 public PasswordEncoder passwordEncoder() {
 return new BCryptPasswordEncoder();
 }
**/
}