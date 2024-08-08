//package ch.axa.ita.em.eventbackend.participant;
//
//import ch.axa.ita.em.eventbackend.model.Participant;
//import ch.axa.ita.em.eventbackend.repository.ParticipantRepository;
//import ch.axa.ita.em.eventbackend.service.ParticipantService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.when;
//
//public class ParticipantServiceTest {
//
//    @Mock
//    private ParticipantRepository participantRepository;
//
//    @InjectMocks
//    private ParticipantService participantService;
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
//        participant.setEmail("John@doe.ch");
//
//        when(participantRepository.save(any(Participant.class))).thenReturn(participant);
//
//        Participant result = participantService.registerParticipant(participant);
//
//        assertEquals("John Doe", result.getName());
//    }
//}