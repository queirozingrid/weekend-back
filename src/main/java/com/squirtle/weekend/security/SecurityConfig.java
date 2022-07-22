package com.squirtle.weekend.security;

import com.squirtle.weekend.repository.EstabelecimentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
//@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AutenticacaoService autenticacaoService;

    @Autowired
    private EstabelecimentoRepository estabelecimentoRepository;
    @Autowired
    private TokenService tokenService;

    @Override
    @Bean
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    // Autorização
    @Override
    protected void configure(HttpSecurity http) throws Exception{
       http.authorizeRequests()
               .antMatchers(HttpMethod.GET, "/estabelecimento/*").permitAll()
               .antMatchers(HttpMethod.POST, "/estabelecimento/salvar").permitAll()
               .antMatchers(HttpMethod.PUT, "estabelecimento/editar").permitAll()
               .antMatchers(HttpMethod.GET, "/evento/*").permitAll()
               .antMatchers(HttpMethod.POST, "/auth").permitAll()
               .anyRequest().authenticated()
               .and().csrf().disable()
               .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
               .and().addFilterBefore(new AutenticacaoTokenFilter(tokenService, estabelecimentoRepository), UsernamePasswordAuthenticationFilter.class);
    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(autenticacaoService).passwordEncoder(new BCryptPasswordEncoder());
    }


}
