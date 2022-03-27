package com.junior.wsm.resource;

import com.junior.wsm.domain.RespostaHTTP;
import com.junior.wsm.domain.Servidor;
import com.junior.wsm.enumatarion.Status;
import com.junior.wsm.service.implementation.ServidorServiceImpl;
import com.sun.javafx.collections.MappingChange;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.io.IOException;
import java.util.Collections;
import java.util.Map;

import static com.junior.wsm.enumatarion.Status.ATIVO;
import static java.time.LocalDateTime.*;
import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/servidor")
@RequiredArgsConstructor
public class ServidorResource {
    private final ServidorServiceImpl servidorService;

    @GetMapping("/list")
    public ResponseEntity<RespostaHTTP> getServidores(@RequestParam(required = false, defaultValue = "30") String qtd){
        return ResponseEntity.ok(
                RespostaHTTP.builder()
                        .timeStamp(now())
                        .dados(Collections.singletonMap("servidores", servidorService.list(Integer.parseInt(qtd))))
                        .status(OK)
                        .mensagem("Servidores carregados")
                        .statusCode(OK.value())
                        .build()
        );
    }

    @GetMapping("/ping/{enderecoIp}")
    public ResponseEntity<RespostaHTTP> pingServidor(@PathVariable String enderecoIp) throws IOException {
        Servidor servidor = servidorService.ping(enderecoIp);
        return ResponseEntity.ok(
                RespostaHTTP.builder()
                        .timeStamp(now())
                        .dados(Collections.singletonMap("servidor", servidor))
                        .status(OK)
                        .mensagem(servidor.getStatus() == ATIVO ? "Conexão realizada com sucesso" : "Falha ao estabelecer conexão")
                        .statusCode(OK.value())
                        .build()
        );
    }

    @PostMapping("/save")
    public ResponseEntity<RespostaHTTP> pingServidor(@RequestBody @Valid Servidor servidor){
        return ResponseEntity.ok(
                RespostaHTTP.builder()
                        .timeStamp(now())
                        .dados(Collections.singletonMap("servidor", servidorService.create(servidor)))
                        .status(CREATED)
                        .mensagem("Servidor criado com sucesso")
                        .statusCode(CREATED.value())
                        .build()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<RespostaHTTP> getServidor(@PathVariable Long id){

        return ResponseEntity.ok(
                RespostaHTTP.builder()
                        .timeStamp(now())
                        .dados(Collections.singletonMap("servidor", servidorService.get(id)))
                        .status(OK)
                        .mensagem("Servidor carregado com sucesso")
                        .statusCode(OK.value())
                        .build()
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<RespostaHTTP> deleteServidor(@PathVariable Long id){

        return ResponseEntity.ok(
                RespostaHTTP.builder()
                        .timeStamp(now())
                        .dados(Collections.singletonMap("servidor", servidorService.delete(id)))
                        .status(OK)
                        .mensagem("Servidor deletado com sucesso")
                        .statusCode(OK.value())
                        .build()
        );
    }
}
