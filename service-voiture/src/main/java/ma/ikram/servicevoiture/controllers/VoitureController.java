package ma.ikram.servicevoiture.controllers;

import ma.ikram.servicevoiture.entities.Client;
import ma.ikram.servicevoiture.entities.Voiture;
import ma.ikram.servicevoiture.repositories.VoitureRepository;
import ma.ikram.servicevoiture.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class VoitureController {

    @Autowired
    private VoitureRepository voitureRepository;

    @Autowired(required = false)
    private ClientService clientService;

    @GetMapping(value = "/voitures", produces = {"application/json"})
    public ResponseEntity<List<Voiture>> findAll() {
        List<Voiture> voitures = voitureRepository.findAll();
        if (clientService != null) {
            for (Voiture v : voitures) {
                if (v.getClientId() != null) {
                    try {
                        v.setClient(clientService.clientById(v.getClientId()));
                    } catch (Exception ignored) {}
                }
            }
        }
        return ResponseEntity.ok(voitures);
    }

    @GetMapping("/voitures/{id}")
    public ResponseEntity<Object> findById(@PathVariable Long id) {
        return voitureRepository.findById(id)
                .map(v -> {
                    if (clientService != null && v.getClientId() != null) {
                        try {
                            v.setClient(clientService.clientById(v.getClientId()));
                        } catch (Exception ignored) {}
                    }
                    return ResponseEntity.ok((Object) v);
                })
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("Voiture non trouvée ID: " + id));
    }

    @GetMapping("/voitures/client/{id}")
    public ResponseEntity<List<Voiture>> findByClient(@PathVariable Long id) {
        List<Voiture> list = voitureRepository.findByClientId(id);
        if (clientService != null) {
            try {
                Client c = clientService.clientById(id);
                for (Voiture v : list) v.setClient(c);
            } catch (Exception ignored) {}
        }
        return ResponseEntity.ok(list);
    }
}
