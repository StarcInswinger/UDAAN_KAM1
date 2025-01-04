package org.mihir.udaan_kam1.model;

import jakarta.persistence.*;
import lombok.*;
import org.mihir.udaan_kam1.enums.RestaurantScale;
import org.mihir.udaan_kam1.enums.RestaurantStatus;

import java.util.List;

@Entity
@Table(name = "restaurants")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"employee", "pointOfContacts"})
@EqualsAndHashCode(exclude = {"employee", "pointOfContacts"})
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    @JoinColumn(name = "employee_username", referencedColumnName = "username", nullable = false)
    private Employee employee;

    @OneToMany(mappedBy = "restaurant", fetch = FetchType.LAZY)
    private List<RestaurantPOC> pointOfContacts;

    @OneToOne
    private Performance performance;
}
