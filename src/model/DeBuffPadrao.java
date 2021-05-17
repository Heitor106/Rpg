package model;

import contantes.AtributoConstantes;
import contantes.DebuffConstantes;

public class DeBuffPadrao {

	static DeBuff debuff = new DeBuff();

	public static DeBuff emChamas() {

		debuff = new DeBuff();

		debuff.setNome("Chamas");
		debuff.setDano(1);
		debuff.setDanoOnHit(2);
		debuff.setTime(2);
		debuff.setTipo(DebuffConstantes.VENENO);

		return debuff;
	}
	
	public static DeBuff Veneno() {

		debuff = new DeBuff();

		debuff.setNome("Envenenado");
		debuff.setDano(1);
		debuff.setDanoOnHit(2);
		debuff.setTime(3);
		debuff.setTipo(DebuffConstantes.FOGO);

		return debuff;
	}
	
	public static DeBuff sangramento() {

		debuff = new DeBuff();

		debuff.setNome("Sangramento");
		debuff.setDano(1);
		debuff.setDanoOnHit(1);
		debuff.setTime(3);
		debuff.setTipo(DebuffConstantes.SANGUE);

		return debuff;
	}

	public static DeBuff stun() {

		debuff = new DeBuff();

		debuff.setNome("Stun");
		debuff.setTime(1);
		debuff.setStun(true);

		return debuff;
	}

	public static DeBuff StunTeste() {

		debuff.setNome("Stunado");
		debuff.setTime(1);
		debuff.setStun(true);
		debuff.setTeste(true);
		debuff.setTipoteste(AtributoConstantes.CON);

		return debuff;

	}

	public static DeBuff desvantagem() {

		debuff = new DeBuff();

		debuff.setNome("Desvantagem");
		debuff.setTime(1);
		debuff.setDesvantagem(true);

		return debuff;
	}
	
	public static DeBuff marcaSolar() {

		debuff = new DeBuff();

		debuff.setNome("Marca Solar");
		debuff.setTime(10);
		debuff.setMarcaSolar(true);
		debuff.setDesvantagem(true);
		
		return debuff;
	}
}
