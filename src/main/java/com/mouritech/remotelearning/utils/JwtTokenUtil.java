package com.mouritech.remotelearning.utils;

import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.mouritech.remotelearning.security.UserDetailsServiceImpl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenUtil {

	@Value("${jwtconfig.jwtSecret}")
	private String secretKey;

	@Value("${jwtconfig.tokenIssuer}")
	private String issuer;

	@Autowired
	private UserDetailsServiceImpl userDetailsServiceImpl;

	@PostConstruct
	protected void init() {
		secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
	}

	public String getUsernameFromAccessToken(String token) {
		return getClaimFromAccessToken(token, Claims::getSubject);
	}

	public Date getExpirationDateFromAccessToken(String token) {
		return getClaimFromAccessToken(token, Claims::getExpiration);
	}

	public <T> T getClaimFromAccessToken(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = getAllClaimsFromAccessToken(token);
		return claimsResolver.apply(claims);
	}

	private Claims getAllClaimsFromAccessToken(String token) {
		return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
	}

	private Boolean isAccessTokenExpired(String token) {
		final Date expiration = getExpirationDateFromAccessToken(token);
		return expiration.before(new Date());
	}

	public String generateAccessToken(UserDetails userDetails) {
		Map<String, Object> claims = new HashMap<>();
		return doGenerateAccessToken(claims, userDetails.getUsername());
	}

	private String doGenerateAccessToken(Map<String, Object> claims, String subject) {
		return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
			.setExpiration(new Date(System.currentTimeMillis() + 1000 * 300))
			.signWith(SignatureAlgorithm.HS256, secretKey).compact();
	}

	public Boolean validateAccessToken(String token, UserDetails userDetails) {
		final String username = getUsernameFromAccessToken(token);
		return (username.equals(userDetails.getUsername()) && !isAccessTokenExpired(token));
	}
}
