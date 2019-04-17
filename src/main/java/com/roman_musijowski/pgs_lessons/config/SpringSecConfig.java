package com.roman_musijowski.pgs_lessons.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SpringSecConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AuthenticationProvider authenticationProvider;

//    @Autowired
//    @Qualifier("daoAuthenticationProvider")
//    public void setAuthenticationProvider(AuthenticationProvider authenticationProvider) {
//        this.authenticationProvider = authenticationProvider;
//    }

//    @Bean
//    public PasswordEncoder passwordEncoder(StrongPasswordEncryptor passwordEncryptor){
//        PasswordEncoder passwordEncoder = new PasswordEncoder();
//        passwordEncoder.setPasswordEncryptor(passwordEncryptor);////////passwordEncoder not found
//        return passwordEncoder;
//    }



    private PasswordEncoder passwordEncoder;

    @Autowired
    public SpringSecConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(PasswordEncoder passwordEncoder,
                                                               UserDetailsService userDetailsService){

        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder);
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        return daoAuthenticationProvider;
    }

    @Autowired
    public void configureAuthManager(AuthenticationManagerBuilder authenticationManagerBuilder){
        authenticationManagerBuilder.authenticationProvider(authenticationProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        super.configure(http);

//        http.csrf().ignoringAntMatchers("/h2-console").disable()
//                .authorizeRequests().antMatchers("/**/favicon.ico")
//                .authorizeRequests().antMatchers("/static/css").permitAll()
//                .and().formLogin().loginPage("/login").permitAll()
//                .and().authorizeRequests().antMatchers("/").authenticated()
////                .and().authorizeRequests().antMatchers("/lesson/list").authenticated()
////                .and().authorizeRequests().antMatchers("/lesson/lessonForm").authenticated()
////                .and().authorizeRequests().antMatchers("/lesson/show").hasAnyAuthority("ADMIN")
////                .and().authorizeRequests().antMatchers("/user/list").hasAnyAuthority("ADMIN")
////                .and().authorizeRequests().antMatchers("/user/userForm").authenticated()
////                .and().authorizeRequests().antMatchers("/user/show").hasAnyAuthority("ADMIN")
//                .and().exceptionHandling().accessDeniedPage("/access_denied");

        http.csrf().ignoringAntMatchers("/h2-console").disable()
                .authorizeRequests().antMatchers("/static/css") .permitAll()
                .and().formLogin().loginPage("/login").permitAll()
                .and().authorizeRequests().antMatchers("/").authenticated()
                .and().authorizeRequests().antMatchers("/user/**").hasAnyAuthority("STUDENT")
                .and().authorizeRequests().antMatchers("/lesson/**").hasAnyAuthority("STUDENT")
                .and().exceptionHandling().accessDeniedPage("/access_denied");
    }
}
