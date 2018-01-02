var selectedTr = null;

$(function () {

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
});


function table(data) {
    $('.data-table').dataTable({
        "bJQueryUI": true,
        "sPaginationType": "full_numbers",
        "sDom": '<""l>t<"F"fp>',
        data: data.data,
        columns: [
            {data: 'noticeContent'},
            {data: 'flag'},
            {data: 'pkNotice',"visible": false},
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

function add() {

}

function modify() {

}

function del() {

}


/*测试dialog*/
$('#dialog').click(function () {
    $.dialog.open('add.html', {
        title: "666",
        cancel: true,
        lock: true,
        height: '450px',
        width: '550px'
    });
});