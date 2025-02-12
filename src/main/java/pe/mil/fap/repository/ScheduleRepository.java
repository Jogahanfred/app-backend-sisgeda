package pe.mil.fap.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import pe.mil.fap.entity.ScheduleEntity;
import pe.mil.fap.entity.SquadronEntity;
import pe.mil.fap.entity.WorkingHoursEntity;

@Repository
public interface ScheduleRepository extends JpaRepository<ScheduleEntity, Long>{

}
