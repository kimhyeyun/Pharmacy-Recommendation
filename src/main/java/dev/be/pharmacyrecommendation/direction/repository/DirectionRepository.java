package dev.be.pharmacyrecommendation.direction.repository;

import dev.be.pharmacyrecommendation.direction.entity.Direction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DirectionRepository extends JpaRepository<Direction, Long> {
}
