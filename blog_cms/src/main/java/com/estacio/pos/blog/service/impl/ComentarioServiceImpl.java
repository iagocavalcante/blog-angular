package com.estacio.pos.blog.service.impl;

import com.estacio.pos.blog.service.ComentarioService;
import com.estacio.pos.blog.domain.Comentario;
import com.estacio.pos.blog.repository.ComentarioRepository;
import com.estacio.pos.blog.service.dto.ComentarioDTO;
import com.estacio.pos.blog.service.mapper.ComentarioMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing Comentario.
 */
@Service
@Transactional
public class ComentarioServiceImpl implements ComentarioService{

    private final Logger log = LoggerFactory.getLogger(ComentarioServiceImpl.class);
    
    private final ComentarioRepository comentarioRepository;

    private final ComentarioMapper comentarioMapper;

    public ComentarioServiceImpl(ComentarioRepository comentarioRepository, ComentarioMapper comentarioMapper) {
        this.comentarioRepository = comentarioRepository;
        this.comentarioMapper = comentarioMapper;
    }

    /**
     * Save a comentario.
     *
     * @param comentarioDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public ComentarioDTO save(ComentarioDTO comentarioDTO) {
        log.debug("Request to save Comentario : {}", comentarioDTO);
        Comentario comentario = comentarioMapper.comentarioDTOToComentario(comentarioDTO);
        comentario = comentarioRepository.save(comentario);
        ComentarioDTO result = comentarioMapper.comentarioToComentarioDTO(comentario);
        return result;
    }

    /**
     *  Get all the comentarios.
     *  
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<ComentarioDTO> findAll() {
        log.debug("Request to get all Comentarios");
        List<ComentarioDTO> result = comentarioRepository.findAll().stream()
            .map(comentarioMapper::comentarioToComentarioDTO)
            .collect(Collectors.toCollection(LinkedList::new));

        return result;
    }

    /**
     *  Get one comentario by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public ComentarioDTO findOne(Long id) {
        log.debug("Request to get Comentario : {}", id);
        Comentario comentario = comentarioRepository.findOne(id);
        ComentarioDTO comentarioDTO = comentarioMapper.comentarioToComentarioDTO(comentario);
        return comentarioDTO;
    }

    /**
     *  Delete the  comentario by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Comentario : {}", id);
        comentarioRepository.delete(id);
    }
}
