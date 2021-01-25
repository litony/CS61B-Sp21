/** Class that prints the Collatz sequence starting from a given number.
 *  @author YOUR NAME HERE
 */
public class Collatz {
    public static int nextNumber(int n) {
        if (n % 2 == 0 ) {
            n = n / 2;
        } else {
            n = 3 * n + 1;
        }
        return n;
    }
    public static void main(String[] args) {
        int n = 5;
        while (n > 0) {
            System.out.print(n + " ");
            if (n == 1) {
                break;
            }
            n = nextNumber(n);
        }

    }
}

