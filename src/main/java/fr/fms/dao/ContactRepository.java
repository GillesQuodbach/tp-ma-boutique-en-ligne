package fr.fms.dao;

import fr.fms.entities.Article;
import fr.fms.entities.Contact;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContactRepository extends JpaRepository<Contact, Long> {


    Page<Contact> findByNameContainingIgnoreCase(String name, Pageable pageable);

    Page<Contact> findByCategoryName(String category, Pageable pageable);

    Page<Contact> findByNameContainingIgnoreCaseAndFirstNameContainingIgnoreCase(String name, String firstName, Pageable pageable);

    Page<Contact> findByName(String name, Pageable pageable);

    List<Contact> findByCategoryName(String categoryName);

    void deleteById(Long id);
}
