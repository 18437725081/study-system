var selectedTr = null;

function loadNotice() {
    $.ajax({
        url: '../../notice/manageNotice.do',
        type: 'post',
        success: function (res) {
            table(res);
        },
        error: function () {
        }
    });

    $('tbody').on('click', 'tr', function () {
        this.style.backgroundColor = "#a1a1a8";
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
            {data: 'pkNotice', "visible": false},
        ]
    });
    $('select').select2();
}

/*得到选中行的第一列的值*/
function query() {
    if (selectedTr != null) {
        var id = $('.data-table').DataTable().row(selectedTr).data().pkNotice;
        alert(id);
    } else {
        alert("请选择一行");
    }
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
    if (selectedTr != null) {
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
        alert("请选择一行");
    }
}

function del() {

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
