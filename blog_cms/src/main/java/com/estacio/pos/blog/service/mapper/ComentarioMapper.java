package com.estacio.pos.blog.service.mapper;

import com.estacio.pos.blog.domain.*;
import com.estacio.pos.blog.service.dto.ComentarioDTO;

import org.mapstruct.*;
import java.util.List;

/**
 * Mapper for the entity Comentario and its DTO ComentarioDTO.
 */
@Mapper(componentModel = "spring", uses = {PostMapper.class, })
public interface ComentarioMapper {

    @Mapping(source = "post.id", target = "postId")
    ComentarioDTO comentarioToComentarioDTO(Comentario comentario);

    List<ComentarioDTO> comentariosToComentarioDTOs(List<Comentario> comentarios);

    @Mapping(source = "postId", target = "post")
    Comentario comentarioDTOToComentario(ComentarioDTO comentarioDTO);

    List<Comentario> comentarioDTOsToComentarios(List<ComentarioDTO> comentarioDTOs);
    /**
     * generating the fromId for all mappers if the databaseType is sql, as the class has relationship to it might need it, instead of
     * creating a new attribute to know if the entity has any relationship from some other entity
     *
     * @param id id of the entity
     * @return the entity instance
     */
     
    default Comentario comentarioFromId(Long id) {
        if (id == null) {
            return null;
        }
        Comentario comentario = new Comentario();
        comentario.setId(id);
        return comentario;
    }
    

}
