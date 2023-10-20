package BadgeAndMembershipClient.clients;

import BadgeAndMembershipClient.common.IOUtil;
import contracts.dto.*;
import contracts.dto.auth.AuthLoginDto;
import contracts.dto.auth.AuthResponse;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class MemberClients {


    RestTemplate restTemplate = new RestTemplate();

    HttpHeaders headers;
    private String serverUrl = "http://localhost:8080";

    public void setup() {
        headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        AuthResponse authResponse = login();

        headers.set("Authorization", "Bearer " + authResponse.getToken());
        System.out.println(getActiveBadge(authResponse.getId()));

        List<MembershipDto> memberships = getMemberships(authResponse.getId());
        List<MembershipDto> checkerMemberships = checkerMemberships(memberships);

        if (checkerMemberships.size() < 1) {
            IOUtil.printExceptionMessage("You don't have checker membership");
            setup();
        }

        IOUtil.printTitle("Select any one plan");

        HashMap<String, String> planlist = getPlansFromMembership(checkerMemberships);
        String selectedPlan = IOUtil.getSelectedOption(planlist);

        //get location
        IOUtil.printTitle("Select any one location");

        HashMap<String, String> locations = getLocationsByPlanID(selectedPlan);

        if (locations.size() < 1) {
            IOUtil.printExceptionMessage("No location set up for this plan");
            setup();
        }

        String selectedLocation = IOUtil.getSelectedOption(locations);

        IOUtil.printTitle("Start scanning");
        startScanner(selectedPlan, selectedLocation);
    }

    public AuthResponse login() {

        IOUtil.printTitle("Please login to continue");

        String email = IOUtil.getInput("email");
        String password = IOUtil.getInput("password");

        AuthLoginDto authLoginDto = new AuthLoginDto(email, password);

        HttpEntity<AuthLoginDto> requestEntity = new HttpEntity<>(authLoginDto, headers);
        try {


            ResponseEntity<AuthResponse> responseEntity = restTemplate.postForEntity(serverUrl + "/login", requestEntity, AuthResponse.class);
            return responseEntity.getBody();

        } catch (Exception ex) {
            IOUtil.printExceptionMessage("Bad Credentials");
            setup();

        }
        return null;
    }

    public List<BadgeDto> getActiveBadge(Integer memberId) {

        IOUtil.printTitle("Here are your badges:");
        HttpEntity<Object> request = new HttpEntity<>(headers);
        BadgeDto[] response = restTemplate.exchange(serverUrl + "/members/"+memberId+"/active-badges"
                ,HttpMethod.GET
                ,request
                , BadgeDto[].class).getBody();
        return Arrays.asList(response);
    }

    public List<MembershipDto> getMemberships(Integer memberId) {

        HttpEntity<Object> request = new HttpEntity<>(headers);
        MembershipDto[] response = restTemplate.exchange(serverUrl + "/members/"+memberId+"/memberships"
                ,HttpMethod.GET
                ,request
                , MembershipDto[].class).getBody();
        return Arrays.asList(response);
    }

    public List<MembershipDto> checkerMemberships(List<MembershipDto> memberships) {
        List<MembershipDto> filteredMembership = memberships.stream()
                .filter(membership -> membership.getMembershipType().equals(MembershipType.CHECKER)).collect(Collectors.toList());
        return filteredMembership;

    }

    public HashMap<String, String> getPlansFromMembership(List<MembershipDto> memberships) {
        HashMap<String, String> planlist = new HashMap<>();
        memberships.stream().map(membershipDto -> membershipDto.getPlan())
                .collect(Collectors.toList())
                .forEach(p -> {
                    planlist.put(String.valueOf(p.getId()), p.getName());
                });
        return planlist;

    }

    public HashMap<String, String> getLocationsByPlanID(String planId) {

        HttpEntity<Object> request = new HttpEntity<>(headers);
        LocationDto[] response = restTemplate.exchange(serverUrl + "/plans/"+planId+"/locations"
                ,HttpMethod.GET
                ,request
                , LocationDto[].class).getBody();
        HashMap<String, String> locations = new HashMap<>();
        Arrays.asList(response)
                .forEach(location -> locations.put(String.valueOf(location.getId()), location.getName()));
        return locations;
    }

    public void startScanner(String selectedPlan, String selectedLocation) {
        while (true) {
            String badgeUid = IOUtil.getInput("Enter badge uid:");
            if (badgeUid.equals("stop")) {
                IOUtil.printTitle("Stopping scanner");
                setup();
            }

            TransactionRequestDto transactionRequestDto = new TransactionRequestDto(badgeUid, Integer.parseInt(selectedPlan), Integer.parseInt(selectedLocation));

            HttpEntity<TransactionRequestDto> requestEntity = new HttpEntity<>(transactionRequestDto, headers);

           try{
//               HttpEntity<Object> request = new HttpEntity<>(headers);
               TransactionDto response = restTemplate.exchange(serverUrl + "/transactions"
                       ,HttpMethod.POST
                       ,requestEntity
                       , TransactionDto.class).getBody();

//               ResponseEntity<TransactionDto> responseEntity = restTemplate.postForEntity(serverUrl + "/transactions", requestEntity, TransactionDto.class);
//               TransactionDto response = responseEntity.getBody();
               IOUtil.printMessage(response.getStatus() + " - " + response.getRemark());

           }
           catch(Exception ex){
                IOUtil.printExceptionMessage("Invalid badge id");
           }

        }
    }

}
