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

	//LIST OF VISITS SCHEDULED FOR THE DAY BY DOCUMENT
	@Query(value = "select v.id_visit as idVisit, v.no_name as noName, v.fe_start as feStart, v.fe_end as feEnd, v.id_schedule as schedule, v.id_document as document, v.id_squadron as squadron from tbl_visit v inner join tbl_visitor_visit vv on v.id_visit = vv.id_visit inner join tbl_visitor tv on vv.id_visitor = tv.id_visitor where CURRENT_TIMESTAMP between v.fe_start and v.fe_end and tv.nu_document = :nuDocument", nativeQuery = true)
	List<VisitEntity> findVisitsScheduledOnTheDayByNuDocument(String nuDocument);
}
