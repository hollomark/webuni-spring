package hu.webuni.airport.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hu.webuni.airport.dto.AirportDto;



@RestController
@RequestMapping("/api/airports")
public class AirportController {

	
	 private Map< Long, AirportDto> airports = new HashMap<>();
	 
	 
	 
	 {
		 airports.put(1L,new AirportDto(1,"abc","XYZ"));
		 airports.put(2L,new AirportDto(2,"def","DKB"));
	 }
	 
	 
	 @GetMapping
	 public List<AirportDto> getAll() {
		 
		 return new ArrayList<>(airports.values());
	 }
	 
	 @GetMapping("/{id}")
	 public ResponseEntity<AirportDto> getById(@PathVariable long id) {
		 AirportDto airportDto = airports.get(id);
		 if(airportDto != null) {
			 return ResponseEntity.ok(airportDto);
		 }
		 return ResponseEntity.notFound().build();
	 }
	 
	 @PostMapping
	 public AirportDto createAirport(@RequestBody AirportDto airportDto) {
		 airports.put(airportDto.getId(), airportDto);
		 
		 return airportDto;
	 }
	 
	 @PutMapping("/{id}")
	 public ResponseEntity<AirportDto> modifyAirport(@PathVariable long id, @RequestBody AirportDto airportDto) {
		 airportDto.setId(id);
		 
		 if(!airports.containsKey(airportDto.getId())) {
			 return ResponseEntity.notFound().build();
		 }
		 airports.put(id, airportDto);
		 
		 return ResponseEntity.ok(airportDto);
	}
	
	 @DeleteMapping("/{id}")
	 public ResponseEntity<String> deleteAirport(@PathVariable long id) {
		 String responseText = "Airport with id:" + id + " removed from the local storage"; 
		if(airports.containsKey(id)) {
			
			airports.remove(id);
			return ResponseEntity.ok(responseText);
		}
		
		return ResponseEntity.notFound().build();
		
		 
	}
	
}