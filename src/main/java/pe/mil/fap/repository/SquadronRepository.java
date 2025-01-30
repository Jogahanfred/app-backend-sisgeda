package pe.mil.fap.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import pe.mil.fap.entity.SquadronEntity;

@Repository
public interface SquadronRepository extends JpaRepository<SquadronEntity, Long>{
	
	@Query("SELECT a FROM SquadronEntity a WHERE (LOWER(a.coIdentifier) LIKE LOWER(CONCAT('%', :filter, '%')) OR LOWER(a.noLongName) LIKE LOWER(CONCAT('%', :filter, '%'))) AND a.flState = true")
	Page<SquadronEntity> page(String filter, Pageable pageable);
	 
}
