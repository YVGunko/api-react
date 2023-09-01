package com.yg.apireact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.web.cors.CorsConfigurationSource;



@Configuration
@EnableWebSecurity(debug = true)
@EnableMethodSecurity(prePostEnabled = true)
//@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class WebConfig  {
    @Qualifier("userDetailsServiceImpl")
    @Autowired
    private UserDetailsService userDetailsService;
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
            source.registerCorsConfiguration("/auth/**", corsConfig);
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
    	http
		.authorizeRequests()
			.antMatchers(HttpMethod.OPTIONS).permitAll()
			.antMatchers("/", "/test/**", "/favicon.ico").permitAll()
			.antMatchers("/api/**").hasAnyRole("ADMIN", "ORDER", "PRICE")
			.antMatchers("/order/edit/**").hasAnyRole("ORDER")
			.antMatchers("/order/view/**").hasAnyRole("ADMIN", "TOP", "ORDER")
			.antMatchers("/price/**").hasAnyRole("PRICE")
			
			.and().exceptionHandling().accessDeniedPage("/403")
		.and()
			.formLogin()
			//.failureHandler(authenticationFailureHandler())
		.and()
			.httpBasic()
		.and().logout().permitAll().and().csrf().disable()
		.logout()                                                                
        .logoutUrl("/logout")                                                 
        .logoutSuccessUrl("/index")                                                                       
        .invalidateHttpSession(true)                                                                                     
        .clearAuthentication(true).deleteCookies("SESSION");
    return http.build();
    }
    /*@Bean
    public AuthenticationFailureHandler authenticationFailureHandler() {
        return new WebAuthenticationFailureHandler();
    }*/
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
    protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
    }
}
