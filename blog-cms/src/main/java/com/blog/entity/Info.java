package com.blog.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "info")
public class Info {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "sobre", columnDefinition = "TEXT")
    private String sobre;

    @Column(name = "linkedin")
    private String linkedin;

    @Column(name = "facebook")
    private String facebook;

    @Column(name = "twitter")
    private String twitter;

    @Column(name = "avatar")
    private String avatar;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getSobre() { return sobre; }
    public void setSobre(String sobre) { this.sobre = sobre; }

    public String getLinkedin() { return linkedin; }
    public void setLinkedin(String linkedin) { this.linkedin = linkedin; }

    public String getFacebook() { return facebook; }
    public void setFacebook(String facebook) { this.facebook = facebook; }

    public String getTwitter() { return twitter; }
    public void setTwitter(String twitter) { this.twitter = twitter; }

    public String getAvatar() { return avatar; }
    public void setAvatar(String avatar) { this.avatar = avatar; }
}
