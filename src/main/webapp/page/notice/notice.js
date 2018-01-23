var selectedTr = null;

//加载全部通知
function loadNotice() {
    $("#noticeContent").val("");
    selectedTr = null;
    $.ajax({
        url: '../../notice/queryNotice.do',
        type: 'post',
        success: function (data) {
            var res = template('template', data);
            document.getElementById('tab').innerHTML = res;
            showPaging(data);
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
        showDialog("错误", "请选择一条通知！")
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
        success: function (res) {
            $("#noticeContent").html(res.data.noticeContent);
            $("#flag").val(res.data.flag);
            var content = res.data.noticeContent;
            $("#text-count").text(content.length);
        }
    });
}

//提交
function sub() {
    var noticeContent = $("#noticeContent").val();
    if (noticeContent.trim() === null || noticeContent.trim() === "") {
        $("#msgs").html("通知内容不能为空！");
        return false;
    }
    $("#add_notice").ajaxSubmit({
        url: '../../notice/addOrModifyNotice.do',
        type: 'post',
        dataType: "json",
        success: function (res) {
            var _page = window.parent.document.getElementById("page").value;
            window.parent.paging(_page);
            $("#msgs").html(res.msg);
            setTimeout(function () {
                $("#msgs").html("");
            }, 2000);
        }
    });
}

//查询
function query() {
    $("#query_notice").ajaxSubmit({
        url: '../../notice/queryNotice.do',
        type: 'post',
        dataType: "json",
        success: function (data) {
            var res = template('template', data);
            document.getElementById('tab').innerHTML = res;
            showPaging(data);
        }
    });
}

//分页查询
function paging(pageNum) {
    var pageNum = pageNum,
        pageSize = 10,
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
            var res = template('template', data);
            document.getElementById('tab').innerHTML = res;
            showPaging(data);
        }
    });
}