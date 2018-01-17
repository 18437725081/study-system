var selectedTr = null;

function loadTeacher() {
    $.ajax({
        url: '../../manage/getTeacherList.do',
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
            {data: 'username'},
            {data: 'name'},
            {data: 'phone'},
            {data: 'pkTeacher', "visible": false}
        ]
    });
    $('select').select2();
}

function showDialog(title, msg) {
    $("#myModal").find(".modal-header").html(title);
    $("#myModal").attr('class', 'modal');
    $("#myModal").find(".modal-body").html(msg);
    setTimeout(function () {
        $("#myModal").attr('class', 'modal hide');
    }, 3000);
}