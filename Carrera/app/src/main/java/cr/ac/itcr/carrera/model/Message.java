package cr.ac.itcr.carrera.model;

import java.io.Serializable;

/**
 * Created by Lincoln on 07/01/16.
 */
public class Message implements Serializable {
    String id, message, createdAt, tipoevento;
    User user;

    public Message() {
    }

    public Message(String id, String message, String createdAt, User user, String tipoevento) {
        this.id = id;
        this.message = message;
        this.createdAt = createdAt;
        this.user = user;
        this.tipoevento = tipoevento;
    }

    public String getTipoevento() {
        return tipoevento;
    }

    public void setTipoevento(String tipoevento) {
        this.tipoevento = tipoevento;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
