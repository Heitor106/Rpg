package model;

public class Bonus {

	private String nome;
	private int bonus;
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getBonus() {
		return bonus;
	}

	public void setBonus(int bonus) {
		this.bonus = bonus;
	}


	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("\n");
		sb.append("Bonus: ");
		sb.append(bonus);
		sb.append("\n");

		return sb.toString();
		}

}
