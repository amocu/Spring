package com.ccsw.tutorial.cliente;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ccsw.tutorial.cliente.model.Cliente;
import com.ccsw.tutorial.cliente.model.ClienteDto;

import jakarta.transaction.Transactional;

/**
 * @author ccsw
 *
 */
@Service
@Transactional
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    ClienteRepository clienteRepository;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Cliente> findAll() {
        return (List<Cliente>) this.clienteRepository.findAll();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void save(Long id, ClienteDto dto) throws Exception {
        Cliente cliente;

        if (id == null) {
            if (this.clienteRepository.findByName(dto.getName()).size() > 0) {
                throw new Exception("Ya existe un cliente con ese nombre");
            }

            cliente = new Cliente();
        } else {
            cliente = this.clienteRepository.findById(id).orElse(null);
        }

        cliente.setName(dto.getName());

        this.clienteRepository.save(cliente);

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(Long id) throws Exception {

        if (this.clienteRepository.findById(id).orElse(null) == null) {
            throw new Exception("No existe el cliente");
        }

        this.clienteRepository.deleteById(id);
    }

}
