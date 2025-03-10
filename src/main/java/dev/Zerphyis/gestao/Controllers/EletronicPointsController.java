package dev.Zerphyis.gestao.Controllers;

import dev.Zerphyis.gestao.Entity.Data.EletronicPoints.DataEletronicPoinsReponseExit;
import dev.Zerphyis.gestao.Entity.Data.EletronicPoints.DataEletronicPointEnty;
import dev.Zerphyis.gestao.Entity.Data.EletronicPoints.DataEletronicPointsReponseEntry;
import dev.Zerphyis.gestao.Service.ServiceEletronicPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/ponto")
public class EletronicPointsController {
    @Autowired
    private ServiceEletronicPoint serviceEletronicPoint;

    @PostMapping("/entrada")
    public ResponseEntity<DataEletronicPointsReponseEntry> registerEntry(@RequestBody DataEletronicPointEnty dataEntry) {
        DataEletronicPointsReponseEntry response = serviceEletronicPoint.registerEntry(dataEntry);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/saida/{eletronicPointId}")
    public ResponseEntity<Void> registerExit(@PathVariable("eletronicPointId") Long eletronicPointId) {
        serviceEletronicPoint.registerExit(eletronicPointId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping()
    public ResponseEntity<List<DataEletronicPoinsReponseExit>> getAllPoints() {
        List<DataEletronicPoinsReponseExit> points = serviceEletronicPoint.getAllPoints();
        return ResponseEntity.ok(points);
    }
}
