package dev.Zerphyis.gestao.Service;

import dev.Zerphyis.gestao.Entity.Data.EletronicPoints.DataEletronicPoinsReponseExit;
import dev.Zerphyis.gestao.Entity.Data.EletronicPoints.DataEletronicPointEnty;
import dev.Zerphyis.gestao.Entity.Data.EletronicPoints.DataEletronicPointsReponseEntry;
import dev.Zerphyis.gestao.Entity.ElectronicPoint.EletronicPoint;
import dev.Zerphyis.gestao.Entity.Employee.Employee;
import dev.Zerphyis.gestao.Repositorys.RepositoryElotronicPoints;
import dev.Zerphyis.gestao.Repositorys.RepositoryEmployee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class ServiceEletronicPoint {
    @Autowired
    private RepositoryElotronicPoints eletronicPointRepository;
    @Autowired
    private RepositoryEmployee employeeRepository;

    @Transactional
    public DataEletronicPointsReponseEntry registerEntry(DataEletronicPointEnty dataEntry) {
        Employee employee = employeeRepository.findById(dataEntry.employeeId())
                .orElseThrow(() -> new IllegalArgumentException("Funcionário não encontrado"));

        EletronicPoint eletronicPoint = new EletronicPoint(employee);
        eletronicPointRepository.save(eletronicPoint);

        return new DataEletronicPointsReponseEntry(employee.getName(), eletronicPoint.getEntryTime());
    }

    @Transactional
    public void registerExit(Long eletronicPointId) {
        EletronicPoint eletronicPoint = eletronicPointRepository.findById(eletronicPointId)
                .orElseThrow(() -> new IllegalArgumentException("Registro de ponto não encontrado"));

        eletronicPoint.setExitTime(LocalDateTime.now());
        eletronicPointRepository.save(eletronicPoint);
    }

    public List<DataEletronicPoinsReponseExit> getAllPoints() {
        return eletronicPointRepository.findAll().stream()
                .map(point -> new DataEletronicPoinsReponseExit(
                        point.getEmployeeId().getName(),
                        point.getEntryTime(),
                        point.getExitTime()
                ))
                .toList();
    }
}
