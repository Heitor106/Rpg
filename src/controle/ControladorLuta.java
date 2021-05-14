package controle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import contantes.AtributoConstantes;
import contantes.BonusConstantes;
import contantes.ClassConstantes;
import contantes.DadosConstantes;
import contantes.DebuffConstantes;
import contantes.ItensConstates;
import contantes.MagiasConstantes;
import contantes.RacaConstantes;
import model.Bonus;
import model.BonusPadrao;
import model.Criatura;
import model.Dado;
import model.DeBuff;
import model.DeBuffPadrao;
import model.Item;
import model.Magia;
import model.Skill;
import model.SkillPadrao;
import uteis.Uteis;

public class ControladorLuta {

	private static final String MAGIA_PASSIVA = "magiaPassiva";

	private static final String MAGIA2 = "Magia";

	private static final String ITEM2 = "Item";

	private static final String SKILL = "Skill";

	private static final String ATAQUE = "Ataque Fisisco";

	private Scanner scan = new Scanner(System.in);

	private Criatura criatura;
	private String acao;
	private Magia magia;
	private Item item;
	private boolean disparavel = true;
	private static Map<String, Criatura> FormasHumanas = new HashMap<>();
	private List<Criatura> criaturas;
	private List<Criatura> criaturasMortas = new ArrayList();
	private int ativo;
	private int jogavel;
	private Criatura passivo;
	private int ini;
	private String acaoNome;
	private boolean critical;
	private static Random rand = new Random();
	private boolean erroCritico0;

	public void Luta(List<Criatura> criaturaLista) {

		this.criaturas = criaturaLista;
		boolean batalha = true;
		int dadoDeAtaque;

		System.out.println("Iniciativas: \n");

		for (jogavel = 0; jogavel < criaturas.size(); jogavel++) {

			criaturas.get(jogavel).setIniciativa(iniciativa(criaturas.get(jogavel).getDexMod()));

			System.out.println("Iniciativa de " + criaturas.get(jogavel).getNome() + " igual á "
					+ criaturas.get(jogavel).getIniciativa());

		}

		System.out.println("\n");

		Collections.sort(criaturas);

		jogavel = 0;

		while (batalha) {

			ativo = jogavel;

			critical = false;

			erroCritico0 = false;

			criatura = criaturas.get(ativo);

			System.out.println(criatura.getNome());

			if (criatura.isStunado()) {

				System.out.println("Você foi atordoado e perdeu seu turno\n");

				ExecutaDebuff();

				proximo();

				continue;
			}

			String acaoNome = selecionador20();

			if (SKILL.equals(acao)) {

				Skill skill = criatura.getSkill(acaoNome);

				passivo = escolhaAlvoDeBuff(skill);

				passivo.addBuffs(skill);

				AddBuffs(passivo);

				Polimorf(criatura);

				acaoNome = selecionador20();

			}

			if (ATAQUE.equals(acao)) {

				item = criatura.getAtaquesFisicos().get(acaoNome);

			} else if (ITEM2.equals(acao)) {

				item = criatura.getItens().get(acaoNome);

			} else if (MAGIA2.equals(acao)) {

				magia = criatura.getMagias().get(acaoNome);

			} else if (MAGIA_PASSIVA.equals(acao)) {

				magia = criatura.getMagiasPassivas().get(acaoNome);

				if (!magia.isCura()) {

					Magia magia = criatura.getMagiasPassivas().get(acaoNome);

					criatura.addBuffsM(magia);

					System.out.println("Vai usarem quem?\n");

					ini = escolhaIni();

					passivo = criaturas.get(ini);

					AddBuffsM(passivo);

					passivo.addBuffsM(magia);

					proximo();

					continue;

				}
			}

			System.out.println("Vai usarem quem?\n");

			ini = escolhaIni();

			passivo = criaturas.get(ini);

			batalha = true;

			dadoDeAtaque = dadoDeAtaque();

			if (!disparavel) {

				System.out.println("Jogador " + criatura.getNome());

			} else {

				System.out.println("Jogador " + criatura.getNome() + ": " + dadoDeAtaque + "\n");

				int dadoDeAtaque2 = dadoDeAtaque + Inspiracao();

				if (dadoDeAtaque2 != dadoDeAtaque) {

					System.out.println("Jogador " + criatura.getNome() + "seu novo dado é: " + dadoDeAtaque2 + "\n");
					dadoDeAtaque = dadoDeAtaque2;

				}

			}

			if (erroCritico0) {

				proximo();

				continue;
			}

			if (dadoDeAtaque >= passivo.getAc()) {

				int dano = dadoDeDano();

				if (acao.equals(MAGIA_PASSIVA) && magia.isCura()) {

					int dano2 = dano + Inspiracao();

					if (dano != dano2) {

						System.out.println(passivo.getNome() + " se curou em " + dano2 + " de vida\n");

						dano = dano2;

						passivo.vidaGanha(dano);

					} else {

						System.out.println(passivo.getNome() + " se curou em " + dano + " de vida\n");

						passivo.vidaGanha(dano);
					}

				} else if (acao.equals(MAGIA2) && !disparavel) {

					int dado = Dado.D20();

					if (validaGnomo()) {

						System.out.println("Sua resistencia gnomica contra magias entra em ação");

						int i1 = Dado.D20();

						if (i1 > dado) {

							dado = i1;

						}

					}

					int bonus = 0;

					switch (magia.getCD()) {

					case AtributoConstantes.STR:

						if (null != passivo.getBonuses().get(BonusConstantes.RFORCA)) {

							bonus = passivo.getBonuses().get(BonusConstantes.RFORCA).getBonus();
						}

						dado = dado + passivo.getStrMod();
						break;

					case AtributoConstantes.DEX:

						if (null != passivo.getBonuses().get(BonusConstantes.RDEX)) {

							bonus = passivo.getBonuses().get(BonusConstantes.RDEX).getBonus();
						}
						dado = dado + passivo.getDexMod();
						break;

					case AtributoConstantes.INT:

						if (null != passivo.getBonuses().get(BonusConstantes.RINT)) {

							bonus = passivo.getBonuses().get(BonusConstantes.RINT).getBonus();
						}
						dado = dado + passivo.getInteMod();
						break;

					case AtributoConstantes.WIS:

						if (null != passivo.getBonuses().get(BonusConstantes.RWIS)) {

							bonus = passivo.getBonuses().get(BonusConstantes.RWIS).getBonus();
						}
						dado = dado + passivo.getWisMod();
						break;

					case AtributoConstantes.CON:

						if (null != passivo.getBonuses().get(BonusConstantes.RCON)) {

							bonus = passivo.getBonuses().get(BonusConstantes.RCON).getBonus();
						}
						dado = dado + passivo.getConMod();
						break;

					case AtributoConstantes.CHA:

						if (null != passivo.getBonuses().get(BonusConstantes.RCHA)) {

							bonus = passivo.getBonuses().get(BonusConstantes.RCHA).getBonus();
						}
						dado = dado + passivo.getChaMod();
						break;

					}

					if (dado > criatura.getCDTR()) {

						System.out.println("\n" + passivo.getNome() + " passou no teste de " + magia.getCD());

						dano = dano / 2;

					} else {

						System.out.println("\n" + passivo.getNome() + "falhou no teste de " + magia.getCD());

					}

					System.out.println("Magia deu " + dano + " de dano");

					dano = dano + MarcaSolar();

					System.out.println(passivo.getNome() + " recebeu " + dano + " de dano");

					passivo.danoRecebido(dano);

					validaDeBuffM();

				} else {

					if (acao == ITEM2 && critical) {

						System.out.println(criatura.getNome() + " você acertou um CRÍTICO !!!!");

						dano = dano + item.dadoDano();
					}

					System.out.println(passivo.getNome() + " recebeu " + dano + " de dano");

					dano = dano + MarcaSolar();

					passivo.danoRecebido(dano);

					if (acao == ITEM2 || acao == ATAQUE) {

						validaDeBuffI();

					} else {

						validaDeBuffM();

					}

				}

			} else {

				if (MAGIA_PASSIVA.equals(acao) && !magia.isCura()) {

					if (magia.getBonusMHP() > 0) {

						System.out.println(passivo.getNome() + " ganhou " + magia.getBonusMHP() + " de vida");
					}

					if (magia.getBonusMAC() > 0) {

						System.out.println(passivo.getNome() + " ganhou " + magia.getBonusMAC() + " de AC");
					}

				} else {

					System.out.println("Você errou!!!");

				}

			}

			System.out.println("vida de " + passivo.getNome() + " = " + passivo.getHp() + "\n");

			for (Skill skill : criatura.getBuffs()) {

				if (skill.getNome().equals(SkillPadrao.ProtegidoPelaLuz().getNome())) {

					System.out.println("\nComo protegido da luz você se cura em 1 de vida\n");

					criatura.vidaGanha(1);

					break;

				}

			}

			setBonus();

			setBonusM();

			ExecutaDebuff();

			if (passivo.getHp() == 0 && passivo.getNomeRaca().equals(RacaConstantes.MEIO_ORC)) {

				ResistenciaOrc();

			}

			if (criatura.getNomeDaClasse().equals(ClassConstantes.BRUXO)) {

				passivo.danoRecebido(100);

			}

			if (criatura.getNomeDaClasse().equals(ClassConstantes.BRUXO) && passivo.getHp() <= 0) {

				System.out.println(criatura.getNome() + " você absorve a alma de " + passivo.getNome());
				criatura.setNmagias(3);
				criatura.addBuffs(SkillPadrao.Lich(criatura));
				AddBuffs(criatura);

			}

			if (passivo.getHp() <= 0) {

				System.out.println(passivo.getNome() + " esta MORTO!!!\n");

				morte();

			}
			
			criaturas.set(jogavel, criatura);

			proximo();

			if (!Fim()) {

				for (Criatura criatura : criaturas) {

					System.out.println("\n" + criatura.getNome() + " você Trinunfou em batalha!!!");

				}

				for (Criatura criatura : criaturasMortas) {

					System.out.println(criatura.getNome() + " morreu :(\n");

				}

				batalha = false;

			}

		}

	}

	private void validaDeBuffM() {
		if (null != magia.getDebuff()) {

			passivo.addDeBuffs(magia.getDebuff());

			List<DeBuff> deBuff1 = passivo.getDebuffs();

			for (int l = 0; l < deBuff1.size(); l++) {

				DeBuff debuff = deBuff1.get(l);

				if (magia.getDebuff().getNome().equals(debuff.getNome())) {

					debuff.setTime(magia.getDebuff().getTime());

					if (0 != debuff.getDanoOnHit()) {

						System.out.println(passivo.getNome() + " recebe mais " + debuff.getDanoOnHit() + " em dano de "
								+ debuff.getTipo());
					}

					if (resistenciaP(debuff)) {

						System.out.println(passivo.getNome() + "seu corpo forte segura os efeitos do veneneno\n");
						passivo.danoRecebido(debuff.getDanoOnHit() / 2);

					} else if (resistenciaF(debuff)) {

						System.out.println(passivo.getNome() + "sua acendencia draconica segura os efeitos do fogo\n");

						passivo.danoRecebido(debuff.getDanoOnHit() / 2);

					} else {

						passivo.danoRecebido(debuff.getDanoOnHit());

					}
				}
			}

		}
	}

	private void validaDeBuffI() {
		if (null != item.getDebuff()) {

			passivo.addDeBuffs(item.getDebuff());

			List<DeBuff> deBuff1 = passivo.getDebuffs();

			for (int l = 0; l < deBuff1.size(); l++) {

				DeBuff debuff = deBuff1.get(l);

				if (item.getDebuff().getNome().equals(debuff.getNome())) {

					debuff.setTime(item.getDebuff().getTime());

					if (0 != debuff.getDanoOnHit()) {

						System.out.println(passivo.getNome() + " recebe mais " + debuff.getDanoOnHit() + " em dano de "
								+ debuff.getTipo());

					}

					if (resistenciaP(debuff)) {

						System.out.println(passivo.getNome() + "seu corpo forte segura os efeitos do veneneno\n");
						passivo.danoRecebido(debuff.getDanoOnHit() / 2);

					} else if (resistenciaF(debuff)) {

						System.out.println(passivo.getNome() + "sua acendencia draconica segura os efeitos do fogo\n");

						passivo.danoRecebido(debuff.getDanoOnHit() / 2);

					} else {

						passivo.danoRecebido(debuff.getDanoOnHit());

					}

				}
			}
		}
	}

	public int iniciativa(int dexMod) {

		return Dado.D20() + dexMod;

	}

	private int dadoDeAtaque() {

		String ataque;
		int bonus = 0;
		String atributo;

		if (MAGIA2.equals(acao)) {

			if (!magia.getDisparavel()) {

				disparavel = false;

				return 100;

			}

			if (criatura.getBonuses().get(BonusConstantes.ARCANA) != null) {

				bonus = criatura.getBonuses().get(BonusConstantes.ARCANA).getBonus();

			}

			atributo = criatura.getAtributoPadrao();

		} else if (MAGIA_PASSIVA.equals(acao)) {

			if (magia.isCura()) {

				disparavel = false;

				return 100;

			} else {

				disparavel = false;

				return -1;
			}

		} else if (ITEM2.equals(acao) || ATAQUE.equals(acao)) {

			ataque = item.getTipoItem();

			if (criatura.getBonuses().get(ataque) != null) {

				bonus = criatura.getBonuses().get(ataque).getBonus();

			}

			atributo = item.getAtributo();

		} else {

			return -1;

		}

		int dado = Dado.D20();

		if (criatura.isDesvantagem()) {

			System.out.println("\nVocê está em desvantagem\n");

			int dado1 = Dado.D20();

			if (dado1 < dado) {

				dado = dado1;

			}

		}

		if (dado == 1 && criatura.getNomeRaca().equals(RacaConstantes.HOBBITP)
				|| criatura.getNomeRaca().equals(RacaConstantes.HOBBITC)) {

			System.out.println("Você ia cometer um erro crítico, mas sua sorte o salva");

			dado = Dado.D20();

		}

		ErroCritico(dado);

		if (dado == 20) {

			critical = true;

		}

		switch (atributo) {

		case AtributoConstantes.STR:
			return dado + criatura.getStrMod() + bonus;

		case AtributoConstantes.DEX:
			return dado + criatura.getDexMod() + bonus;

		case AtributoConstantes.INT:
			return dado + criatura.getInte() + bonus;

		case AtributoConstantes.WIS:
			return dado + criatura.getWisMod() + bonus;

		case AtributoConstantes.CON:
			return dado + criatura.getConMod() + bonus;

		case AtributoConstantes.CHA:
			return dado + criatura.getChaMod() + bonus;

		}

		return Dado.D20();

	}

	private int dadoDeDano() {

		String atributo;
		int dano = 0;
		int bonusf = 0;
		int bonusm = 0;

		if (MAGIA2.equals(acao)) {

			dano = magia.dadoDano() + bonusesAtivosDado();
			atributo = " ";

		} else if (ITEM2.equals(acao) || ATAQUE.equals(acao)) {

			dano = item.dadoDano() + bonusesAtivosDado();

			if (critical) {

				dano = dano + item.dadoDano();

				if (criatura.getNomeRaca() == RacaConstantes.MEIO_ORC) {

					dano = dano + item.dadoDano();

				}

			}
			atributo = item.getAtributo();

		} else if (MAGIA_PASSIVA.equals(acao)) {

			dano = magia.dadoDano();
			atributo = criatura.getAtributoPadrao();

		} else {

			return -1;

		}

		switch (atributo) {

		default:
			return dano;

		case AtributoConstantes.STR:
			return dano + criatura.getStrMod();

		case AtributoConstantes.DEX:
			return dano + criatura.getDexMod();

		case AtributoConstantes.CON:
			return dano + criatura.getConMod();

		case AtributoConstantes.CHA:
			return dano + criatura.getChaMod();

		case AtributoConstantes.WIS:
			return dano + criatura.getWisMod();

		case AtributoConstantes.INT:
			return dano + criatura.getInteMod();
		}
	}

	private String selecionador20() {

		int escolhido;

		String acaoEscolhida;

		List<String> acoes = new ArrayList<>();

		System.out.println("Escolha sua Ação: ");

		if (criatura.getAtaquesFisicos() != null) {

			acoes.add(ATAQUE);

		}

		if (criatura.getItens() != null) {

			acoes.add(ITEM2);

		}

		if (criatura.getMagias() != null && magiaUtil() || criatura.getMagiasPassivas() != null && magiaPassivaUtil()) {

			acoes.add(MAGIA2);

		}

		if (criatura.getSkills() != null && !SKILL.equals(acao) && skillUtil()) {

			acoes.add(SKILL);

		}

		for (int i = 0; i < acoes.size(); i++) {

			System.out.println(i + 1 + " " + acoes.get(i));

		}

		escolhido = Uteis.escaneador(acoes.size());

		escolhido--;

		acaoEscolhida = acoes.get(escolhido);

		return escolheAcao(acaoEscolhida);

	}

	private String escolheAcao(String escolhido) {

		int magiaEscolhida;

		switch (escolhido) {

		case ATAQUE:
			return escolhiAtaque();

		case ITEM2:
			return escolhiItem();

		case MAGIA2:

			if (criatura.getMagiasPassivas() != null) {

				System.out.println("1 Magia de ataque\n");
				System.out.println("2 Magia Passiva");

				magiaEscolhida = Uteis.escaneador(2);

				if (magiaEscolhida == 1) {

					return escolhiMagia();

				} else if (magiaEscolhida == 2 && magiaPassivaUtil()) {

					return escolhiMagiaPassiva();

				}

			}
			return escolhiMagia();

		case SKILL:

			return escolhiSkill();

		}
		return null;

	}

	private <E> void mostraMap(Map<String, E> map) {

		int i = 1;

		for (Map.Entry<String, E> nomes : map.entrySet()) {

			System.out.println(i + " " + nomes.getKey());

			i++;
		}

	}

	private <E> String procuraMap(Map<String, E> map, int escolhido) {

		int i = 1;

		for (Map.Entry<String, E> entrada : map.entrySet()) {

			if (i == escolhido) {

				return entrada.getKey();

			}

			i++;

		}

		return null;

	}

	private Skill validaBonus(Skill skill) {

		if (skill != null && skill.getTime() > 0) {

			skill.setTime(skill.getTime() - 1);

			return skill;
		}

		return skill;

	}

	private void validaBonuses() {

		for (Skill skill : criatura.getBuffs()) {

			validaBonus(skill);

		}

	}

	private boolean temBonus(Skill bonus) {

		return bonus != null;

	}

	private Criatura addBonus(Criatura criatura, Skill skill) {

		Boolean ativo = bonusAtivo(skill);

		if (ativo != null && ativo) {

			criatura.setAc(criatura.getAc() + skill.getBonusAc());
			criatura.setHp(criatura.getHp() + skill.getBonusVida());

			if (AtributoConstantes.STR.equals(skill.getAtributo())) {

				criatura.setStr(criatura.getStr() + skill.getBonusAtributo());

			} else if (AtributoConstantes.DEX.equals(skill.getAtributo())) {

				criatura.setDex(criatura.getDex() + skill.getBonusAtributo());

			} else if (AtributoConstantes.CON.equals(skill.getAtributo())) {

				criatura.setCon(criatura.getCon() + skill.getBonusAtributo());

			} else if (AtributoConstantes.CHA.equals(skill.getAtributo())) {

				criatura.setCha(criatura.getCha() + skill.getBonusAtributo());

			} else if (AtributoConstantes.WIS.equals(skill.getAtributo())) {

				criatura.setWis(criatura.getWis() + skill.getBonusAtributo());

			} else if (AtributoConstantes.CON.equals(skill.getAtributo())) {

				criatura.setInte(criatura.getInte() + skill.getBonusAtributo());
			}
		}
		return criatura;

	}

	private Criatura addBonusM(Criatura criatura, Magia magia) {

		Boolean ativoM = bonusMAtivo(magia);

		if (ativoM != null && ativoM) {

			criatura.setAc(criatura.getAc() + magia.getBonusMAC());
			criatura.setHp(criatura.getHp() + magia.getBonusMHP());

		}

		return criatura;
	}

	private Criatura naoBonus(Criatura criatura, Skill skill) {

		Boolean ativo = bonusAtivo(skill);

		if (ativo != null && !ativo) {

			criatura.setAc(criatura.getAc() - skill.getBonusAc());
			criatura.setHp(criatura.getHp() - skill.getBonusVida());

			if (AtributoConstantes.STR.equals(skill.getAtributo())) {

				criatura.setStr(criatura.getStr() - skill.getBonusAtributo());

			} else if (AtributoConstantes.DEX.equals(skill.getAtributo())) {

				criatura.setDex(criatura.getDex() - skill.getBonusAtributo());

			} else if (AtributoConstantes.CON.equals(skill.getAtributo())) {

				criatura.setCon(criatura.getCon() - skill.getBonusAtributo());

			} else if (AtributoConstantes.CHA.equals(skill.getAtributo())) {

				criatura.setCha(criatura.getCha() - skill.getBonusAtributo());

			} else if (AtributoConstantes.WIS.equals(skill.getAtributo())) {

				criatura.setWis(criatura.getWis() - skill.getBonusAtributo());

			} else if (AtributoConstantes.CON.equals(skill.getAtributo())) {

				criatura.setInte(criatura.getInte() - skill.getBonusAtributo());
			}

			if (skill.getNome().equals(SkillPadrao.Polimorf().getNome())) {

				int vida = 0;
				List < Skill > buffs = new ArrayList();
				List < Magia> buffsM = new ArrayList();
				List <DeBuff> debuffs= new ArrayList();
				boolean AmigoC = criatura.isAmigo();
				boolean MarcaSolarC = criatura.isMarcaSolar();
				boolean StunadoC = criatura.isStunado();
				boolean DesvantagemC = criatura.isDesvantagem();
				
				switch (criatura.getNomeDaClasse()) {

				case "Jaguar":
					vida = (criatura.getHp());
					break;
				case "Coelho":
					vida = (criatura.getHp() * 2);
					break;
				case "Urso":
					vida = (criatura.getHp() / 2);
					break;
				}

				criatura = FormasHumanas.get(criatura.getNome());

				criatura.setHp(vida);
				criatura.setBuffs(buffs);
				criatura.setBuffsM(buffsM);
				criatura.setDebuffs(debuffs);
				criatura.setAmigo(AmigoC);
				criatura.setMarcaSolar(MarcaSolarC);
				criatura.setStunado(StunadoC);
				criatura.setDesvantagem(DesvantagemC);
				
			}
		}

		return criatura;
	}

	private Criatura naoBonusM(Criatura criatura, Magia magia) {

		Boolean ativoM = bonusMAtivo(magia);

		if (ativoM != null && !ativoM) {

			criatura.setAc(criatura.getAc() - magia.getBonusMAC());
			criatura.setHp(criatura.getHp() - magia.getBonusMHP());

		}

		validaLista(criatura, this.ativo);
		return criatura;

	}

	private Boolean bonusAtivo(Skill bonus) {

		Boolean temBonus = temBonus(bonus);

		if (null != temBonus && temBonus) {

			return bonus.getTime() > 0;
		}

		return null;

	}

	private void validaLista(Criatura criatura, int ami) {

		criaturas.set(ami, criatura);

	}

	private boolean validaSkill(String skillNome) {

		Skill skill = criatura.getSkills().get(skillNome);

		return skill.getEspacoDeSkill() >= 0;
	}

	private boolean validaMagia(String nomeMagia) {

		int on;

		Magia magia = criatura.getMagias().get(nomeMagia);

		on = criatura.getNmagias() - magia.getEspacoDeMagia();

		if (on >= 0) {

			criatura.setNmagias(on);

			return true;

		}

		return false;

	}

	private boolean validaMagiaPassiva(String nomeMagia) {

		int on;

		Magia magia = criatura.getMagiasPassivas().get(nomeMagia);

		on = criatura.getNmagias() - magia.getEspacoDeMagia();

		if (on >= 0) {

			criatura.setNmagias(on);

			return true;

		}

		return false;

	}

	private int escolhaIni() {

		int i;
		int ini;

		for (i = 0; i < criaturas.size(); i++) {

			System.out.println(i + 1 + " " + criaturas.get(i).getNome());

		}

		ini = escaneadorIni(criaturas.size());

		return ini;
	}

	private int escaneadorIni(int max) {

		int ini = Uteis.escaneador(max);

		ini--;

		return ini;

	}

	private String escolhiItem() {

		int escolhido;

		mostraMap(criatura.getItens());

		escolhido = Uteis.escaneador(criatura.getItens().size());

		acao = ITEM2;

		return procuraMap(criatura.getItens(), escolhido);
	}

	private String escolhiAtaque() {

		int escolhido;

		mostraMap(criatura.getAtaquesFisicos());

		escolhido = Uteis.escaneador(criatura.getAtaquesFisicos().size());

		acao = ATAQUE;

		return procuraMap(criatura.getAtaquesFisicos(), escolhido);
	}

	private String escolhiSkill() {

		int escolhido;
		String skillNome = null;
		boolean on = true;

		while (on) {

			mostraMap(criatura.getSkills());

			escolhido = Uteis.escaneador(criatura.getSkills().size());

			acao = SKILL;

			skillNome = procuraMap(criatura.getSkills(), escolhido);

			if (validaSkill(skillNome)) {

				System.out.println(
						skillNome + " pode ser usada mais " + criatura.getSkills().get(skillNome).getEspacoDeSkill());

				return skillNome;

			}

			System.out.println(skillNome + " não pode ser usada mais\n");

		}
		return skillNome;
	}

	private String escolhiMagia() {

		int escolhido;
		mostraMap(criatura.getMagias());

		escolhido = Uteis.escaneador(criatura.getMagias().size());

		acao = MAGIA2;

		String magiaNome = procuraMap(criatura.getMagias(), escolhido);

		if (magiaNome == "Sopro do dragão") {

			if (criatura.getSkillClase() > 0) {

				criatura.setSkillClase(0);
				return magiaNome;

			} else {

				criatura.setSkillClase(0);
				return null;
			}

		}

		if (validaMagia(magiaNome)) {

			System.out.println("Você tem mais " + criatura.getNmagias() + " espaços de magia \n");

			return magiaNome;

		}

		System.out.println(magiaNome + " indisponivel:(" + "\n" + "tente outra coisa");

		return null;
	}

	private String escolhiMagiaPassiva() {

		int escolhido;

		mostraMap(criatura.getMagiasPassivas());

		escolhido = Uteis.escaneador(criatura.getMagiasPassivas().size());

		acao = MAGIA_PASSIVA;

		String magiaNome = procuraMap(criatura.getMagiasPassivas(), escolhido);

		if (validaMagiaPassiva(magiaNome)) {

			System.out.println("Você tem mais " + criatura.getNmagias() + " espaços de magia \n");

			return magiaNome;

		}

		System.out.println(magiaNome + " indisponivel:(" + "\n" + "tente outra coisa");

		return null;
	}

	private List<Criatura> morte() {

		criaturasMortas.add(criaturas.get(ini));

		for (int i = ini; i < criaturas.size() - 1; i++) {

			criaturas.remove(ini);

			criaturas.add(i, criaturas.get(i));

		}

		criaturas.remove(criaturas.size() - 1);

		return criaturas;

	}

	private boolean Fim() {

		int nAmigos = 0;

		if (criaturas.size() == 1) {

			return false;

		}

		for (int i = 0; i < criaturas.size(); i++) {

			if (criaturas.get(i).isAmigo()) {

				nAmigos = nAmigos + 1;

			}

		}

		if (nAmigos == 0) {

			return false;

		}

		if (nAmigos == criaturas.size()) {

			return false;
		}

		return true;

	}

	private boolean skillUtil() {

		int utilizaveis = 0;

		for (Map.Entry<String, Skill> skills : criatura.getSkills().entrySet()) {

			if (skills.getValue().getEspacoDeSkill() != 0) {

				utilizaveis++;

			}

		}

		if (utilizaveis == 0) {

			return false;
		}

		return true;

	}

	private boolean magiaUtil() {

		int utilizaveis = 0;

		for (Map.Entry<String, Magia> magia : criatura.getMagias().entrySet()) {

			if (magia.getValue().getEspacoDeMagia() < criatura.getNmagias()) {

				utilizaveis++;

			}

		}

		if (utilizaveis == 0) {

			return false;
		}

		return true;
	}

	private boolean magiaPassivaUtil() {

		int utilizaveis = 0;

		for (Map.Entry<String, Magia> magia : criatura.getMagiasPassivas().entrySet()) {

			if (magia.getValue().getEspacoDeMagia() < criatura.getNmagias()) {

				utilizaveis++;

			}

		}

		if (utilizaveis == 0) {

			return false;
		}

		return true;
	}

	private Magia validaBonusM(Magia magia) {

		if (magia != null && magia.getTime() > 0) {

			magia.setTime(magia.getTime() - 1);

			return magia;

		}

		return magia;
	}

	private void validaBonusesM() {

		for (Magia magia : criatura.getBuffsM())

			validaBonusM(magia);

	}

	private boolean temBonusM(Magia magia) {

		return magia != null;

	}

	private Boolean bonusMAtivo(Magia magia) {

		if (temBonusM(magia)) {

			return magia.getTime() > 0;
		}

		return null;

	}

	public int Inspiracao() {

		Skill bonus = null;
		int i;

		for (i = 0; i < criatura.getBuffs().size(); i++) {

			if (criatura.getBuffs().get(i).getNome().equals(SkillPadrao.Inspiração().getNome())) {

				bonus = SkillPadrao.Inspiração();

				break;

			} else {

				bonus = null;
			}

		}

		if (null != bonus && bonus.isBonusDado()) {

			System.out.println("Você quer usar o bonus de " + bonus.getNome() + ":\n1 sim\n2 não");

			int escolha = Uteis.escaneador(2);

			if (escolha == 1) {

				bonus.setTime(0);

				List<Skill> buffs = criatura.getBuffs();

				buffs.set(i, bonus);

				criatura.setBuffs(buffs);

				return bonus.dadoBonus();

			}

			if (escolha == 2) {

				return 0;

			}
		}
		return 0;
	}

	private void AddBuffs(Criatura criatura) {

		for (Skill skill : criatura.getBuffs()) {

			criatura = addBonus(criatura, skill);
		}

	}

	private void NoBuffs(Criatura criatura) {

		for (Skill skill : criatura.getBuffs()) {

			naoBonus(criatura, skill);
		}

	}

	private void AddBuffsM(Criatura criatura) {

		for (Magia magia : criatura.getBuffsM()) {

			criatura = addBonusM(criatura, magia);
		}

	}

	private void NoBuffsM(Criatura criatura) {

		for (Magia magia : criatura.getBuffsM()) {

			naoBonusM(criatura, magia);
		}

	}

	private Criatura escolhaAlvoDeBuff(Skill skill) {

		int ini;

		if (skill.isAlvosMultiplos()) {

			System.out.println("vai usar em quem?\n");

			ini = escolhaIni();

			passivo = criaturas.get(ini);

			return passivo;

		}

		return criatura;
	}

	private int bonusAtivoDaDo(Skill skill) {

		if (temBonus(skill)) {

			String tipoSkill = skill.getTiposkill();

			switch (tipoSkill) {

			case ItensConstates.DANO_M:

				if ("Magia" == acao) {

					return skill.getBonusDano();
				}

				return 0;

			case ItensConstates.DANO_F:

				if ("Item" == acao) {

					return skill.getBonusDano();
				}

				return 0;
			}
		}

		return 0;
	}

	public int bonusesAtivosDado() {

		int bonus = 0;

		for (Skill skill : criatura.getBuffs()) {

			bonus = bonus + bonusAtivoDaDo(skill);

		}

		return bonus;
	}

//	private void setBonus(Skill skill) {
//
//		if(criatura.getBuffs().get(1)==null) {
//						
//			criatura.addBuffs(skill);
//			
//		} else if (criatura.getBuffs().get(1)!= null) {
//			
//			
//			
//		}
//		
//	}

	private void setBonus() {

		List<Skill> skills = new ArrayList();

		for (int i = 0; i < criatura.getBuffs().size(); i++) {

			Skill skill = criatura.getBuffs().get(i);
			validaBonus(skill);
			criatura = naoBonus(criatura, skill);
			skills.add(skill);

		}

		criatura.setBuffs(skills);

	}

	private void setBonusM() {

		List<Magia> magias = new ArrayList();

		for (int i = 0; i < criatura.getBuffsM().size(); i++) {

			Magia magia = criatura.getBuffsM().get(i);
			validaBonusM(magia);
			criatura = naoBonusM(criatura, magia);
			magias.add(magia);

		}

		criatura.setBuffsM(magias);

	}

	private boolean validaGnomo() {

		if (RacaConstantes.GNOMOF.equals(criatura.getNomeRaca())
				|| RacaConstantes.GNOMOR.equals(criatura.getNomeRaca()) && magia.getCD() == AtributoConstantes.CHA
				|| magia.getCD() == AtributoConstantes.WIS || magia.getCD() == AtributoConstantes.INT) {

			return true;

		}

		return false;
	}

	private void ResistenciaOrc() {

		if (passivo.getSkillClase() > 0) {

			System.out.println(passivo.getNome() + " sua resistencia e perseverança te trazem a vida\n");

			passivo.setHp(1);

		}

		passivo.setSkillClase(0);

	}

	private void ExecutaDebuff() {

		List<DeBuff> deBuff1 = criatura.getDebuffs();

		for (int i = 0; i > deBuff1.size(); i++) {

			DeBuff debuff = deBuff1.get(i);

			if (debuff.getTime() > 0) {

				System.out.println("Você está sofrendo de" + deBuff1.get(i).getNome() + " recebeu ");

				if (resistenciaP(debuff)) {

					System.out.println("Seu corpo forte segura os efeitos do veneneno\n");

					criatura.danoRecebido(debuff.getDano() / 2);

				} else if (resistenciaF(debuff)) {

					System.out.println("Sua acendencia draconiana segura os efeitos fogo\n");

					criatura.danoRecebido(debuff.getDano() / 2);

				} else {

					criatura.danoRecebido(debuff.getDano());

				}

				debuff.setTime(debuff.getTime() - 1);

				deBuff1.set(i, debuff);

			}
		}

		criatura.setDebuffs(deBuff1);
	}

	private void ErroCritico(int dado) {

		if (dado <= 1) {

			int problema = rand.nextInt(2);

			System.out.print("\nVocê teve um erro crítico ");

			if (ATAQUE.equals(acao)) {

				problema = 1;

			}

			switch (problema) {

			case 1:

				System.out.print("e cabou por se machucar com " + item.getNome() + "\n");

				int dano = dadoDeDano();

				criatura.danoRecebido(dano);

				System.out.println("Você toma " + dano * -1 + " de dano\n");

				erroCritico0 = true;

				break;

			case 0:

				System.out.print("e acabou por deichar " + item.getNome() + " escapar de suas mãos\n");

				Map<String, Item> ItensC = criatura.getItens();

				ItensC.remove(item.getNome());

				criatura.setItens(ItensC);

				erroCritico0 = true;

			}
		}

	}

	private void proximo() {

		if (ativo < criaturas.size() - 1) {

			jogavel++;

		} else {

			jogavel = 0;
		}

	}

	private boolean resistenciaP(DeBuff debuff) {

		if (criatura.getNomeRaca().equals(RacaConstantes.ANAOC) || criatura.getNomeRaca().equals(RacaConstantes.ANAOM)
				|| criatura.getNomeRaca().equals(RacaConstantes.HOBBITC)
						&& debuff.getTipo().equals(DebuffConstantes.VENENO)) {

			return true;

		} else {

			return false;
		}
	}

	private boolean resistenciaF(DeBuff debuff) {

		if (criatura.getNomeRaca().equals(RacaConstantes.DRACONATO) && debuff.getTipo().equals(DebuffConstantes.FOGO)) {

			return true;
		} else {

			return false;
		}
	}

	private int MarcaSolar() {

		if (passivo.isMarcaSolar()) {

			int i = Dado.D4();
			int i1 = 0;

			for (Map.Entry<String, Skill> skill : criatura.getSkills().entrySet()) {

				if (skill.getValue().getNome().equals(SkillPadrao.ProtegidoPelaLuz().getNome())) {

					i1 = Dado.D4();

					break;

				}

			}

			i = i + i1;

			if (i1 != 0) {

				System.out.println("Você como protegido pela luz explode a marca solar, causando " + i + " de dano");

			} else {

				System.out.println("A marca solar explode, causando " + i + " de dano");

			}

			List<DeBuff> deBuff1 = passivo.getDebuffs();

			for (int J = 0; J > deBuff1.size(); J++) {

				DeBuff debuff = deBuff1.get(J);

				if (debuff.getNome().equals(DeBuffPadrao.marcaSolar().getNome())) {

					debuff.setTime(0);

					deBuff1.set(J, debuff);

				}

			}

			criatura.setDebuffs(deBuff1);

			return i;

		}

		return 0;

	}

	static Criatura Polimorf(Criatura criatura) {

		for (Skill skil : criatura.getBuffs()) {

			if (skil.getNome().equals(SkillPadrao.Polimorf().getNome()) && skil.getTime() == 2) {

				Criatura druida = new  Criatura(criatura);

				if (criatura.getNomeDaClasse().equals(ClassConstantes.DRUIDA)) {

					FormasHumanas.put(criatura.getNome(), druida);

				}

				System.out.println("1 Jaguar\n2 Coelho\n3 Urso");

				String m = criatura.getNomeDaClasse();

				do {

					int p = 0;

					if (p != 0) {

						System.out.println("1 Jaguar\n2 Coelho\n3 Urso");

						System.out.println("Você já é um " + criatura.getClass() + ", tente outra coisa");

					}

					switch (Uteis.escaneador(3)) {

					case 1:
						criatura = mudancaDeClasse(criatura, "jaguar");
						break;
					case 2:
						criatura = mudancaDeClasse(criatura, "coelho");
						break;
					case 3:
						criatura = mudancaDeClasse(criatura, "urso");
						break;
					}

					p++;

				} while (criatura.getNomeDaClasse() == m);

				return criatura;

			} else {

				return criatura;

			}
		}
		return criatura;
	}

	static Criatura mudancaDeClasse(Criatura criatura, String animal) {

		int vida = 0;

		criatura.getMagias().clear();
		criatura.getMagiasPassivas().clear();
		criatura.getItens().clear();
		criatura.getSkills().clear();
		criatura.getBonuses().clear();
		criatura.getAtaquesFisicos().clear();

		switch (animal) {

		case "jaguar":
			vida = (criatura.getHp());
			break;
		case "coelho":
			vida = (criatura.getHp() / 2);
			break;
		case "urso":
			vida = (criatura.getHp() * 2);
			break;
		}

		criatura.setClass(animal);

		criatura.setHp(vida);

		return criatura;
	}
}
