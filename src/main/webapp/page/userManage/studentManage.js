var selectedTr = null;

//加载学生信息
function loadStudent() {
    $("#username").val("");
    $("#name").val("");
    $("#phone").val("");
    selectedTr = null;
    $.ajax({
        url: '../../manage/queryStudent.do',
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

//跳转新增学生页面
function add() {
    $.dialog.open('studentAdd.html', {
        id: "studentAdd",
        title: "新增学生",
        lock: true,
        height: '500px',
        width: '400px',
        cancelDisplay: false,
        resize: false
    });
}