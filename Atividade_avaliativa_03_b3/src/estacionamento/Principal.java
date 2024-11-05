package estacionamento;

import gerenciamento.Vaga;
import gerenciamento.Veiculo;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Principal {

    private static List<Vaga> vagas = new ArrayList<>();
    private static List<Veiculo> veiculosEstacionados = new ArrayList<>();
    private static Scanner scan = new Scanner(System.in);

    
    public static void main(String[] args) {
        inicializarVagas();
        Boolean Loop = true;
        
        while(Loop){ 
        System.out.println("Seja bem Vindo!!"
        		+ "\nEscolha como gostaria de acessar o sistema:\n"
        		+ "1)Funcionário\n"
        		+ "2)Administrador\n"
        		+ "3)Sair\n");
        
        int escolha = scan.nextInt();
        
        if(escolha==1) {
        	menuPrincipal();
        }
        else if(escolha==2) {
        	administrador();
        }
        else if(escolha==3) {
        	
        }
        else {
        	System.out.println("Numero errado...");
        	
        }
        
        
    }
    }
    
    private static void administrador() {
        boolean loop = true;
        while (loop) {
            System.out.println("\nMenu do Administrador:\n"
                    + "1) Ver Vagas Disponíveis e Ocupadas\n"
                    + "2) Ver Veículos Estacionados (Placas)\n"
                    + "3) Ver Tempo de Permanência por Veículo\n"
                    + "4) Voltar ao Menu Principal\n");

            int escolha = scan.nextInt();

            switch (escolha) {
                case 1 -> listarVagas();
                case 2 -> listarVeiculosEstacionados();
                case 3 -> listarTempoDePermanencia();
                case 4 -> loop = false;
                default -> System.out.println("Opção inválida.");
            }
        }
    }

    // Opção 2: Verificar placas dos veículos estacionados
    private static void listarVeiculosEstacionados() {
        System.out.println("Veículos Estacionados (Placas):");
        for (Veiculo veiculo : veiculosEstacionados) {
            System.out.println("Placa: " + veiculo.getPlaca());
        }
    }

    // Opção 3: Exibir tempo de permanência de cada veículo
    private static void listarTempoDePermanencia() {
        System.out.println("Tempo de Permanência dos Veículos:");
        for (Veiculo veiculo : veiculosEstacionados) {
            int horas = calcularHorasDePermanencia(veiculo);
            System.out.println("Placa: " + veiculo.getPlaca() + " - Horas: " + horas);
        }
    }

    // Método auxiliar para calcular a diferença entre hora de entrada e hora atual (simulado)
    private static int calcularHorasDePermanencia(Veiculo veiculo) {
        // Simulação: substitua por uma lógica para calcular com base em horário real
        return (int) (Math.random() * 5 + 1); // Exemplo de horas aleatórias para simplificação
    }
    
    
    
    
   
    
    private static void inicializarVagas() {
        vagas.add(new Vaga(1, "pequeno"));
        vagas.add(new Vaga(2, "médio"));
        vagas.add(new Vaga(3, "grande"));
    }

    private static void menuPrincipal() {
        while (true) {
            System.out.println("\n1) Registrar Entrada\n2) Registrar Saída\n3) Listar Vagas\n4) Sair");
            int opcao = scan.nextInt();

            switch (opcao) {
                case 1 -> registrarEntrada();
                case 2 -> registrarSaida();
                case 3 -> listarVagas();
                case 4 -> {
                    System.out.println("Encerrando o sistema.");
                    return;
                }
                default -> System.out.println("Opção inválida.");
            }
        }
    }

    private static void registrarEntrada() {
        System.out.print("Digite a placa do veículo: ");
        String placa = scan.next();
        System.out.print("Digite o tamanho do veículo (pequeno, médio, grande): ");
        String tamanho = scan.next();

        Vaga vagaDisponivel = encontrarVagaDisponivel(tamanho);

        if (vagaDisponivel != null) {
            Veiculo veiculo = new Veiculo(placa, tamanho);
            veiculosEstacionados.add(veiculo);
            vagaDisponivel.ocuparVaga();
            System.out.println("Veículo registrado na vaga " + vagaDisponivel.getNumero());
        } else {
            System.out.println("Não há vagas disponíveis para esse tamanho.");
        }
    }

    private static Vaga encontrarVagaDisponivel(String tamanho) {
        for (Vaga vaga : vagas) {
            if (vaga.isDisponivel() && vaga.getTamanho().equalsIgnoreCase(tamanho)) {
                return vaga;
            }
        }
        return null;
    }

    private static void registrarSaida() {
        System.out.print("Digite a placa do veículo: ");
        String placa = scan.next();

        Veiculo veiculo = encontrarVeiculoEstacionado(placa);
        if (veiculo != null) {
            Vaga vaga = encontrarVagaOcupada(veiculo.getTamanho());
            if (vaga != null) {
                vaga.liberarVaga();
                veiculosEstacionados.remove(veiculo);
                System.out.println("Saída registrada. Valor a pagar: " + calcularValor(1) + " reais.");
            }
        } else {
            System.out.println("Veículo não encontrado.");
        }
    }

    private static Veiculo encontrarVeiculoEstacionado(String placa) {
        for (Veiculo veiculo : veiculosEstacionados) {
            if (veiculo.getPlaca().equalsIgnoreCase(placa)) {
                return veiculo;
            }
        }
        return null;
    }

    private static Vaga encontrarVagaOcupada(String tamanho) {
        for (Vaga vaga : vagas) {
            if (!vaga.isDisponivel() && vaga.getTamanho().equalsIgnoreCase(tamanho)) {
                return vaga;
            }
        }
        return null;
    }

    private static int calcularValor(int horas) {
        if (horas <= 1) return 5;
        if (horas <= 3) return 10;
        return 15;
    }

    private static void listarVagas() {
        System.out.println("Status das Vagas:");
        for (Vaga vaga : vagas) {
            System.out.println(vaga);
        }
    }
}
