package pe.mil.fap.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;
import pe.mil.fap.entity.SegmentVisitorEntity; 

@Repository
public interface SegmentVisitorRepository extends JpaRepository<SegmentVisitorEntity, Long>{

	@Query(value = "select COUNT(*) from tbl_segment_visitor a where a.id_visit = :idVisit and a.id_visitor = :idVisitor and a.co_segment_type = 'ENTRANCE' and TO_CHAR(a.fe_start_segment,'DD-MM-YYYY') = :feEntrance", nativeQuery = true)
	Integer validateVisitorEntry(Long idVisit, Long idVisitor, String feEntrance);

	@Query(value = "select COUNT(*) from tbl_segment_visitor a where a.id_segment_visitor = :idSegment and a.co_segment_type = 'EXIT' and TO_CHAR(a.fe_start_segment,'DD-MM-YYYY') = :feExit", nativeQuery = true)
	Integer validateVisitorExit(Long idSegment, String feExit);
	
	@Transactional
	@Modifying
	@Query(value = "update tbl_segment_visitor set co_segment_type = 'EXIT', fe_end_segment = CURRENT_TIMESTAMP where id_segment_visitor = :idSegmentVisitor", nativeQuery = true)
	void updateSituationVisitorExit(Long idSegmentVisitor);

}
