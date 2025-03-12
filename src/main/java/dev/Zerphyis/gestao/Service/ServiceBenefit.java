package dev.Zerphyis.gestao.Service;

import dev.Zerphyis.gestao.Entity.Benefit.Benefit;
import dev.Zerphyis.gestao.Entity.Data.DataBenefit;
import dev.Zerphyis.gestao.Repositorys.RepositorysBenefit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceBenefit {
    @Autowired
    RepositorysBenefit repository;



    public Benefit registerBenefit(DataBenefit data) {
        var benefit = new Benefit(data);
        return repository.save(benefit);
    }

    public Benefit atualizationBenefit(Long id, DataBenefit data) {
        Optional<Benefit> optional = repository.findById(id);
        if (optional.isPresent()) {
            Benefit attBenefit = optional.get();
            attBenefit.setBenefitValue(data.benefitValue());
            attBenefit.setNameBenefit(data.nameBenefit());
            return repository.save(attBenefit);
        } else {
            throw new RuntimeException("Beneficio não encontrado com o ID: " + id);
        }
    }

    public void deleteBenefit(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new RuntimeException("Beneficio  não encontrado com o ID: " + id);
        }
    }

    public List<Benefit> ListAll() {
        return repository.findAll();
    }

    public Optional<Benefit> listByid(Long id) {
        return repository.findById(id);
    }

}


