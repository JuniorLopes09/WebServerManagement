package com.junior.wsm.service;

import com.junior.wsm.domain.Servidor;

import java.io.IOException;
import java.util.Collection;

public interface ServidorService {
    Servidor create(Servidor servidor);
    Servidor ping(String enderecoIp) throws IOException;
    Collection<Servidor> list(int quantidade);
    Servidor get(Long id);
    Servidor update(Servidor servidor);
    Boolean delete(Long id);
}
