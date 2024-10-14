const CODE = {
    "c++": {
        solutionUrl: "https://api.github.com/repos/alan10607/MyPractice/contents/c++",
        enabled: true,
        solutions: new Map(),
        isLoadingComplete: false
    },
    "java": {
        solutionUrl: "https://api.github.com/repos/alan10607/MyPractice/contents/java",
        enabled: true,
        solutions: new Map(),
        isLoadingComplete: false
    }
};

const LEETCODE_URL = "https://leetcode.com/problems/";
var LOAD_SIZE = 10;
var SOU_NO = [];
const STORAGE_KEY = "lc-did-no";
const ENABLED_LANGUAGE = new Map();

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
    for (let language of Object.keys(CODE)) {
        getFilePath(language);
    }

    showLoading();
    const loading = setInterval(function() {
        const loadingCount = Object.values(CODE).reduce((count, code) => {
            return code.isLoadingComplete ? count : count + 1;
        }, 0);

        if(loadingCount > 0){
            console.log("Wait file loading...");
        }else{
            printPages();
            closeLoading();
            clearInterval(loading);
        }
    }, 200);
}

function getFilePath(language){
    const url = CODE[language].solutionUrl;
    get(url, getFilePathAfter, language);
}

function getFilePathAfter(res, language){
    console.log(`Loading ${language} file paths...`);

    for(const r of res){
        if(!isSolution(r.name) || r.download_url == null) continue;

        var num = parseInt(r.download_url.match(new RegExp("Solution(\\d+)\\."))[1]);
        CODE[language].solutions.set(num, {
            "name" : r.name,
            "url" : r.download_url,
            "github" : r.html_url
        });
    }

    CODE[language].isLoadingComplete = true;
}

function isSolution(fileName){
    return new RegExp("^Solution").test(fileName);
}

function printPages(){
    var numSet = new Set();
    for(const code of Object.values(CODE)){
        const solutionsMap = code.solutions;
        for(let num of solutionsMap.keys()){
            numSet.add(num);
        }
    }
    SOU_NO = Array.from(numSet).sort((a, b) => a - b);

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

    var codeType = $('<span>', { id: 'combo-checkbox' });
    $("<span>", {text: "顯示: [C++"}).appendTo(codeType);
    $("<input>", { type: "checkbox", value: "c++", "onclick" : "setCodeEnable(this)", checked: CODE["c++"].enabled }).appendTo(codeType);
    $("<span>", {text: "] / [Java"}).appendTo(codeType);
    $("<input>", { type: "checkbox", value: "java", "onclick" : "setCodeEnable(this)", checked: CODE["c++"].enabled }).appendTo(codeType);
    $("<span>", {text: "]"}).appendTo(codeType);
    codeType.appendTo($('#page-bar'));
}

function printCode(start, len){
    $("#code-box").empty();
    $("#no-bar").empty();
    var query = [];
    for(let i = 0; i < len && start + i < SOU_NO.length; ++i){
        var num = SOU_NO[start + i];
        var problem = PROBLEMS.get(num);
        $("<div>", {id : num}).appendTo($("#code-box"));
        if(len > 1) {
            $("<a>", {text : num, href : "#" + num}).appendTo($("#no-bar"));
        }
        $("<span>", {text : num + ". "}).appendTo($("#" + num));
        $("<a>", {text : problem, href : LEETCODE_URL + problem, target : "_blank"}).appendTo($("#" + num));

        for(const code of Object.values(CODE)){
            if(!code.enabled) continue;
            const solutions = code.solutions;
            if(!solutions.has(num)) continue;
            const solution = solutions.get(num);
            $("<div>", {id : solution.name}).appendTo($("#" + num));
            query.push(solution)
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

function setCodeEnable(checkbox) {
    CODE[checkbox.value].enabled = checkbox.checked;
}

/* --- Loading視窗 --- */
function showLoading(){
    $("#loading").removeClass("disable");
}

function closeLoading(){
    $("#loading").addClass("disable");
}