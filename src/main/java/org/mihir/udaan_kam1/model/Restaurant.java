package org.mihir.udaan_kam1.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.mihir.udaan_kam1.enums.RestaurantScale;
import org.mihir.udaan_kam1.enums.RestaurantStatus;

import java.util.List;

@Entity
@Table(name = "restaurants")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long restaurantId;

    @Column(name = "name", nullable = false)
    private String restaurantName;

    @Column(name = "address", nullable = false, length = 200)
    private String restaurantAddress;

    @Enumerated(EnumType.STRING)
    @Column(name = "scale", nullable = false)
    private RestaurantScale restaurantScale;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private RestaurantStatus restaurantStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id", referencedColumnName = "id", nullable = false)
    private Employee employee;

    @OneToMany(mappedBy = "restaurant", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<RestaurantPOC> pointOfContacts;
}
