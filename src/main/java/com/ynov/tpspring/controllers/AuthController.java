package com.ynov.tpspring.controllers;

import com.ynov.tpspring.dto.TokenDTO;
import com.ynov.tpspring.services.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {


    @Autowired
    private AuthService authService;

    @Operation(summary = "Create or get a token for a given user from a given center")
    @RequestMapping(value = "/{username}/access_token", method = {RequestMethod.POST})
    public TokenDTO getUserToken(@RequestParam("center_id") String center_uuid,
                                 @PathVariable("username") String username) {

        return authService.getUserToken(center_uuid, username);
    }


}
