package com.hbs.auracar.repository;

import com.hbs.auracar.repository.entity.CountDownEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountDownRepository extends JpaRepository<CountDownEntity, Long> {
}
