package io.nana.datacollation.artisan;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CollationRepository extends JpaRepository<Artisan, Long> {
    List<Artisan> findByTrade(String trade);
}
