package fr.fms.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Collection;

/**
 * customer class definition
 *
 * @author Gilles
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Customer implements Serializable {
    /**
     * customer id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * customer name
     */
    @Pattern(regexp = "^[a-zA-Z]+$", message = "name must contains characters")
    private String name;

    /**
     * customer lastName
     */
    @Pattern(regexp = "^[a-zA-Z]+$", message = "lastName must contains characters")
    private String lastName;
    /**
     * customer address
     */
    @Size(min = 10, max = 50)
    private String address;

    /**
     * customer email
     */
    @Email
    private String email;
    /**
     * customer phone
     */
    @Pattern(regexp = "^\\d{10,14}$", message = "contains only numbers")
    private String phone;


    /**
     * Entity relationship with user
     */
    @ManyToOne
    @JoinColumn(name = "username")
    private User user;

}
