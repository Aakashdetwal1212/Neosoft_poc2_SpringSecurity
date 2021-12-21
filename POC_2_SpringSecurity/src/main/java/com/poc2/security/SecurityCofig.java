package com.poc2.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityCofig extends WebSecurityConfigurerAdapter {

	// Authentication : User --> Roles
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
				.passwordEncoder(new BCryptPasswordEncoder())
				.withUser("user1").password("$2a$10$7GsKjWaF5ovimW5pwXmpMuwCwq/tiUiBewqJBZ7FL6PqAxqdteSIq")
				.roles("USER").and().withUser("admin1")
				.password("$2a$10$H7onwod8WJFIdpT8xas44u5gTVSTOWsNbDy4O/8QYeGqHVRWYwvIy")
				.roles("USER", "ADMIN");
	}

	// Authorization : Role -> Access
	protected void configure(HttpSecurity http) throws Exception {
		http.httpBasic().and().authorizeRequests().antMatchers("/student/insert/**").hasRole("USER").antMatchers("/**")
				.hasRole("ADMIN").and().csrf().disable().headers().frameOptions().disable();
	}
}
