package com.blog.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "post")
public class Post {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "conteudo", columnDefinition = "TEXT")
    private String conteudo;

    @Column(name = "dt_criacao")
    private LocalDate dtCriacao;

    @Column(name = "dt_atualizacao")
    private LocalDate dtAtualizacao;

    @Column(name = "tags")
    private String tags;

    @Column(name = "status")
    private Boolean status = false;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private java.util.List<Comentario> comentarios;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getConteudo() { return conteudo; }
    public void setConteudo(String conteudo) { this.conteudo = conteudo; }

    public LocalDate getDtCriacao() { return dtCriacao; }
    public void setDtCriacao(LocalDate dtCriacao) { this.dtCriacao = dtCriacao; }

    public LocalDate getDtAtualizacao() { return dtAtualizacao; }
    public void setDtAtualizacao(LocalDate dtAtualizacao) { this.dtAtualizacao = dtAtualizacao; }

    public String getTags() { return tags; }
    public void setTags(String tags) { this.tags = tags; }

    public Boolean getStatus() { return status; }
    public void setStatus(Boolean status) { this.status = status; }

    public java.util.List<Comentario> getComentarios() { return comentarios; }
    public void setComentarios(java.util.List<Comentario> comentarios) { this.comentarios = comentarios; }
}
