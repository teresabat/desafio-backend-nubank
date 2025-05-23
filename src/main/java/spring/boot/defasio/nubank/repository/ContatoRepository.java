package spring.boot.defasio.nubank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import spring.boot.defasio.nubank.model.Contato;

public interface ContatoRepository extends JpaRepository<Contato, Long> {
    
}
