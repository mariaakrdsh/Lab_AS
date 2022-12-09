import java.util.Scanner;
import java.util.Random;
import java.math.BigInteger;
public class Algorithms {
    public static int[] primes = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71,
            73, 79, 83, 89, 97, 101, 103, 107, 109, 113, 127, 131, 137, 139, 149, 151, 157, 163, 167, 173, 179,
            181, 191, 193, 197, 199, 211, 223, 227, 229};

    public static boolean isPrime(int n) {
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

    public static int f(int x, int n) {
        return (x * x + 1) % n;
    }

    public static void pollard() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введіть число n:");
        int n = scanner.nextInt();
        int x = (int) (Math.random() % n);
        int y = x;
        int d = 1;
        while (d == 1) {
            x = f(x, n) % n; // хід черепахи
            y = f(f(y, n), n) % n; // хід зайця
            d = gcd(n, Math.abs(x - y));
        }
        if (d != n)
            System.out.println(d + "  " + n / d);
        else
            System.out.println("Нетривіальних дільників немає");
    }

    public static void bigStepSmallStep() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введіть число n:"); // mod n
        int n = scanner.nextInt();
        System.out.println("Введіть число а:"); // число яке підносимо в степінь х
        int a = scanner.nextInt() % n;
        System.out.println("Введіть число b:"); // результат
        int b = scanner.nextInt() % n;
        int m = (int) Math.sqrt(n); // округлений вгору корінь квадратний з n
        if (Math.sqrt(n) % 1 > 0) m++; // якщо корінь взявся не націло додамо одиничку
        int[] arr = new int[m];
        arr[0] = 1;
        for (int j = 1; j < m; j++) { // проходимось по масиву від 1 по m-1 записуємо a^j mod n
            arr[j] = (arr[j - 1] * a) % n;
        }
        int am = (int) (Math.pow(a, m) % n); // am = a^m mod n
        int ai;
        for (int i = 0; i < m; i++) {
            ai = (int) (Math.pow(am, i) % n); // ai = am ^ i mod n
            for (int j = 0; j < m; j++) {
                if (ai * arr[j] % n == b) { // перевірка чи ai * arr[i] % n == b
                    System.out.println("x = " + (i * m + j));
                    return;
                }
            }
        }
        System.out.println("х не знайдено");
    }

    public static void eulerFunction() {//Функція підраховує кількість додатних цілих чисел,
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
                if (n % (i * i) == 0) { // повертає 0, якщо n ділиться на квадрат простого числа
                    System.out.println(0);
                    return;
                } else
                    p++;
            }
        }
        System.out.println((int) Math.pow(-1, p));
    }

    public static void legendreInterface() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введіть число а:");
        int a = scanner.nextInt();
        System.out.println("Введіть число р:");
        int p = scanner.nextInt();
        System.out.println(legendreSymbol(a, p));
    }

    public static Integer legendreSymbol(int a, int p) {
        if ((p < 3) || !(isPrime(p))) {
            return null;
        }
        if (a % p == 0) // якщо а ділить p
            return 0;
        if (Math.pow(a, ((p - 1) / 2) % p) % p == 1)
            return 1; //якщо а є квадратичним лишком за мод p
        return -1; //якщо а є квадратичним нелишком за мод p
    }

    public static void jacobiInterface() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введіть число а:");
        int a = scanner.nextInt();
        System.out.println("Введіть число m:");
        int m = scanner.nextInt();
        System.out.println(jacobiSymbol(a, m));
    }

    public static Integer jacobiSymbol(int a, int m) {
        if ((m < 3) || (m % 2 == 0)) {
            if (m == 1) { // для всіх m=1 символ Якобі 1
                return 1;
            }
            return null;
        }
        int res = 1;
        for (int i = 0; i < Math.sqrt(m); i++) {
            if (m % primes[i] == 0) {
                while (m % primes[i] == 0) { //розкладаємо m на прості м-ки
                    m /= primes[i];
                    res *= legendreSymbol(a, primes[i]); // Застосовуємо Лежандра для (а/p), p - простий  м-к m
                }
            }
        }
        return res;
    }

    public static void chipollaAlgorithm() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введіть число n:");
        int n = scanner.nextInt();
        System.out.println("Введіть число р:");
        int p = scanner.nextInt();
        if (Legendre(n, p) != 1) {
            System.out.println("коренів нема");
            return;
        }
        int a = 0;
        while (Legendre(a * a - n, p) != -1) { // перебором шукаємо а
            a++;
        }
        int b = 1;
        int a1 = a;
        int b1 = b;
        int sqrt = a * a - n;
        for (int i = 1; i < (p + 1) / 2; i++) {
            int t = a1;
            a1 = (a1 * a + b1 * b * sqrt) % p;
            b1 = (t * b + b1 * a) % p;
        }
        if (a1 < 0) a1 += p;
        System.out.println("корені рівняння:");
        System.out.println(a1 + " " + ((p - a1) % p));
    }

    public static void soloveyShtrassen() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введіть число n:");
        int n = scanner.nextInt();
        if (n < 2){
            System.out.println("не відповідає умовам");
            return;
        }
        if (n != 2 && n % 2 == 0) {
            System.out.println("не відповідає умовам");
            return;
        }
        int k = 10;
        Random rand = new Random();
        for (int i = 0; i < k; i++) {
            int r = Math.abs(rand.nextInt()); // вибираємо випадкове число r, 1 < r < n
            int a = r % (n - 1) + 1;
            int jacobian = (n + calculateJacobian(a, n)) % n;  // обчислюємо символ якобі для n та а
            int mod = modulo(a, (n - 1) / 2, n); // a^((n-1)/2)mod n
            if (jacobian == 0 || mod != jacobian) {
                System.out.println(n + " є складеним");
                return;
            }
        }
        System.out.println(n + " є простим з ймовiрнiстю ≥ " + (1 - Math.pow(2, -k)));
        return;
    }


    static int modulo(int base, int exponent, int mod) {
        int x = 1;
        int y = base;
        while (exponent > 0) {
            if (exponent % 2 == 1)
                x = (x * y) % mod;
            y = (y * y) % mod;
            exponent = exponent / 2;
        }
        return x % mod;
    }

    static int calculateJacobian(int a, int n)
    {
        if (n <= 0 || n % 2 == 0)
            return 0;
        int ans = 1;
        if (a < 0)
        {
            a = -a; // (a/n) = (-a/n)*(-1/n)
            if (n % 4 == 3)
                ans = -ans; // (-1/n) = -1 if n = 3 (mod 4)
        }
        if (a == 1)
            return ans; // (1/n) = 1
        while (a != 0)
        {
            if (a < 0)
            {
                a = -a; // (a/n) = (-a/n)*(-1/n)
                if (n % 4 == 3)
                    ans = -ans; // (-1/n) = -1 if n = 3 (mod 4)
            }
            while (a % 2 == 0)
            {
                a /= 2;
                if (n % 8 == 3 || n % 8 == 5)
                    ans = -ans;
            }
            int temp = a;
            a = n;
            n = temp;
            if (a % 4 == 3 && n % 4 == 3)
                ans = -ans;
            a %= n;
            if (a > n / 2)
                a = a - n;
        }
        if (n == 1)
            return ans;
        return 0;
    }
    public static int Legendre(long a, long b) {
        BigInteger A = BigInteger.valueOf(a);
        BigInteger B = BigInteger.valueOf(b);
        if (b < 3)
            return 2;
        if (!IsPrime(b))
            return 3;
        if (a % b == 0)
            return 0;

        // a^((b-1)/2) (mod b)
        BigInteger ab = A.modPow(B.subtract(BigInteger.ONE).divide(BigInteger.TWO), B);
        if (ab.compareTo(BigInteger.ONE) == 0)
            return 1;
        else
            return -1;
    }
    private static boolean IsPrime(long n) {
        for (int i = 2; i <= Math.sqrt(n); i++)
            if (n % i == 0)
                return false;
        return n > 1;
    }
}
