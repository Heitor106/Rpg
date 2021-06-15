package model;

import contantes.DadosConstantes;

public class Skill {

	private int bonusDano;
	private String atributo;
	private int espacoDeSkill;
	private String Nome;
	private int time;
	private String dado;
	private boolean bonusDado = false;
	private boolean alvos=false;
	private int bonusVida;
	private int bonusAtributo;
	private int bonusAc;
	private String tiposkill;
	private boolean alvosMultiplos=false;
	
	public boolean isAlvosMultiplos() {
		return alvosMultiplos;
	}

	public void setAlvosMultiplos(boolean alvosMultiplos) {
		this.alvosMultiplos = alvosMultiplos;
	}

	public String getDado() {
		return dado;
	}

	public void setDado(String Dado) {
		dado = Dado;
	}

	public boolean isAlvos() {
		return alvos;
	}

	public void setAlvos(boolean alvos) {
		this.alvos = alvos;
	}

	public boolean isBonusDado() {
		return bonusDado;
	}

	public void setBonusDado(boolean bonusDado) {
		this.bonusDado = bonusDado;
	}

	public int getEspacoDeSkill() {
		return espacoDeSkill;
	}

	public void setEspacoDeSkill(int espacoDeSkill) {
		this.espacoDeSkill = espacoDeSkill;
	}

	public String getTiposkill() {
		return tiposkill;
	}

	public void setTiposkill(String tiposkill) {
		this.tiposkill = tiposkill;
	}

	public int getBonusAc() {
		return bonusAc;
	}

	public void setBonusAc(int bonusAc) {
		this.bonusAc = bonusAc;
	}

	public int getBonusDano() {
		return bonusDano;
	}

	public void setBonusDano(int bonusDano) {
		this.bonusDano = bonusDano;
	}

	public int getBonusVida() {
		return bonusVida;
	}

	public void setBonusVida(int bonusVida) {
		this.bonusVida = bonusVida;
	}

	public int getBonusAtributo() {
		return bonusAtributo;
	}

	public void setBonusAtributo(int bonusAtributo) {
		this.bonusAtributo = bonusAtributo;
	}

	public int getBonus() {
		return bonusDano;
	}

	public void setBonus(int bonus) {
		this.bonusDano = bonus;
	}

	public String getAtributo() {
		return atributo;
	}

	public void setAtributo(String atributo) {
		this.atributo = atributo;
	}

	public String getNome() {
		return Nome;
	}

	public void setNome(String nome) {
		Nome = nome;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}
	
	public int dadoBonus() {

		switch (dado) {

		case DadosConstantes.D4N:
			return Dado.D4();

		case DadosConstantes.D6N:
			return Dado.D6();

		case DadosConstantes.D8N:
			return Dado.D8();

		case DadosConstantes.D10N:
			return Dado.D10();

		case DadosConstantes.D12N:
			return Dado.D12();

		}

		return -1;

	}

}
