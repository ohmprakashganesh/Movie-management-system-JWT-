package com.jwt.AuthServices;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.annotation.Nonnull;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthFilterService extends OncePerRequestFilter {

        private final UserDetailsService userDetailsService;
        private final JwtService jwtService;


    @Override
    protected void doFilterInternal(@Nonnull HttpServletRequest request, 
                                   @Nonnull HttpServletResponse response, 
                                   @Nonnull FilterChain filterChain)throws ServletException,IOException{
                                            final String authHeader= request.getHeader("Authorization");
                                            String jwt;
                                            String username;
                                            
                                            if(authHeader ==null || !authHeader.startsWith("Bearer")){
                                                filterChain.doFilter(request, response);
                                                return;
                                            }
                                            //extract jwt
                                            jwt= authHeader.substring(7);


                                            //extract username form jwt
                                            username=jwtService.extractUsername(jwt);

                                            if(username !=null && SecurityContextHolder.getContext().getAuthentication()==null){
                                                UserDetails userDetails= userDetailsService.loadUserByUsername(username);
                                                if(jwtService.isTokenValid(jwt, userDetails)){
                                                    UsernamePasswordAuthenticationToken authenticationToken=new UsernamePasswordAuthenticationToken(
                                                        username,null, userDetails.getAuthorities()
                                                        );
                                                  
                                                        authenticationToken.setDetails(
                                                            new WebAuthenticationDetailsSource().buildDetails(request)
                                                        );
                                                        SecurityContextHolder.getContext().setAuthentication(authenticationToken);



                                                }
                                            }
                                            filterChain.doFilter(request, response);

                                   }

}
