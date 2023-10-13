package com.springboot.project.nisumprojectrestapp.repository;

import com.springboot.project.nisumprojectrestapp.entity.UserPhonesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
/**
 * Repository IUserPhoneRepository
 * <p>
 * Data access component with operations related to the entity {@link UserPhonesEntity}
 *
 * @see UserPhonesEntity
 * @see Repository
 */
@Repository
public interface IUserPhoneRepository extends JpaRepository<UserPhonesEntity, Long> {
}



