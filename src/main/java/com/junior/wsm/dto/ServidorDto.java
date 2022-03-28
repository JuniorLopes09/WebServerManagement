package com.junior.wsm.dto;


import com.junior.wsm.domain.Servidor;
import com.junior.wsm.service.IpAddressConstraint;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
public class ServidorDto {
    @NotEmpty(message = "O Endereço IP não pode estar vazio") @IpAddressConstraint
    private String enderecoIp;
    private String nome;
    private String memoria;
    private String tipo;

    public Servidor toServidor(){
        Servidor servidor = new Servidor();
        servidor.setEnderecoIp(this.enderecoIp);
        servidor.setNome(this.nome);
        servidor.setMemoria(this.memoria);
        servidor.setTipo(this.tipo);

        return servidor;
    }
}
