package ch.axa.ita.em.eventbackend.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@Entity
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long participant_id;

    public int event_id;
    public String name;
    public String email;
    public int rating;
    public LocalDateTime registration_time;
    public boolean attendance_status;


}
