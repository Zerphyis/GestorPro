package dev.Zerphyis.gestao.Service;


import dev.Zerphyis.gestao.Entity.Data.DataPosition;
import dev.Zerphyis.gestao.Entity.Position.Position;
import dev.Zerphyis.gestao.Repositorys.RepositoryPosition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServicePosition {
    @Autowired
    RepositoryPosition repository;


    public Position registerPosition(DataPosition data) {
        var position = new Position(data);
        return repository.save(position);
    }

    public Position atualizationPosition(Long id, DataPosition data) {
        Optional<Position> optional = repository.findById(id);
        if (optional.isPresent()) {
            Position attPosition = optional.get();
            attPosition.setName(data.name());
            attPosition.setDescription(data.description());

            return repository.save(attPosition);
        } else {
            throw new RuntimeException("Cargo não encontrado com o ID: " + id);
        }
    }

    public void deletePosition(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new RuntimeException("Cargo  não encontrado com o ID: " + id);
        }
    }

    public List<Position> ListAll() {
        return repository.findAll();
    }

    public Optional<Position> listByid(Long id) {
        return repository.findById(id);
    }
}
