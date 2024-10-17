package com.bruce.services.user_service.repository;

import com.bruce.services.user_service.dao.User;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {












    List<User> findAll();
    Page<User> findAll(Pageable pageable);
    Optional<User> findById(UUID id);
    void deleteById(UUID id);
}
