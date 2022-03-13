package battleship;

import java.util.Scanner;

public class Main {


    static char[][] p1= new char[11][11];
    static char[][] p2= new char[11][11];
    static char[][] array2= new char[11][11];
    static  char[][] empty1=new char[11][11];
    static  char[][] empty2=new char[11][11];

    static void printGame(int printNo, char [][]array){

        char ch1='A';
        char ch2='1';

        for(int i=0;i<11;i++){

            if(i==0) {
                System.out.print(" ");
            }else {
                System.out.print(ch1);
                ch1++;
            }
            for(int j=0;j<11;j++){
                if(i==0 && j!=10) {

                    System.out.print(" " + (j+1));
                }

                if(i!=0 && j!=0) {
                    if(printNo==1) {
                        array[i][j] = '~';
                        System.out.print(" " + array[i][j]);
                    } else
                    {
                        array2[i][j] = '~';
                        System.out.print(" " + array2[i][j]);
                    }
                }
            }
            System.out.println();
        }
    }

    static void updateGame(int arrayNo, char [][] array){
        char ch='A';

        for(int i=0;i<11;i++){
            if(i==0) {
                System.out.print(" ");
            }else {
                System.out.print(ch);
                ch++;
            }
            for(int j=0;j<11;j++){
                if(i==0 && j!=10) {

                    System.out.print(" " + (j+1));
                }
                if(i!=0 && j!=0) {
                    if(arrayNo==1)
                        System.out.print(" " + array[i][j]);
                    else if(arrayNo==2)
                        System.out.print(" " + array2[i][j]);

                }
            }
            System.out.println();
        }
    }
    static int convert(String s1){

        char ch='A';
        char ch2=s1.charAt(0);
        int temp=0;

        for(int i=1;i<=27;i++) {
            if (ch2==ch) {
                temp = i;
            }
            ch++;
        }
       // System.out.println(temp);
        return temp;

    }
    static boolean checkPossible(int x1, int y1, int x2, int y2, char [][]array){

        if(x1==x2){
            for(int i=y1;i<=y2;i++){
                if(x1!=10) {
                    if (array[x1 + 1][i] == 'O')
                        return false;
                }
                if(x1!=1) {
                    if (array[x1 - 1][i] == 'O')
                        return false;
                }
            }
            if(y1!=1) {
                if (array[x1][y1 - 1] == 'O')
                    return false;
            }
            if(y2!=10) {
                if (array[x1][y2 + 1] == 'O')
                    return false;
            }
        } else  if(y1==y2){
            for(int i=x1;i<=x2;i++){
                if(y1!=10) {
                    if (array[i][y1 + 1] == 'O')
                        return false;
                }
                if(y1!=1) {
                    if (array[i][y1 - 1] == 'O')
                        return false;
                }
            }
            if(x1!=1){
             if(array[x1-1][y1]=='O')
                return false;
            }
            if(x2!=10) {
                if (array[x1 + 1][y1] == 'O')
                    return false;
            }
        }
        return true;
    }
    static boolean checkStatus(int x1,int y1, char [][]array){

        if(x1!=10) {
            if (array[x1 + 1][y1] == 'O')
                return false;
        }
        if(x1!=1) {
            if (array[x1 - 1][y1] == 'O')
                return false;
        }
        if(y1!=1) {
            if (array[x1][y1 - 1] == 'O')
                return false;
        }
        if(y1!=10) {
            if (array[x1][y1 + 1] == 'O')
                return false;
        }
            return true;
    }

    static void gameMethod1(char [][]array){

        Scanner sc = new Scanner(System.in);
        printGame(1,array );

        int flag=1;
        int count=5;
        while(count>1) {

            if(count==5)
                System.out.println("Enter the coordinates of the Aircraft Carrier (" + count + " cells):");
            if(count==4)
                System.out.println("Enter the coordinates of the Battleship (" + count + " cells):");
            if(count==3 && flag==1)
                System.out.println("Enter the coordinates of the Submarine (" + count + " cells):");
            if(count==3 && flag==2)
                System.out.println("Enter the coordinates of the Cruiser (" + count + " cells):");
            if(count==2)
                System.out.println("Enter the coordinates of the Destroyer (" + count + " cells):");


            String str = sc.nextLine();
            String[] arr;
            arr = str.split(" ");

            String[] s1 = arr[0].split("", 2);
            String[] s2 = arr[1].split("", 2);
            int x1 = convert(s1[0]);
            int x2 = convert(s2[0]);


            int y1 = Integer.parseInt(s1[1]);
            int y2 = Integer.parseInt(s2[1]);
            if(!(checkPossible(x1,y1,x2,y2, array))) {
                System.out.println("Error! You placed it too close to another one. Try again:");
                continue;
            }

            if(x1!=x2 && y1!=y2){
                System.out.println("Error! Wrong ship location! Try again:");
            }
            if(y1>y2){
                int temp=y1;
                y1=y2;
                y2=temp;
            }
            if(x1>x2){
                int temp=x1;
                x1=x2;
                x2=temp;
            }

            if(Math.abs(x1-x2)==(count-1) || (Math.abs(y1-y2)==(count-1))) {

                if (x1 == x2) {
                    for (int i = y1; i <= y2; i++) {
                        array[x1][i] = 'O';
                    }
                    updateGame(1,array);
                    if(count==3 && flag==1)
                        flag++;
                    else
                        count--;
                } else if (y1 == y2) {
                    for (int i = x1; i <= x2; i++) {
                        array[i][y1] = 'O';
                    }
                    updateGame(1, array);
                    if(count==3 && flag==1)
                        flag++;
                    else
                        count--;
                }
            } else
                System.out.println("Error! Wrong length of the Submarine! Try again:");
        }
    }

    public static void promptEnterKey() {
        System.out.println("Press Enter and pass the move to another player");
        System.out.println("...");
        try {
            System.in.read();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        Scanner sc= new Scanner(System.in);
        System.out.println("Player 1, place your ships on the game field");
        gameMethod1(p1);
       promptEnterKey();

        System.out.println("Player 2, place your ships on the game field");
       gameMethod1(p2);
       promptEnterKey();



        int count=1;
        int shipCount1=0;
        int shipCount2=0;

        while(true) {

            if(count%2!=0) {
                printGame(1, empty1);
                System.out.println("---------------------");
                updateGame(1, p1);
                System.out.println("Player 1, it's your turn:");
            } else if (count%2==0){
                printGame(1, empty2);
                System.out.println("---------------------");
                updateGame(1, p2);
                System.out.println("Player 2, it's your turn:");
            }


            String shot = sc.next();
            String[] sh = shot.split("", 2);
            int xCoordinate = convert(sh[0]);
            int yCoordinate = Integer.parseInt(sh[1]);

            if (count % 2 != 0) {
                if (xCoordinate > 10 || yCoordinate > 10) {
                    System.out.println("Error! You entered the wrong coordinates! Try again:");
                    continue;
                } else {
                    if (p2[xCoordinate][yCoordinate] == 'X') {
                      // updateGame(2,empty2);
                        continue;
                    }
                    if (p2[xCoordinate][yCoordinate] == 'O') {
                        empty2[xCoordinate][yCoordinate] = 'X';
                        p2[xCoordinate][yCoordinate] = 'X';
                        count++;
                      //  updateGame(2);

                        if (checkStatus(xCoordinate, yCoordinate,p2)) {

                            if (shipCount1 == 4) {
                                System.out.println("You sank the last ship. You won. Congratulations!");
                                break;
                            } else {
                                System.out.println("You sank a ship!");
                                shipCount1++;
                            }
                        } else
                            System.out.println("You hit a ship!");

                    } else {
                        empty2[xCoordinate][yCoordinate] = 'M';
                        p2[xCoordinate][yCoordinate] = 'M';
                        count++;
                       // updateGame(2);
                        System.out.println("You missed.");
                    }
                }
            } else if (count % 2 == 0) {
                if (xCoordinate > 10 || yCoordinate > 10) {
                    System.out.println("Error! You entered the wrong coordinates! Try again:");
                    continue;
                } else {
                    if (p1[xCoordinate][yCoordinate] == 'X') {
                        // updateGame(2);
                        continue;
                    }
                    if (p1[xCoordinate][yCoordinate] == 'O') {
                        empty1[xCoordinate][yCoordinate] = 'X';
                        p1[xCoordinate][yCoordinate] = 'X';
                        count++;
                        //  updateGame(2);

                        if (checkStatus(xCoordinate, yCoordinate,p1)) {

                            if (shipCount2 == 4) {
                                System.out.println("You sank the last ship. You won. Congratulations!");
                                break;
                            } else {
                                System.out.println("You sank a ship!");
                                shipCount2++;
                            }
                        } else
                            System.out.println("You hit a ship!");

                    } else {
                        empty1[xCoordinate][yCoordinate] = 'M';
                        p1[xCoordinate][yCoordinate] = 'M';
                        count++;
                        // updateGame(2);
                        System.out.println("You missed.");
                    }
                }
            }

            if(count==32){
                System.out.println("You sank the last ship. You won. Congratulations!");
            }
            promptEnterKey();

        }
    }
}
