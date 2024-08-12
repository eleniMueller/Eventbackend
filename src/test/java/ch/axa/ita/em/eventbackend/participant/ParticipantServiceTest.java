package ch.axa.ita.em.eventbackend.participant;

import ch.axa.ita.em.eventbackend.model.Participant;
import ch.axa.ita.em.eventbackend.repository.ParticipantRepository;
import ch.axa.ita.em.eventbackend.service.ParticipantService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ParticipantServiceTest {

    @Mock
    private ParticipantRepository participantRepository;

    @InjectMocks
    private ParticipantService participantService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRegisterParticipantSuccess() {
        Participant participant = new Participant();
        when(participantRepository.save(any(Participant.class))).thenReturn(participant);
        Participant savedParticipant = participantService.registerParticipant(participant);
        assertNotNull(savedParticipant);
        verify(participantRepository, times(1)).save(participant);
    }

    @Test
    void testGetParticipantsByEventIdSuccess() {
        Participant participant1 = new Participant();
        Participant participant2 = new Participant();
        when(participantRepository.findByEventId(anyInt())).thenReturn(Arrays.asList(participant1, participant2));
        List<Participant> participants = participantService.getParticipantsByEventId(1);
        assertNotNull(participants);
        assertEquals(2, participants.size());
    }

    @Test
    void testGetParticipantsByEventIdEmptyList() {
        when(participantRepository.findByEventId(anyInt())).thenReturn(Arrays.asList());
        List<Participant> participants = participantService.getParticipantsByEventId(1);
        assertNotNull(participants);
        assertTrue(participants.isEmpty());
    }

    @Test
    void testDeleteParticipantSuccess() {
        doNothing().when(participantRepository).deleteById(anyLong());
        assertDoesNotThrow(() -> participantService.deleteParticipant(1L));
        verify(participantRepository, times(1)).deleteById(1L);
    }

    @Test
    void testDeleteParticipantThrowsException() {
        doThrow(new RuntimeException("DB error")).when(participantRepository).deleteById(anyLong());
        assertThrows(RuntimeException.class, () -> participantService.deleteParticipant(1L));
    }

    @Test
    void testUpdateParticipantSuccess() {
        Participant existingParticipant = new Participant();
        when(participantRepository.findById(anyLong())).thenReturn(Optional.of(existingParticipant));
        when(participantRepository.save(any(Participant.class))).thenReturn(existingParticipant);
        assertDoesNotThrow(() -> participantService.updateParticipant(1L, new Participant()));
        verify(participantRepository, times(1)).findById(1L);
        verify(participantRepository, times(1)).save(any(Participant.class));
    }

    @Test
    void testUpdateParticipantThrowsException() {
        when(participantRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(IllegalArgumentException.class, () -> participantService.updateParticipant(1L, new Participant()));
    }
}