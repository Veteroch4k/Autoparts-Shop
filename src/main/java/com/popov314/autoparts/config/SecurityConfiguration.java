package com.popov314.autoparts.config;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(jsr250Enabled = true)
public class SecurityConfiguration {

  @Autowired
  private DataSource dataSource;

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder(8);
  }

  @Bean
  public UserDetailsService authentication() {

    JdbcUserDetailsManager manager = new JdbcUserDetailsManager(dataSource);

    manager.setUsersByUsernameQuery(
        "SELECT username, password, enabled FROM users WHERE username = ?"
    );

    manager.setAuthoritiesByUsernameQuery(
        "SELECT username, role FROM users WHERE username = ?"
    );

    return manager;

  }

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

    http

        .authorizeHttpRequests(auth -> auth

            .requestMatchers("/admin/**").hasRole("DIRECTOR")

            .requestMatchers("/css/**", "/js/**", "/images/**", "/webjars/**").permitAll()

            .requestMatchers("/auth/login", "/auth/register", "/auth/forgot-password","/auth/reset-password", "/public/**").permitAll()

            .requestMatchers(HttpMethod.POST, "/auth/register").permitAll()

            .anyRequest().authenticated()
        )

        .formLogin(form -> form
            .loginPage("/auth/login")
            .loginProcessingUrl("/perform-login")
            .defaultSuccessUrl("/", true)
            .failureUrl("/auth/login?error=true")
            .permitAll()
        )

        // 3. Выход из системы
        .logout(logout -> logout
            .logoutUrl("/logout")
            .logoutSuccessUrl("/auth/login?logout=true")
            .deleteCookies("JSESSIONID")
            .permitAll()
        );

    return http.build();
  }


}
