package ytcf1;

import java.util.Arrays;
import java.util.Random;

public class YTCF1 {

    public static void main(String[] args) {
        int N=(int) Math.pow(10, 5);
        int[] points = new Random().ints(N, 0, (int) Math.pow(10, 9)).distinct().toArray();
        int R = new Random().nextInt((int) Math.pow(10, 9));
        groupPoints.groupPointsTest(R, points);
        
    }

}

class groupPoints {
    private static final long MEGABYTE = 1024L * 1024L;
    public static void groupPointsTest(int R, int[] points){
        int groupsNumber = getGroupsNumber(R, points);
        Runtime runtime =Runtime.getRuntime();
        runtime.gc();
        long memory = runtime.totalMemory() - runtime.freeMemory();
        System.out.println("Groups = "+groupsNumber);
        System.out.println("Used memory is bytes: " + memory);
        System.out.println("Used memory is megabytes: " + (memory/MEGABYTE));
    }
    public static int getGroupsNumber(int R, int[] points) {
        Arrays.sort(points);
        /* System.out.print("Points = ");
        for (int point : points) {
            System.out.print(point + " ");
        }
        System.out.println(""); */
        int numberOfGroups = 0;
        int i = 0;
        int j = 1;
        boolean centerFound = false;
        while (i < points.length) {
            String leftOrCenter = centerFound?" of center point ":" of leftmost point ";
            String pointOnEdge="Rightmost point within R=" + R +leftOrCenter + points[i];
            String nextLeftPoint = " Move to next leftmost point.";
            while (j < points.length && points[i] + R >= points[j]) {                
                j++;
            }
            
            if (j == i + 1) {
                System.out.println("Within=" + R + leftOrCenter + points[i] + " there is no other points."+nextLeftPoint);
                numberOfGroups++;
                i = j;
                j = i + 1;
                centerFound = false;

            } else {
                if (!centerFound) {
                    System.out.println(pointOnEdge + ": point " + points[j-1]+". Consider it being center now.");   
                    i = j - 1;
                    j = i + 1;
                    centerFound = true;
                } else {
                    System.out.println(pointOnEdge + ": point " + points[j-1]+"."+nextLeftPoint);
                    numberOfGroups++;
                    i = j;
                    j = i + 1;
                    centerFound = false;
                }
            }
        }
        return numberOfGroups;
    }
}

