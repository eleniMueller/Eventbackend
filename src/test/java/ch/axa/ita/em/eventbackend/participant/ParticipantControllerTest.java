package ch.axa.ita.em.eventbackend.controller;

import ch.axa.ita.em.eventbackend.model.Participant;
import ch.axa.ita.em.eventbackend.service.ParticipantService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ParticipantControllerTest {

    @Mock
    private ParticipantService participantService;

    @InjectMocks
    private ParticipantController participantController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testRegisterParticipant() {
        Participant participant = new Participant();
        when(participantService.registerParticipant(participant)).thenReturn(participant);

        ResponseEntity<String> response = participantController.registerParticipant(participant);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Participant registered successfully", response.getBody());
    }

    @Test
    public void testRegisterParticipant_Conflict() {
        Participant participant = new Participant();
        doThrow(new IllegalArgumentException("Participant already attending")).when(participantService).registerParticipant(participant);

        ResponseEntity<String> response = participantController.registerParticipant(participant);

        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
        assertEquals("Participant already attending", response.getBody());
    }

    @Test
    public void testGetParticipantsByEventId() {
        int eventId = 1;
        Participant participant1 = new Participant();
        participant1.setEventId(eventId);
        Participant participant2 = new Participant();
        participant2.setEventId(eventId);

        when(participantService.getParticipantsByEventId(eventId)).thenReturn(Arrays.asList(participant1, participant2));

        ResponseEntity<List<Participant>> response = participantController.getParticipantsByEventId(eventId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, response.getBody().size());
    }

    @Test
    public void testGetParticipantsByEventId_NoParticipants() {
        int eventId = 1;

        when(participantService.getParticipantsByEventId(eventId)).thenReturn(Arrays.asList());

        ResponseEntity<List<Participant>> response = participantController.getParticipantsByEventId(eventId);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    public void testGetParticipantsByEventId_Error() {
        int eventId = 1;
        when(participantService.getParticipantsByEventId(eventId)).thenThrow(new RuntimeException("Error fetching participants"));

        ResponseEntity<List<Participant>> response = participantController.getParticipantsByEventId(eventId);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test
    public void testDeleteParticipant() {
        Long participantId = 1L;
        doNothing().when(participantService).deleteParticipant(participantId);

        ResponseEntity<String> response = participantController.deleteParticipant(participantId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Participant deleted successfully", response.getBody());
    }

    @Test
    public void testDeleteParticipant_Error() {
        Long participantId = 1L;
        doThrow(new RuntimeException("Error deleting participant")).when(participantService).deleteParticipant(participantId);

        ResponseEntity<String> response = participantController.deleteParticipant(participantId);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test
    public void testUpdateParticipant() {
        Long participantId = 1L;
        Participant participant = new Participant();
        doNothing().when(participantService).updateParticipant(participantId, participant);

        ResponseEntity<String> response = participantController.updateParticipant(participantId, participant);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Participant updated successfully", response.getBody());
    }

    @Test
    public void testUpdateParticipant_Error() {
        Long participantId = 1L;
        Participant participant = new Participant();
        doThrow(new RuntimeException("Error updating participant")).when(participantService).updateParticipant(participantId, participant);

        ResponseEntity<String> response = participantController.updateParticipant(participantId, participant);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }
}