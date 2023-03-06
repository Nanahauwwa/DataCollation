package io.nana.datacollation.artisan;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CollationRepository extends JpaRepository<ArtisanModel, Long> {
    List<ArtisanModel> findByTrade(String trade);
}
