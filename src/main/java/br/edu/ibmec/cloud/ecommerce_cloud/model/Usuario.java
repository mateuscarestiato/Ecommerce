package br.edu.ibmec.cloud.ecommerce_cloud.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity(name = "usuario")
public class Usuario {

    public Usuario() {
        this.cartoes = new ArrayList<>();
        this.enderecos = new ArrayList<>();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String nome;

    @Column
    private String email;

    @Column
    private LocalDateTime dtNascimento;

    @Column
    private String cpf;

    @Column
    private String telefone;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(referencedColumnName = "id", name = "id_usuario")
    private List<Cartao> cartoes;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(referencedColumnName = "id", name = "id_usuario")
    private List<Endereco> enderecos;


}
