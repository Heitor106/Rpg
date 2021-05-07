package model;

public class MonstroPadrao {
	
	private static Monstro monstro;
	
	public static Monstro gobling () {
		
		monstro=new Monstro();
		
		monstro.setNome("Gobling");
		monstro.setHp(7);
		monstro.setAc(15);
		monstro.setStr(8);
		monstro.setDex(14);
		monstro.setInte(8);
		monstro.setWis(8);		
		monstro.addItem(ItemPadrao.arcoCurto());
		monstro.addItem(ItemPadrao.cimitarra());
		
		return monstro;
		
	}

}
