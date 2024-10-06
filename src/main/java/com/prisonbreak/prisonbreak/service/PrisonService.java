package com.prisonbreak.prisonbreak.service;

import com.prisonbreak.prisonbreak.model.Crime;
import com.prisonbreak.prisonbreak.model.Prisoner;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PrisonService {

    private List<Prisoner> prisoners = new ArrayList<>();

    public List<Prisoner> getAllPrisoners() {
        return prisoners;
    }

    public Optional<Prisoner> getPrisonerById(Long id) {
        return prisoners.stream()
                .filter(prisoner -> prisoner.getId().equals(id))
                .findFirst();
    }

    public Prisoner addPrisoner(String nome, int tempoPrisao, String local, List<Crime> crimes) {
        Prisoner prisoner = new Prisoner();
        prisoner.setId((long) (prisoners.size() + 1)); // Gerar ID sequencial
        prisoner.setNome(nome);
        prisoner.setTempoPrisao(tempoPrisao);
        prisoner.setLocal(local);

        if (crimes != null) {
            for (Crime crime : crimes) {
                prisoner.adicionarCrime(crime);
            }
        }

        prisoners.add(prisoner);
        return prisoner;
    }

    public Optional<Prisoner> updatePrisoner(Long id, int tempoPrisao, String local) {
        Optional<Prisoner> prisonerOptional = prisoners.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst();

        if (prisonerOptional.isPresent()) {
            Prisoner prisoner = prisonerOptional.get();
            prisoner.setTempoPrisao(tempoPrisao); // Atualiza apenas o tempo de prisão
            if (local != null) {
                prisoner.setLocal(local); // Atualiza o local apenas se ele for enviado
            }
            return Optional.of(prisoner);
        }

        return Optional.empty();
    }

    public boolean deletePrisoner(Long id) {
        return prisoners.removeIf(prisoner -> prisoner.getId().equals(id));
    }

    public Optional<List<Crime>> getCrimesByPrisoner(Long id) {
        Optional<Prisoner> prisonerOptional = getPrisonerById(id);
        return prisonerOptional.map(Prisoner::getCrimes);
    }

    public Optional<Prisoner> addCrimeToPrisoner(Long id, String descricao, LocalDate data) {
        Optional<Prisoner> prisonerOptional = getPrisonerById(id);

        if (prisonerOptional.isPresent()) {
            Prisoner prisoner = prisonerOptional.get();
            Crime newCrime = new Crime(descricao, data);
            prisoner.adicionarCrime(newCrime);
            return Optional.of(prisoner);
        }

        return Optional.empty();
    }
}
