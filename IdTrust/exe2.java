import java.util.Scanner;

/*
 * Resposta para a primeira pergunta:
 * Monte a estrutura condicional if/else para as regras abaixo e no final o que será mostrado para cada valores:
 */

public class exe2 {

    public static void main(String[] args) throws Exception {
        int idade;
        char sexo;

        Scanner scanner = new Scanner(System.in);

        System.out.println("Digite a idade: ");
        idade = scanner.nextInt();
        System.out.println("Digite o sexo: ");
        sexo = scanner.next().charAt(0);
        sexo = Character.toUpperCase(sexo);

        if (sexo == 'M' && idade >= 65) {
            System.out.println("APOSENTADO");
        } else if (sexo == 'F' && idade >= 60) {
            System.out.println("APOSENTADA");
        } else if (idade >= 13 && idade <= 18) {
            System.out.println("ADOLESCENTE");
        } else if (idade < 13) {
            System.out.println("CRIANÇA");
        } else {
            System.out.println("ADULTO");
        }
    }

    /*
     * Resultados dos valores testados:
            a) masculino;74 = APOSENTADO
            b) feminino;4 = CRIANÇA
            c) feminino;13 = ADOLESCENTE
            d) masculino;60 = ADULTO
            e) masculino;19 = ADULTO
            f) feminino;60 = APOSENTADA
     */
}

/*
 * Resposta para a segunda pergunta:
 * De acordo com o algoritmo a seguir, escolha uma das alternativas abaixo.
        
    contador :=  3
        soma  := 57
        for (contador <= 10 ) {
            if (contador < 5 || contador == 8 ) {
                soma := soma - contador
            } else {
                    soma = soma + contador
            }
            contador++
        } 
        print(“O valor da soma é ” + soma)

 * Resposta correta: 
 * Alternativa c: O valor da soma é 79
 */
