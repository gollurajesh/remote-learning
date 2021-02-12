package com.mouritech.remotelearning.security;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.mouritech.remotelearning.entity.User;
import com.mouritech.remotelearning.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUserEmail(username);
		if (user == null) {
			throw new UsernameNotFoundException("User '" + username + "' not found");
		}
		Set<GrantedAuthority> grantAuth = new HashSet<>();
		grantAuth.add(new SimpleGrantedAuthority(user.getUserRole()));
		return new org.springframework.security.core.userdetails.User(user.getUserEmail(), user.getPassword(),
				grantAuth);
	}
}
