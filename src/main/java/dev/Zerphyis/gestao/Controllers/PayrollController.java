package dev.Zerphyis.gestao.Controllers;

import dev.Zerphyis.gestao.Entity.Data.Payroll.DataPayrolEntry;
import dev.Zerphyis.gestao.Entity.Data.Payroll.DataPayrolExit;
import dev.Zerphyis.gestao.Entity.Payroll.Payroll;
import dev.Zerphyis.gestao.Service.ServicePayroll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pagamento")
public class PayrollController {

        @Autowired
        private ServicePayroll servicePayroll;

        @PostMapping
        public ResponseEntity<Payroll> createPayroll(@RequestBody Payroll payroll) {
            Payroll createdPayroll = servicePayroll.createPayroll(payroll);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdPayroll);
        }

        @GetMapping("/{id}")
        public ResponseEntity<Payroll> getPayrollById(@PathVariable Long id) {
            return servicePayroll.getPayrollById(id)
                    .map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());
        }

        @GetMapping
        public ResponseEntity<List<Payroll>> getAllPayrolls() {
            List<Payroll> payrolls = servicePayroll.getAllPayrolls();
            return ResponseEntity.ok(payrolls);
        }

        @PutMapping("/{id}")
        public ResponseEntity<Payroll> updatePayroll(@PathVariable("id") Long id, @RequestBody Payroll payroll) {
            Payroll updatedPayroll = servicePayroll.updatePayroll(id, payroll);
            return updatedPayroll != null ? ResponseEntity.ok(updatedPayroll) : ResponseEntity.notFound().build();
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<Void> deletePayroll(@PathVariable("id") Long id) {
            return servicePayroll.deletePayroll(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
        }

        @PostMapping("/calcular")
        public ResponseEntity<DataPayrolExit> calculateEmployeeWage(@RequestBody DataPayrolEntry entry) {
            BigDecimal wage = servicePayroll.calculateEmployeeWage(entry.employeeId());
            Optional<Payroll> payroll = servicePayroll.getPayrollById(entry.employeeId());

            if (payroll.isPresent()) {
                DataPayrolExit response = new DataPayrolExit(payroll.get().getDiscount(), wage);
                return ResponseEntity.ok(response);
            }
            return ResponseEntity.notFound().build();
        }
    }


