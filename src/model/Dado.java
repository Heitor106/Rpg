package model;

import java.util.Random;

import contantes.DadosConstantes;

public class Dado {

	private static Random rand= new Random();
	
	public static int D20 () {
		
		return rand.nextInt(DadosConstantes.D20)+1;
		
	}
	
	public static int D12 () {
		
		return rand.nextInt(DadosConstantes.D12)+1;
		
	}
	
	public static int D10 () {
		
		return rand.nextInt(DadosConstantes.D10)+1;
		
	}
	
	public static int D8 () {
		
		return rand.nextInt(DadosConstantes.D8)+1;
		
	}
	
	public static int D6 () {
		
		return rand.nextInt(DadosConstantes.D6)+1;
		
	}
	
	public static int D4 () {
		
		return rand.nextInt(DadosConstantes.D4)+1;
		
	}
	
}
