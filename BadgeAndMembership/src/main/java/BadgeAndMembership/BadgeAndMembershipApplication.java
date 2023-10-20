package BadgeAndMembership;

import BadgeAndMembership.service.MemberService;
import BadgeAndMembership.service.MembershipService;
import contracts.dto.MembershipDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



import java.time.LocalDateTime;

@SpringBootApplication
public class BadgeAndMembershipApplication{

	@Autowired
	private MemberService memberService;

	public static void main(String[] args) {
		SpringApplication.run(BadgeAndMembershipApplication.class, args);
	}
}
