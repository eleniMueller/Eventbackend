package ch.axa.ita.em.eventbackend.participant;

import ch.axa.ita.em.eventbackend.controller.ParticipantController;
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
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class ParticipantControllerTest {

    @Mock
    private ParticipantService participantService;

    @InjectMocks
    private ParticipantController participantController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRegisterParticipantSuccess() {
        Participant participant = new Participant();
        when(participantService.registerParticipant(any(Participant.class))).thenReturn(participant);
        ResponseEntity<String> response = participantController.registerParticipant(participant);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Participant registered successfully", response.getBody());
        verify(participantService, times(1)).registerParticipant(participant);
    }

    @Test
    void testRegisterParticipantConflict() {
        doThrow(new IllegalArgumentException("Participant already attending")).when(participantService).registerParticipant(any(Participant.class));
        ResponseEntity<String> response = participantController.registerParticipant(new Participant());
        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
        assertEquals("Participant already attending", response.getBody());
    }

    @Test
    void testGetParticipantsByEventIdSuccess() {
        Participant participant1 = new Participant();
        Participant participant2 = new Participant();
        when(participantService.getParticipantsByEventId(anyInt())).thenReturn(Arrays.asList(participant1, participant2));
        ResponseEntity<List<Participant>> response = participantController.getParticipantsByEventId(1);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(2, response.getBody().size());
    }

    @Test
    void testGetParticipantsByEventIdNoContent() {
        when(participantService.getParticipantsByEventId(anyInt())).thenReturn(Arrays.asList());
        ResponseEntity<List<Participant>> response = participantController.getParticipantsByEventId(1);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    void testGetParticipantsByEventIdInternalServerError() {
        doThrow(new RuntimeException("DB error")).when(participantService).getParticipantsByEventId(anyInt());
        ResponseEntity<List<Participant>> response = participantController.getParticipantsByEventId(1);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test
    void testDeleteParticipantSuccess() {
        doNothing().when(participantService).deleteParticipant(anyLong());
        ResponseEntity<String> response = participantController.deleteParticipant(1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Participant deleted successfully", response.getBody());
    }

    @Test
    void testDeleteParticipantInternalServerError() {
        doThrow(new RuntimeException("DB error")).when(participantService).deleteParticipant(anyLong());
        ResponseEntity<String> response = participantController.deleteParticipant(1L);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test
    void testUpdateParticipantSuccess() {
        doNothing().when(participantService).updateParticipant(anyLong(), any(Participant.class));
        ResponseEntity<String> response = participantController.updateParticipant(1L, new Participant());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Participant updated successfully", response.getBody());
    }

    @Test
    void testUpdateParticipantInternalServerError() {
        doThrow(new RuntimeException("DB error")).when(participantService).updateParticipant(anyLong(), any(Participant.class));
        ResponseEntity<String> response = participantController.updateParticipant(1L, new Participant());
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }
}