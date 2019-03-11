import java.util.*;
import java.io.*;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
public class USACO {
	public static int bronze(String filename) {
    try{
		File text=new File(filename);
		Scanner info=new Scanner(text);
		int lineNumber=1;
		String line;
		int[][]elevation=new int[2][2];
		int[]instruction=new int[1];
		ArrayList<Integer>List=new ArrayList<Integer>();
		int R,C,E,N;
		R=C=E=N=0;
		while(info.hasNextLine()) {
			line=info.nextLine();
			if(lineNumber==1) {
				int lastNumber=0;
			    for(int i=0;i<line.length();i++) {
			       if(line.substring(i,i+1).equals(" ")) {
				     List.add(Integer.parseInt(line.substring(lastNumber,i)));
						// System.out.println(List); // checks to see the list
				     lastNumber=i+1;
			          }
			    }
					List.add(Integer.parseInt(line.substring(lastNumber,line.length())));
			    R=List.get(0);
				//	System.out.println(R);
			    C=List.get(1);
					//System.out.println(C);
			    E=List.get(2);
					//S/ystem.out.println(E);
			    N=List.get(3);
					//System.out.println(N);
			    elevation=new int[R][C];
			    instruction=new int[N];   //initializes the array according to the read fil
			    List.clear();
		    }// ends of first line procedure
			else if(lineNumber>=2 && lineNumber<=R+1) {
				int lastNumber=0;
				for(int i=0;i<line.length();i++) {
					if(line.substring(i,i+1).equals(" ")) {
						List.add(Integer.parseInt(line.substring(lastNumber,i)));
						lastNumber=i+1;
					}
				}
				List.add(Integer.parseInt(line.substring(lastNumber,line.length())));
				for(int i=0;i<C;i++) {  // fills in the elevation array with the right inputs
					elevation[lineNumber-2][i]=List.get(i);
				}
			//	System.out.println(print(elevation));
				List.clear();
			}
			else if(lineNumber>=R+2 && lineNumber<=R+N+1) {
      //  System.out.println(line);
      //  System.out.println(line.length());
				int lastNumber=0;
				for(int i=0;i<line.length();i++) {
					if(line.substring(i,i+1).equals(" ")) {
						List.add(Integer.parseInt(line.substring(lastNumber,i)));
						lastNumber=i+1;
          ///  System.out.println("i :"+i);
            //System.out.println(List);
          //  System.out.println("lastNumber:"+ lastNumber);
					}
				}
				List.add(Integer.parseInt(line.substring(lastNumber,line.length())));
				//System.out.println(Arrays.deepToString(elevation));
				stomp(List.get(0)-1,List.get(1)-1,List.get(2),elevation);
        //System.out.println(print(elevation));
        List.clear();
			   // performs stomps
			}

			lineNumber++;
		}// performs the stomps
		for(int i=0;i<elevation.length;i++) {
			for(int j=0;j<elevation[0].length;j++) {
				if(elevation[i][j]>=E) {
					elevation[i][j]=0;
				}
				else {
					elevation[i][j]=E-elevation[i][j];
				}
			}
		}
		int sum=0;
		for(int i=0;i<elevation.length;i++) {
			for(int j=0;j<elevation[0].length;j++) {
				sum+=elevation[i][j];
			}
		}
		return 72*72*sum;
  }
  catch(FileNotFoundException e){
    System.out.println("wrong file name");
    return -1;
  }
	}
	public static  void stomp(int row,int col,int d,int[][]array) {
		int[] maximum=max(array,row,col);
		array[maximum[0]][maximum[1]]=array[maximum[0]][maximum[1]]-d;
		int maxValue=array[maximum[0]][maximum[1]];
		for(int i=row;i<row+3 && row<array.length;i++) {
			for(int j=col;j<array[0].length && j<col+3;j++) {
				if(array[i][j]>maxValue) {
					array[i][j]=maxValue;
				}
			}
		}
    //System.out.println(print(array));

	}
	public static int[]max(int[][]array,int r,int c){// searches for the greatest elevation in a 3 by 3 grid
		int max=0;
		int row=0;
		int col=0;
		for(int i=r;i<r+3 && i<array.length;i++) {
			for(int j=c;j<c+3 && j<array[0].length;j++) {
				if(array[i][j]>max) {
					max=array[i][j];
					row=i;
					col=j;
				}
			}
		}
		int[]answer=new int[2];
		answer[0]=row;
		answer[1]=col;
		return answer;
	}
	public static String print(int[][]ary){
		String ans="";
		for(int i=0;i<ary.length;i++){
			for(int j=0;j<ary[0].length;j++){
				ans+=ary[i][j]+",";
			}
			ans+="\n";
		}
		return ans;
	}
  // silver part
  public static int silver(String filename) {
try {

    File document = new File(filename);
    Scanner reciter = new Scanner(document);

    int length = reciter.nextInt();
    int width = reciter.nextInt();
    int time = reciter.nextInt();

    char[][] land = new char[length][width];
    String line = reciter.nextLine();

    for (int row = 0; row < land.length; row++) {
        line = reciter.nextLine();

         for (int col = 0; col < land[row].length; col++) {
           land[row][col] = line.charAt(col);
         }
    }

    int[] coordinates = new int[4];

    for (int cell = 0; cell < coordinates.length; cell++) {
       coordinates[cell] = reciter.nextInt() - 1;
    }
    int[][] past = new int[length][width];
    int[][] current = new int[length][width];

    for (int row = 0; row < land.length; row++) {
          for (int col = 0; col < land[row].length; col++) {
              if (land[row][col] == '*') {
               past[row][col] = -1;
              current[row][col] = -1;
              }
          }
    }

    current[coordinates[0]][coordinates[1]] = 1;

  while (time > 0) {
     for (int row = 0; row < land.length; row++) {
       for (int col = 0; col < land[row].length; col++) {
         past[row][col] = current[row][col];
      }
     }

  for (int row = 0; row < current.length; row++) {
      for (int col = 0; col < current[row].length; col++) {
        if (current[row][col] != -1) {
            current[row][col] = 0;

            if (row - 1 >= 0 && current[row - 1][col] >= 0) {
               current[row][col] += past[row - 1][col];
             }
          if (col - 1 >= 0 && current[row][col - 1] >= 0) {
              current[row][col] += past[row][col - 1];
          }

          if (row + 1 < past.length && current[row + 1][col] >= 0) {
            current[row][col] += past[row + 1][col];
          }

          if (col + 1 < past[row].length && current[row][col + 1] >= 0) {
             current[row][col] += past[row][col + 1];
          }
      }
    }
  }
  time--;
}

    return current[coordinates[2]][coordinates[3]];
}

catch (FileNotFoundException e) {
   System.out.println("file doesn't exist");
    return -1;

  }
 }
}
