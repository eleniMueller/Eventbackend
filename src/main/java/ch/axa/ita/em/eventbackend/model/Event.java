package ch.axa.ita.em.eventbackend.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Data
@Entity
@Table(name = "event")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long event_id;

    @Getter
    @Setter
    public String title;

    @Getter
    @Setter
    public String description;

    @Getter
    @Setter
    public Date date;

    @Getter
    @Setter
    public int duration;

    @Getter
    @Setter
    public String location;

    @Getter
    @Setter
    public String owner_email;

    @Getter
    @Setter
    public int participant_limit;

    @Getter
    @Setter
    public int category_id;

    @Getter
    @Setter
    public String keywords;

    @Getter
    @Setter
    public String image;
}
