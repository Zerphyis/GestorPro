package dev.Zerphyis.gestao.Controllers;

import dev.Zerphyis.gestao.Entity.Data.Employee.DataEmployee;
import dev.Zerphyis.gestao.Entity.Data.Employee.DataResponseEmployee;
import dev.Zerphyis.gestao.Service.ServiceEmployee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/funcionarios")
public class EmployeeControler {
    @Autowired
    private ServiceEmployee serviceEmployee;


    @PostMapping
    public ResponseEntity<DataResponseEmployee> save(@RequestBody DataEmployee dataEmployee) {
        DataResponseEmployee response = serviceEmployee.save(dataEmployee);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


    @GetMapping
    public ResponseEntity<List<DataResponseEmployee>> findAll() {
        List<DataResponseEmployee> employees = serviceEmployee.findAll();
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DataResponseEmployee> findById(@PathVariable("id") Long id) {
        DataResponseEmployee response = serviceEmployee.findById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @PutMapping("/{id}")
    public ResponseEntity<DataResponseEmployee> update(@PathVariable("id") Long id, @RequestBody DataEmployee dataEmployee) {
        DataResponseEmployee response = serviceEmployee.update(id, dataEmployee);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        serviceEmployee.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
