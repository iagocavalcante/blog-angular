package com.blog.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "comentario")
public class Comentario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "comentario", columnDefinition = "TEXT")
    private String comentario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getComentario() { return comentario; }
    public void setComentario(String comentario) { this.comentario = comentario; }

    public Post getPost() { return post; }
    public void setPost(Post post) { this.post = post; }
}
