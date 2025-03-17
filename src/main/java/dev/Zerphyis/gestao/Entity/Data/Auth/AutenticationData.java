package dev.Zerphyis.gestao.Entity.Data.Auth;

import dev.Zerphyis.gestao.Entity.Login.TypeRole;

public record AutenticationData(String email, String password, TypeRole role) {
}
