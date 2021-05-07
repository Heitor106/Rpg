package model;

import contantes.AtributoConstantes;

public class Atributo {

	private int str = AtributoConstantes.BASE;
	private int dex = AtributoConstantes.BASE;
	private int con = AtributoConstantes.BASE;
	private int inte = AtributoConstantes.BASE;
	private int wis = AtributoConstantes.BASE;
	private int cha = AtributoConstantes.BASE;

	private int ac = AtributoConstantes.BASE;

	public int getStr() {

		return str;

	}

	public int getDex() {

		return dex;

	}

	public int getCon() {

		return con;

	}

	public int getInte() {

		return inte;

	}

	public int getWis() {

		return wis;

	}

	public int getCha() {

		return cha;

	}

	public int getAc() {

		return ac;

	}

	public int getStrMod() {

		return creatMod(str);

	}

	public int getDexMod() {

		return creatMod(dex);

	}

	public int getConMod() {

		return creatMod(con);

	}

	public int getInteMod() {

		return creatMod(inte);

	}

	public int getWisMod() {

		return creatMod(wis);

	}

	public int getChaMod() {

		return creatMod(cha);

	}

	public void setStr(int str) {

		this.str = str;

	}

	public void setDex(int dex) {

		this.dex = dex;

	}

	public void setCon(int con) {

		this.con = con;

	}

	public void setInte(int inte) {

		this.inte = inte;

	}

	public void setWis(int wis) {

		this.wis = wis;

	}

	public void setCha(int cha) {

		this.cha = cha;

	}

	public void setAc(int ac) {

		this.ac = ac;

	}

	private int creatMod(int atributo) {

		if (atributo == 1) {

			return -5;

		} else if (atributo == 2 || atributo == 3) {

			return -4;

		} else if (atributo == 4 || atributo == 5) {

			return -3;

		} else if (atributo == 6 || atributo == 7) {

			return -2;

		} else if (atributo == 8 || atributo == 9) {

			return -1;

		} else if (atributo == 10 || atributo == 11) {

			return 0;

		} else if (atributo == 12 || atributo == 13) {

			return 1;

		} else if (atributo == 14 || atributo == 15) {

			return 2;

		} else if (atributo == 16 || atributo == 17) {

			return 3;

		} else if (atributo == 18 || atributo == 19) {

			return 4;

		} else if (atributo == 20) {

			return 5;

		}

		return 0;
	}

	public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(AtributoConstantes.STR);
        sb.append(": ");
        sb.append(str);
        sb.append("\n");
        sb.append(AtributoConstantes.DEX);
        sb.append(": ");
        sb.append(dex);
        sb.append("\n");
        sb.append(AtributoConstantes.CON);
        sb.append(": ");
        sb.append(con);
        sb.append("\n");
        sb.append(AtributoConstantes.INT);
        sb.append(": ");
        sb.append(inte);
        sb.append("\n");
        sb.append(AtributoConstantes.WIS);
        sb.append(": ");
        sb.append(wis);
        sb.append("\n");
        sb.append(AtributoConstantes.CHA);
        sb.append(": ");
        sb.append(cha);
        sb.append("\n");
        return sb.toString();
    }
}
