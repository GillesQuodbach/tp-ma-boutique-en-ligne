package fr.fms.dao;

import fr.fms.entities.Contact;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<Contact, Long> {

    Page<Contact> findByCategoryName(String category, Pageable pageable);

    Page<Contact> findByNameContainingIgnoreCaseAndFirstNameContainingIgnoreCase(String name, String firstName, Pageable pageable);

    void deleteById(Long id);
}
