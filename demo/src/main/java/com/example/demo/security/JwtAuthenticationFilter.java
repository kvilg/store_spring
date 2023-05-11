package com.example.demo.security;

import com.example.demo.servis.UserService;
import com.example.demo.servis.JwtService;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

import static com.example.demo.model.Constants.HEADER_STRING;
import static com.example.demo.model.Constants.TOKEN_PREFIX;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserService service;

    @Autowired
    private JwtService jwtSevice;

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        logger.warn("1____________________________________________________________________");
        String header = req.getHeader(HEADER_STRING);
        String cookie = req.getHeader(HttpHeaders.COOKIE);
        logger.warn(cookie);
        String username = null;
        String authToken = null;
        if (header != null && header.startsWith(TOKEN_PREFIX)) {
            
            authToken = header.replace(TOKEN_PREFIX,"");
            if(jwtSevice.findToken(authToken)){
                throw new SecurityException("NOT VALID TOKEN");
            }
            jwtSevice.addTokenUse(authToken);
            try {
                username = jwtTokenUtil.getUsernameFromToken(authToken);
            } catch (IllegalArgumentException e) {
                logger.error("an error occured during getting username from token", e);
            } catch (ExpiredJwtException e) {
                logger.warn("the token is expired and not valid anymore", e);
            } catch(SignatureException e){
                logger.error("Authentication Failed. Username or Password not valid.");
            }
        } else if(cookie != null) {
            try {
                int a1 = cookie.indexOf("TOKEN=Bearer ") + 13;
                cookie = cookie.substring(a1);
                logger.warn(cookie + "    COOKIES");
                int a2 = cookie.indexOf(";") ;
                if(a2 == -1){
                    authToken = cookie;
                }else {
                    authToken = cookie.substring(0, a2);
                }
                logger.warn(authToken + "    authToken");
                username = jwtTokenUtil.getUsernameFromToken(authToken);
                logger.warn(cookie + "    username");
            }catch (Exception e){
                logger.error("bad cookies", e);
            }
        }else{
            logger.warn("couldn't find bearer string, will ignore the header");
        }
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            UserDetails userDetails = userDetailsService.loadUserByUsername(username);

            if (jwtTokenUtil.validateToken(authToken, userDetails)) {
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN")));
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(req));
                logger.info("authenticated user " + username + ", setting security context");
                SecurityContextHolder.getContext().setAuthentication(authentication);
                try {
                    res.addHeader("Authorization",TOKEN_PREFIX+jwtTokenUtil.generateToken(service.getByLogin(username)));
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }
        chain.doFilter(req, res);
        logger.warn("2____________________________________________________________________");
    }
}