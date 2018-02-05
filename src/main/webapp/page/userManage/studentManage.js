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
        height: '420px',
        width: '400px',
        cancelDisplay: false,
        resize: false
    });
}

//跳转修改学生页面
function modify() {
    if (selectedTr !== null) {
        var pkStudent = selectedTr.childNodes[1].innerHTML;
        art.dialog.data("pkStudent", pkStudent);
        $.dialog.open('studentModify.html', {
            id: "modifyStudent",
            title: "修改学生",
            lock: true,
            height: '420px',
            width: '400px',
            cancelDisplay: false,
            resize: false
        });
    } else {
        swal("提示", "请选择一条信息！")
    }
}

//获取单条学生信息
function getStudent(pkStudent) {
    $.ajax({
        url: '../../manage/getStudentInfo.do',
        data: {
            pkStudent: pkStudent
        },
        type: 'post',
        success: function (data) {
            if (data.status === 10) {
                window.parent.location.href = "../../login.html";
            } else if (data.status === 1) {
                window.parent.swal("错误", data.msg);
            } else {
                $("#username").val(data.data.username);
                $("#name").val(data.data.name);
                $("#studentId").val(data.data.studentId);
                var grade = $("#grade").select2();
                grade.val(data.data.grade).trigger("change");
                tt(data.data.grade, data.data.major);
                var major = $("#major").select2();
                major.val(data.data.major).trigger("change");
            }
        },
        error: function () {
            window.location.href = "../other/error500.html";
        }
    });
}

function tt(grade, major1) {

    $.ajax({
        url: '../../manage/getMajor.do',
        type: 'post',
        data: {
            grade: grade
        },
        success: function (data) {
            if (data.status === 10) {
                window.parent.location.href = "../../login.html";
            } else if (data.status === 1) {
                window.parent.swal("获取专业信息失败", data.msg)
            } else {
                $("#major").empty();
                document.getElementById('major').innerHTML = template('majorModal', data);
                var major = $("#major").select2();
                major.val(major1).trigger("change");
            }
        },
        error: function () {
            window.location.href = "../other/error500.html";
        }
    });
}

//提交
function sub() {
    var username = $("#username").val(),
        name = $("#name").val(),
        studentId = $("#studentId").val(),
        major = $("#major").val();
    if (isNull(username) || isNull(name) || isNull(studentId) || isNull(major)) {
        window.parent.swal("提示", "参数不能为空");
        return false;
    }
    $("#add_student").ajaxSubmit({
        url: '../../manage/addOrUpdateStudent.do',
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

//分页查询
function paging(pageNum) {
    selectedTr = null;
    var username = $("#username").val(),
        name = $("#name").val(),
        studentId = $("#studentId").val(),
        major = $("#major").val();
    $.ajax({
        url: '../../manage/queryStudent.do',
        type: 'post',
        data: {
            pageNum: pageNum,
            username: username,
            name: name,
            studentId: studentId,
            major: major
        },
        success: function (data) {
            if (data.status === 10) {
                window.parent.location.href = "../../login.html";
            } else if (data.status === 1) {
                swal("错误", data.msg)
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