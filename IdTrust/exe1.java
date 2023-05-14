package br.com.idtrust.dev.junior;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;
import java.time.format.DateTimeFormatter;

public class InterviewDev {    

    private static final Set<Recebivel> RECEBIVEIS;

    // Block to know if user wants to make another consultation
    public static boolean isMakeAnotherConsultation(Scanner sc) {
        System.out.println("Deseja fazer outra consulta? (S/N)");
        String option = sc.next();
        return option.equalsIgnoreCase("S");
    }


    // Block to get value per due date
    private static void getValuePerDateVenc() {
        // the map will be organized by due date and the value will be the sum of the values of the same due date
        // it use a TreeMap because it is possible to organize by key
        Map<LocalDate, BigDecimal> map = new TreeMap<LocalDate, BigDecimal>();

        // for each receivable, get the due date and the value
        RECEBIVEIS.forEach(recebivel -> {
            LocalDate vencimento = recebivel.dataVencimento;
            BigDecimal valor = recebivel.valor;

            // if the map contains the due date, add the value to the current value
            // else, put the due date and the value in the map
            if (map.containsKey(vencimento)) {
                BigDecimal valorAtual = map.get(vencimento);
                map.put(vencimento, valorAtual.add(valor));
            } else {
                map.put(vencimento, valor);
            }
        });
        
        // organize the map by due date
        Map<LocalDate, BigDecimal> sortedMap = new TreeMap<LocalDate, BigDecimal>(map);

        // print the map
        sortedMap.forEach((k, v) -> System.out.println(k + " - " + v));
    }

    public static BigDecimal calcReciVenc() {
        BigDecimal soma = BigDecimal.ZERO;

        // for each receivable, if the due date is before the current date, add the value to the sum
        for (Recebivel recebivel : RECEBIVEIS) {
            if (recebivel.dataVencimento.isBefore(LocalDate.now())) {
                soma = soma.add(recebivel.valor);
            }
        }

        return soma;
    }

    public static BigDecimal getDateVenc(LocalDate date) {
        // for each receivable, if the due date is equals to the date, return the value
        for (Recebivel recebivel : RECEBIVEIS) {
            if (recebivel.dataVencimento.equals(date)) {
                return recebivel.valor;
            }
        }

        // if the date is not found, return 0
        return BigDecimal.ZERO;
    }

    public static String formatToReal(BigDecimal valor) {
        // format the value to brazilian currency
        return "R$ " + valor.toString().replace('.', ',');
    }

    public static long getDaysBetweenReceipt(LocalDate date) {
        long days = 0;

        // for each receivable, if the due date is equals to the date, 
        // return the days between the due date and the emission date
        for (Recebivel recebivel : RECEBIVEIS) {
            if (recebivel.dataVencimento.equals(date)) {
                days = recebivel.dataVencimento.toEpochDay() - recebivel.dataEmissao.toEpochDay();
            }
        }

        return days;
    }

    public static String concatAllFields() {
        String concat = "";

        // for each receivable, concat all fields of the receivable in a string separated by ";"
        for (Recebivel recebivel : RECEBIVEIS) {
            concat += "Código: " + recebivel.codigo + "; Valor: " + recebivel.valor + "; Data emissão: " + recebivel.dataEmissao + ";"
                    + recebivel.dataVencimento + "; Data vencimento: " + recebivel.valor + "\n";
        }

        return concat;
    }

    public static String formatDate(LocalDate date) {
        // format the date to brazilian format
        return date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    public static List<BigDecimal> addNewValue(List<BigDecimal> valores) {
        List<BigDecimal> newValues = new ArrayList<BigDecimal>();

        for (BigDecimal valor : valores) {
            // for each value, if the value is less than or equal to 100, add 5.90
            if (valor.compareTo(new BigDecimal(100.00)) <= 0) {
                // add 5.90 to the value
                valor = valor.add(new BigDecimal(5.90));

                // round the number to 2 decimal places
                valor = valor.setScale(2, RoundingMode.HALF_EVEN);

                newValues.add(valor);

            // if the value is equals to 150, add 3.55
            } else if (valor.compareTo(new BigDecimal(150.00)) == 0) {
                // add 3.55 to the value
                valor = valor.add(new BigDecimal(3.55));

                // round the number to 2 decimal places
                valor = valor.setScale(2, RoundingMode.HALF_EVEN);

                newValues.add(valor);
            
            // if the value is less than or equal to 200, add 15.00
            } else if (valor.compareTo(new BigDecimal(20.00)) <= 0) {
                // add 15.00 to the value
                valor = valor.add(new BigDecimal(15.00));

                // round the number to 2 decimal places
                valor = valor.setScale(2, RoundingMode.HALF_EVEN);

                newValues.add(valor);
            
            // if the value is higher than 200, add 2.10
            } else if (valor.compareTo(new BigDecimal(200.00)) > 0) {
                // add 2.10 to the value
                valor = valor.add(new BigDecimal(2.10));

                // round the number to 2 decimal places
                valor = valor.setScale(2, RoundingMode.HALF_EVEN);

                newValues.add(valor);

            // if the value is higher than 100, add 4.33
            } else if (valor.compareTo(new BigDecimal(100.00)) > 0) {
                // add 4.33 to the value
                valor = valor.add(new BigDecimal(4.33));

                // round the number to 2 decimal places
                valor = valor.setScale(2, RoundingMode.HALF_EVEN);

                newValues.add(valor);
            }
        }

        return newValues;
    }

    // block to get the option selected by the user and call the method
    public static void getOptionSelected(int option) {
        
        switch (option) {
            case 0:
                System.out.println("Saindo...");
                break;
            case 1:
                getValuePerDateVenc();
                break;
            case 2:

                System.out.println(calcReciVenc());
                break;
            case 3:
                
                System.out.println(formatToReal(getDateVenc(LocalDate.of(2023, 7, 25))));
                break;
            case 4:
                
                System.out.println(getDaysBetweenReceipt(LocalDate.of(2023, 10, 12)));
                break;
            case 5:
                System.out.println(concatAllFields());
                break;
            case 6:
                System.out.println(formatDate(LocalDate.of(2023, 6, 25)));
                break;
            case 7:
                
                List<BigDecimal> valores = Arrays.asList(new BigDecimal("88.88"), new BigDecimal("17.01"),
                new BigDecimal("20.00"), new BigDecimal("150.00"), new BigDecimal("124.21"), new BigDecimal("247.09"),
                new BigDecimal("100.00"), new BigDecimal("4.99"));

                valores = addNewValue(valores);

                System.out.println(valores);
                System.out.println(valores.stream().reduce(BigDecimal.ZERO, BigDecimal::add));
                break;
            default:
                System.out.println("Opção inválida");
                break;
        }
    }

    // block to clear the console
    public static void clearConsole() {
        // clear the console
        System.out.print("\033[H\033[2J");

        // flush the output stream and forces any buffered output bytes to be written out
        System.out.flush();
    }

    public static void main(String[] args) throws Exception {
        // create a scanner object to read the user input
        Scanner sc = new Scanner(System.in);

        int option = 0;
        
        System.out.println("Faça os exercícios abaixo usando os dados pré-criados na variável `RECEBIVEIS`.");
        System.out.println("Peço que prepare a resolução logo abaixo de cada enunciado.");
        System.out.println("");

        do {
            clearConsole();

            System.out.println("Escolha uma opção:");

            System.out.println("1 - Print a soma agrupando as mesmas datas de vencimentos");
            System.out.println("2 - Print a soma dos recebiveis ja vencidos");
            System.out.println("3 - Formate para moeda Real o valor do recebivel com vencimento 25/07/2023");
            System.out.println("4 - Print o prazo em dias entre emissao e vencimento do recebivel com vencimento 12/10/2023");
            System.out.println("5 - Print a concatenação de todos os campos do recebivel separando por ;");
            System.out.println("6 - Formate a data 2023-06-25 do recebivel para o formato dd/MM/yyyy");
            System.out.println("");
            System.out.println("Exercício extra:");
            System.out.println("7 - Dado uma lista da variável `valores` abaixo, acrescente um novo valor de acordo com as regras a seguir:");
            System.out.println("    -  R$5,90 para valores menor e igual que R$100,00");
            System.out.println("    -  R$15,00 para valores menor que R$20,00");
            System.out.println("    -  R$4,33 para valores maior que R$100,00");
            System.out.println("    -  R$2,10 para valores maior que R$200,00");
            System.out.println("    -  R$3,55 para valores igual que R$150,00");
            System.out.println("    Print o novo resultado na saída da condição de validação e no final print a soma de todos os novos valores da lista");

            System.out.println("Escolha o exercício que deseja executar (0 para sair):");

            option = sc.nextInt();
            getOptionSelected(option);

            if (option != 0) {
                // check if the user wants to make another consultation
                if (!isMakeAnotherConsultation(sc)) {
                    // if not, set the option to 0 to exit the program
                    option = 0;
                    System.out.println("Saindo...");
                }
            }
            
        } while (option != 0);

        // close the scanner object
        sc.close();

    }

    static {
        Set<Recebivel> rs = new HashSet<>();
        rs.add(Recebivel.create("1H01R6HA1", LocalDate.parse("2023-05-02"), LocalDate.parse("2023-08-09"),
                new BigDecimal("146.99")));
        rs.add(Recebivel.create("1H01R6HB1", LocalDate.parse("2023-05-02"), LocalDate.parse("2023-05-08"),
                new BigDecimal("592.18")));
        rs.add(Recebivel.create("1H01R6HC1", LocalDate.parse("2023-05-03"), LocalDate.parse("2023-06-28"),
                new BigDecimal("98.20")));
        rs.add(Recebivel.create("1H01R6HD1", LocalDate.parse("2023-05-06"), LocalDate.parse("2023-09-19"),
                new BigDecimal("726.01")));
        rs.add(Recebivel.create("1H01R6HE1", LocalDate.parse("2023-05-02"), LocalDate.parse("2023-05-08"),
                new BigDecimal("81.88")));
        rs.add(Recebivel.create("1H01R6HF1", LocalDate.parse("2023-05-03"), LocalDate.parse("2023-07-15"),
                new BigDecimal("221.34")));
        rs.add(Recebivel.create("1H01R6HG1", LocalDate.parse("2023-05-02"), LocalDate.parse("2023-07-25"),
                new BigDecimal("711.98")));
        rs.add(Recebivel.create("1H01R6HH1", LocalDate.parse("2023-05-05"), LocalDate.parse("2023-10-10"),
                new BigDecimal("100.27")));
        rs.add(Recebivel.create("1H01R6HI1", LocalDate.parse("2023-05-02"), LocalDate.parse("2023-10-12"),
                new BigDecimal("3021.83")));
        rs.add(Recebivel.create("1H01R6HJ1", LocalDate.parse("2023-05-03"), LocalDate.parse("2023-09-19"),
                new BigDecimal("1930.76")));

        RECEBIVEIS = Collections.unmodifiableSet(rs);
    }

    public static class Recebivel {

        public static Recebivel create(String codigo, LocalDate dataEmissao, LocalDate dataVencimento,
                BigDecimal valor) {
            Recebivel r = new Recebivel();
            r.codigo = codigo;
            r.dataEmissao = dataEmissao;
            r.dataVencimento = dataVencimento;
            r.valor = valor;
            return r;
        }

        private String codigo;
        private LocalDate dataEmissao;
        private LocalDate dataVencimento;
        private BigDecimal valor;

    }

}