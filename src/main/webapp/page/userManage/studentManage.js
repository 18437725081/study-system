var selectedTr = null;

//加载学生信息
function loadStudent() {
    $("#username").val("");
    $("#name").val("");
    $("#studentId").val("");
    var grade = $("#grade").select2();
    grade.val(1).trigger("change");
    var major = $("#major").select2();
    major.val(1).trigger("change");
    selectedTr = null;
    $.ajax({
        url: '../../manage/queryStudent.do',
        type: 'post',
        success: function (data) {
            if (data.status === 10) {
                window.parent.location.href = "../../login.html";
            } else if (data.status === 1) {
                swal("", data.msg,"warning");
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
        swal("", "请选择一条信息！","error")
    }
}

//删除学生
function remove() {
    if (selectedTr !== null) {
        swal({
                title: "您确定要删除这条信息吗",
                text: "删除后将无法恢复，请谨慎操作！",
                type: "warning",
                showCancelButton: true,
                confirmButtonColor: "#DD6B55",
                confirmButtonText: "删除",
                closeOnConfirm: false
            },
            function (isConfirm) {
                if (isConfirm) {
                    var pkStudent = selectedTr.childNodes[1].innerHTML;
                    $.ajax({
                        url: '../../manage/delStudent.do',
                        data: {
                            pkStudent: pkStudent
                        },
                        type: 'post',
                        success: function (data) {
                            swal.close();
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
                }
            });
    } else {
        swal("", "请选择一条信息！","error")
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
                window.parent.swal("", data.msg,"error");
            } else {
                $("#username").val(data.data.username);
                $("#name").val(data.data.name);
                $("#studentId").val(data.data.studentId);
                $("#grade").select2().val(data.data.grade).trigger("change");
                $("#majorValue").val(data.data.pkMajor);
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
        window.parent.swal("", "参数不能为空","warning");
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

//查询
function query() {
    selectedTr = null;
    $("#query").ajaxSubmit({
        url: '../../manage/queryStudent.do',
        type: 'post',
        dataType: "json",
        success: function (data) {
            if (data.status === 10) {
                window.parent.location.href = "../../login.html";
            } else if (data.status === 1) {
                swal("", data.msg,"error");
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
                swal("", data.msg,"error");
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