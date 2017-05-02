public class Lz78 {

    private int number;
    private StringBuffer s;
    private char c;

    public Lz78(int n, StringBuffer b){
	this.number = n;
	this.s = b;
    }

    public Lz78(int n, char c){
	this.number = n;
	this.c = c;
    }

    public int getNumber(){ return number; }
    public StringBuffer getSb(){ return s; }
    public char getChar(){ return c; }

}
