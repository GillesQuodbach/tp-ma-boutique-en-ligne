package fr.fms.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;

/** customer class definition
 * @author Gilles
 * */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Customer implements Serializable {
    /**
     * customer id
     * */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * customer name
     * */
    @Pattern(regexp = "^[a-zA-Z]+$", message = "name must contains characters")
    private String name;

    /**
     * customer lastName
     * */
    @Pattern(regexp = "^[a-zA-Z]+$", message = "lastName must contains characters")
    private String lastName;
    /**
     * customer address
     * */
    @Size(min = 10, max = 50)
    private String address;

    /**
     * customer email
     * */
    @Email
    private String email;
    /**
     * customer phone
     * */
    @Pattern(regexp = "^[0-9]{10,14}$", message = "contains only numbers")
    private String phone;



}
