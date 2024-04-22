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
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Article implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Size(min = 10, max = 50)
    private String description;
    @DecimalMin("50")
    private double price;

    @ManyToOne
   private Category category;

    @OneToMany(mappedBy = "article")
    @ToString.Exclude
    private Collection<OrderItem> orderItems;



    public Article(String description, double price) {
        this.description = description;
        this.price = price;
    }


}