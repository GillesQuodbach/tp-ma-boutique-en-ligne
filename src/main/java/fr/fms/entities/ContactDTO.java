package fr.fms.entities;

import groovyjarjarantlr4.v4.runtime.misc.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * DTO Contact class definition
 *
 * @author Gilles
 */
@Setter
@Getter
public class ContactDTO {
    private Long categoryId;
    @NotNull
    @NotBlank(message = "a name is requiered")
    private String name;
    @NotBlank(message = "a firstname is requiered")
    private String firstName;
    @Email
    private String email;
    private String phone;
    private String address;
}
