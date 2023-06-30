package com.yg.apireact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfigurationSource;

@Configuration
@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class WebConfig  {
	/*@Bean
    @Primary
    public CorsConfigurationSource corsConfiguration() {
        CorsConfiguration corsConfig = new CorsConfiguration();
        corsConfig.setAllowedOrigins(Arrays.asList("http://localhost:4232", "http://localhost:3000"));
        corsConfig.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE", "HEAD", "OPTIONS"));
        corsConfig.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        if (corsConfig.getAllowedOrigins() != null && !corsConfig.getAllowedOrigins().isEmpty()) {
            source.registerCorsConfiguration("/api/**", corsConfig);
            source.registerCorsConfiguration("/**", corsConfig);
            source.registerCorsConfiguration("/oauth/**", corsConfig);
        }

        return source;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, CorsConfigurationSource configurationSource) throws Exception {
        return http
            .cors().configurationSource(configurationSource)
            .and()
            .headers().frameOptions().sameOrigin()
            .and()
            .build();
    }

    @Bean
    public CookieSameSiteSupplier cookieSameSiteSupplier(){
        return CookieSameSiteSupplier.ofNone();
    }*/
	
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, CorsConfigurationSource configurationSource) throws Exception {
    	http.authorizeRequests()
        .anyRequest()
        .authenticated()
        .and()
        .httpBasic()
        .and()
        .cors(); ;
    return http.build();
    }
}
