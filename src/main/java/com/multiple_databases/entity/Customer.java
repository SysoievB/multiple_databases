package com.multiple_databases.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "customers")
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    String surname;
    String address;
    String phone;

    public Customer(String name, String surname, String address, String phone) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.phone = phone;
    }
}
