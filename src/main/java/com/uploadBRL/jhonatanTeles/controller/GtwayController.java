package com.uploadBRL.jhonatanTeles.controller;

import com.uploadBRL.jhonatanTeles.busines.GtwaryService;
import com.uploadBRL.jhonatanTeles.dto.out.TransacaoKafkaDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/")
@Tag(name = "Binance", description = "Gerenciamento da carteira cripto")
@RestController
public class GtwayController {

    private final GtwaryService service;

    public GtwayController(GtwaryService service){
        this.service = service;
    }

    @GetMapping("/ExtratoTransações")
    @Operation(summary = "buscar", description = "buscar Lista de códogo de Cripto")
    @ApiResponse(responseCode = "200", description = "busca com sucesso!")
    @ApiResponse(responseCode = "400", description = "erro de requisisão.")
    @ApiResponse(responseCode = "401", description = "Lista não cadastrado")
    public ResponseEntity<List<TransacaoKafkaDTO>> Listartransasoes(){
        return ResponseEntity.ok(service.listartransasoes());
    }
}
