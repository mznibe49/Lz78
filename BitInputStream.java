import java.io.*;
import java.util.*;

public class BitInputStream extends FilterInputStream {
    int bits;
    int mask;

    public BitInputStream(InputStream in) {
	super(in);
    }

    /**
     * Reads the next bit from this input stream. The value is returned as an
     * int in the range 0 to 1. If no bit is available because the end of the
     * stream has been reached, the value -1 is returned. This method blocks
     * until input data is available, the end of the stream is detected, or an
     * exception is thrown.
     */
    public int readBit() throws IOException {
	int bits;
	int mask = this.mask;

	if (mask == 0) {
	    bits = read();
	    if (bits == -1)
		return -1;

	    this.bits = bits;
	    mask = 0x80;
	} else {
	    bits = this.bits;
	}

	if ((bits & mask) == 0) {
	    this.mask = mask >> 1;
	    return 0;
	} else {
	    this.mask = mask >> 1;
	    return 1;
	}
    }
	
    public int[] readBit16(int nombre ) throws IOException{
	int[] bits = new int[nombre];
	for(int i=0; i<bits.length;i++)
	    bits[i] = -1;
		
	int j = 0;
	int tmp=-1;
	for(int i = 0;i < bits.length; i++){
	    tmp = readBit();
	    if(tmp < 0){
		break;
	    }
	    else{
		bits[j] = tmp;
		j++;
	    }
	}
	return bits;
    }
	
    public LinkedList<Integer> bits(int [] bits){
	LinkedList<Integer> tmp = new LinkedList<Integer>();
	for(int i=0; i< bits.length;i++){
	    if(bits[i] == 1){
		tmp.add(bits[i]);
		for(i=i+1; i<bits.length; i++){
		    tmp.add(bits[i]);
		}
	    }
	}
	return tmp;
    }
	
	
    public int convertInDecimal(LinkedList<Integer> binaires){
	int n = binaires.size();
	int p = n -1;
	int res = 0;
	for(int val : binaires){
	    res =  (int)(res+ val*Math.pow(2, p));
	    p--;
	}
	return res;
    }
	
    public static char intToChar(int n){
	return (char) n;
    }
	
    public void affiche(ArrayList<Lz78> list){
	System.out.println("\n########");
	for(int i=0; i<list.size(); i++){
	    System.out.println(list.get(i).getNumber()+ " " + list.get(i).getSb());
	}
	System.out.println("########\n");
    }

    public StringBuffer Sback(int indice, ArrayList<Lz78> dicho){
	for(int i = 0; i<dicho.size(); i++){
	    //System.out.println("GETNUM : "+dicho.get(i).getNumber()+" Indice : "+indice);
	    if(dicho.get(i).getNumber() == indice){
		return dicho.get(i).getSb();
	    }
	}
	return new StringBuffer("");
    }
    
    public void decompression() throws IOException {
	StringBuffer t = new StringBuffer("");
	int reference = bits(readBit16(32)).size();
	//System.out.println("\nreference : "+reference+"\n");
	int j; // pointeur
	char lettre; // char
	int cpt = 0;
	ArrayList<Lz78> list = new ArrayList<Lz78>();
	ArrayList<Lz78> dicho = new ArrayList<Lz78>();
	String s = "";
	boolean b = false;
	while(this.available() != 0){
	    j = convertInDecimal(bits(readBit16(reference)));
	    lettre = intToChar(convertInDecimal(bits(readBit16(8))));
	    //System.out.println("("+j+","+lettre+")");
	    list.add(new Lz78(j,lettre));
	    if(lettre == '\u0000')
		b = true;
	    //res.append()
	    //affiche(list);
	    cpt++;
	    if(j == 0){
	    //if(!exist(dicho,)){
	    //lis
		t.append(lettre);
		s = ""+lettre;
		StringBuffer tmp  = new StringBuffer(s);
		dicho.add(new Lz78(cpt,tmp));
		//System.out.println(cpt+" "+tmp);
		
	    } else {
		//String str = s.substring(dicho.get(j-1).getSb().length()) + lettre;
		//res = new StringBuffer(str);
		StringBuffer res = dicho.get(j-1).getSb();
		String str = res.substring(0,res.length());
		str += lettre;
		res = new StringBuffer(str);
		dicho.add(new Lz78(cpt,res));
		//dicho.get(cpt-1).getSb().append(lettre);
		//System.out.println("CPTS IS : "+(cpt-1));
		//affiche(dicho);
		//System.out.println("RES : "+str);
		//StringBuffer res = Sback(j,dicho);		
		//res.append(lettre);
		//	System.out.println("Lettre : "+lettre);
		//System.out.println("\nRes ==> : "+res+" Indice ==> "+j);			       
		//dicho.add(new Lz78(cpt,res));
		/*System.out.println(cpt+" "+res);
		  dicho.add(new Lz78(cpt,res));*/
		t.append(res);	       
	    }
	}
	PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("decode.txt")));
	String end = "";
	if(b == false)
	    end = t.substring(0,t.length());
	else
	    end = t.substring(0,t.length()-1);
	//System.out.println(t);
	out.write(end);
	out.close();
    }
    
}
