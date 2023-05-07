package com.ehrlich.codechallenge.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "order_details")
public class OrderDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "order_details_id")
    private Long orderDetailsId;

    @Column(name = "order_id", nullable = false)
    private Long orderId;

    @Column(name = "orders_id", nullable = false, unique = true)
    private Long ordersId;

    @Column(name = "pizza_id", nullable = false)
    private String pizzaId;

    @Column(name = "quantity", nullable = false)
    private Long quantity;

    @ManyToOne(targetEntity = Orders.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "orders_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Orders orders;

}
