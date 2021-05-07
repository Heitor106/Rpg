package model;

import contantes.BonusConstantes;
import contantes.DadosConstantes;
import contantes.ItensConstates;

public class BonusPadrao {

	static Bonus bonus;

	public static Bonus ArmaduraLeve() {

		bonus = new Bonus();

		bonus.setNome(ItensConstates.ARMADURAL);
		bonus.setBonus(BonusConstantes.BONUS);

		return bonus;

	}

	public static Bonus ArmaduraMedia() {

		bonus = new Bonus();

		bonus.setNome(ItensConstates.ARMADURAM);
		bonus.setBonus(BonusConstantes.BONUS);

		return bonus;

	}

	public static Bonus ArmasMarciais() {

		bonus = new Bonus();

		bonus.setNome(ItensConstates.ARMAM);
		bonus.setBonus(BonusConstantes.BONUS);

		return bonus;

	}

	public static Bonus ArmaduraPesada() {

		bonus = new Bonus();

		bonus.setNome(ItensConstates.ARMADURAP);
		bonus.setBonus(BonusConstantes.BONUS);

		return bonus;

	}

	public static Bonus ArmasSimples() {

		bonus = new Bonus();

		bonus.setNome(ItensConstates.ARMAS);
		bonus.setBonus(BonusConstantes.BONUS);

		return bonus;

	}

	public static Bonus escudo() {

		bonus = new Bonus();

		bonus.setNome(ItensConstates.ESCUDO);
		bonus.setBonus(BonusConstantes.BONUS);

		return bonus;

	}

	public static Bonus ResistenciStr() {

		bonus = new Bonus();

		bonus.setNome(BonusConstantes.RFORCA);
		bonus.setBonus(BonusConstantes.BONUS);

		return bonus;

	}

	public static Bonus ResistenciDex() {

		bonus = new Bonus();

		bonus.setNome(BonusConstantes.RDEX);
		bonus.setBonus(BonusConstantes.BONUS);

		return bonus;

	}

	public static Bonus ResistenciCon() {

		bonus = new Bonus();

		bonus.setNome(BonusConstantes.RCON);
		bonus.setBonus(BonusConstantes.BONUS);

		return bonus;

	}

	public static Bonus ResistenciInt() {

		bonus = new Bonus();

		bonus.setNome(BonusConstantes.RINT);
		bonus.setBonus(BonusConstantes.BONUS);

		return bonus;

	}

	public static Bonus ResistenciaWis() {

		bonus = new Bonus();

		bonus.setNome(BonusConstantes.RWIS);
		bonus.setBonus(BonusConstantes.BONUS);

		return bonus;

	}

	public static Bonus ResistenciCha() {

		bonus = new Bonus();

		bonus.setNome(BonusConstantes.RCHA);
		bonus.setBonus(BonusConstantes.BONUS);

		return bonus;

	}

	public static Bonus Arcana() {

		bonus = new Bonus();
		bonus.setNome(BonusConstantes.ARCANA);
		bonus.setBonus(BonusConstantes.BONUS);

		return bonus;
	}
	
	public static Bonus Instrumento() {
		
		bonus = new Bonus();
		bonus.setNome("Instrumento");
		bonus.setBonus(BonusConstantes.BONUS);
		return bonus;
		
	}

}
