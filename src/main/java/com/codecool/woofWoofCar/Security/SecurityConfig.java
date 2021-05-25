package com.codecool.woofWoofCar.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtTokenServices jwtTokenServices;

    public SecurityConfig(JwtTokenServices jwtTokenServices) {
        this.jwtTokenServices = jwtTokenServices;
    }

    @Override
    public void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .httpBasic().disable().cors().and()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/test/**").permitAll()
                .antMatchers("/api/v1/user/login").permitAll()
                .antMatchers("/api/v1/user/").authenticated()
                .antMatchers("/api/v1/user/update-user").authenticated()
                .antMatchers("/api/v1/user/register").permitAll()
                .antMatchers("/api/v1/user/logout").authenticated()
                .antMatchers("/api/v1/user/get-user/**").permitAll()
                .antMatchers(HttpMethod.GET, "/api/v1/ride/rides").authenticated()
                .antMatchers("/api/v1/ride/animal-types").authenticated()
                .antMatchers(HttpMethod.GET,"/api/v1/ride/car-types").authenticated()
                .antMatchers(HttpMethod.GET,"/api/v1/ride/rides/**").authenticated()
                .antMatchers(HttpMethod.POST, "/api/v1/ride/add-ride" ).authenticated()
                .antMatchers(HttpMethod.DELETE, "/api/v1/ride/**").hasRole("ADMIN")
                .antMatchers("/api/v1/booking/**").authenticated()
                .anyRequest().denyAll()
                .and()
                .addFilterBefore(new JwtTokenFilter(jwtTokenServices), UsernamePasswordAuthenticationFilter.class);



    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder;
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}