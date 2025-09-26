package conta_bancaria;

import java.util.InputMismatchException;
import java.util.Scanner;

import conta_bancaria.controller.ContaController;
import conta_bancaria.model.Conta;
import conta_bancaria.model.ContaCorrente;
import conta_bancaria.model.ContaPoupanca;
import conta_bancaria.util.Cores;

public class Menu {
	//final = não ser modificado e ser constante
	//static para ser usado em tudo dessa pagina
	//private só funcionar dentro da classe menu
	private static final Scanner leia = new Scanner(System.in);
	private static final ContaController contaController = new ContaController();
	
	public static void main(String[] args) {
		
		int opcao;
		
		criarContasTeste();
		
		while(true) {
			System.out.println(Cores.TEXT_PURPLE + Cores.ANSI_BLACK_BACKGROUND
					+ "==================================");
			System.out.println("Banco do Brazil com Z - Generation");
			System.out.println("==================================");
			System.out.println("1 - Criar Conta                   ");
			System.out.println("2 - Listar Todas as Contas        ");
			System.out.println("3 - Buscar Conta por Numero       ");
			System.out.println("4 - Atualizar dados da Conta      ");
			System.out.println("5 - Apagar Conta                  ");
			System.out.println("6 - Sacar                         ");
			System.out.println("7 - Depositar                     ");
			System.out.println("8 - Tranferir Valores entre Contas");
			System.out.println("0 - Sair                          ");
			System.out.println("==================================");
			System.out.println("Entre com a opcao desejada:       ");
			System.out.println("==================================");

			try {
				opcao = leia.nextInt();
				leia.nextLine();;
			}
			catch(InputMismatchException e) {
				opcao = -1;
				System.out.println("\nDigite um numero inteiro entre 0 e 8: ");
				leia.nextLine();
			}
			
			
			if(opcao == 0) {
				System.out.println("\n Banco do Brazil com Z - O seu futuro começa aqui!");
				sobre();
				leia.close();
				System.exit(0);
			}
			switch (opcao) {
			case 1:
				System.out.println(Cores.TEXT_WHITE + "Criar Conta\n\n");
				
				cadastrarConta();
				
				keyPress();
				break;
			case 2:
				System.out.println(Cores.TEXT_WHITE + "Listar todas as Contas\n\n");

				listarContas();
				keyPress();
				break;
			case 3:
				System.out.println(Cores.TEXT_WHITE + "Consultar dados da Conta - por número\n\n");
				
				procurarContaPorNumero();
				
				keyPress();
				break;
			case 4:
				System.out.println(Cores.TEXT_WHITE + "Atualizar dados da Conta\n\n");
				
				atualizarConta();

				keyPress();
				break;
			case 5:
				System.out.println(Cores.TEXT_WHITE + "Apagar a Conta\n\n");

				deletarConta();
				
				keyPress();
				break;
			case 6:
				System.out.println(Cores.TEXT_WHITE + "Saque\n\n");

				keyPress();
				break;
			case 7:
				System.out.println(Cores.TEXT_WHITE + "Depósito\n\n");

				keyPress();
				break;
			case 8:
				System.out.println(Cores.TEXT_WHITE + "Transferência entre Contas\n\n");

				keyPress();
				break;
			default:
				System.out.println(Cores.TEXT_RED_BOLD + "\nOpção Inválida!\n" + Cores.TEXT_RESET);
				break;
			}
		}
	}

	public static void sobre() {
		System.out.println("\n==================================");
		System.out.println("Projeto Desenvolvido por: ");
		System.out.println("Maria Julia Medeiros - MariaM@genstudents.org");
		System.out.println("github.com/Maju2016");
		System.out.println("==================================");
	}
	
	public static void keyPress() {
		System.out.println(Cores.TEXT_RESET + "\n\nPressione Enter para continuar...");
		leia.nextLine();
	}
	
	private static void criarContasTeste() {
		contaController.cadastrar(new ContaCorrente(contaController.gerarNumero(), 456, 1, "Thuany Silva", 1000000.00f, 100000.00f));
		contaController.cadastrar(new ContaPoupanca(contaController.gerarNumero(), 456, 2, "Marcia Condarco", 1000000.00f, 10));
	}
	
	private static void listarContas() {
		contaController.listarTodas();
	}
	
	private static void cadastrarConta() {
		
		System.out.println("Digite o numero da Agencia: ");
		int agencia = leia.nextInt();
		
		System.out.println("Digite o nome do Titular: ");
		leia.skip("\\R"); //para ignorar ou o enter e funcionar certo
		String titular = leia.nextLine();
		
		System.out.println("Digite o Tipo da conta (1 - CC | 2 - CP): ");
		int tipo = leia.nextInt();

		System.out.println("Digite o Saldo Inicial: ");
		float saldo = leia.nextFloat();
		
		switch(tipo) {
		case 1 -> {
			System.out.print("Digite o Limite inicial: ");
			float limite = leia.nextFloat();
			contaController.cadastrar(new ContaCorrente(contaController.gerarNumero(), agencia, tipo, titular, saldo, limite));
		}
		case 2 -> {
			System.out.print("Digite o dia do aniversario da conta: ");
			int aniversario = leia.nextInt();
			leia.nextLine();
			contaController.cadastrar(new ContaPoupanca(contaController.gerarNumero(), agencia, tipo, titular, saldo, aniversario));
		}
		default -> System.out.println(Cores.TEXT_RED + "Tipo de conta invalido!" + Cores.TEXT_RESET);
		}
	}
	
	private static void procurarContaPorNumero() {
		
		System.out.print("Digite o numero da conta: ");
		int numero = leia.nextInt();
		leia.nextLine();
		
		contaController.procurarPorNumero(numero);
		
	}
	
	private static void deletarConta() {
		System.out.printf("Digite o numero da conta: ");
		int numero = leia.nextInt();
		leia.nextLine();
		
		Conta conta = contaController.buscarCollection(numero);
		
		if(conta != null) {
			System.out.print("\nTem certeza que deseja excluir esta conta? (S/N): "); //printf é usado para deixar resposta do lado e não em baixo
			String confirmacao = leia.nextLine();
		
			if(confirmacao.equalsIgnoreCase("S")) { //equalsIgnoreCse é para ignorar se a letra for Maiuscula ou miniscula
				contaController.deletar(numero);
			}
			else {
				System.out.println("\nOperacao cancelada!");
			}
		}
		else {
			System.out.printf("\n A conta numero %d nao foi encontrada!", numero);
		}
	}
	
	private static void atualizarConta() {
		System.out.printf("Digite o numero da conta: ");
		int numero = leia.nextInt();
		leia.nextLine();
		
		Conta conta = contaController.buscarCollection(numero);
		
		if(conta != null) {
			
			int agencia = conta.getAgencia();
			String titular = conta.getTitular();
			int tipo = conta.getTipo();
			float saldo = conta.getSaldo();
			
			System.out.printf("A Agencia atual: %d\nNova Agencia (Pressione ENTER para manter o valor atual: ", agencia);
			String entrada = leia.nextLine();
			agencia = entrada.isEmpty() ? agencia : Integer.parseInt(entrada);
			
			System.out.printf("O nome do Titular atual: %s\nNovo Titular (Pressione ENTER para manter o valor atual: ", titular);
			entrada = leia.nextLine();
			titular = entrada.isEmpty() ? titular : entrada;

			System.out.printf("O Saldo atual: %.2f\nNovo Saldo(Pressione ENTER para manter o valor atual: ", saldo);
			entrada = leia.nextLine();
			saldo = entrada.isEmpty() ? saldo : Float.parseFloat(entrada);
			
			switch(tipo) {
			case 1 -> {
				float limite = ((ContaCorrente) conta).getLimite();
				
				System.out.printf("O Limite atual: %.2f\nNovo Limite (Pressione ENTER para manter o valor atual: ", limite);
				entrada = leia.nextLine();
				limite = entrada.isEmpty() ? limite : Float.parseFloat(entrada);
				contaController.atualizar(new ContaCorrente(numero, agencia, tipo, titular, saldo, limite));
			}
			case 2 -> {
				int aniversario = ((ContaPoupanca) conta).getAniversario();
				
				System.out.printf("O aniversario atual é: %d\nNova Aniversario (Pressione ENTER para manter o valor atual: ", aniversario);
				entrada = leia.nextLine();
				aniversario = entrada.isEmpty() ? aniversario : Integer.parseInt(entrada);
				contaController.atualizar(new ContaPoupanca(numero, agencia, tipo, titular, saldo, aniversario));
			}
			default -> System.out.println(Cores.TEXT_RED + "Tipo de conta invalido!" + Cores.TEXT_RESET);
			}
		}
		else {
			System.out.printf("\n A conta numero %d nao foi encontrada!", numero);
		}
		
	}
	
}
