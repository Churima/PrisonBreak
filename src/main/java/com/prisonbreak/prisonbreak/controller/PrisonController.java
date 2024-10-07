package com.prisonbreak.prisonbreak.controller;

import com.prisonbreak.prisonbreak.model.Crime;
import com.prisonbreak.prisonbreak.model.Prisoner;
import com.prisonbreak.prisonbreak.service.PrisonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        Prisoner prisoner = prisonService.getPrisonerById(id);
        return ResponseEntity.ok(prisoner);
    }

    @PostMapping
    public ResponseEntity<Prisoner> addPrisoner(@RequestBody Prisoner prisoner) {
        Prisoner newPrisoner = prisonService.addPrisoner(prisoner.getNome(), prisoner.getTempoPrisao(), prisoner.getLocal(), prisoner.getCrimes());
        return ResponseEntity.status(HttpStatus.CREATED).body(newPrisoner);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Prisoner> updatePrisoner(@PathVariable Long id, @RequestBody Prisoner prisoner) {
        Prisoner updatedPrisoner = prisonService.updatePrisoner(id, prisoner.getTempoPrisao(), prisoner.getLocal());
        return ResponseEntity.ok(updatedPrisoner);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePrisoner(@PathVariable Long id) {
        prisonService.deletePrisoner(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}/crimes")
    public ResponseEntity<List<Crime>> getCrimesByPrisoner(@PathVariable Long id) {
        List<Crime> crimes = prisonService.getCrimesByPrisoner(id);
        return ResponseEntity.ok(crimes);
    }

    @PostMapping("/{id}/crimes")
    public ResponseEntity<Prisoner> addCrimeToPrisoner(@PathVariable Long id, @RequestBody Crime crime) {
        Prisoner prisoner = prisonService.addCrimeToPrisoner(id, crime.getDescricao(), crime.getData());
        return ResponseEntity.ok(prisoner);
    }
}
