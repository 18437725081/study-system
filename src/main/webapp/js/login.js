//用户登录
function checkLogin() {
    var username = $("#username").val();
    var password = $("#password").val();
    var role = $("#role").val();
    if ($.trim(username) === "" || $.trim(password) === "") {
        swal( "登录失败","请正确填写用户名和密码！");
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
                if (data.status === 0) {
                    window.location.href = "index.html";
                } else {
                    swal( "登录失败", data.msg);
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
                if (data.status === 0) {
                    window.location.href = "indexT.html";
                } else {
                    swal( "登录失败", data.msg);
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
                if (data.status === 0) {
                    window.location.href = "indexS.html";
                } else {
                    swal( "登录失败", data.msg);
                }
            },
            error: function () {
            }
        });
    }
}