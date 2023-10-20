package BadgeAndMembership.controller;

import BadgeAndMembership.model.Plan;
import BadgeAndMembership.service.PlanService;
import contracts.dto.LocationDto;
import contracts.dto.PlanDto;
import contracts.dto.PlanRequestDto;
import edu.miu.common.controller.BaseReadController;
import edu.miu.common.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/plans")
public class PlanController extends BaseReadController<PlanDto, Plan, Integer> {
    @Autowired
    private PlanService planService;

    @PostMapping
    public ResponseEntity<?> add(@RequestBody PlanRequestDto requestDto) {
        planService.add(requestDto);
        return new ResponseEntity<>("Plan created Successfully", HttpStatus.OK);
    }

    @PutMapping("/{id}")

    public ResponseEntity<?> update(@PathVariable int id, @RequestBody PlanRequestDto requestDto) throws ResourceNotFoundException {
        planService.update(id, requestDto);
        return new ResponseEntity<>("Plan update Successfully", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) throws ResourceNotFoundException {
        planService.deleteById(id);
        return new ResponseEntity<>("Plan Delete Successfully", HttpStatus.OK);
    }

    @GetMapping("{id}/locations")
    public ResponseEntity<?> getAllLocations(@PathVariable int id) throws ResourceNotFoundException{
        Collection<LocationDto> locations = planService.getAllLocations(id);
        return new ResponseEntity<Collection<LocationDto>>(locations, HttpStatus.OK);
    }
}
