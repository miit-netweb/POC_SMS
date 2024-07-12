package Microservices.Auth_Server.Controller;

import Microservices.Auth_Server.Dto.AuthRequest;
import Microservices.Auth_Server.Dto.ResponseDto;
import Microservices.Auth_Server.Dto.SubscriberDto;
import Microservices.Auth_Server.Dto.TokenGenerationResponseDto;
import Microservices.Auth_Server.Service.AuthService;
import Microservices.Auth_Server.Service.JwtService;
import Microservices.Auth_Server.proxy.JwtServerProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService service;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtServerProxy jwtServerProxy;

    @PostMapping("/register")
    public ResponseEntity<?> addNewUser(@RequestBody SubscriberDto user) {
        try{
            if(service.ValidateResponse(user) == null){
                ResponseDto responseDto = service.checkPartnerNumber(user);
               if(responseDto == null){
                   ResponseEntity<?> tokenGenerationResponseDto = jwtServerProxy.tokenGenerationForPartner(user.getEnrollmentDetail().getPartnerNumber());
                   return ResponseEntity.ok(tokenGenerationResponseDto);
               } else {
                   return ResponseEntity.badRequest().body(responseDto);
               }
            } else{
                return ResponseEntity.badRequest().body(service.ValidateResponse(user));
            }
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/token")
    public String getToken(@RequestBody AuthRequest authRequest) {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        if (authenticate.isAuthenticated()) {
            return service.generateToken(authRequest.getUsername());
        } else {
            return "invalid access";
        }
    }

    @GetMapping("/validate")
    public String validateToken(@RequestParam("token") String token) {
        service.validateToken(token);
        return "Token is valid";
    }
}