package splett.genero;

public enum Genero {
    MASCULINO("Masculino"), FEMININO("Feminino"), OUTRO("Outro");

    private String label;

    private Genero(String label) {
	this.label = label;
    }

    public String toString() {
	return this.label;
    }
}
