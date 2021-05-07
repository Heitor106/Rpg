package model;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

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
	private Map<String, Magia> magias;
	private Map<String, Skill> skills;
	private Map<String, Bonus> bonuses;
	private Map<String, Item> itens;
	private Map<String, Item> ataquesFisicos;
	private Map<String, Magia> magiasPassivas;
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

	private void setClass(String clase) {

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

		}

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
		;

		Nmagias = 3;
		atributoPadrao = AtributoConstantes.WIS;

		setAc(AtributoConstantes.BASE + getDexMod() + getConMod());

		addSkill(SkillPadrao.Furia());

		addBonus(BonusPadrao.ArmaduraLeve());
		
		addBonus(BonusPadrao.ArmaduraMedia());

		addBonus(BonusPadrao.ArmasSimples());
		
		addBonus(BonusPadrao.ArmasMarciais());
		
		addBonus(BonusPadrao.escudo());

		addItem(ItemPadrao.machadao());

		addItem(ItemPadrao.machadoMao());

		addAquesFisicos(ItemPadrao.Soco());

	}

	public void bardo() {

		nomeDaClasse = ClassConstantes.BARDO;

		hp = super.getHp() + DadosConstantes.D8 + getConMod();
				
		Nmagias = 4;
		atributoPadrao = AtributoConstantes.CHA;
		addAquesFisicos(ItemPadrao.Soco());

		addSkill(SkillPadrao.Inspiração());

		addBonus(BonusPadrao.ArmaduraLeve());

		addBonus(BonusPadrao.Instrumento());

		addBonus(BonusPadrao.ArmasSimples());

		addBonus(BonusPadrao.Arcana());

		addItem(ItemPadrao.espadacurta());

		addItem(ItemPadrao.adaga());

		addItem(ItemPadrao.violao());

		setArmadura(ItemPadrao.ArmaduraDeCouro());

		addMagia(MagiaPadrao.somEstridente());

		addMagiaPassiva(MagiaPadrao.CurarFerimentos());

		addMagiaPassiva(MagiaPadrao.VitalitadeFalsa());

		addMagiaPassiva(MagiaPadrao.ArmaduraDeNotas());
	}

	public void bruxo() {

		nomeDaClasse = ClassConstantes.BRUXO;

		hp = super.getHp() + DadosConstantes.D8 + getConMod();
		Nmagias = 3;
		atributoPadrao = AtributoConstantes.CHA;
		addAquesFisicos(ItemPadrao.Soco());

		addBonus(BonusPadrao.ArmaduraLeve());

		addBonus(BonusPadrao.ArmasSimples());

		addBonus(BonusPadrao.Arcana());

		addItem(ItemPadrao.adaga());

		addItem(ItemPadrao.espadacurta());

		setArmadura(ItemPadrao.ArmaduraDeCouro());

		addMagia(MagiaPadrao.RajadaMistica());

		addMagia(MagiaPadrao.RajadaMisticaF());

		addMagia(MagiaPadrao.RajadaMistica());

		addMagia(MagiaPadrao.EnergiaEscura());

	}
	
	public void clerigo() {

		nomeDaClasse = ClassConstantes.CLERIGO;

		hp = super.getHp() + DadosConstantes.D8 + getConMod();
		Nmagias = 5;
		atributoPadrao = AtributoConstantes.WIS;
		addAquesFisicos(ItemPadrao.Soco());
		
		addBonus(BonusPadrao.ArmaduraLeve());
		
		addBonus(BonusPadrao.ArmaduraMedia());

		addBonus(BonusPadrao.ArmasSimples());
		
		addBonus(BonusPadrao.ArmasMarciais());
		
		addBonus(BonusPadrao.escudo());
		
		addItem(ItemPadrao.Maca());
		
		addItem(ItemPadrao.Escudosimples());
		
		setArmadura(ItemPadrao.ArmaduraDeCouro());
		
		setEscudos(ItemPadrao.Escudosimples());
		
		addMagiaPassiva(MagiaPadrao.CuraSagrada());
		
		addMagiaPassiva(MagiaPadrao.VitalitadeFalsa());

		addMagia(MagiaPadrao.RaioDeLuz());
		
		addMagia(MagiaPadrao.MarcaSolar());	
				
		addSkill(SkillPadrao.ProtegidoPelaLuz());
		
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
