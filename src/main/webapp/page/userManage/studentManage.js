var selectedTr = null;

//加载教师信息
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