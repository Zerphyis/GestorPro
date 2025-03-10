package dev.Zerphyis.gestao.Service;

import dev.Zerphyis.gestao.Entity.Employee.Employee;
import dev.Zerphyis.gestao.Entity.Payroll.Payroll;
import dev.Zerphyis.gestao.Repositorys.RepositoryEmployee;
import dev.Zerphyis.gestao.Repositorys.RepositoryPayroll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class ServicePayroll {
        @Autowired
        private RepositoryPayroll payrollRepository;

        @Autowired
        private RepositoryEmployee employeeRepository;

        public Payroll createPayroll(Payroll payroll) {
            payroll.calculateWage();
            return payrollRepository.save(payroll);
        }

        public Optional<Payroll> getPayrollById(Long id) {
            return payrollRepository.findById(id);
        }

        public List<Payroll> getAllPayrolls() {
            return payrollRepository.findAll();
        }

        public Payroll updatePayroll(Long id, Payroll updatedPayroll) {
            if (payrollRepository.existsById(id)) {
                updatedPayroll.calculateWage();
                return payrollRepository.save(updatedPayroll);
            }
            return null;
        }

        public boolean deletePayroll(Long id) {
            if (payrollRepository.existsById(id)) {
                payrollRepository.deleteById(id);
                return true;
            }
            return false;
        }

        public BigDecimal calculateEmployeeWage(Long employeeId) {
            Optional<Employee> employee = employeeRepository.findById(employeeId);

            if (employee.isPresent()) {
                Payroll payroll = payrollRepository.findByEmployeeId(employeeId);

                if (payroll != null) {
                    payroll.calculateWage();
                    payrollRepository.save(payroll);
                    return payroll.getWage();
                }
            }
            return BigDecimal.ZERO;
        }
    }


