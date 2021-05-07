package model;

import contantes.DadosConstantes;

public class Item {

	private String nome;
	private int preco;
	private String dadoDeDano;
	private String tipoDano;
	private int slot;
	private int ac;
	private String atributo;
	private String mao;
	private String tipoItem;
	private int NdeDados = 1;
	private DeBuff debuff;
	
	public DeBuff getDebuff() {
		return debuff;
	}

	public void setDebuff(DeBuff debuff) {
		this.debuff = debuff;
	}

	public int getNdeDados() {
		return NdeDados;
	}

	public void setNdeDados(int ndeDados) {
		NdeDados = ndeDados;
	}

	public String getMao() {
		return mao;
	}

	public void setMao(String mao) {
		this.mao = mao;
	}

	public String getAtributo() {
		return atributo;
	}

	public void setAtributo(String atributo) {
		this.atributo = atributo;
	}

	public int getAc() {
		return ac;
	}

	public void setAc(int ac) {
		this.ac = ac;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getPreco() {
		return preco;
	}

	public void setPreco(int preco) {
		this.preco = preco;
	}

	public String getDadoDeDano() {
		return dadoDeDano;
	}

	public void setDadoDeDano(String dano) {
		this.dadoDeDano = dano;
	}

	public String getTipoDano() {
		return tipoDano;
	}

	public void setTipoDano(String tipoDano) {
		this.tipoDano = tipoDano;
	}

	public int getSlot() {
		return slot;
	}

	public void setSlot(int slot) {
		this.slot = slot;
	}

	public String getTipoItem() {
		return tipoItem;
	}

	public void setTipoItem(String tipoItem) {
		this.tipoItem = tipoItem;
	}

	public String toString() {

		StringBuilder sb = new StringBuilder();

		sb.append("\n");
		sb.append("dano: ");
		sb.append(dadoDeDano);
		sb.append("\n");
		sb.append("preço: ");
		sb.append(preco);
		sb.append("\n");
		sb.append("\n");

		return sb.toString();

	}

	public int dadoDano() {

		int dano = 0;

		for (int i = 0; i < NdeDados; i++) {

			switch (dadoDeDano) {
			
			case "1":
				dano=dano+1;
				break;

			case DadosConstantes.D4N:
				dano = dano + Dado.D4();
				break;

			case DadosConstantes.D6N:
				dano = dano + Dado.D6();
				break;

			case DadosConstantes.D8N:
				dano = dano + Dado.D8();
				break;

			case DadosConstantes.D10N:
				dano = dano + Dado.D10();
				break;

			case DadosConstantes.D12N:
				dano = dano + Dado.D12();
				break;

			}

		}
		
		return dano;
	}
}
