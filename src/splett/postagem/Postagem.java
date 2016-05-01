package splett.postagem;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import splett.usuario.Usuario;

@Entity
@Table(name = "tbPostagem")
public class Postagem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String texto;

    @Temporal(TemporalType.DATE)
    private Date dataPostagem;

    private String foto;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    private Usuario usuario;

    private boolean visibilidade;

    public boolean isVisibilidade() {
	return visibilidade;
    }

    public void setVisibilidade(boolean visibilidade) {
	this.visibilidade = visibilidade;
    }

    public int getId() {
	return id;
    }

    public void setId(int id) {
	this.id = id;
    }

    public String getTexto() {
	return texto;
    }

    public void setTexto(String texto) {
	this.texto = texto;
    }

    public Date getDataPostagem() {
	return dataPostagem;
    }

    public void setDataPostagem(Date dataPostagem) {
	this.dataPostagem = dataPostagem;
    }

    public String getFoto() {
	return foto;
    }

    public void setFoto(String foto) {
	this.foto = foto;
    }

    public Usuario getUsuario() {
	return usuario;
    }

    public void setUsuario(Usuario usuario) {
	this.usuario = usuario;
    }

}
