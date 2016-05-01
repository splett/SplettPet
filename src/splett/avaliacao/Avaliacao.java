package splett.avaliacao;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import splett.usuario.Usuario;

@Entity
@Table(name = "tbAvaliacao")
public class Avaliacao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String texto;

	private int pontuacao = 3;

	@JoinColumn(referencedColumnName = "id", name = "usuario_avaliador_id")
	@OneToOne
	private Usuario avaliador;

	@JoinColumn(referencedColumnName = "id", name = "usuario_avaliado_id")
	@OneToOne
	private Usuario avaliado;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public int getPontuacao() {
		return pontuacao;
	}

	public void setPontuacao(int pontuacao) {
		this.pontuacao = pontuacao;
	}

	public Usuario getAvaliador() {
		return avaliador;
	}

	public void setAvaliador(Usuario avaliador) {
		this.avaliador = avaliador;
	}

	public Usuario getAvaliado() {
		return avaliado;
	}

	public void setAvaliado(Usuario avaliado) {
		this.avaliado = avaliado;
	}

}
