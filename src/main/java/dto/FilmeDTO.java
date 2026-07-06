package dto;

import enums.Status;
import jakarta.persistence.*;
import lombok.Data;

@Data
public class FilmeDTO {
    private String nome;
    private Integer ano;
    private String classificacao;
    private String sinopse;
    private Status status;
}
