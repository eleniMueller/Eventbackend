package ch.axa.ita.em.eventbackend.service;

import ch.axa.ita.em.eventbackend.model.Participant;
import ch.axa.ita.em.eventbackend.repository.ParticipantRepository;
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

public class ParticipantServiceTest {

    @Mock
    private ParticipantRepository participantRepository;

    @InjectMocks
    private ParticipantService participantService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testRegisterParticipant() {
        Participant participant = new Participant();
        when(participantRepository.save(participant)).thenReturn(participant);

        Participant result = participantService.registerParticipant(participant);

        assertEquals(participant, result);
        verify(participantRepository, times(1)).save(participant);
    }

    @Test
    public void testGetParticipantsByEventId() {
        int eventId = 1;
        Participant participant1 = new Participant();
        participant1.setEventId(eventId);
        Participant participant2 = new Participant();
        participant2.setEventId(eventId);

        when(participantRepository.findByEventId(eventId)).thenReturn(Arrays.asList(participant1, participant2));

        List<Participant> participants = participantService.getParticipantsByEventId(eventId);

        assertEquals(2, participants.size());
        assertEquals(eventId, participants.get(0).getEventId());
        assertEquals(eventId, participants.get(1).getEventId());
    }

    @Test
    public void testDeleteParticipant() {
        Long participantId = 1L;
        doNothing().when(participantRepository).deleteById(participantId);

        participantService.deleteParticipant(participantId);

        verify(participantRepository, times(1)).deleteById(participantId);
    }


    @Test
    public void testUpdateParticipant() {
        Long participantId = 1L;
        Participant existingParticipant = new Participant();
        existingParticipant.setParticipant_id(participantId);
        Participant newParticipant = new Participant();
        newParticipant.setEventId(2);

        when(participantRepository.findById(participantId)).thenReturn(Optional.of(existingParticipant));
        when(participantRepository.save(newParticipant)).thenReturn(newParticipant);

        participantService.updateParticipant(participantId, newParticipant);

        assertEquals(participantId, newParticipant.getParticipant_id());
        verify(participantRepository, times(1)).save(newParticipant);
    }

    @Test
    public void testUpdateParticipant_NotFound() {
        Long participantId = 1L;
        Participant newParticipant = new Participant();

        when(participantRepository.findById(participantId)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> {
            participantService.updateParticipant(participantId, newParticipant);
        });
    }
}