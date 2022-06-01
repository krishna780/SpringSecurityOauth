package com.spring.springSecurity.service;

import com.spring.springSecurity.model.Role;
import com.spring.springSecurity.model.UserDetails;
import com.spring.springSecurity.repository.RoleRepo;
import com.spring.springSecurity.repository.UserRepo;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@AllArgsConstructor
@Transactional
@Log4j2
public class UserServiceImplement implements UserService, UserDetailsService {
   private final UserRepo userRepo;
   private final RoleRepo roleRepo;
   private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails saveUser(UserDetails user) {
     user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepo.save(user);
    }

    @Override
    public Role saveRole(@Valid Role role) {
        return roleRepo.save(role);
    }

    @Override
    public void addRoleToUser(String userName, String roleName) {
         UserDetails user= userRepo.findByUserName(userName);
         Role role= roleRepo.findByName(roleName);
         user.getRoles().add(role);
    }

    @Override
    public UserDetails getUser(String userName) {
        return userRepo.findByUserName(userName);
    }

    @Override
    public List<UserDetails> getUsers() {
        return userRepo.findAll();
    }

    @Override
    public org.springframework.security.core.userdetails.UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        UserDetails user= userRepo.findByUserName(userName);
        if(ObjectUtils.isEmpty(user)){
            log.info("user not found");
        }
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(s-> authorities.add(new SimpleGrantedAuthority(s.getName())));
        return new User(user.getUserName(),user.getPassword(), authorities);
            }
        }
