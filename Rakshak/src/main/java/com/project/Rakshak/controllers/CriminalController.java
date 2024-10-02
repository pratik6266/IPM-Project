package com.project.Rakshak.controllers;

import com.project.Rakshak.entities.Criminal;
import com.project.Rakshak.entities.Crime;
import com.project.Rakshak.services.CriminalService;
import com.project.Rakshak.utils.ImageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Controller
@RequestMapping("/criminals")
public class CriminalController {

    @Autowired
    private CriminalService criminalService;

    @Autowired
    private ImageUtils imageUtils;

    @GetMapping("/view")
    public String criminalDetails(Model model) {
        List<Criminal> criminals = criminalService.getAllCriminals();
        for (Criminal criminal : criminals) {
            System.out.println("Criminal ID: " + criminal.getCriminalId());
            System.out.println("Left Photo Size: " + (criminal.getLeftPhoto() != null ? criminal.getLeftPhoto().length : 0));
            System.out.println("Right Photo Size: " + (criminal.getRightPhoto() != null ? criminal.getRightPhoto().length : 0));
            System.out.println("Front Photo Size: " + (criminal.getFrontPhoto() != null ? criminal.getFrontPhoto().length : 0));
        }
        model.addAttribute("criminals", criminals);
        return "ViewAll";
    }


    @GetMapping("/add")
    public String addCriminalForm(Model model) {
        return "AddCriminal";
    }

    @PostMapping("/add")
    public String addCriminal(
            String name,
            MultipartFile leftPhoto,
            MultipartFile rightPhoto,
            MultipartFile frontPhoto,
            MultipartFile retinaScan,
            MultipartFile fingerprintScan,
            String[] crimeDescriptions,
            String[] placeOfCrimes,
            String[] dateOfCrimes,
            String[] timeOfCrimes,
            String[] victimDetails) throws IOException {

        Criminal criminal = new Criminal();
        criminal.setName(name);

        if (!leftPhoto.isEmpty()) {
            byte[] leftPhotoBytes = leftPhoto.getBytes();
            System.out.println("Left Photo Size: " + leftPhotoBytes.length);
            criminal.setLeftPhoto(leftPhotoBytes);
        }

        if (!rightPhoto.isEmpty()) {
            criminal.setRightPhoto(rightPhoto.getBytes());
        }
        if (!frontPhoto.isEmpty()) {
            criminal.setFrontPhoto(frontPhoto.getBytes());
        }
        if (!retinaScan.isEmpty()) {
            criminal.setRetinaScan(retinaScan.getBytes());
        }
        if (!fingerprintScan.isEmpty()) {
            criminal.setFingerprintScan(fingerprintScan.getBytes());
        }

        List<Crime> crimes = new ArrayList<>();
        for (int i = 0; i < crimeDescriptions.length; i++) {
            Crime crime = new Crime();
            crime.setDescription(crimeDescriptions[i]);
            crime.setLocation(placeOfCrimes[i]); // Make sure to set location
            crime.setDate(dateOfCrimes[i]);
            crime.setTimeOfCrime(timeOfCrimes[i]);
            crime.setVictimDetails(victimDetails[i]);
            crimes.add(crime);
            crime.setCriminal(criminal); // Set the reference to the criminal
        }

        criminal.setCrimes(crimes);
        criminalService.addCriminal(criminal);

        return "redirect:/criminals/view";
    }


}