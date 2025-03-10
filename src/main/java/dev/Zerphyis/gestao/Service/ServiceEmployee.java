package dev.Zerphyis.gestao.Service;

import dev.Zerphyis.gestao.Entity.Benefit.Benefit;
import dev.Zerphyis.gestao.Entity.Data.Employee.DataEmployee;
import dev.Zerphyis.gestao.Entity.Data.Employee.DataResponseEmployee;
import dev.Zerphyis.gestao.Entity.Employee.Employee;
import dev.Zerphyis.gestao.Entity.Position.Position;
import dev.Zerphyis.gestao.Repositorys.RepositoryEmployee;
import dev.Zerphyis.gestao.Repositorys.RepositoryPosition;
import dev.Zerphyis.gestao.Repositorys.RepositorysBenefit;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ServiceEmployee {
    @Autowired
    private RepositoryPosition positionRepository;
    @Autowired
    private RepositorysBenefit benefitRepository;
    @Autowired
    private RepositoryEmployee employeeRepository;


    @Transactional
    public DataResponseEmployee save(DataEmployee dto) {
        Position position = positionRepository.findById(dto.positionId())
                .orElseThrow(() -> new EntityNotFoundException("Cargo não encontrado"));

        List<Benefit> benefits = benefitRepository.findAllById(dto.benefitsIds());


        Employee employee = new Employee(dto, position, benefits);

        Employee savedEmployee = employeeRepository.save(employee);
        return new DataResponseEmployee(
                savedEmployee.getId(),
                savedEmployee.getName(),
                savedEmployee.getDocument(),
                savedEmployee.getEmail(),
                savedEmployee.getPhone(),
                savedEmployee.getDateAdmission(),
                savedEmployee.getWage(),
                savedEmployee.getPosition().getName(),
                savedEmployee.getBenefits().stream()
                        .map(Benefit::getNameBenefit)
                        .collect(Collectors.toList())
        );
    }

    @Transactional(readOnly = true)
    public List<DataResponseEmployee> findAll() {
        return employeeRepository.findAll().stream().map(employee ->
                new DataResponseEmployee(
                        employee.getId(),
                        employee.getName(),
                        employee.getDocument(),
                        employee.getEmail(),
                        employee.getPhone(),
                        employee.getDateAdmission(),
                        employee.getWage(),
                        employee.getPosition().getName(),
                        employee.getBenefits().stream()
                                .map(Benefit::getNameBenefit)
                                .collect(Collectors.toList())
                )
        ).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public DataResponseEmployee findById(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Funcionário não encontrado"));

        return new DataResponseEmployee(
                employee.getId(),
                employee.getName(),
                employee.getDocument(),
                employee.getEmail(),
                employee.getPhone(),
                employee.getDateAdmission(),
                employee.getWage(),
                employee.getPosition().getName(),
                employee.getBenefits().stream()
                        .map(Benefit::getNameBenefit)
                        .collect(Collectors.toList())
        );
    }

    @Transactional
    public DataResponseEmployee update(Long id, DataEmployee dto) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Funcionário não encontrado"));

        Position position = positionRepository.findById(dto.positionId())
                .orElseThrow(() -> new EntityNotFoundException("Cargo não encontrado"));

        List<Benefit> benefits = benefitRepository.findAllById(dto.benefitsIds());

        employee.setName(dto.name());
        employee.setDocument(dto.document());
        employee.setEmail(dto.email());
        employee.setPhone(dto.phone());
        employee.setDateAdmission(dto.dateAdmission());
        employee.setWage(dto.wage());
        employee.setPosition(position);
        employee.setBenefits(benefits);

        Employee updatedEmployee = employeeRepository.save(employee);

        return new DataResponseEmployee(
                updatedEmployee.getId(),
                updatedEmployee.getName(),
                updatedEmployee.getDocument(),
                updatedEmployee.getEmail(),
                updatedEmployee.getPhone(),
                updatedEmployee.getDateAdmission(),
                updatedEmployee.getWage(),
                updatedEmployee.getPosition().getName(),
                updatedEmployee.getBenefits().stream()
                        .map(Benefit::getNameBenefit)
                        .collect(Collectors.toList())
        );
    }

    @Transactional
    public void delete(Long id) {
        if (!employeeRepository.existsById(id)) {
            throw new EntityNotFoundException("Funcionário não encontrado");
        }
        employeeRepository.deleteById(id);
    }
}

