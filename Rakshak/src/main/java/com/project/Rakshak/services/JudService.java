package com.project.Rakshak.services;

import com.project.Rakshak.entities.Criminal;
import com.project.Rakshak.entities.User;
import com.project.Rakshak.repositories.CriminalRepository;
import com.project.Rakshak.repositories.UserViewRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JudService {

    @Autowired
    private  CriminalRepository criminalRepository;

    @Autowired
    private UserViewRepo userViewRepo;

    @Autowired
    public JudService(UserViewRepo userViewRepo) {
        this.userViewRepo = userViewRepo;
    }

    public Criminal getCriminalById(Long id) {
        return criminalRepository.findById(id).orElse(null);
    }

    public List<Criminal> getAllCriminals() {
        return criminalRepository.findAll();
    }

    public List<User> getAllUser() {
        return userViewRepo.findAll();
    }
}
