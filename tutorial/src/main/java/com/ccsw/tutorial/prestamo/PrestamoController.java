package com.ccsw.tutorial.prestamo;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ccsw.tutorial.author.model.AuthorDto;
import com.ccsw.tutorial.prestamo.model.Prestamo;
import com.ccsw.tutorial.prestamo.model.PrestamoDto;
import com.ccsw.tutorial.prestamo.model.PrestamoSearchDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * @author ccsw
 *
 */
@Tag(name = "Prestamo", description = "API de Prestamos")
@RequestMapping(value = "/prestamo")
@RestController
@CrossOrigin(origins = "*")
public class PrestamoController {
    @Autowired
    private PrestamoService prestamoService;

    @Autowired
    ModelMapper mapper;

    /**
     * Método para recuperar todos los prestamos
     *
     * @return {@link List} de {@link PrestamoDto}
     */
    @Operation(summary = "Find", description = "Metodo que recupera los prestamos")
    @RequestMapping(path = "", method = RequestMethod.GET)
    public List<PrestamoDto> findAll() {

        List<Prestamo> prestamos = this.prestamoService.findAll();

        return prestamos.stream().map(e -> mapper.map(e, PrestamoDto.class)).collect(Collectors.toList());
    }

    /**
     * Método para recuperar un listado paginado de {@link Prestamo}
     *
     * @param dto dto de búsqueda
     * @return {@link Page} de {@link AuthorDto}
     */
    @Operation(summary = "Find Page", description = "Method that return a page of Authors")
    @RequestMapping(path = "", method = RequestMethod.POST)
    public Page<PrestamoDto> findPage(@RequestBody PrestamoSearchDto dto) {

        Page<Prestamo> page = this.prestamoService.findPage(dto);

        return new PageImpl<>(
                page.getContent().stream().map(e -> mapper.map(e, PrestamoDto.class)).collect(Collectors.toList()),
                page.getPageable(), page.getTotalElements());
    }

    /**
     * Método para crear o actualizar un prestamo
     *
     * @param id  PK de la entidad
     * @param dto datos de la entidad
     * @throws Exception
     */
    @Operation(summary = "Save or Update", description = "Metodo para guardar o actualizar un prestamo")
    @RequestMapping(path = { "", "/{id}" }, method = RequestMethod.PUT)
    public void save(@PathVariable(name = "id", required = false) Long id, @RequestBody PrestamoDto dto)
            throws Exception {
        this.prestamoService.save(id, dto);
    }

    /**
     * Método para borrar un prestamo
     *
     * @param id PK de la entidad
     * @throws Exception
     */
    @Operation(summary = "Delete", description = "Metodo para eliminar un prestamo")
    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") Long id) throws Exception {

        this.prestamoService.delete(id);
    }
}
