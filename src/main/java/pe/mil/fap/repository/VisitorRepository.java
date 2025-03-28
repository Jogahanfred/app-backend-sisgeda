package pe.mil.fap.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
 
import pe.mil.fap.entity.VisitorEntity;

@Repository
public interface VisitorRepository extends JpaRepository<VisitorEntity, Long> {

	@Query("SELECT a FROM VisitorEntity a WHERE (LOWER(a.noName) LIKE LOWER(CONCAT('%', :filter, '%')) OR LOWER(a.noSurName) LIKE LOWER(CONCAT('%', :filter, '%')) OR LOWER(a.nuDocument) LIKE LOWER(CONCAT('%', :filter, '%')))")
	Page<VisitorEntity> page(String filter, Pageable pageable);

	@Query("select v from VisitorEntity v where v.nuDocument = :document")
	Optional<VisitorEntity> findByNuDocumento(String document);
	
	@Query(value = "select count(*) from tbl_visitor tv", nativeQuery = true)
	Integer counterVisitors();	
	
	@Query(value = "select count(*) from tbl_visitor tv where tv.no_nationality != 'PERU'", nativeQuery = true)
	Integer counterForeignVisitor();	
}
