package BadgeAndMembership.controller;

import BadgeAndMembership.model.Membership;
import BadgeAndMembership.service.MembershipService;
import contracts.dto.MembershipDto;
import contracts.dto.MembershipRequestDto;
import edu.miu.common.controller.BaseReadController;
import edu.miu.common.exception.ResourceNotFoundException;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/memberships")
public class MembershipController extends BaseReadController<MembershipDto, Membership, Integer> {
    @Autowired
    private MembershipService membershipService;

    @Autowired
    private Logger logger;

    @PostMapping
    public ResponseEntity<?> addMembership(@RequestBody MembershipRequestDto membershipRequestDto) throws ResourceNotFoundException {
        membershipService.add(membershipRequestDto);
        logger.info("MembershipService: Adding Membership: " + membershipRequestDto);
        return new ResponseEntity<>("Membership has been successfully added", HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateMembership(@PathVariable int id, @RequestBody MembershipRequestDto membershipRequestDto) throws ResourceNotFoundException {
        membershipService.update(id, membershipRequestDto);
        logger.info("MembershipService: Updating Membership: " + membershipRequestDto);
        return new ResponseEntity<>("Membership has been successfully updated", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMembership(@PathVariable int id) throws ResourceNotFoundException {
        membershipService.delete(id);
        logger.info("MembershipService: Deleting Membership: " + id);
        return new ResponseEntity<>("Membership has been successfully deleted", HttpStatus.OK);
    }

//    @PostMapping("/members/{id}/memberships")
//    public ResponseEntity<?> addMembership(@PathVariable int id, @RequestBody MembershipDto membershipDto) throws ResourceNotFoundException {
//        membershipService.add(id, membershipDto);
//        logger.info("MembershipService: Adding Membership: " + membershipDto);
//        return new ResponseEntity<>("Membership has been successfully added", HttpStatus.OK);
//    }
}
