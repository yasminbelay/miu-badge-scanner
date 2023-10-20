package BadgeAndMembership.service;

import BadgeAndMembership.model.Badge;
import BadgeAndMembership.model.Member;
import BadgeAndMembership.repository.BadgeRepository;
import BadgeAndMembership.repository.MemberRepository;
import contracts.dto.BadgeDto;
import contracts.dto.BadgeRequestDto;
import edu.miu.common.exception.ResourceNotFoundException;
import edu.miu.common.service.BaseReadServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Transactional
public class BadgeServiceImpl extends BaseReadServiceImpl<BadgeDto, Badge, Integer> implements BadgeService {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private BadgeRepository badgeRepository;
    @Autowired
    private MemberRepository memberRepository;

    @Override
    public void addBadge(BadgeRequestDto requestDto) throws ResourceNotFoundException {
        //TODO: check duplicate batchUid
        Member member = memberRepository.findById(requestDto.getMemberId()).orElse(null);
        if(member == null)
            throw new ResourceNotFoundException();
        Badge badge = modelMapper.map(requestDto, Badge.class);
        //deactivate previous badges
        deactivatePreviousBadgesForMember(member);
        //save new badge
        badge.setMember(member);
        badge.setCreatedDate(LocalDateTime.now());
        badge.setModifiedDate(LocalDateTime.now());
        badgeRepository.save(badge);
    }

    @Override
    public void updateBadge(int id, BadgeRequestDto requestDto) throws ResourceNotFoundException {
        Member member = memberRepository.findById(requestDto.getMemberId()).orElse(null);
        if(member == null)
            throw new ResourceNotFoundException();
        Badge badge = badgeRepository.findById(id).orElse(null);
        if(badge == null) {
            throw new ResourceNotFoundException();
        }
        badge.setBadgeUid(requestDto.getBadgeUid());
        badge.setIsActive(requestDto.getIsActive());
        badge.setMember(member);
        badge.setModifiedDate(LocalDateTime.now());
        badgeRepository.save(badge);
    }

    @Override
    public void deleteBadge(Integer id) {
        badgeRepository.deleteById(id);
    }

    private void deactivatePreviousBadgesForMember(Member member){
        badgeRepository.updateBadgeStatusByMemberId(member.getId(), false, LocalDateTime.now());
    }
}
