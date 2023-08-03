package com.ccsw.tutorial.prestamo;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import com.ccsw.tutorial.prestamo.model.Prestamo;

public interface PrestamoRepository extends CrudRepository<Prestamo, Long>, JpaSpecificationExecutor<Prestamo> {

    List<Prestamo> findAll(Specification<Prestamo> spec);

    Page<Prestamo> findAll(Pageable pageable);

}
