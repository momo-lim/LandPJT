package io.ssafy.ssafyland.db.repository;

import io.ssafy.ssafyland.db.entity.Treasure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TreasureRepository extends JpaRepository<Treasure, Long> {
}
