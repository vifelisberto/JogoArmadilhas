/*
*Jogo das armadilhas
3 peças - Jogadores
48 quadrados - Mais o inicio e fim (50)

Todas começam no inicio

A cada turno os jogadores jogam um par de dados
Peças andam pra frente com a soma dos pontos dos dados (menos perto da ultima casa)

Se cair na armadilha, perde a vez no próximo turno

3 armadilhas ( 15, 23, 36 )

vencedor - chegar no fim

se a soma dos pontos do dado for mais que o necessario para chegar no fim, 
a quantidade "a mais" deve ser retrocedido

2 dados
 */
package armadilhas;

import java.util.Scanner;

/**
 *
 * @author vfelisberto
 */
public class Armadilhas {

    public static void main(String[] args) {
        //Como não é possivel criar uma matriz de tipos diferentes, foi feito uma representação com vetores separados
        // ou seja a posicao 1 de qualquer vetor representa o jogador 1,  a posição 2 representa o jogador dois assim por diante
        String[] jogadores = new String[3];
        int[] posicaoJogador = new int[3];
        boolean[] jogaTurno = new boolean[3];
        boolean semGanhador = true;

        String[] tabuleiro = new String[50];
        int QtdTurnos = 0;

        //Utilitarios
        Scanner sc = new Scanner(System.in);

        //Solicita o nome de cada jogador e armazena no vetor
        for (int linha = 0; linha < 3; linha++) {
            System.out.println("Digite o nome do " + (linha + 1) + "º Jogador(a):");

            jogadores[linha] = sc.nextLine();
            posicaoJogador[linha] = 0;
            jogaTurno[linha] = true;
        }

        //Apresenta os jogadores que entraram no jogo
        //System.out.println("Esses são os jogadores: " + Arrays.toString(jogadores));
        //ler os valores que estão cada casa
        for (int j = 0; j < 3; j++) {
            System.out.println(jogadores[j]);
        }

        //Aqui o jogo é "iniciado", onde se inicia os turnos etc
        while (semGanhador) {
            //Iniciar um turno
            QtdTurnos = QtdTurnos++; //Turno é: A cada 3 jogadas ou a cada jogada ?

            for (int i = 0; i < jogadores.length; i++) {
                if (jogaTurno[i]) {
                    //Chamar jogador na posição i para jogar os dados.
                    System.out.println("Por favor " + jogadores[i] + " Jogue os dados!");

                    int dado1 = JogaDado();
                    int dado2 = JogaDado();
                    int pontosDados = dado1 + dado2;
                    
                    System.out.println("Posição Anterior: " + posicaoJogador[i]);
                    System.out.println("1º Dado: " + dado1 + " | 2º Dado: " + dado2 + " | Totalizando: " + pontosDados);

                    //Altera o posição do jogador em questão
                    posicaoJogador[i] += pontosDados;

                    if (posicaoJogador[i] > 49) {
                        int casasRetrocesso = (posicaoJogador[i] - 49);
                        System.out.println("Poxa, a quantidade de pontos nos dados ultrapassou o fim do tabuleiro :( em " + casasRetrocesso + " casa(s), então você irá voltar " + casasRetrocesso + " casa(s).");

                        posicaoJogador[i] = 49 - casasRetrocesso;
                        System.out.println("Você está na posição: " + posicaoJogador[i] + " do tabuleiro.");

                        jogaTurno[i] = false;
                    } else {
                        System.out.println("Você está na posição: " + posicaoJogador[i] + " do tabuleiro.");
                        jogaTurno[i] = true;
                    }

                    if (posicaoJogador[i] == 49) {
                        semGanhador = false;
                        System.out.println("Ganhador é: " + jogadores[i]);
                        return;
                    }
                } else {
                    System.out.println("Esse turno você não joga :/ " + jogadores[i]);
                    jogaTurno[i] = true;
                }
            }

            semGanhador = !VerificaGanhador(posicaoJogador);

        }

    }

    public static int JogaDado() {
        int pontos = (int) (Math.random() * 6) + 1;

        return pontos;
    }

    public static boolean VerificaGanhador(int[] posicao) {
        for (int i = 0; i < posicao.length; i++) {
            if (posicao[i] == 49) {
                return true;
            }
        }

        return false;
    }
}
