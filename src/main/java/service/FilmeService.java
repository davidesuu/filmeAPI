package service;

import dto.FilmeDTO;
import entity.Filme;
import enums.Status;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.FilmeRepo;

import java.util.List;

@Service
public class FilmeService {
    @Autowired
    private FilmeRepo filmeRepo;

    /**
     * Retorna uma lista de filmes com o Status informado no parametro
     * @param status status do filme
     * @return uma lista de filmes
     */
    @Transactional
    public List<Filme> listarFilmes(Status status){
        if(status == null){
            throw new RuntimeException("Status não informado");
        }
        return filmeRepo.findByStatus(status);
    }

    /**
     * Essa funçao cria um filme a partir de um FilmeDTO e salva no repositorio
     * @param filmeDTO Dto de filme
     * @return Retorna o filme salvo se sucesso
     */
    @Transactional
    public Filme salvarFilme(FilmeDTO filmeDTO){
        if(filmeDTO == null){
            throw new IllegalArgumentException("os campos do filme não foram informados");
        }
        if (filmeDTO.getAno() == null){
            throw new IllegalArgumentException("Ano invalido");
        }
        if (filmeDTO.getNome() == null || filmeDTO.getNome().isBlank()){
            throw new IllegalArgumentException("Nome invalido");
        }
        if(filmeDTO.getSinopse() == null || filmeDTO.getSinopse().isBlank()){
            throw new IllegalArgumentException("Sinopse invalido");
        }
        if(filmeDTO.getStatus() == null){
            throw new IllegalArgumentException("Status invalido");
        }
        if(filmeDTO.getClassificacao() == null || filmeDTO.getClassificacao().isBlank()){
            throw new IllegalArgumentException("Classificação invalida");
        }
        Filme filme = Filme.builder().nome(filmeDTO.getNome())
                .ano(filmeDTO.getAno())
                .classificacao(filmeDTO.getClassificacao())
                .sinopse(filmeDTO.getSinopse())
                .status(filmeDTO.getStatus())
                .build();
        return filmeRepo.save(filme);
    }

    /**
     * Essa função atualiza um filme informando um id de um filme existente
     * @param id
     * @param filmeDTO
     * @return
     */
    @Transactional
    public Filme atualizarFilme(Integer id,FilmeDTO filmeDTO){
        if(filmeDTO == null){
            throw new IllegalArgumentException("os campos do filme não foram informados");
        }
        if (filmeDTO.getAno() == null){
            throw new IllegalArgumentException("Ano invalido");
        }
        if (filmeDTO.getNome() == null || filmeDTO.getNome().isBlank()){
            throw new IllegalArgumentException("Nome invalido");
        }
        if(filmeDTO.getSinopse() == null || filmeDTO.getSinopse().isBlank()){
            throw new IllegalArgumentException("Sinopse invalido");
        }
        if(filmeDTO.getStatus() == null){
            throw new IllegalArgumentException("Status invalido");
        }
        if(filmeDTO.getClassificacao() == null || filmeDTO.getClassificacao().isBlank()){
            throw new IllegalArgumentException("Classificação invalida");
        }
        Filme filme = buscarPorId(id);
        filme.setAno(filmeDTO.getAno());
        filme.setNome(filmeDTO.getNome());
        filme.setStatus(filmeDTO.getStatus());
        filme.setClassificacao(filmeDTO.getClassificacao());
        filme.setSinopse(filmeDTO.getSinopse());
        return filmeRepo.save(filme);
    }

    /**
     * Essa função deleta um filme informando um id
     * @param id id do filme a ser deletado
     */
    @Transactional
    public void deletarFilme(Integer id){
        Filme filme = buscarPorId(id);
        filmeRepo.delete(filme);
    }

    /**
     * Retorna todos os filmes do repositorio
     * @return retorna uma lista de filmes
     */
    public List<Filme> listarTodosFilmes(){
        return filmeRepo.findAll();
    }

    /**
     * Essa função busca um filme informando uma id
     * @param id Id do filme procurado
     * @return retorna o filme se achar, lança excessao se nao
     */
    public Filme buscarPorId(Integer id){
        return filmeRepo.findById(id).orElseThrow(() -> {
            throw new IllegalArgumentException("Filme não encontrado");
        });
    }

}
