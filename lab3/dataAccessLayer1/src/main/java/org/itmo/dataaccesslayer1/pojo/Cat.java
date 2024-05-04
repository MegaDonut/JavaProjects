package org.itmo.dataaccesslayer1.pojo;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.proxy.HibernateProxy;
import org.itmo.dataaccesslayer1.enums.CatBreed;
import org.itmo.dataaccesslayer1.enums.CatColors;

import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
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

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "cat_cats",
            joinColumns = @JoinColumn(name = "cat_1_id"),
            inverseJoinColumns = @JoinColumn(name = "cats_2_id"))
    private Set<Cat> cats = new LinkedHashSet<>();

    @ManyToOne(optional = false)
    @JoinColumn(name = "master_id")
    private Master master;

    @PreRemove
    void clearFriends() {
        cats.forEach(cat -> cat.getCats().remove(this));
        cats.clear();
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Cat cat = (Cat) o;
        return Objects.equals(getId(), cat.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
