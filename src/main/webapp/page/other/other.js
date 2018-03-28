function setQuestion() {
    var question = $("#question").val(),
        answer = $("#answer").val();
    if (isNull(question) || isNull(answer)) {
        swal("", "问题和答案不能为空", "error");
        return;
    }
    $.ajax({
        url: '../../teacher/updateTeacherInformation.do',
        data: {
            question: question,
            answer: answer
        },
        type: 'post',
        success: function (data) {
            if (data.status === 0) {
                toast("success", data.msg)
            } else {
                toast("error", data.msg)
            }
        },
        error: function () {
            window.location.href = "../other/error500.html";
        }
    });
}


function modifyPwd() {
    var passwordOld = $("#passwordOld").val(),
        passwordNew = $("#passwordNew").val(),
        passwordNew2 = $("#passwordNew2").val();
    if (isNull(passwordNew) || isNull(passwordNew2) || isNull(passwordOld)) {
        swal("", "密码不能为空", "error");
        return;
    }
    if (passwordNew !== passwordNew2) {
        swal("", "两次输入密码不一致", "error");
        return false;
    }
    $.ajax({
        url: '../../teacher/resetPassword.do',
        data: {
            passwordOld: passwordOld,
            passwordNew: passwordNew
        },
        type: 'post',
        success: function (data) {
            if (data.status === 0) {
                toast("success", "修改密码成功")
            } else {
                toast("error", data.msg)
            }
        },
        error: function () {
            window.location.href = "../other/error500.html";
        }
    });
}