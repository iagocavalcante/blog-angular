package com.estacio.pos.blog.service.mapper;

import com.estacio.pos.blog.domain.*;
import com.estacio.pos.blog.service.dto.InfoDTO;

import org.mapstruct.*;
import java.util.List;

/**
 * Mapper for the entity Info and its DTO InfoDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface InfoMapper {

    InfoDTO infoToInfoDTO(Info info);

    List<InfoDTO> infosToInfoDTOs(List<Info> infos);

    Info infoDTOToInfo(InfoDTO infoDTO);

    List<Info> infoDTOsToInfos(List<InfoDTO> infoDTOs);
    /**
     * generating the fromId for all mappers if the databaseType is sql, as the class has relationship to it might need it, instead of
     * creating a new attribute to know if the entity has any relationship from some other entity
     *
     * @param id id of the entity
     * @return the entity instance
     */
     
    default Info infoFromId(Long id) {
        if (id == null) {
            return null;
        }
        Info info = new Info();
        info.setId(id);
        return info;
    }
    

}
