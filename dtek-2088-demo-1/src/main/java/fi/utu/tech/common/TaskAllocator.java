package fi.utu.tech.common;

import java.util.ArrayList;
import java.util.List;

/**
 * You need to modify this file
 */


public class TaskAllocator {

    /**
     * Allocator that creates list of two (2) GradingTask objects with each having half of the given submissions
     * @param submissions The submissions to be allocated
     * @return The two GradingTask objects in a list, each having half of the submissions
     */
    public static List<GradingTask> sloppyAllocator(List<Submission> submissions) {
        // TODO: Tehtävä 4
        // Retruns null for now to suppress warnings
        List<GradingTask> tasks = new ArrayList<>();
        int halfway = (int) Math.ceil(submissions.size()/2);
        List<Submission> firstList = submissions.subList(0, halfway);
        List<Submission> secondList = submissions.subList(halfway, submissions.size());
        GradingTask firstTask = new GradingTask(firstList);
        GradingTask secondTask = new GradingTask(secondList);
        tasks.add(firstTask);
        tasks.add(secondTask);
        return tasks;
    }


    
    /**
     * Allocate List of ungraded submissions to tasks
     * @param submissions List of submissions to be graded
     * @param taskCount Amount of tasks to be generated out of the given submissions
     * @return List of GradingTasks allocated with some amount of submissions (depends on the implementation)
     */
    public static List<GradingTask> allocate(List<Submission> submissions, int taskCount) {
        // TODO: Tehtävä 5
        // Retruns null for now to suppress warnings
        //System.out.println(taskCount);
        List<GradingTask> tasks = new ArrayList<>();

        for (int i = 1; i <= taskCount; i++) {
            tasks.add(new GradingTask(new ArrayList<>()));
        }
        //System.out.println("here");
        for (int i = 0; i < submissions.size(); i++) {
            //System.out.println("i: " + i % taskCount);
            //System.out.println(i % taskCount);
            tasks.get(i % taskCount).getSubmissions().add(submissions.get(i));
            //System.out.println(tasks.get(i % taskCount).getSubmissions());
        }
        return tasks;
    }
}
