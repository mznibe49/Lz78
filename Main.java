import java.io.*;
import java.util.*;


public class Main  {

    public static void main(String [] args) throws IOException {
	Chrono.Go_Chrono();
	File f = new File("test.txt");
	Reader reader = new Reader(f);
	String texte = reader.texte();

	ArrayList<Lz78> list = new ArrayList<Lz78>();

	ArrayList<String> dicho = new ArrayList<String>();
	//System.out.println(11);

	reader.compresser(texte,list,dicho);
	//System.out.println(11);
	/*	for(int i = 0; i<list.size(); i++){
	    System.out.println(list.get(i).getNumber()+" "+list.get(i).getChar());
	}

	System.out.println();
	
	for(int k = 0; k<dicho.size(); k++){
	    System.out.println(dicho.get(k));
	}
*/
	reader.writeInFileBit(list);

	BitInputStream input= new BitInputStream(new FileInputStream("code.txt"));
	input.decompression();
	Chrono.Stop_Chrono();
    }

}
