package ch.axa.ita.em.eventbackend.service;

import ch.axa.ita.em.eventbackend.model.Participant;
import ch.axa.ita.em.eventbackend.repository.ParticipantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ParticipantService {

    @Autowired
    private ParticipantRepository participantRepository;

    public Participant registerParticipant(Participant participant) {
        return participantRepository.save(participant);
    }
}
