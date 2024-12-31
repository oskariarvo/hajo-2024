package fi.utu.tech.assignment4;

import java.util.ArrayList;
import java.util.List;

// Käytetään tehtässä 1 muokattua GradingTask-oliota
import fi.utu.tech.common.GradingTask;
// Allokointifunktiot implementoidaan TaskAllocator-luokassa
import fi.utu.tech.common.TaskAllocator;

import fi.utu.tech.common.Submission;
import fi.utu.tech.common.SubmissionGenerator;
import fi.utu.tech.common.SubmissionGenerator.Strategy;


public class App4 {
    public static void main( String[] args ) throws InterruptedException {
        // Otetaan funktion aloitusaika talteen suoritusajan laskemista varten
        long startTime = System.currentTimeMillis();

        // Generoidaan kasa esimerkkitehtäväpalautuksia
        List<Submission> ungradedSubmissions = SubmissionGenerator.generateSubmissions(21, 200, Strategy.STATIC);

        // Tulostetaan tiedot esimerkkipalautuksista ennen arviointia
        for (var ug : ungradedSubmissions) {
            System.out.println(ug);
        }

        // Luodaan uusi arviointitehtävä
        //GradingTask gradingTask = new GradingTask(ungradedSubmissions);

        List<GradingTask> tasks = TaskAllocator.sloppyAllocator(ungradedSubmissions);
        List<Thread> allThreads = new ArrayList<>();

        // Luodaan uudet säikeet
        for (GradingTask task : tasks) {
            Thread thread = new Thread(task);
            allThreads.add(thread);
            thread.start();
        }

        for (Thread thread : allThreads) {
            thread.join();
        }

        List<Submission> allGraded = new ArrayList<>();
        for (GradingTask task : tasks) {
            allGraded.addAll(task.getGradedSubmissions());
        }
        //thread.start();

        //Odotetaan suorittamista
        //thread.join();

        // Tulostetaan arvioidut palautukset
        System.out.println("------------ CUT HERE ------------");
        for (var gs : allGraded) {
            System.out.println(gs);
        }

        // Lasketaan funktion suoritusaika
        System.out.printf("Total time for grading: %d ms%n", System.currentTimeMillis()-startTime);
    }
}
