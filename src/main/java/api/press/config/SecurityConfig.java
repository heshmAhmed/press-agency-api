package api.press.config;

import api.press.Filter.JwtAuthFilter;
import api.press.Filter.JwtVerifyFilter;
import api.press.service.IActorService;
import api.press.util.TokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import static org.springframework.security.config.http.SessionCreationPolicy.*;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final PasswordEncoder passwordEncoder;
    private final IActorService actorService;
    private final TokenUtil tokenUtil;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors().and().csrf()
                .disable()
                .sessionManagement().sessionCreationPolicy(STATELESS)
                .and()
                .authorizeRequests().antMatchers("/api/v1/**").permitAll()
                .and()
                .authorizeRequests().anyRequest().authenticated()
                .and()
                .addFilter(getJWTAuthenticationFilter())
                .addFilterBefore(jwtVerifyFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(actorService).passwordEncoder(passwordEncoder);
    }

    public JwtAuthFilter getJWTAuthenticationFilter() throws Exception {
        JwtAuthFilter jwtAuthFilter = new JwtAuthFilter(authenticationManager(), tokenUtil);
        jwtAuthFilter.setFilterProcessesUrl("/api/auth");
        return jwtAuthFilter;
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Bean
    public JwtVerifyFilter jwtVerifyFilter(){
        return new JwtVerifyFilter(tokenUtil);
    }


}
