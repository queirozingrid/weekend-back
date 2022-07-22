package com.squirtle.weekend.security;

import com.squirtle.weekend.models.Estabelecimento;
import com.squirtle.weekend.repository.EstabelecimentoRepository;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AutenticacaoTokenFilter extends OncePerRequestFilter {

    private TokenService tokenService;

    private EstabelecimentoRepository estabelecimentoRepository;

    public AutenticacaoTokenFilter(TokenService tokenService, EstabelecimentoRepository estabelecimentoRepository) {
        this.tokenService = tokenService;
        this.estabelecimentoRepository = estabelecimentoRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String token = recuperarToken(request);

        boolean valido = tokenService.isValido(token);
        
        if(valido){
            autenticar(token);
        }

        filterChain.doFilter(request, response);

    }

    private void autenticar(String token) {
        String cnpjEst = tokenService.getCnpjEst(token);
        Estabelecimento estabelecimento = estabelecimentoRepository.findByCnpj(cnpjEst).get();
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(estabelecimento, null, estabelecimento.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);

    }

    private String recuperarToken(HttpServletRequest request){
        String token = request.getHeader("Authorization");
        if(token == null || token.isEmpty() || !token.startsWith("Bearer ")){
            return null;
        }
        return token.substring(7, token.length());
    }
}
