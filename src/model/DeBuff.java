package model;

public class DeBuff {

	private int Time;
	private int dano;
	private int danoOnHit;
	private String tipo;
	private String nome;
	private boolean stun;
	private boolean teste;
	private boolean desvantagem;
	private String Tipoteste;
	private boolean MarcaSolar;
	
	
	
	public boolean isMarcaSolar() {
		return MarcaSolar;
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

	public String getTipoteste() {
		return Tipoteste;
	}

	public void setTipoteste(String tipoteste) {
		Tipoteste = tipoteste;
	}

	public boolean isTeste() {
		return teste;
	}

	public void setTeste(boolean teste) {
		this.teste = teste;
	}

	public int getDanoOnHit() {
		return danoOnHit;
	}

	public void setDanoOnHit(int danoOnHit) {
		this.danoOnHit = danoOnHit;
	}

	public boolean isStun() {
		return stun;
	}

	public void setStun(boolean stun) {
		this.stun = stun;
	}

	public int getTime() {
		return Time;
	}

	public void setTime(int time) {
		Time = time;
	}

	public int getDano() {
		return dano;
	}

	public void setDano(int dano) {
		this.dano = dano;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
