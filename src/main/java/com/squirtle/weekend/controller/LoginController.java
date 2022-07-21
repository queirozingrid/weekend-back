package com.squirtle.weekend.controller;

import com.squirtle.weekend.dto.output.token.TokenDTO;
import com.squirtle.weekend.form.LoginForm;
import com.squirtle.weekend.repository.EstabelecimentoRepository;
import com.squirtle.weekend.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.message.AuthException;
import javax.validation.Valid;


@RestController
@RequestMapping("/auth")
public class LoginController {

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private EstabelecimentoRepository estabelecimentoRepository;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity<?> autenticar(@RequestBody @Valid LoginForm login){
        UsernamePasswordAuthenticationToken dadosLogin = login.converter();

        try{
            Authentication authentication = authManager.authenticate(dadosLogin);
            String token = tokenService.gerarToken(authentication);
           // System.out.println(token);

            return ResponseEntity.ok(new TokenDTO(token, "Bearer"));

        } catch (AuthenticationException e){
            return ResponseEntity.badRequest().build();
        }

    }

}
