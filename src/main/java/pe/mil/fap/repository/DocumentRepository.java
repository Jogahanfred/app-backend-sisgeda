package pe.mil.fap.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
 
import pe.mil.fap.entity.DocumentEntity;

@Repository
public interface DocumentRepository extends JpaRepository<DocumentEntity, Long> {

	@Query("SELECT a FROM DocumentEntity a WHERE (LOWER(a.noReference) LIKE LOWER(CONCAT('%', :filter, '%')) )")
	Page<DocumentEntity> page(String filter, Pageable pageable);
}
