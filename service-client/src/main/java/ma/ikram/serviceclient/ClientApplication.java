package ma.ikram.serviceclient;

import ma.ikram.serviceclient.entities.Client;
import ma.ikram.serviceclient.repositories.ClientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

@EnableDiscoveryClient
@SpringBootApplication
public class ClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClientApplication.class, args);
    }

    @Bean
    CommandLineRunner initH2(ClientRepository clientRepository) {
        return args -> {
            clientRepository.save(new Client(null, "Fatima Ezzahra", 22f));
            clientRepository.save(new Client(null, "Mehdi Chraibi", 31f));
            clientRepository.save(new Client(null, "Nouhaila Tazi", 26f));
        };
    }
}
