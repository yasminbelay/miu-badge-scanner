package BadgeAndMembership.service;

import BadgeAndMembership.model.Member;
import BadgeAndMembership.model.Role;
import BadgeAndMembership.repository.MemberRepository;
import BadgeAndMembership.service.jwt.JwtService;
import contracts.dto.auth.AuthLoginDto;
import contracts.dto.auth.AuthRegisterDto;
import contracts.dto.auth.AuthResponse;
import edu.miu.common.service.BaseReadServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class AuthenticationServiceImpl extends BaseReadServiceImpl<AuthResponse, Member, Integer> implements AuthenticationService {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private JwtService jwtService;


    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public AuthResponse register(AuthRegisterDto authRegisterDto) {
        var roles = Arrays.asList(Role.builder().name("User").build(), Role.builder().name("Admin").build());

        //TODO Use model mapper
        var user = Member.builder()
                .firstName(authRegisterDto.getFirstName())
                .lastName(authRegisterDto.getLastName())
                .email(authRegisterDto.getEmail())
                .password(passwordEncoder.encode(authRegisterDto.getPassword()))
                .roles(roles)
                .build();
        memberRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthResponse.builder()
                .token(jwtToken)
                .build();
    }

    @Override
    public AuthResponse authenticate(AuthLoginDto authLoginDto) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authLoginDto.getEmail(),
                        authLoginDto.getPassword()
                )
        );
        var user = memberRepository.findByEmail(authLoginDto.getEmail())
                .orElseThrow();


        var jwtToken = jwtService.generateToken(user);

        return AuthResponse.builder()
                .token(jwtToken)
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .build();
    }
}
