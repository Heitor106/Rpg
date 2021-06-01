package model;

import contantes.AtributoConstantes;
import contantes.DadosConstantes;
import contantes.ItensConstates;

public class ItemPadrao {

	static Item item;
	
	public static Item Escudosimples () {
		
		item=new Item();
		
		item.setNome("Escudo");
		item.setDadoDeDano(DadosConstantes.D4N);
		item.setTipoDano(ItensConstates.DANO_F);
		item.setPreco(2);
		item.setSlot(1);
		item.setAtributo(AtributoConstantes.DEX);
		item.setMao(ItensConstates.MAO1);
		item.setTipoItem(ItensConstates.ARMAS);
		item.setAc(2);
		return item;
		
	}
	
	public static Item adaga() {
		
		item=new Item();
		
		item.setNome("Adaga");
		item.setDadoDeDano(DadosConstantes.D4N);
		item.setTipoDano(ItensConstates.DANO_F);
		item.setPreco(2);
		item.setSlot(1);
		item.setAtributo(AtributoConstantes.DEX);
		item.setMao(ItensConstates.MAO1);
		item.setTipoItem(ItensConstates.ARMAS);
		
		return item;
	}
	
	public static Item bastao() {
		
		item=new Item();
		
		item.setNome("Bastão");
		item.setDadoDeDano(DadosConstantes.D8N);
		item.setTipoDano(ItensConstates.DANO_F);
		item.setPreco(2);
		item.setSlot(1);
		item.setAtributo(AtributoConstantes.DEX);
		item.setMao(ItensConstates.MAO2);
		item.setTipoItem(ItensConstates.ARMAS);
		
		return item;
	}
	
	public static Item palmaRapida() {
		
		item=new Item();
		
		item.setNome("Palma Rapida");
		item.setDadoDeDano(DadosConstantes.D4N);
		item.setTipoDano(ItensConstates.DANO_F);
		item.setPreco(2);
		item.setSlot(1);
		item.setAtributo(AtributoConstantes.DEX);
		item.setMao(ItensConstates.MAO1);
		item.setTipoItem(ItensConstates.ARMAS);
		
		return item;
		
		
	}
	
	public static Item espadaSagrada() {
		
		item=new Item();
		
		item.setNome("Espada Sagrada");
		item.setDadoDeDano(DadosConstantes.D8N);
		item.setTipoDano(ItensConstates.DANO_M);
		item.setPreco(20);
		item.setSlot(1);
		item.setAtributo(AtributoConstantes.STR);
		item.setMao(ItensConstates.MAO1);
		item.setTipoItem(ItensConstates.ARMAM);
		
		return item;

	}
	
	public static Item cimitarra() {
		
		item=new Item();
		
		item.setNome("Cimitarra");
		item.setDadoDeDano(DadosConstantes.D6N);
		item.setTipoDano(ItensConstates.DANO_F);
		item.setPreco(25);
		item.setSlot(1);
		item.setAtributo(AtributoConstantes.DEX);
		item.setMao(ItensConstates.MAO1);
		item.setTipoItem(ItensConstates.ARMAM);
		
		return item;
	}
	
	public static Item rapieira() {
		
		item=new Item();
		
		item.setNome("Rapieira");
		item.setDadoDeDano(DadosConstantes.D8N);
		item.setTipoDano(ItensConstates.DANO_F);
		item.setPreco(25);
		item.setSlot(1);
		item.setAtributo(AtributoConstantes.DEX);
		item.setMao(ItensConstates.MAO1);
		item.setTipoItem(ItensConstates.ARMAM);
		
		return item;
	}

	
	public static Item arcoCurto() {
		
		item=new Item();
		
		item.setNome("Arco_curto");
		item.setDadoDeDano(DadosConstantes.D6N);
		item.setTipoDano(ItensConstates.DANO_F);
		item.setPreco(25);
		item.setSlot(1);
		item.setAtributo(AtributoConstantes.DEX);
		item.setMao(ItensConstates.MAO2);
		item.setTipoItem(ItensConstates.ARMAS);
		
		return item;
	}
	
	public static Item machadao() {
		
		item=new Item();
		
		item.setNome("Machadão");
		item.setDadoDeDano(DadosConstantes.D12N);
		item.setTipoDano(ItensConstates.DANO_F);
		item.setPreco(30);
		item.setSlot(1);
		item.setAtributo(AtributoConstantes.STR);
		item.setMao(ItensConstates.MAO2);
		item.setTipoItem(ItensConstates.ARMAM);
		
		return item;
	}
	
	public static Item espadaGrande() {
		
		item=new Item();
		
		item.setNome("Espadão");
		item.setDadoDeDano(DadosConstantes.D10N);
		item.setTipoDano(ItensConstates.DANO_F);
		item.setPreco(30);
		item.setSlot(1);
		item.setAtributo(AtributoConstantes.STR);
		item.setMao(ItensConstates.MAO2);
		item.setTipoItem(ItensConstates.ARMAM);
		
		return item;
	}
	
	public static Item machadoMao() {
		
		item=new Item();
		
		item.setNome("Machado_de_mão");
		item.setDadoDeDano(DadosConstantes.D6N);
		item.setTipoDano(ItensConstates.DANO_F);
		item.setPreco(30);
		item.setSlot(1);
		item.setAtributo(AtributoConstantes.STR);
		item.setMao(ItensConstates.MAO2);
		item.setTipoItem(ItensConstates.ARMAS);
		item.setDebuff(DeBuffPadrao.emChamas());
		
		return item;
	}
	
	public static Item espadin() {
		
		item=new Item();
		
		item.setNome("Espadin");
		item.setDadoDeDano(DadosConstantes.D8N);
		item.setTipoDano(ItensConstates.DANO_F);
		item.setPreco(20);
		item.setSlot(1);
		item.setAtributo(AtributoConstantes.STR);
		item.setMao(ItensConstates.MAO1);
		item.setTipoItem(ItensConstates.ARMAS);
		
		return item;
	}
	
	public static Item violao() {
		
		item=new Item();
		
		item.setNome("violão");
		item.setDadoDeDano(DadosConstantes.D4N);
		item.setTipoDano(ItensConstates.DANO_M);
		item.setPreco(10);
		item.setSlot(1);
		item.setAtributo(AtributoConstantes.CHA);
		item.setMao(ItensConstates.MAO1);
		item.setTipoItem(ItensConstates.INSTRUMENTO);
		
		return item;
		
		
	}
	
	public static Item ArmaduraDeCouro() {
		
		item=new Item();
		
		item.setNome("Armadura de Couro");
		item.setPreco(30);
		item.setAc(11);
		item.setTipoItem(ItensConstates.ARMADURAL);
		
		return item;
	}
	
	public static Item ArmaduraDeMalha() {
		
		item=new Item();
		
		item.setNome("Armadura de Malha");
		item.setPreco(30);
		item.setAc(16);
		item.setTipoItem(ItensConstates.ARMADURAP);
		
		return item;
	}
	
	public static Item Saltitante() {
		
		item=new Item();
		
		item.setNome("Saltitante");
		item.setPreco(0);
		item.setAc(18);
		item.setTipoItem(ItensConstates.ARMADURAL);
		
		return item;
	}
	
	public static Item PeleGrossa() {
		
		item=new Item();
		
		item.setNome("Pele Grossa");
		item.setPreco(0);
		item.setAc(14);
		item.setTipoItem(ItensConstates.ARMADURAL);
		
		return item;
	}
	
	public static Item Soco() {
		
		item=new Item();
		
		item.setNome("soco");
		item.setDadoDeDano("1");
		item.setTipoDano(ItensConstates.DANO_F);
		item.setPreco(00);
		item.setSlot(0);
		item.setAtributo(AtributoConstantes.STR);
		item.setMao(ItensConstates.MAO1);
		item.setTipoItem(ItensConstates.ARMAS);
		
		return item;
	}
	
	public static Item espadaCurta() {
		
		item=new Item();
		
		item.setNome("Espada curta");
		item.setDadoDeDano(DadosConstantes.D6N);
		item.setTipoDano(ItensConstates.DANO_F);
		item.setPreco(20);
		item.setSlot(1);
		item.setAtributo(AtributoConstantes.STR);
		item.setMao(ItensConstates.MAO1);
		item.setTipoItem(ItensConstates.ARMAS);
		
		return item;

	}
	
	public static Item Maca() {
		
		item=new Item();
		
		item.setNome("Maça");
		item.setDadoDeDano(DadosConstantes.D6N);
		item.setTipoDano(ItensConstates.DANO_F);
		item.setPreco(20);
		item.setSlot(1);
		item.setAtributo(AtributoConstantes.STR);
		item.setMao(ItensConstates.MAO1);
		item.setTipoItem(ItensConstates.ARMAS);
		
		return item;

	}
	
	public static Item garra() {
		
		item=new Item();
		
		item.setNome("Garra");
		item.setDadoDeDano(DadosConstantes.D6N);
		item.setTipoDano(ItensConstates.DANO_F);
		item.setPreco(0);
		item.setSlot(1);
		item.setAtributo(AtributoConstantes.STR);
		item.setMao(ItensConstates.MAO1);
		item.setTipoItem(ItensConstates.ARMAS);
		
		return item;

	}
	
	public static Item arrahao() {
		
		item=new Item();
		
		item.setNome("Arranão");
		item.setDadoDeDano(DadosConstantes.D10N);
		item.setTipoDano(ItensConstates.DANO_F);
		item.setPreco(0);
		item.setSlot(1);
		item.setAtributo(AtributoConstantes.DEX);
		item.setMao(ItensConstates.MAO1);
		item.setTipoItem(ItensConstates.ARMAM);
		
		return item;

	}
	
	public static Item mordidinha() {
		
		item=new Item();
		
		item.setNome("Arranão");
		item.setDadoDeDano(DadosConstantes.D4N);
		item.setTipoDano(ItensConstates.DANO_F);
		item.setPreco(0);
		item.setSlot(1);
		item.setAtributo(AtributoConstantes.DEX);
		item.setMao(ItensConstates.MAO1);
		item.setTipoItem(ItensConstates.ARMAM);
		
		return item;

	}
	
	
	
}
