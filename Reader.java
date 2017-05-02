import java.io.*;
import java.util.*;

public class Reader {

    private File f;

    public Reader(File f){
	this.f = f;
    }

    public String texte() throws IOException {
	Scanner in = new Scanner(this.f);
	StringBuffer s = new StringBuffer("");
	while(in.hasNextLine()){
	    s.append(in.nextLine());
	    s.append("\n");
	}
	if(s.equals("")) return "";
	String newS = s.substring(0,s.length()-1);
	//System.out.println(newS+'\n');
	return newS;
    }

    /*    public boolean kifkif(StringBuffer s1, StringBuffer s2){
	if(s1.length() == s2.length()){
	    for(int  i = 0; i<s1.length(); i++){
		if( s1.charAt(i) != s2.charAt(i)){
		    return false;
		}
	    }
	    return true;
	}  
	return false;	
    }

    public boolean exist(ArrayList<Lz78> dicho, StringBuffer s){
	String s1 = s.substring(0,s.length());
	String s2 = "";
	StringBuffer tmp = new StringBuffer("");
	for(int i = 0; i<dicho.size(); i++){
	    tmp =  dicho.get(i).getSb();
	    s2 = tmp.substring(0,tmp.length());
	    if(s1.equals(s2)) return true;
	    /*if(kifkif(dicho.get(i).getSb(),s)){
		//System.out.println("trouvé");
		return true;
	    }
	}
	//System.out.println("pas trvé");
	return false;
    }

    public int position(ArrayList<Lz78> l, String s){
	StringBuffer tmp = new StringBuffer("");
	String str = "";
	//StringBuffer tmp = new StringBuffer(s);
	for( int i = 0; i<l.size(); i++){
	    tmp = l.get(i).getSb();
	    str = tmp.substring(0,tmp.length());
	    if(s.equals(str)) return i+1;
		
	    /* if(kifkif(l.get(i).getSb(),tmp)){
		return i+1;
		}
	}
	return -1;
    }*/

    public void affiche(ArrayList<Lz78> list){
	System.out.println("\n########");
	for(int i=0; i<list.size(); i++){
	    System.out.println(list.get(i).getNumber()+ " " + list.get(i).getSb());
	}
	System.out.println("########\n");
    }
    
    public void compresser(String phrase, ArrayList<Lz78> list, ArrayList<String> dicho){
	int cpt = 0;
	int number = 0;
	StringBuffer s = new StringBuffer("");
	System.out.println(11);
	for(int i = 0; i<phrase.length(); i++){
	    s.append(phrase.charAt(i));
	    /*si ça n existe pas*/
	    String str = s.substring(0,s.length());
	    number = dicho.indexOf(str)+1;
	    if(number == 0){
	        cpt++;
		dicho.add(str);
		if(s.length() == 1){
		    list.add(new Lz78(0,s.charAt(0)));
		} else {
		    String tmp = s.substring(0,s.length()-1);
		    number = dicho.indexOf(tmp)+1;
		    list.add(new Lz78(number,phrase.charAt(i)));
		}
		s = new StringBuffer("");
	    } else if (number != 0 && i == phrase.length()-1){
	        cpt++;		
		list.add(new Lz78(number,'\u0000'));
		dicho.add(str);
		System.out.println(20);
	    }	   
	}
	System.out.println(11);
	//affiche(dicho);
    }

        // convertion d'un entier entre 0 et 9 a un char '0','1',..,'9'
    public char intToChar(int a){
	String s = ""+a;
	return s.charAt(0);
    }

    // convertion d'un int a un String qui contient sa representation binaire
    // ex : un 7 devient "111"
    public StringBuffer intToByte(int a){
	if(a == 0)	    
	    return new StringBuffer("0");
        ArrayList<Integer> list = new ArrayList<Integer>();
	while( a != 0 ){
	    int x = a%2;
	    list.add(x);
	    a /= 2;
	}	
	StringBuffer b = new StringBuffer("");
	for(int i = list.size()-1; i>=0; i--){
	    char tmp = intToChar(list.get(i));
	    b.append(tmp);		     
	}	
	return b;
    }


        //ecrire  4oc dans le fichiers pour representer la Taille max que Pointer/Length du triplet
    public void writeMaxInFile(BitOutputStream x, StringBuffer res) throws IOException {
	int z = 0;
	while (z < 32-res.length()) {
	    x.writeBit(0);
	    z++;
	}
	for(int i = 0; i<res.length(); i++){
	    if(res.charAt(i) == '1'){
		x.writeBit(1);
	    }else{
		x.writeBit(0);
	    }
	}
    }

    
    // renvoie le max pointer de la list 
    // pour representer une taille fixe
    public int max_list(ArrayList<Lz78> list){
	int max = 0;
	for(int i = 0; i<list.size(); i++){
	    if(max <= list.get(i).getNumber())
		max = list.get(i).getNumber();
	}
	return max;
    }

      // on ecrit le pointer
    public void writeNumberInFile(BitOutputStream x, StringBuffer res, StringBuffer pt) throws IOException{
	//on ecrit le pointer tel qu'il a une taille fixe
	int w = res.length()-pt.length();
	while(w != 0){
	    x.writeBit(0);
	    w--;
	}
	//on ecrit ke reste du pointeur
	for(int i = 0; i<pt.length(); i++){
	    if(pt.charAt(i) == '1'){
		x.writeBit(1);
	    }else{
		x.writeBit(0);
	    }
	}
    }

    // on ecrite le char sur 1oc
    public void writeCharInFile(BitOutputStream x, char c) throws IOException {
	int a = (int)(c);
	//System.out.println("A:"+a);
	int z = 0;
	StringBuffer res = intToByte(a);	
	//System.out.println("RES :"+res);
	while(z<8-res.length()){
	    x.writeBit(0);
	    z++;
	}
	for(int i = 0; i<res.length(); i++){
	    if(res.charAt(i) == '1'){
		x.writeBit(1);
	    }else{
		x.writeBit(0);
	    }
	}	
    }

    public void writeInFileBit(ArrayList<Lz78> list) throws IOException {
	//System.out.println(11);
	BitOutputStream x = new BitOutputStream(new FileOutputStream("code.txt"));
	int max = max_list(list);
	//System.out.println("\nMAX => "+max);
	StringBuffer res = new StringBuffer("");

	if( max == 0)
	    res = new StringBuffer("1");
	else
	    res = intToByte(max);

	//System.out.println(res);

	//on occupe 4oc pour stocker le max
	//System.out.println(11);
	writeMaxInFile(x,res);
	//System.out.println(11);
	for(int i = 0; i<list.size(); i++){

	    // ici number represente le pointer dans la liste
	    StringBuffer number = intToByte(list.get(i).getNumber());

            //on ecrit le pointer dans le fichier sur la taille fix  
	    writeNumberInFile(x,res,number);

	    //on ecrite le char sur 1oc	    
	    writeCharInFile(x,list.get(i).getChar());
	}
	x.close();	
    }


    
}
