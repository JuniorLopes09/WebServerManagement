package com.junior.wsm.service.implementation;

import com.junior.wsm.domain.Servidor;
import com.junior.wsm.repository.ServidorRepository;
import com.junior.wsm.service.ServidorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.net.InetAddress;
import java.util.Collection;
import java.util.Random;

import static com.junior.wsm.enumatarion.Status.ATIVO;
import static com.junior.wsm.enumatarion.Status.INATIVO;
import static java.lang.Boolean.TRUE;

@RequiredArgsConstructor
@Service
@Transactional
@Slf4j
public class ServidorServiceImpl implements ServidorService {

    private final ServidorRepository servidorRepository;

    @Override
    public Servidor create(Servidor servidor) {
        log.info("Salvando novo servidor '{}'", servidor.getNome());
        servidor.setUrlImagem(getImageUrl());
        return servidorRepository.save(servidor);
    }

    private String getImageUrl() {
        String[] imagesURL = {
                "https://cdn-icons-png.flaticon.com/512/2911/2911789.png",
                "https://cdn-icons-png.flaticon.com/512/3208/3208726.png",
                "https://cdn-icons-png.flaticon.com/512/1383/1383395.png",
                "https://cdn-icons-png.flaticon.com/512/3208/3208727.png",
                "https://cdn-icons-png.flaticon.com/512/1674/1674901.png",
                "https://cdn-icons-png.flaticon.com/512/188/188109.png",
                "https://www.flaticon.com/free-icon/server_148820",
                "https://cdn-icons-png.flaticon.com/512/2972/2972377.png",
                "https://cdn-icons-png.flaticon.com/512/900/900297.png",
                "https://cdn-icons-png.flaticon.com/512/4305/4305643.png",
                "https://cdn-icons-png.flaticon.com/512/3617/3617073.png"
        };
        return imagesURL[new Random().nextInt(imagesURL.length)];
    }

    @Override
    public Servidor ping(String enderecoIp) throws IOException {
        log.info("Estabelecendo conexão com IP: {}", enderecoIp);
        Servidor servidor = servidorRepository.findByEnderecoIp(enderecoIp);
        InetAddress address = InetAddress.getByName(enderecoIp);
        servidor.setStatus(address.isReachable(10000) ? ATIVO : INATIVO);
        servidorRepository.save(servidor);
        return servidor;
    }

    @Override
    public Collection<Servidor> list(int quantidade) {
        log.info("Listando os primeiros {} servidores", quantidade);
        return servidorRepository.findAll(PageRequest.of(0, quantidade)).toList();
    }

    @Override
    public Servidor get(Long id) {
        log.info("Carregando servidor com ID {}", id);
        return servidorRepository.findById(id).get();
    }

    @Override
    public Servidor update(Servidor servidor) {
        log.info("Atualizando servidor '{}'", servidor.getNome());
        return servidorRepository.save(servidor);
    }

    @Override
    public Boolean delete(Long id) {
        log.info("Excluindo servidor com ID '{}'", id);
        servidorRepository.deleteById(id);
        return TRUE;
    }
}
