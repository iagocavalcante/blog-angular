package com.estacio.pos.blog.service;

import com.estacio.pos.blog.service.dto.InfoDTO;
import java.util.List;

/**
 * Service Interface for managing Info.
 */
public interface InfoService {

    /**
     * Save a info.
     *
     * @param infoDTO the entity to save
     * @return the persisted entity
     */
    InfoDTO save(InfoDTO infoDTO);

    /**
     *  Get all the infos.
     *  
     *  @return the list of entities
     */
    List<InfoDTO> findAll();

    /**
     *  Get the "id" info.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    InfoDTO findOne(Long id);

    /**
     *  Delete the "id" info.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);
}
