package com.springboot.project.nisumprojectrestapp.repository;

import com.springboot.project.nisumprojectrestapp.entity.UserEntity;
import com.springboot.project.nisumprojectrestapp.entity.UserPhonesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository IUserRepository
 * <p>
 * Data access component with operations related to the entity {@link UserEntity}
 *
 * @see UserEntity
 * @see Repository
 */
@Repository
public interface IUserRepository extends JpaRepository<UserEntity, Long> {

    @Query(
            "Select UE from UserEntity UE where UE.email = :email")
    Optional<UserEntity> getUserEntityByEmail(@Param("email") String email);

    @Query(
            "Select UE from UserEntity UE where UE.uuid = :uuid")
    Optional<UserEntity> getUserEntityByUuid(@Param("uuid") String uuid);




}

