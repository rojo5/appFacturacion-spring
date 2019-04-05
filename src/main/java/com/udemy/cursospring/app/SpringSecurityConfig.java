package com.udemy.cursospring.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.udemy.cursospring.app.auth.filter.JWTAuthenticationFilter;
import com.udemy.cursospring.app.auth.filter.JWTAuthorizationFilter;
import com.udemy.cursospring.app.auth.handler.LoginSuccessHandler;
import com.udemy.cursospring.app.models.service.JpaUserDetailsService;


//import javax.sql.DataSource;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.User.UserBuilder;
//import org.springframework.security.crypto.factory.PasswordEncoderFactories;


@EnableGlobalMethodSecurity(securedEnabled=true, prePostEnabled=true) //Para habilitar las anotaciones @Secured y las @PreAuthorize
@Configuration
public class SpringSecurityConfig  extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private LoginSuccessHandler successHandler;
	
	
	//Conexion a la BBDD
	/*
	@Autowired
	private DataSource dataSource;
	*/
	@Autowired
	private BCryptPasswordEncoder passwordEnconder;
	
	@Autowired
	private JpaUserDetailsService userDetailsService;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests().antMatchers("/", "/css/**", "/js/**", "/images/**", "/listar**", "/locale").permitAll()
		//Lo comento para que en vez de hacerlo de esta forma utilizo las anotaciones @Secured
		/*.antMatchers("/ver/**").hasAnyRole("USER")
		.antMatchers("/uploads/**").hasAnyRole("USER")
		.antMatchers("/form/**").hasAnyRole("ADMIN")
		.antMatchers("/eliminar/**").hasAnyRole("ADMIN")
		.antMatchers("/factura/**").hasAnyRole("ADMIN")*/
		.anyRequest().authenticated()
		//Autenticacion por sesiones
		/*.and()
		    .formLogin()
		        .successHandler(successHandler)
		        .loginPage("/login")
		    .permitAll()
		.and()
		.logout().permitAll()
		.and()
		.exceptionHandling().accessDeniedPage("/error_403")*/
		.and()
		.addFilter(new JWTAuthenticationFilter(authenticationManager()))
		.addFilter(new JWTAuthorizationFilter(authenticationManager()))
		.csrf().disable()
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

	}

	//Metodo que actuara como "repositorios" de los usuarios que se autenticaran
	@Autowired
	public void configurerGlobal(AuthenticationManagerBuilder builder) throws Exception{
		
		builder.userDetailsService(userDetailsService)
		.passwordEncoder(passwordEnconder);
		
		
		//autenticacion mediante JDBC
		/*
		builder.jdbcAuthentication()
		.dataSource(dataSource)
		.passwordEncoder(passwordEnconder)
		.usersByUsernameQuery("SELECT username, password, enabled FROM users WHERE username=?")
		.authoritiesByUsernameQuery("SELECT u.username, a.authority FROM  authorities a INNER JOIN users u ON (a.user_id=u.id) WHERE u.username=?");
		*/
		
	/*	PasswordEncoder encoder= PasswordEncoderFactories.createDelegatingPasswordEncoder();
		//Permite crear usuarios en memoria
		UserBuilder users = User.builder().passwordEncoder(encoder::encode);
		
		builder.inMemoryAuthentication()
		.withUser(users.username("admin").password("12345").roles("ADMIN", "USER"))
		.withUser(users.username("luis").password("12345").roles("USER"));
		
		*/
	}

}
