function showDialog(title, msg) {
    $("#myModal").find(".modal-header").html(title);
    $("#myModal").attr('class', 'modal');
    $("#myModal").find(".modal-body").html(msg);
    setTimeout(function () {
        $("#myModal").attr('class', 'modal hide');
    }, 3000);
}

function checkLogin() {
    var username = $("#username").val();
    var password = $("#password").val();
    var role = $("#role").val();
    if ($.trim(username) === "" || 　$.trim(password) === "") {
        showDialog("登陆信息", "<span style='color:red;'>请正确填写用户名和密码！</span>");
        return false;
    }

    $.ajax({
        url: 'user/login.do',
        data: {
            username: username,
            password: password,
            role: role
        },
        type: 'post',
        success: function (data) {
            if (data.status === 0) {
                window.location.href = "index.html";
            } else {
                showDialog("登陆信息", "<span style='color:red;'>" + data.msg + "</span>");
            }
        },
        error: function () {}
    });
}