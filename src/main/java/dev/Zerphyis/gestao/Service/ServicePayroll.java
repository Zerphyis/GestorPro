package dev.Zerphyis.gestao.Service;

import dev.Zerphyis.gestao.Entity.Benefit.Benefit;
import dev.Zerphyis.gestao.Entity.Data.Payroll.DataPayrolEntry;
import dev.Zerphyis.gestao.Entity.Employee.Employee;
import dev.Zerphyis.gestao.Entity.Payroll.Payroll;
import dev.Zerphyis.gestao.Repositorys.RepositoryEmployee;
import dev.Zerphyis.gestao.Repositorys.RepositoryPayroll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ServicePayroll {
    @Autowired
    private RepositoryPayroll repositoryPayroll;

    @Autowired
    private RepositoryEmployee repositoryEmployee;

    public Payroll createPayroll(DataPayrolEntry entry) {
        Long employeeId = entry.employeeId();

        Employee employee = repositoryEmployee.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Funcionário não encontrado"));

        List<Benefit> benefits = employee.getBenefits();

        BigDecimal totalBenefits = benefits.stream()
                .map(Benefit::getBenefitValue)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal discount = calculateDiscount(totalBenefits, benefits.size());

        BigDecimal finalWage = employee.getWage().add(totalBenefits).subtract(discount);

        Payroll payroll = new Payroll(employee, employee.getWage(), discount, benefits);
        payroll.setWage(finalWage);
        return repositoryPayroll.save(payroll);
    }

    public Payroll getPayroll(Long id) {
        return repositoryPayroll.findById(id)
                .orElseThrow(() -> new RuntimeException("Folha de pagamento não encontrada"));
    }

    public Payroll updatePayroll(Long id, DataPayrolEntry entry) {
        Payroll payroll = repositoryPayroll.findById(id)
                .orElseThrow(() -> new RuntimeException("Folha de pagamento não encontrada"));

        Long employeeId = entry.employeeId();

        Employee employee = repositoryEmployee.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Funcionário não encontrado"));

        List<Benefit> benefits = employee.getBenefits();

        BigDecimal totalBenefits = benefits.stream()
                .map(Benefit::getBenefitValue)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal discount = calculateDiscount(totalBenefits, benefits.size());

        BigDecimal finalWage = employee.getWage().add(totalBenefits).subtract(discount);

        payroll.setBaseSalary(employee.getWage());
        payroll.setDiscount(discount);
        payroll.setBenefit(benefits);
        payroll.setWage(finalWage);

        return repositoryPayroll.save(payroll);
    }

    public void deletePayroll(Long id) {
        Payroll payroll = repositoryPayroll.findById(id)
                .orElseThrow(() -> new RuntimeException("Folha de pagamento não encontrada"));

        repositoryPayroll.delete(payroll);
    }

    private BigDecimal calculateDiscount(BigDecimal totalBenefits, int benefitsCount) {
        BigDecimal discountPercentage = new BigDecimal("0.04").multiply(new BigDecimal(benefitsCount));
        return totalBenefits.multiply(discountPercentage);
    }
}



