const FILE_URL = [
    "https://api.github.com/repos/alan10607/MyPractice/contents/src/leetCode/c++",
    "https://api.github.com/repos/alan10607/MyPractice/contents/src/leetCode/java"
];
const LOAD_SIZE = 10;
var CODE = new Array(FILE_URL.length).fill(null).map(() => new Map());
var LOAD_CNT = FILE_URL.length;
var SOU_NO = [];

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

        var no = parseInt(r.download_url.match(/(?<=Solution)[0-9]+(?=.)/)[0]);
        CODE[index].set(no, {
            "name" : r.name,
            "url" : r.download_url,
            "github" : r.html_url
        });
    }
    --LOAD_CNT;
}

function isSolution(fileName){
    return /^Solution/.test(fileName);
}

function printPages(){
    var noSet = new Set();
    for(let code of CODE){
        for(let no of code.keys()){
            noSet.add(no);
        }
    }
    SOU_NO = Array.from(noSet).sort((a, b) => a - b);

    for(let i=0; i<SOU_NO.length; ++i){
        if(i % LOAD_SIZE == LOAD_SIZE - 1 || i == SOU_NO.length - 1){
            var start = i - (LOAD_SIZE > 1 && i % LOAD_SIZE == 0 ? LOAD_SIZE : i % LOAD_SIZE);
            var str = SOU_NO[start] + "-" + SOU_NO[i];
            $("<span>", {text : str, "onclick" : `printCode(${start}, LOAD_SIZE);`}).appendTo($("#page-bar"));
        }
    }

    var barHeight = $("#page-bar")[0].offsetHeight;
    $("#header").css("height", barHeight + "px");
}

function printCode(start, len){
    $("#code-box").empty();
    $("#no-bar").empty();
    var query = [];
    for(let i = 0; i < len && start + i < SOU_NO.length; ++i){
        var no = SOU_NO[start + i];
        $("<div>", {id : no}).appendTo($("#code-box"));
        $("<a>", {text : no, href : "#" + no}).appendTo($("#no-bar"));
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
    $("<a>", {text : name, href : github}).appendTo($(id));
    $("<pre>", {text : res, class : "prettyprint linenums"}).appendTo($(id));

    PR.prettyPrint();
}

/* --- Loading視窗 --- */
function showLoading(){
    $("#loading").removeClass("disable");
}

function closeLoading(){
    $("#loading").addClass("disable");
}