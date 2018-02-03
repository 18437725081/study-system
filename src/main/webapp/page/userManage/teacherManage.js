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
        swal("提示", "请选择一条信息！")
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
                    window.parent.location.href = "../../login.html";
                } else if (data.status === 1) {
                    toast("error", data.msg);
                } else {
                    toast("success", data.msg);
                    var _page = document.getElementById("page").value;
                    paging(_page);

                }
            },
            error: function () {
                window.location.href = "../other/error500.html";
            }
        });
    } else {
        swal("错误", "请选择一条信息！")
    }
}

//获取单条教师信息
function getTeacher(pkTeacher) {
    $.ajax({
        url: '../../manage/getTeacherInfo.do',
        data: {
            pkTeacher: pkTeacher
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
                $("#phone").val(data.data.phone);
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

//查询
function query() {
    selectedTr = null;
    $("#query").ajaxSubmit({
        url: '../../manage/queryTeacher.do',
        type: 'post',
        dataType: "json",
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

//跳转至教师关联专业页面
function addMajor(pkTeacher) {
    art.dialog.data("pkTeacher", pkTeacher);
    $.dialog.open('teacher_related_major.html', {
        id: "teacher_related_major",
        title: "关联专业",
        lock: true,
        height: '550px',
        width: '780px',
        cancelDisplay: false,
        resize: false
    });
}

//获取年级
function getGrade() {
    $.ajax({
        url: '../../manage/getGrade.do',
        type: 'post',
        success: function (data) {
            if (data.status === 10) {
                window.parent.location.href = "../../login.html";
            } else if (data.status === 1) {
                window.parent.swal("获取年级信息失败", data.msg)
            } else {
                document.getElementById('grade').innerHTML = template('gradeModal', data);
            }
        },
        error: function () {
            window.location.href = "../other/error500.html";
        }
    });
}

//获取专业
$("#grade").change(function () {
    $.ajax({
        url: '../../manage/getMajor.do',
        type: 'post',
        data: {
            grade: $("#grade").val()
        },
        success: function (data) {
            if (data.status === 10) {
                window.parent.location.href = "../../login.html";
            } else if (data.status === 1) {
                window.parent.swal("获取专业信息失败", data.msg)
            } else {
                document.getElementById('major').innerHTML = template('majorModal', data);
            }
        },
        error: function () {
            window.location.href = "../other/error500.html";
        }
    });
});

//获取教师关联的专业
function getTeacherMajor(pkTeacher) {
    $.ajax({
        url: '../../manage/getTeacherMajor.do',
        type: 'post',
        data: {
            pkTeacher: pkTeacher
        },
        success: function (data) {
            if (data.status === 10) {
                window.parent.parent.location.href = "../../login.html";
            } else if (data.status === 1) {
                window.parent.swal("提示", data.msg);
            } else {
                document.getElementById('tab').innerHTML = template('majorTable', data);
                showPaging(data);
            }
        },
        error: function () {
            window.location.href = "../other/error500.html";
        }
    });
}

//关联教师和专业
function related() {
    var major = $("#major").val(),
        pkTeacher = $("#pkTeacher").val();
    if (isNull(major) || isNull(pkTeacher)){
        window.parent.swal("提示","请选择年级和专业");
    }
    $("#rel_major").ajaxSubmit({
        url: '../../manage/addRelTeacherMajor.do',
        type: 'post',
        dataType: "json",
        success: function (data) {
            if (data.status === 10) {
                window.parent.location.href = "../../login.html";
            } else if (data.status === 1) {
                window.parent.toast("error", data.msg);
            } else {
                window.parent.toast("success", data.msg);
                getTeacherMajor($("#pkTeacher").val());
            }
        },
        error: function () {
            window.location.href = "../other/error500.html";
        }
    });
}

//删除教师关联的班级
function del(pkMajor) {
    var pkTeacher = $("#pkTeacher").val();
    $.ajax({
        url: '../../manage/delRelTeacherMajor.do',
        type: 'post',
        data: {
            pkMajor: pkMajor,
            pkTeacher: pkTeacher
        },
        success: function (data) {
            if (data.status === 10) {
                window.parent.parent.location.href = "../../login.html";
            } else if (data.status === 1) {
                window.parent.toast("error", data.msg);
            } else {
                window.parent.toast("success", data.msg);
                getTeacherMajor(pkTeacher);
            }
        },
        error: function () {
            window.location.href = "../other/error500.html";
        }
    });
}