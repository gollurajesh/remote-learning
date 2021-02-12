package com.mouritech.remotelearning.rest;

import java.util.List;
import java.util.Objects;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

import com.mouritech.remotelearning.entity.AuthenticationResponse;
import com.mouritech.remotelearning.entity.User;
import com.mouritech.remotelearning.exceptions.BadRequestException;
import com.mouritech.remotelearning.model.UserDTO;
import com.mouritech.remotelearning.repository.UserRepository;
import com.mouritech.remotelearning.service.UserService;
import com.mouritech.remotelearning.utils.JwtTokenUtil;
import com.mouritech.remotelearning.utils.ResponseBody;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserResource {

	@Autowired
	private UserService userService;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@PostMapping(value = "/register")
	public ResponseEntity<Object> save(@RequestBody UserDTO userDTO, HttpServletResponse response)
		throws HttpClientErrorException, BadRequestException {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		String encodedPassword = bCryptPasswordEncoder.encode(userDTO.getPassword());
		userDTO.setPassword(encodedPassword);
		if (userDTO.getUserId() != 0) {
			throw new IllegalArgumentException("New user Can not have Id and user cannot be created");
		}
		if (userService.userExistsByUserEmail(userDTO)) {
			return ResponseEntity.status(400)
				.body(new ResponseBody(HttpStatus.BAD_REQUEST.value(), "User already exists"));
		}
		if (userDTO.getUserName() == null) {
			return ResponseEntity.status(400).body(new ResponseBody(HttpStatus.BAD_REQUEST.value(), "User not found"));
		}
		return new ResponseEntity<>(userService.saveUser(userDTO), HttpStatus.OK);
	}

	@PostMapping(value = "/login")
	public ResponseEntity<Object> createAuthenticationToken(@Valid @RequestBody UserDTO user) throws Exception {
		HttpHeaders responseHeaders = new HttpHeaders();
		User user1 = userRepository.findByUserEmail(user.getUserEmail());
		try {
			authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(user.getUserEmail(), user.getPassword()));
		} catch (BadCredentialsException e) {
			return ResponseEntity.badRequest()
				.body(new ResponseBody(HttpStatus.BAD_REQUEST.value(), "user does not exists"));
		}
		UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUserEmail());
		if (userDetails == null) {
			return ResponseEntity.badRequest()
				.body(new ResponseBody(HttpStatus.BAD_REQUEST.value(), "user does not exists"));
		}
		final String jwt = jwtTokenUtil.generateAccessToken(userDetails);
		responseHeaders.set("Jwt", jwt);
		AuthenticationResponse response = new AuthenticationResponse(jwt);
		response.setUserId(user1.getUserId());
		response.setUserEmail(user1.getUserEmail());
		response.setUserName(user1.getUserName());
		response.setPhonenumber(user1.getPhonenumber());
		response.setQualification(user1.getQualification());
		response.setProfileImg(user1.getProfileImg());
		return ResponseEntity.ok(response);
	}

	@PostMapping("/logout")
	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
	public ResponseEntity<Object> logoutUser(@RequestParam(value = "userId", required = false) String userId) {
		UserDTO userModel = userService.findOneUser(Integer.parseInt(userId));
		if (Objects.isNull(userModel)) {
			return ResponseEntity.badRequest()
				.body(new ResponseBody(HttpStatus.BAD_REQUEST.value(), "user does not exists"));
		} else {
			return ResponseEntity.ok().body(new ResponseBody(HttpStatus.OK.value(), "User Logged Out successfully"));
		}
	}

	@GetMapping(value = "/userDetails")
	@Transactional(readOnly = true)
	public ResponseEntity<List<UserDTO>> getAll() {
		List<UserDTO> userDto = userService.getAllUsers();
		return new ResponseEntity<>(userDto, HttpStatus.OK);
	}

	@PutMapping(value = "/updatePassword/{userId}")
	public ResponseEntity<ResponseBody> updatePassword(@PathVariable Integer userId, @RequestBody UserDTO userDTO) {
		String updatedPassword = passwordEncoder.encode(userDTO.getPassword());
		userService.updatePassword(updatedPassword, userId);
		return ResponseEntity.ok().body(new ResponseBody(HttpStatus.OK.value(), "Updateed Successfully"));
	}

	@PutMapping(value = "/userUpdate")
	public ResponseEntity<UserDTO> update(@RequestBody UserDTO userDTO) {
		if (userDTO.getUserId() == 0) {
			throw new IllegalArgumentException("User must have an id");
		}
		UserDTO result = userService.saveUser(userDTO);
		return ResponseEntity.ok().body(result);
	}

	@GetMapping(value = "/usergetId/{id}")
	public ResponseEntity<UserDTO> get(@PathVariable int id) {
		UserDTO result = userService.findOneUser(id);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@GetMapping(value = "/version")
	public ResponseEntity<String> getVersion() {
		return new ResponseEntity<>("Version 0.1", HttpStatus.OK);
	}
}
