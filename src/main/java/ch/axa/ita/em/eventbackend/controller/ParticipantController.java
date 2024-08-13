package ch.axa.ita.em.eventbackend.controller;

import ch.axa.ita.em.eventbackend.model.Participant;
import ch.axa.ita.em.eventbackend.service.ParticipantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;


@RestController
@RequestMapping("/participants")
@CrossOrigin(origins = "http://localhost:3000")
public class ParticipantController {

    @Autowired
    private ParticipantService participantService;

    private static final Logger logger = Logger.getLogger(EventController.class.getName());

    @PostMapping("/register")
    public ResponseEntity<String> registerParticipant(@RequestBody Participant participant) {
        try {
            participantService.registerParticipant(participant);
            logger.info("Participant registered successfully");
            return ResponseEntity.ok("Participant registered successfully");
        } catch (IllegalArgumentException e) {
            logger.warning("Participant already attending");
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Participant already attending");
        }
    }

    @GetMapping("/event/{eventId}")
    public ResponseEntity<List<Participant>> getParticipantsByEventId(@PathVariable int eventId) {
        try {
            List<Participant> participants = participantService.getParticipantsByEventId(eventId);
            if (participants.isEmpty()) {
                logger.warning("No participants found for event ID " + eventId);
                return ResponseEntity.noContent().build();
            } else {
                logger.info("Participants for event ID " + eventId + " fetched successfully");
                return ResponseEntity.ok(participants);
            }
        } catch (Exception e) {
            logger.severe("Error fetching participants for event ID " + eventId);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/delete/{participantId}")
    public ResponseEntity<String> deleteParticipant(@PathVariable Long participantId) {
        try {
            participantService.deleteParticipant(participantId);
            logger.info("Participant deleted successfully");
            return ResponseEntity.ok("Participant deleted successfully");
        } catch (Exception e) {
            logger.severe("Error deleting participant");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PatchMapping("/update/{participantId}")
    public ResponseEntity<String> updateParticipant(@PathVariable Long participantId, @RequestBody Participant participant) {
        try {
            participantService.updateParticipant(participantId, participant);
            logger.info("Participant updated successfully");
            return ResponseEntity.ok("Participant updated successfully");
        } catch (Exception e) {
            logger.severe("Error updating participant");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
