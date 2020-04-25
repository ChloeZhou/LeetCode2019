/**
 * Created by keyingzhou on 2/17/20.
 */
import java.util.*;
public class Contest176 {
    class Node {
        int num; int x; int y;
        public Node (int num, int x, int y) {
            this.num = num;
            this.x = x;
            this.y = y;
        }
    }
    public int countNegatives(int[][] grid) {
        //corner case:
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        //
        int n = grid.length; int m = grid[0].length;
        int[][] dir = {{1, 0}, {1, 1}, {0, 1}};
        boolean[][] visited = new boolean[n][m];
        Queue<Node> q = new LinkedList<Node>();
        q.offer(new Node(grid[0][0], 0, 0));
        visited[0][0] = true;
        int res = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                Node no = q.poll();
                if (no.num >= 0) {
                    res++;
                }
                for (int j = 0; j < dir.length; j++) {
                    int x = no.x + dir[j][0];
                    int y = no.y + dir[j][1];
                    if (visited[x][y]) {
                        continue;
                    }
                    q.offer(new Node(grid[x][y], x, y));
                }
            }
        }
        return n*m - res;
    }

    public ListNode appendTail(ListNode head, int value) {
        //corner case:
        if (head == null) {
            return new ListNode(value);
        }
        ListNode prev = head;
        while (prev.next != null) {
            prev = prev.next;
        }
        prev.next = new ListNode(value);
        return head;
    }

    public int maxEvents(int[][] events) {
        //corner case:
        if (events == null || events.length == 0 || events[0].length == 0) {
            return 0;
        }
        //Greedy alg: always attend the event that going to end sooner from today
        //Sort events date first
        //
        Arrays.sort(events, new Comparator<int[]>() {
            public int compare (int[] a, int[] b) {
                if (a[0] == b[0]) {
                    return 0;
                }
                return a[0] < b[0] ? -1 : 1;
            }
        });
        PriorityQueue<int[]> pq = new PriorityQueue<int[]>(new Comparator<int[]>() {
            public int compare (int[] a, int[] b) {
                if (a[1] == b[1]) {
                    return 0;
                }
                return a[1] < b[1] ? -1 : 1;
            }
        });
        int maxEvents = events.length; int maxDays = 0;
        for (int i = 0; i < events.length; i++) {
            if (events[i][1] > maxDays) {
                maxDays = events[i][1];
            }
        }
        int curDate = 1; int res = 0; int event_num = 0;
        while (curDate <= maxDays) {
            //if no events avaliable
            if (event_num < events.length && pq.isEmpty()) {
                curDate = events[event_num][1];
            }
            //insert event avaliable from curDate
            while (event_num < maxEvents && events[event_num][0] <= curDate) {
                pq.offer(events[event_num]);
            }
            //skip endDate pass events
            while (!pq.isEmpty() && pq.peek()[1] < curDate) {
                pq.poll();
            }
            if (!pq.isEmpty()) {
                pq.poll();
                res++;
            }
            curDate++;
        }
        return res;
    }
    public int[] closestDivisors(int num) {
        int d1 = largestDivision(num + 1); int d2 = largestDivision(num + 2);
        if (Math.abs(d1 - ((num+1) / d1)) < Math.abs(d2 - ((num + 2) / d2))) {
            return new int[]{d1, (num+1) / d1};
        } else {
            return new int[]{d2, (num+2) / d2};
        }
    }

    private int largestDivision (int num) {
        int d = (int)Math.sqrt(num);
        while (d > 0) {
            if (num % d == 0) {
                return d;
            }
            d--;
        }
        return d;
    }

    public int[] sortByBits(int[] arr) {
        //corner case:
        if (arr == null || arr.length < 2) {
            return arr;
        }
        //
        Integer[] arrInt = new Integer[arr.length];
        for (int i = 0; i < arr.length; i++) {
            arrInt[i] = arr[i];
        }
        Arrays.sort(arrInt, (a, b) -> {
            if (countBits(a) == countBits(b)) {
                return a - b;
            }
            return countBits(a) < countBits(b) ? -1 : 1;
        });
        for (int i = 0; i < arr.length; i++) {
            arr[i] = arrInt[i];
        }
        return arr;
    }

    private int countBits(int num) {
        int res = 0;
        for (int i = 0; i < 32; i++) {
            if(((num>>i) & 1) == 1) {
                res++;
            }
        }
        return res;
    }
    public int[] kSmallest(int[] array, int k) {
        // Write your solution here
        //coner case:
        if (array == null || array.length < 2) {
            return array;
        }
        //
        int[] res = new int[array.length];
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>(new Comparator<Integer>(){
            public int compare(Integer a, Integer b) {
                if (a == b) {
                    return 0;
                }
                return a < b ? 1 : -1;
            }
        });
        for (int i = 0; i < array.length; i++) {
            if (pq.size() < k) {
                pq.offer(array[i]);
            } else if (pq.peek() > array[i]) {
                pq.poll();
                pq.offer(array[i]);
            }
        }
        for (int i = k - 1; i >= 0; i--) {
            res[i] = pq.poll();
        }
        return res;
    }
    public boolean isBipartite(List<GraphNode> graph) {
        // write your solution here
        //corner case:
        if (graph == null || graph.size() == 0) {
            return true;
        }
        Map<GraphNode, Integer> map = new HashMap<GraphNode, Integer>();
        for (int i = 0; i < graph.size(); i++) {
            if (!bfs(graph.get(i), map)) {
                return false;
            }
        }
        return true;
    }

    private boolean bfs(GraphNode root,Map<GraphNode, Integer> map) {
        //corner case/base case:
        if (map.containsKey(root)) {
            return true;
        }
        Queue<GraphNode> q = new LinkedList<GraphNode>();
        q.offer(root);
        int curParty = 2;
        while (!q.isEmpty()) {
            int size = q.size();
            curParty = curParty == 1 ? 2 : 1;
            for (int i = 0; i < size; i++) {
                GraphNode cur = q.poll();
                if (map.containsKey(cur) && map.get(cur) != curParty) {
                    return false;
                }
                map.put(cur, curParty);
                for (GraphNode c : cur.neighbors) {
                    q.offer(c);
                }
            }
        }
        return true;
    }
    public String replace(String input, String source, String target) {
        // Write your solution here
        //corner case:
        if (input == null) {
            return input;
        }
        //
        StringBuilder sb = new StringBuilder();
        int fromIdex = 0;
        while (fromIdex < input.length() - source.length()) {
            int match = input.indexOf(source, fromIdex);
            sb.append(input.substring(fromIdex, match));
            sb.append(target);
            fromIdex = match + source.length();
        }
        return sb.toString();

    }
}
