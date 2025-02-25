package pe.mil.fap.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;
import pe.mil.fap.entity.VisitEntity;
import pe.mil.fap.utils.enums.PersonalSituationEnum;

@Repository
public interface VisitRepository extends JpaRepository<VisitEntity, Long>{
	
	@Query("SELECT a FROM VisitEntity a WHERE (LOWER(a.noName) LIKE LOWER(CONCAT('%', :filter, '%')))")
	Page<VisitEntity> page(String filter, Pageable pageable);
	
	@Transactional
	@Modifying
	@Query("UPDATE VisitorVisitEntity a SET a.coSituation = :situation WHERE a.visitor.idVisitor = :idVisitor and a.visit.idVisit = :idVisit")
	void updateSituationVisitor(Long idVisit, Long idVisitor, PersonalSituationEnum situation);

	//LIST OF VISITS SCHEDULED FOR THE DAY
	@Query(value = "select * from tbl_visit v where CURRENT_TIMESTAMP between v.fe_start and v.fe_end", nativeQuery = true)
	List<VisitEntity> findVisitsScheduledOnTheDay();
 
}
