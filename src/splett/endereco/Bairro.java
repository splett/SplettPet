package splett.endereco;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "log_bairro")
public class Bairro implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "BAI_NU_SEQUENCIAL")
    private Integer id;

    @Column(name = "UFE_SG")
    private String uf;

    @Column(name = "BAI_NO")
    private String nome;

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
}
