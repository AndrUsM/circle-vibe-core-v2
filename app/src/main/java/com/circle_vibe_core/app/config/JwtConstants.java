package com.circle_vibe_core.app.config;

import io.jsonwebtoken.Jwts;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;

public final class JwtConstants {

    private JwtConstants() {}

    public static final MacAlgorithm NIMBUS_ALGORITHM = MacAlgorithm.HS256;

    public static final io.jsonwebtoken.security.MacAlgorithm JJWT_ALGORITHM = Jwts.SIG.HS256;
}