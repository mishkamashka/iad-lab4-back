package se.ifmo.ru.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;
import se.ifmo.ru.service.UserDetailsService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests().anyRequest().fullyAuthenticated();
//        http.httpBasic();
        http
                .csrf().disable()
                .exceptionHandling()
//                .authenticationEntryPoint(restAuthenticationEntryPoint)
                .and()
                .authorizeRequests()
                .anyRequest().fullyAuthenticated()
                .and()
                .formLogin()
//                .successHandler(mySuccessHandler)
//                .failureHandler(myFailureHandler)
                .and()
                .logout();
    }

    @Component
    public final class RestAuthenticationEntryPoint
            implements AuthenticationEntryPoint {

        @Override
        public void commence(
                final HttpServletRequest request,
                final HttpServletResponse response,
                final AuthenticationException authException) throws IOException {

            response.sendError(HttpServletResponse.SC_UNAUTHORIZED,
                    "Unauthorized");
        }
    }
}
