package app.secure.securify.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        return httpSecurity
                .authorizeHttpRequests( auth ->{
                    auth.requestMatchers("/","/error","/login", "/oauth2/**").permitAll();
                    auth.anyRequest().authenticated();
                })
                .oauth2Login(oauth2Login ->{
                    oauth2Login.loginPage("/login");
//                    oauth2Login.loginPage("/oauth2/authorization/custom-client");
                    oauth2Login.failureUrl("/login?error=true");
                })
                .logout((logout)-> {
                    logout.logoutUrl("/logout");
                    logout.logoutSuccessUrl("/login");
                    logout.invalidateHttpSession(true);
                    logout.deleteCookies("JSESSIONID");
                })
                .build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        config.addAllowedOrigin("http://localhost:9000/");
        config.setAllowCredentials(true);
        source.registerCorsConfiguration("/**", config);
        return source;
    }
}
