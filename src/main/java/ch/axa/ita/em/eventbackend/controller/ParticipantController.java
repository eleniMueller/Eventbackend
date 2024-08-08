package ch.axa.ita.em.eventbackend.controller;

import ch.axa.ita.em.eventbackend.model.Participant;
import ch.axa.ita.em.eventbackend.service.ParticipantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import java.util.logging.Logger;


@RestController
@RequestMapping("/participants")
@CrossOrigin
public class ParticipantController {
    @Autowired
    private ParticipantService participantService;

    @PostMapping("/register")
    public ResponseEntity<String> registerParticipant(@RequestBody Participant participant) {
        try {
            System.out.println(participant);
            participantService.registerParticipant(participant);
            Logger.getLogger("Participant registered successfully");
            return ResponseEntity.ok("Participant registered successfully");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Participant already attending");
        }
    }
}
