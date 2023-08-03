package com.ccsw.tutorial.cliente;

import java.util.List;

import com.ccsw.tutorial.cliente.model.Cliente;
import com.ccsw.tutorial.cliente.model.ClienteDto;

/**
 * @author ccsw
 *
 */

public interface ClienteService {
    /**
     * Método para recuperar todas los clientes
     *
     * @return {@link List} de {@link Cliente}
     */
    List<Cliente> findAll();

    /**
     * Método para crear o actualizar un cliente
     *
     * @param id  PK de la entidad
     * @param dto datos de la entidad
     */
    void save(Long id, ClienteDto dto) throws Exception;

    /**
     * Método para borrar un cliente
     *
     * @param id PK de la entidad
     * @throws Exception
     */
    void delete(Long id) throws Exception;
}
