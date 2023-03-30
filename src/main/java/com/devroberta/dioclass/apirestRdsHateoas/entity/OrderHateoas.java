package com.devroberta.dioclass.apirestRdsHateoas.entity;

import org.springframework.hateoas.RepresentationModel;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "CUSTOMER_ORDER")
public class OrderHateoas extends RepresentationModel<OrderHateoas> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;
    private Status status;
    private String description;

    public OrderHateoas() {
    }

    public OrderHateoas(Status status, String description) {
        this.id = id;
        this.status = status;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        OrderHateoas that = (OrderHateoas) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id);
    }

    @Override
    public String toString() {
        return "OrderHateoas{" +
                "id=" + id +
                ", status=" + status +
                ", description='" + description + '\'' +
                '}';
    }
}
