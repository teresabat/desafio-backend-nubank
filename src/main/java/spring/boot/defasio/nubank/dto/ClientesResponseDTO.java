package spring.boot.defasio.nubank.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ClientesResponseDTO {

    private Long id;
    private String nome;
    private List<ContatoResponseDTO> contatos;
    
}
