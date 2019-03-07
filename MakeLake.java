import java.util.*;
import java.io.*;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
public class MakeLake{
private int[][]elevation;
private int R,C,E,N;
public MakeLake() throws FileNotFoundException{
  File text=new File("data.txt");
  Scanner info=new Scanner(text);
  String line;
  int lineNumber=1;
  while(info.hasNextLine()){
    line=info.nextLine();
    if(lineNumber==1){
      R=Integer.parseInt(line.substring(0,1));
      C=Integer.parseInt(line.substring(2,3));
      E=Integer.parseInt(line.substring(4,5));
      N=Integer.parseInt(line.substring(6,7));
    }
    else if(lineNumber>=2 && lineNumber<=R+1){
      
    }
  }
}
}
