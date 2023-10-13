package com.springboot.project.nisumprojectrestapp.entity;

import javax.persistence.*;
import lombok.*;

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
@Table(name = "USERS",
        indexes = @Index(name = "uniqueIndexEmail", columnList = "correo", unique = true))
public class UserEntity implements Serializable  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USUARIO_ID")
    private Long idUser;

    @Column(name = "NOMBRE", nullable = false, length = 250)
    private String name;

    @Column(name = "UUID", nullable = false, length = 36)
    private String uuid;

    @Column(name = "CORREO", unique = true ,nullable = false, length = 250)
    private String email;

    @Column(name = "CONTRASENA", nullable = false)
    private String password;

    @Column(name = "DATE_CREATED", nullable = false)
    private LocalDate dateCreated;

    @Column(name = "DATE_MODIFIED")
    private LocalDate dateModified;

    @Column(name = "LAST_LOGIN", nullable = false)
    private LocalDate lastLogin;

    @Column(name = "TOKEN", nullable = false)
    private String token;

    @Column(name = "ISACTIVE", nullable = false)
    private Boolean isActive;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userEntity")
    private List<UserPhonesEntity> userPhonesEntities;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "usuario_id"),
    inverseJoinColumns = @JoinColumn(name= "role_id"),
    uniqueConstraints = {@UniqueConstraint(columnNames = {"usuario_id", "role_id"})})
    private List<RoleEntity> roles;

}