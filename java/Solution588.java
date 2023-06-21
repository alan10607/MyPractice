package leetCode.java;

import java.util.*;

//FileSystem(), ls(), addContentToFile(), readContentFromFile(): O(1) O(n), mkdir(): O(m) O(n)
//n = 檔案或資料夾數量, m = path.length()
class FileSystem {//Solution588 $
    public Map<String, Set<String>> dirs;
    public Map<String, String> files;

    public FileSystem() {
        dirs = new HashMap<>();
        files = new HashMap<>();
        dirs.put("/", new HashSet<>());
    }

    public List<String> ls(String path) {
        if(files.containsKey(path)){
            int i = path.lastIndexOf("/");
            List<String> res = new ArrayList<>();
            res.add(path.substring(i + 1));
            return res;
        }else if(dirs.containsKey(path)){
            return new ArrayList<>(dirs.get(path));
        }else{
            return new ArrayList<>();
        }
    }

    public void mkdir(String path) {
        if("/".equals(path)) return;

        String[] p = path.split("/");
        String dir = "/";
        for(int i=1; i<p.length; i++){//掉過第一個'/'
            if(!dirs.containsKey(dir))
                dirs.put(dir, new HashSet<>());

            dirs.get(dir).add(p[i]);
            dir += ("/".equals(dir) ? p[i] : "/" + p[i]);
        }
        if(!dirs.containsKey(dir))
            dirs.put(dir, new HashSet<>());
    }

    public void addContentToFile(String filePath, String content) {
        int i = filePath.lastIndexOf("/");
        String dir = filePath.substring(0, i);
        String fileName = filePath.substring(i + 1);
        if(dir.isEmpty()) dir = "/";

        if(!dirs.containsKey(dir)) mkdir(dir);
        dirs.get(dir).add(fileName);
        files.put(filePath, content);
    }

    public String readContentFromFile(String filePath) {
        if(!files.containsKey(filePath)) return "";
        return files.get(filePath);
    }
}