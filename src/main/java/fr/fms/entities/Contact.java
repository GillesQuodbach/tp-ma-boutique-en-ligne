package fr.fms.entities;

import groovyjarjarantlr4.v4.runtime.misc.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Size;

/**
 * Article class definition
 *
 * @author Gilles
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Contact implements Serializable {
    /**
     * article id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * contact name
     */
    @NotNull
    @Size(min = 5, max = 50)
    private String name;
    /**
     * contact firstName
     */
    @NotNull
    @Size(min = 5, max = 50)
    private String firstName;

    /**
     * contact email
     */
    @NotNull
    private String email;

    /**
     * contact phone
     */
    @NotNull
    private String phone;

    /**
     * contact address
     */
    @NotNull
    private String address;
    /**
     * contact category
     */
    @ManyToOne
    private Category category;


    public Contact(String name, String firstName, String email, String phone, String address, Category category) {
        this.name = name;
        this.firstName = firstName;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.category = category;
    }
    public Contact(String name, String firstName, String email, String phone, String address) {
        this.name = name;
        this.firstName = firstName;
        this.email = email;
        this.phone = phone;
        this.address = address;

    }

}