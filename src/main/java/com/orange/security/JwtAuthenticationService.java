package com.orange.security;

import java.util.Base64;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.orange.services.UserDetailServiceImpl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtAuthenticationService {
	
	private static final String SECRET_KEY = Base64.getEncoder().encodeToString("MySecrretKey".getBytes());
	
	private static final String PREFIX="Bearer";
	
	private static final String EMPTY = "";
	
	private static final long EXPIRATION_TIME = 24*60*60*1000;
	
	private static final String AUTHORIZATION = "Authorization";
	
	@Autowired
	private UserDetailServiceImpl userDetailsService;
	
	public String createToken(String userName, List<String> roles) {
		
		Claims claims = Jwts.claims().setSubject(userName);
		claims.put("roles", roles);
		Date now = new Date();
		Date validity = new Date(now.getTime() + EXPIRATION_TIME);
		return Jwts.builder()
				.setClaims(claims)
				.setIssuedAt(now)
				.setExpiration(validity)
				.signWith(SignatureAlgorithm.HS256, SECRET_KEY)
				.compact();
	}
	
	public Authentication getAuthentication(HttpServletRequest request) {
		String token = resolveToken(request);
		if (token != null && validateToken(token)) {
			String userName = getUserName(token);
			if (userName != null) {
				UserDetails userDetails = userDetailsService.loadUserByUsername(userName);
				return new UsernamePasswordAuthenticationToken(userDetails,null, userDetails.getAuthorities());
			}
		}
		return null;
	}
	
	private String getUserName(String token) {
		return Jwts.parser()
				.setSigningKey(SECRET_KEY)
				.parseClaimsJws(token)
				.getBody().getSubject();
	}
	
	private String resolveToken(HttpServletRequest req) {
		String bearerToken = req.getHeader(AUTHORIZATION);
		if (bearerToken != null && bearerToken.startsWith(PREFIX)) {
			return bearerToken.replace(PREFIX, EMPTY).trim();
		}
		return null;
	}
	
	private boolean validateToken(String token) {
		try {
			Jws<Claims> claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
			
			if (claims.getBody().getExpiration().before(new Date())) {
				return false;
			}
			return true;
		}
		catch(JwtException | IllegalArgumentException e) {
			throw new IllegalArgumentException("Expired or invalid JWT token");
		}
	}
	
	
	
	
}
