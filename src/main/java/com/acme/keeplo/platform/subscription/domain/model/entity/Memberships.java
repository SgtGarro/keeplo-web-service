package com.acme.keeplo.platform.subscription.domain.model.entity;

import com.acme.keeplo.platform.shared.domain.model.entities.AuditableModel;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter // Genera getters para todos los campos
@Setter // Añadir @Setter si quieres usar setters para la actualización
@Entity
public class Memberships extends AuditableModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true) // Nombre debe ser único y no nulo
    private String name; // Cambié 'Name' a 'name' por convención de Java

    @Column(nullable = false)
    private float price; // Cambié 'Price' a 'price' por convención de Java

    @Column(nullable = false)
    private String description; // Cambié 'Description' a 'description' por convención de Java

    // Constructor vacío para JPA
    protected Memberships() {
        // Inicializar con valores por defecto si es necesario
        this.name = "";
        this.price = 0.0f;
        this.description = "";
    }

    // Constructor para crear nuevas instancias de Memberships desde el servicio de comandos
    public Memberships(String name, float price, String description) {
        // Validación básica, las validaciones completas deberían estar en el comando o value object
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Membership name cannot be null or empty.");
        }
        if (price < 0) {
            throw new IllegalArgumentException("Membership price cannot be negative.");
        }
        if (description == null || description.isBlank()) {
            throw new IllegalArgumentException("Membership description cannot be null or empty.");
        }
        this.name = name;
        this.price = price;
        this.description = description;
    }

    // Método para actualizar las propiedades de una membresía existente
    public void update(String name, float price, String description) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Membership name cannot be null or empty.");
        }
        if (price < 0) {
            throw new IllegalArgumentException("Membership price cannot be negative.");
        }
        if (description == null || description.isBlank()) {
            throw new IllegalArgumentException("Membership description cannot be null or empty.");
        }
        this.name = name;
        this.price = price;
        this.description = description;
    }

    public boolean isFree() {
        return price == 0; // Usar 'price' en minúscula
    }
}
