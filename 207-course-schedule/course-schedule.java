import java.util.*;

class Solution {
    /**
     * LeetCode Problem 207: Course Schedule
     * 
     * Determines if it is possible to finish all courses given prerequisites.
     * 
     * @param numCourses The total number of courses.
     * @param prerequisites An array of course pairs, where prerequisites[i][0] is the course
     *                      that requires prerequisites[i][1].
     * @return True if it is possible to finish all courses, false otherwise.
     */
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // Create an adjacency list to represent the graph of courses and prerequisites.
        List<List<Integer>> adjList = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            adjList.add(new ArrayList<>());
        }

        // Create an array to store the in-degree (number of incoming edges) for each course.
        int[] inDegree = new int[numCourses];

        // Populate the adjacency list and in-degree array based on the prerequisites.
        for (int[] prerequisite : prerequisites) {
            int course = prerequisite[0];
            int preReq = prerequisite[1];
            adjList.get(preReq).add(course);
            inDegree[course]++;
        }

        // Create a queue to store courses with an in-degree of 0 (no prerequisites).
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (inDegree[i] == 0) {
                queue.offer(i);
            }
        }

        // Initialize a counter to track the number of courses that can be finished.
        int count = 0;

        // Perform topological sort using Kahn's algorithm.
        while (!queue.isEmpty()) {
            int course = queue.poll();
            count++;

            // Iterate through the neighbors (courses that depend on this course).
            for (int neighbor : adjList.get(course)) {
                // Decrease the in-degree of the neighbor.
                inDegree[neighbor]--;

                // If the in-degree of the neighbor becomes 0, add it to the queue.
                if (inDegree[neighbor] == 0) {
                    queue.offer(neighbor);
                }
            }
        }

        // If the number of courses that can be finished equals the total number of courses,
        // it means there is no cycle and all courses can be completed.
        return count == numCourses;
    }

    // Example Usage (for testing purposes)
    public static void main(String[] args) {
        Solution solution = new Solution();

        // Test case 1
        int numCourses1 = 2;
        int[][] prerequisites1 = {{1, 0}};
        boolean result1 = solution.canFinish(numCourses1, prerequisites1);
        System.out.println("Can finish all courses (Test Case 1): " + result1); // Output: true

        // Test case 2
        int numCourses2 = 2;
        int[][] prerequisites2 = {{1, 0}, {0, 1}};
        boolean result2 = solution.canFinish(numCourses2, prerequisites2);
        System.out.println("Can finish all courses (Test Case 2): " + result2); // Output: false
    }
}