package fi.utu.tech.assignment4;


public class App4 {

    public static void main(String[] args) {
        Boolikulho kulho = new Boolikulho();

        // Käynnistetään säikeet
        new Boolivastaava(kulho).start();
        for (int i=0; i<Boolivastaava.booliReseptit.length; i++) {
            new Opiskelija(kulho).start();
        }
    }

}


class Boolikulho {

    private String booli;
    private boolean booliValmis = false;

    public synchronized void valmistaBooli(String boolinNimi) throws InterruptedException {
        /* 
         * Ennen kuin boolimaljaan alkaa tekemään mitään,
         * voi olla hyvä idea odottaa sen tyhjenemistä...
         */

        while (booliValmis) {
            wait();
        }

        if (booliValmis) {
            booli = booli + ", " + boolinNimi;
            System.out.printf("Boolimalja tulvii juomia %s!%n", booli);
        } else {
            this.booli = boolinNimi;
            booliValmis = true; // Boolivastaava täyttää booliastian
            System.out.println("Booli valmis: " + booli);
            notifyAll();
        }
    }

    public synchronized void juoBooli(String juoja) throws InterruptedException {
        /*
         * Ennen kuin boolimaljasta juodaan mitään,
         * kannattaa odottaa, että sinne ilmestyy jotain...
         */
        while (!booliValmis) {
            wait();
        }
        if (!booliValmis) {
            System.out.println(juoja + " sai käteensä tyhjän boolimaljan");
        } else {
            booliValmis = false; // Opiskelija juo boolin
            System.out.println(juoja + " nautti boolin " + booli);
            notifyAll();
        }

    }

}

// Alla oleviin luokkiin ei tule tai tarvitse koskea

class Opiskelija extends Thread {

    private Boolikulho kulho;
    private String nimi;

    public Opiskelija(Boolikulho kulho) {
        this.kulho = kulho;
        this.nimi = NameGenerator.nextName();
    }

    @Override
    public void run() {
        try {
            kulho.juoBooli(this.nimi);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}

class Boolivastaava extends Thread {

    private Boolikulho kulho;
    public static String[] booliReseptit = { "Marjabooli", "Simabooli", "Mehukattibooli", "Boolibooli", "Gambinabooli" };


    public Boolivastaava(Boolikulho kulho) {
        this.kulho = kulho;
    }

    @Override
    public void run() {
        for (String boolinNimi : booliReseptit) {
            try {
                kulho.valmistaBooli(boolinNimi);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    };
}