import java.util.Scanner;

public class Algorithms {
    public static int[] primes = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71,
            73, 79, 83, 89, 97, 101, 103, 107, 109, 113, 127, 131, 137, 139, 149, 151, 157, 163, 167, 173, 179,
            181, 191, 193, 197, 199, 211, 223, 227, 229};

    public static boolean isPrime(int n){
        if (n < 2)
            return false;
        for (int i = 2; i * i <= n; i++)
            if (n % i == 0)
                return false;
        return true;
    }

    public static int gcd(int n1, int n2) {
        if (n2 == 0) {
            return n1;
        }
        return gcd(n2, n1 % n2);
    }

    public static int f(int x,int n){
        return (x * x + 1) % n;
    }

    public static void pollard(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введіть число n:");
        int n = scanner.nextInt();
        int x = (int)(Math.random() % n);
        int y = x;
        int d = 1;
        while (d == 1){
            x = f(x,n)%n;
            y = f(f(y,n),n)%n;
            d = gcd(n,Math.abs(x-y));
        }
        if (d != n)
            System.out.println(d + "  " + n/d);
        else
            System.out.println("Нетривіальних дільників немає");
    }

    public static void bigStepSmallStep(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введіть число n:"); // mod n
        int n = scanner.nextInt();
        System.out.println("Введіть число а:"); // число яке підносимо в степінь х
        int a = scanner.nextInt()%n;
        System.out.println("Введіть число b:"); // результат
        int b = scanner.nextInt()%n;
        int m = (int)Math.sqrt(n); // округлений вгору корінь квадратний з n
        if (Math.sqrt(n)%1 > 0) m++; // якщо корінь взявся не націло додамо одиничку
        int[] arr = new int[m];
        arr[0] = 1;
        for (int j = 1; j < m; j++) { // проходимось по масиву від 1 по m-1 записуємо a^j mod n
            arr[j] = (arr[j-1]*a)%n;
        }
        int am = (int) (Math.pow(a,m)%n); // am = a^m mod n
        int ai;
        for (int i = 0; i < m; i++) {
            ai = (int) (Math.pow(am,i)%n); // ai = am ^ i mod n
            for (int j = 0; j < m; j++) {
                if (ai*arr[j]%n == b){ // перевірка чи ai * arr[i] % n == b
                    System.out.println("x = " + (i*m+j));
                    return;
                }
            }
        }
        System.out.println("х не знайдено");
    }

    public static void eulerFunction(){//Функція підраховує кількість додатних цілих чисел,
        // менших за задане ціле число, яке є відносно простими числами до заданого цілого числа.
        // тобто чисел, у яких найбільший спільний дільник з n дорівнює 1.
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введіть число n:");
        int n = scanner.nextInt();
        int result = 1;
        for (int i = 2; i < n; i++)
            if (gcd(i, n) == 1)
                result++;
        System.out.println(result);
    }

    public static void mobiusFunction() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введіть число n:");
        int n = scanner.nextInt();
        if (n == 1) {
            System.out.println(1);
            return;
        }
        int p = 0;
        for (int i = 1; i <= n; i++) {
            if (n % i == 0 && isPrime(i)) {
                if (n % (i * i) == 0) { // Check if N is divisible by i^2
                    System.out.println(0);
                    return;
                } else
                    p++;
            }
        }
        System.out.println((int) Math.pow(-1, p));
    }

    public static void legendreInterface(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введіть число а:");
        int a = scanner.nextInt();
        System.out.println("Введіть число р:");
        int p = scanner.nextInt();
        System.out.println(legendreSymbol(a,p));
    }

    public static Integer legendreSymbol(int a, int p){
        if ((p < 3) || !(isPrime(p))) {
            return null;
        }
        if (a % p == 0)
            return 0;
        if (Math.pow(a, ((p-1)/2) % p) % p == 1)
            return 1;
        return -1;
    }

    public static void jacobiSymbol(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введіть число а:");
        int a = scanner.nextInt();
        System.out.println("Введіть число m:");
        int m = scanner.nextInt();
        if ((m < 3) || (m % 2 == 0)) {
            System.out.println("не знайдено");
            return;
        }
        int res = 1;
        for (int i = 0; i < Math.sqrt(m); i++) {
            if (m % primes[i] == 0){
                while (m % primes[i] == 0){
                    m /= primes[i];
                    res *= legendreSymbol(a, primes[i]);
                }
            }
            if (m == 1){
                System.out.println(res);
                return;
            }
        }
    }

    public static void chipollaAlgorithm(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введіть число n:");
        int n = scanner.nextInt();
        System.out.println("Введіть число р:");
        int p = scanner.nextInt();
        if (legendreSymbol(n,p) != 1){
            System.out.println("коренів нема");
            return;
        }
        int a = 0;
        while (legendreSymbol(a * a - n, p) != -1){
            a++;
        }
        int b = 1;
        int a1 = a;
        int b1 = b;
        int sqrt = a * a - n;
        for (int i = 1; i < (p+1)/2; i++) {
            int t = a1;
            a1 = (a1*a + b1*b*sqrt) % p;
            b1 = (t * b + b1 * a) % p;
        }
        if (a1<0) a1 += p;
        System.out.println("корені рівняння:");
        System.out.println(a1 + " " + ((-a1 + p) % p));
    }

    public static void millerRabinAlgorithm(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введіть число а:");
        int a = scanner.nextInt();
        if (gcd(a,6) > 1){
            System.out.println("не підходить");
            return;
        }
        int n = 0;
        int q = a-1;
        while (q % 2 == 0){
            q/=2;
            n++;
        }
        int N = 200;
        for (int i = N; i > 0; i--) {
            int k = (int)(Math.random() % (a - 2)) + 2;
            int b = (int) (Math.pow(k,q) % a);
            if ((b != 1) && (b != a - 1)){
                for (int j = 0; j < n; j++) {
                    b = (b*b)%a;
                    if (b == a - 1) break;
                }
                System.out.println(a + " є складеним");
                return;
            }
        }
        System.out.println(a + " є простим з ймовiрнiстю ≥ " + (1-Math.pow(4,1 - N)));
    }
}