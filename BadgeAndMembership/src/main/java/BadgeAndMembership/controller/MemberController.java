package BadgeAndMembership.controller;

import BadgeAndMembership.model.Member;
import BadgeAndMembership.service.MemberService;
import contracts.dto.*;
import edu.miu.common.controller.BaseReadController;
import edu.miu.common.exception.ResourceNotFoundException;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/members")
public class MemberController extends BaseReadController<MemberDto, Member, Integer> {
    @Autowired
    private MemberService memberService;
    @Autowired
    private Logger logger;

    @PostMapping
    public ResponseEntity<?> add(@RequestBody MemberRequestDto memberDto) {
        logger.info("MemberController: Adding member :{}" + memberDto);
        memberService.add(memberDto);
        return new ResponseEntity<>("Member created successfully", HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable int id, @RequestBody MemberRequestDto memberDto) throws ResourceNotFoundException {
        logger.info("MemberService: Updating member with id: " + id + " to: {}" + memberDto);
        memberService.update(id, memberDto);
        return new ResponseEntity<>("Member updated successfully", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable int id) throws ResourceNotFoundException {
        logger.info("MemberService: Deleting member with id: " + id);
        memberService.delete(id);
        return new ResponseEntity<>("Member deleted successfully", HttpStatus.OK);
    }

    //For a member, return the list of all plans
    @GetMapping("/{member_id}/plans")
    public ResponseEntity<?> getPlansByMemberId(@PathVariable int member_id) throws ResourceNotFoundException{
        return new ResponseEntity<List<PlanDto>>(memberService.findPlansByMemberId(member_id), HttpStatus.OK);
    }

    //For a member, return the list of all badges
    @GetMapping("/{member_id}/badges")
    public ResponseEntity<?> findBadgesByMemberId(@PathVariable int member_id) throws ResourceNotFoundException{
        return new ResponseEntity<List<BadgeDto>>(memberService.findBadgesByMemberId(member_id), HttpStatus.OK);
    }

    //For a member, return the list of all memberships
    @GetMapping("/{member_id}/memberships")
    public ResponseEntity<?> findMembershipsByMemberId(@PathVariable int member_id) throws ResourceNotFoundException {
        return new ResponseEntity<List<MembershipDto>>(memberService.findMembershipsByMemberId(member_id), HttpStatus.OK);
    }

    //For a member, return the list of all active badges
    @GetMapping("/{member_id}/active-badges")
    public ResponseEntity<?> findActiveBadgesByMemberId(@PathVariable int member_id, boolean isActive) throws ResourceNotFoundException {
        return new ResponseEntity<List<BadgeDto>>(memberService.findActiveBadgesByMemberId(member_id, true
        ), HttpStatus.OK);
    }

    //For a member, return the list of all Transactions
    @GetMapping("/{id}/transactions")
    public ResponseEntity<?> findTransactionsByMemberId(@PathVariable int id) throws ResourceNotFoundException{
        Collection<TransactionDto> transactions = memberService.findTransactionsByMemberId(id);
        return new ResponseEntity<Collection<TransactionDto>>(transactions, HttpStatus.OK);
    }
}
