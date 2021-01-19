package kwan.org.webloginchatdemo.security.config.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import kwan.org.webloginchatdemo.controller.api.request.LoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

import static kwan.org.webloginchatdemo.security.SecurityConstants.*;


/**
 * Created by https://github.com/kwanpham
 */
public class ApiJWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;


    public ApiJWTAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
        this.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/api/auth", "POST"));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest req,
                                                HttpServletResponse res) throws AuthenticationException {
        try {
            LoginRequest user = new ObjectMapper().readValue(req.getInputStream(),  LoginRequest .class);
            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            user.getUsername(),
                            user.getPassword(),
                            Collections.emptyList())
            );
        } catch (IOException e) {
            return null;
        }
    }

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Override
    protected void successfulAuthentication(HttpServletRequest req,
                                            HttpServletResponse res,
                                            FilterChain chain,
                                            Authentication auth) throws IOException, ServletException {
        if (auth.getPrincipal() != null) {
                String token = jwtTokenProvider.generateToken(auth);
                res.addHeader(HEADER_STRING, TOKEN_PREFIX + token);
                res.setContentType(MediaType.TEXT_HTML_VALUE);
                res.getWriter().write("Login successful");
            }
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        response.setContentType(MediaType.TEXT_HTML_VALUE);
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        response.getWriter().write("Wrong username or password");
    }
}
