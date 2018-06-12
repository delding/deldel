// Clone an undirected graph. Each node in the graph contains a label and a list of its neighbors.
//
//
// OJ's undirected graph serialization:
// Nodes are labeled uniquely.
//
// We use # as a separator for each node, and , as a separator for node label and each neighbor of the node.
// As an example, consider the serialized graph {0,1,2#1,2#2,2}.
//
// The graph has a total of three nodes, and therefore contains three parts as separated by #.
//
// First node is labeled as 0. Connect node 0 to both nodes 1 and 2.
// Second node is labeled as 1. Connect node 1 to node 2.
// Third node is labeled as 2. Connect node 2 to node 2 (itself), thus forming a self-cycle.
// Visually, the graph looks like the following:
//
//        1
//       / \
//      /   \
//     0 --- 2
//          / \
//          \_/


/**
 * Definition for undirected graph.
 * struct UndirectedGraphNode {
 *     int label;
 *     vector<UndirectedGraphNode *> neighbors;
 *     UndirectedGraphNode(int x) : label(x) {};
 * };
 */
class Solution {
public:
    UndirectedGraphNode *cloneGraph(UndirectedGraphNode *node) {
        if (node == nullptr) return nullptr;
        queue<UndirectedGraphNode*> q;
        unordered_map<UndirectedGraphNode*, UndirectedGraphNode*> cloned;
        cloned[node] = new UndirectedGraphNode(node->label);
        q.push(node);
        while (!q.empty()) {
            auto n = q.front();
            q.pop();
            for (auto nei : n->neighbors) {
                if (cloned.count(nei) == 0) {
                    cloned[nei] = new UndirectedGraphNode(nei->label);
                    q.push(nei);
                }
                cloned[n]->neighbors.push_back(cloned[nei]);
            }
        }
        return cloned[node];
    }


    UndirectedGraphNode *cloneGraphDFS(UndirectedGraphNode *node) {
        unordered_map<UndirectedGraphNode*, UndirectedGraphNode*> memo;
        return clone(memo, node);
    }

    UndirectedGraphNode* clone(unordered_map<UndirectedGraphNode*, UndirectedGraphNode*>& memo, UndirectedGraphNode* n) {
        if (n == nullptr) return nullptr;
        if (memo.count(n) != 0) return memo[n];
        auto c = new UndirectedGraphNode(n->label);
        memo[n] = c;  // update memo before recursion to handle cycle
        for (auto nei : n->neighbors) {
            c->neighbors.push_back(clone(memo, nei));
        }
        return c;
    }
};
