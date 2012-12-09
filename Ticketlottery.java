/**
 * @author David Paredes (davidparedes.es)
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Ticketlottery {
    
    private static String str;
    private static int m, n, t, p;
    private static int needToWin;
    private static double probLose;

    private static double binomialCoefficient(int n, int k) {
        if (k < 0 || k > n) return 0;
        if (k > n-k) k = n-k;
        double aux = 1;
        for (int i = 0; i < k; i++) {
            aux *= (n-i);
            aux = aux/(i+1);
        }
        return aux;
    }

    public static void answer(double probability) {
        System.out.println(probability);
        System.exit(0);
    }
    
    public static void main(String[] args) throws IOException {
        
        InputStreamReader inp = new InputStreamReader(System.in); 
        BufferedReader br = new BufferedReader(inp);
        
        // Wait for input
        str = br.readLine();
        
        // Input splits into m, n, t and p.
        StringTokenizer tokens = new StringTokenizer(str);
        
        try {
            m = Integer.parseInt(tokens.nextToken()); // Total number of people who entered the lottery
            n = Integer.parseInt(tokens.nextToken()); // Total number of winners drawn
            t = Integer.parseInt(tokens.nextToken()); // Number of tickets each winner is allowed to buy
            p = Integer.parseInt(tokens.nextToken()); // Number of people in your group

        } catch (Exception e) {
            System.out.println("Incorrect format.");
            System.exit(0);
        }

        // Min number of people of your group that need to win
        needToWin = (int)Math.ceil((float)p/t);

        if (needToWin > n) {
            // Impossible to get enough tickets
            answer(0);
        }

        if (n==m && m>=needToWin) {
            // You'll get tickets for sure
            answer(1);
        }

        for (int 
            i=0; i<needToWin; i++) {
            // Number of cases where your group isn't able to win enough tickets
            probLose += binomialCoefficient(p,i)*binomialCoefficient(m-p,n-i);
        }

        // Good cases = 1 - (bad cases / total cases)
        answer(1-(probLose/binomialCoefficient(m,n)));
    }
}