package com.prisonbreak.prisonbreak.service;

import com.prisonbreak.prisonbreak.exception.PrisonerNotFoundException;
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

    public Prisoner getPrisonerById(Long id) {
        return prisoners.stream()
                .filter(prisoner -> prisoner.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new PrisonerNotFoundException("Prisioneiro com ID " + id + " não encontrado."));
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

    public Prisoner updatePrisoner(Long id, int tempoPrisao, String local) {
        Prisoner prisoner = getPrisonerById(id);
        prisoner.setTempoPrisao(tempoPrisao);
        if (local != null) {
            prisoner.setLocal(local); // Atualiza o local apenas se ele for enviado
        }
        return prisoner;
    }

    public boolean deletePrisoner(Long id) {
        Prisoner prisoner = getPrisonerById(id);
        return prisoners.remove(prisoner);
    }

    public List<Crime> getCrimesByPrisoner(Long id) {
        Prisoner prisoner = getPrisonerById(id);
        return prisoner.getCrimes();
    }

    public Prisoner addCrimeToPrisoner(Long id, String descricao, LocalDate data) {
        Prisoner prisoner = getPrisonerById(id);
        Crime newCrime = new Crime(descricao, data);
        prisoner.adicionarCrime(newCrime);
        return prisoner;
    }
}
