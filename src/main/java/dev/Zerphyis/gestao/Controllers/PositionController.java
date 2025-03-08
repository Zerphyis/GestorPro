package dev.Zerphyis.gestao.Controllers;

import dev.Zerphyis.gestao.Entity.Data.DataPosition;
import dev.Zerphyis.gestao.Entity.Position.Position;
import dev.Zerphyis.gestao.Service.ServicePosition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cargos")
public class PositionController {
    @Autowired
    ServicePosition service;

    @PostMapping()
    public ResponseEntity<Position> addPosition(@RequestBody DataPosition data){
      Position position =service.registerPosition(data);
        return ResponseEntity.status(HttpStatus.CREATED).body(position);
    }

    @PutMapping("{id}")
    public ResponseEntity<Position> attPosition(@PathVariable("id")Long id,@RequestBody DataPosition data){
        Position position =service.atualizationPosition(id,data);
        return ResponseEntity.ok(position);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteBenefit(@PathVariable("id")Long id){
        service.deletePosition(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping()
    public ResponseEntity<List<Position>> listAllpositions() {
        List<Position> positions = service.ListAll();
        return ResponseEntity.ok(positions);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Position> getById(@PathVariable("id") Long id) {
        return service.listByid(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


}
