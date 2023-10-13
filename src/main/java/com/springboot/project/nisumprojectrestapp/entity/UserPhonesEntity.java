package com.springboot.project.nisumprojectrestapp.entity;


import lombok.*;
import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Data
@ToString
@Builder
@Entity
@Table(name = "USERS_PHONE")
public class UserPhonesEntity implements Serializable  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDPHONE")
    private Long idPhone;

    @Column(name = "NUMBER_PHONE", nullable = false)
    private String numberPhone;

    @Column(name = "CITY_CODE", nullable = false)
    private String cityCode;

    @Column(name = "COUNTRY_CODE", nullable = false)
    private String countryCode;

    @ManyToOne
    @JoinColumn(name = "USUARIO_ID")
    private UserEntity userEntity;

}