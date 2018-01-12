var selectedTr = null;

function loadNotice() {
    $.ajax({
        url: '../../notice/manageNotice.do',
        type: 'post',
        success: function (res) {
            table(res);
        }
    });

    $('tbody').on('click', 'tr', function () {
        this.style.backgroundColor = "#b4b4bb";
        if (selectedTr !== null)
            selectedTr.style.backgroundColor = "#f9f9f9";
        if (selectedTr === this)
        //加上此句，以控制点击变白，再点击反灰
            selectedTr = null;
        else
            selectedTr = this;
    });
}


function table(data) {
    $('.data-table').dataTable({
        "bJQueryUI": true,
        "sPaginationType": "full_numbers",
        "sDom": '<""l>t<"F"fp>',
        data: data.data,
        columns: [
            {data: 'noticeContent'},
            {data: 'flag'},
            {data: 'pkNotice', "visible": false}
        ]
    });
    $('select').select2();
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
        var pkNotice = $('.data-table').DataTable().row(selectedTr).data().pkNotice;
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

function back() {
    window.parent.location.reload();
    art.dialog.close();
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
            $("#msgs").html(res.msg);
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

function showDialog(title, msg) {
    $("#myModal").find(".modal-header").html(title);
    $("#myModal").attr('class', 'modal');
    $("#myModal").find(".modal-body").html(msg);
    setTimeout(function () {
        $("#myModal").attr('class', 'modal hide');
    }, 3000);
}