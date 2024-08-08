package ch.axa.ita.em.eventbackend.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "participant")
@AllArgsConstructor
@NoArgsConstructor
public class Participant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long participant_id;

    @Getter
    @Setter
    public int eventId;

    @Getter
    @Setter
    public String name;

    @Getter
    @Setter
    public String email;

    @Getter
    @Setter
    public int rating;

    @Getter
    @Setter
    public LocalDateTime registration_time;

    @Getter
    @Setter
    public boolean attendance_status;


}
