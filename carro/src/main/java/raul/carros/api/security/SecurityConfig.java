package raul.carros.api.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import raul.carros.api.security.jwt.JwtAuthenticationFilter;
import raul.carros.api.security.jwt.JwtAuthorizationFilter;
import raul.carros.api.security.jwt.handler.AccessDeniedHandler;
import raul.carros.api.security.jwt.handler.UnauthorizedHandler;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true) // Config para perfil de acesso API
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	// userDetailsService estÃ¡ Utilizando o identifica da classe que esta sendo injetada
	@Autowired
	@Qualifier("userDetailsService")
	private UserDetailsService userDetailsService;
	
	 @Autowired
	 private UnauthorizedHandler unauthorizedHandler;

	 @Autowired
	 private AccessDeniedHandler accessDeniedHandler;
	 
	 
	 @Override
	    protected void configure(HttpSecurity http) throws Exception {
	        AuthenticationManager authManager = authenticationManager();

	        http
	                .authorizeRequests()
	                .antMatchers(HttpMethod.GET, "/api/v1/login").permitAll()
	                .antMatchers("/v2/api-docs", "/configuration/**", "/swagger*/**", "/webjars/**")
	                .permitAll()
	                .anyRequest().authenticated()
	                .and().csrf().disable()
	                .addFilter(new JwtAuthenticationFilter(authManager))
	                .addFilter(new JwtAuthorizationFilter(authManager, userDetailsService))
	                .exceptionHandling()
	                .accessDeniedHandler(accessDeniedHandler)
	                .authenticationEntryPoint(unauthorizedHandler)
	                .and()
	                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	    }

	    @Override
	    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

	        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

	        auth.userDetailsService(userDetailsService).passwordEncoder(encoder);
	    }
	    
	    
	    
	
//	@Override
//    protected void configure(HttpSecurity http) throws Exception {
//
//        http
//            .authorizeRequests()
//            .anyRequest().authenticated()
//            .and().httpBasic()
//            .and().csrf().disable();
//    } 
//	 
//	 // Dois usuÃ¡rio com perfis diferentes
//	 @Override
//	 protected void configure(AuthenticationManagerBuilder auth) throws Exception{
//		 BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//		 
//		 auth.userDetailsService(userDetailsService).passwordEncoder(encoder);
//		 
//		 
//		 // Substituido pela injeÃ§Ã£o de dependencia de outra classe criada
////		 auth.inMemoryAuthentication().passwordEncoder(encoder)
////		 .withUser("user").password(encoder.encode("user")).roles("USER").and()
////	 	 .withUser("admin").password(encoder.encode("admin")).roles("USER", "ADMIN");
//	 }
	 
	 

}
