package entity;

import enums.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import entity.Usuario;

import java.util.List;


@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Filme {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_filme")
    private Integer id;

    private String nome;

    private Integer ano;

    private String classificacao;

    private String sinopse;

    private List<Usuario> usuarios;

    @Enumerated(EnumType.STRING)
    private Status status;
}
