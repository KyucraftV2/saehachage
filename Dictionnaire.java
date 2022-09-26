import java.math.BigInteger;
import java.lang.Math;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Dictionnaire{
    private HTNaive t;

    //question 16
    public Dictionnaire(int m){
        this.t = new HTNaive(m);
    }

    //question 17
    public static BigInteger stringToBigInteger(String mot){
        BigInteger temp = BigInteger.valueOf(0);
        int long_mot = mot.length()-1;
        int c = 0;
        int x=0;
        for(int i=0;i<mot.length();i++){
            c = mot.charAt(i);
            x=c;
            x = x*(int)Math.pow(256,long_mot);
            long_mot--;
            temp = temp.add(BigInteger.valueOf(x));
        }
        return temp;
    }

    public static BigInteger stringToBigInteger2(String mot){
        BigInteger temp = BigInteger.valueOf(0);
        int long_mot = mot.length()-1;
        int c = 0;
        int x=0;
        BigInteger test;
        for(int i=0;i<mot.length();i++){
            c = mot.charAt(i);
            test = BigInteger.valueOf(c);
            if(i<mot.length()-1){
                temp = temp.multiply(BigInteger.valueOf(256));
            }

        }
        return temp;
    }

    //question 19
    public boolean ajout(String s){
        BigInteger motInt = Dictionnaire.stringToBigInteger(s);
        boolean res = this.t.ajout(motInt);
        return res;

    }

    //question 20
    public boolean contient(String s){
        return this.t.contient(Dictionnaire.stringToBigInteger(s));
    }

    //question 21
    public int getCardinal(){
        return this.t.getCardinal();
    }

    public int getMaxSize(){
        return this.t.getMaxSize();
    }

    public int getNbListes(){
        return this.t.getNbListes();
    }

    public String toString(){
        return this.t.toString();
    }

    public String toStringV2(){
        return this.t.toStringV2();
    }


    //question 23
    public static ListeBigI calculeListeInt(String fileName){
        ListeBigI res = new ListeBigI();
        File f = new File(fileName);
        BigInteger temp_mot = BigInteger.valueOf(0);
        Scanner sc;
        try {
            sc = new Scanner(f); 
            //ici on construit le scanner avec comme entrée f //cette construction peut échouer (si par exemple
            //fichier.txt n’existe pas)
        }
        catch(FileNotFoundException e){
        //si la construction échoue, on passe ici 
            System.out.println(("problème d’accès au fichier " + e.getMessage()));
            return res; 
        }
        sc.useDelimiter(" |\\n|,|;|:|\\.|!|\\?|-");
        while (sc.hasNext()) { 
            //sc.hasNext() renvoie vrai ssi
            //il reste encore un morceau à découvrir dans f 
            String mot = sc.next(); 
            temp_mot = Dictionnaire.stringToBigInteger(mot);
            res.ajoutFin(temp_mot);
        }
        sc.close();
        return res;
    }

    public Dictionnaire(String fileName,int m){
        this.t = new HTNaive(m);
        ListeBigI temp_liste = Dictionnaire.calculeListeInt(fileName);
        this.t.ajoutListe(temp_liste);
    }

    public Dictionnaire(String fileName,double f){
        ListeBigI temp_liste = Dictionnaire.calculeListeInt(fileName);
        HTNaive temp = new HTNaive(temp_liste.longueur()+1);
        temp.ajoutListe(temp_liste);
        double m2;
        m2 = temp.getCardinal()*f;
        int m = (int)m2;
        this.t = new HTNaive(m);
        this.t.ajoutListe(temp_liste);
    }
}