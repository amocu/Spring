package com.ccsw.tutorial.prestamo;

import java.util.List;

import org.springframework.data.domain.Page;

import com.ccsw.tutorial.author.model.Author;
import com.ccsw.tutorial.prestamo.model.Prestamo;
import com.ccsw.tutorial.prestamo.model.PrestamoDto;
import com.ccsw.tutorial.prestamo.model.PrestamoSearchDto;

public interface PrestamoService {
    /**
     * Método para recuperar un listado paginado de {@link Author}
     *
     * @param dto dto de búsqueda
     * @return {@link Page} de {@link Author}
     */
    Page<Prestamo> findPage(PrestamoSearchDto dto);

    /**
     * Método para recuperar todas los prestamos
     *
     * @return {@link List} de {@link Prestamo}
     */
    List<Prestamo> findAll();

    /**
     * Método para crear o actualizar un prestamo
     *
     * @param id  PK de la entidad
     * @param dto datos de la entidad
     */
    void save(Long id, PrestamoDto dto) throws Exception;

    /**
     * Método para borrar un prestamo
     *
     * @param id PK de la entidad
     * @throws Exception
     */
    void delete(Long id) throws Exception;
}
