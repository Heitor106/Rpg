package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import contantes.AtributoConstantes;
import contantes.ClassConstantes;
import contantes.RacaConstantes;
import controle.ControladorCriatura;
import controle.ControladorLuta;
import model.Bonus;
import model.Criatura;
import model.Item;
import model.ItemPadrao;
import model.Monstro;
import model.MonstroPadrao;

public class Main {

	public static void main(String[] args) {

		ControladorCriatura criatura = new ControladorCriatura();

		Criatura joao = new Criatura("Joao O Cantante", RacaConstantes.MEIO_ELFO, ClassConstantes.MAGO, true);
//		Criatura jonas = new Criatura("Jonas O Terrivel", RacaConstantes.MEIO_ORC, ClassConstantes.DRUIDA,false);
//		Criatura jhoseth = new Criatura("Joseth O Sombrio", RacaConstantes.DRACONATO, ClassConstantes.BRUXO,false);
		Criatura juca = new Criatura("Juca O Illuminado", RacaConstantes.HUMANO, ClassConstantes.PATRULHEIRO, false);

		Monstro gobling = MonstroPadrao.gobling();

//		System.out.println(gobling.getNome());
//		System.out.println(gobling.toString());

		ControladorLuta luta = new ControladorLuta();

		List<Criatura> criaturas = new ArrayList();
		List<Criatura> criaturasMortas = new ArrayList();
		List<Criatura> fugiram = new ArrayList();
		List<Item> chao = new ArrayList();

		criaturas.add(juca);
//		criaturas.add(jonas);
		criaturas.add(joao);
//		criaturas.add(jhoseth);

		criaturas = luta.luta(criaturas, chao, fugiram, criaturasMortas);
		
		

	}

}
