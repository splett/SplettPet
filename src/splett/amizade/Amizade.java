package splett.amizade;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import splett.usuario.Usuario;

@Entity
@Table(name = "tbAmizade")
public class Amizade {

    public Amizade() {
	status = Status.ESPERA;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JoinColumn(name = "id_origem", referencedColumnName = "id")
    @ManyToOne
    private Usuario usuarioOrigem;

    @JoinColumn(name = "id_destino", referencedColumnName = "id")
    @ManyToOne
    private Usuario usuarioDestino;

    @Enumerated(EnumType.STRING)
    private Status status;

    public Integer getId() {
	return id;
    }

    public void setId(Integer id) {
	this.id = id;
    }

    public Usuario getUsuarioOrigem() {
	return usuarioOrigem;
    }

    public void setUsuarioOrigem(Usuario usuarioOrigem) {
	this.usuarioOrigem = usuarioOrigem;
    }

    public Usuario getUsuarioDestino() {
	return usuarioDestino;
    }

    public void setUsuarioDestino(Usuario usuarioDestino) {
	this.usuarioDestino = usuarioDestino;
    }

    public Status getStatus() {
	return status;
    }

    public void setStatus(Status status) {
	this.status = status;
    }
}
