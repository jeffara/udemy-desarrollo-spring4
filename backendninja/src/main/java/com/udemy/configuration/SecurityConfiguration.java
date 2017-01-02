package com.udemy.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
//Permite que possam ser utilizadas anotacoes de seguranca para permitir o acesso a metodos/recursos URI da aplicacao 
//apenas por usuarios autenticados que possuirem papeis previamente configurados
@EnableGlobalMethodSecurity(prePostEnabled=true) 
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	@Qualifier("userService")
	private UserDetailsService userDetailsService;
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		    .antMatchers("/css/*", "/imgs/*").permitAll() //Permitira a carga dos recursos estaticos sem necessidade de autenticacao
		    .anyRequest().authenticated()
		    .and()
		    .formLogin().loginPage("/login").loginProcessingUrl("/logincheck")
		    .usernameParameter("username")
		    .passwordParameter("password")
		    .defaultSuccessUrl("/loginsuccess").permitAll()
		    .and()
		    .logout().logoutUrl("/logout").logoutSuccessUrl("/login?logout").permitAll();
	}
}
