package org.itmo.pojo;

import jakarta.persistence.*;
import lombok.*;
import org.itmo.enums.CatBreed;
import org.itmo.enums.CatColors;

import java.util.*;

@Data
@Entity
@Table(name = "cat")
public class Cat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Date birthday;
    private CatBreed breed;
    private CatColors colors;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name = "cat_cats",
            joinColumns = @JoinColumn(name = "cat_1_id"),
            inverseJoinColumns = @JoinColumn(name = "cats_2_id"))
    private List<Cat> cats = new LinkedList<>();

    @ManyToOne(optional = false, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "master_id", nullable = false)
    private Master master;

}
