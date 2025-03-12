package dev.Zerphyis.gestao.Controllers;

import dev.Zerphyis.gestao.Entity.Data.Payroll.DataPayrolEntry;
import dev.Zerphyis.gestao.Entity.Data.Payroll.DataPayrolExit;
import dev.Zerphyis.gestao.Entity.Payroll.Payroll;
import dev.Zerphyis.gestao.Service.ServicePayroll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/pagamento")
public class PayrollController {

    @Autowired
    private ServicePayroll servicePayroll;

    @PostMapping
    public ResponseEntity<DataPayrolExit> createPayroll(@RequestBody DataPayrolEntry entry) {
        Payroll payroll = servicePayroll.createPayroll(entry);
        DataPayrolExit responseDTO = new DataPayrolExit(payroll.getBaseSalary(), payroll.getDiscount(), payroll.getWage());
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DataPayrolExit> getPayroll(@PathVariable Long id) {
        Payroll payroll = servicePayroll.getPayroll(id);
        DataPayrolExit responseDTO = new DataPayrolExit(payroll.getBaseSalary(), payroll.getDiscount(), payroll.getWage());
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DataPayrolExit> updatePayroll(@PathVariable Long id, @RequestBody DataPayrolEntry entry) {
        Payroll payroll = servicePayroll.updatePayroll(id, entry);
        DataPayrolExit responseDTO = new DataPayrolExit(payroll.getBaseSalary(), payroll.getDiscount(), payroll.getWage());
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePayroll(@PathVariable Long id) {
        servicePayroll.deletePayroll(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}


