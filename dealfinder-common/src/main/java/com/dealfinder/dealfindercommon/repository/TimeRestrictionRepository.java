package com.dealfinder.dealfindercommon.repository;

import com.dealfinder.dealfindercommon.model.TimeRestriction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TimeRestrictionRepository extends JpaRepository<TimeRestriction, Long> {
}
