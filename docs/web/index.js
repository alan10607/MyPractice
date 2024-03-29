const FILE_URL = [
    "https://api.github.com/repos/alan10607/MyPractice/contents/c++",
    "https://api.github.com/repos/alan10607/MyPractice/contents/java"
];
const LEETCODE_URL = "https://leetcode.com/problems/";
var LOAD_SIZE = 10;
var CODE = new Array(FILE_URL.length).fill(null).map(() => new Map());
var LOAD_CNT = FILE_URL.length;
var SOU_NO = [];
const STORAGE_KEY = "lc-did-no";

function get(url, callback, ...args){
	$.ajax({
		type: "GET",
		url: url,
		success: function (res, status) {
            callback(res, (args != null && args.length == 1 ? args[0] : args));
			//console.log("Status:" + status + ",res:" + res);
		},
		error: function (xhr, status) {
			console.log("Status:" + status + ",xhr:" + JSON.stringify(xhr));
		}
	});
}

function init(){
    for(let i=0; i<FILE_URL.length; ++i){
        getFilePath(FILE_URL[i], i);
    }

    showLoading();
    var loading = setInterval(function() {
        if(LOAD_CNT > 0){
            console.log("Wait file loading...");
        }else{
            printPages();
            closeLoading();
            clearInterval(loading);
        }
    }, 200);
}

function getFilePath(url, index){
    get(url, getFilePathAfter, index);
}

function getFilePathAfter(res, index){
    for(let r of res){
        if(!isSolution(r.name) || r.download_url == null) continue;

        var no = parseInt(r.download_url.match(new RegExp("Solution(\\d+)\\."))[1]);
        CODE[index].set(no, {
            "name" : r.name,
            "url" : r.download_url,
            "github" : r.html_url
        });
    }
    --LOAD_CNT;
}

function isSolution(fileName){
    return new RegExp("^Solution").test(fileName);
}

function printPages(){
    var noSet = new Set();
    for(let code of CODE){
        for(let no of code.keys()){
            noSet.add(no);
        }
    }
    SOU_NO = Array.from(noSet).sort((a, b) => a - b);

    $("#page-bar").empty();
    $("<span>", {text : `題數: ${LOAD_SIZE}`, "onclick" : "changeLoadSize()"}).appendTo($("#page-bar"));
    for(let i=0; i<SOU_NO.length; ++i){
        if(i % LOAD_SIZE == LOAD_SIZE - 1 || i == SOU_NO.length - 1){
            var start = i - (LOAD_SIZE > 1 && i % LOAD_SIZE == 0 ? LOAD_SIZE : i % LOAD_SIZE);
            var str = start === i ? SOU_NO[start] : SOU_NO[start] + "-" + SOU_NO[i];
            $("<span>", {text : str, "onclick" : `printCode(${start}, LOAD_SIZE); getBold(this)`}).appendTo($("#page-bar"));
        }
    }
    $("<span>", {text : "[每日一題]", "onclick" : "printRandom()"}).appendTo($("#page-bar"));
}

function printCode(start, len){
    $("#code-box").empty();
    $("#no-bar").empty();
    var query = [];
    for(let i = 0; i < len && start + i < SOU_NO.length; ++i){
        var no = SOU_NO[start + i];
        var problem = PROBLEMS.get(no);
        $("<div>", {id : no}).appendTo($("#code-box"));
        if(len > 1) {
            $("<a>", {text : no, href : "#" + no}).appendTo($("#no-bar"));
        }
        $("<span>", {text : no + ". "}).appendTo($("#" + no));
        $("<a>", {text : problem, href : LEETCODE_URL + problem, target : "_blank"}).appendTo($("#" + no));
        for(let code of CODE){
            if(!code.has(no)) continue
            $("<div>", {id : code.get(no).name}).appendTo($("#" + no));
            query.push(code.get(no))
        }
    }

    for(let q of query){
        get(q.url, printCodeAfter, q.name, q.github);
    }
}

function printCodeAfter(res, args){
    var name = args[0];
    var github = args[1];
    var id = "#" + name.replace(".", "\\.");
    $("<a>", {text : name, href : github, target : "_blank"}).appendTo($(id));
    $("<pre>", {text : res, class : "prettyprint linenums"}).appendTo($(id));

    PR.prettyPrint();
}

function getBold(e){
    $("#page-bar .bold").removeClass("bold")
    $(e).addClass("bold");
}

function changeLoadSize(){
    const queue = [10, 1, 20];
    const index = queue.indexOf(LOAD_SIZE);
    LOAD_SIZE = queue[(index + 1) % queue.length];
    printPages();
}

function t(){
for(let i=0; i<230; ++i){
    printRandom();
}
}
function printRandom(){
    $("#random-bar").empty();
    const didNos = getDidNos();
    $("<span>", {text : `歷史: ${getDidNos()}`}).appendTo($("#random-bar"));
    
    if(didNos.length < SOU_NO.length){
        const no = getRandomProblemNo();
        const problemUrl = `https://leetcode.com/problems/${PROBLEMS.get(no)}`;
        $("<span>", {text : `本題: ${no}`}).appendTo($("#random-bar"));
        $("<a>", {text : problemUrl, "href" : problemUrl, "target" : "_blank"}).appendTo($("#random-bar"));
    }else{
        $("<span>", {text : "已完成全部"}).appendTo($("#random-bar"));
    }

    $("<span>", {text : "[清除紀錄]", "onclick" : `saveDidNos([]); $("#random-bar").empty()`}).appendTo($("#random-bar"));
}

function getDidNos(){
    const storage = localStorage.getItem(STORAGE_KEY);
    return storage ? JSON.parse(storage) : [];  
}

function saveDidNos(didNos){
    localStorage.setItem(STORAGE_KEY, JSON.stringify(didNos));  
}

function getRandomProblemNo(){
    const noSet = new Set(SOU_NO);
    const didNos = getDidNos();  
    console.log("Did Solutions:", didNos);
    for(let didNo of didNos){
        noSet.delete(didNo);
    }
    const noArray = Array.from(noSet);
    const no = noArray[Math.floor(Math.random() * noArray.length)];
    didNos.push(no);
    saveDidNos(didNos);
    return no;
}

/* --- Loading視窗 --- */
function showLoading(){
    $("#loading").removeClass("disable");
}

function closeLoading(){
    $("#loading").addClass("disable");
}