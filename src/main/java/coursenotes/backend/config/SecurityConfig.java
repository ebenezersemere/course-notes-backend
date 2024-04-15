package coursenotes.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests( auth -> {
//                            auth.antMatchers("/auth/signin", "/auth/signup").permitAll();
                            auth.anyRequest().authenticated();
                })
                .oauth2Login(withDefaults())
                .csrf().disable() // for swagger to work, disable in production
//                .formLogin(withDefaults())
                .build();
    }
}
