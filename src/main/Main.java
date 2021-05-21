package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import contantes.AtributoConstantes;
import contantes.ClassConstantes;
import contantes.RacaConstantes;
import controle.ControladorCriatura;
import controle.ControladorLuta;
import model.Bonus;
import model.Criatura;
import model.ItemPadrao;
import model.Monstro;
import model.MonstroPadrao;


public class Main {

	public static void main(String[] args) {
		
		ControladorCriatura criatura= new ControladorCriatura();		

//		Criatura personagem1 = criatura.novaCriatura();
//		Criatura personagem2 = criatura.novaCriatura();
//	    Criatura personagem3 = criatura.novaCriatura();
//		Criatura personagem4 = criatura.novaCriatura();
//		Criatura joao = new Criatura("Joao O Cantante", RacaConstantes.MEIO_ELFO, ClassConstantes.BARDO,true);
		Criatura jonas = new Criatura("Jonas O Terrivel", RacaConstantes.MEIO_ORC, ClassConstantes.GUERREIRO,true);
//		Criatura jhoseth = new Criatura("Joseth O Sombrio", RacaConstantes.DRACONATO, ClassConstantes.BRUXO,false);
		Criatura juca = new Criatura("Juca O Illuminado", RacaConstantes.HUMANO, ClassConstantes.MONGE,false);
//		System.out.println(criaturaP.toString());

//		for (Map.Entry<String, Bonus> bonus : criaturaP.getBonuses().entrySet()) {
//
//			System.out.println(bonus.toString());
//					
//		}
			
	Monstro gobling= MonstroPadrao.gobling();
	
//	System.out.println(gobling.getNome());
//	System.out.println(gobling.toString());
	
	ControladorLuta luta = new ControladorLuta();
	
	List<Criatura> criaturas = new ArrayList();
	
//	criaturas.add(personagem1);
//	criaturas.add(personagem2);
//	criaturas.add(personagem3);
//	criaturas.add(personagem4);
	criaturas.add(juca);
	criaturas.add(jonas);
//	criaturas.add(joao);
//	criaturas.add(jhoseth);
	
	luta.Luta(criaturas);

	}

}
