package model;

import java.util.HashMap;
import java.util.Map;

public class Monstro extends Atributo {

	private Map<String, Item> itens;

	private int hp;
	private String nome;

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Map<String, Item> getItens() {
		return itens;
	}

	public void setItens(Map<String, Item> itens) {
		this.itens = itens;
	}

	public void addItem(Item item) {

		if (itens == null) {

			itens = new HashMap<>();

		}
		
		itens.put(item.getNome(),item);

	}
	public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n");
        sb.append("MONSTRO: ");
        sb.append("\n");
        sb.append("Nome: ");
        sb.append(nome);
        sb.append("\n");
        sb.append("Hit Points: ");
        sb.append(hp);
        sb.append("\n");
        sb.append(super.toString());
        sb.append("\n");
        
        for (Map.Entry<String, Item> item : itens.entrySet()) {
            sb.append(item.toString());
            sb.append("\n");
        }
        sb.append("\n");
        
        return sb.toString();
    }

}
