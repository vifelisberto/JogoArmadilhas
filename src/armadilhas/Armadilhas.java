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

import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author vfelisberto(21034071), Felipe, Gustavo
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
        //Variavel usada para saber se existe ganhador
        boolean semGanhador = true;

        //String[] tabuleiro = new String[50];
        //Contadores
        int QtdTurnos = 0;

        //Utilitarios
        Scanner sc = new Scanner(System.in);

        //Solicita o nome de cada jogador e armazena no vetor Jogadores
        for (int linha = 0; linha < 3; linha++) {
            System.out.println("Digite o nome do " + (linha + 1) + "º Jogador(a):");

            jogadores[linha] = sc.nextLine();
            //Todos os jogadore são colocados na posição inicial(0) e podem jogar no próximo turno.
            posicaoJogador[linha] = 0;
            jogaTurno[linha] = true;
        }

        //Apresenta-se os jogadores que entraram no jogo
        System.out.println("Esses são os jogadores: " + Arrays.toString(jogadores));;
        //ler os valores que estão cada casa
        /*for (int j = 0; j < 3; j++) {
            System.out.println("Jogador " + (j + 1) + ": " + jogadores[j]);
        }*/

        //Aqui o jogo é "iniciado", onde se inicia os turnos etc
        //Enquanto não tiver ganhador, continua a execução
        while (semGanhador) {
            //Ao iniciar um turno é somado mais 1
            QtdTurnos++;

            //Para cada jogador, se ele estiver apto a jogar, é feito as jogadas
            for (int i = 0; i < jogadores.length; i++) {
                if (jogaTurno[i]) {
                    //Chamar jogador na posição i para jogar os dados.
                    System.out.println("Sua vez " + jogadores[i] + "! \n Por favor jogue os dados!");

                    int dado1 = JogaDado();
                    int dado2 = JogaDado();
                    int pontosDados = dado1 + dado2;

                    System.out.println("Posição Atual: " + posicaoJogador[i]);
                    System.out.println("1º Dado: " + dado1 + " | 2º Dado: " + dado2 + " | Totalizando: " + pontosDados);

                    //Altera o posição do jogador atual
                    posicaoJogador[i] += pontosDados;

                    //49 representa a casa 48 do tabuleiro (vetores se iniciam na posição 0)
                    if (posicaoJogador[i] > 49) {
                        int casasRetrocesso = (posicaoJogador[i] - 49);
                        System.out.println("Poxa, a quantidade de pontos nos dados ultrapassou o fim do tabuleiro :( em " + casasRetrocesso + " casa(s), então você irá voltar " + casasRetrocesso + " casa(s).");

                        posicaoJogador[i] = 49 - casasRetrocesso;
                        System.out.println("Você está na posição: " + posicaoJogador[i] + " do tabuleiro.");

                        jogaTurno[i] = false;
                    } else {
                        System.out.println("Você está na posição: " + posicaoJogador[i] + " do tabuleiro.");
                        jogaTurno[i] = (posicaoJogador[i] != 13 && posicaoJogador[i] != 23 && posicaoJogador[i] != 36);
                    }

                    if (posicaoJogador[i] == 49) {
                        semGanhador = false;
                    }
                } else {
                    System.out.println("Que pena! esse turno você não joga," + jogadores[i] + " você caiu em uma armadilha no turno anterior :/");
                    jogaTurno[i] = true;
                }
            }

            semGanhador = !VerificaGanhador(posicaoJogador);

        }

        System.out.println("Ganhador é: " + jogadores[i]);
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
