var selectedTr = null;

//加载全部通知
function loadMajor() {
    $("#grade").val("");
    $("#major").val("");
    selectedTr = null;
    $.ajax({
        url: '../../manage/queryMajor.do',
        type: 'post',
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

//跳转新增通知页面
function add() {
    $.dialog.open('add.html', {
        id: "addMajor",
        title: "新增专业",
        lock: true,
        height: '240px',
        width: '400px',
        cancelDisplay: false,
        resize: false
    });
}

//跳转修改专业页面
function modify() {
    if (selectedTr !== null) {
        var pkMajor = selectedTr.childNodes[1].innerHTML;
        art.dialog.data("pkMajor", pkMajor);
        $.dialog.open('modify.html', {
            id: "addNotice",
            title: "修改专业",
            lock: true,
            height: '240px',
            width: '400px',
            cancelDisplay: false,
            resize: false
        });
    } else {
        showDialog("错误", "请选择一条信息！")
    }
}

//删除专业
function remove() {
    if (selectedTr !== null) {
        var pkMajor = selectedTr.childNodes[1].innerHTML;
        $.ajax({
            url: '../../manage/delMajor.do',
            data: {
                pkMajor: pkMajor
            },
            type: 'post',
            success: function (data) {
                if (data.status === 10) {
                    window.location.href = "../../login.html";
                } else {
                    showDialog("信息",data.msg)
                    if (data.status === 0) {
                        var _page = document.getElementById("page").value;
                        paging(_page);
                    }
                }
            },
            error:function () {
                window.location.href = "../other/error500.html";
            }
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
    var major = $("#major").val();
    var grade = $("#grade").val();
    if (isNull(major) || isNull(grade)) {
        msg("年级或专业不能为空");
        return false;
    }
    $("#add_Major").ajaxSubmit({
        url: '../../manage/addOrUpdateMajor.do',
        type: 'post',
        dataType: "json",
        success: function (data) {
            if (data.status === 10){
                window.location.href = "../../login.html";
            }
            var _page = window.parent.document.getElementById("page").value;
            window.parent.paging(_page);
            msg(data.msg);
        },
        error:function () {
            window.location.href = "../other/error500.html";
        }
    });
}

//查询
function query() {
    selectedTr = null;
    $("#query").ajaxSubmit({
        url: '../../manage/queryMajor.do',
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
    var pageSize = 10,
        grade = $("#grade").val(),
        major = $("#major").val();
    $.ajax({
        url: '../../manage/queryMajor.do',
        type: 'post',
        data: {
            pageNum: pageNum,
            pageSize: pageSize,
            grade: grade,
            major:major
        },
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