// Matthias Pfaff 4-Gewinnt mit Alpha-Beta
// (Implementierter Algo für Alpha Beta ausm Netz
// ist somit Alpha Beta Pruning  hier mal zum Spass mit 8x8)

class KI_Test {

//### Konstanten und globale Variablen #################

// Groesse des Feldes
private static final int HOEHE  = 8;
private static final int BREITE = 8;

// Die Eintraege für das Feld
private static final int LEER     =  0;
private static final int MAXCONST =  1;
private static final int MINCONST = -1;
// Bemerkung: MAX <=> true, MIN <=> false

// Gewichte fuer die Bewertungsfunktion
private static final int MAX2FAKTOR = 1;
private static final int MIN2FAKTOR = 1;
private static final int MAX3FAKTOR = 100;
private static final int MIN3FAKTOR = 500;


private static final int POS_INFINITY = (int)Double.POSITIVE_INFINITY;
private static final int NEG_INFINITY = (int)Double.NEGATIVE_INFINITY;

//### Die Spielsituation ################################

static class SpielSituation {

  private int[][] feld = new int[BREITE][HOEHE];
  private boolean zug;

  // Konstruktor
  public SpielSituation() {
    for (int x=0; x<BREITE; x++)
      for (int y=0; y<HOEHE; y++)
        this.feld[x][y] = LEER;
    zug = false;
  }

  // allg. Zugriffsmethoden
  public int[][] getFeld() { return this.feld; }
  public boolean getZug() { return this.zug; }
  public void setZug(boolean spieler) { this.zug = spieler; }
  public void setFeld(int x, int y, int wert) { this.feld[x][y] = wert; }

  // prueft ob ein Chip in gegebene Spalte geworfen werden kann
  boolean gueltigerZug(int spalte) {
    if ((0<=spalte) && (spalte<BREITE)) {
      int y = 0;
      while ((y<HOEHE) && (this.feld[spalte][y]!=LEER)) y++;
      if (y<HOEHE) return true;   // gueltige Position 
      return false;
    } else return false; 
  }

  // prueft ob es noch einen gueltigen Zug gibt
  boolean existiertGueltigerZug() {
    for (int x=0; x<BREITE; x++) 
      if (this.gueltigerZug(x)) return true;
    return false;
  }

  // führe einen Zug aus
  public boolean wirfChip(int spalte) { 
    if (!this.gueltigerZug(spalte)) return false; // ungueltige Position
    int y = 0;
    while ((y<HOEHE) && (this.feld[spalte][y]!=LEER)) y++;
    if (this.zug)
      this.feld[spalte][y] = MAXCONST;
    else 
      this.feld[spalte][y] = MINCONST;
    this.zug = !this.zug;
    return true;                  // gueltiger Zug
  }

  // legt eine Kopie an
  private SpielSituation copy() {
    SpielSituation spiel2 = new SpielSituation();
    spiel2.setZug(this.zug);
    for (int x=0; x<BREITE; x++)
      for (int y=0; y<HOEHE; y++)
        spiel2.setFeld(x,y,this.getFeld()[x][y]);
    return spiel2;
  }

  // Ausgabe der Spielsituation auf der Konsole
  public void print() {
    System.out.println();
    for (int y=HOEHE-1; y>=0; y--) {
      for (int x=0; x<BREITE; x++) 
        if (this.feld[x][y]==MAXCONST) System.out.print(" X");
        else if (this.feld[x][y]==MINCONST) System.out.print(" O");
        else System.out.print(" .");
      System.out.println();
    }
    for (int x=0; x<BREITE; x++) System.out.print("__");  System.out.println("_");
    for (int x=0; x<BREITE; x++) System.out.print(" " + x);  System.out.println();
    System.out.println();
    System.out.println((this.zug==true ? "MAX (=X)" : "MIN (=O)") + " ist am Zug.");
  }
}


//### Die Bewertungsfunktion ############################

static int bewertung(SpielSituation spiel) {

  int[][] feld = spiel.getFeld();

  int min2er = 0; int max2er = 0;
  int min3er = 0; int max3er = 0;

  for (int x=0; x<BREITE; x++) {
    for (int y=0; y<HOEHE; y++) {
        // Noch 4 Chips nach oben moeglich?
        if (HOEHE-y>=4) {
          // 4 gleiche Chips?
          if (istReihe(feld,MAXCONST,x,y,0,1)==4) 
            return POS_INFINITY;  // gewonnen
          else if (istReihe(feld,MINCONST,x,y,0,1)==4)
            return NEG_INFINITY;  // verloren
          // 3 gleiche Chips?
          else if (istReihe(feld,MAXCONST,x,y,0,1)==3) 
            max3er++; 
          else if (istReihe(feld,MINCONST,x,y,0,1)==3)
            min3er++;
          // 2 gleiche Chips?
          else if (istReihe(feld,MAXCONST,x,y,0,1)==2) 
            max2er++; 
          else if (istReihe(feld,MINCONST,x,y,0,1)==2)
            min2er++;
        }
        // Noch 4 Chips nach rechts oben moeglich?
        if ((HOEHE-y>=4) && (BREITE-x>=4)) {
          // 4 gleiche Chips nach rechts oben?
          if (istReihe(feld,MAXCONST,x,y,1,1)==4) 
            return POS_INFINITY;  // gewonnen
          else if (istReihe(feld,MINCONST,x,y,1,1)==4)
            return NEG_INFINITY;  // verloren
          // 3 gleiche Chips uebereinander?
          else if (istReihe(feld,MAXCONST,x,y,1,1)==3) 
            max3er++; 
          else if (istReihe(feld,MINCONST,x,y,1,1)==3)
            min3er++;
          // 2 gleiche Chips uebereinander?
          else if (istReihe(feld,MAXCONST,x,y,1,1)==2) 
            max2er++; 
          else if (istReihe(feld,MINCONST,x,y,1,1)==2)
            min2er++;
        } 
        // Noch 4 Chips nach rechts moeglich?
        if (BREITE-x>=4) {
          if (istReihe(feld,MAXCONST,x,y,1,0)==4) 
            return POS_INFINITY;  // gewonnen
          else if (istReihe(feld,MINCONST,x,y,1,0)==4)
            return NEG_INFINITY;  // verloren
          // 3 gleiche Chips uebereinander?
          else if (istReihe(feld,MAXCONST,x,y,1,0)==3) 
            max3er++; 
          else if (istReihe(feld,MINCONST,x,y,1,0)==3)
            min3er++;
          // 2 gleiche Chips uebereinander?
          else if (istReihe(feld,MAXCONST,x,y,1,0)==2) 
            max2er++; 
          else if (istReihe(feld,MINCONST,x,y,1,0)==2)
            min2er++;
        }
        // Noch 4 Chips nach rechts unten moeglich?
        if ((BREITE-x>=4) && (y>=3)) {
           if (istReihe(feld,MAXCONST,x,y,1,-1)==4) 
            return POS_INFINITY;  // gewonnen
          else if (istReihe(feld,MINCONST,x,y,1,-1)==4)
            return NEG_INFINITY;  // verloren
          // 3 gleiche Chips uebereinander?
          else if (istReihe(feld,MAXCONST,x,y,1,-1)==3) 
            max3er++; 
          else if (istReihe(feld,MINCONST,x,y,1,-1)==3)
            min3er++;
          // 2 gleiche Chips uebereinander?
          else if (istReihe(feld,MAXCONST,x,y,1,-1)==2) 
            max2er++; 
          else if (istReihe(feld,MINCONST,x,y,1,-1)==2)
            min2er++;
        } 
    }

  }
//System.out.println("Bewertung intern: " + MAX2FAKTOR + "*" + max2er + " + " + MAX3FAKTOR + "*" + max3er 
//            + " - " + MIN2FAKTOR + "*" + min2er + " - " + MIN3FAKTOR + "*" + min3er);
  return MAX2FAKTOR*max2er + MAX3FAKTOR*max3er - MIN2FAKTOR*min2er - MIN3FAKTOR*min3er;
}

// Hilfsfunktion zum Erkennen einer 2er-, 3er- oder 4er-Reihe
// dx und dy geben die Richtung an, in die gesucht wird
// (ab der Position (x,y))
private static int istReihe(int [][] feld, int spieler, int x, int y, int dx, int dy) {
  int num = 0;
  // 4 freie oder von spieler belegte Felder in Richtung (dx,dy)?
  if (  ((feld[x][y]==spieler) || (feld[x][y]==LEER))
     && ((feld[x+1*dx][y+1*dy]==spieler) || (feld[x+1*dx][y+1*dy]==LEER))
     && ((feld[x+2*dx][y+2*dy]==spieler) || (feld[x+2*dx][y+2*dy]==LEER))
     && ((feld[x+3*dx][y+3*dy]==spieler) || (feld[x+3*dx][y+3*dy]==LEER))) {

    // zaehle Anzahl von spieler belegter Felder
    for (int i=0; i<4; i++) if (feld[x+i*dx][y+i*dy]==spieler) num++;
//if ((x==2) && (y==3)) System.out.println(dx +"," + dy +":  "+"num=" + num);
  }
  return num;
} // Ende Hilfsfunktion


//### Berechnung des Minimax-Wertes #####################

private static int minimaxWert(SpielSituation spiel, int tiefe, int alpha, int beta) { 

  SpielSituation spiel_tmp;
  int minimax_tmp;
  int minimax_lokal;
  // Initialisiere bisher besten gefundenen Wert
  if (spiel.getZug())   // Ist Computer (MAX) am Zug?
    minimax_lokal = alpha;
  else 
    minimax_lokal = beta;

  // Abbruch bei erreichter Maximaltiefe, aktuelle Situation 
  // wird mit Funktion bewertung() bewertet.
  if (tiefe==0)
    return bewertung(spiel);
  // Ansonsten untersuche (rek.) alle möglichen Zuege
  else {
    for (int spalte=0; spalte<BREITE; spalte++) {
      spiel_tmp = spiel.copy();
      if (spiel_tmp.wirfChip(spalte)) {    // gueltiger Zug?
        minimax_tmp = minimaxWert(spiel_tmp, tiefe-1, alpha, beta);
        // Merke min./max. Bewertung, je nachdem wer am Zug ist
        if (spiel.getZug()) {
          minimax_lokal = java.lang.Math.max(minimax_tmp, minimax_lokal);
          alpha = minimax_lokal;
          if (alpha>=beta) return beta;
        }
        else {
          minimax_lokal = java.lang.Math.min(minimax_tmp, minimax_lokal);
          beta = minimax_lokal;
          if (beta<=alpha) return alpha;
        };
      };
//System.out.println(prefix + tiefe +  ": " + (spiel.getZug() ? "X" : "M" ) + spalte + "->" + minimax_lokal);
    };
    return minimax_lokal;
  }

}

//### Berechnung des besten Zuges (fuer MAX) ##############

private static int berechneZug(SpielSituation spiel, int tiefe) {

  // Merke die Bewertungen der Zuege
  int [] wert = new int[BREITE];

System.out.print("Suche Zug: ");

  SpielSituation spiel_tmp;
  // probiere alle moeglichen Zuege
  for (int spalte=0; spalte<BREITE; spalte++) 
    if (spiel.gueltigerZug(spalte)) {
      spiel_tmp = spiel.copy();
      spiel_tmp.wirfChip(spalte);
      wert[spalte] = minimaxWert(spiel_tmp, tiefe, NEG_INFINITY, POS_INFINITY);
System.out.print(spalte+":"+wert[spalte]+"  ");
    }
  else System.out.print(spalte+":--  ");
  // Suche hoechsten Wert
  int zug = -1;  int max = NEG_INFINITY;
  for (int spalte=0; spalte<BREITE; spalte++) 
    if ((wert[spalte]>=max) && (spiel.gueltigerZug(spalte))) { zug = spalte;  max = wert[spalte]; };
System.out.println(" => " + zug);
  return zug;
}

//### Das Programm ######################################

  // Kleine Hilfsfunktion
 static int eingabe() {
    String s = "";
    try { 
      s = new java.io.BufferedReader(
              new java.io.InputStreamReader(System.in)).readLine();
    }
    catch (java.io.IOException e) {}
    return java.lang.Integer.parseInt (s);
  }

  // Zocken yeaha !!!
  public static void main(String[] args) {

    // Einstellung der Suchtiefe   bei  8x8 is net viel mehr drinn ;)
    int suchTiefe = 6;
    System.out.println("Suchtiefe: " + suchTiefe);

    int spalte;
    SpielSituation spiel = new SpielSituation();

    System.out.print("Wer faengt an? (0=selber/1=Rechner) ");
    int wer = eingabe();
    if (wer==1) {
      spiel.setZug(true);   // per default fängt der Spieler (false) an
      System.out.println();
       // Zug des Programms 
      spalte = berechneZug(spiel, suchTiefe);
      spiel.wirfChip(spalte);
    };

    System.out.println();
    spiel.print();
    while (true) {
      // Zug des Spielers
      do {
        System.out.print("Naechster Zug? (0-" + (BREITE-1) + ")  ");
        spalte = eingabe();
      } while (!spiel.gueltigerZug(spalte));
      spiel.wirfChip(spalte);
      spiel.print();
      System.out.println("Bewertung: " + bewertung(spiel));
      // Sieg?
      if (bewertung(spiel)==NEG_INFINITY) {
        System.out.println(" GEWONNEN!");
        break;
      };
      if (!spiel.existiertGueltigerZug()) {
        System.out.println(" UNENTSCHIEDEN ...");
        break;
      };

      // Zug des Programms 
      spalte = berechneZug(spiel, suchTiefe);
      spiel.wirfChip(spalte);
      spiel.print();
      System.out.println("Bewertung: " + bewertung(spiel));
      // Sieg?
      if (bewertung(spiel)==POS_INFINITY) {
        System.out.println(" VERLOREN!");
        break;
      };
      if (!spiel.existiertGueltigerZug()) {
        System.out.println(" UNENTSCHIEDEN ...");
        break;
      };
      System.out.println("_____________________________________");
      System.out.println();
    };
    System.out.println();
  }


}

