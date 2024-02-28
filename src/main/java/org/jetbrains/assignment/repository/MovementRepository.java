package org.jetbrains.assignment.repository;

import org.jetbrains.assignment.model.Movement;
import org.springframework.data.jpa.repository.JpaRepository;


public interface MovementRepository extends JpaRepository<Movement, Long> {
}
