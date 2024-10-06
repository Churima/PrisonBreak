package com.prisonbreak.prisonbreak.controller;

import com.prisonbreak.prisonbreak.model.Crime;
import com.prisonbreak.prisonbreak.model.Prisoner;
import com.prisonbreak.prisonbreak.service.PrisonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/presos")
public class PrisonController {

    @Autowired
    private PrisonService prisonService;

    @GetMapping
    public ResponseEntity<List<Prisoner>> getAllPrisoners() {
        return ResponseEntity.ok(prisonService.getAllPrisoners());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Prisoner> getPrisonerById(@PathVariable Long id) {
        Optional<Prisoner> prisoner = prisonService.getPrisonerById(id);
        return prisoner.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping
    public ResponseEntity<Prisoner> addPrisoner(@RequestBody Prisoner prisoner) {
        Prisoner newPrisoner = prisonService.addPrisoner(prisoner.getNome(), prisoner.getTempoPrisao(), prisoner.getLocal(), prisoner.getCrimes());
        return ResponseEntity.status(HttpStatus.CREATED).body(newPrisoner);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Prisoner> updatePrisoner(@PathVariable Long id, @RequestBody Prisoner prisoner) {
        Optional<Prisoner> updatedPrisoner = prisonService.updatePrisoner(id, prisoner.getTempoPrisao(), prisoner.getLocal());
        return updatedPrisoner.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePrisoner(@PathVariable Long id) {
        boolean deleted = prisonService.deletePrisoner(id);
        return deleted ? ResponseEntity.ok().build() : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping("/{id}/crimes")
    public ResponseEntity<List<Crime>> getCrimesByPrisoner(@PathVariable Long id) {
        Optional<List<Crime>> crimes = prisonService.getCrimesByPrisoner(id);
        return crimes.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping("/{id}/crimes")
    public ResponseEntity<Prisoner> addCrimeToPrisoner(@PathVariable Long id, @RequestBody Crime crime) {
        Optional<Prisoner> prisoner = prisonService.addCrimeToPrisoner(id, crime.getDescricao(), crime.getData());
        return prisoner.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
}
