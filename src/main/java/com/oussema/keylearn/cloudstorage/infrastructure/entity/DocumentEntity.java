package com.oussema.keylearn.cloudstorage.infrastructure.entity;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@MappedSuperclass
public class DocumentEntity {

  @Id
  @GeneratedValue
  @Column(name = "id", nullable = false, unique = true)
  private UUID id;

  @Column(name = "url", nullable = false)
  private String url;

  @Column(name = "name", nullable = false)
  private String name;

  @Column(name = "extension", nullable = false)
  private String extension;

  @Column(name = "size", nullable = false)
  private Long size;
}
