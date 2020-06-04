import java.util.*;
import java.lang.*;

public class Main {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int sel='0';
        System.out.println(" 1-part1 : LinkedIn \n 2-part2 : Maze \n 3-part3 : Shortest path \n 4-Exit ");
        boolean flag=true;
        while(flag) {
            sel = s.next().charAt(0);
            s.nextLine();
            AbstractPart obj;
            switch (sel) {
                case '1':
                    obj = new part1();
                    obj.start();
                    break;
                case '2':
                    obj = new part2();
                    obj.start();
                    break;
                case '3':
                    obj = new part3();
                    obj.start();
                    break;
                case '4':
                    flag=false;
                    break;
                default:
                    System.out.println("INVALID SELECTION");
                    break;
            }
        }
    }
}
