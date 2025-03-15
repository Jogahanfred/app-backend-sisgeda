package pe.mil.fap.repository;

import java.util.List; 

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import pe.mil.fap.dto.helpers.GraphicPatternsDTO;
import pe.mil.fap.dto.helpers.ReportNumberEntriesPerPersonDTO;
import pe.mil.fap.dto.helpers.StatsWidgetDTO;
import pe.mil.fap.entity.SegmentVisitorEntity;

@Repository
public interface GraphicRepository extends JpaRepository<SegmentVisitorEntity, Long> {

	@Query(value = "WITH days_of_week AS ( "
			+ "    SELECT 'Monday' AS day_of_week, 1 AS day_order "
			+ "    UNION ALL SELECT 'Tuesday', 2 "
			+ "    UNION ALL SELECT 'Wednesday', 3 "
			+ "    UNION ALL SELECT 'Thursday', 4 "
			+ "    UNION ALL SELECT 'Friday', 5 "
			+ "    UNION ALL SELECT 'Saturday', 6 "
			+ "    UNION ALL SELECT 'Sunday', 7 "
			+ "), visitor_counts AS ( "
			+ "    SELECT TO_CHAR(fe_start_segment, 'Day') AS day_week, "
			+ "           COUNT(DISTINCT id_visitor) AS count "
			+ "    FROM tbl_segment_visitor "
			+ "    WHERE TO_CHAR(fe_start_segment, 'MM') = :month  "
			+ "          AND TO_CHAR(fe_start_segment, 'YYYY') = :year "
			+ "    GROUP BY TO_CHAR(fe_start_segment, 'Day') "
			+ ") "
			+ "SELECT d.day_of_week as no_day, "
			+ "       COALESCE(vc.count, 0) as nu_count "
			+ "FROM days_of_week d "
			+ "LEFT JOIN visitor_counts vc ON TRIM(d.day_of_week) = TRIM(vc.day_week) "
			+ "ORDER BY d.day_order", nativeQuery = true)
	List<GraphicPatternsDTO> findGraphicPatternsByMonthAndYear(String month, String year);
	
	@Query(value = "SELECT id_visitor as id_visitor, count(*) as nu_count "
			+ "FROM tbl_segment_visitor tsv "
			+ "WHERE TO_CHAR(fe_start_segment, 'MM') = :month AND "
			+ "      TO_CHAR(fe_start_segment, 'YYYY') = :year "
			+ "GROUP BY id_visitor "
			+ "ORDER BY nu_count DESC LIMIT 10", nativeQuery = true)
	List<ReportNumberEntriesPerPersonDTO> findReportNumberEntriesPerPersonDTO(String month, String year);
	
}
