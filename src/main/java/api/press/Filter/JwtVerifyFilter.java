package api.press.Filter;


import api.press.model.WebToken;
import api.press.util.QueryUtil;
import api.press.util.TokenUtil;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Slf4j
@RequiredArgsConstructor
public class JwtVerifyFilter extends OncePerRequestFilter {
    private final TokenUtil tokenUtil;
    @Value("${auth.header}")
    private String TOKEN_HEADER;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String header = request.getHeader(TOKEN_HEADER);
        final SecurityContext securityContext = SecurityContextHolder.getContext();
        if(header != null && securityContext.getAuthentication() == null){
            String token = header.substring("Bearer ".length());
            WebToken webToken = tokenUtil.mapJWT(token);
            securityContext.setAuthentication(
                    new UsernamePasswordAuthenticationToken(webToken.getUsername(),
                            null,
                            webToken.getAuthority()));
        }
        filterChain.doFilter(request, response);
    }
}
