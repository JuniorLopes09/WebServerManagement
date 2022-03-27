package com.junior.wsm.repository;

import com.junior.wsm.domain.Servidor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServidorRepository extends JpaRepository<Servidor, Long> {
    Servidor findByEnderecoIp(String enderecoIP);
}
