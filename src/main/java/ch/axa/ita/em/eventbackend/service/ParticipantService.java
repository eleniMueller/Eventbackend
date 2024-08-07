package ch.axa.ita.em.eventbackend.service;

import ch.axa.ita.em.eventbackend.model.Participant;
import ch.axa.ita.em.eventbackend.repository.ParticipantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ParticipantService {

    @Autowired
    private ParticipantRepository participantRepository;

    public Participant registerParticipant(int eventId, String name, String email) {
        Participant newParticipant = new Participant();

        newParticipant.setEvent_id(eventId);
        newParticipant.setName(name);
        newParticipant.setEmail(email);
        newParticipant.setRating(0);
        newParticipant.setRegistration_time(LocalDateTime.now());
        newParticipant.setAttendance_status(true);

        return participantRepository.save(newParticipant);
    }
}
