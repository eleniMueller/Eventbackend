package ch.axa.ita.em.eventbackend.service;

import ch.axa.ita.em.eventbackend.model.Participant;
import ch.axa.ita.em.eventbackend.repository.ParticipantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ParticipantService {

    @Autowired
    private ParticipantRepository participantRepository;

    public Participant registerParticipant(Participant participant) {
        return participantRepository.save(participant);
    }

    public List<Participant> getParticipantsByEventId(int eventId) {
        return participantRepository.findByEventId(eventId);
    }

    public void deleteParticipant(Long participantId) {
        participantRepository.deleteById(participantId);
    }

    public Participant updateParticipant(Long participantId, Participant newParticipant) {
        try {
            Participant existingParticipant = participantRepository.findById(participantId)
                    .orElseThrow(() -> new EmptyResultDataAccessException("Participant with ID " + participantId + " does not exist.", 1));
            newParticipant.setParticipant_id(existingParticipant.getParticipant_id());
            return participantRepository.save(newParticipant);
        } catch (EmptyResultDataAccessException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }
}
