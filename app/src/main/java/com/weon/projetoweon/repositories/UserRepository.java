package com.weon.projetoweon.repositories;

import java.util.Optional;
import java.util.UUID;

import com.weon.projetoweon.models.UserModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserModel, UUID>, JpaSpecificationExecutor<UserModel>{

    @Query(value = "select * from tb_users where user_name = :userName", nativeQuery = true)
    Optional<UserModel> findByUserName(@Param("userName") String userName);

    @Query(value = "select * from tb_users where email = :email", nativeQuery = true)
    Optional<UserModel> findByEmail(@Param("email") String email);

    @Query(value = "select * from tb_users where phone = :phone", nativeQuery = true)
    Optional<UserModel> findByPhone(@Param("phone") String phone);
    
    boolean existsByUserName(String userName);
    boolean existsByEmail(String email);
    boolean existsByPhone(String phone);
}
