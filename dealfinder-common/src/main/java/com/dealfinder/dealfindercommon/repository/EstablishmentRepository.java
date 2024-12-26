package com.dealfinder.dealfindercommon.repository;

import com.dealfinder.dealfindercommon.model.Establishment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstablishmentRepository extends JpaRepository<Establishment, Long> {

    Establishment findByLoginAndPassword(String login, String password);
}