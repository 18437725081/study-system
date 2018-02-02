var selectedTr = null;

//加载全部通知
function loadNotice() {
    $("#noticeContent").val("");
    selectedTr = null;
    $.ajax({
        url: '../../notice/queryNotice.do',
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

//跳转新增通知页面
function addNotice() {
    $.dialog.open('add.html', {
        id: "addNotice",
        title: "新增通知",
        lock: true,
        height: '300px',
        width: '850px',
        cancelDisplay: false,
        resize: false
    });
}

//跳转修改通知页面
function modify() {
    if (selectedTr !== null) {
        var pkNotice = selectedTr.childNodes[1].innerHTML;
        art.dialog.data("pkNotice", pkNotice);
        $.dialog.open('modify.html', {
            id: "addNotice",
            title: "修改通知",
            lock: true,
            height: '300px',
            width: '850px',
            cancelDisplay: false,
            resize: false
        });
    } else {
        swal("错误", "请选择一条通知！");
    }
}

//获取单条通知信息
function getNotice(pkNotice) {
    $.ajax({
        url: '../../notice/getNotice.do',
        data: {
            pkNotice: pkNotice
        },
        type: 'post',
        success: function (data) {
            if (data.status === 10) {
                window.parent.location.href = "../../login.html";
            } else if (data.status === 1) {
                //swal("获取通知内容失败", data.msg);
            } else {
                $("#noticeContent").html(data.data.noticeContent);
                $("#flag").val(data.data.flag);
                var content = data.data.noticeContent;
                $("#text-count").text(content.length);
            }
        },
        error: function () {
            window.location.href = "../other/error500.html";
        }
    });
}

//提交
function sub() {
    var noticeContent = $("#noticeContent").val();
    if (noticeContent.trim() === null || noticeContent.trim() === "") {
        swal("提示", "通知内容不能为空！");
        return false;
    }
    $("#add_notice").ajaxSubmit({
        url: '../../notice/addOrModifyNotice.do',
        type: 'post',
        dataType: "json",
        success: function (data) {
            if (data.status === 10) {
                window.parent.location.href = "../../login.html";
            } else if (data.status === 1) {
                window.parent.toast("error", data.msg)
            } else {
                var _page = window.parent.document.getElementById("page").value;
                window.parent.paging(_page);
                window.parent.toast("success", data.msg)
            }
        },
        error: function () {
            window.location.href = "../other/error500.html";
        }
    });
}

//查询
function query() {
    selectedTr = null;
    $("#query_notice").ajaxSubmit({
        url: '../../notice/queryNotice.do',
        type: 'post',
        dataType: "json",
        success: function (data) {
            if (data.status === 10) {
                window.parent.location.href = "../../login.html";
            } else if (data.status === 1) {
                toast("error", data.msg)
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

//分页查询
function paging(pageNum) {
    selectedTr = null;
    var pageSize = 10,
        noticeContent = $("#noticeContent").val();
    $.ajax({
        url: '../../notice/queryNotice.do',
        type: 'post',
        data: {
            pageNum: pageNum,
            pageSize: pageSize,
            noticeContent: noticeContent
        },
        success: function (data) {
            if (data.status === 10) {
                window.parent.location.href = "../../login.html";
            } else if (data.status === 1) {
                toast("error", data.msg)
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

$(function () {
    /*字数限制*/
    $("#noticeContent").on("input propertychange", function () {
        var $this = $(this),
            _val = $this.val(),
            count;
        if (_val.length > 175) {
            $this.val(_val.substring(0, 175));
        }
        count = $this.val().length;
        $("#text-count").text(count);
    });
});