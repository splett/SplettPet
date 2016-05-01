package splett.endereco;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "log_localidade")
public class Localidade implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "LOC_NU_SEQUENCIAL")
    private Integer id;

    @Column(name = "LOC_NO", nullable = false)
    private String nome;

    @Column(name = "CEP")
    private String cep;

    @Column(name = "UFE_SG")
    private String uf;

    public Integer getId() {
	return id;
    }

    public void setId(Integer id) {
	this.id = id;
    }

    public String getNome() {
	return nome;
    }

    public void setNome(String nome) {
	this.nome = nome;
    }

    public String getCep() {
	return cep;
    }

    public void setCep(String cep) {
	this.cep = cep;
    }

    public String getUf() {
	return uf;
    }

    public void setUf(String uf) {
	this.uf = uf;
    }
}
