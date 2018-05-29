package armadilhas;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author vifelisberto
 * @github https://github.com/vifelisberto/JogoArmadilhas (Private até a entrega)
 * JOGO DAS ARMADILHAS 3 Jogadores e 48 posições (mais início e fim 50, o vetor
 * se inicia em 0 e vai até 49) Todos começam no inicio(posição 0)
 *
 * A cada turno os jogadores jogam um par de dados As Peças andam pra frente
 * somandos os pontos existentes com a soma dos pontos dos dados, Se a soma dos
 * pontos dos dados for mais que o necessário para se chegar ao fim(49), A
 * quantidade ultrapassada "a mais" deve ser retrocedida.
 *
 * Se cair em alguma armadilha, o jogador perde a vez no próximo turno. São 3
 * armadilhas nas posições 15, 23, 36
 *
 * Vencedor - Quando existe algum jogador na posição 49
 */
public class Armadilhas {

    @SuppressWarnings("empty-statement")
    public static void main(String[] args) {
        /*Como não é possivel criar uma matriz de tipos diferentes, foi feito uma representação com vetores separados
        * ou seja a posicao 1 de qualquer vetor representa o jogador 1,  a posição 2 representa o jogador dois assim por diante.
         */
        String[] jogadores = new String[3];
        int[] posicaoJogador = new int[3];
        boolean[] jogaTurno = new boolean[3];
        int QtdTurnos = 0;
        Scanner sc = new Scanner(System.in);

        System.out.println("Olá! Seja bem-vindo ao Joga das Armadilhas! \n O objetivo é chegar na posição 49 do tabuleiro, \n mas cuidado com as armadilhas, elas podem te atrasar!\n");
        //Solicita o nome de cada jogador e armazena no vetor Jogadores
        for (int linha = 0; linha < 3; linha++) {
            System.out.println("Digite o nome do " + (linha + 1) + "º Jogador(a):");

            jogadores[linha] = sc.nextLine();
            //Todos os jogadore são colocados na posição inicial(0) e podem jogar no próximo turno.
            posicaoJogador[linha] = 0;
            jogaTurno[linha] = true;
        }

        //Apresenta-se os jogadores que entraram no jogo
        System.out.println("Esses são os jogadores: " + Arrays.toString(jogadores));
        System.out.println();

        //Aqui começa as ações do jogo
        //Enquanto não tiver ganhador, continua a execução para sempre
        while (true) {
            QtdTurnos++;//Ao iniciar um turno é somado mais 1

            //Para cada jogador, se ele estiver apto a jogar, é pedido para os dados serem jogados e as informações são exibidas em tela.
            for (int i = 0; i < jogadores.length; i++) {
                if (jogaTurno[i]) {
                    //Chamar jogador na posição i para jogar os dados.
                    System.out.println("Sua vez " + jogadores[i] + "! \n Por favor digite algo e presione enter para jogar os dados!");

                    if (sc.next() != null) {
                        int pontosDados = JogaDados(); //Método que joga e exibe o valor dos dois dados

                        System.out.println("Posição Anterior: " + posicaoJogador[i]);

                        //Altera o posição do jogador atual
                        posicaoJogador[i] += pontosDados;

                        //49 representa a casa Final do tabuleiro
                        if (posicaoJogador[i] > 49) {
                            int casasRetrocesso = posicaoJogador[i] - 49;
                            System.out.println("Poxa, a quantidade de pontos nos dados ultrapassou o fim do tabuleiro :( em " + casasRetrocesso + " casa(s), então você irá voltar " + casasRetrocesso + " casa(s) em relação a última casa.");

                            posicaoJogador[i] = 49 - casasRetrocesso;
                            System.out.println("Você está na posição: " + posicaoJogador[i] + " do tabuleiro.");
                        } else {
                            System.out.println("Você está na posição: " + posicaoJogador[i] + " do tabuleiro.");
                            //Armadilhas, caso o jogador esteja na casa 13 ou 23 ou 36 ele não poderar jogar no proximo turno
                            jogaTurno[i] = (posicaoJogador[i] != 13 && posicaoJogador[i] != 23 && posicaoJogador[i] != 36);

                            if (!jogaTurno[i]) {
                                System.out.println("Você caiu na armadilha :(, não irá poder jogar no próximo turno.");
                            }
                        }

                        if (posicaoJogador[i] == 49) {
                            System.out.println();
                            System.out.println("Com uma Quantidade de: " + QtdTurnos + " turnos");
                            System.out.println("O Ganhador é: " + jogadores[i] + "!!!");
                            return;
                        }
                    }
                } else {
                    System.out.println("Que pena! esse turno você não joga " + jogadores[i] + ", você caiu em uma armadilha no turno anterior :/");
                    jogaTurno[i] = true; //No próximo turno o jogador poderá jogar.
                }

                System.out.println();
            }

        }
    }

    public static int JogaDados() {
        //Joga os dois dados
        int dado1 = JogaDado();
        int dado2 = JogaDado();
        int pontos = dado1 + dado2;

        System.out.println("1º Dado: " + dado1 + " | 2º Dado: " + dado2 + " | Totalizando: " + pontos);

        return pontos;
    }

    public static int JogaDado() {
        //Gera números randomicos de 1 a 6
        int pontos = (int) (Math.random() * 6) + 1;

        return pontos;
    }
}
