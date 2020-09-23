package com.diegunix.iobuilders.dao.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@Entity
@Table(name = "useriobuilder")
public class UserIoBuilder {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column(nullable = false)
  private String login;

  @Column(nullable = false)
  private Boolean active;

  @Column(nullable = false)
  private String firstName;

  @Column(nullable = false)
  private String lastName;

  @Column(nullable = false)
  private String mail;

  @Column(nullable = false)
  private LocalDateTime createdDate;

  @Column(nullable = true)
  private LocalDateTime updatedDate;
}
