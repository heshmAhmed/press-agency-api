package api.press.util;

import api.press.model.Actor;
import api.press.model.WebToken;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
public class TokenUtil {
    private final String CLAIMS_ID = "id";
    private final String CLAIMS_ROLE = "role";

    @Value("${auth.expiration}")
    private  Long TOKEN_VALIDITY = 604000L;
    @Value("${auth.secret}")
    private String TOKEN_SECRET = "press-agency-api-secret-key";

    public String generateAccessToke(Actor userDetails){
        return JWT.create()
                .withSubject(userDetails.getUsername())
                .withExpiresAt(generateExpirationDate())
                .withIssuedAt(new Date())
                .withClaim(CLAIMS_ID, userDetails.getId())
                .withClaim(CLAIMS_ROLE, userDetails.getRole().getName())
                .sign(Algorithm.HMAC256(TOKEN_SECRET.getBytes()));
    }

    private  Date generateExpirationDate() {
        return new Date(System.currentTimeMillis() + TOKEN_VALIDITY * 1000);
    }

    public WebToken mapJWT(String token){
        try{
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(TOKEN_SECRET)).build();
            DecodedJWT decodedJWT = verifier.verify(token);
            return new WebToken(
                    decodedJWT.getSubject(),
                    decodedJWT.getClaim("id").asInt(),
                    decodedJWT.getExpiresAt(),
                    decodedJWT.getClaim("iat").asDate(),
                    List.of(new SimpleGrantedAuthority(decodedJWT.getClaim("role").asString())));
        }catch (Exception e){
            return null;
        }
    }

    public WebToken getCurrentWebToken(){
        return (WebToken) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
