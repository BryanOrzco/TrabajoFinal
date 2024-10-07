package gp.Sisteme.Inventario.config;

import gp.Sisteme.Inventario.security.JWTAutenticacionFilter;
import gp.Sisteme.Inventario.security.JWTAuthorzationFilter;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.security.web.SecurityFilterChain;
import org.springframework.context.annotation.Bean;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@AllArgsConstructor
public class SecurityConfig {

    private final UserDetailsService userDetailsService;
    private final JWTAuthorzationFilter jwtAuthorzationFilter;


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, AuthenticationManager authenticationManager) throws Exception {
       JWTAutenticacionFilter jwtAutenticacionFilter = new JWTAutenticacionFilter();
       jwtAutenticacionFilter.setAuthenticationManager(authenticationManager);
       jwtAutenticacionFilter.setFilterProcessesUrl("/login");
        return http
                .cors()
                .and()
                .csrf().disable()
                .authorizeHttpRequests()
//                .requestMatchers("/inventario-app/**")
//                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilter(jwtAutenticacionFilter)
                .addFilterBefore(jwtAuthorzationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

//    @Bean
//    UserDetailsService userDetailsService(){
//        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
//        manager.createUser(User.withUsername("admin")
//                .password(passwordEncoder().encode("admin"))
//                .roles()
//                .build());
//        return manager;
//    }

    @Bean
    AuthenticationManager authenticationManager(HttpSecurity http, PasswordEncoder passwordEncoder) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder())
                .and()
                .build();
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

//    public static void main(String[] args) {
//        System.out.println("pass: "+ new BCryptPasswordEncoder().encode("admin"));
//    }
}
