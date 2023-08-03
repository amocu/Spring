package com.ccsw.tutorial.cliente;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.ccsw.tutorial.cliente.model.Cliente;

public interface ClienteRepository extends CrudRepository<Cliente, Long> {

    // @Query("select id from cliente where name = :name")
    List<Cliente> findByName(String name);

}
