package spring.boot.defasio.nubank.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import spring.boot.defasio.nubank.dto.ContatoDTO;
import spring.boot.defasio.nubank.model.Clientes;
import spring.boot.defasio.nubank.model.Contato;
import spring.boot.defasio.nubank.repository.ClientesRepository;
import spring.boot.defasio.nubank.repository.ContatoRepository;


@RestController
@RequestMapping("/contatos")
public class ContatoController {
    
    @Autowired
    private ContatoRepository contatoRepository;

    @Autowired
    private ClientesRepository clientesRepository;

    @PostMapping
    public ResponseEntity<?> criar(@RequestBody ContatoDTO dto) {

        Optional<Clientes> clientesOpt = clientesRepository.findById(dto.getClienteId());
        if (clientesOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Cliente não encontrado!");
        }

        Contato contato = new Contato();
        contato.setTelefone(dto.getTelefone());
        contato.setEmail(dto.getEmail());
        contato.setClientes(clientesOpt.get());
        contatoRepository.save(contato);

        return ResponseEntity.status(HttpStatus.CREATED).body(contato);
    }
    

}
