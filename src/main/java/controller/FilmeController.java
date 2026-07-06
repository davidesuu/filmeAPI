package controller;

import dto.FilmeDTO;
import entity.Filme;
import enums.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import service.FilmeService;

import java.util.List;

@RestController
@RequestMapping("/api/filmes")
public class FilmeController {

    @Autowired
    private FilmeService filmeService;

    @GetMapping
    public List<Filme> listarFilmes() {
        return filmeService.listarTodosFilmes();
    }

    @GetMapping("/{id}")
    public Filme buscarFilme(@PathVariable Integer id) {
        return filmeService.buscarPorId(id);
    }

    @GetMapping("/status")
    public List<Filme> listarPorStatus(@RequestParam Status status) {
        return filmeService.listarFilmes(status);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Filme criarFilme(@RequestBody FilmeDTO filmeDTO) {
        return filmeService.salvarFilme(filmeDTO);
    }

    @PutMapping("/{id}")
    public Filme atualizarFilme(
            @PathVariable Integer id,
            @RequestBody FilmeDTO filmeDTO) {
        return filmeService.atualizarFilme(id, filmeDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarFilme(@PathVariable Integer id) {
        filmeService.deletarFilme(id);
    }
}