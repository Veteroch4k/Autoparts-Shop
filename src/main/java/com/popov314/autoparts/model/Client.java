package com.popov314.autoparts.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "clients")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Client {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(name = "first_name", nullable = false, length = 25)
  private String firstName;

  @Column(name = "second_name", nullable = false, length = 25)
  private String secondName;

  @Column(name = "phone_number", nullable = false,  length = 22)
  private String phoneNumber;

}
