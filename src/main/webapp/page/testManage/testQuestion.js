var selectedTr = null;

//加载试题数据
function loadTest() {
    selectedTr = null;
    $("#testTitle").val("");
    $("#testContent").val("");
    var testType = $("#testType").select2();
    testType.val("").trigger("change");
    var testSubject = $("#testSubject").select2();
    testSubject.val("").trigger("change");
    var url = $("#url").val();
    $.ajax({
        url: url,
        type: 'post',
        success: function (data) {
            if (data.status === 10) {
                window.parent.location.href = "../../login.html";
            } else if (data.status === 1) {
                swal("", data.msg,"warning");
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
        swal("", "请选择一条试题！", "error")
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
                window.parent.swal("", data.msg,"error");
            } else {
                setDetail(data.data);
            }
        },
        error: function () {
            window.location.href = "../other/error500.html";
        }
    });
}

//新增试题提交
function sub() {
    var data = checkAddTestParameter();
    if (!data.flag) {
        swal("", "请填完整的题目信息", "warning");
        return false;
    }
    $.ajax({
        url: '../../tests/addTest.do',
        type: 'post',
        data: data,
        success: function (data) {
            if (data.status === 10) {
                window.location.href = "../../login.html";
            } else if (data.status === 1) {
                toast("error", data.msg)
            } else {
                toast("success", data.msg)
            }
        },
        error: function () {
            window.location.href = "../other/error500.html";
        }
    });
}

//更改试题状态
function alterFlag(flag) {
    if (selectedTr !== null) {
        var pkTest = selectedTr.childNodes[1].innerHTML;
        $.ajax({
            url: '../../tests/modifyTestFlag.do',
            type: 'post',
            data: {
                pkTest:pkTest,
                flag:flag
            },
            success: function (data) {
                if (data.status === 10) {
                    window.location.href = "../../login.html";
                } else if (data.status === 1) {
                    toast("error", data.msg)
                } else {
                    var _page = document.getElementById("page").value;
                    paging(_page);
                    toast("success", data.msg)
                }
            },
            error: function () {
                window.location.href = "../other/error500.html";
            }
        });
    } else {
        swal("", "请选择一条试题！", "error")
    }
}


//查询
function query() {
    selectedTr = null;
    $("#testContent").val($("#testTitle").val());
    var url = $("#url").val();
    $("#query").ajaxSubmit({
        url: url,
        type: 'post',
        dataType: "json",
        success: function (data) {
            if (data.status === 10) {
                window.parent.location.href = "../../login.html";
            } else if (data.status === 1) {
                swal("", data.msg,"error");
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

//设置试题详情
function setDetail(data) {
    var spans = $("span");
    spans[0].innerHTML = data.testType;
    spans[1].innerHTML = data.testSubject;
    spans[2].innerHTML = data.testTitle;

    var contents = data.testContent.split(";");
    spans[3].innerHTML = contents[0] + ";";
    spans[4].innerHTML = contents[1] + ";";
    spans[5].innerHTML = contents[2] + ";";
    spans[6].innerHTML = contents[3] + ";";

    spans[7].innerHTML = data.testAnswer;
    spans[8].innerHTML = data.testAnalyze;
    spans[9].innerHTML = data.createdBy;
    spans[10].innerHTML = data.lastUpdatedTime;
}

//新增试题参数校验
function checkAddTestParameter() {
    var flag = true;
    var testType = $("#testType").val(),
        testSubject = $("#testSubject").val(),
        testTitle = $("#testTitle").val(),
        testContent = "",
        testAnswer = $("#testAnswer").val(),
        testAnalyze = $("#testAnalyze").val();
    if (isNull(testTitle)) {
        flag = false;
    }
    if (isNull(testAnswer)) {
        flag = false;
    }
    if (isNull(testAnalyze)) {
        flag = false;
    }
    if (isNull(testSubject)) {
        testSubject = $('input[name=testSubject]').val();
        if (isNull(testSubject)) {
            flag = false;
        }
    }
    var options = $("textarea[name=option]");
    for (var i = 0; i < options.length; i++) {
        if (isNull(options[i].value)) {
            flag = false;
        }
        testContent = testContent === "" ? options[i].value : testContent + ";" + options[i].value;
    }
    return {
        flag: flag,
        testType: testType,
        testSubject: testSubject,
        testTitle: testTitle,
        testContent: testContent,
        testAnswer: testAnswer,
        testAnalyze: testAnalyze
    };
}


function remind() {
    swal("", "此题型暂时无法创建，请等待后续通知", "warning")
}

//新增选择题
$('#Select').click(function () {
    $("#one").fadeIn();
    $("#two").hide();
    $("#p").hide();
});

//新增判断题
$('#Judgment').click(function () {
    $("#one").hide();
    $("#two").fadeIn();
    $("#p").hide();
    remind();
});

//自定义学科
function zdy() {
    $("#subject").toggle();
    $("#userDefinedSubject").toggle();
    var testSubject = $("#testSubject").select2();
    testSubject.val("").trigger("change");
    $("input[name=testSubject]").val("");
}

//分页查询
function paging(pageNum) {
    selectedTr = null;
    $("#testContent").val($("#testTitle").val());
    var testContent = $("#testContent").val(),
        testTitle = $("#testTitle").val(),
        testSubject = $("#testTitle").val(),
        testType = $("#testType").val();
    var url = $("#url").val();
    $.ajax({
        url: url,
        type: 'post',
        data: {
            pageNum: pageNum,
            testContent:testContent,
            testTitle:testTitle,
            testSubject:testSubject,
            testType:testType
        },
        success: function (data) {
            if (data.status === 10) {
                window.parent.location.href = "../../login.html";
            } else if (data.status === 1) {
                swal("", data.msg,"error");
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

//获取学科列表
function getSubject() {
    $.ajax({
        url: '../../tests/selectSubjectList.do',
        type: 'post',
        success: function (data) {
            if (data.status === 10) {
                window.parent.location.href = "../../login.html";
            } else if (data.status === 1) {
                swal("获取信息失败", data.msg)
            } else {
                document.getElementById('testSubject').innerHTML = template('subjectModel', data);
            }
        },
        error: function () {
            window.location.href = "../other/error500.html";
        }
    });
}