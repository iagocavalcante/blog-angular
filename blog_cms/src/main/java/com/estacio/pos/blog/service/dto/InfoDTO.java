package com.estacio.pos.blog.service.dto;


import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Info entity.
 */
public class InfoDTO implements Serializable {

    private Long id;

    private String sobre;

    private String linkedin;

    private String facebook;

    private String twitter;

    private String avatar;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getSobre() {
        return sobre;
    }

    public void setSobre(String sobre) {
        this.sobre = sobre;
    }
    public String getLinkedin() {
        return linkedin;
    }

    public void setLinkedin(String linkedin) {
        this.linkedin = linkedin;
    }
    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }
    public String getTwitter() {
        return twitter;
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }
    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        InfoDTO infoDTO = (InfoDTO) o;

        if ( ! Objects.equals(id, infoDTO.id)) { return false; }

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "InfoDTO{" +
            "id=" + id +
            ", sobre='" + sobre + "'" +
            ", linkedin='" + linkedin + "'" +
            ", facebook='" + facebook + "'" +
            ", twitter='" + twitter + "'" +
            ", avatar='" + avatar + "'" +
            '}';
    }
}
