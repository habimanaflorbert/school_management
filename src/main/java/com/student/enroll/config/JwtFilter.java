package com.student.enroll.config;

import java.io.IOException;

import com.student.enroll.user.service.MyUserDetailService;
import com.student.enroll.user.service.JwtService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;

import org.springframework.web.filter.OncePerRequestFilter;

@Configuration
@EnableWebSecurity
public class JwtFilter extends OncePerRequestFilter{
  
  @Autowired
  JwtService jwtService;
  
  @Autowired
  ApplicationContext context;
  
  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException,ServletException {
      
      String authHeader = request.getHeader("Authorization");
      String token = null;
      String userName = null;
      
      if(authHeader != null && authHeader.startsWith("Bearer ")){
          token = authHeader.substring(7);
          try{

            userName = jwtService.extractUserName(token);
          }catch (Exception e){
            request=null;
            response=null;
          }
          
        }
      
      if(userName != null && SecurityContextHolder.getContext().getAuthentication()==null){
          
          UserDetails userDetails = context.getBean(MyUserDetailService.class).loadUserByUsername(userName);
          
          if(jwtService.validateToken(token, userDetails)){
              UsernamePasswordAuthenticationToken authToken =
                      new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
              authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
              SecurityContextHolder.getContext().setAuthentication(authToken);
          }

      }
      
      System.out.println("filterChain");
      filterChain.doFilter(request, response);
  }
    
    
}
