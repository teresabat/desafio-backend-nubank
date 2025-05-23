package spring.boot.defasio.nubank.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.Entity;
import spring.boot.defasio.nubank.dto.ClientesDTO;
import spring.boot.defasio.nubank.dto.ClientesResponseDTO;
import spring.boot.defasio.nubank.dto.ContatoResponseDTO;
import spring.boot.defasio.nubank.model.Clientes;
import spring.boot.defasio.nubank.model.Contato;
import spring.boot.defasio.nubank.repository.ClientesRepository;

@Service
public class ClientesService {
    
    @Autowired
    private ClientesRepository clientesRepository;

    public Clientes salvarClientes(ClientesDTO dto) {
        Clientes clientes = new Clientes();
        clientes.setNome(dto.getNome());

        if(dto.getContatos() != null && dto.getContatos().size() > 0) {
            List<Contato> contatos = dto.getContatos().stream().map(c -> {
                Contato contato = new Contato();
                contato.setTelefone(c.getTelefone());
                contato.setEmail(c.getEmail());
                contato.setClientes(clientes);
                return contato;
            }).collect(Collectors.toList());
            clientes.setContatos(contatos);
        }

        return clientesRepository.save(clientes);
    }

    public List<ClientesResponseDTO> listarTodos(){
        return clientesRepository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    public List<ContatoResponseDTO> listarContatosPorCliente(Long clienteId) {
    Clientes cliente = clientesRepository.findById(clienteId)
        .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

    }

    private ClientesResponseDTO toDTO(Clientes cliente){
        ClientesResponseDTO dto = new ClientesResponseDTO();
        dto.setId(cliente.getId());
        dto.setNome(cliente.getNome());

        List<ContatoResponseDTO> contatos = cliente.getContatos().stream().map(c -> {
            ContatoResponseDTO contatoDTO = new ContatoResponseDTO();
            contatoDTO.setId(c.getId());
            contatoDTO.setTelefone(c.getTelefone());
            contatoDTO.setEmail(c.getEmail());
            return contatoDTO;
            }).collect(Collectors.toList());
            dto.setContatos(contatos);

            return dto;
    }
}
