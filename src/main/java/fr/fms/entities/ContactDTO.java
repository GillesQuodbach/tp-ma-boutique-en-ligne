package fr.fms.entities;

import groovyjarjarantlr4.v4.runtime.misc.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Size;

/**
 * DTO Article class definition
 *
 * @author Gilles
 */
@Setter
@Getter
public class ContactDTO {
    private Long categoryId;
    @NotNull

    private String name;
    private String firstName;
    private String email;
    private String phone;
    private String address;



}
