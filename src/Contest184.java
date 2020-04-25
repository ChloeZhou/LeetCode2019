/**
 * Created by keyingzhou on 4/11/20.
 */
import java.util.*;
public class Contest184 {
    public List<String> stringMatching(String[] words) {
        //corner case：
        List<String> res = new ArrayList<String>();
        if (words == null || words.length == 0) {
            return res;
        }
        //
        Arrays.sort(words, new Comparator<String>(){
            public int compare (String a, String b) {
                if (a.length() == b.length()) {
                    return 0;
                }
                return a.length() < b.length() ? -1 : 1;
            }
        });
        for (int i = 0; i < words.length; i++) {
            for (int j = i + 1; j < words.length; j++) {
                if (words[j].indexOf(words[i]) > -1) {
                    res.add(words[i]);
                    break;
                }
            }
        }
        return res;
    }

    class ListNode {
        public int val;
        public ListNode next;
        public ListNode(int val) {
            this.val = val;
        }
    }
    public int[] processQueries(int[] queries, int m) {
        //corner case:
        if (m ==0 || queries == null || queries.length == 0) {
            return new int[0];
        }
        //
        ListNode dummyHead = new ListNode(0);
        ListNode tail = dummyHead;
        for (int i = 0; i < m; i++) {
            ListNode node = new ListNode(i);
            tail.next = node;
            tail = node;
        }
        int[] res = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int q = queries[i];
            ListNode response = dummyHead.next;
            ListNode prev = dummyHead;
            for (int j = 0; j < q; j++) {
                prev = prev.next;
                response = response.next;
            }
            res[i] = response.val;
            ListNode next = response.next;
            prev.next = next;
            ListNode sec = dummyHead.next;
            dummyHead.next = response;
            response.next = sec;
        }
        return res;
    }

    public String entityParser(String text) {
        //corner case:
        if (text == null || text.length() == 0) {
            return "";
        }
        //
        Map<String, Character> map = new HashMap<String, Character>();
        map.put("&quot;", '"');
        map.put("&apos;", '\'');
        map.put("&amp;", '&');
        map.put("&gt;", '>');
        map.put("&lt;", '<');
        map.put("&frasl;", '/');
        char[] textArray = text.toCharArray();
        int slow = 0;
        for (int i = 0; i < textArray.length; i++) {
            if (textArray[i] != '&') {
                textArray[slow++] = textArray[i];
            } else {
                int j = i;
                StringBuilder sb = new StringBuilder();
                while (j < textArray.length) {
                    if (textArray[j] != ';') {
                        sb.append(textArray[j]);
                        j++;
                    } else {
                        sb.append(textArray[j++]);
                        if (map.containsKey(sb.toString())) {
                            textArray[slow++] = map.get(sb.toString());
                            i = j == textArray.length ? textArray.length - 1: j;
                        } else {
                            textArray[slow++] = textArray[i];
                        }
                        break;
                    }
                }
                if (j == textArray.length) {
                    textArray[slow++] = textArray[i];
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < slow; i++) {
            sb.append(textArray[i]);
        }
        return sb.toString();
    }

    //
    //reorderLogFiles - O(nlogn)
    //space(n)
    //
    //
    //
    public String[] reorderLogFiles(String[] logs) {
        //corner case:
        if (logs == null || logs.length == 0) {
            return new String[0];
        }
        //
        List<String[]> digits = new ArrayList<String[]>();
        List<String[]> character = new ArrayList<String[]>();
        //O(n)
        for (int i = 0; i < logs.length; i++) {
            String[] keyContent = logs[i].split(" ", 2);
            if (keyContent[1].charAt(0) <= '9') {
                digits.add(keyContent);
            } else {
                character.add(keyContent);
            }
        }
        //O(mnlogn)
        Collections.sort(character, (a, b) -> {
            if (a[1].equals(b[1])) {
                return a[0].compareTo(b[0]);
            }
            return a[1].compareTo(b[1]);
        });
        String[] res = new String[logs.length];
        //
        for (int i = 0; i < character.size(); i++) {
            String[] keyContent = character.get(i);
            res[i] = keyContent[0] + " " + keyContent[1];
        }
        for (int i = 0; i < digits.size(); i++) {
            String[] keyContent = digits.get(i);
            res[i + character.size()] = keyContent[0] + " " + keyContent[1];
        }
        return res;
    }
    //
    //O(mn)
    //Space(mn)
    public int numIslands(char[][] grid) {
        //corner case:
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        //
        int res = 0;
        int n = grid.length; int m = grid[0].length;
        boolean[][] visited = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == '1' && !visited[i][j]) {
                    DFSMarking(grid, i, j, visited);
                    res++;
                }
            }
        }
        return res;
    }
    //position, grid
    private void DFSMarking (char[][] grid, int i, int j, boolean[][] visited) {
        int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        //base case:
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] != '1' || visited[i][j]) {
            return;
        }
        //recursive rule
        visited[i][j] = true;
        for (int k = 0; k < directions.length; k++) {
            DFSMarking(grid, i + directions[k][0], j + directions[k][1], visited);
        }
    }
    //
    //
    //O(n)
    //Space(n)
    public List<String> topKFrequent(String[] words, int k) {
        //corner case:
        if (words == null || words.length == 0) {
            return new ArrayList<String>();
        }
        //
        Map<String, Integer> stringFreq = new HashMap<String, Integer>();
        for (int i = 0; i < words.length; i++) {
            stringFreq.put(words[i], stringFreq.getOrDefault(words[i], 0) + 1);
        }
        PriorityQueue<String> maxHeap = new PriorityQueue<String>((a, b) -> {
            if (stringFreq.get(a).equals(stringFreq.get(b))) {
                return a.compareTo(b);
            }
            return stringFreq.get(a) < stringFreq.get(b) ? 1 : -1;
        });
        for (Map.Entry<String, Integer> en : stringFreq.entrySet()) {
            maxHeap.offer(en.getKey());
        }
        List<String> res = new ArrayList<String>();
        int cnt = 0;
        while (cnt < k) {
            res.add(maxHeap.poll());
            cnt++;
        }
        return res;
    }

    public int[] twoSum(int[] nums, int target) {

        if(nums.length < 2 || nums == null){
            return null;
        }
        int[] result = new int[2];
        Map<Integer, Integer> table = new HashMap<Integer, Integer>();
        for(int i = 0; i < nums.length; i++){
            if (table.containsKey(target - nums[i])){
                result[1] = i;
                result[0] = table.get(target - nums[i]);
                return result;
            }
            table.put(nums[i], i);
        }
        return result;
    }
    //
    //O(n)
    //Space(1)
    //
    public List<Integer> partitionLabels(String S) {
        List<Integer> res = new ArrayList<>();
        //corner case:
        if (S == null || S.length() == 0) {
            return res;
        }
        //record the end index of each character
        int[] map = new int[26];
        for (int i = 0; i < S.length(); i++) {
            map[S.charAt(i) - 'a'] = i;
        }
        //
        int last = 0;
        int start = 0;
        for (int i = 0; i < S.length(); i++) {
            last = Math.max(last, map[S.charAt(i) - 'a']);
            if (last == i) {
                res.add(last - start + 1);
                start = last + 1;
            }
        }
        return res;
    }
    //
    // O(n + m)
    ///Space(n)
    //

    public List<int[]> getPairs(List<int[]> a, List<int[]> b, int target) {
        Collections.sort(a, (i,j) -> {
            if (i[1] == j[1]) {
                return 0;
            }
            return i[1] < j[1] ? -1 : 1;
        });
        Collections.sort(b, (i,j) -> {
            if (i[1] == j[1]) {
                return 0;
            }
            return i[1] < j[1] ? -1 : 1;
        });
       int start = 0;
       int end = b.size() - 1;
       int max = Integer.MIN_VALUE;
       List<int[]> res = new ArrayList<int[]>();
       while (start < a.size() && end >= 0) {
           int[] candidateA = a.get(start);
           int[] candidateB = b.get(end);
           int sum = candidateA[1] + candidateB[1];
           if (sum < target) {
               max = Math.max(max, sum);
               res.clear();
           }
           if (sum <= target) {
               res.add(new int[]{candidateA[0], candidateB[0]});
               int tempEnd = end - 1;
               while (tempEnd >= 0 && b.get(tempEnd)[1] == candidateB[1]) {
                   res.add(new int[]{candidateA[0], b.get(tempEnd--)[0]});
               }
               start++;
           } else {
               end--;
           }
       }
        return res;
    }

    int id = 0;
    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        //
        List<Integer>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i < connections.size(); i++) {
            List<Integer> conn = connections.get(i);
            graph[conn.get(0)].add(conn.get(1));
            graph[conn.get(1)].add(conn.get(0));
        }
        int[] ids = new int[n];
        boolean[] visited = new boolean[n];
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        for (int i = 0; i < n; i++) {
            dfs(res, ids, visited, i, -1, graph);
        }
        return res;
    }

    private int dfs (List<List<Integer>> res, int[] ids, boolean[] visited, int cur, int parent, List[] graph) {
        //base case:
        if (visited[cur]) {
            return ids[cur];
        }
        //
        visited[cur] = true;
        ids[cur] = id++;
        int lowLink = Integer.MAX_VALUE;
        for (Integer nei : (List<Integer>)graph[cur]) {
            if (parent == nei) {
                continue;
            }
            int neiId = dfs(res, ids, visited, nei, cur, graph);
            lowLink = Math.min(lowLink, neiId);
        }
        if (lowLink >= ids[cur]) {
            if (parent > -1) {
                res.add(Arrays.asList(parent, cur));
            }
        }
        ids[cur] = Math.min(lowLink, ids[cur]);
        return ids[cur];

    }
        int rootNodeOutcomeEdge = 0;
        public List<Integer> findArticualrPoint(int n, List<List<Integer>> connections) {
            id = 0;
            // use a timestamp, for each node, check the samllest timestamp that can reach from the node
            // construct the graph first
            List<int[]> graph = new ArrayList<int[]>();
            for (List<Integer> c : connections) {
                graph.add(new int[]{c.get(0), c.get(1)});
                graph.add(new int[] {c.get(1), c.get(0)});
            }
            int[] ids = new int[n];
            boolean[] visited = new boolean[n];
            List<Integer> artiPoints = new ArrayList<Integer>();
            for (int i = 0; i < graph.size(); i++) {
                rootNodeOutcomeEdge = 0;
                artidfs(artiPoints, visited, ids, i, -1, graph, i);
                if (rootNodeOutcomeEdge > 1) {
                    artiPoints.add(i);
                }
            }
            return artiPoints;
        }

        // return the minimum timestamp it ever visited in all the neighbors
        private int artidfs(List<Integer> artiPoints, boolean[] visited, int[] ids, int cur, int parent,
                            List<int[]> graph, int root) {
            //base case:
            if (visited[cur]) {
                return ids[cur];
            }
            if (parent == root) {
                rootNodeOutcomeEdge++;
            }
            visited[cur] = true;
            ids[cur] = id++;
            int lowLink = Integer.MAX_VALUE;
            for (int nei : graph.get(cur)) {
                if (nei == parent) continue; // no need to check the parent
                int neiId = artidfs(artiPoints, visited, ids, nei, cur, graph, root);
                lowLink = Math.min(lowLink, neiId);
            }

            if (ids[cur] <= lowLink) {
                artiPoints.add(cur);
            }
            ids[cur] = Math.min(ids[cur], lowLink);
            return ids[cur];
        }

    private static List<String> solve(int k, String[] keywords, String[] reviews) {
        List<String> res = new ArrayList<>();
        Set<String> set = new HashSet<>(Arrays.asList(keywords));
        Map<String, Integer> map = new HashMap<>();
        for(String r : reviews) {
            String[] strs = r.split("\\W");
            Set<String> added = new HashSet<>();
            for(String s : strs) {
                s = s.toLowerCase();
                if(set.contains(s) && !added.contains(s)) {
                    map.put(s, map.getOrDefault(s, 0) + 1);
                    added.add(s);
                }
            }
        }
        Queue<Map.Entry<String, Integer>> maxHeap = new PriorityQueue<>((a, b)->a.getValue() == b.getValue() ? a.getKey().compareTo(b.getKey()) : b.getValue() - a.getValue());
        maxHeap.addAll(map.entrySet());
        while(!maxHeap.isEmpty() && k-- > 0) {
            res.add(maxHeap.poll().getKey());
        }
        return res;
    }

    public int minHours(int[][] grid) {
        //corner case:
        if (grid == null || grid.length == 0) {
            return 0;
        }
        int[][] dir = new int[][]{{-1, 0},{1, 0}, {0, -1}, {0, 1}};
        Queue<int[]> q = new LinkedList<int[]>();
        int m = grid.length; int n = grid[0].length;
        boolean[][] visited = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    q.offer(new int[]{i, j});
                    visited[i][j] = true;
                }
            }
        }
        int res = -1;
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i  = 0; i < size; i++) {
                int[] pos = q.poll();
                for (int j = 0; j < dir.length; j++) {
                    int[] next = new int[]{pos[0] + dir[j][0], pos[1] + dir[j][1]};
                    if (next[0] >= 0 && next[0] < m && next[1] >= 0
                            && next[1] <  n && !visited[next[0]][next[1]]) {
                        q.offer(next);
                        visited[next[0]][next[1]] = true;
                    }
                }
            }
            res++;
        }
        return res;
    }
    //
    // Time(m*n*logn + searchWord.length);
    // Space(L*m);
    //
    class TrieTreeNode1 {
        public char v;
        public List<String> suggestions;
        public TrieTreeNode1[] children;
        public TrieTreeNode1 (char v) {
            this.v = v;
            this.suggestions = new ArrayList<String>();
            this.children = new TrieTreeNode1[26];
        }
    }
    public List<List<String>> suggestedProducts(String[] products, String searchWord) {
        List<List<String>> res = new ArrayList<List<String>>();
        Arrays.sort(products);
        //coner case:
        if (products == null || products.length == 0 || searchWord.length() == 0) {
            return res;
        }
        //
        TrieTreeNode1 root = new TrieTreeNode1('0');
        buildTree(products, root);
        List<List<String>> ans = new ArrayList<>();
        for (int i = 0; i < searchWord.length(); i++) {
            Character c = searchWord.charAt(i);
            if (root != null) // if there exist products with current prefix.
                root = root.children[c - 'a'];
            ans.add(root == null ? Arrays.asList() : root.suggestions); // add it if there exist products with current prefix.
        }
        return ans;
    }

    private void buildTree(String[] products, TrieTreeNode1 root) {
        //
        for (String product : products) {
            TrieTreeNode1 cur = root;
            for (int i = 0; i < product.length(); i++) {
                if (cur.children[product.charAt(i) - 'a'] == null) {
                    cur.children[product.charAt(i) - 'a'] = new TrieTreeNode1(product.charAt(i));
                }
                cur = cur.children[product.charAt(i) - 'a'];
                if (cur.suggestions.size() < 3) {
                    cur.suggestions.add(product);
                }
            }
        }
    }
    //
    // n - review numbers
    //m - average words in a review
    //O(mn)
    //O(mn)

    public void keyWords(String[] args) {
        int k1 = 2;
        String[] keywords1 = { "anacell", "cetracular", "betacellular" };
        String[] reviews1 = { "Anacell provides the best services in the city", "betacellular has awesome services",
                "Best services provided by anacell, everyone should use anacell", };
        int k2 = 2;
        String[] keywords2 = { "anacell", "betacellular", "cetracular", "deltacellular", "eurocell" };
        String[] reviews2 = { "I love anacell Best services; Best services provided by anacell",
                "betacellular has great services", "deltacellular provides much better services than betacellular",
                "cetracular is worse than anacell", "Betacellular is better than deltacellular.", };
        System.out.println(topKKeyWords(k1, keywords1, reviews1));
        System.out.println(topKKeyWords(k2, keywords2, reviews2));
    }

    private List<String> topKKeyWords(int k, String[] keywords, String[] reviews) {
        List<String> res = new ArrayList<>();
        Set<String> set = new HashSet<>(Arrays.asList(keywords));
        Map<String, Integer> map = new HashMap<>();
        for(String r : reviews) {
            String[] strs = r.split("\\W");
            Set<String> added = new HashSet<>();
            for(String s : strs) {
                s = s.toLowerCase();
                if(set.contains(s) && !added.contains(s)) {
                    map.put(s, map.getOrDefault(s, 0) + 1);
                    added.add(s);
                }
            }
        }
        Queue<Map.Entry<String, Integer>> maxHeap = new PriorityQueue<>((a, b)->a.getValue() == b.getValue() ? a.getKey().compareTo(b.getKey()) : b.getValue() - a.getValue());
        maxHeap.addAll(map.entrySet());
        while(!maxHeap.isEmpty() && k-- > 0) {
            res.add(maxHeap.poll().getKey());
        }
        return res;
    }

    public int minNumberOfFrogs(String croak) {
        int c = 0, r = 0, o = 0, a = 0, k = 0, in_use = 0, answer = 0;
        for (char d:croak.toCharArray()) {
            switch(d) {
                case 'c':
                    c++;
                    in_use++;
                    break;
                case 'r':
                    r++;
                    break;
                case 'o':
                    o++;
                    break;
                case 'a':
                    a++;
                    break;
                case 'k':
                    k++;
                    in_use--;
                    break;
            }
            answer = Math.max(answer, in_use);
            if ((c < r) || (r < o) || (o < a) || (a < k))
                return -1;
        }
        if (in_use == 0 && c == r && c == o && c == a && c == k)
            return answer;

        return -1;
    }


}
