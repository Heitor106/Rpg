package model;

import contantes.AtributoConstantes;
import contantes.DadosConstantes;
import contantes.ItensConstates;

public class SkillPadrao {

	private static Skill skill;

	public static Skill SurtoDeAcao() {

		 skill = new Skill();
		 
		 skill.setTime(1);
		 skill.setNome("Surto de Ação");
		 skill.setTiposkill(ItensConstates.DANO_F);
		 skill.setEspacoDeSkill(2);
		 
		 return skill;

	}
	
	public static Skill Furia() {

		 skill = new Skill();
		 
		 skill.setTime(3);
		 skill.setBonusDano(3);
		 skill.setBonusAc(4);
		 skill.setNome("Furia");
		 skill.setTiposkill(ItensConstates.DANO_F);
		 skill.setEspacoDeSkill(2);
		 
		 return skill;

	}
	
	public static Skill Lich(Criatura criatura) {

		 skill = new Skill();
		 skill.setTime(3);
		 skill.setBonusDano(2);
		 skill.setBonusVida(criatura.getChaMod()+4);
		 skill.setNome("Absolvição de vidas");
		 skill.setTiposkill(ItensConstates.DANO_M);
		 skill.setEspacoDeSkill(2);
		 
		 return skill;

	}
	
	public static Skill Inspiração() {

		 skill = new Skill();
		 skill.setTime(100);
		 skill.setTiposkill(ItensConstates.DANO_M);
		 skill.setDado(DadosConstantes.D6N);
		 skill.setBonusDado(true);
		 skill.setAlvos(true);
		 skill.setAlvosMultiplos(true);
		 skill.setNome("Inspiração Bárdica");
		 skill.setEspacoDeSkill(2);
		 
		 return skill;

	}
	
	public static Skill ProtegidoPelaLuz() {

		 skill = new Skill();
		 skill.setTime(4);
		 skill.setTiposkill(ItensConstates.DANO_M);
		 skill.setDado(DadosConstantes.D4N);
		 skill.setBonusVida(4);
		 skill.setBonusDado(true);
		 skill.setAlvos(true);
		 skill.setAlvosMultiplos(true);
		 skill.setNome("Protegido pela Luz");
		 skill.setEspacoDeSkill(2);
		 
		 return skill;

	}
	
	public static Skill Polimorf() {

		 skill = new Skill();
		 skill.setTime(2);
		 skill.setTiposkill(ItensConstates.DANO_M);
		 skill.setAlvos(true);
		 skill.setNome("Transform");
		 skill.setEspacoDeSkill(2);
		 
		 return skill;

	}
	
	
	
}
