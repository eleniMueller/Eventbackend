package ch.axa.ita.em.eventbackend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    public int event_id;
    public String name;
    public String email;
    public int rating;
    public LocalDateTime registration_time;
    public boolean attendance_status;


}
