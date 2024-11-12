package com.project.Rakshak.controllers;

import com.project.Rakshak.entities.Crime;
import com.project.Rakshak.entities.Criminal;
import com.project.Rakshak.entities.PunishmentPeriod;
import com.project.Rakshak.services.CriminalService;
import com.project.Rakshak.services.PunishmentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/criminals")
public class CriminalController {

    @Autowired
    private CriminalService criminalService;

    @Autowired
    private PunishmentService punishmentService;

    // View all criminals
    @GetMapping("/view")
    public String criminalDetails(Model model) {
        List<Criminal> criminals = criminalService.getAllCriminals();
        model.addAttribute("criminals", criminals);
        return "ViewAll";  // Your "ViewAll.html" page
    }

    // Show form to add a new criminal
    @GetMapping("/add")
    public String addCriminalForm(Model model) {
        return "AddCriminal";  // Your "AddCriminal.html" page
    }
    // Handle the POST request to add a new criminal
    @PostMapping("/add")
    public String addCriminal(@Valid @ModelAttribute Criminal criminal,
                              BindingResult result,  // Capture validation errors
                              @RequestParam String[] crimeDescriptions,
                              Model model) {
        // If there are validation errors, return to the form with the errors
        if (result.hasErrors()) {
            model.addAttribute("errors", result.getAllErrors());  // Add errors to the model
            model.addAttribute("criminal", criminal);  // Retain the criminal object
            return "AddCriminal";  // Return to the form with validation errors
        }

        // Process crime descriptions and add them to the criminal
        List<Crime> crimes = new ArrayList<>();
        for (String description : crimeDescriptions) {
            if (description != null && !description.trim().isEmpty()) {  // Validate non-empty descriptions
                Crime crime = new Crime();
                crime.setDescription(description);
                crime.setCriminal(criminal);  // Associate crime with the criminal
                crimes.add(crime);
            }
        }

        criminal.setCrimes(crimes);

        // Save the criminal using the service layer
        criminalService.addCriminal(criminal);

        // Redirect to view criminals after successful addition
        return "redirect:/criminals/view";
    }

    // Show form to update a criminal
    @GetMapping("/update/{id}")
    public String updateCriminalForm(@PathVariable Long id, Model model) {
        Optional<Criminal> criminalOpt = criminalService.searchCriminal(id);
        if (criminalOpt.isEmpty()) {
            return "redirect:/criminals/view";
        }

        model.addAttribute("criminal", criminalOpt.get());
        return "UpdateCriminal";  // Your "UpdateCriminal.html" page
    }

    // Update the criminal
    @PostMapping("/update/{id}")
    public String updateCriminal(@PathVariable Long id, @RequestParam String name, @RequestParam String[] crimeDescriptions, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("errors", result.getAllErrors());
            return "UpdateCriminal";  // Return to form with validation errors
        }

        Optional<Criminal> criminalOpt = criminalService.searchCriminal(id);
        if (criminalOpt.isEmpty()) {
            return "redirect:/criminals/view";
        }

        Criminal criminal = criminalOpt.get();
        criminal.setName(name);

        // Update the list of crimes
        List<Crime> crimes = new ArrayList<>();
        for (String description : crimeDescriptions) {
            Crime crime = new Crime();
            crime.setDescription(description);
            crime.setCriminal(criminal);
            crimes.add(crime);
        }

        criminal.setCrimes(crimes);
        criminalService.updateCriminal(criminal);

        return "redirect:/criminals/view";  // Redirect to view criminals
    }

    // View all punishments
    @GetMapping("/punishments")
    public String viewPunishments(Model model) {
        List<PunishmentPeriod> punishments = punishmentService.getAllPunishments();
        model.addAttribute("punishments", punishments);
        return "PunishmentPeriods";  // Return to 'PunishmentPeriods' HTML page
    }

    // Edit punishment for a criminal
    @GetMapping("/punishments/edit/{id}")
    public String editPunishment(@PathVariable Long id, Model model) {
        Optional<Criminal> criminalOpt = criminalService.searchCriminal(id);
        if (criminalOpt.isEmpty()) {
            return "redirect:/criminals/punishments";
        }

        Criminal criminal = criminalOpt.get();
        Optional<PunishmentPeriod> punishment = punishmentService.getPunishmentByCriminal(criminal);

        model.addAttribute("criminal", criminal);
        model.addAttribute("punishment", punishment.orElse(new PunishmentPeriod()));
        return "EditPunishment";  // Your "EditPunishment.html" page
    }

    @PostMapping("/punishments/edit/{id}")
    public String updatePunishment(
            @PathVariable Long id,
            @RequestParam String punishmentPeriod,
            @RequestParam String startDate,
            @RequestParam(required = false) String endDate) {

        Optional<Criminal> criminalOpt = criminalService.searchCriminal(id);
        if (criminalOpt.isEmpty()) {
            return "redirect:/criminals/punishments";
        }

        Criminal criminal = criminalOpt.get();
        Optional<PunishmentPeriod> punishmentOpt = punishmentService.getPunishmentByCriminal(criminal);
        PunishmentPeriod punishment = punishmentOpt.orElse(new PunishmentPeriod());  // If no punishment exists, create a new one

        // Set the updated punishment details
        punishment.setPunishmentPeriod(punishmentPeriod);
        punishment.setStartDate(startDate);
        if (endDate != null && !endDate.isEmpty()) {
            punishment.setEndDate(endDate);
        }

        // Link the punishment to the criminal
        punishment.setCriminal(criminal);

        // Save the punishment (pass the PunishmentPeriod, not an Optional)
        punishmentService.savePunishment(punishment);

        return "redirect:/criminals/punishments";
    }

    @GetMapping("/transfer")
    public String showTransferForm(Model model) {
//        // Add a new CriminalTransferDTO to the model to populate the form
//        model.addAttribute("criminalTransferDTO", new CriminalTransferDTO());
//        return "criminal-transfer";  // Return the name of the view (criminal-transfer.html)
        return "CriminalTransfer";
    }
//
//    // Handle the criminal transfer (POST request)
//    @PostMapping("/transfer")
//    public String transferCriminal(@ModelAttribute("criminalTransferDTO") CriminalTransferDTO, Model) {
//        // Validate the incoming transfer DTO
//        if (criminalTransferDTO == null || criminalTransferDTO.getCriminalId() == null) {
//            model.addAttribute("error", "Invalid data for transfer.");
//            return "criminal-transfer";
//        }
//
//        // Call the service to handle the transfer
//        try {
//            boolean success = criminalService.transferCriminal(criminalTransferDTO);
//            if (success) {
//                model.addAttribute("message", "Criminal transferred successfully!");
//            } else {
//                model.addAttribute("error", "Transfer failed. Please try again.");
//            }
//        } catch (Exception e) {
//            model.addAttribute("error", "An error occurred during the transfer: " + e.getMessage());
//        }
//
//        // Return back to the transfer page
//        return "criminal-transfer";
//    }
//
    @GetMapping("/search")
    public String searchCriminal(Model model)
    {
        return "SearchCriminal";
    }

    @GetMapping("/update")
    public String updateCriminalForm(Model model)
    {
        return "redirect:/criminals/view";
    }

    @GetMapping("/meetings")
    public String meetingRecords(Model model)
    {
        return "MeetingRecords";
    }

    @GetMapping("/assignedWorks")
    public String assignWork(Model model)
    {
        return "AssignedWorks";
    }

    @GetMapping("/location")
    public String locationOfCriminal(Model model)
    {
        return "Location";
    }

    @GetMapping("/health")
    public String healthOfCriminal(Model model)
    {
        return "Health";
    }

}
