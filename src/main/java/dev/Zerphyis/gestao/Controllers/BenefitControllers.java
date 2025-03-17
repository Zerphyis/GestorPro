package dev.Zerphyis.gestao.Controllers;

import dev.Zerphyis.gestao.Entity.Benefit.Benefit;
import dev.Zerphyis.gestao.Entity.Data.Benefit.DataBenefit;
import dev.Zerphyis.gestao.Entity.Data.Benefit.ResponseBenefit;
import dev.Zerphyis.gestao.Service.ServiceBenefit;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("beneficios")
@RestController
public class BenefitControllers {
    @Autowired
    ServiceBenefit service;

    @PostMapping()
    public ResponseEntity<Benefit> registerBenefit(@RequestBody DataBenefit data) {
        Benefit benefit = service.registerBenefit(data);
        return ResponseEntity.status(HttpStatus.CREATED).body(benefit);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Benefit> updateBenefit(@PathVariable("id") Long id, @RequestBody DataBenefit data) {
        Benefit updatedBenefit = service.atualizationBenefit(id, data);
        return ResponseEntity.ok(updatedBenefit);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBenefit(@PathVariable("id")  Long id) {
        service.deleteBenefit(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping()
    public ResponseEntity<List<ResponseBenefit>> listAllBenefits() {
        List<Benefit> benefits = service.ListAll();

        List<ResponseBenefit> response = benefits.stream()
                .map(b -> new ResponseBenefit(b.getNameBenefit(), b.getBenefitValue()))
                .toList();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Benefit> getById(@PathVariable("id") Long id) {
        return service.listByid(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
