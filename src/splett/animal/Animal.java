package splett.animal;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import splett.animal.raca.Raca;
import splett.genero.Genero;
import splett.usuario.Usuario;

@Entity
@Table(name = "tbAnimal")
public class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nome;

    private int idade;

    private float peso;

    private String necessidades;

    @Enumerated(EnumType.STRING)
    private Genero genero;

    @OneToOne
    private Raca raca;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    private Usuario dono;

    private String fotoCaminho;
    private String fotoNome;
    private String fotoContentType;

    public String getFotoContentType() {
	return fotoContentType;
    }

    public void setFotoContentType(String fotoContentType) {
	this.fotoContentType = fotoContentType;
    }

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

    public int getIdade() {
	return idade;
    }

    public void setIdade(int idade) {
	this.idade = idade;
    }

    public float getPeso() {
	return peso;
    }

    public void setPeso(float peso) {
	this.peso = peso;
    }

    public String getNecessidades() {
	return necessidades;
    }

    public void setNecessidades(String necessidades) {
	this.necessidades = necessidades;
    }

    public Genero getGenero() {
	return genero;
    }

    public void setGenero(Genero genero) {
	this.genero = genero;
    }

    public Raca getRaca() {
	return raca;
    }

    public void setRaca(Raca raca) {
	this.raca = raca;
    }

    public Usuario getDono() {
	return dono;
    }

    public void setDono(Usuario dono) {
	this.dono = dono;
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

}
