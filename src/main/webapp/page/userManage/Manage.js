var selectedTr = null;

//加载教师信息
function loadTeacher() {
    $("#username").val("");
    $("#name").val("");
    $("#phone").val("");
    selectedTr = null;
    $.ajax({
        url: '../../manage/queryTeacher.do',
        type: 'post',
        success: function (data) {
            if (data.status === 10) {
                window.location.href = "../../login.html";
            }
            document.getElementById('tab').innerHTML = template('template', data);
            showPaging(data);
        },
        error: function () {
            window.location.href = "../other/error500.html";
        }
    });
}

//跳转新增教师页面
function add() {
    $.dialog.open('teacherAdd.html', {
        id: "addTeacher",
        title: "新增教师",
        lock: true,
        height: '300px',
        width: '400px',
        cancelDisplay: false,
        resize: false
    });
}

//跳转修改教师页面
function modify() {
    if (selectedTr !== null) {
        var pkTeacher = selectedTr.childNodes[1].innerHTML;
        art.dialog.data("pkTeacher", pkTeacher);
        $.dialog.open('teacherModify.html', {
            id: "modifyTeacher",
            title: "修改教师",
            lock: true,
            height: '300px',
            width: '400px',
            cancelDisplay: false,
            resize: false
        });
    } else {
        showDialog("错误", "请选择一条信息！")
    }
}

//删除教师
function remove() {
    if (selectedTr !== null) {
        var pkTeacher = selectedTr.childNodes[1].innerHTML;
        $.ajax({
            url: '../../manage/delTeacher.do',
            data: {
                pkTeacher: pkTeacher
            },
            type: 'post',
            success: function (data) {
                if (data.status === 10) {
                    window.location.href = "../../login.html";
                } else {
                    showDialog("信息", data.msg)
                    if (data.status === 0) {
                        var _page = document.getElementById("page").value;
                        paging(_page);
                    }
                }
            },
            error: function () {
                window.location.href = "../other/error500.html";
            }
        });
    } else {
        showDialog("错误", "请选择一条信息！")
    }
}

//获取单条专业信息
function getTeacher(pkTeacher) {
    $.ajax({
        url: '../../manage/getTeacherInfo.do',
        data: {
            pkTeacher: pkTeacher
        },
        type: 'post',
        success: function (data) {
            if (data.status === 10) {
                window.location.href = "../../login.html";
            }
            $("#username").val(data.data.username);
            $("#name").val(data.data.name);
            $("#phone").val(data.data.phone);
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
        phone = $("#phone").val();
    if (isNull(username) || isNull(name) || isNull(phone)) {
        msg("参数不能为空");
        return false;
    }
    $("#add_teacher").ajaxSubmit({
        url: '../../manage/addOrUpdateTeacher.do',
        type: 'post',
        dataType: "json",
        success: function (data) {
            if (data.status === 10) {
                window.location.href = "../../login.html";
            }
            var _page = window.parent.document.getElementById("page").value;
            window.parent.paging(_page);
            msg(data.msg);
        },
        error: function () {
            window.location.href = "../other/error500.html";
        }
    });
}

//查询
function query() {
    selectedTr = null;
    $("#query").ajaxSubmit({
        url: '../../manage/queryTeacher.do',
        type: 'post',
        dataType: "json",
        success: function (data) {
            if (data.status === 10) {
                window.location.href = "../../login.html";
            }
            document.getElementById('tab').innerHTML = template('template', data);
            showPaging(data);
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
        phone = $("#phone").val();
    $.ajax({
        url: '../../manage/queryTeacher.do',
        type: 'post',
        data: {
            pageNum: pageNum,
            username: username,
            name: name,
            phone: phone
        },
        success: function (data) {
            if (data.status === 10) {
                window.location.href = "../../login.html";
            }
            document.getElementById('tab').innerHTML = template('template', data);
            showPaging(data);
        },
        error: function () {
            window.location.href = "../other/error500.html";
        }
    });
}

function addMajor() {
    $.dialog.open('teacher_related_major.html', {
        id: "teacher_related_major",
        title: "关联专业",
        lock: true,
        height: '330px',
        width: '400px',
        cancelDisplay: false,
        resize: false
    });
}

function getGrade() {
    $.ajax({
        url: '../../manage/getGrade.do',
        type: 'post',
        success: function (data) {
            if (data.status === 10) {
                window.location.href = "../../login.html";
            }
            document.getElementById('grade').innerHTML = template('gradeModal', data);
        },
        error: function () {
            window.location.href = "../other/error500.html";
        }
    });
}

$("#grade").change(function () {
    $.ajax({
        url: '../../manage/getMajor.do',
        type: 'post',
        data: {
            grade: $("#grade").val()
        },
        success: function (data) {
            if (data.status === 10) {
                window.location.href = "../../login.html";
            }
            document.getElementById('major').innerHTML = template('gradeModal', data);
        },
        error: function () {
            window.location.href = "../other/error500.html";
        }
    });
});
