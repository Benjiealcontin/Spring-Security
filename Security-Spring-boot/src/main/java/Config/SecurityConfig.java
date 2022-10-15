package Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;

import UserDetailsService.JpaUserDetailsService;





@Configuration
@EnableMethodSecurity
@EnableWebSecurity
public class SecurityConfig {
	
	
	private final RsaKeyProperties rsaKeys;

    private final JpaUserDetailsService myUserDetailsService;

    public SecurityConfig(JpaUserDetailsService myUserDetailsService,RsaKeyProperties rsaKeys) {
        this.myUserDetailsService = myUserDetailsService;
        this.rsaKeys = rsaKeys;
    }
    
    @Bean
    public AuthenticationManager authManager(UserDetailsService userDetailsService) {		//To authenticate the particular username and password
        var authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(new BCryptPasswordEncoder());			//To login using Bcrypt
        return new ProviderManager(authProvider);
    }
    
    
	
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable()) 
                .authorizeRequests( auth -> auth
                		
                		.mvcMatchers("/","/login").permitAll() //All can access
                		.mvcMatchers("/api/login").permitAll() //All can access
                		.mvcMatchers("/user/**").authenticated() //Only users can Access
                		.mvcMatchers("/admin/**").authenticated() //Only Admin can Access
                		.mvcMatchers("/api/posts/**").authenticated() //Only users and Admin can Access
                        .anyRequest().authenticated() 
                )
                .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) 
                .userDetailsService(myUserDetailsService)
                .headers(headers -> headers.frameOptions().sameOrigin())               
                .build();
    }
    
    @Bean
    JwtDecoder jwtDecoder() {		//JWT public key
        return NimbusJwtDecoder.withPublicKey(rsaKeys.publicKey()).build();
    }
    
    @Bean
    JwtEncoder jwtEncoder() {
        JWK jwk = new RSAKey.Builder(rsaKeys.publicKey()).privateKey(rsaKeys.privateKey()).build();		//JWT  private key
        JWKSource<SecurityContext> jwks = new ImmutableJWKSet<>(new JWKSet(jwk));
        return new NimbusJwtEncoder(jwks);
    }
    
    @Bean
    PasswordEncoder passwordEncoder() {				//To Brycpt the password for
        return new BCryptPasswordEncoder();			// example: $2a$10$nCmjCBuwM85nsZ7D1LEs4u.ZMsn2hSrf5hYLgBgsmmBwnFR4SZVC.
    }
    
}
