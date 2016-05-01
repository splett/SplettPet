package splett.usuarioDisponibilidade;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import splett.disponibilidade.Disponibilidade;
import splett.usuario.Usuario;

@Entity
@Table(name = "tbUsuarioDisponibilidade")
public class UsuarioDisponibilidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JoinColumn(referencedColumnName = "id", name = "usuario_id")
    @ManyToOne
    private Usuario usuario;

    @JoinColumn(referencedColumnName = "id", name = "disponibilidade_id")
    @ManyToOne
    private Disponibilidade disponibilidade;

    public Usuario getUsuario() {
	return usuario;
    }

    public void setUsuario(Usuario usuario) {
	this.usuario = usuario;
    }

    public Disponibilidade getDisponibilidade() {
	return disponibilidade;
    }

    public void setDisponibilidade(Disponibilidade disponibilidade) {
	this.disponibilidade = disponibilidade;
    }

    public Integer getId() {
	return id;
    }

    public void setId(Integer id) {
	this.id = id;
    }

}
