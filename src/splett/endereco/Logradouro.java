package splett.endereco;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "log_logradouro")
@NamedQueries({ @NamedQuery(name = "Logradouro.pesquisaPorCep",
	query = "SELECT logradouro FROM Logradouro logradouro WHERE logradouro.cep = :cep") })
public class Logradouro implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "LOG_NU_SEQUENCIAL")
    private Integer id;

    @Column(name = "UFE_SG")
    private String uf;

    @JoinColumn(name = "LOC_NU_SEQUENCIAL", referencedColumnName = "LOC_NU_SEQUENCIAL",
	    updatable = false)
    @ManyToOne(optional = true)
    private Localidade localidade = new Localidade();

    @Column(name = "LOG_NO")
    private String nome;

    @Column(name = "LOG_NOME")
    private String nomeCompleto;

    @JoinColumn(name = "BAI_NU_SEQUENCIAL_INI", referencedColumnName = "BAI_NU_SEQUENCIAL",
	    updatable = false)
    @ManyToOne(optional = true)
    private Bairro bairroInicial = new Bairro();

    @JoinColumn(name = "BAI_NU_SEQUENCIAL_FIM", referencedColumnName = "BAI_NU_SEQUENCIAL",
	    updatable = false)
    @ManyToOne(optional = true)
    private Bairro bairroFinal = new Bairro();

    @Column(name = "CEP")
    private String cep;

    @Column(name = "LOG_TIPO_LOGRADOURO")
    private String tipoLogradouro;

    public Integer getId() {
	return id;
    }

    public void setId(Integer id) {
	this.id = id;
    }

    public String getUf() {
	return uf;
    }

    public void setUf(String uf) {
	this.uf = uf;
    }

    public String getNome() {
	return nome;
    }

    public void setNome(String nome) {
	this.nome = nome;
    }

    public Bairro getBairroInicial() {
	return bairroInicial;
    }

    public void setBairroInicial(Bairro bairroInicial) {
	this.bairroInicial = bairroInicial;
    }

    public Bairro getBairroFinal() {
	return bairroFinal;
    }

    public void setBairroFinal(Bairro bairroFinal) {
	this.bairroFinal = bairroFinal;
    }

    public String getCep() {
	return cep;
    }

    public void setCep(String cep) {
	this.cep = cep.replace("-", "");
    }

    public String getTipoLogradouro() {
	return tipoLogradouro;
    }

    public void setTipoLogradouro(String tipoLogradouro) {
	this.tipoLogradouro = tipoLogradouro;
    }

    public Localidade getLocalidade() {
	return localidade;
    }

    public void setLocalidade(Localidade localidade) {
	this.localidade = localidade;
    }

    public String getNomeCompleto() {
	return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
	this.nomeCompleto = nomeCompleto;
    }
}
