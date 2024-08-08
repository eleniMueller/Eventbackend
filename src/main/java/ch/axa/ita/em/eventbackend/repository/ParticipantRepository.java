package ch.axa.ita.em.eventbackend.repository;

import ch.axa.ita.em.eventbackend.model.Participant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParticipantRepository extends JpaRepository<Participant, Long> {
}

