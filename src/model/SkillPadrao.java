package model;

import contantes.AtributoConstantes;
import contantes.DadosConstantes;
import contantes.ItensConstates;

public class SkillPadrao {

	private static Skill skill;

	public static Skill surtoDeAcao() {

		 skill = new Skill();
		 
		 skill.setTime(1);
		 skill.setNome("Surto de Ação");
		 skill.setTiposkill(ItensConstates.DANO_F);
		 skill.setEspacoDeSkill(2);
		 
		 return skill;

	}
	
	public static Skill flechasGemias() {

		 skill = new Skill();
		 
		 skill.setTime(1);
		 skill.setNome("Flechas Gêmeas");
		 skill.setBonusDano(1);
		 skill.setTiposkill(ItensConstates.DANO_F);
		 skill.setEspacoDeSkill(2);
		 
		 return skill;

	}
	
	public static Skill palmaFuriosa() {

		 skill = new Skill();
		 
		 skill.setTime(1);
		 skill.setNome("Palmas furiosas");
		 skill.setTiposkill(ItensConstates.DANO_F);
		 skill.setEspacoDeSkill(1);
		 
		 return skill;

	}
	
	public static Skill furia() {

		 skill = new Skill();
		 
		 skill.setTime(3);
		 skill.setBonusVida(4);
		 skill.setBonusDano(3);
		 skill.setBonusAc(4);
		 skill.setNome("Furia");
		 skill.setTiposkill(ItensConstates.DANO_F);
		 skill.setEspacoDeSkill(2);
		 
		 return skill;

	}
	
	public static Skill SustentoM() {

		 skill = new Skill();
		 
		 skill.setTime(1);
		 skill.setBonusVida(4);
		 skill.setNome("Sustento Mágico");
		 skill.setTiposkill(ItensConstates.DANO_M);
		 skill.setEspacoDeSkill(2);
		 
		 return skill;

	}
	
	public static Skill lich(Criatura criatura) {

		 skill = new Skill();
		 skill.setTime(1000);
		 skill.setBonusDano(2);
		 skill.setBonusVida(criatura.getChaMod()+4);
		 skill.setNome("Absolvição de Almas");
		 skill.setTiposkill(ItensConstates.DANO_M);
		 skill.setEspacoDeSkill(2);
		 
		 return skill;

	}
	
	public static Skill inspiração() {

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
	
	public static Skill sombras() {

		 skill = new Skill();
		 skill.setTime(2);
		 skill.setTiposkill(ItensConstates.DANO_M);
		 skill.setNome("Mergulho Sombrio");
		 skill.setEspacoDeSkill(2);
		 
		 return skill;

	}
	
	public static Skill laminaDupla() {

		 skill = new Skill();
		 skill.setTime(1);
		 skill.setTiposkill(ItensConstates.DANO_M);
		 skill.setNome("Contra Lâmina");
		 skill.setEspacoDeSkill(1);
		 
		 return skill;

	}
	
	
	public static Skill RespeitoDivino() {

		 skill = new Skill();
		 skill.setTime(1);
		 skill.setBonusVida(5);
		 skill.setBonusAtributo(3);
		 skill.setTiposkill(ItensConstates.DANO_M);
		 skill.setNome("Asas angelicais");
		 skill.setEspacoDeSkill(2);
		 
		 return skill;

	}
	
	
	public static Skill protegidoPelaLuz() {

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
	
	public static Skill polimorf() {

		 skill = new Skill();
		 skill.setTime(2);
		 skill.setTiposkill(ItensConstates.DANO_M);
		 skill.setAlvos(true);
		 skill.setNome("Transform");
		 skill.setEspacoDeSkill(2);
		 
		 return skill;

	}
	
	
	
}
