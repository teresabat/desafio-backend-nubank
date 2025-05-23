package spring.boot.defasio.nubank.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import spring.boot.defasio.nubank.dto.ClientesDTO;
import spring.boot.defasio.nubank.dto.ClientesResponseDTO;
import spring.boot.defasio.nubank.dto.ContatoResponseDTO;
import spring.boot.defasio.nubank.model.Clientes;
import spring.boot.defasio.nubank.service.ClientesService;




@RestController
@RequestMapping("/clientes")
public class ClienteController {
    
    @Autowired
    private ClientesService clientesService;

    @PostMapping
    public ResponseEntity<Clientes> criar(@RequestBody ClientesDTO dto) {
        Clientes clienteSalvo = clientesService.salvarClientes(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteSalvo);
    }

    @GetMapping
    public ResponseEntity<List<ClientesResponseDTO>> listarTodos() {
        return ResponseEntity.ok(clientesService.listarTodos());
    }
    
    @GetMapping("/clientes/{id}/contato")
    public ResponseEntity<List<ContatoResponseDTO>> listarContatos(@PathVariable Long id) {
        return ResponseEntity.ok(clientesService.listarContatosPorCliente(id));
    }
    
    

}
