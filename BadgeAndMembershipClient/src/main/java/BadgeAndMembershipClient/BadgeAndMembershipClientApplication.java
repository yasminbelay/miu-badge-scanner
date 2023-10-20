package BadgeAndMembershipClient;

import BadgeAndMembershipClient.clients.MemberClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BadgeAndMembershipClientApplication implements CommandLineRunner {

    @Autowired
    MemberClients memberClients;
    public static void main(String[] args) {
        SpringApplication.run(BadgeAndMembershipClientApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        memberClients.setup();
    }
}
