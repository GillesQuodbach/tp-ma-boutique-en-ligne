package fr.fms.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

/**
 * article category class definition
 *
 * @author Gilles
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Category implements Serializable {
    /**
     * category id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * category name
     */
    private String name;
    /**
     * category description
     */
    private String description;

    /**
     * article in a category
     */
    @OneToMany(mappedBy = "category")
    @ToString.Exclude
    private Collection<Contact> contact;

    // Test constructor
    public Category(String test) {
    }
}

