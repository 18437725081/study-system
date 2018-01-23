var selectedTr = null;

//加载教师信息
function loadTeacher() {
    selectedTr = null;
    $.ajax({
        url: '../../manage/queryTeacher.do',
        type: 'post',
        success: function (data) {
            if (data.status === 10){
                window.location.href = "../../login.html";
            }
            var res = template('template', data);
            document.getElementById('tab').innerHTML = res;
            showPaging(data);
        },
        error:function () {
            window.location.href = "../other/error500.html";
        }
    });
}

//跳转新增教师页面
function add() {
    $.dialog.open('addTeacher.html', {
        id: "addTeacher",
        title: "新增教师",
        lock: true,
        height: '400px',
        width: '400px',
        cancelDisplay: false,
        resize: false
    });
}

//跳转修改通知页面
function modify() {
    if (selectedTr !== null) {
        var pkTeacher = selectedTr.childNodes[1].innerHTML;
        art.dialog.data("pkTeacher", pkTeacher);
        $.dialog.open('modifyTeacher.html', {
            id: "modifyTeacher",
            title: "修改教师",
            lock: true,
            height: '300px',
            width: '850px',
            cancelDisplay: false,
            resize: false
        });
    } else {
        showDialog("错误", "请选择一个教师！")
    }
}

//提交
function sub() {
    // var noticeContent = $("#noticeContent").val();
    // if (noticeContent.trim() === null || noticeContent.trim() === "") {
    //     $("#msgs").html("通知内容不能为空！");
    //     return false;
    // }
    $("#add_teacher").ajaxSubmit({
        url: '../../manage/addOrUpdateTeacher.do',
        type: 'post',
        dataType: "json",
        success: function (data) {
            if (data.status === 10){
                window.location.href = "../../login.html";
            }
            //var _page = window.parent.document.getElementById("page").value;
            //window.parent.paging(_page);
            $("#msgs").html(data.msg);
            setTimeout(function () {
                $("#msgs").html("");
            }, 2000);
        },
        error:function () {
            window.location.href = "../other/error500.html";
        }
    });
}