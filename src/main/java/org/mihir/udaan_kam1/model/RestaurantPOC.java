package org.mihir.udaan_kam1.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZoneId;

@Entity
@Table(name = "pocs")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantPOC {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long pocId;

    @Column(name = "name", nullable = false, length = 100)
    private String pocName;

    @Column(name = "contact_number", nullable = false, length = 20)
    private String pocContactNumber;

    @Column(name = "role", nullable = false, length = 50)
    private String pocRole;

    @Column(name = "zone", nullable = false)
    private ZoneId pocTimeZone;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", referencedColumnName = "id", nullable = false)
    private Restaurant restaurant;
}
