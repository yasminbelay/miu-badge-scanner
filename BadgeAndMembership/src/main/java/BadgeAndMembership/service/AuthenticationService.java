package BadgeAndMembership.service;

import BadgeAndMembership.model.Member;
import contracts.dto.auth.AuthLoginDto;
import contracts.dto.auth.AuthRegisterDto;
import contracts.dto.auth.AuthResponse;
import edu.miu.common.service.BaseReadService;
import org.springframework.stereotype.Service;

@Service
public interface AuthenticationService extends BaseReadService<AuthResponse, Member, Integer> {
    AuthResponse register(AuthRegisterDto authRegisterDto);
    AuthResponse authenticate(AuthLoginDto authLoginDto);
}
