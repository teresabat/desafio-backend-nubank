package spring.boot.defasio.nubank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import spring.boot.defasio.nubank.model.Clientes;

public interface ClientesRepository extends JpaRepository<Clientes, Long> {
    
}
