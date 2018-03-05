package com.estacio.pos.blog.service.impl;

import com.estacio.pos.blog.service.InfoService;
import com.estacio.pos.blog.domain.Info;
import com.estacio.pos.blog.repository.InfoRepository;
import com.estacio.pos.blog.service.dto.InfoDTO;
import com.estacio.pos.blog.service.mapper.InfoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing Info.
 */
@Service
@Transactional
public class InfoServiceImpl implements InfoService{

    private final Logger log = LoggerFactory.getLogger(InfoServiceImpl.class);
    
    private final InfoRepository infoRepository;

    private final InfoMapper infoMapper;

    public InfoServiceImpl(InfoRepository infoRepository, InfoMapper infoMapper) {
        this.infoRepository = infoRepository;
        this.infoMapper = infoMapper;
    }

    /**
     * Save a info.
     *
     * @param infoDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public InfoDTO save(InfoDTO infoDTO) {
        log.debug("Request to save Info : {}", infoDTO);
        Info info = infoMapper.infoDTOToInfo(infoDTO);
        info = infoRepository.save(info);
        InfoDTO result = infoMapper.infoToInfoDTO(info);
        return result;
    }

    /**
     *  Get all the infos.
     *  
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<InfoDTO> findAll() {
        log.debug("Request to get all Infos");
        List<InfoDTO> result = infoRepository.findAll().stream()
            .map(infoMapper::infoToInfoDTO)
            .collect(Collectors.toCollection(LinkedList::new));

        return result;
    }

    /**
     *  Get one info by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public InfoDTO findOne(Long id) {
        log.debug("Request to get Info : {}", id);
        Info info = infoRepository.findOne(id);
        InfoDTO infoDTO = infoMapper.infoToInfoDTO(info);
        return infoDTO;
    }

    /**
     *  Delete the  info by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Info : {}", id);
        infoRepository.delete(id);
    }
}
