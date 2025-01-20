package pe.mil.fap.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.mil.fap.entity.ImagenEntity;

@Repository
public interface ImagenRepository extends JpaRepository<ImagenEntity, Long>{

}
