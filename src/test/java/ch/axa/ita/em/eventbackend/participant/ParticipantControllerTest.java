//package ch.axa.ita.em.eventbackend.participant;
//
//import ch.axa.ita.em.eventbackend.controller.ParticipantController;
//import ch.axa.ita.em.eventbackend.model.Participant;
//import ch.axa.ita.em.eventbackend.service.ParticipantService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.http.ResponseEntity;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.when;
//
//public class ParticipantControllerTest {
//
//    @Mock
//    private ParticipantService participantService;
//
//    @InjectMocks
//    private ParticipantController participantController;
//
//    @BeforeEach
//    public void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    public void testRegisterParticipant() {
//        Participant participant = new Participant();
//        participant.setName("John Doe");
//        participant.setEventId(1);
//        participant.setEmail("john@doe.ch");
//
//        when(participantService.registerParticipant(any(Participant.class))).thenReturn(participant);
//
//        ResponseEntity<Participant> response = participantController.registerParticipant(participant);
//
//        assertEquals(200, response.getStatusCodeValue());
//        assertEquals("John Doe", response.getBody().getName());
//
//}