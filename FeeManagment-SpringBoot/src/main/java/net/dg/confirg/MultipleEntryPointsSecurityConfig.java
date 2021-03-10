package net.dg.confirg;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import net.dg.service.AccountantService;

@Configuration
@EnableWebSecurity
public class MultipleEntryPointsSecurityConfig {

	@Bean
	public static BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Configuration
	@Order(1)
	public static class AdminConfig extends WebSecurityConfigurerAdapter {

		public AdminConfig() {
			super();
		}
		
		@Override
		protected void configure(HttpSecurity http) throws Exception {
            http.requestMatcher(new AntPathRequestMatcher("/admin_login*"))
            		.authorizeRequests().antMatchers(
	                "/accountants/**").hasRole("ADMIN")
                    .anyRequest().authenticated()
                    .and()
                    .formLogin()
                    .loginPage("/admin_login")
                    .loginProcessingUrl("/admin_login")
                    .defaultSuccessUrl("/accountants")
                    .permitAll()
                    .and()
                    .logout()
                    .permitAll();
        }
       
		
		 @Override
		  protected void configure(AuthenticationManagerBuilder auth) throws Exception
		  {
		    String password = passwordEncoder().encode("admin");
		    auth.inMemoryAuthentication().passwordEncoder(passwordEncoder()).withUser("admin@gmail.com").password(password).roles("ADMIN");
		  }
	}
	
		@Configuration
		@Order(2)
		public static class UserConfig extends WebSecurityConfigurerAdapter {
			public UserConfig() {
				super();
			}

			@Autowired
			private AccountantService accountantService;

			@Bean
			public DaoAuthenticationProvider authenticationProvider() {
				DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
				auth.setUserDetailsService(accountantService);
				auth.setPasswordEncoder(passwordEncoder());
				return auth;
			}
			
			 @Override
		        protected void configure(HttpSecurity http) throws Exception {
		            http.requestMatcher(new AntPathRequestMatcher("/**"))
		            .authorizeRequests().antMatchers(
		            		"/accountants/**").hasRole("ADMIN")
		                    .anyRequest().authenticated()
		                    .and()
		                    .formLogin()
		                    .loginPage("/user_login")
		                    .defaultSuccessUrl("/students")
		                    .permitAll()
		                    .and()
		                    .logout()
		                    .permitAll();
		        }


			
			
			@Override
			protected void configure(AuthenticationManagerBuilder auth) throws Exception
			{

				auth.authenticationProvider(authenticationProvider());
			}
		}
	}


