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
            var res = template('template', data);
            document.getElementById('tab').innerHTML = res;
            showPaging(data);
        },
        error: function () {
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
        height: '350px',
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
        $.dialog.open('modifyTeacher.html', {
            id: "modifyTeacher",
            title: "修改教师",
            lock: true,
            height: '350px',
            width: '400px',
            cancelDisplay: false,
            resize: false
        });
    } else {
        showDialog("错误", "请选择一条信息！")
    }
}

//获取单条专业信息
function getMajor(pkMajor) {
    $.ajax({
        url: '../../manage/getMajorInfo.do',
        data: {
            pkMajor: pkMajor
        },
        type: 'post',
        success: function (data) {
            if (data.status === 10){
                window.location.href = "../../login.html";
            }
            $("#grade").val(data.data.grade);
            $("#major").val(data.data.major);
        },
        error:function () {
            window.location.href = "../other/error500.html";
        }
    });
}

//提交
function sub() {
    var username = $("#username").val(),
        password = $("#password").val(),
        name = $("#name").val(),
        phone = $("#phone").val();
    if (isNull(username) || isNull(password) || isNull(name) || isNull(phone)) {
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
            _page = window.parent.document.getElementById("page").value;
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
            if (data.status === 10){
                window.location.href = "../../login.html";
            }
            document.getElementById('tab').innerHTML = template('template', data);
            showPaging(data);
        },
        error:function () {
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
            username:username,
            name:name,
            phone:phone
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
