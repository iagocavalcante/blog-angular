package com.estacio.pos.blog.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Post.
 */
@Entity
@Table(name = "post")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Post implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "conteudo")
    private String conteudo;

    @Column(name = "dtcriacao")
    private LocalDate dtcriacao;

    @Column(name = "dtatualizacao")
    private LocalDate dtatualizacao;

    @Column(name = "tags")
    private String tags;

    @Column(name = "status")
    private Boolean status;

    @OneToMany(mappedBy = "post")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Comentario> comentarios = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getConteudo() {
        return conteudo;
    }

    public Post conteudo(String conteudo) {
        this.conteudo = conteudo;
        return this;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public LocalDate getDtcriacao() {
        return dtcriacao;
    }

    public Post dtcriacao(LocalDate dtcriacao) {
        this.dtcriacao = dtcriacao;
        return this;
    }

    public void setDtcriacao(LocalDate dtcriacao) {
        this.dtcriacao = dtcriacao;
    }

    public LocalDate getDtatualizacao() {
        return dtatualizacao;
    }

    public Post dtatualizacao(LocalDate dtatualizacao) {
        this.dtatualizacao = dtatualizacao;
        return this;
    }

    public void setDtatualizacao(LocalDate dtatualizacao) {
        this.dtatualizacao = dtatualizacao;
    }

    public String getTags() {
        return tags;
    }

    public Post tags(String tags) {
        this.tags = tags;
        return this;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public Boolean isStatus() {
        return status;
    }

    public Post status(Boolean status) {
        this.status = status;
        return this;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Set<Comentario> getComentarios() {
        return comentarios;
    }

    public Post comentarios(Set<Comentario> comentarios) {
        this.comentarios = comentarios;
        return this;
    }

    public Post addComentario(Comentario comentario) {
        this.comentarios.add(comentario);
        comentario.setPost(this);
        return this;
    }

    public Post removeComentario(Comentario comentario) {
        this.comentarios.remove(comentario);
        comentario.setPost(null);
        return this;
    }

    public void setComentarios(Set<Comentario> comentarios) {
        this.comentarios = comentarios;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Post post = (Post) o;
        if (post.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, post.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Post{" +
            "id=" + id +
            ", conteudo='" + conteudo + "'" +
            ", dtcriacao='" + dtcriacao + "'" +
            ", dtatualizacao='" + dtatualizacao + "'" +
            ", tags='" + tags + "'" +
            ", status='" + status + "'" +
            '}';
    }
}
