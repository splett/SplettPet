package splett.animal.raca;

public enum Porte {
	PEQUENO("Pequeno"), MEDIO("Médio"), GRANDE("Grande");

	private String label;

	private Porte(String label) {
		this.label = label;
	}

	@Override
	public String toString() {
		return this.label;
	}
}
