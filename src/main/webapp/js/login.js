//用户登录
function checkLogin() {
    var username = $("#username").val();
    var password = $("#password").val();
    var role = $("#role").val();
    if ($.trim(username) === "" || $.trim(password) === "") {
        swal("", "请正确填写用户名和密码！", "warning");
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
                    swal("", data.msg, "error");
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
                    swal("", data.msg, "error");
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
                    swal("", data.msg, "error");
                }
            },
            error: function () {
            }
        });
    }
}

$(document).ready(function () {

    $('#to-recover').click(function () {
        $("#loginform").slideUp();
        $("#recoverform").fadeIn();
    });

    $('#to-login').click(function () {
        $("#recoverform").hide();
        $("#loginform").fadeIn();
    });
});

function tologin() {
    $("#loginform").fadeIn();
    $("#checkAnswer").hide();
    $("#resetPwd").hide();
}

function getQuestion() {
    var name = $("#name").val(),
        role = $("#role2").val();
    if ($.trim(name) === "") {
        swal("", "请正确填写用户名！", "warning");
        return false;
    }
    if (isNull(role)) {
        swal("", "请选择身份！", "warning");
        return false;
    }
    //教师
    if (role == 1) {
        $.ajax({
            url: 'teacher/forgetGetQuestion.do',
            data: {
                username: name
            },
            type: 'post',
            success: function (data) {
                if (data.status === 0) {
                    $("#question").val(data.data);
                    $("#recoverform").hide();
                    $("#checkAnswer").fadeIn();
                } else {
                    swal("", data.msg, "warning");
                }
            },
            error: function () {
            }
        });
    }
}

function checkQuestion() {
    var username = $("#name").val(),
        question = $("#question").val(),
        answer = $("#answer").val(),
        role = $("#role2").val();
    if (isNull(answer)) {
        swal("", "请填写答案", "warning")
    }
    //教师
    if (role == 1) {
        $.ajax({
            url: 'teacher/forgetCheckAnswer.do',
            data: {
                username: username,
                question: question,
                answer: answer
            },
            type: 'post',
            success: function (data) {
                if (data.status === 0) {
                    $("#forgetToken").val(data.data);
                    $("#checkAnswer").hide();
                    $("#resetPwd").fadeIn();
                } else {
                    swal("", data.msg, "warning");
                }
            },
            error: function () {
            }
        });
    }
}

function resetPwd() {
    var username = $("#name").val(),
        passwordNew = $("#pwd").val(),
        pwd2 = $("#pwd2").val(),
        forgetToken = $("#forgetToken").val(),
        role = $("#role2").val();
    if (isNull(passwordNew) || isNull(pwd2)) {
        swal("", "请填写密码", "warning");
        return false;
    }
    if (passwordNew !== pwd2) {
        swal("", "两次输入密码不一致", "warning");
        return false;
    }
    //教师
    if (role == 1) {
        $.ajax({
            url: 'teacher/forgetResetPassword.do',
            data: {
                username: username,
                passwordNew: passwordNew,
                forgetToken: forgetToken
            },
            type: 'post',
            success: function (data) {
                if (data.status === 0) {
                    swal("", data.msg, "warning");
                } else {
                    swal("", data.msg, "warning");
                }
            },
            error: function () {
            }
        });
    }
}