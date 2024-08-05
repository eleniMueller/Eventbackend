package ch.axa.ita.em.eventbackend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.Date;

@Data
@Entity
public class Participant {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long event_id;

    public String title;
    public String description;
    public Date date;
    public String location;
    public String owner_email;
    public int participant_limit;
    public int category_id;
    public String keywords;
    public String image;
}
