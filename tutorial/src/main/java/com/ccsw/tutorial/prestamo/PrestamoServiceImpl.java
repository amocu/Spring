package com.ccsw.tutorial.prestamo;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.ccsw.tutorial.common.criteria.SearchCriteria;
import com.ccsw.tutorial.prestamo.model.Prestamo;
import com.ccsw.tutorial.prestamo.model.PrestamoDto;
import com.ccsw.tutorial.prestamo.model.PrestamoSearchDto;

import jakarta.transaction.Transactional;

/**
 * @author ccsw
 *
 */
@Service
@Transactional
public class PrestamoServiceImpl implements PrestamoService {

    private int PLAZODIAS = 14; // Plazo máximo de días de préstamo

    @Autowired
    PrestamoRepository prestamoRepository;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Prestamo> findAll() {
        return (List<Prestamo>) this.prestamoRepository.findAll();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Page<Prestamo> findPage(PrestamoSearchDto dto) {

        return this.prestamoRepository.findAll(dto.getPageable().getPageable());
    }

    @Override
    public void save(Long id, PrestamoDto dto) throws Exception {
        Prestamo prestamo = null;

        if (id == null) {

            if (validaciones(dto)) {
                prestamo = new Prestamo();
            }
        } else {
            prestamo = this.prestamoRepository.findById(id).orElse(null);
        }

        BeanUtils.copyProperties(dto, prestamo, "id", "game", "cliente");

        prestamo.setGame(dto.getGame());
        prestamo.setCliente(dto.getCliente());

        this.prestamoRepository.save(prestamo);

    }

    @Override
    public void delete(Long id) throws Exception {

        if (this.prestamoRepository.findById(id).orElse(null) == null) {
            throw new Exception("No existe el prestamo");
        }

        this.prestamoRepository.deleteById(id);

    }

    private boolean validaciones(PrestamoDto dto) throws Exception {

        List<Prestamo> prestamos;
        Specification<Prestamo> spec;

        /*
         * La fecha de fin NO podrá ser anterior a la fecha de inicio
         */

        if (dto.getFechaInicio().compareTo(dto.getFechaFin()) > 0) {
            throw new Exception("La fecha de fin no puede ser inferior a la de inicio");
        }
        /*
         * El periodo de préstamo máximo solo podrá ser de 14 días
         */

        if (limetePlazo(dto.getFechaInicio(), dto.getFechaFin(), PLAZODIAS)) {
            throw new Exception("El préstamo no puede superar los 14 días");
        }

        /*
         * Un mismo cliente no puede tener prestados más de 2 juegos en un mismo día
         */

        PrestamoSpecification clienteSpec = new PrestamoSpecification(
                new SearchCriteria("cliente.id", ":", dto.getCliente().getId()));

        // inicio del prestamos dentro de un prestamo existente
        PrestamoSpecification fecIniPosIniSpec = new PrestamoSpecification(
                new SearchCriteria("fechaInicio", "<", dto.getFechaInicio()));
        PrestamoSpecification fecIniPreFinSpec = new PrestamoSpecification(
                new SearchCriteria("fechaFin", ">", dto.getFechaInicio()));

        // finalización del prestamo dentro de uno existente
        PrestamoSpecification fecFinPosIniSpec = new PrestamoSpecification(
                new SearchCriteria("fechaInicio", "<", dto.getFechaFin()));
        PrestamoSpecification fecFinPreFinSpec = new PrestamoSpecification(
                new SearchCriteria("fechaFin", ">", dto.getFechaFin()));

        // Prestamos dentro del plazo del nuevo prestamo
        PrestamoSpecification fecIniPreIniSpec = new PrestamoSpecification(
                new SearchCriteria("fechaInicio", ">", dto.getFechaInicio()));
        PrestamoSpecification fecFinPosFinSpec = new PrestamoSpecification(
                new SearchCriteria("fechaFin", "<", dto.getFechaFin()));

        spec = Specification.where(clienteSpec).and(fecIniPosIniSpec.and(fecIniPreFinSpec))
                .or(fecFinPosIniSpec.and(fecFinPreFinSpec)).or(fecIniPreIniSpec.and(fecFinPosFinSpec));

        prestamos = prestamoRepository.findAll(spec);

        if (prestamos.size() > 1) {
            throw new Exception("No es posible tener más de 2 préstamos un mismo día.");
        }

        /*
         * El mismo juego no puede estar prestado a dos clientes distintos en un mismo
         * día
         */
        PrestamoSpecification gameSpec = new PrestamoSpecification(
                new SearchCriteria("game.id", ":", dto.getGame().getId()));

        spec = Specification.where(gameSpec).and(fecIniPosIniSpec.and(fecIniPreFinSpec))
                .or(fecFinPosIniSpec.and(fecFinPreFinSpec)).or(fecIniPreIniSpec.and(fecFinPosFinSpec));

        prestamos = prestamoRepository.findAll(spec);

        if (prestamos.size() > 0) {
            throw new Exception("Un juego no puede estar prestado a dos clientes a la vez");
        }

        return true;
    }

    private boolean limetePlazo(Date fechaInicio, Date fechaFin, int dias) {

        long diasEntreFechas = ChronoUnit.DAYS.between(convertToLocalDateViaInstant(fechaInicio),
                convertToLocalDateViaInstant(fechaFin));

        if (diasEntreFechas > dias)
            return true;
        else
            return false;
    }

    private LocalDate convertToLocalDateViaInstant(Date dateToConvert) {
        return dateToConvert.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }
}
