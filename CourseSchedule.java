/*
* This is a graph problem as we need to identify and track what courses are dependent on what course.
* eg [1,0] -> 1 is dependent on 0. In order to take 1, we need to take 0 and there is no dependency on 0
* so, we can keep track of the indegrees array where idx is the course and value is the number of dependencies we need to finish before we can take that course
* Once, a course is taken, we need to go through the edge list to search for courses that has become independent or free to take. So iterating through the edge list everytime a course becomes independent is costly operation. So we make a map which is called adj list. 
* When you want to create the adj list(map):
    - key -> independent nodes -> because once a course/node is taken we need to check what all dependencies are resolved hence it makes sense to use independent nodes as keys
    - value: -> dependent nodes
* Lastly, for traversing the graph we can either do BFS or DFS. 
* If the indegree array elements are 0, that means we have taken all the courses, else we have a cycle and we wont be able to take all the course so return false
*/

//TC: (v+e) -> which is O(max(v,e)) -> where v is vertices e is edges
// SC: (v+e) -> to store the adj list and dependency array

class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        int[] indegrees = new int[numCourses];
        HashMap<Integer, List<Integer>> adjList = new HashMap<>();

        // fill indegrees array and adj list
        for(int[] pr : prerequisites){
            indegrees[pr[0]]++;
            adjList.putIfAbsent(pr[1], new ArrayList<>());
            adjList.get(pr[1]).add(pr[0]);
        }

        // add courses/nodes in the queue that doesnt have any dependency 
        int count = 0;
        Queue<Integer> queue = new LinkedList<>();

        for(int i = 0; i < numCourses; i++){
            if(indegrees[i] == 0){
                queue.add(i);
                count++;
            }
        }
        // all courses are dependent so we wont be able to take any courses
        if(queue.isEmpty())
            return false;
        // all courses are independent
        if(queue.size() == numCourses)
            return true;

        while(!queue.isEmpty()){
            int curr = queue.poll();
            List<Integer> dependencies = adjList.get(curr);

            if(dependencies != null){
                for(int dependency : dependencies){
                    indegrees[dependency]--;
                    if(indegrees[dependency] == 0){
                        queue.add(dependency);
                        count++;
                        if(count == numCourses)
                            return true;
                    }
                }
            }
        }
        return false;
    }
}