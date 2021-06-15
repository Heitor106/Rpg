package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;



import contantes.ItensConstates;

public class Criatura extends Class implements Comparable<Criatura> {

	private String nome;
	private int iniciativa;
	private List<String>Ataquei = new ArrayList<>();
	private List<String>meAtacaaram = new ArrayList<>();
	private List < Skill > buffs = new ArrayList();
	private List < Magia> buffsM = new ArrayList();
	private List <DeBuff> debuffs= new ArrayList();
	private boolean amigo;
	private boolean MarcaSolar = false;
	private boolean desvantagem = false;
	private boolean stunado = false;
	private boolean EmFuga = false;
	private boolean bot = false;
	private int hp = super.getHp();
	
	public boolean isBot() {
		return bot;
	}

	public void setBot(boolean bot) {
		this.bot = bot;
	}

	public boolean isEmFuga() {
		return EmFuga;
	}

	public void setEmFuga(boolean emFuga) {
		EmFuga = emFuga;
	}

	public Criatura(Criatura criatura) {
		this(criatura.getNome(),criatura.getNomeRaca(), "", "", criatura.getNomeDaClasse(),criatura.isAmigo());
		this.hp = criatura.getHp();
		this.iniciativa = criatura.getIniciativa();
	}

	public Criatura(String nome, String raca, String clase, boolean amigo) {
		this(nome, raca, "", "", clase, amigo);

	}

	public Criatura(String nome, String raca, String atributo1, String atributo2, String clase, boolean amigo) {
		super(raca, atributo1, atributo2, clase);
		this.nome = nome;
		this.amigo = amigo;
	}
	
	public boolean isMarcaSolar() {
			
		for (DeBuff debuffs : getDebuffs()) {

			if (debuffs.isMarcaSolar() && debuffs.getTime() > 0) {
				
				return true;
			
			}
		}
		
		return false;
	}

	public void setMarcaSolar(boolean marcaSolar) {
		MarcaSolar = marcaSolar;
	}

	public boolean isDesvantagem() {
		
		return desvantagem;
	}

	public void setDesvantagem(boolean desvantagem) {
		this.desvantagem = desvantagem;
	}

	public boolean isStunado() {
		
		for (DeBuff debuffs :getDebuffs()) {

			if (debuffs.isStun() && debuffs.getTime() > 0) {

				return true;
			}
			
		}
		return false;
		
	}

	public void setStunado(boolean stunado) {
		this.stunado = stunado;
	}

	public List<Magia> getBuffsM() {
		return buffsM;
	}

	public void setBuffsM(List<Magia> buffsM) {
		this.buffsM = buffsM;
	}

	public void setBuffs(List<Skill> buffs) {
		this.buffs = buffs;
	}

	public boolean isAmigo() {
		return amigo;
	}

	public void setAmigo(boolean amigo) {
		this.amigo = amigo;
	}


	public List< Skill> getBuffs() {
		return buffs;
	}
	
	public void addBuffs(Skill skill) {

		if (buffs== null) {

			buffs = new ArrayList<>(2);
		}

		buffs.add(skill);

	}
	
	public void removeBuff(Skill skill) {
		
		if (buffs!= null) {

			buffs.remove(skill);
			
		}
		
	}
	
	public void addBuffsM(Magia magia) {

		if (buffsM== null) {

			buffsM = new ArrayList<>(2);
		}

		buffsM.add(magia);

	}

	public String getNome() {
		return nome;
	}

	public int getIniciativa() {
		return iniciativa;
	}

	public void setIniciativa(int iniciativa) {
		this.iniciativa = iniciativa;
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public void danoRecebido(int dano) {
		this.hp = hp - dano;
	}
	
	public void vidaGanha(int cura) {
		
		this.hp = hp + cura;
		
		if(this.hp>=super.getHp()) {
			
			this.hp=super.getHp();
			
		}
		
	}
	
	public void addDeBuffs(DeBuff deBuff) {

		if (debuffs== null) {

			debuffs = new ArrayList<>(2);
		}
				
			desvantagem=deBuff.isDesvantagem();
	

		debuffs.add(deBuff);

	}

	public List<DeBuff> getDebuffs() {
		return debuffs;
	}
	

	public void setDebuffs(List<DeBuff> debuffs) {
		this.debuffs = debuffs;
	}
	
	public List<String> getAtaquei() {
		return Ataquei;
	}

	public void setAtaquei(List<String> ataquei) {
		Ataquei = ataquei;
	}

	public List<String> getMeAtacaaram() {
		return meAtacaaram;
	}

	public void setMeAtacaaram(List<String> meAtacaaram) {
		this.meAtacaaram = meAtacaaram;
	}

	public String toString() {

		StringBuilder sb = new StringBuilder();

		sb.append("Nome: ");
		sb.append(nome);
		sb.append("\n");
		sb.append("Raça: ");
		sb.append(getNomeRaca());
		sb.append("\n");
		sb.append("Classe: ");
		sb.append(getNomeDaClasse());
		sb.append("\n");
		sb.append("Vida: ");
		sb.append(getHp());
		sb.append("\n");
		sb.append("iniciativa: ");
		sb.append(iniciativa);
		sb.append("\n");
		sb.append(super.toString());
		sb.append("\n");
		sb.append("itens: ");
		sb.append("\n");
		sb.append("\n");

		for (Map.Entry<String, Item> item : getItens().entrySet()) {

			sb.append(item.toString());
			sb.append("\n");

		}
		sb.append("\n");

		return sb.toString();

	}

	public int compareTo(Criatura criatura) {

		if (this.iniciativa > criatura.getIniciativa()) {
			return -1;
		}
		if (this.iniciativa < getIniciativa()) {
			return 1;
		}
		return 0;

	}
	
}
