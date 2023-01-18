package com.spring.jwt;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtInMemoryUserDetailsService implements UserDetailsService {

  static List<JwtUserDetails> inMemoryUserList = new ArrayList<>();

  static {
	  
    inMemoryUserList.add(new JwtUserDetails(1L, "in28minutes",
    		"$2a$04$Ix7LW5TisQjuk.lxXMoKHunR/vVfv7DPJ4BK8hJtNPK8HMKsb3b/C", "ROLE_USER_1"));
     // above password is encrypted - pass  password as Ramesh while invoking API through postman. disable Encode while invoking from mobile or web 
    inMemoryUserList.add(new JwtUserDetails(2L, "Ramesh",
            "$2a$10$fSD3/usJSBBHXcHNdj/pa.O4XXFb9T9KMcyW.nXIKCz3DZf1PjcVG", "ROLE_USER_2"));
  }
  
  //$2a$10$fSD3/usJSBBHXcHNdj/pa.O4XXFb9T9KMcyW.nXIKCz3DZf1PjcVG

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Optional<JwtUserDetails> findFirst = inMemoryUserList.stream()
        .filter(user -> user.getUsername().equals(username)).findFirst();
    
    System.out.println("@Ramesh current user " + username);

    if (!findFirst.isPresent()) {
        System.out.println("@Ramesh current user not present " );

      throw new UsernameNotFoundException(String.format("USER_NOT_FOUND '%s'.", username));
    }

    return findFirst.get();
  }

 }


