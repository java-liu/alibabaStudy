package com.ljava.auth.module.login;

import com.ljava.auth.module.login.request.LoginRequest;
import com.ljava.auth.module.user.dao.UserRedisRespository;
import com.ljava.auth.utils.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/user")
public class LoginController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello, User!";
    }
}
