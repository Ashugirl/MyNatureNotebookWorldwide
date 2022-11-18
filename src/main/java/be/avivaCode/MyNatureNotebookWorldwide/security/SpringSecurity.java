package be.avivaCode.MyNatureNotebookWorldwide.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SpringSecurity {

    // allows app to show different content based on role.
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
        auth
                .inMemoryAuthentication()
                .withUser("user").password(passwordEncoder().encode("password")).roles("USER")
                .and()
                .withUser("admin").password(passwordEncoder().encode("admin")).roles("ADMIN");
    }

        //TODO make not static - made static to allow access to encrypted pws for dummy data
        @Bean
        public static PasswordEncoder passwordEncoder() {
            return new BCryptPasswordEncoder();
        }

        // configure security filter chain
        @Bean
        public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
            http.headers().frameOptions().disable();
            http.csrf().disable()
                    .authorizeRequests()
                    .antMatchers("/register/**").permitAll()
                    .antMatchers("/index").permitAll()
                    .antMatchers("/users").hasRole("ADMIN")
                    .antMatchers("/h2/**").permitAll()
                    .antMatchers("/addSighting/**").permitAll()
                    .antMatchers("/yourPage").permitAll()
                    .antMatchers("/sightingPage").permitAll()
                    .antMatchers("/profile").permitAll()
                    .and()
                    .formLogin(
                            form -> form
                                    .loginPage("/login")
                                    .loginProcessingUrl("/login")
                                    .defaultSuccessUrl("/index")
                                    .permitAll()
                    ).logout(
                            logout -> logout
                                    .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                                    .permitAll()
                    );
            return http.build();
        }
    }