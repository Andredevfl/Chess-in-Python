import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        int i= 0;

        int[] casaAtual = new int[2];

        //Casa para verificar casas futuras
        int[] casaTeste = new int[2];

        // Opcoes de movimento
        int[][] opcoesDeMovimento = new int[2][8];

        int numPassos = 63;

        // Tabuleiro
        boolean[][] tabuleiro = new boolean[8][8];

        // matriz que guarda os movimentos errados
        boolean[] movimentosErrados = new boolean[8];

        // primeiro movimento
        Scanner scan = new Scanner(System.in);

        casaAtual = lerString(scan.next());

        //Casa atual vira true
        tabuleiro[casaAtual[0]][casaAtual[1]] = true;

        while (numPassos != 64) {

            //Array que decide o proximo movimento
            int[] menorMovimento = new int[2];

            //preenche os array com as opcoes de movimento
            preencherMovimentosValidos(casaAtual, tabuleiro, opcoesDeMovimento);

            int menor = 9;
            for (int j = 0; j < 7; j++) {
                //Atribui a opcao a ser contada a quantidade de passos

                if(opcoesDeMovimento[0][j] != -1){
                    casaTeste[0] = opcoesDeMovimento[0][j];
                } else {
                    break;
                }

                if(opcoesDeMovimento[1][j] != -1){
                    casaTeste[1] = opcoesDeMovimento[1][j];
                } else {
                    break;
                }

                if((contarMovimentos(casaTeste, tabuleiro) < menor) && (contarMovimentos(casaTeste, tabuleiro) != -1)){
                    menorMovimento[0] = casaTeste[0];
                    menorMovimento[1] = casaTeste[1];
                    menor = contarMovimentos(casaTeste, tabuleiro);
                }

            }

            System.out.println("Linha: " + menorMovimento[0] + " Coluna: " + menorMovimento[1]);

            numPassos++;

        }
    }
    // mais perto do centro, mais perto da ponta !=

    public static int[] popularOpcoes(int direcao){
        int[] opcao = new int[2];
        switch(direcao){
            //Direita para baixo ( 1 linha + 2 coluna )
            case 0:
                opcao[0] = 1;
                opcao[1] = 2;
                return  opcao;
            //direita para cima ( -1 linha +2 coluna)
            case 1:
                opcao[0] = -1;
                opcao[1] = 2;
                return  opcao;
            // Cima para direita ( -2 linha +1 coluna)
            case 2:
                opcao[0] = -2;
                opcao[1] = 1;
                return  opcao;
            //Cima para esquerda
            case 3:
                opcao[0] = -2;
                opcao[1] = -1;
                return  opcao;
            //Esquerda para cima
            case 4:
                opcao[0] = -1;
                opcao[1] = -2;
                return  opcao;
            // Esquerda para baixo
            case 5:
                opcao[0] = +1;
                opcao[1] = -2;
                return  opcao;
            //Baixo para esquerda -2
            case 6:
                opcao[0] = +2;
                opcao[1] = -1;
                return  opcao;
            //Baixo para direita
            case 7:
                opcao[0] = 2;
                opcao[1] = 1;
                return  opcao;
            default: return opcao;

        }
    }

    public static int[] lerString(String posicaoString){
        //Vetor com posicao
        int[] posicaoInt = new int[2];
        //Letra para transformar
        String cha = "" + posicaoString.charAt(0);
        //Numero a transformar
        String num = "" + posicaoString.charAt(1);
        //Int que armazena letra transformada

        int coluna = switch (cha) {
            case "A" -> 0;
            case "B" -> 1;
            case "C" -> 2;
            case "D" -> 3;
            case "E" -> 4;
            case "F" -> 5;
            case "G" -> 6;
            case "H" -> 7;
            default -> 0;
        };

        //le a Coluna
        posicaoInt[1] = coluna;

        //Le a linha
        posicaoInt[0] = Integer.parseInt(num) - 1;

        return posicaoInt;
    }

    //preenche os array com as opcoes de movimento
    public static void preencherMovimentosValidos(int[] casaAtual, boolean[][]tabuleiro, int[][]opcoesDeMovimento){
        int i = 0;
        do{
            int[] opcoes = popularOpcoes(i);
            if ((opcoes[0] + casaAtual[0]) <= 7 && ((opcoes[0] + casaAtual[0]) >= 0)
            && (opcoes[1] + casaAtual[1]) <= 7 && ((opcoes[1] + casaAtual[1]) >= 0)) {
                if(!tabuleiro[opcoes[0] + casaAtual[0]][opcoes[1] + casaAtual[1]]){
                    opcoesDeMovimento[0][i] = opcoes[0] + casaAtual[0];
                    opcoesDeMovimento[1][i] = opcoes[1] + casaAtual[1];

                } else{
                    opcoesDeMovimento[0][i] = -1;
                    opcoesDeMovimento[1][i] = -1;
                }

            }
            i++;

        } while (i != 8);

    }

    public static int contarMovimentos(int[] casaFutura, boolean[][] tabuleiro){

        int i = 0, contador = 0;
        while (i != 8){
            int[] opcoes = popularOpcoes(i);
            if ((opcoes[0] + casaFutura[0]) <= 7 && ((opcoes[0] + casaFutura[0]) >= 0)
                && (opcoes[1] + casaFutura[1]) <= 7 && ((opcoes[1] + casaFutura[1]) >= 0)) {

                if(!tabuleiro[opcoes[0] + casaFutura[0]][opcoes[1] + casaFutura[1]]){
                    contador++;

                }
            }
            i++;
        }

        System.out.println("Contador da casa: " + (casaFutura[0]) + " " + (casaFutura[1]) + " " + "Numbero: " + contador);
        return contador;
    }
}

/*
*                       {"A8", "B8", "C8", "D8", "E8", "F8", "G8", "H8"},
                        {"A7", "B7", "C7", "D7", "E7", "F7", "G7", "H7"},
                        {"A6", "B6", "C6", "D6", "E6", "F6", "G6", "H6"},
                        {"A5", "B5", "C5", "D5", "E5", "F5", "G5", "H5"},
                        {"A4", "B4", "C4", "D4", "E4", "F4", "G4", "H4"},
                        {"A3", "B3", "C3", "D3", "E3", "F3", "G3", "H3"},
                        {"A2", "B2", "C2", "D2", "E2", "F2", "G2", "H2"},
                        {"A1", "B1", "C1", "D1", "E1", "F1", "G1", "H1"}
* */