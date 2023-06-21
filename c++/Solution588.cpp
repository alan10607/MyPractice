//FileSystem(), ls(), addContentToFile(), readContentFromFile(): O(1) O(n), mkdir(): O(m) O(n)
//n = 檔案或資料夾數量, m = path.length()
class FileSystem {//Solution588 $
public:
    unordered_map<string, set<string>> dirs;//<path, 檔名>
    unordered_map<string, string> files;//<檔名, 內容>

    FileSystem() {
        dirs["/"];
    }

    vector<string> ls(string path) {
        if(files.count(path)){//路徑是檔案時
            int i = path.find_last_of("/");//等同java lastIndexOf()
            return {path.substr(i + 1)};
        }

        set<string> names = dirs[path];
        return vector<string>(names.begin(), names.end());
    }

    void mkdir(string path) {
        if(path == "/") return;

        string dir = "/", folder = "";
        for(int i=1; i<path.length(); ++i){//略過第一個'/'
            if(path[i] == '/'){
                dirs[dir].insert(folder);
                dir += (dir == "/" ? folder : "/" + folder);
                folder.clear();
            }else{
                folder.push_back(path[i]);
            }
        }
        dirs[dir].insert(folder);
        dir += (dir == "/" ? folder : "/" + folder);
        dirs[dir];
    }

    void addContentToFile(string filePath, string content) {
        int i = filePath.find_last_of("/");
        string dir = filePath.substr(0, i);
        string fileName = filePath.substr(i + 1);
        if(dir.empty()) dir = "/";//避免filePath="/a"

        if(!dirs.count(dir)) mkdir(dir);
        dirs[dir].insert(fileName);
        files[filePath].append(content);
    }

    string readContentFromFile(string filePath) {
        return files[filePath];
    }

};