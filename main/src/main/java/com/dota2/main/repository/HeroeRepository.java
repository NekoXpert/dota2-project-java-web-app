package com.dota2.main.repository;

// Author: Felipe Reyes { Nekosor }
import com.dota2.main.model.Heroe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HeroeRepository extends JpaRepository<Heroe, Long> {
}

