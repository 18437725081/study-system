//信息提示
function showDialog(title, msg) {
    var _myModal = $("#myModal");
    _myModal.find(".modal-header").html(title);
    _myModal.attr('class', 'modal');
    _myModal.find(".modal-body").html(msg);
    setTimeout(function () {
        _myModal.attr('class', 'modal hide');
    }, 2000);
}

//用户登录
function checkLogin() {
    var username = $("#username").val();
    var password = $("#password").val();
    var role = $("#role").val();
    if ($.trim(username) === "" || $.trim(password) === "") {
        showDialog("登陆信息", "<span style='color:red;'>请正确填写用户名和密码！</span>");
        return false;
    }
    //管理员登录
    if (role == 0) {
        $.ajax({
            url: 'manage/login.do',
            data: {
                username: username,
                password: password
            },
            type: 'post',
            success: function (data) {
                if (data.status === 10) {
                    window.location.href = "login.html";
                }
                if (data.status === 0) {
                    window.location.href = "index.html";
                } else {
                    showDialog("登陆信息", "<span style='color:red;'>" + data.msg + "</span>");
                }
            },
            error: function () {
            }
        });
    }

    //教师登录
    if (role == 1) {
        $.ajax({
            url: 'teacher/login.do',
            data: {
                username: username,
                password: password
            },
            type: 'post',
            success: function (data) {
                if (data.status === 10) {
                    window.location.href = "login.html";
                }
                if (data.status === 0) {
                    window.location.href = "indexT.html";
                } else {
                    showDialog("登陆信息", "<span style='color:red;'>" + data.msg + "</span>");
                }
            },
            error: function () {
            }
        });
    }

    //学生登录
    if (role == 2) {
        $.ajax({
            url: 'student/login.do',
            data: {
                username: username,
                password: password
            },
            type: 'post',
            success: function (data) {
                if (data.status === 10) {
                    window.location.href = "login.html";
                }
                if (data.status === 0) {
                    window.location.href = "indexS.html";
                } else {
                    showDialog("登陆信息", "<span style='color:red;'>" + data.msg + "</span>");
                }
            },
            error: function () {
            }
        });
    }
}