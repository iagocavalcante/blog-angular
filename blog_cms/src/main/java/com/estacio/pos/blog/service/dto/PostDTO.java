package com.estacio.pos.blog.service.dto;

import java.time.LocalDate;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the Post entity.
 */
public class PostDTO implements Serializable {

    private Long id;

    private String conteudo;

    private LocalDate dtcriacao;

    private LocalDate dtatualizacao;

    private String tags;

    private Boolean status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }
    public LocalDate getDtcriacao() {
        return dtcriacao;
    }

    public void setDtcriacao(LocalDate dtcriacao) {
        this.dtcriacao = dtcriacao;
    }
    public LocalDate getDtatualizacao() {
        return dtatualizacao;
    }

    public void setDtatualizacao(LocalDate dtatualizacao) {
        this.dtatualizacao = dtatualizacao;
    }
    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }
    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        PostDTO postDTO = (PostDTO) o;

        if ( ! Objects.equals(id, postDTO.id)) { return false; }

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "PostDTO{" +
            "id=" + id +
            ", conteudo='" + conteudo + "'" +
            ", dtcriacao='" + dtcriacao + "'" +
            ", dtatualizacao='" + dtatualizacao + "'" +
            ", tags='" + tags + "'" +
            ", status='" + status + "'" +
            '}';
    }
}
