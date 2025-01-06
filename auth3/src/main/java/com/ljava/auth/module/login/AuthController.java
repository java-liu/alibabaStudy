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
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserRedisRespository userRedisRespository;

    public AuthController(AuthenticationManager authenticationManager, UserRedisRespository userRedisRespository) {
        this.authenticationManager = authenticationManager;
        this.userRedisRespository = userRedisRespository;
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody LoginRequest request){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );

        String token = JwtUtil.generateToken(request.getUsername());
        Map<String, String> response = Map.of("token", token);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/hello")
    public String hello() {
        return "Hello, Auth,World!";
    }
}
