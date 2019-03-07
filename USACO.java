import java.util.*;
import java.io.*;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
public class USACO {
	public static int bronze(String filename)throws FileNotFoundException {
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
			         if(line.substring(i,i+1).equals("")) {
				     List.add(Integer.parseInt(line.substring(lastNumber,i)));
				     lastNumber=i;
			          }
			    }
			    R=List.get(0);
			    C=List.get(1);
			    E=List.get(2);
			    N=List.get(3);
			    elevation=new int[R][C];
			    instruction=new int[N];   //initializes the array according to the read file
			    List.clear();
		    }// ends of first line procedure
			else if(lineNumber>=2 && lineNumber<=R+1) {
				int lastNumber=0;
				for(int i=0;i<line.length();i++) {
					if(line.substring(i,i+1).equals("")) {
						List.add(Integer.parseInt(line.substring(lastNumber,i)));
						lastNumber=i;
					}
				}
				for(int i=0;i<C;i++) {
					elevation[lineNumber-2][i]=List.get(i);
				}
				List.clear();
			}
			else if(lineNumber>=R+2 && lineNumber<=N+1) {
				int lastNumber=0;
				for(int i=0;i<line.length();i++) {
					if(line.substring(i,i+1).equals("")) {
						List.add(Integer.parseInt(line.substring(lastNumber,i)));
						lastNumber=i;
					}
				}
				stomp(List.get(0),List.get(1),List.get(2),elevation);
			}
		}// performs the stomps
		for(int i=0;i<elevation.length;i++) {
			for(int j=0;j<elevation[0].length;j++) {
				if(elevation[i][j]>=E) {
					elevation[i][j]=0;
				}
				else {
					elevation[i][j]=E=elevation[i][j];
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

	}
	public static int[]max(int[][]array,int r,int c){
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


}