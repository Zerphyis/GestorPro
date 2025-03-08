package dev.Zerphyis.gestao.Entity.Position;

import dev.Zerphyis.gestao.Entity.Data.DataPosition;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "tb_Cargos")
public class Position {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String name;
    @NotBlank
    private String description;

    public Position(){

    }
    public Position(DataPosition data ){
        this.name=data.name();
        this.description= data.description();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
