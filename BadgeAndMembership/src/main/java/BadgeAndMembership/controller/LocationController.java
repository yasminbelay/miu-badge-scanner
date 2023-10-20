package BadgeAndMembership.controller;

import BadgeAndMembership.model.Location;
import BadgeAndMembership.service.LocationService;
import contracts.dto.LocationDto;
import contracts.dto.LocationRequestDto;
import edu.miu.common.controller.BaseReadController;
import edu.miu.common.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping( "/locations")
public class LocationController  extends BaseReadController<LocationDto, Location, Integer > {

    @Autowired
    private LocationService locationService;

    @PostMapping
    public ResponseEntity<?> add(@RequestBody LocationRequestDto locationDto) throws ResourceNotFoundException {
        locationService.add(locationDto);
        return new ResponseEntity<>( "Location created Successfully", HttpStatus.OK);
    }

    @PutMapping("/{id}")

    public ResponseEntity<?> update(@PathVariable int id, @RequestBody LocationRequestDto locationDto) throws ResourceNotFoundException {
        locationService.update(id, locationDto);
        return new ResponseEntity<>( "location update Successfully", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) throws ResourceNotFoundException {
        locationService.deleteById(id);
        return new ResponseEntity<>( "Location Delete Successfully", HttpStatus.OK);
    }



}
