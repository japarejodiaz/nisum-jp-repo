package com.springboot.project.nisumprojectrestapp.entity;

import javax.persistence.*;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Data
@ToString
@Builder
@Entity
@Table(name = "ROLES")
public class RoleEntity implements Serializable  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Long idRol;

    @Column(name = "NOMBRE", unique = true,nullable = false, length = 250)
    private String name;

}