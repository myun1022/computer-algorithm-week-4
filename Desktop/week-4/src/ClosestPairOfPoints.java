import java.util.*;

public class ClosestPairOfPoints {
    int x, y;

    public ClosestPairOfPoints(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public static void main(String[] args) {
        Random rand = new Random();
        ClosestPairOfPoints[] P = new ClosestPairOfPoints[rand.nextInt(100)];
        // System.out.println("점의 개수 : " + P.length);
        for (int i = 0; i < P.length; i++) {
            P[i] = new ClosestPairOfPoints(rand.nextInt(100), rand.nextInt(100));
            //System.out.println("x : " + P[i].x + ", y : " + P[i].y);
        }
        System.out.println();

        Arrays.sort(P, (P1, P2) -> P1.x - P2.x);

        /* for (int i = 0; i < P.length; i++) {
            System.out.println("x : " + P[i].x + ", y : " + P[i].y);
        } */

    }
}
