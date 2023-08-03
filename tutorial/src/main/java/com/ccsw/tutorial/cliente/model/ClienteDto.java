package com.ccsw.tutorial.cliente.model;

/**
 * @author ccsw
 *
 */

public class ClienteDto {
    private Long id;
    private String name;

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id new value of {@link #getId}.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param id new value of {@link #getName}.
     */
    public void setName(String name) {
        this.name = name;
    }

}
