package com.online.movie.ticket.core.security;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.online.movie.ticket.core.security.jwt.CustomAuthenticationEntryPoint;
import com.online.movie.ticket.service.AuthUserDetailService;
import com.online.movie.ticket.core.security.jwt.JwtAuthTokenFilter;


import lombok.extern.log4j.Log4j2;


/***
 * 
 * @author Sundar
 *
 *
 *	* void configure( AuthenticationManagerBuilder auth): To configure user details services
 *	* void configure( HttpSecurity http): To configure how requests are secured by interceptors
 *	* void configure( WebSecurity web): To configure Spring Security's filter chain
 *
 */
@Configuration
@Log4j2
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	
    @Autowired
    AuthUserDetailService userDetailsService;
    
    @Autowired
    private CustomAuthenticationEntryPoint unauthorizedHandler;
 


    /**
     * This method defines the source of authentication user information acquisition and password verification rules
     *
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    	log.debug("eSuit Custom Authentication Provider Started ");
    	auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
    	log.debug("eSuit WebSecurity Started ");
        super.configure(web);
        web.ignoring().antMatchers("/css/**", "/images/**", "/js/**", "/webjars/**");
    }
    
    
   @Override
    protected void configure(HttpSecurity http) throws Exception {
        log.debug("eSuit Custom HttpSecurity Started");

        http.cors().and().csrf().disable().
        authorizeRequests()
        .antMatchers("/api/v1/auth/signin").permitAll()
        .antMatchers("/api/v1/user/save").permitAll()
        .antMatchers("/api/v1/application/getallenums").permitAll()
        .anyRequest().authenticated()
        .and()
        .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        
     // Add our custom JWT security filter
        
        http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
        /**
         *  Spring Security is a filter based framework. 
         *  Either we are enabling existing filter and configuring it or adding our custom filter.
         *  
         *  
         *  Here filter can be any custom filter should be implementation of GenericFilterBean 
         *  most cases implementation of OncePerRequestFilterFilter will be used.
         */
        
    }
  
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public JwtAuthTokenFilter authenticationJwtTokenFilter() {
        return new JwtAuthTokenFilter();
    }
    
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
