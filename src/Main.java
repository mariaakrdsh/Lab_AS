import java.util.Scanner;
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("1. факторизація ро-Полларда \n" +
                    "2. великий крок - малий крок \n" +
                    "3. функція Ейлера \n" +
                    "4. функція Мьобіуса \n" +
                    "5. символ Лежандра \n" +
                    "6. символ Якобі \n" +
                    "7. алгоритм Чіполли \n" +
                    "8. алгоритм Соловея-Штрассена");
            System.out.println("Виберіть алгоритм:");
            int a = scanner.nextInt();
            switch (a) {
                case 1: {
                    Algorithms.pollard();
                    break;
                }
                case 2:
                    Algorithms.bigStepSmallStep();
                    break;
                case 3:
                    Algorithms.eulerFunction();
                    break;
                case 4:
                    Algorithms.mobiusFunction();
                    break;
                case 5:
                    Algorithms.legendreInterface();
                    break;
                case 6:
                    Algorithms.jacobiInterface();
                    break;
                case 7:
                    Algorithms.chipollaAlgorithm();
                    break;
                case 8:
                    Algorithms.soloveyShtrassen();
                    break;
                default:
                    break;
            }
        }
    }
}