package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import contantes.AtributoConstantes;
import contantes.ClassConstantes;
import contantes.DadosConstantes;
import contantes.ItensConstates;

public class Class extends Raca {

	private String nomeDaClasse;
	private int hp;
	private int Nmagias;
	private String atributoPadrao = "";
	private Item armadura;
	private Item escudos;
	private String fonteDePoder;
	private Map<String, Magia> magias = new HashMap<>();
	private Map<String, Skill> skills = new HashMap<>();
	private Map<String, Bonus> bonuses = new HashMap<>();
	private Map<String, Item> itens = new HashMap<>();
	private Map<String, Item> ataquesFisicos = new HashMap<>();
	private Map<String, Magia> magiasPassivas = new HashMap<>();
	private int CDTR = 8 + ModPadrao() + 2;
	

	public String getAtributoPadrao() {
		return atributoPadrao;
	}

	public int getNmagias() {
		return Nmagias;
	}

	public void setNmagias(int nmagias) {
		Nmagias = nmagias;
	}

	public Class(String raca, String atributo1, String atributo2, String clase) {
		super(raca, atributo1, atributo2);
		setClass(clase);
		JuntaMagias();

	}

	public String getNomeDaClasse() {
		return nomeDaClasse;
	}

	public void addSkill(Skill skill) {

		if (skills == null) {

			skills = new HashMap<>();
		}

		skills.put(skill.getNome(), skill);

	}

	public int getHp() {
		return hp;
	}

	public void addMagia(Magia magia) {

		if (magias == null) {

			magias = new HashMap<>();
		}

		magias.put(magia.getNome(), magia);

	}

	public void addMagiaPassiva(Magia magia) {

		if (magiasPassivas == null) {

			magiasPassivas = new HashMap<>();
		}

		magiasPassivas.put(magia.getNome(), magia);

	}

	public void addBonus(Bonus bonus) {

		if (bonuses == null) {

			bonuses = new HashMap<>();
		}

		bonuses.put(bonus.getNome(), bonus);

	}

	public Map<String, Bonus> getBonuses() {
		return bonuses;
	}

	public void addAquesFisicos(Item item) {
		if (ataquesFisicos == null) {

			ataquesFisicos = new HashMap<String, Item>();
		}

		ataquesFisicos.put(item.getNome(), item);

	}

	public Map<String, Item> getAtaquesFisicos() {
		return ataquesFisicos;
	}

	public void setAtaquesFisicos(Map<String, Item> ataquesFisicos) {
		this.ataquesFisicos = ataquesFisicos;
	}

	public void addItem(Item item) {

		if (itens == null) {

			itens = new HashMap<String, Item>();
		}

		itens.put(item.getNome(), item);

	}

	public Map<String, Item> getItens() {
		return itens;
	}

	public void setItens(Map<String, Item> itens) {
		this.itens = itens;
	}

	public void setClass(String clase) {

		switch (clase) {

		case ClassConstantes.BARBARO:
			barbaro();

			break;

		case ClassConstantes.BARDO:
			bardo();

			break;

		case ClassConstantes.BRUXO:
			bruxo();

			break;

		case ClassConstantes.CLERIGO:
			clerigo();

			break;
			
		case ClassConstantes.GUERREIRO:
			guerreiro();

			break;
			
		case ClassConstantes.DRUIDA:
			Druida();

			break;
			
		case ClassConstantes.MONGE:
			monge();

			break;
			
		case ClassConstantes.PALADINO:
			paladino();

			break;
			
		case ClassConstantes.LADINO:
			ladino();
			
			break;
			
		case ClassConstantes.PATRULHEIRO:
			Patrulheiro();
			
			break;
						
		case ClassConstantes.MAGO:
			mago();
			
			break;
			
		case ClassConstantes.URSO:
			Urso();

			break;
			
			
		case ClassConstantes.JAGUAR:
			jaguar();

			break;
			
		case ClassConstantes.COELHO:
			coelho();

			break;

		}

	}
	

	public String getFonteDePoder() {
		return fonteDePoder;
	}

	public void setFonteDePoder(String fonteDePoder) {
		this.fonteDePoder = fonteDePoder;
	}

	public Map<String, Magia> getMagiasPassivas() {
		return magiasPassivas;
	}

	public void setMagiasPassivas(Map<String, Magia> magiasPassivas) {
		this.magiasPassivas = magiasPassivas;
	}

	public Map<String, Magia> getMagias() {
		return magias;
	}

	public Map<String, Skill> getSkills() {
		return skills;
	}

	public void barbaro() {

		nomeDaClasse = ClassConstantes.BARBARO;
		hp = super.getHp() + DadosConstantes.D12 + getConMod();
		Nmagias = 3;
		atributoPadrao = AtributoConstantes.WIS;
		fonteDePoder = "Ódio";

		setAc(AtributoConstantes.BASE + getDexMod() + getConMod());

		addSkill(SkillPadrao.furia());

		addBonus(BonusPadrao.ArmaduraLeve());
		
		addBonus(BonusPadrao.ArmaduraMedia());

		addBonus(BonusPadrao.ArmasSimples());
		
		addBonus(BonusPadrao.ArmasMarciais());
		
		addBonus(BonusPadrao.escudo());

		addItem(ItemPadrao.machadao());

		addItem(ItemPadrao.machadoMao());

		addAquesFisicos(ItemPadrao.Soco());

	}
	
	public void monge() {

		nomeDaClasse = ClassConstantes.MONGE;
		hp = super.getHp() + DadosConstantes.D8 + getConMod();
		Nmagias = 4;
		atributoPadrao = AtributoConstantes.WIS;
		fonteDePoder = "pontos de Ki";
		
		setAc(AtributoConstantes.BASE + getDexMod() + getWisMod());

		addSkill(SkillPadrao.palmaFuriosa());

		addBonus(BonusPadrao.ArmaduraLeve());
		
		addBonus(BonusPadrao.ArmaduraMedia());

		addBonus(BonusPadrao.ArmasSimples());
		
		addMagia(MagiaPadrao.KiBlocking());
		
		addMagia(MagiaPadrao.KiStrugle());
		
		addMagiaPassiva(MagiaPadrao.Meditação());

		addItem(ItemPadrao.bastao());

		addAquesFisicos(ItemPadrao.palmaRapida());

	}
	
	public void Patrulheiro() {

		nomeDaClasse = ClassConstantes.PATRULHEIRO;
		hp = super.getHp() + DadosConstantes.D8 + getConMod();
		Nmagias = 4;
		atributoPadrao = AtributoConstantes.WIS;
		fonteDePoder = "flechas mágicas";

		addBonus(BonusPadrao.ArmaduraLeve());
		
		addBonus(BonusPadrao.ArmaduraMedia());
		
		addBonus(BonusPadrao.ArmasSimples());
		
		addBonus(BonusPadrao.ArmasMarciais());
		
		setArmadura(ItemPadrao.ArmaduraDeCarapaca());

		addItem(ItemPadrao.ArcoDeJhin());
		
		addMagia(MagiaPadrao.ShurikenCortante());
		
		addMagiaPassiva(MagiaPadrao.CapusInvisivel());

		addAquesFisicos(ItemPadrao.Soco());
		
		addSkill(SkillPadrao.flechasGemias());

	}
	
	public void guerreiro() {

		nomeDaClasse = ClassConstantes.GUERREIRO;
		hp = super.getHp() + DadosConstantes.D10 + getConMod();
		Nmagias = 3;
		atributoPadrao = AtributoConstantes.INT;
		fonteDePoder = "manobras";

		addSkill(SkillPadrao.surtoDeAcao());

		addBonus(BonusPadrao.ArmaduraLeve());
		
		addBonus(BonusPadrao.ArmaduraMedia());
		
		addBonus(BonusPadrao.ArmaduraPesada());

		addBonus(BonusPadrao.ArmasSimples());
		
		addBonus(BonusPadrao.ArmasMarciais());
		
		addBonus(BonusPadrao.escudo());
		
		setArmadura(ItemPadrao.ArmaduraDeMalha());

		addItem(ItemPadrao.espadaGrande());

		addItem(ItemPadrao.espadaCurta());
		
		addMagia(MagiaPadrao.PuloCeCotovelo());

		addAquesFisicos(ItemPadrao.Soco());
		
		addSkill(SkillPadrao.surtoDeAcao());

	}
		
	public void paladino() {

		nomeDaClasse = ClassConstantes.PALADINO;
		hp = super.getHp() + DadosConstantes.D10 + getConMod();
		Nmagias = 3;
		atributoPadrao = AtributoConstantes.WIS;
		fonteDePoder = "magias";

		addBonus(BonusPadrao.ArmaduraLeve());
		
		addBonus(BonusPadrao.ArmaduraMedia());
		
		addBonus(BonusPadrao.ArmaduraPesada());

		addBonus(BonusPadrao.ArmasSimples());
		
		addBonus(BonusPadrao.ArmasMarciais());
		
		addBonus(BonusPadrao.escudo());
		
		setArmadura(ItemPadrao.ArmaduraDeMalha());
		
		setEscudos(ItemPadrao.Escudosimples());

		addItem(ItemPadrao.espadaSagrada());
		
		addMagiaPassiva(MagiaPadrao.CuraDaLuz());
		
		addAquesFisicos(ItemPadrao.Soco());
		
		addSkill(SkillPadrao.RespeitoDivino());

	}
	
	public void ladino() {

		nomeDaClasse = ClassConstantes.LADINO;
		hp = super.getHp() + DadosConstantes.D8 + getConMod();
		Nmagias = 3;
		atributoPadrao = AtributoConstantes.INT;

		addBonus(BonusPadrao.ArmaduraLeve());

		addBonus(BonusPadrao.ArmasSimples());
		
		addBonus(BonusPadrao.ArmasMarciais());
			
		setArmadura(ItemPadrao.ArmaduraDeCouro());

		addItem(ItemPadrao.rapieira());

		addItem(ItemPadrao.adaga());
		
		addAquesFisicos(ItemPadrao.Soco());
		
		addSkill(SkillPadrao.sombras());
		
		addSkill(SkillPadrao.laminaDupla());
		
	}

	public void bardo() {

		nomeDaClasse = ClassConstantes.BARDO;
		hp = super.getHp() + DadosConstantes.D8 + getConMod();			
		Nmagias = 4;
		atributoPadrao = AtributoConstantes.CHA;
		fonteDePoder = "musicas";
		
		addAquesFisicos(ItemPadrao.Soco());

		addSkill(SkillPadrao.inspiração());

		addBonus(BonusPadrao.ArmaduraLeve());

		addBonus(BonusPadrao.Instrumento());

		addBonus(BonusPadrao.ArmasSimples());

		addBonus(BonusPadrao.Arcana());

		addItem(ItemPadrao.espadaCurta());

		addItem(ItemPadrao.adaga());

		addItem(ItemPadrao.violao());

		setArmadura(ItemPadrao.ArmaduraDeCouro());

		addMagia(MagiaPadrao.somEstridente());

		addMagiaPassiva(MagiaPadrao.Curar());

		addMagiaPassiva(MagiaPadrao.VitalitadeFalsa());

		addMagiaPassiva(MagiaPadrao.ArmaduraDeNotas());
	}
	
	public void mago() {

		nomeDaClasse = ClassConstantes.MAGO;
		hp = super.getHp() + DadosConstantes.D8 + getConMod();
		Nmagias = 8;
		atributoPadrao = AtributoConstantes.INT;
		fonteDePoder = "pontos de poder";
		
		addAquesFisicos(ItemPadrao.Soco());

		addBonus(BonusPadrao.ArmaduraLeve());

		addBonus(BonusPadrao.Instrumento());

		addBonus(BonusPadrao.ArmasSimples());

		addBonus(BonusPadrao.Arcana());
		
		addSkill(SkillPadrao.SustentoM());
		
		addMagia(MagiaPadrao.FocoEnergético());
		
		addMagia(MagiaPadrao.ProgétilGêmio());
		
		addMagia(MagiaPadrao.tiroMágico());
		
		addMagia(MagiaPadrao.EnergiaPura());
		
		addMagia(MagiaPadrao.explosaoRevelante());
		
		addMagia(MagiaPadrao.misseisMagicos());

		addItem(ItemPadrao.adaga());

	}

	public void bruxo() {

		nomeDaClasse = ClassConstantes.BRUXO;
		hp = super.getHp() + DadosConstantes.D8 + getConMod();
		Nmagias = 3;
		atributoPadrao = AtributoConstantes.CHA;
		fonteDePoder = "sacrfícios";
		
		addAquesFisicos(ItemPadrao.Soco());

		addBonus(BonusPadrao.ArmaduraLeve());

		addBonus(BonusPadrao.ArmasSimples());

		addBonus(BonusPadrao.Arcana());

		addItem(ItemPadrao.adaga());

		addItem(ItemPadrao.espadaCurta());

		setArmadura(ItemPadrao.ArmaduraDeCouro());

		addMagia(MagiaPadrao.RajadaMistica());

		addMagia(MagiaPadrao.RajadaMisticaF());

		addMagia(MagiaPadrao.RajadaMistica());

		addMagia(MagiaPadrao.EnergiaEscura());

	}
	
	public void clerigo() {

		nomeDaClasse = ClassConstantes.CLERIGO;
		hp = super.getHp() + DadosConstantes.D8 + getConMod();
		Nmagias = 4;
		atributoPadrao = AtributoConstantes.WIS;
		fonteDePoder = "pedidos divinos";
		
		addAquesFisicos(ItemPadrao.Soco());
		
		addBonus(BonusPadrao.ArmaduraLeve());
		
		addBonus(BonusPadrao.ArmaduraMedia());

		addBonus(BonusPadrao.ArmasSimples());
		
		addBonus(BonusPadrao.escudo());
		
		addItem(ItemPadrao.Maca());
		
		addItem(ItemPadrao.Escudosimples());
		
		setArmadura(ItemPadrao.ArmaduraDeCouro());
		
		setEscudos(ItemPadrao.Escudosimples());
		
		addMagiaPassiva(MagiaPadrao.CuraSagrada());
		
		addMagiaPassiva(MagiaPadrao.VitalitadeFalsa());

		addMagia(MagiaPadrao.RaioDeLuz());
		
		addMagia(MagiaPadrao.MarcaSolar());	
				
		addSkill(SkillPadrao.protegidoPelaLuz());
		
	}
	
	public void Druida() {

		nomeDaClasse = ClassConstantes.DRUIDA;
		hp = super.getHp() + DadosConstantes.D8 + getConMod();
		Nmagias = 5;
		atributoPadrao = AtributoConstantes.WIS;
		fonteDePoder = "forças naturais";
		
		addAquesFisicos(ItemPadrao.Soco());
		
		addBonus(BonusPadrao.ArmaduraLeve());
		
		addBonus(BonusPadrao.ArmaduraMedia());
		
		addBonus(BonusPadrao.ArmasSimples());
		
		setArmadura(ItemPadrao.ArmaduraDeCouro());
		
		addBonus(BonusPadrao.escudo());
		
		setEscudos(ItemPadrao.Escudosimples());
		
		addItem(ItemPadrao.cimitarra());
		
		addMagiaPassiva(MagiaPadrao.CurarFerimentos());
		
		addMagia(MagiaPadrao.ChicoteDeVinhas());
			
		addSkill(SkillPadrao.polimorf());		
				
	}
	
	public void Urso() {

		nomeDaClasse = ClassConstantes.URSO;
		hp = super.getHp() + DadosConstantes.D8*2 + getConMod();
		Nmagias = 2;
		fonteDePoder = "Manobras";
		
		setCon(getCon() + 4);
		setStr(getStr() + 2);
		
		addAquesFisicos(ItemPadrao.garra());
		addMagia(MagiaPadrao.Patada());
		addBonus(BonusPadrao.ArmasMarciais());
		setArmadura(ItemPadrao.ArmaduraDeCouro());
		
	}
	
	public void jaguar() {

		nomeDaClasse = ClassConstantes.JAGUAR;
		hp = super.getHp() + DadosConstantes.D8 + getConMod();
		Nmagias = 2;
		fonteDePoder = "Manobras";
		
		setCon(getDex() + 4);
		setStr(getStr() + 4);
		
		addAquesFisicos(ItemPadrao.arrahao());
		addMagia(MagiaPadrao.Mordida());
		addBonus(BonusPadrao.ArmasMarciais());
		setArmadura(ItemPadrao.ArmaduraDeCouro());
		
	}
	
	public void coelho() {

		nomeDaClasse = ClassConstantes.COELHO;
		hp = super.getHp() + DadosConstantes.D4 + getConMod();
		Nmagias = 2;
		fonteDePoder = "Manobras";
		
		setCon(getDex() + 4);
		
		addAquesFisicos(ItemPadrao.mordidinha());
		addMagia(MagiaPadrao.Encantado());
		addBonus(BonusPadrao.ArmasMarciais());
		setArmadura(ItemPadrao.Saltitante());
		
	}
	

	public Skill getSkill(String skillNome) {

		for (Map.Entry<String, Skill> skill : skills.entrySet()) {

			if (skill.getKey().equals(skillNome)) {

				Skill skillPrincipal = skill.getValue();

				skillPrincipal.setEspacoDeSkill(skillPrincipal.getEspacoDeSkill() - 1);

				skills.remove(skillNome);
				skills.put(skillNome, skillPrincipal);

				return skillPrincipal;

			}
		}

		return null;
	}

	public Item getArmadura() {
		return armadura;
	}

	public void setArmadura(Item armadura) {

		String tipo = armadura.getTipoItem();

		switch (tipo) {

		case ItensConstates.ARMADURAL:
			armadura.setAc(armadura.getAc() + getDexMod());

			break;

		case ItensConstates.ARMADURAM:

			int dex = getDexMod();

			if (dex > 2) {

				dex = 2;
			}

			armadura.setAc(armadura.getAc() + dex);

			break;
		}

		this.armadura = armadura;
	}

	public Item getEscudos() {
		return escudos;
	}

	public void setEscudos(Item escudos) {
		this.escudos = escudos;
	}

	public int getAc() {

		if (escudos != null && armadura != null) {

			return escudos.getAc() + armadura.getAc();

		}

		if (armadura != null) {

			return armadura.getAc();

		}

		if (escudos != null) {

			return super.getAc() + escudos.getAc();

		}

		return super.getAc();

	}

	private void JuntaMagias() {

		for (Magia magia : super.getMagiasR()) {

			addMagia(magia);
		}

	}

	private void JuntaBonus() {

		for (Bonus bonus : super.getBonusR()) {

			addBonus(bonus);
		}
	}

	private int ModPadrao() {

		int dado = 0;

		switch (atributoPadrao) {

		case AtributoConstantes.STR:
			dado = dado + getStrMod();

		case AtributoConstantes.DEX:
			dado = dado + getDexMod();

		case AtributoConstantes.INT:
			dado = dado + getInte();

		case AtributoConstantes.WIS:
			dado = dado + getWisMod();

		case AtributoConstantes.CON:
			dado = dado + getConMod();

		case AtributoConstantes.CHA:
			dado = dado + getChaMod();

		}
		return dado;
	}

	public int getCDTR() {
		return CDTR;
	}

	public void setCDTR(int cDTR) {
		CDTR = cDTR;
	}

	
	

}
