package pe.mil.fap.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.mil.fap.entity.EscuadronEntity;

@Repository
public interface EscuadronRepository extends JpaRepository<EscuadronEntity, Long>{

}
