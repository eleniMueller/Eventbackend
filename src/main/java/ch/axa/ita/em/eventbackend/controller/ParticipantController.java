package ch.axa.ita.em.eventbackend.controller;

import ch.axa.ita.em.eventbackend.model.Participant;
import ch.axa.ita.em.eventbackend.service.ParticipantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;


@RestController
@RequestMapping("/participants")
@CrossOrigin
public class ParticipantController {
    @Autowired
    private ParticipantService participantService;

    @PostMapping("/register")
    public ResponseEntity<Participant> registerParticipant(@RequestParam int eventId, @RequestParam String name, @RequestParam String email) {
        Participant participant = participantService.registerParticipant(eventId, name, email);
        return new ResponseEntity<>(participant, HttpStatus.CREATED);
    }

}
