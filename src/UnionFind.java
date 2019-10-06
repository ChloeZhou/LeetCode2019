/**
 * Created by keyingzhou on 9/24/19.
 */
public class UnionFind {
    public int[] size;
    public int[] parent;
    public UnionFind(int count) {
        this.size = new int[count];
        this.parent = new int[count];
        for (int i = 0; i < count; i++) {
            this.size[i] = 1;
            this.parent[i] = i;
        }
    }

    public int find(int cur) {
        while (this.parent[cur] != cur) {
            cur = this.parent[this.parent[cur]];
        }
        return cur;
    }

    public void union(int a, int b) {
        int aroot = find(a);
        int broot = find(b);
        if (aroot == broot) {
            return;
        }
        if (this.size[aroot] >= this.size[broot]) {
            this.parent[broot] = aroot;
            this.size[aroot] += this.size[broot];
        } else {
            this.parent[aroot] = broot;
            this.size[broot] += this.size[aroot];
        }
    }
}
