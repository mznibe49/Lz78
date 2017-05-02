
import java.util.*;

public class Test {

    public static void main(String [] args){

	ArrayList<String> l1 = new ArrayList<String>();
	l1.add("sasa");
	l1.add("sisi");
	l1.add("soso");
	System.out.println(l1.indexOf("sisi"));
	//StringBuffer s1 = new StringBuffer("blabla");
	//System.out.println(s1.toString().getClass().getName());
	/*StringBuffer x1 = new StringBuffer("bla");
	StringBuffer x2 = new StringBuffer("bla");
	String s1 = "bli";
	String s2 = "bli";
	
	Lz78 n1 = new Lz78(11,'c');
	Lz78 n2 = new Lz78(11,'c');
	System.out.println(x1.getClass().getName());
	System.out.println(x2);

	if ( s1.equals(s2) ) System.out.println(true);
	else System.out.println(false);*/
	//System.identityHashCode(x1);
	//x1.hashCode();
	
	/*	ArrayList<Lz78> l1 = new ArrayList<Lz78>();
	ArrayList<Lz78> l2 = new ArrayList<Lz78>();

	StringBuffer t = new StringBuffer("");
	int cpt = 0;

	l1.add(new Lz78(0,'a'));
	l1.add(new Lz78(0,'b'));
	l1.add(new Lz78(0,'r'));
	l1.add(new Lz78(1,'c'));
	l1.add(new Lz78(1,'d'));
	l1.add(new Lz78(1,'b'));
	l1.add(new Lz78(3,'a'));
	l1.add(new Lz78(0,'d'));

	for(int i = 0; i<l1.size(); i++){
	    cpt++;
	    if(l1.get(i).getNumber() == 0){
		t.append(l1.get(i).getChar());
		String s = ""+l1.get(i).getChar();
		l2.add(new Lz78(cpt,new StringBuffer(s)));
	    } else {
		StringBuffer res = l2.get(l1.get(i).getNumber()-1).getSb().append(l1.get(i).getChar());
		//	res.append(l1.get(i).getChar());
		l2.add(new Lz78(cpt,res));
		t.append(res);
	    }
	}
	System.out.println(t+"\n");

	for(int i = 0; i<l2.size(); i++){
	    System.out.println(l2.get(i).getNumber()+ " " + l2.get(i).getSb());
	}
	
	System.out.println();*/
	//StringBuffer s = new StringBuffer("blabla");
	//System.out.println(s.deleteCharAt(s.length()-1));
	/*	
	ArrayList<Integer> l = new ArrayList<Integer>();
	l.add(29);
	l.add(21);
	l.add(199);
	l.add(78);
	System.out.println(l.indexOf(21));
	System.out.println(l.size());*/
    }

}
