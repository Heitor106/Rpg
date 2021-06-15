package controle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
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
import contantes.SkillConstantes;
import model.Bonus;
import model.BonusPadrao;
import model.Criatura;
import model.Dado;
import model.DeBuff;
import model.DeBuffPadrao;
import model.Item;
import model.ItemPadrao;
import model.Magia;
import model.MagiaPadrao;
import model.Skill;
import model.SkillPadrao;
import uteis.Uteis;

public class ControladorLuta {

	private static final String FUGIR = "Fugir";

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
	private List<Criatura> criaturasMortas = new ArrayList<>();
	private List<Criatura> fugiram = new ArrayList<>();
	private List<Item> chao = new ArrayList<>();
	private int ativo;
	private int jogavel;
	private Criatura passivo;
	private int ini;
	private String acaoNome;
	private boolean critical;
	private static Random rand = new Random();
	private boolean respeitoDivino;
	private boolean erroCritico0;
	static int nDeTurnos;
	int turnos;
	int dadoDeAtaque;

	public List<Criatura> luta(List<Criatura> criaturaLista, List<Item> chao, List<Criatura> fugiram,
			List<Criatura> criaturasMortas) {

		this.criaturas = criaturaLista;
		this.fugiram = fugiram;
		this.criaturasMortas = criaturasMortas;
		this.chao = chao;

		boolean batalha = true;

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

			nDeTurnos = 1;

			ativo = jogavel;

			criatura = criaturas.get(ativo);

			System.out.println(criatura.getNome());

			if (criatura.isStunado()) {

				System.out.println("Você foi atordoado e perdeu seu turno\n");

				executaDebuff();

				proximo();

				continue;
			}

			for (turnos = 0; turnos < nDeTurnos; turnos++) {

				critical = false;

				erroCritico0 = false;

				if (!iSflechasGemeas(criatura)) {

					acaoNome = selecionador20();

				} else {

					acao = ITEM2;

				}

				if (FUGIR.equals(acao)) {

					criatura.setEmFuga(true);

					passivo = criatura;

					fugir();

				}

				if (SKILL.equals(acao)) {

					Skill skill = criatura.getSkill(acaoNome);

					passivo = escolhaAlvoDeBuff(skill);

					passivo.addBuffs(skill);

					respeitoDivino(skill);

					AddBuffs(passivo);

					polimorf(criatura);

					SurtoDeAcao(criatura);

					palmasFuriosas(criatura);

					flechasGemeas(criatura);

					if (!iSflechasGemeas(criatura)) {

						acaoNome = selecionador20();

					}

				}

				if (ATAQUE.equals(acao)) {

					item = criatura.getAtaquesFisicos().get(acaoNome);

				} else if (ITEM2.equals(acao)) {

					if (iSflechasGemeas(criatura)) {

						item = ItemPadrao.ArcoDeJhin();

					} else {

						item = criatura.getItens().get(acaoNome);

					}

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

				batalha = true;

				if (acao.equals(MAGIA_PASSIVA) && magia.isCura()) {

					int cura = dadoDeDano();

					System.out.println("Vai usarem quem?\n");

					ini = escolhaIni();

					passivo = criaturas.get(ini);

					int cura2 = cura + Inspiracao();

					if (cura != cura2) {

						System.out.println(passivo.getNome() + " se curou em " + cura2 + " de vida\n");

						cura = cura2;

						if (magia.getNome().equals(MagiasConstantes.CURAS) && criatura.getHp() <= 4) {

							System.out.println("Sua sofrencia ativa a clemência de Deus");

							cura = cura + 3;

						}

						passivo.vidaGanha(cura);

					} else {

						System.out.println(passivo.getNome() + " se curou em " + cura + " de vida\n");

						if (magia.getNome().equals(MagiasConstantes.CURAS) && criatura.getHp() <= 4) {

							System.out.println("Sua sofrencia ativa a clemência de Deus");

							cura = cura + 3;

						}

						passivo.vidaGanha(cura);
					}

				} else if (MAGIA_PASSIVA.equals(acao) && !magia.isCura()) {

					int dano = dadoDeDano();

					System.out.println("Vai usarem quem?\n");

					ini = escolhaIni();

					passivo = criaturas.get(ini);

					if (magia.getBonusMHP() > 0) {

						System.out.println(passivo.getNome() + " ganhou " + magia.getBonusMHP() + " de vida");
					}

					if (magia.getBonusMAC() > 0) {

						System.out.println(passivo.getNome() + " ganhou " + magia.getBonusMAC() + " de AC");
					}

				} else if (acao.equals(MAGIA2) && !disparavel) {

					if (!magia.isAlvosmultiplos()) {

						for (int i = 0; i < magia.getAlvosN(); i++) {

							if (magia.getAlvosN() != 1) {

								System.out.println(
										"Quem vai ser o " + (i + 1) + " dos seus " + magia.getAlvosN() + " alvos?\n");

							} else {

								System.out.println("Vai usarem quem?\n");

							}

							ini = escolhaIni();

							passivo = criaturas.get(ini);

							dadoEsysout();

							int tR = testesDeTr();

							int dano = dadoDeDano();

							if (tR > criatura.getCDTR()) {

								System.out.println("\n" + passivo.getNome() + " passou no teste de " + magia.getCD());

								dano = dano / 2;

							} else {

								System.out.println("\n" + passivo.getNome() + "falhou no teste de " + magia.getCD());

							}

							System.out.println(magia.getNome() + " deu " + dano + " de dano");

							dano = dano + marcaSolar();

							System.out.println(passivo.getNome() + " recebeu " + dano + " de dano");

							passivo.danoRecebido(dano);

							validaDeBuffM();

						}

					} else {

						for (Criatura alvo : criaturas) {

							if (!alvo.isAmigo() == criatura.isAmigo()) {

								passivo = alvo;

								int tR = testesDeTr();

								int dano = dadoDeDano();

								if (tR > criatura.getCDTR()) {

									System.out
											.println("\n" + passivo.getNome() + " passou no teste de " + magia.getCD());

									dano = dano / 2;

								} else {

									System.out
											.println("\n" + passivo.getNome() + "falhou no teste de " + magia.getCD());

								}

								System.out.println(magia.getNome() + " deu " + dano + " de dano");

								dano = dano + marcaSolar();

								System.out.println(passivo.getNome() + " recebeu " + dano + " de dano");

								passivo.danoRecebido(dano);

								validaDeBuffM();

							}

						}

					}

				} else if (acao.equals(MAGIA2) && disparavel) {

					for (int i = 0; i < magia.getAlvosN(); i++) {

						if (magia.getAlvosN() != 1) {

							System.out.println(
									"Quem vai ser o " + (i + 1) + " dos seus " + magia.getAlvosN() + " alvos?\n");

						} else {

							System.out.println("Vai usarem quem?\n");

						}

						ini = escolhaIni();

						passivo = criaturas.get(ini);

						dadoEsysout();

						if (erroCritico0) {

							proximo();

							continue;
						}

						if (dadoDeAtaque >= passivo.getAc()) {

							int dano = dadoDeDano();

							System.out.println(magia.getNome() + " deu " + dano + " de dano");

							dano = dano + marcaSolar();

							System.out.println(passivo.getNome() + " recebeu " + dano + " de dano");

							passivo.danoRecebido(dano);

							validaDeBuffM();

						} else {

							errou();

						}

					}

				} else if (acao.equals(ITEM2) || acao.equals(ATAQUE)) {

					System.out.println("Vai usarem quem?\n");

					ini = escolhaIni();

					passivo = criaturas.get(ini);

					dadoEsysout();

					if (erroCritico0) {

						proximo();

						continue;
					}

					if (dadoDeAtaque >= passivo.getAc()) {

						int dano = dadoDeDano();

						if (acao.equals(ITEM2) && critical || acao.equals(ATAQUE) && critical) {

							System.out.println(criatura.getNome() + " você acertou um CRÍTICO !!!!");

							dano = dano + item.dadoDano();
						}

						if (criatura.getNomeDaClasse() == ClassConstantes.PATRULHEIRO && criatura.getNmagias() >= 0) {

							System.out.println(
									"Você quer usar uma de suas " + criatura.getNmagias() + " flechas mágica ?\n");

							int lp = 3;

							System.out.println("1 Não");
							System.out.println("2 " + MagiaPadrao.FlechaDePorior().getNome());
							System.out.println("3 " + MagiaPadrao.FlechaDeSkirin().getNome());

							if (criatura.getNmagias() >= 2) {

								System.out.println("4 " + MagiaPadrao.FlechaDeLucios().getNome());
								lp++;

							}

							int sN = Uteis.escaneador(lp);

							switch (sN) {

							case 2:

								magia = MagiaPadrao.FlechaDePorior();
								criatura.setNmagias(criatura.getNmagias() - magia.getEspacoDeMagia());

								break;

							case 3:

								magia = MagiaPadrao.FlechaDeSkirin();
								criatura.setNmagias(criatura.getNmagias() - magia.getEspacoDeMagia());

								break;

							case 4:

								magia = MagiaPadrao.FlechaDeLucios();
								criatura.setNmagias(criatura.getNmagias() - magia.getEspacoDeMagia());

								break;

							case 1:

								break;

							}
						}

						if (criatura.getNomeDaClasse() == ClassConstantes.MONGE && criatura.getNmagias() >= 0) {

							System.out.println("1 Não");
							System.out.println("2 " + MagiaPadrao.KiBlocking().getNome());
							System.out.println("3 " + MagiaPadrao.KiStrugle().getNome());
							int sN = Uteis.escaneador(3);

							System.out.println("Você quer usar o poder de seus " + criatura.getNmagias() + " KI ?\n");

							switch (sN) {

							case 2:

								magia = MagiaPadrao.KiBlocking();
								criatura.setNmagias(criatura.getNmagias() - magia.getEspacoDeMagia());

								break;

							case 3:

								magia = MagiaPadrao.KiStrugle();
								criatura.setNmagias(criatura.getNmagias() - magia.getEspacoDeMagia());

								break;

							case 1:
								break;

							}

							validaDeBuffM();

						}

						if (isSombra(criatura)) {

							int danoD = Dado.D4();

							System.out.println("Você sai das sombras para desferir um ataque surpresa, dando " + danoD);

							dano = dano + danoD;

						}

						if (passivo.getNomeDaClasse().equals(ClassConstantes.PALADINO)) {

							int rep = 0;

							for (String nome : passivo.getMeAtacaaram()) {

								if (nome == criatura.getNome()) {

									rep++;
								}

							}

							if (rep == 0) {

								passivo.getMeAtacaaram().add(criatura.getNome());

							}

							if (batiEm(passivo.getNome())) {

								System.out.println("A covardia de bater em um paladino pacífico diminui suas forças\n");

								dano = dano / 2;

							} else {

								System.out.println("Você deseja usar dos poderes de deus?\n");
								System.out.println("1 Sim");
								System.out.println("2 Não");
								int sN = Uteis.escaneador(2);
								if (sN == 1) {

									int dano1 = Dado.D10();

									System.out.println(
											MagiaPadrao.raioDivino().getNome() + " cai do céu dando mais " + dano1);

									dano = dano + dano1;

								}
							}

						}

						if (criatura.getNomeDaClasse().equals(ClassConstantes.PALADINO)) {

							System.out.println("Usar o poder de Deus contra inocentes tira suas forças\n");

							dano = dano / 2;

						}

						if (criatura.getNomeDaClasse() == ClassConstantes.BARBARO && criatura.getHp() < 3) {

							System.out.println(
									"O seu próprio sangue lhe da força, " + "você da mais 2 de dano e se cura em 1\n");
							dano = dano + 2;
							criatura.vidaGanha(1);

						}

						dano = dano + marcaSolar();

						if (respeitoDivino && !criatura.getNome().equals(passivo.getNome())) {

							List<Criatura> respeitadores = new ArrayList<Criatura>();

							for (Criatura passivo : criaturas) {

								if (isRespeitando(passivo, criatura)) {

									respeitadores.add(criatura);

								}

							}

							System.out.println("Aqueles sobre suas asas divias não serão atingidos\n"
									+ "\nVocê só pode atingir o anjo:");

							for (int i = 0; i < respeitadores.size(); i++) {

								System.out.println(i + 1 + " " + criatura.getNome());

							}

							passivo = respeitadores.get(Uteis.escaneador(respeitadores.size()) - 1);

						}

						System.out.println("Você deu " + dano + " de dano");

						passivo.danoRecebido(dano);

						if (criatura.getNomeDaClasse().equals(ClassConstantes.MONGE) && nDeTurnos == 1) {

							nDeTurnos = 2;

						} else if (criatura.getNomeDaClasse().equals(ClassConstantes.MONGE) && nDeTurnos == 2) {

							nDeTurnos = 3;

						}

						validaDeBuffI();

					} else {

						errou();

					}

				}

				if ((criatura.getHp() >= 0)) {

					System.out.println("vida de " + passivo.getNome() + " = " + passivo.getHp());

				}

				for (Skill skill : criatura.getBuffs()) {

					if (skill.getNome().equals(SkillPadrao.protegidoPelaLuz().getNome())) {

						System.out.println("\nComo protegido da luz você se cura em 1 de vida\n");

						criatura.vidaGanha(1);

						break;

					}

				}

				if (criatura.getNomeDaClasse() == ClassConstantes.GUERREIRO) {

					System.out.println("Você como guerreiro se mantem, você ganhou 1 de vida");

					criatura.vidaGanha(1);

				}

				if (turnos == nDeTurnos - 1) {

					setBonus();

					setBonusM();

					executaDebuff();
				}

				if (passivo.getHp() == 0 && passivo.getNomeRaca().equals(RacaConstantes.MEIO_ORC)) {

					ResistenciaOrc();

				}

				if (criatura.getNomeDaClasse().equals(ClassConstantes.BRUXO) && passivo.getHp() <= 0) {

					System.out.println(criatura.getNome() + " você absorve a alma de " + passivo.getNome());
					criatura.setNmagias(3);
					criatura.addBuffs(SkillPadrao.lich(criatura));
					AddBuffs(criatura);

				}

				if (passivo.getHp() <= 0) {

					System.out.println(passivo.getNome() + " esta MORTO!!!");

					morte();

				}

				if (criatura.getHp() <= 0 && !criatura.equals(passivo)) {

					System.out.println("\n O contra ataque é forte de mais e mata");
					System.out.println(criatura.getNome() + " esta MORTO!!!");

					morte();

					continue;

				}

				if (criatura.getNomeDaClasse() == ClassConstantes.LADINO && passivo.getHp() <= 0) {

					System.out.println("O sangue dos seus inimigos te empolga e você ataca novamente");

					nDeTurnos = 2;

				}

			}

			proximo();

			if (!fim()) {

				for (Criatura criatura : criaturas) {

					System.out.println("\n" + criatura.getNome() + " você Trinunfou em batalha!!!\n");

				}

				for (Criatura criatura : criaturasMortas) {

					System.out.println(criatura.getNome() + " morreuXXX\n");

				}

				for (Criatura criatura : fugiram) {

					System.out.println(criatura.getNome() + " fugio\n");

				}

				Loot();

				batalha = false;

				return criaturas;
			}

		}

		return null;

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

			if (criatura.getBonuses().get(BonusConstantes.ARCANA) != null) {

				bonus = criatura.getBonuses().get(BonusConstantes.ARCANA).getBonus();

			}

			atributo = criatura.getAtributoPadrao();

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

		if (criatura.isDesvantagem() || isSombra(passivo)) {

			if (isSombra(passivo)) {

				System.out.println("\nA escuridão o cega e você está perdido");

			} else {

				System.out.println("\nVocê está em desvantagem\n");

			}

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

		int escolhido = 0;

		String acaoEscolhida;

		List<String> acoes = new ArrayList<>();

		System.out.println("\nEscolha sua " + (turnos + 1) + " ação:");

		if (criatura.getAtaquesFisicos() != null && ((!SKILL.equals(acao) && (criatura.getItens() != null && turnos < 1)
				|| (!criatura.getNomeDaClasse().equals(ClassConstantes.PATRULHEIRO))))) {

			acoes.add(ATAQUE);

		}

		if (criatura.getItens() != null && turnos < 1 || criatura.getNomeDaClasse().equals(ClassConstantes.GUERREIRO)
				|| criatura.getNomeDaClasse().equals(ClassConstantes.PATRULHEIRO)) {

			acoes.add(ITEM2);

		}

		if ((criatura.getMagias() != null && magiaUtil() || criatura.getMagiasPassivas() != null && magiaPassivaUtil())
				&& (!criatura.getNomeDaClasse().equals(ClassConstantes.MONGE)) && (isBlocked(criatura))
				&& (!SKILL.equals(acao) && (criatura.getItens() != null && turnos < 1)
						|| !criatura.getNomeDaClasse().equals(ClassConstantes.PATRULHEIRO))) {

			acoes.add(MAGIA2);

		}

		if (criatura.getMagias() != null && magiaUtil() && turnos == 1
				&& criatura.getNomeDaClasse().equals(ClassConstantes.MONGE)) {

			acoes.add(MAGIA2);

		}

		if (criatura.getSkills() != null && !SKILL.equals(acao) && skillUtil() && turnos < 1) {

			acoes.add(SKILL);

		}

		acoes.add(FUGIR);

		for (int i = 0; i < acoes.size(); i++) {

			System.out.println(i + 1 + " " + acoes.get(i));

		}

		if (criatura.isBot()) {

			escolhido = Uteis.escolhaBot(acoes.size());

		} else {

			escolhido = Uteis.escaneador(acoes.size());

		}

		escolhido--;

		acaoEscolhida = acoes.get(escolhido);

		return escolheAcao(acaoEscolhida);

	}

	private String escolheAcao(String escolhido) {

		int magiaEscolhida;

		switch (escolhido) {

		case FUGIR:

			acao = FUGIR;

			return FUGIR;

		case ATAQUE:
			return escolhiAtaque();

		case ITEM2:
			return escolhiItem();

		case MAGIA2:

			if (!criatura.getMagiasPassivas().isEmpty() && !criatura.getMagias().isEmpty()) {

				System.out.println("1 Magia de ataque\n");
				System.out.println("2 Magia Passiva");

				magiaEscolhida = Uteis.escaneador(2);

				if (magiaEscolhida == 1) {

					return escolhiMagia();

				} else if (magiaEscolhida == 2 && magiaPassivaUtil()) {

					return escolhiMagiaPassiva();

				}

			} else if (!criatura.getMagiasPassivas().isEmpty() && criatura.getMagias().isEmpty()) {

				return escolhiMagiaPassiva();

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

	private void mostraMagias() {

		System.out.println("Você tem " + criatura.getNmagias() + " de " + criatura.getFonteDePoder() + "\n");

		int i = 1;

		for (Entry<String, Magia> magia : criatura.getMagias().entrySet()) {

			System.out.println(i + " " + magia.getValue().getNome() + "\t\t" + magia.getValue().getEspacoDeMagia());

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

			if (skill.getBonusAc() != 0) {

				criatura.setAc(criatura.getAc() + skill.getBonusAc());

				System.out.println("Você ganha " + skill.getBonusAc() + " de armadura");

			}

			if (skill.getBonusVida() != 0) {

				criatura.setHp(criatura.getHp() + skill.getBonusVida());

				System.out.println("Você ganha " + skill.getBonusVida() + " de vida");

			}

			if (AtributoConstantes.STR.equals(skill.getAtributo())) {

				criatura.setStr(criatura.getStr() + skill.getBonusAtributo());

				System.out.println("Você ganha " + skill.getBonusAtributo() + " de força");

			} else if (AtributoConstantes.DEX.equals(skill.getAtributo())) {

				criatura.setDex(criatura.getDex() + skill.getBonusAtributo());

				System.out.println("Você ganha " + skill.getBonusAtributo() + " de Dex");

			} else if (AtributoConstantes.CON.equals(skill.getAtributo())) {

				criatura.setCon(criatura.getCon() + skill.getBonusAtributo());

				System.out.println("Você ganha " + skill.getBonusAtributo() + " de Con");

			} else if (AtributoConstantes.CHA.equals(skill.getAtributo())) {

				criatura.setCha(criatura.getCha() + skill.getBonusAtributo());

				System.out.println("Você ganha " + skill.getBonusAtributo() + " de Cha");

			} else if (AtributoConstantes.WIS.equals(skill.getAtributo())) {

				criatura.setWis(criatura.getWis() + skill.getBonusAtributo());

				System.out.println("Você ganha " + skill.getBonusAtributo() + " de Wis");

			} else if (AtributoConstantes.INT.equals(skill.getAtributo())) {

				criatura.setInte(criatura.getInte() + skill.getBonusAtributo());

				System.out.println("Você ganha " + skill.getBonusAtributo() + " de Int");

			}
		}
		return criatura;

	}

	private Criatura addBonusM(Criatura criatura, Magia magia) {

		Boolean ativoM = bonusMAtivo(magia);

		if (ativoM != null && ativoM) {

			if (magia.getBonusMAC() != 0) {

				criatura.setAc(criatura.getAc() + magia.getBonusMAC());

				System.out.println("Você ganha " + magia.getBonusMAC() + " de armadura");

			}

			if (magia.getBonusMHP() != 0) {

				criatura.setHp(criatura.getAc() + magia.getBonusMHP());

				System.out.println("Você ganha " + magia.getBonusMHP() + " de Vida");

			}

		}

		return criatura;
	}

	private Criatura naoBonus(Criatura criatura, Skill skill) {

		Boolean ativo = bonusAtivo(skill);

		if (ativo != null && !ativo) {
			if (criatura.getNomeDaClasse().equals(ClassConstantes.PALADINO)) {

				respeitoDivino = false;

			}
			criatura.setAc(criatura.getAc() - skill.getBonusAc());

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

			if (skill.getNome().equals(SkillPadrao.polimorf().getNome())) {

				int vida = 0;
				List<Skill> buffs = new ArrayList<>();
				List<Magia> buffsM = new ArrayList<>();
				List<DeBuff> debuffs = new ArrayList<>();
				boolean AmigoC = criatura.isAmigo();
				boolean MarcaSolarC = criatura.isMarcaSolar();
				boolean StunadoC = criatura.isStunado();
				boolean DesvantagemC = criatura.isDesvantagem();

				switch (criatura.getNomeDaClasse()) {

				case ClassConstantes.JAGUAR:
					vida = (criatura.getHp());
					break;
				case ClassConstantes.COELHO:
					vida = (criatura.getHp() * 2);
					break;
				case ClassConstantes.URSO:
					vida = (criatura.getHp() / 2);
					break;
				}

				System.out.println(
						"Você volta a ser um " + criatura.getNomeRaca() + " com " + criatura.getHp() + " de vida");
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

			System.out.println("Buff " + skill.getNome() + " termina");

		}

		return criatura;
	}

	private Criatura naoBonusM(Criatura criatura, Magia magia) {

		Boolean ativoM = bonusMAtivo(magia);

		if (ativoM != null && !ativoM) {

			criatura.setAc(criatura.getAc() - magia.getBonusMAC());
			criatura.setHp(criatura.getHp() - magia.getBonusMHP());

			System.out.println("Magia " + magia.getNome() + " termina");

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

		ini = Uteis.escaneador(max);

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
		
		if(criatura.isBot()) {
			
		escolhido = Uteis.escolhaBot(criatura.getAtaquesFisicos().size());
			
		} else {

		escolhido = Uteis.escaneador(criatura.getAtaquesFisicos().size());

		}
		
		acao = ATAQUE;

		return procuraMap(criatura.getAtaquesFisicos(), escolhido);
	}

	private String escolhiSkill() {

		int escolhido;
		String skillNome = null;
		boolean on = true;

		while (on) {

			mostraMap(criatura.getSkills());

			if(criatura.isBot()) {
				
				escolhido = Uteis.escolhaBot(criatura.getSkills().size());
					
				} else {

				escolhido = Uteis.escaneador(criatura.getSkills().size());

				}

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

		mostraMagias();

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

		System.out.println(magiaNome + " indisponivel:(" + "\n" + "Tente outra coisa\n");

		return escolhiMagia();
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

		return escolhiMagiaPassiva();
	}

	private List<Criatura> morte() {

		for (int i = 0; i < criaturas.size(); i++) {

			if (criaturas.get(i).getHp() <= 0) {

				criaturasMortas.add(criaturas.get(i));

				for (Map.Entry<String, Item> Item : criaturas.get(i).getItens().entrySet()) {

					chao.add(item);

					criaturas.get(i).getItens().remove(Item);

				}

				criaturas.remove(i);

			}

		}

		return criaturas;

	}

	private boolean fim() {

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

			if (criatura.getBuffs().get(i).getNome().equals(SkillPadrao.inspiração().getNome())) {

				bonus = SkillPadrao.inspiração();

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

		if (RacaConstantes.GNOMOF.equals(passivo.getNomeRaca())
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

	private void executaDebuff() {

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

			if (ATAQUE.equals(acao) && ITEM2.equals(acao) && item.isImperdivel()) {

				problema = 1;

			}

			if (MAGIA2.equals(acao)) {

				problema = 2;

			}

			int dano;

			switch (problema) {

			case 2:

				System.out.print("e cabou por se machucar com " + magia.getNome() + "\n");

				dano = dadoDeDano();

				criatura.danoRecebido(dano);

				System.out.println("Você toma " + dano + " de dano\n");

				erroCritico0 = true;

				break;

			case 1:

				System.out.print("e cabou por se machucar com " + item.getNome() + "\n");

				dano = dadoDeDano();

				criatura.danoRecebido(dano);

				System.out.println("Você toma " + dano + " de dano\n");

				erroCritico0 = true;

				break;

			case 0:

				System.out.print("e acabou por deichar " + item.getNome() + " escapar de suas mãos\n");

				Map<String, Item> ItensC = criatura.getItens();

				ItensC.remove(item.getNome());

				chao.add(item);

				criatura.setItens(ItensC);

				erroCritico0 = true;

			}
		}

	}

	private void proximo() {

		if (jogavel < criaturas.size() - 1) {

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

	private int marcaSolar() {

		if (passivo.isMarcaSolar()) {

			int i = Dado.D4();
			int i1 = 0;

			for (Map.Entry<String, Skill> skill : criatura.getSkills().entrySet()) {

				if (skill.getValue().getNome().equals(SkillPadrao.protegidoPelaLuz().getNome())) {

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

	Criatura polimorf(Criatura criatura) {

		for (Skill skil : criatura.getBuffs()) {

			if (ClassConstantes.DRUIDA.equals(criatura.getNomeDaClasse()) && skil.getTime() == 2) {

				Criatura druida = new Criatura(criatura);

				if (criatura.getNomeDaClasse().equals(ClassConstantes.DRUIDA)) {

					FormasHumanas.put(criatura.getNome(), druida);

				}

				System.out.println("1 Jaguar\n2 Coelho\n3 Urso");

				String nomeC = criatura.getNomeDaClasse();

				do {

					int NdeTentativas = 0;

					if (NdeTentativas != 0) {

						System.out.println("1 Jaguar\n2 Coelho\n3 Urso");

						System.out.println("Você já é um " + criatura.getClass() + ", tente outra coisa");

					}

					switch (Uteis.escaneador(3)) {

					case 1:
						criatura = mudancaDeClasse(criatura, ClassConstantes.JAGUAR);
						break;
					case 2:
						criatura = mudancaDeClasse(criatura, ClassConstantes.COELHO);
						break;
					case 3:
						criatura = mudancaDeClasse(criatura, ClassConstantes.URSO);
						break;
					}

					NdeTentativas++;

				} while (criatura.getNomeDaClasse() == nomeC);

				return criatura;

			} else {

				return criatura;

			}
		}
		return criatura;
	}

	private Criatura mudancaDeClasse(Criatura criatura, String animal) {

		int vida = 0;

		criatura.getMagias().clear();
		criatura.getMagiasPassivas().clear();
		criatura.getItens().clear();
		criatura.getSkills().clear();
		criatura.getBonuses().clear();
		criatura.getAtaquesFisicos().clear();

		switch (animal) {

		case ClassConstantes.JAGUAR:
			System.out.println("Você se tranforma em um Jaguar");
			vida = (criatura.getHp());
			break;
		case ClassConstantes.COELHO:
			System.out.println("Você se tranforma em um Coelho");
			vida = (criatura.getHp() / 2);
			break;
		case ClassConstantes.URSO:
			System.out.println("Você se tranforma em um Urso");
			vida = (criatura.getHp() * 2);
			break;
		}

		criatura.setClass(animal);

		criatura.setHp(vida);

		return criatura;
	}

	private void SurtoDeAcao(Criatura criatura) {

		for (Skill skil : criatura.getBuffs()) {

			if (skil.getNome().equals(SkillPadrao.surtoDeAcao().getNome())) {

				nDeTurnos++;

			}
		}
	}

	private void flechasGemeas(Criatura criatura) {

		if (iSflechasGemeas(criatura)) {

			nDeTurnos++;

			acao = ITEM2;

		}
	}

	private boolean iSflechasGemeas(Criatura criatura) {

		for (Skill skil : criatura.getBuffs()) {

			if (skil.getNome().equals(SkillPadrao.flechasGemias().getNome())) {

				return true;

			}

		}

		return false;

	}

	private void palmasFuriosas(Criatura criatura) {

		for (Skill skil : criatura.getBuffs()) {

			if (skil.getNome().equals(SkillPadrao.palmaFuriosa().getNome())) {

				nDeTurnos++;
				nDeTurnos++;
			}
		}
	}

	private void respeitoDivino(Skill skill) {

		if (skill.getNome() == SkillPadrao.RespeitoDivino().getNome()) {

			respeitoDivino = true;

		}

	}

	private boolean isBlocked(Criatura criatura) {

		for (int i = 0; i < criatura.getDebuffs().size(); i++) {

			if (criatura.getDebuffs().get(i).getNome().equals(DebuffConstantes.BLOCKED)) {

				return false;

			}

		}

		return true;

	}

	private boolean isSombra(Criatura passivo) {

		for (Skill skill : passivo.getBuffs()) {

			if (skill.getNome() == SkillConstantes.MERGULHO_SOMBRIO) {

				return true;

			}

		}
		return false;

	}

	private boolean isPreparado(Criatura passivo) {

		for (Skill skill : passivo.getBuffs()) {

			if (skill.getNome() == SkillConstantes.CONTRA_LAINA) {

				return true;

			}

		}

		return false;

	}

	private boolean isRespeitando(Criatura passivo, Criatura criatura) {

		for (Skill skill : passivo.getBuffs()) {

			if (skill.getNome() == SkillPadrao.RespeitoDivino().getNome() && criatura.isAmigo() == passivo.isAmigo()) {

				return true;

			}

		}

		return false;

	}

	private boolean MeBateram(String nome) {

		for (String atacante : passivo.getMeAtacaaram()) {

			if (atacante == nome) {

				return false;
			}

		}

		return true;
	}

	private boolean batiEm(String nome) {

		for (String atacante : passivo.getAtaquei()) {

			if (atacante == nome) {

				return false;
			}

		}

		return true;
	}

	private void dadoEsysout() {

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

	}

	private void errou() {

		System.out.println("Você errou!!!");

		if (isPreparado(passivo) && passivo.getHp() > 0) {

			int counterD = Dado.D12() + 2;

			criatura.danoRecebido(counterD);

			System.out.println(" E ele prepara um contra ataque, dando " + counterD + " de dano");

			for (int i = 0; i < passivo.getBuffs().size(); i++) {

				if (passivo.getBuffs().get(i).getNome() == "Contra Lâmina") {

					passivo.getBuffs().remove(i);

				}

			}

		}

	}

	private int testesDeTr() {

		int tR = Dado.D20();

		if (validaGnomo()) {

			System.out.println("Sua resistencia gnomica contra magias entra em ação");

			int dadoVantagem = Dado.D20();

			if (dadoVantagem > tR) {

				tR = dadoVantagem;

			}

		}

		int bonus = 0;

		switch (magia.getCD()) {

		case AtributoConstantes.STR:

			if (null != passivo.getBonuses().get(BonusConstantes.RFORCA)) {

				bonus = passivo.getBonuses().get(BonusConstantes.RFORCA).getBonus();
			}

			tR = tR + passivo.getStrMod();

			break;

		case AtributoConstantes.DEX:

			if (null != passivo.getBonuses().get(BonusConstantes.RDEX)) {

				bonus = passivo.getBonuses().get(BonusConstantes.RDEX).getBonus();
			}

			tR = tR + passivo.getDexMod();

			break;

		case AtributoConstantes.INT:

			if (null != passivo.getBonuses().get(BonusConstantes.RINT)) {

				bonus = passivo.getBonuses().get(BonusConstantes.RINT).getBonus();
			}

			tR = tR + passivo.getInteMod();

			break;

		case AtributoConstantes.WIS:

			if (null != passivo.getBonuses().get(BonusConstantes.RWIS)) {

				bonus = passivo.getBonuses().get(BonusConstantes.RWIS).getBonus();
			}

			tR = tR + passivo.getWisMod();

			break;

		case AtributoConstantes.CON:

			if (null != passivo.getBonuses().get(BonusConstantes.RCON)) {

				bonus = passivo.getBonuses().get(BonusConstantes.RCON).getBonus();
			}

			tR = tR + passivo.getConMod();

			break;

		case AtributoConstantes.CHA:

			if (null != passivo.getBonuses().get(BonusConstantes.RCHA)) {

				bonus = passivo.getBonuses().get(BonusConstantes.RCHA).getBonus();
			}

			tR = tR + passivo.getChaMod();

			break;

		}

		return tR;
	}

	private List<Criatura> fugir() {

		for (int i = 0; i < criaturas.size(); i++) {

			if (criatura.isEmFuga()) {

				fugiram.add(criaturas.get(i));

				criaturas.remove(i);

			}

		}

		return criaturas;

	}

	private List<Criatura> Loot() {

		System.out.println("É hora de aproveitar os prêmios que vem com a vitória\n");

		while (criaturas.size() != 0) {

			for (int j = 0; j < criaturas.size(); j++) {

				System.out.println(criaturas.get(j).getNome() + " pegue um item");

				System.out.println("1 Não quero nada");

				for (int i = 0; i < chao.size(); i++) {

					System.out.println((i + 2) + " " + item.getNome());

				}

				int escolhido = Uteis.escaneador(chao.size() + 1) - 1;

				if (escolhido == 0) {

					criaturas.remove(criaturas.get(j));

				} else {

					criaturas.get(j).getItens().put(chao.get(escolhido - 1).getNome(), chao.get(escolhido - 1));

					System.out.println("Você pega uma " + chao.get(escolhido - 1).getNome());

					chao.remove(escolhido - 1);

				}

				if (j < criaturas.size() - 1) {

					j++;

				} else {

					j = 0;
				}

			}

		}

		return criaturas;

	}

	private List<Criatura> criaturasVivas() {

		for (int i = 0; i < fugiram.size(); i++) {

			criaturas.add(fugiram.get(i));

		}

		return criaturas;
	}

}
