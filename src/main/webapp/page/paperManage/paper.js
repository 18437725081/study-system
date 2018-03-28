var selectedTr = null;

//新增试卷框显示隐藏
function addOpen() {
    $("#addBox").toggle();
}

//加载全部通知
function loadPaper() {
    selectedTr = null;
    var url = $("#url").val();
    $.ajax({
        url: url,
        type: 'post',
        success: function (data) {
            if (data.status === 10) {
                window.parent.location.href = "../../login.html";
            } else if (data.status === 1) {
                swal("", data.msg, "error");
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

//新增试卷
function addPaper() {
    selectedTr = null;
    var paperName = $("#addPaperName").val(),
        publicFlag = $("#publicFlag").val();
    if (isNull(paperName) || isNull(publicFlag)) {
        swal("", "参数不能为空", "warning");
        return false;
    }
    $.ajax({
        url: "../../paper/addPaper.do",
        type: 'post',
        dataType: "json",
        data: {
            paperName: paperName,
            publicFlag: publicFlag
        },
        success: function (data) {
            if (data.status === 10) {
                window.parent.location.href = "../../login.html";
            } else if (data.status === 1) {
                swal("", data.msg, "error");
            } else {
                paperName = $("#addPaperName").val("");
                publicFlag = $("#publicFlag").val("");
                toast("success", data.msg);
                var _page = document.getElementById("page").value;
                paging(_page);
            }
        },
        error: function () {
            window.location.href = "../other/error500.html";
        }
    });
}

//修改公开状态或有效状态
function modifyFlag(pkPaper, flag, url) {
    $.ajax({
        url: url,
        type: 'post',
        dataType: "json",
        data: {
            pkPaper: pkPaper,
            flag: flag
        },
        success: function (data) {
            if (data.status === 10) {
                window.parent.location.href = "../../login.html";
            } else if (data.status === 1) {
                swal("", data.msg, "error");
            } else {
                toast("success", data.msg);
                var _page = document.getElementById("page").value;
                paging(_page);
            }
        },
        error: function () {
            window.location.href = "../other/error500.html";
        }
    });
}

//选择试卷
function selectPaper() {
    $.dialog.open('selectPaper.html', {
        id: "myPaperStock",
        title: "选择试卷",
        lock: true,
        height: '460px',
        width: '800px',
        cancelDisplay: false,
        resize: false
    });
}

function sel() {
    if (selectedTr !== null) {
        window.parent.document.getElementById("fkPaper").value = selectedTr.childNodes[1].innerHTML;
        window.parent.document.getElementById("p").value = selectedTr.childNodes[3].innerHTML;
        art.dialog.close();
    } else {
        swal("", "请选择一条信息！", "warning");
    }
}

//选择试题
function selectTests() {
    $.dialog.open('../testManage/selectTests.html', {
        id: "selectTests",
        title: "选择试题",
        lock: true,
        height: '560px',
        width: '1200px',
        cancelDisplay: false,
        resize: false
    });
}

//选择专业
function selectMajor() {
    $.dialog.open('../majorManage/selectMajor.html', {
        id: "selectMajor",
        title: "选择专业",
        lock: true,
        height: '460px',
        width: '800px',
        cancelDisplay: false,
        resize: false
    });
}

//提交
function addPaperTests() {
    var fkPaper = $("#fkPaper").val(),
        fkTests = $("#fkTests").val(),
        score = $("#score").val(),
        priority = $("#priority").val();
    if (isNull(fkPaper) || isNull(fkTests)) {
        swal("", "试卷或试题不能为空!", "warning");
        return;
    }
    var reg = /^(0|[1-9][0-9]{0,2})$/;
    /*定义验证表达式*/
    if (!reg.test(score)) {
        swal("", "成绩必须是不超过三位的数字!", "warning");
        return;
    }
    if (!reg.test(priority)) {
        swal("", "优先级必须是不超过三位的数字!", "warning");
        return;
    }
    $("#addPaperTests").ajaxSubmit({
        url: '../../paper/compositionPaper.do',
        type: 'post',
        dataType: "json",
        success: function (data) {
            if (data.status === 10) {
                location.href = "../../login.html";
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

//提交
function relPaperMajor() {
    var fkPaper = $("#fkPaper").val(),
        fkMajor = $("#fkMajor").val();

    if (isNull(fkPaper) || isNull(fkMajor)) {
        swal("", "试卷或试题不能为空!", "warning");
        return;
    }
    $("#relPaperMajor").ajaxSubmit({
        url: '../../paper/assignmentPaper.do',
        type: 'post',
        dataType: "json",
        success: function (data) {
            if (data.status === 10) {
                location.href = "../../login.html";
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


//分页查询
function paging(pageNum) {
    selectedTr = null;
    var pageSize = 10;
    var url = $("#url").val();
    $.ajax({
        url: url,
        type: 'post',
        data: {
            pageNum: pageNum,
            pageSize: pageSize
        },
        success: function (data) {
            if (data.status === 10) {
                window.parent.location.href = "../../login.html";
            } else if (data.status === 1) {
                swal("", data.msg, "error");
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

//试题编辑
function modifyTests() {
    if (selectedTr !== null) {
        var pkPaper = selectedTr.childNodes[1].innerHTML;
        art.dialog.data("pkPaper", pkPaper);
        $.dialog.open('paperTestsManage.html', {
            id: "paperTestsManage",
            title: "试题编辑",
            lock: true,
            height: '540px',
            width: '880px',
            cancelDisplay: false,
            resize: false
        });
    } else {
        swal("", "请选择一条信息！", "warning");
    }
}

//获取试卷试题
function getTests(pkPaper) {
    selectedTr = null;
    $.ajax({
        url: "../../paper/selectPaperTests.do",
        type: 'post',
        data: {
            fkPaper: pkPaper
        },
        success: function (data) {
            if (data.status === 10) {
                window.parent.location.href = "../../login.html";
            } else if (data.status === 1) {
                swal("", data.msg, "error");
            } else {
                document.getElementById('tab').innerHTML = template('template', data);
            }
        },
        error: function () {
            window.location.href = "../other/error500.html";
        }
    });
}

//清空试卷所有试题
function allRemove() {
    var pkPaper = art.dialog.data('pkPaper');
    window.parent.swal({
            title: "您确定要清空该试卷所有试题吗",
            text: "删除后将无法恢复，请谨慎操作！",
            type: "warning",
            showCancelButton: true,
            confirmButtonColor: "#DD6B55",
            confirmButtonText: "删除",
            closeOnConfirm: false
        },
        function (isConfirm) {
            if (isConfirm) {
                $.ajax({
                    url: '../../paper/emptyTestsFromPaper.do',
                    data: {
                        fkPaper: pkPaper
                    },
                    type: 'post',
                    success: function (data) {
                        window.parent.swal.close();
                        if (data.status === 10) {
                            window.parent.location.href = "../../login.html";
                        } else if (data.status === 1) {
                            window.parent.toast("error", data.msg);
                        } else {
                            window.parent.toast("success", data.msg);
                            getTests(pkPaper);
                        }
                    },
                    error: function () {
                        window.location.href = "../other/error500.html";
                    }
                });
            }
        });
}

//删除试卷试题
function remove(pkTest) {
    var pkPaper = art.dialog.data('pkPaper');
    window.parent.swal({
            title: "您确定要删除这道试题吗",
            text: "删除后将无法恢复，请谨慎操作！",
            type: "warning",
            showCancelButton: true,
            confirmButtonColor: "#DD6B55",
            confirmButtonText: "删除",
            closeOnConfirm: false
        },
        function (isConfirm) {
            if (isConfirm) {
                $.ajax({
                    url: '../../paper/deleteTestsFromPaper.do',
                    data: {
                        fkTest: pkTest,
                        fkPaper:pkPaper
                    },
                    type: 'post',
                    success: function (data) {
                        window.parent.swal.close();
                        if (data.status === 10) {
                            window.parent.location.href = "../../login.html";
                        } else if (data.status === 1) {
                            window.parent.toast("error", data.msg);
                        } else {
                            window.parent.toast("success", data.msg);
                            getTests(pkPaper);
                        }
                    },
                    error: function () {
                        window.location.href = "../other/error500.html";
                    }
                });
            }
        });
}


function preview() {
    if (selectedTr !== null) {
        var pkPaper = selectedTr.childNodes[1].innerHTML;
        art.dialog.data("pkPaper", pkPaper);
        $.dialog.open('paperPreView.html', {
            id: "paperPreView",
            title: "试卷预览",
            lock: true,
            height: '540px',
            width: '990px',
            cancelDisplay: false,
            resize: false
        });
    } else {
        swal("", "请选择一条信息！", "warning");
    }
}

function loadPaperDetail(pkPaper){
    $.ajax({
        url: "../../paper/paperDetail.do",
        type: 'post',
        data:{
            pkPaper:pkPaper
        },
        success: function (data) {
            if (data.status === 10) {
                window.parent.location.href = "../../login.html";
            } else if (data.status === 1) {
                swal("", data.msg, "error");
            } else {
                document.getElementById('content-header').innerHTML = template('template', data);
            }
        },
        error: function () {
            window.location.href = "../other/error500.html";
        }
    });
}