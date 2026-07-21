package com.elotech.gerenciadorTarefas.infrastructure.security;

import com.elotech.gerenciadorTarefas.domain.usuario.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
public class JwtService {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private long expiration;

    /**
     * Gera um JWT para o usuário.
     */
    public String gerarToken(final Usuario usuario) {

        Date agora = new Date();
        Date validade = new Date(agora.getTime() + expiration);

        return Jwts.builder()
                .setSubject(usuario.getEmail())
                .setIssuedAt(agora)
                .setExpiration(validade)
                .signWith(getSecretKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    /**
     * Extrai o e-mail do token.
     */
    public String extrairEmail(final String token) {

        return extrairClaims(token).getSubject();
    }

    /**
     * Verifica se o token é válido.
     */
    public boolean tokenValido(final String token) {

        try {
            extrairClaims(token);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    /**
     * Extrai todas as informações do token.
     */
    private Claims extrairClaims(final String token) {

        return Jwts.parserBuilder()
                .setSigningKey(getSecretKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * Retorna a chave utilizada para assinar o JWT.
     */
    private SecretKey getSecretKey() {

        return Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }

}