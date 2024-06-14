package com.pokemonreview.api.dto;

import lombok.Data;

@Data
public class RegisterRequestDto {
    private String firstName;
    private String lastName;
    private String address;
    private String mobile;
    private String email;
    private String username;
    private String password;

}
