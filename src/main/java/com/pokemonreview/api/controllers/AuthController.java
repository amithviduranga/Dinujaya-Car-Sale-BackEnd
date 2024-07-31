package com.pokemonreview.api.controllers;

import com.pokemonreview.api.dto.AuthResponseDTO;
import com.pokemonreview.api.dto.LoginDto;
import com.pokemonreview.api.dto.RegisterRequestDto;
import com.pokemonreview.api.entity.Role;
import com.pokemonreview.api.entity.UserEntity;
import com.pokemonreview.api.repository.RoleRepository;
import com.pokemonreview.api.repository.UserRepository;
import com.pokemonreview.api.security.JWTGenerator;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.rmi.NoSuchObjectException;
import java.util.Collections;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private AuthenticationManager authenticationManager;
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    private JWTGenerator jwtGenerator;
    private Exception RuntimeException;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, UserRepository userRepository,
                          RoleRepository roleRepository, PasswordEncoder passwordEncoder, JWTGenerator jwtGenerator) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtGenerator = jwtGenerator;
    }

    @PostMapping("/createRole")
    public ResponseEntity<Role> createRole(@RequestBody Role requestDTO){

        Role role = new Role();

        role.setName(requestDTO.getName());
        Role createdRole = roleRepository.save(role);

        return new ResponseEntity<>(createdRole, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody LoginDto loginDto){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                loginDto.getUsername(),
                loginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtGenerator.generateToken(authentication);
        String role = authentication.getAuthorities().toString();
        String result = role.substring(1, role.length() - 1);
        return new ResponseEntity<>(new AuthResponseDTO(token,result), HttpStatus.OK);
    }

    @PostMapping("register/{roleId}")
    public ResponseEntity<String> register(@RequestBody RegisterRequestDto registerDto , @PathVariable(required = true) int roleId) {
        if (userRepository.existsByUsername(registerDto.getUsername())) {
            return new ResponseEntity<>("Username is taken!", HttpStatus.BAD_REQUEST);
        }
        UserEntity user = new UserEntity();
        user.setUsername(registerDto.getUsername());
        user.setPassword(passwordEncoder.encode((registerDto.getPassword())));
        user.setAddress(registerDto.getAddress());
        user.setEmail(registerDto.getEmail());
        user.setFirstName(registerDto.getFirstName());
        user.setLastName(registerDto.getLastName());
        user.setMobile(registerDto.getMobile());

        Role roles = roleRepository.findById(roleId).get();
        user.setRoles(Collections.singletonList(roles));

        userRepository.save(user);

        return new ResponseEntity<>("User registered success!", HttpStatus.OK);
    }

    @GetMapping("userDetails/{userName}")
    public ResponseEntity<?> getUserDetailsByUserName(@PathVariable("userName") String userName){
        UserEntity user = null;
        try{
            user = userRepository.findByUsername(userName).get();

        }catch (Exception ex){

          throw  ex;
        }
      return  ResponseEntity.ok(user);
    }

}
