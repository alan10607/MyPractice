//DFS serialize(), deserialize(): O(V) O(V)
class Codec {//Solution297
public:
    string serialize(TreeNode* root) {
        ostringstream oss;
        serialize(root, oss);
        return oss.str();
    }

    void serialize(TreeNode* root, ostringstream& oss) {
        if (!root) {
            oss << "N ";
        } else {
            oss << root->val << " ";
            serialize(root->left, oss);
            serialize(root->right, oss);
        }
    }

    TreeNode* deserialize(string data) {
        istringstream iss(data);
        return deserialize(iss);
    }

    TreeNode* deserialize(istringstream& iss) {
        string val;
        iss >> val;
        if (val == "N") {
            return nullptr;
        } else {
            TreeNode* node = new TreeNode(stoi(val));
            node->left = deserialize(iss);
            node->right = deserialize(iss);
            return node;
        }
    }
};


//DFS serialize(), deserialize(): O(V) O(V)
class Codec {//Solution297_2
public:
    string serialize(TreeNode* root) {
        if (!root) return "N";

        return format("{},{},{}", root->val, serialize(root->left), serialize(root->right));
    }

    TreeNode* deserialize(string data) {
        queue<string> q; // 有可能是兩位數以上, 用string
        string tmp = "";
        for (char ch : data) {
            if (ch == ',') {
                q.push(tmp);
                tmp = "";
            } else {
                tmp.push_back(ch);
            }
        }
        q.push(tmp); // 放入最後
        return buildTree(q);
    }

    TreeNode* buildTree(queue<string>& q) { // 依照preorder解析
        string val = q.front(); q.pop();
        if (val == "N") return nullptr;

        TreeNode* node = new TreeNode(stoi(val));
        node->left = buildTree(q);
        node->right = buildTree(q);
        return node;
    }
};