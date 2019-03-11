import java.util.*;
import java.io.*;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
public class USACO2 {
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
	public static String print(String[][]ary){
		String ans="";
		for(int i=0;i<ary.length;i++){
			for(int j=0;j<ary[0].length;j++){
				ans+=ary[i][j]+",";
			}
			ans+="\n";
		}
		return ans;
	}
public static int silver(String fileName)throws FileNotFoundException{
	String[][]field;
	ArrayList<Integer> List2=new ArrayList<Integer>();
	ArrayList<String>List=new ArrayList<String>();
	int N=0;
	int M=0;
	int T=0;
	int startR=0;
	int startC=0;
	int endR=0;
	int endC=0;
	field=new String[N][M];
	File text=new File(fileName);
	Scanner info=new Scanner(text);
	int lineNumber=1;
	String line="";
	while(info.hasNextLine()){
		line=info.nextLine();
		if(lineNumber==1) {
			int lastNumber=0;
				for(int i=0;i<line.length();i++) {
					 if(line.substring(i,i+1).equals(" ")) {
					 List2.add(Integer.parseInt(line.substring(lastNumber,i)));
					// System.out.println(List); // checks to see the list
					 lastNumber=i+1;
							}
				}
				System.out.println(line);
				List2.add(Integer.parseInt(line.substring(lastNumber,line.length())));
				N=List2.get(0);
				M=List2.get(1);
				T=List2.get(2);
				field=new String[N][M];
				List2.clear();
	  }
		else if(lineNumber>=2 && lineNumber<=N+1) {
			int lastNumber=0;

			for(int i=0;i<M;i++) {  // fills in the elevation array with the right inputs
				field[lineNumber-2][i]=line.substring(i,i+1);
			}
		//	System.out.println(print(elevation));

		}
		else if(lineNumber==N+2 ) {
		//  System.out.println(line);
		//  System.out.println(line.length());
			int lastNumber=0;
			for(int i=0;i<line.length();i++) {
				if(line.substring(i,i+1).equals(" ")) {
					List2.add(Integer.parseInt(line.substring(lastNumber,i)));
					lastNumber=i+1;
				}
			}
			List2.add(Integer.parseInt(line.substring(lastNumber,line.length())));
			//System.out.println(Arrays.deepToString(elevation));
			//System.out.println(print(elevation));
		 startR=List2.get(0);
		 startC=List2.get(1);
		 endR=List2.get(2);
		 endC=List2.get(3);
			 // performs stomps
		}
	lineNumber++;
}

return 1;
}
public static void algorithmSilver(int StartR,int StartC,int endR,int endC){

}
public static void reachPath(int StartR,int StartC,int endR,int endC,int Time){

}
public static void main(String[]args)throws FileNotFoundException{
	System.out.println(silver("ctravel.txt"));
}
}
