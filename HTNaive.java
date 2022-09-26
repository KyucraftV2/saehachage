import java.math.BigInteger;

public class HTNaive{
    private ListeBigI[] t; // Liste de bin integer 
    private long totalTimeh;
    private long totalTimeContient;

    //question 4 contructeur 
    public HTNaive(int m){
        this.t = new ListeBigI[m]; 
        for(int i=0;i<this.t.length;i++){
            this.t[i] = new ListeBigI();
        }
        this.totalTimeh = 0;
        this.totalTimeContient = 0;
    }

    //Question 5
    public ListeBigI getListe(int i){ //renvoie la liste de couleur i
        return t[i];
        }
    

    //question 6
    public int h(BigInteger u){ //fonction de hachage 
        long startTime = System.currentTimeMillis();
        int res = u.intValue()%this.t.length;
        long endTime = System.currentTimeMillis();
        this.totalTimeh += (endTime-startTime);
        return res;
    }


    //question 7
    public boolean contient(BigInteger u){ //renvoie true si dans this il y a u
        int couleur = h(u);
        boolean res = false;
        long startTime = System.currentTimeMillis();
        if(this.t[couleur].contient(u)==true){
            res = true;
        }
        long endTime = System.currentTimeMillis();
        this.totalTimeContient += (endTime-startTime);
        return res;
    }
    

    //question 8
    public boolean ajout(BigInteger u){ //ajout de u si il n'est pas deja present 
        int couleur = h(u);
        boolean res = false;
        if(this.contient(u)==false){
            res = true;
            this.t[couleur].ajoutTete(u);
        }
        return res;
    }

    public void ajoutListe(ListeBigI L){
        ListeBigI l_copie = new ListeBigI(L); //copie de ma liste (afin de pouvoir modif sans toucher L)
        BigInteger copie_tete; // BigI qui recupereras la tete

        if(l_copie.longueur()==1){
            l_copie.ajoutTete(BigInteger.valueOf(4));
            copie_tete = l_copie.supprTete();
            if(this.contient(copie_tete)==false){ //si la tete n'y est pas je l'ajoute dans this
                this.ajout(copie_tete);
            }
        }

        else{
            for(int i=0;i<=l_copie.longueur()+1;i++){ //+1 parceque sinon bug et ne fais pas le dernier ! je dois le faire longueur+1 fois
                copie_tete = l_copie.supprTete();
                if(this.contient(copie_tete)==false){ //si la tete n'y est pas je l'ajoute dans this
                    this.ajout(copie_tete);
                }
            }
        }
    }

    //question 9
    public ListeBigI getElements(){ //renvoie une liste avec tout les elements de this 
        ListeBigI res = new ListeBigI();
        for(int i=0;i<this.t.length;i++){
            res.ajoutListe(this.t[i]);
        }
        return res;
    }

    //question 10
    public String toString(){ //affichage
        String res = "";
        for(int i=0;i<this.t.length;i++){
            res = res + "t["+i+"] : "+this.t[i].toString()+"\n";
        }
        res=res+"temps="+this.totalTimeh;
        return res;
    }

    //question 12
    public int getNbListes(){ //donne le nb de couleurs 
        return this.t.length;
    }

    public int getCardinal(){ //nb d'elem dans this 
        int card = 0;
        for(int i=0;i<this.t.length;i++){
            card+=this.t[i].longueur();
        }
        return card;
    }

    public int getMaxSize(){ //la taille max des couleurs de this
        int max = 0;
        for(int i=0;i<this.t.length;i++){
            if(this.t[i].longueur()>max){
                max = this.t[i].longueur();
            }
        }
        return max;
    }

    //question 13
    public String toStringV2(){
        String res = "";
        for(int i=0;i<this.t.length;i++){
            if(this.t[i].longueur()>0){
                res = res + "t["+i+"] : "+this.t[i].toString()+"\n";
            }
        }
        return res;
    }

    //question 14
    public HTNaive(ListeBigI l,int m){ //constructeur avec une liste
        this.t = new ListeBigI[m]; 
        this.totalTimeh=0;
        this.totalTimeContient=0;
        for(int i=0;i<this.t.length;i++){
            this.t[i] = new ListeBigI();
        }
        this.ajoutListe(l);

    }

    //question 15
    public HTNaive(ListeBigI l, double f){
        // Va créer une liste HTnaive avec m = |l|*f
        // Ou |l| est le nb d'elements différents dans l (donc peut etre différents de l.longueur())
        this.totalTimeh=0;
        this.totalTimeContient=0;
        HTNaive temp = new HTNaive(l.longueur()+1);
        temp.ajoutListe(l);
        double m2;
        m2 = temp.getCardinal()*f;
        int m = (int)m2;
        this.t = new ListeBigI[m]; 
        for(int i=0;i<this.t.length;i++){
            this.t[i] = new ListeBigI();
        } 
    }
}