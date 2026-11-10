package ma.ikram.servicevoiture;

import ma.ikram.servicevoiture.entities.Voiture;
import ma.ikram.servicevoiture.repositories.VoitureRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication
public class VoitureApplication {

    public static void main(String[] args) {
        SpringApplication.run(VoitureApplication.class, args);
    }

    @Bean
    CommandLineRunner initH2(VoitureRepository voitureRepository) {
        return args -> {
            voitureRepository.save(new Voiture(null, "Mercedes", "C 10 123", "Classe A", 1L, null));
            voitureRepository.save(new Voiture(null, "Volkswagen", "D 20 456", "Golf 8", 1L, null));
            voitureRepository.save(new Voiture(null, "Audi", "E 30 789", "Q3", 2L, null));
        };
    }
}
