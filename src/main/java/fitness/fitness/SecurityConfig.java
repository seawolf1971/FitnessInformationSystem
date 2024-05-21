package fitness.fitness;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

 @Autowired
 CustomUserDetailsService customUserDetailsService;

 @Bean
 public static PasswordEncoder passwordEncoder() {
  return new BCryptPasswordEncoder();
 }

 @Bean
public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http.csrf().disable()
        .authorizeRequests()
            .requestMatchers("/register").permitAll()
            .requestMatchers("/home").permitAll()
            .requestMatchers("/admin/**").hasRole("ADMIN")
            .anyRequest().authenticated()
        .and()
        .formLogin()
            .loginPage("/login")
            .loginProcessingUrl("/login")
            .defaultSuccessUrl("/home", true)
            .permitAll()
        .and()
        .logout()
            .invalidateHttpSession(true)
            .clearAuthentication(true)
            .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
            .logoutSuccessUrl("/login?logout")
            .permitAll();

    return http.build();
}

 @Autowired
 public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
  auth.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder());
 }
}