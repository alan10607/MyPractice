//DFS serialize(), deserialize(): O(V) O(V)
class Codec {//Solution297
public:
    string serialize(TreeNode* root) {
        return serDFS(root);
    }

    string serDFS(TreeNode* root){
        if(!root) return "N";
        return to_string(root->val) + "," + serDFS(root->left) + "," + serDFS(root->right);
    }

    TreeNode* deserialize(string data) {
        queue<string> q;//有可能是兩位數以上, 用string
        string temp = "";
        for(char ch : data){
            if(ch == ','){
                q.push(temp);
                temp.clear();
            }else{
                temp.push_back(ch);
            }
        }
        q.push(temp);//放入最後
        return desDFS(q);
    }

    TreeNode* desDFS(queue<string>& q){//要使用&q否則每次遞迴會不一樣
        string str = q.front(); q.pop();
        if(str == "N") return nullptr;

        int val = stoi(str);
        TreeNode* node = new TreeNode(val);
        node->left = desDFS(q);//依順序解決
        node->right = desDFS(q);
        return node;
    }
};