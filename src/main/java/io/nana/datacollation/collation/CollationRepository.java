package io.nana.datacollation.collation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CollationRepository extends JpaRepository<CollationModel, Long> {
}
