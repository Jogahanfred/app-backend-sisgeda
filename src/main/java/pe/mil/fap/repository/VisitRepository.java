package pe.mil.fap.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;
import pe.mil.fap.dto.helpers.VisitorScheduleOnTheDayDTO;
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
	@Query(value = "select * from tbl_visit v where CURRENT_DATE between v.fe_start and v.fe_end", nativeQuery = true)
	List<VisitEntity> findVisitsScheduledOnTheDay();
	
	//LIST OF VISITOR SCHEDULED FOR THE DAY
	@Query(value = 
			  "SELECT tv.no_name AS no_name_visit, " +
			  "       TO_CHAR(tv.fe_start, 'dd-mm-yyyy') AS fe_start, " +
			  "       TO_CHAR(tv.fe_end, 'dd-mm-yyyy') AS fe_end, " +
			  "       tsq.no_long_name AS no_name_squadron, " +
			  "       ts.no_name AS no_name_schedule, " +
			  "       tvi.nu_document AS nu_document_visitor, " +
			  "       tvi.no_sur_names AS no_sur_names_visitor, " +
			  "       tvi.no_names AS no_names_visitor, " +
			  "       tvv.co_situation " +
			  "FROM tbl_visit tv " +
			  "INNER JOIN tbl_visitor_visit tvv ON tv.id_visit = tvv.id_visit " +
			  "INNER JOIN tbl_squadron tsq ON tsq.id_squadron = tv.id_squadron " +
			  "INNER JOIN tbl_schedule ts ON ts.id_schedule = tv.id_schedule " +
			  "INNER JOIN tbl_visitor tvi ON tvi.id_visitor = tvv.id_visitor " +
			  "WHERE CURRENT_DATE BETWEEN tv.fe_start AND tv.fe_end", nativeQuery = true)
	List<VisitorScheduleOnTheDayDTO> findVisitorsScheduledOnTheDay();
	
	//COUNT SCHEDULE VISIT TO VISITOR
	@Query(value = "select count(*) from tbl_visitor_visit tvv inner join tbl_visit tv on tvv.id_visit  = tv.id_visit where current_date between tv.fe_start and tv.fe_end  and id_visitor = :idVisitor", nativeQuery = true)
	Integer findScheduledVisitToVisitor(Long idVisitor);
	
	//COUNT SEGMENT VISIT
	@Query(value = "select count(*) from tbl_segment_visitor tsv where id_visit = :idVisit", nativeQuery = true)
	Integer findSegmentVisit(Long idVisit);

	//COUNT SEGMENT VISIT TYPE ENTRANCE
	@Query(value = "select count(*) from tbl_segment_visitor tsv where id_visit = :idVisit and co_segment_type = 'ENTRANCE'", nativeQuery = true)
	Integer findSegmentVisitTypeEntrance(Long idVisit);

	//COUNT VISITOR SCHEDULED ON THE DAY
	@Query(value = "select count(id_visitor) " +
				   "FROM tbl_visit tv " +
				   "INNER JOIN tbl_visitor_visit tvv on tvv.id_visit = tv.id_visit " +
				   "WHERE CURRENT_DATE BETWEEN tv.fe_start and tv.fe_end", nativeQuery = true)
	Integer counterVisitorsScheduledOnTheDay();
	
	//COUNT VISITOR SCHEDULED ON THE DAY IN PROGRESS
	@Query(value = "SELECT count(distinct id_visitor) " +
				   "FROM tbl_segment_visitor tsv " +
				   "INNER JOIN tbl_visit tv on tsv.id_visit  = tv.id_visit " +
				   "WHERE (CURRENT_DATE between tv.fe_start and tv.fe_end and to_char(tsv.fe_start_segment,'yyyy-mm-dd') = to_char(current_date,'yyyy-mm-dd')) and tsv.co_segment_type = 'ENTRANCE'", nativeQuery = true)
	Integer counterVisitorsScheduledOnTheDayInProgress();
	
	//COUNT VISITOR SCHEDULED ON THE DAY IN DETENTION
	@Query(value = "SELECT count(distinct id_visitor) " +
				   "FROM tbl_segment_visitor tsv " +
				   "INNER JOIN tbl_visit tv on tsv.id_visit  = tv.id_visit " +
				   "WHERE (CURRENT_DATE between tv.fe_start and tv.fe_end and to_char(tsv.fe_start_segment,'yyyy-mm-dd') = to_char(current_date,'yyyy-mm-dd')) and tsv.co_segment_type = 'DETENTION'", nativeQuery = true)
	Integer counterVisitorsScheduledOnTheDayDetention();
	
	
	//COUNT VISITOR SCHEDULED ON THE DAY IN EXIT
	@Query(value = "SELECT count(distinct id_visitor) from tbl_segment_visitor tsv " +
				   "INNER JOIN tbl_visit tv on tsv.id_visit  = tv.id_visit " +
				   "WHERE (CURRENT_DATE between tv.fe_start and tv.fe_end and to_char(tsv.fe_start_segment,'yyyy-mm-dd') = to_char(current_date,'yyyy-mm-dd')) and tsv.co_segment_type = 'EXIT' and tsv.id_visitor not in ( "+
				   "																SELECT distinct id_visitor from tbl_segment_visitor tsv "+
				   "																INNER JOIN  tbl_visit tv on tsv.id_visit  = tv.id_visit "+
				   "																WHERE (CURRENT_DATE between tv.fe_start and tv.fe_end and to_char(tsv.fe_start_segment,'yyyy-mm-dd') = to_char(current_date,'yyyy-mm-dd')) and tsv.co_segment_type = 'ENTRANCE')", nativeQuery = true)
	Integer counterVisitorsScheduledOnTheDayExit();	
	
	//COUNT VISIT PENDING
	@Query(value = "select COUNT(*) from tbl_visit tv where tv.fe_start > current_date", nativeQuery = true)
	Integer counterVisitsPending();	

	@Query(value = "select COUNT(*) from tbl_visit tv", nativeQuery = true)
	Integer counterVisits();	
}
