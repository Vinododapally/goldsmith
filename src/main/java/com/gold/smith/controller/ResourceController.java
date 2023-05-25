package com.gold.smith.controller;

import com.gold.smith.config.UserDetailsServiceImpl;
import com.gold.smith.models.AuthenticationRequest;
import com.gold.smith.models.AuthenticationResponse;
import com.gold.smith.util.BaseConfig;
import com.gold.smith.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = BaseConfig.baseUrl)
class ResourceController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtUtil jwtTokenUtil;

	@Autowired
	private UserDetailsServiceImpl userDetailsService;

	@RequestMapping({ "/" })
	public String welcomeNote() {
		return "Hello Welcome to Spring Security module";
	}

	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
		AuthenticationResponse response = new AuthenticationResponse();

		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
			);
		}
		catch (BadCredentialsException e) {
//			throw new Exception("Incorrect username or password please verify once", e);
			response.setErrorMessage("Incorrect username or password please verify once");

			return ResponseEntity.ok(response);
		}

		final UserDetails userDetails = userDetailsService
				.loadUserByUsername(authenticationRequest.getUsername());

		final String jwt = jwtTokenUtil.generateToken(userDetails);
		response.setToken(jwt);
		response.setUsername(userDetails.getUsername());
		response.setPassword(userDetails.getPassword());
		return ResponseEntity.ok(response);
	}

}

