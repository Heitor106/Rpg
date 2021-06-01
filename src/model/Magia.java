package model;

import contantes.AtributoConstantes;
import contantes.DadosConstantes;

public class Magia {

	private String valorDeDado;
	private boolean disparavel;
	private int espacoDeMagia;
	private String Nome;
	private boolean Cura;
	private String tipoDeDano;
	private int nDados;
	private String CD;
	private int time;
	private int bonusMAC;
	private int bonusMHP;
	private DeBuff debuff;
	private boolean alvosmultiplos = false;
	private int alvosN = 1;
		
	public boolean isAlvosmultiplos() {
		return alvosmultiplos;
	}

	public void setAlvosmultiplos(boolean alvosmultiplos) {
		this.alvosmultiplos = alvosmultiplos;
	}

	public int getAlvosN() {
		return alvosN;
	}

	public void setAlvosN(int alvosN) {
		this.alvosN = alvosN;
	}

	public DeBuff getDebuff() {
		return debuff;
	}

	public void setDebuff(DeBuff debuff) {
		this.debuff = debuff;
	}

	public String getCD() {
		return CD;
	}

	public void setCD(String cD) {
		CD = cD;
	}

	public boolean isCura() {
		return Cura;
	}

	public void setCura(boolean cura2) {
		this.Cura = cura2;
	}

	public int getBonusMAC() {
		return bonusMAC;
	}

	public void setBonusMAC(int bonusMAC) {
		this.bonusMAC = bonusMAC;
	}

	public int getBonusMHP() {
		return bonusMHP;
	}

	public void setBonusMHP(int bonusMHP) {
		this.bonusMHP = bonusMHP;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public String getTipoDeDano() {
		return tipoDeDano;
	}

	public void setTipoDeDano(String tipoDeDano) {
		this.tipoDeDano = tipoDeDano;
	}

	public int getnDdos() {
		return nDados;
	}

	public void setnDdos(int nDdos) {
		this.nDados = nDdos;
	}

	public int getEspacoDeMagia() {
		return espacoDeMagia;
	}

	public void setEspacoDeMagia(int espacoDeMagia) {
		this.espacoDeMagia = espacoDeMagia;
	}

	public String getNome() {
		return Nome;
	}

	public void setNome(String nome) {
		Nome = nome;
	}

	public boolean getDisparavel() {
		return disparavel;
	}

	public void setDisparavel(boolean disparavel) {
		this.disparavel = disparavel;
	}

	public String getValorDeDado() {
		return valorDeDado;
	}

	public void setValorDeDado(String valorDeDado) {
		this.valorDeDado = valorDeDado;
	}

	public int dadoDano() {

		int dano = 0;
		int i;

		for (i = 0; i < nDados; i++) {

			switch (valorDeDado) {
			
			case "1":
				dano=dano+1;

			case DadosConstantes.D4N:
				dano = dano + Dado.D4();
				break;

			case DadosConstantes.D6N:
				dano = dano + Dado.D6();
				break;

			case DadosConstantes.D8N:
				dano = dano + Dado.D8();
				break;

			case DadosConstantes.D10N:
				dano = dano + Dado.D10();
				break;

			case DadosConstantes.D12N:
				dano = dano + Dado.D12();
				break;

			}

		}

		return dano;

	}

	public String toString() {

		StringBuilder sb = new StringBuilder();

		sb.append("\n");
		sb.append("dano: ");
		sb.append(valorDeDado);
		sb.append("\n");
		sb.append("Espaço de magia: ");
		sb.append(espacoDeMagia);
		sb.append("\n");
		if (disparavel) {

			sb.append("Disparavel");

		}
		sb.append("\n");
		sb.append("\n");

		return sb.toString();

	}
}
