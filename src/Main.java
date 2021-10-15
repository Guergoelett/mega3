/**
 mega3
 */

import java.util.Scanner;

class MegaSena {

    static int NUMERO_DEZENAS = 6;

    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);

        int[] sorteio = sorteaSena();
        int[] aposta = new int[NUMERO_DEZENAS];

        //trecho processa a aposta
        System.out.println("Faça sua aposta: ");
        for (int i = 0; i < NUMERO_DEZENAS; i++) {
            int nroAposta;
            boolean repetido = false;

            do {
                System.out.print("Informe a dezena "+(i+1)+": ");
                nroAposta = teclado.nextInt();
                if (nroAposta <= 0) {
                    System.out.println("Número inválido, aposta cancelada!");
                    return;
                }
                repetido = existeNumero(aposta, nroAposta);
                if (repetido) {
                    System.out.println("Número repetido!");
                }
            } while(repetido); // evita repetição de número

            aposta[i] = nroAposta;
        }

        System.out.println("\nConfira sua aposta: ");
        for (int i = 0; i < aposta.length; i++) {
            System.out.print(aposta[i] + " ");
        }

        System.out.println("\nResultado do sorteio: ");
        for (int i = 0; i < sorteio.length; i++) {
            System.out.print(sorteio[i] + " ");
        }

        int nroAcertos = contaAcertos(sorteio, aposta);
        System.out.println("\nNúmero de acertos: "+nroAcertos);

        switch (nroAcertos) {
            case 4: System.out.println("Parabéns. Você acertou a quadra!"); break;
            case 5: System.out.println("Parabéns. Você acertou a quina!"); break;
            case 6: System.out.println("Parabéns. Você é campeão da Mega!"); break;
            default: System.out.println("Não foi dessa vez"); break;
        }
    }

    /*
     * Função retorna uma array com 6 números gerados randomicamente (API do Java),
     * sem duplicidade, representando o sorteio da megasena.
     */
    static int[] sorteaSena() {
        int[] resultado = new int[NUMERO_DEZENAS];

        for (int i = 0; i < NUMERO_DEZENAS; i++) {
            int sorteado;
            boolean repetido = false;

            do {
                sorteado = (int) (Math.random()*60)+1; //nro aleatorio de 1 a 60
                repetido = existeNumero(resultado, sorteado);
            } while(repetido); // evita repetição de número

            resultado[i] = sorteado;
        }
        return resultado;
    }

    /*
     * Função comparada cada número apostado, com os números sorteados.
     * Por fim retorna a quantidade de acertos.
     */
    static int contaAcertos(int[] sorteio, int[] aposta) {
        int acertos = 0;
        for (int i = 0; i < NUMERO_DEZENAS; i++) {
            int nroAposta = aposta[i];

            //compara cada nro apostado com os sorteados
            if (existeNumero(sorteio, nroAposta)) {
                acertos++;
            }
        }
        return acertos;
    }

    /*
     * Função indica se o n existe no array numeros.
     * Útil para validar duplicadade de dados.
     */
    static boolean existeNumero(int[] numeros, int n) {
        for (int i = 0; i < numeros.length; i++) {
            if (numeros[i] == n) {
                return true;
            }
        }
        return false;
    }

}