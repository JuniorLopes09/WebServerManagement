package com.junior.wsm.domain;

import com.junior.wsm.enumatarion.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Servidor {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true) @NotEmpty(message = "O Endereço IP não pode estar vazio")
    private String enderecoIp;
    private String nome;
    private String memoria;
    private String tipo;
    private String urlImagem;
    private Status status;
}
