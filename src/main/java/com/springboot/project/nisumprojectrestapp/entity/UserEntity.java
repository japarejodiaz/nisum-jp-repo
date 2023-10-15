package com.springboot.project.nisumprojectrestapp.entity;

import javax.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
        indexes = @Index(name = "uniqueIndexEmail", columnList = "EMAIL", unique = true))
public class UserEntity implements Serializable  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID")
    private Long idUser;

    @Column(name = "NAME", nullable = false, length = 250)
    private String name;

    @Column(name = "UUID", nullable = false, length = 36)
    private String uuid;

    @Column(name = "EMAIL", unique = true ,nullable = false, length = 250)
    private String email;

    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @Column(name = "DATE_CREATED", nullable = false)
    private LocalDateTime dateCreated;

    @Column(name = "DATE_MODIFIED")
    private LocalDateTime dateModified;

    @Column(name = "LAST_LOGIN", nullable = false)
    private LocalDateTime lastLogin;

    @Column(name = "TOKEN", nullable = false, length = 600)
    private String token;

    @Column(name = "ISACTIVE", nullable = false)
    private Boolean isActive;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userEntity")
    private List<UserPhonesEntity> userPhonesEntities;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id"),
    inverseJoinColumns = @JoinColumn(name= "role_id"),
    uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "role_id"})})
    private List<RoleEntity> roles;

}