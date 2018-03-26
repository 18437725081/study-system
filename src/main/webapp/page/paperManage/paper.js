var selectedTr = null;

//查询框显示隐藏
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
function modifyFlag(pkPaper,flag,url) {
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