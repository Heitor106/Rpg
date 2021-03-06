package model;

import contantes.AtributoConstantes;
import contantes.DadosConstantes;
import contantes.ItensConstates;
import contantes.MagiasConstantes;

public class MagiaPadrao {

	public static Magia somEstridente() {

		Magia magia = new Magia();

		magia.setNome("Som Estridente");
		magia.setValorDeDado(DadosConstantes.D6N);
		magia.setnDdos(1);
		magia.setEspacoDeMagia(2);
		magia.setCD(AtributoConstantes.INT);
		magia.setDisparavel(false);
		magia.setTipoDeDano(ItensConstates.DANO_M);
		magia.setDebuff(DeBuffPadrao.StunTeste());

		return magia;

	}

	public static Magia Curar() {

		Magia magia = new Magia();

		magia.setNome("Curara ferimentos");
		magia.setValorDeDado(DadosConstantes.D6N);
		magia.setnDdos(1);
		magia.setCura(true);
		magia.setEspacoDeMagia(1);
		magia.setDisparavel(false);
		return magia;

	}
	
	public static Magia Meditação() {

		Magia magia = new Magia();

		magia.setNome("Meditação revitalizante");
		magia.setValorDeDado("1");
		magia.setnDdos(1);
		magia.setCura(true);
		magia.setEspacoDeMagia(1);
		magia.setDisparavel(false);
		return magia;

	}
	
	public static Magia CurarFerimentos() {

		Magia magia = new Magia();

		magia.setNome("Curara ferimentos");
		magia.setValorDeDado(DadosConstantes.D4N);
		magia.setnDdos(1);
		magia.setCura(true);
		magia.setEspacoDeMagia(1);
		magia.setDisparavel(false);
		return magia;

	}
	
	public static Magia CuraDaLuz() {

		Magia magia = new Magia();

		magia.setNome("Cura da Luz");
		magia.setValorDeDado(DadosConstantes.D4N);
		magia.setnDdos(1);
		magia.setCura(true);
		magia.setEspacoDeMagia(0);
		magia.setDisparavel(false);
		return magia;

	}

	public static Magia CuraSagrada() {

		Magia magia = new Magia();

		magia.setNome("Cura Sagrada");
		magia.setValorDeDado(DadosConstantes.D8N);
		magia.setnDdos(1);
		magia.setCura(true);
		magia.setEspacoDeMagia(1);
		magia.setDisparavel(false);
		return magia;

	}

	public static Magia VitalitadeFalsa() {

		Magia magia = new Magia();

		magia.setNome("Vitlidade falsa");
		magia.setBonusMHP(5);
		magia.setTime(4);
		magia.setEspacoDeMagia(1);
		magia.setDisparavel(false);
		return magia;

	}

	public static Magia ArmaduraDeNotas() {

		Magia magia = new Magia();

		magia.setNome("Armadura de notas");
		magia.setBonusMAC(3);
		magia.setTime(3);
		magia.setEspacoDeMagia(1);
		magia.setDisparavel(false);
		return magia;

	}
	
	public static Magia	CapusInvisivel() {

		Magia magia = new Magia();

		magia.setNome("Capus Invisivel");
		magia.setBonusMAC(3);
		magia.setTime(2);
		magia.setEspacoDeMagia(1);
		magia.setDisparavel(false);
		return magia;

	}

	public static Magia sopro() {

		Magia magia = new Magia();

		magia.setNome("Sopro do dragão");
		magia.setValorDeDado(DadosConstantes.D6N);
		magia.setnDdos(2);
		magia.setEspacoDeMagia(0);
		magia.setCD(AtributoConstantes.DEX);
		magia.setDisparavel(false);
		magia.setTipoDeDano(ItensConstates.DANO_M);
		return magia;

	}
	
	public static Magia EnergiaPura() {

		Magia magia = new Magia();

		magia.setNome("Energia pura  ");
		magia.setValorDeDado(DadosConstantes.D4N);
		magia.setnDdos(2);
		magia.setEspacoDeMagia(3);
		magia.setAlvosmultiplos(true);
		magia.setTipoDeDano(ItensConstates.DANO_M);
		return magia;

	}
	
	public static Magia FocoEnergético() {

		Magia magia = new Magia();

		magia.setNome("Foco energético");
		magia.setValorDeDado(DadosConstantes.D12N);
		magia.setnDdos(1);
		magia.setEspacoDeMagia(2);
		magia.setTipoDeDano(ItensConstates.DANO_M);
		return magia;

	}
	
	public static Magia misseisMagicos() {

		Magia magia = new Magia();

		magia.setNome("Misseis mágicos");
		magia.setValorDeDado(DadosConstantes.D4N);
		magia.setnDdos(1);
		magia.setEspacoDeMagia(1);
		magia.setAlvosN(3);
		magia.setDisparavel(true);
		magia.setTipoDeDano(ItensConstates.DANO_M);
		return magia;

	}

	public static Magia Mordida() {

		Magia magia = new Magia();

		magia.setNome("Dente de aço");
		magia.setValorDeDado(DadosConstantes.D6N);
		magia.setnDdos(2);
		magia.setEspacoDeMagia(1);
		magia.setCD(AtributoConstantes.DEX);
		magia.setDisparavel(false);
		magia.setTipoDeDano(ItensConstates.DANO_M);
		magia.setDebuff(DeBuffPadrao.sangramento());
		return magia;

	}
	
	public static Magia ChicoteDeVinhas() {

		Magia magia = new Magia();

		magia.setNome("Chicote de vinhas");
		magia.setValorDeDado(DadosConstantes.D6N);
		magia.setnDdos(1);
		magia.setEspacoDeMagia(1);
		magia.setCD(AtributoConstantes.DEX);
		magia.setDisparavel(false);
		magia.setTipoDeDano(ItensConstates.DANO_M);
		magia.setDebuff(DeBuffPadrao.Veneno());
		return magia;

	}
	
	public static Magia ShurikenCortante() {

		Magia magia = new Magia();

		magia.setNome("Shurikens Cortantes");
		magia.setValorDeDado(DadosConstantes.D4N);
		magia.setnDdos(1);
		magia.setEspacoDeMagia(1);
		magia.setCD(AtributoConstantes.DEX);
		magia.setDisparavel(false);
		magia.setTipoDeDano(ItensConstates.DANO_M);
		magia.setDebuff(DeBuffPadrao.Veneno());
		magia.setAlvosN(2);

		return magia;

	}
	
	public static Magia FlechaDeSkirin() {

		Magia magia = new Magia();

		magia.setNome("Flecha venenosa de Skirin");
		magia.setValorDeDado(DadosConstantes.D6N);
		magia.setnDdos(1);
		magia.setEspacoDeMagia(1);
		magia.setCD(AtributoConstantes.DEX);
		magia.setDisparavel(false);
		magia.setTipoDeDano(ItensConstates.DANO_M);
		magia.setDebuff(DeBuffPadrao.Veneno());
		return magia;

	}
	
	public static Magia FlechaDeLucios() {

		Magia magia = new Magia();

		magia.setNome("Flecha atordoante de Lucios");
	
		magia.setValorDeDado(DadosConstantes.D4N);
		magia.setnDdos(1);
		magia.setEspacoDeMagia(2);
		magia.setCD(AtributoConstantes.WIS);
		magia.setDisparavel(true);
		magia.setTipoDeDano(ItensConstates.DANO_F);
		magia.setDebuff(DeBuffPadrao.stun());
		return magia;

	}
	
	public static Magia FlechaDePorior() {

		Magia magia = new Magia();

		magia.setNome("Flecha atordoante de Porior");
	
		magia.setValorDeDado(DadosConstantes.D4N);
		magia.setnDdos(1);
		magia.setEspacoDeMagia(1);
		magia.setCD(AtributoConstantes.WIS);
		magia.setDisparavel(true);
		magia.setTipoDeDano(ItensConstates.DANO_F);
		magia.setDebuff(DeBuffPadrao.stun());
		return magia;

	}
	
	public static Magia KiStrugle() {

		Magia magia = new Magia();

		magia.setNome("Convulçao Espiritual");
	
		magia.setValorDeDado(DadosConstantes.D4N);
		magia.setnDdos(1);
		magia.setEspacoDeMagia(0);
		magia.setCD(AtributoConstantes.WIS);
		magia.setDisparavel(true);
		magia.setTipoDeDano(ItensConstates.DANO_F);
		magia.setDebuff(DeBuffPadrao.StunTeste());
		return magia;

	}

	public static Magia KiBlocking() {
		
		Magia magia = new Magia();

		magia.setNome("Bloqueio de Ki");

		magia.setValorDeDado(DadosConstantes.D4N);
		magia.setnDdos(1);
		magia.setEspacoDeMagia(1);
		magia.setCD(AtributoConstantes.WIS);
		magia.setDisparavel(false);
		magia.setTipoDeDano(ItensConstates.DANO_F);
		magia.setDebuff(DeBuffPadrao.espiritoRompido());
		return magia;

	}

	public static Magia Ilusão() {

		Magia magia = new Magia();

		magia.setNome("Ilusão");
		magia.setValorDeDado(DadosConstantes.D4N);
		magia.setnDdos(1);
		magia.setEspacoDeMagia(0);
		magia.setCD(AtributoConstantes.INT);
		magia.setDisparavel(true);
		magia.setTipoDeDano(ItensConstates.DANO_M);
		magia.setDebuff(DeBuffPadrao.StunTeste());

		return magia;

	}

	public static Magia EnergiaEscura() {

		Magia magia = new Magia();

		magia.setNome("Energia escura");
		magia.setValorDeDado(DadosConstantes.D4N);
		magia.setnDdos(3);
		magia.setEspacoDeMagia(2);
		magia.setCD(AtributoConstantes.DEX);
		magia.setDisparavel(false);
		magia.setTipoDeDano(ItensConstates.DANO_M);
		return magia;

	}

	public static Magia RajadaMistica() {

		Magia magia = new Magia();

		magia.setNome("Rajada Mística");
		magia.setValorDeDado(DadosConstantes.D10N);
		magia.setnDdos(1);
		magia.setEspacoDeMagia(2);
		magia.setDisparavel(true);
		magia.setTipoDeDano(ItensConstates.DANO_M);
		return magia;

	}
	
	public static Magia ProgétilGêmio() {

		Magia magia = new Magia();

		magia.setNome("Progétil gêmio");
		magia.setValorDeDado(DadosConstantes.D8N);
		magia.setnDdos(1);
		magia.setEspacoDeMagia(2);
		magia.setAlvosN(2);
		magia.setDisparavel(false);
		magia.setTipoDeDano(ItensConstates.DANO_M);
		return magia;

	}
	
	public static Magia tiroMágico() {

		Magia magia = new Magia();

		magia.setNome("Tiro energético");
		magia.setValorDeDado(DadosConstantes.D6N);
		magia.setnDdos(0);
		magia.setEspacoDeMagia(0);
		magia.setDisparavel(true);
		magia.setTipoDeDano(ItensConstates.DANO_M);
		return magia;

	}
	
	public static Magia raioDivino() {

		Magia magia = new Magia();

		magia.setNome("Julgamento divino");
		magia.setValorDeDado(DadosConstantes.D8N);
		magia.setnDdos(2);
		magia.setEspacoDeMagia(2);
		magia.setDisparavel(true);
		magia.setTipoDeDano(ItensConstates.DANO_M);
		return magia;

	}

	public static Magia RajadaMisticaF() {

		Magia magia = new Magia();

		magia.setNome("Rajada Mística Empoderada");
		magia.setValorDeDado(DadosConstantes.D12N);
		magia.setnDdos(1);
		magia.setEspacoDeMagia(2);
		magia.setDisparavel(true);
		magia.setTipoDeDano(ItensConstates.DANO_M);
		return magia;

	}

	public static Magia RajadaMisticaS() {

		Magia magia = new Magia();

		magia.setNome("Rajada Mística Focada");
		magia.setValorDeDado(DadosConstantes.D10N);
		magia.setnDdos(1);
		magia.setEspacoDeMagia(2);
		magia.setDisparavel(true);
		magia.setTipoDeDano(ItensConstates.DANO_M);
		magia.setDebuff(DeBuffPadrao.StunTeste());
		return magia;

	}

	public static Magia RaioDeLuz() {

		Magia magia = new Magia();

		magia.setNome("Raio de Luz");
		magia.setValorDeDado(DadosConstantes.D8N);
		magia.setnDdos(1);
		magia.setEspacoDeMagia(1);
		magia.setCD(AtributoConstantes.CON);
		magia.setDisparavel(true);
		magia.setTipoDeDano(ItensConstates.DANO_M);

		return magia;

	}

	public static Magia MarcaSolar() {

		Magia magia = new Magia();

		magia.setNome("Marca solar");
		magia.setValorDeDado("1");
		magia.setnDdos(1);
		magia.setEspacoDeMagia(2);
		magia.setCD(AtributoConstantes.CON);
		magia.setDisparavel(false);
		magia.setTipoDeDano(ItensConstates.DANO_M);
		magia.setDebuff(DeBuffPadrao.desvantagem());
		magia.setDebuff(DeBuffPadrao.marcaSolar());

		return magia;

	}

	public static Magia Patada() {

		Magia magia = new Magia();

		magia.setNome("Ilusão");
		magia.setValorDeDado(DadosConstantes.D6N);
		magia.setnDdos(1);
		magia.setEspacoDeMagia(1);
		magia.setCD(AtributoConstantes.CON);
		magia.setDisparavel(true);
		magia.setTipoDeDano(ItensConstates.DANO_F);
		magia.setDebuff(DeBuffPadrao.StunTeste());

		return magia;

	}
	
	public static Magia PuloCeCotovelo() {

		Magia magia = new Magia();

		magia.setNome("Pulo de Cotovelo");
		magia.setValorDeDado(DadosConstantes.D4N);
		magia.setnDdos(1);
		magia.setEspacoDeMagia(1);
		magia.setCD(AtributoConstantes.CON);
		magia.setDisparavel(true);
		magia.setTipoDeDano(ItensConstates.DANO_F);
		magia.setDebuff(DeBuffPadrao.StunTeste());

		return magia;

	}
	
	public static Magia explosaoRevelante() {

		Magia magia = new Magia();

		magia.setNome("Explosão revelante");
		magia.setValorDeDado(DadosConstantes.D6N);
		magia.setnDdos(1);
		magia.setEspacoDeMagia(1);
		magia.setCD(AtributoConstantes.CHA);
		magia.setDisparavel(true);
		magia.setTipoDeDano(ItensConstates.DANO_M);
		magia.setDebuff(DeBuffPadrao.StunTeste());

		return magia;

	}
	
	
	public static Magia Encantado() {

		Magia magia = new Magia();

		magia.setNome("encantado");
		magia.setValorDeDado("0");
		magia.setnDdos(1);
		magia.setEspacoDeMagia(1);
		magia.setCD(AtributoConstantes.CHA);
		magia.setDisparavel(false);
		magia.setTipoDeDano(ItensConstates.DANO_M);
		magia.setDebuff(DeBuffPadrao.desvantagem());

		return magia;

	}


}
