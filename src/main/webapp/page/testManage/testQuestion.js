var selectedTr = null;

//加载试题数据
function loadTest() {
    selectedTr = null;
    $.ajax({
        url: '../../tests/queryTests.do',
        type: 'post',
        success: function (data) {
            if (data.status === 10) {
                window.parent.location.href = "../../login.html";
            } else if (data.status === 1) {
                swal("提示", data.msg);
            } else {
                document.getElementById('tab').innerHTML = template('template', data);
                showPaging(data);
            }
        },
        error: function () {
            window.location.href = "../other/error500.html";
        }
    });
}

//跳转试题详情
function details() {
    if (selectedTr !== null) {
        var pkTest = selectedTr.childNodes[1].innerHTML;
        art.dialog.data("pkTest", pkTest);
        $.dialog.open('testQuestionDetails.html', {
            id: "testQuestionDetails",
            title: "试题详情",
            lock: true,
            height: '520px',
            width: '850px',
            cancelDisplay: false,
            resize: false
        });
    } else {
        swal("", "请选择一条试题！","error")
    }
}

//获取试题详情信息
function getDetail(pkTest) {
    $.ajax({
        url: '../../tests/getTestsInfo.do',
        data: {
            pkTest: pkTest
        },
        type: 'post',
        success: function (data) {
            if (data.status === 10) {
                window.parent.location.href = "../../login.html";
            } else if (data.status === 1) {
                window.parent.swal("错误", data.msg);
            } else {
                setDetail(data.data);
            }
        },
        error: function () {
            window.location.href = "../other/error500.html";
        }
    });
}

//设置试题详情
function setDetail(data) {
    var spans = $("span");
    spans[0].innerHTML=data.testType;
    spans[1].innerHTML=data.testSubject;
    spans[2].innerHTML=data.testTitle;

    var contents = data.testContent.split(";");
    spans[3].innerHTML=contents[0]+";";
    spans[4].innerHTML=contents[1]+";";
    spans[5].innerHTML=contents[2]+";";
    spans[6].innerHTML=contents[3]+";";

    spans[7].innerHTML=data.testAnswer;
    spans[8].innerHTML=data.testAnalyze;
    spans[9].innerHTML=data.createdBy;
    spans[10].innerHTML=data.lastUpdatedTime;
}


function remind() {
    swal("", "此题型暂时无法创建，请等待后续通知", "warning")
}

$('#addSelect').click(function () {
    $("#two").slideUp();
    $("#one").fadeIn();
});

$('#aa').click(function () {
    $("#one").slideUp();
    $("#two").fadeIn();
    remind();
});