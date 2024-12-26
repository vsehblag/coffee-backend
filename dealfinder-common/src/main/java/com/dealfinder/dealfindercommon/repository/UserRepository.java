package com.dealfinder.dealfindercommon.repository;

import com.dealfinder.dealfindercommon.model.DealFinderUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<DealFinderUser, Long> {

    boolean existsByUsername(String username);
    DealFinderUser findByLoginAndPassword(String username, String password);
}
