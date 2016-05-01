package splett.usuario;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import splett.animal.Animal;
import splett.genero.Genero;
import splett.preferencia.Preferencia;
import splett.usuario.endereco.Endereco;

@Entity
@Table(name = "tbUsuario")
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "username", unique = true)
	private String email;

	@Column(name = "password")
	private String senha;

	private String nome;

	@Enumerated(EnumType.STRING)
	private Genero genero;

	@Enumerated(EnumType.STRING)
	private Preferencia preferencia;

	private String telefoneCelular;

	private String telefoneFixo;

	private boolean isFacebook;

	@OneToOne(cascade = CascadeType.ALL)
	private Endereco endereco;

	@Column(name = "authority")
	@Enumerated(EnumType.STRING)
	private TipoUsuario tipo;

	@Temporal(TemporalType.DATE)
	private Date dataNascimento;

	@OneToMany(mappedBy = "dono")
	private List<Animal> animais;

	private boolean genero_isPublico = false;

	private boolean telefoneFixo_isPublico = false;

	private boolean telefoneCelular_isPublico = false;

	private boolean dataNascimento_isPublico = false;

	private boolean email_isPublico = false;

	private boolean endereco_isPublico = false;

	private String fotoCaminho;

	private String fotoNome;

	private String fotoContentType;

	public boolean isGenero_isPublico() {
		return genero_isPublico;
	}

	public String getFotoCaminho() {
		return fotoCaminho;
	}

	public void setFotoCaminho(String fotoCaminho) {
		this.fotoCaminho = fotoCaminho;
	}

	public String getFotoNome() {
		return fotoNome;
	}

	public void setFotoNome(String fotoNome) {
		this.fotoNome = fotoNome;
	}

	public String getFotoContentType() {
		return fotoContentType;
	}

	public void setFotoContentType(String fotoContentType) {
		this.fotoContentType = fotoContentType;
	}

	public void setGenero_isPublico(boolean genero_isPublico) {
		this.genero_isPublico = genero_isPublico;
	}

	public boolean isTelefoneFixo_isPublico() {
		return telefoneFixo_isPublico;
	}

	public void setTelefoneFixo_isPublico(boolean telefone1_isPublico) {
		this.telefoneFixo_isPublico = telefone1_isPublico;
	}

	public Preferencia getPreferencia() {
		return preferencia;
	}

	public void setPreferencia(Preferencia preferencia) {
		this.preferencia = preferencia;
	}

	public boolean isTelefoneCelular_isPublico() {
		return telefoneCelular_isPublico;
	}

	public void setTelefoneCelular_isPublico(boolean telefone2_isPublico) {
		this.telefoneCelular_isPublico = telefone2_isPublico;
	}

	public boolean isDataNascimento_isPublico() {
		return dataNascimento_isPublico;
	}

	public void setDataNascimento_isPublico(boolean dataNascimento_isPublico) {
		this.dataNascimento_isPublico = dataNascimento_isPublico;
	}

	public boolean isEndereco_isPublico() {
		return endereco_isPublico;
	}

	public void setEndereco_isPublico(boolean endereco_isPublico) {
		this.endereco_isPublico = endereco_isPublico;
	}

	public boolean isEmail_isPublico() {
		return email_isPublico;
	}

	public void setEmail_isPublico(boolean email_isPublico) {
		this.email_isPublico = email_isPublico;
	}

	public List<Animal> getAnimais() {
		return animais;
	}

	public void setAnimais(List<Animal> animais) {
		this.animais = animais;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public boolean isFacebook() {
		return isFacebook;
	}

	public void setFacebook(boolean isFacebook) {
		this.isFacebook = isFacebook;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getPrimeiroNome() {
		return (nome.split(" "))[0];
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public TipoUsuario getTipo() {
		return tipo;
	}

	public void setTipo(TipoUsuario tipo) {
		this.tipo = tipo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Genero getGenero() {
		return genero;
	}

	public void setGenero(Genero genero) {
		this.genero = genero;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public String getTelefoneCelular() {
		return telefoneCelular;
	}

	public void setTelefoneCelular(String telefoneCelular) {
		this.telefoneCelular = telefoneCelular;
	}

	public String getTelefoneFixo() {
		return telefoneFixo;
	}

	public void setTelefoneFixo(String telefoneFixo) {
		this.telefoneFixo = telefoneFixo;
	}

	public boolean equals(Object other) {
		return other instanceof Usuario && (id != null) ? id
				.equals(((Usuario) other).getId()) : (other == this);
	}

	public int hashCode() {
		return id != null ? this.getClass().hashCode() + id.hashCode() : super
				.hashCode();
	}

	@Override
	public String toString() {
		return this.email;
	}

}
