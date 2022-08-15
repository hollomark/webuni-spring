package hu.webuni.hr.mark.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import hu.webuni.hr.mark.model.Position;

public interface PositionRepository  extends JpaRepository<Position, Long >{

	
}
