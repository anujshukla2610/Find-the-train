package com.anuj.Find.the.train.repo;

import com.anuj.Find.the.train.enitity.Station;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface StationRepository extends JpaRepository<Station,Long> {
}
