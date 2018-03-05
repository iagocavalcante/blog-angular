package com.estacio.pos.blog.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.estacio.pos.blog.service.InfoService;
import com.estacio.pos.blog.web.rest.util.HeaderUtil;
import com.estacio.pos.blog.service.dto.InfoDTO;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * REST controller for managing Info.
 */
@RestController
@RequestMapping("/api")
public class InfoResource {

    private final Logger log = LoggerFactory.getLogger(InfoResource.class);

    private static final String ENTITY_NAME = "info";
        
    private final InfoService infoService;

    public InfoResource(InfoService infoService) {
        this.infoService = infoService;
    }

    /**
     * POST  /infos : Create a new info.
     *
     * @param infoDTO the infoDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new infoDTO, or with status 400 (Bad Request) if the info has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/infos")
    @Timed
    public ResponseEntity<InfoDTO> createInfo(@RequestBody InfoDTO infoDTO) throws URISyntaxException {
        log.debug("REST request to save Info : {}", infoDTO);
        if (infoDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new info cannot already have an ID")).body(null);
        }
        InfoDTO result = infoService.save(infoDTO);
        return ResponseEntity.created(new URI("/api/infos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /infos : Updates an existing info.
     *
     * @param infoDTO the infoDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated infoDTO,
     * or with status 400 (Bad Request) if the infoDTO is not valid,
     * or with status 500 (Internal Server Error) if the infoDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/infos")
    @Timed
    public ResponseEntity<InfoDTO> updateInfo(@RequestBody InfoDTO infoDTO) throws URISyntaxException {
        log.debug("REST request to update Info : {}", infoDTO);
        if (infoDTO.getId() == null) {
            return createInfo(infoDTO);
        }
        InfoDTO result = infoService.save(infoDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, infoDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /infos : get all the infos.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of infos in body
     */
    @GetMapping("/infos")
    @Timed
    public List<InfoDTO> getAllInfos() {
        log.debug("REST request to get all Infos");
        return infoService.findAll();
    }

    /**
     * GET  /infos/:id : get the "id" info.
     *
     * @param id the id of the infoDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the infoDTO, or with status 404 (Not Found)
     */
    @GetMapping("/infos/{id}")
    @Timed
    public ResponseEntity<InfoDTO> getInfo(@PathVariable Long id) {
        log.debug("REST request to get Info : {}", id);
        InfoDTO infoDTO = infoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(infoDTO));
    }

    /**
     * DELETE  /infos/:id : delete the "id" info.
     *
     * @param id the id of the infoDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/infos/{id}")
    @Timed
    public ResponseEntity<Void> deleteInfo(@PathVariable Long id) {
        log.debug("REST request to delete Info : {}", id);
        infoService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
