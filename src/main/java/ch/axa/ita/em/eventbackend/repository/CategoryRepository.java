package ch.axa.ita.em.eventbackend.repository;

import ch.axa.ita.em.eventbackend.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
