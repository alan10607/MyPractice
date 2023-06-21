//DFS O(V) O(V)
class Solution133 {
public:
    unordered_map<Node*, Node*> memo;

    Node* cloneGraph(Node* node) {
        if(!node) return nullptr;
        if(memo.count(node)) return memo[node];

        Node* clone = new Node(node->val);
        memo[node] = clone;

        for(Node* n : node->neighbors){
            clone->neighbors.push_back(cloneGraph(n));
        }
        return clone;
    }
};