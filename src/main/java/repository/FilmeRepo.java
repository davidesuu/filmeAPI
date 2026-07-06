package repository;

import entity.Filme;
import enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FilmeRepo extends JpaRepository<Filme, Integer> {
    List<Filme> findByStatus(Status status);
}
