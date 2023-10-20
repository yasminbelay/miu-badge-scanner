package BadgeAndMembership.controller;

import BadgeAndMembership.model.Badge;
import BadgeAndMembership.service.BadgeService;
import contracts.dto.BadgeDto;
import contracts.dto.BadgeRequestDto;
import edu.miu.common.controller.BaseReadController;
import edu.miu.common.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/badges")
public class BadgeController extends BaseReadController<BadgeDto, Badge, Integer > {
    @Autowired
    private BadgeService badgeService;

    @PostMapping
    public ResponseEntity<?> add(@RequestBody BadgeRequestDto requestDto) throws ResourceNotFoundException {
        badgeService.addBadge(requestDto);
        return new ResponseEntity<>("Badge added successfully", HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable int id, @RequestBody BadgeRequestDto requestDto) throws ResourceNotFoundException {
        badgeService.updateBadge(id, requestDto);
        return new ResponseEntity<>("Badge updated successfully", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable int id){
        badgeService.deleteBadge(id);
        return new ResponseEntity<>("Badge deleted successfully", HttpStatus.OK);
    }
}
