package com.squirtle.weekend.security;
import com.squirtle.weekend.models.Estabelecimento;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenService {

    @Value("${com.squirtle.weekend.jwt.expiration}")
    private String expiration;

    @Value("${com.squirtle.weekend.jwt.secret}")
    private String secret;


    public String gerarToken(Authentication authentication){
        Estabelecimento estabelecimento = (Estabelecimento) authentication.getPrincipal();
        Date today = new Date();
        Date expirationDate = new Date(today.getTime() + Long.parseLong(expiration));

        return Jwts.builder()
                .setIssuer("API - THIS.ENVOLVE - WKND BACK")
                .setSubject(estabelecimento.getCnpj())
                .setIssuedAt(today)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS256, secret.getBytes())
                .compact();
    }
}
