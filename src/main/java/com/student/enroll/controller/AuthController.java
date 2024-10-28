package com.student.enroll.controller;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.student.enroll.response.ApiResponse;
import com.student.enroll.user.model.User;
import com.student.enroll.user.repository.UserPrincipal;
import com.student.enroll.user.request.AddUserRequest;
import com.student.enroll.user.request.LoginUserRequest;
import com.student.enroll.user.service.IUserService;
import com.student.enroll.user.service.JwtService;

import org.springframework.web.bind.annotation.GetMapping;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    
    private final IUserService userService;
    private final JwtService jwtService;
    
    private final AuthenticationManager authenticationManager;
    
    @GetMapping("/me")
    public ResponseEntity<ApiResponse> userInformation(@AuthenticationPrincipal UserPrincipal userDetails) {
        try { 
            
        return ResponseEntity.ok(new ApiResponse("added sucessful", userDetails));
    } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
    }
    }
    
    @PostMapping("/register")
    public ResponseEntity<ApiResponse> createUser(@RequestBody AddUserRequest item) {

        try {
            if (userService.getUserByUsername(item.getUsername()).isPresent()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(null,"username is arleady exist"));
            }
            if (userService.getUserByEmail(item.getEmail()).isPresent()) {
                return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(null,"Email is arleady exist"));
            }
            User userData=userService.createUser(item);
            
            return ResponseEntity.ok(new ApiResponse("added sucessful", userData));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), item));
        }
     
    }

     @PostMapping("/login")
    public ResponseEntity<ApiResponse> login(@RequestBody LoginUserRequest user) {
        try {
            Authentication authentication=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword()));
            if (authentication.isAuthenticated()){
                return ResponseEntity.ok(new ApiResponse("accepted",jwtService.generateToken(user.getUsername()))); 
            }else{
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse("failed",null));
            }
    } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(e.getMessage(),null));
    }
    }
}
