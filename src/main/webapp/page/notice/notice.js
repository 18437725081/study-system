var selectedTr = null;

function loadNotice() {
    $.ajax({
        url: '../../notice/manageNotice.do',
        type: 'post',
        success: function (data) {
            var res = template('template', data);
            document.getElementById('tab').innerHTML = res;
            selectTr();
            showPaging(data);
        }
    });
}

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
        showDialog("错误","请选择一条通知！")
    }
}

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

function sub() {
    var noticeContent = $("#noticeContent").val();
    if (noticeContent.trim() === null || noticeContent.trim() === ""){
        $("#msgs").html("通知内容不能为空！");
        return false;
    }
    $("#add_notice").ajaxSubmit({
        url: '../../notice/addOrModifyNotice.do',
        type: 'post',
        dataType: "json",
        success: function (res) {
            window.parent.loadNotice();
            window.parent.selectTr();
            $("#msgs").html(res.msg);
            setTimeout(function () {
                $("#msgs").html("");
            },2000);
        }
    });
}


function query(){
    $("#query_notice").ajaxSubmit({
        url: '../../notice/queryNotice.do',
        type: 'post',
        dataType: "json",
        success: function (data) {
            var res = template('template', data);
            document.getElementById('tab').innerHTML = res;
            selectTr();
        }
    });
}

function paging(obj) {
    var pageNum = obj,
        pageSize = 10;
    $.ajax({
        url: '../../notice/manageNotice.do',
        type: 'post',
        data: {
            pageNum: pageNum,
            pageSize: pageSize
        },
        success: function (data) {
            var res = template('template', data);
            document.getElementById('tab').innerHTML = res;
            selectTr();
            showPaging(data);
        }
    });
}