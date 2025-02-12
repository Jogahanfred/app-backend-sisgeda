package pe.mil.fap.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
 
import pe.mil.fap.entity.VisitorEntity;

@Repository
public interface VisitorRepository extends JpaRepository<VisitorEntity, Long> {

	@Query("SELECT a FROM VisitorEntity a WHERE (LOWER(a.noName) LIKE LOWER(CONCAT('%', :filter, '%')) OR LOWER(a.noSurName) LIKE LOWER(CONCAT('%', :filter, '%')) OR LOWER(a.nuDocumento) LIKE LOWER(CONCAT('%', :filter, '%')))")
	Page<VisitorEntity> page(String filter, Pageable pageable);
}
