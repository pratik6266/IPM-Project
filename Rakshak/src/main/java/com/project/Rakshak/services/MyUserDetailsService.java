//package com.project.Rakshak.services;
//
//import com.project.Rakshak.Model.UserPrincipal;
//import com.project.Rakshak.entities.UserT;
//import com.project.Rakshak.repositories.UserRepo;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//@Service
//public class MyUserDetailsService implements UserDetailsService {
//
//    @Autowired
//    private UserRepo repo;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        UserT user = repo.findByusername(username);
//
//        if(user == null){
//            System.out.println("User not found.");
//            throw new UsernameNotFoundException("User not found.");
//        }
//
//        return new UserPrincipal(user);
//    }
//}
