package gr.codehub.basic.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class MainSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic()
           .and()
                //Enables cross origin requests
                .cors()
           .and()
                //Makes the system stateless and requires auth in every call
                //Comment out in order to integrate system with thymeleaf
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
                .authorizeRequests()
                .antMatchers("/ping")
                .permitAll()
            .and()
                .authorizeRequests()
               .antMatchers("/admin")
               .hasRole("ADMIN")
            .and()
                .authorizeRequests()
                .antMatchers("/product")
                .hasRole("USER")

                .anyRequest()
                .authenticated() ;
    }

    @Autowired
    private DataSource dataSource;

    @Autowired
    private CustomAuthenticationProvider authProvider;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

//    //-- 1. from memory
//        auth.inMemoryAuthentication()
//                .passwordEncoder(passwordEncoder())
//                .withUser("user")
//                .password(ENCODED_PASSWORD)
//                .roles("USER")
//                .and()
//                .withUser("2")
//                .password(new BCryptPasswordEncoder().encode("2"))
//                .roles("AUTHOR") ;


        //    //-- 2.  from database
        auth.jdbcAuthentication().dataSource(dataSource)
                .usersByUsernameQuery("select username, password, enabled  from users where username=?")
                .authoritiesByUsernameQuery("select username, authority  from authorities where username=?")
                .passwordEncoder(new BCryptPasswordEncoder());

        //    //-- 3. custom

//        auth.authenticationProvider(authProvider);


    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
