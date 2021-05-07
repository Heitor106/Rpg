package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import contantes.AtributoConstantes;
import contantes.RacaConstantes;

public class Raca extends Atributo {

	private String nomeRaca;
	private int hp=0;
	private int skillClase=1;
	private List<Bonus>bonusR =new ArrayList<Bonus>();
	private List<Magia>magiasR=new ArrayList<Magia>();

	public Raca(String raca, String atributo1, String atributo2) {

		setRace(raca, atributo1, atributo2);

	}

	private void setRace(String raca, String atributo1, String atributo2) {

		switch (raca) {

		case RacaConstantes.ANAOC:
			anaoColina();
			break;
			
		case RacaConstantes.ANAOM:
			anaoColina();
			break;

		case RacaConstantes.ELFOA:
			elfoAlto();
			
			break;
			
		case RacaConstantes.ELFOD:
			elfoDrow();
			
			break;
			
		case RacaConstantes.ELFOF:
			elfoFloresta();
			
			break;
	
		case RacaConstantes.HOBBITP:
			hobbitP();

			break;
			
		case RacaConstantes.HOBBITC:
			hobbitC();

			break;

		case RacaConstantes.HUMANO:
			humano();

			break;

		case RacaConstantes.DRACONATO:
			draconato();

			break;

		case RacaConstantes.GNOMOF:
			gnomoF();

			break;

		case RacaConstantes.MEIO_ELFO:
			meioElfo(atributo1, atributo2);

			break;

		case RacaConstantes.MEIO_ORC:
			meioOrc();

			break;
		}

	}

	public void anaoColina() {

		nomeRaca = RacaConstantes.ANAOC;
		
		setHp(1);
		setCon(getCon() + 4);
		setWis(getWis() + 2);

	}
	
	public void anaoMontnha() {

		nomeRaca = RacaConstantes.ANAOM;
		
		setCon(getCon() + 4);
		setStr(getStr() + 4);
		addBonusR(BonusPadrao.ArmaduraLeve());
		addBonusR(BonusPadrao.ArmaduraMedia());

	}

	public void elfoAlto() {

		nomeRaca = RacaConstantes.ELFOA;

		setDex(getDex() + 4);
		setInte(getInte() + 2);
		magiasR.add(MagiaPadrao.ToqueChocante());
		addBonusR(BonusPadrao.ArmasSimples());
	}

	public void elfoFloresta() {

		nomeRaca = RacaConstantes.ELFOF;

		setDex(getDex() + 4);
		setWis(getWis() + 2);
		addBonusR(BonusPadrao.ArmasSimples());
		addBonusR(BonusPadrao.ArmaduraLeve());

	}
	
	public void elfoDrow() {

		nomeRaca = RacaConstantes.ELFOD;

		setDex(getDex() + 4);
		setCha(getCha() + 2);
		addBonusR(BonusPadrao.ArmasSimples());
	
	}



	public void hobbitP() {

		nomeRaca = RacaConstantes.HOBBITP;

		setDex(getDex() + 4);
		setCha(getCha() + 2);

	}
	
	public void hobbitC() {

		nomeRaca = RacaConstantes.HOBBITC;

		setDex(getDex() + 4);
		setCon(getCon() + 2);

	}


	public void humano() {

		nomeRaca = RacaConstantes.HUMANO;

		setDex(getDex() + 2);
		setCon(getCon() + 2);
		setCha(getCha() + 2);
		setWis(getWis() + 2);
		setStr(getStr() + 2);
		setInte(getInte() + 2);

	}

	public void draconato() {

		nomeRaca = RacaConstantes.DRACONATO;
	
		magiasR.add(MagiaPadrao.sopro());
		setStr(getStr() + 4);
		setCha(getCha() + 2);

	}

	public void gnomoF() {

		nomeRaca = RacaConstantes.GNOMOF;

		setInte(getInte() + 4);
		setDex(getDex() + 2);
		magiasR.add(MagiaPadrao.ToqueChocante());
	}
	
	public void gnomoR() {

		nomeRaca = RacaConstantes.GNOMOF;

		setInte(getInte() + 4);
		setDex(getDex() + 2);
		addBonusR(BonusPadrao.ArmasMarciais());
		
	}


	public void meioElfo(String atributo1, String atributo2) {

		nomeRaca = RacaConstantes.MEIO_ELFO;

		setAtributo(atributo1);
		setAtributo(atributo2);

		setCha(getCha() + 4);

	}

	public void meioOrc() {

		nomeRaca = RacaConstantes.MEIO_ORC;

		setStr(getStr() + 2);
		setCon(getCon() + 4);

	}

	public void tiefling() {

		setInte(getInte() + 4);
		setCha(getCha() + 2);

	}

	private void setAtributo(String atributo) {

		switch (atributo) {

		case AtributoConstantes.STR:
			setStr(getStr() + 2);
			break;

		case AtributoConstantes.DEX:
			setDex(getDex() + 1);
			break;

		case AtributoConstantes.INT:
			setInte(getInte() + 1);
			break;

		case AtributoConstantes.WIS:
			setWis(getWis() + 1);
			break;

		case AtributoConstantes.CON:
			setCon(getCon() + 1);
			break;

		}

	}
	
	

	public List<Magia> getMagiasR() {
		return magiasR;
	}

	public void addMagiaR(Magia magia) {

		if (magiasR == null) {

			magiasR = new ArrayList();
		}

		magiasR.add(magia);

	}

	public String getNomeRaca() {

		return nomeRaca;

	}

	public String toString() {
		return super.toString();
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public List<Bonus> getBonusR() {
		return bonusR;
	}
	
	public void addBonusR(Bonus bonus) {

		if (bonusR == null) {

			bonusR = new ArrayList();
		}

		bonusR.add(bonus);

	}

	public int getSkillClase() {
		return skillClase;
	}

	public void setSkillClase(int skillClase) {
		this.skillClase = skillClase;
	}
	
	
	
}
