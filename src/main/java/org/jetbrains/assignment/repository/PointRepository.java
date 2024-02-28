package org.jetbrains.assignment.repository;

import org.jetbrains.assignment.model.Point;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PointRepository extends JpaRepository<Point, Long> {
}
