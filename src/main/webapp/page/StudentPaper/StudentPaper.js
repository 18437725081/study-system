var selectedTr = null;

//获取未完成的试卷列表
function getPaper() {
    selectedTr = null;
    $.ajax({
        url: '../../student/getUnfinishedPaper.do',
        type: 'post',
        success: function (data) {
            if (data.status === 10) {
                window.parent.location.href = "../../login.html";
            } else if (data.status === 1) {
                swal("", data.msg, "warning");
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

//跳转答卷页面
function exam(pkPaper) {
    window.location.href = "Examination.html?"+pkPaper;
}

//获取试卷详情
function getPaperDetail(pkPaper) {
    $.ajax({
        url: "../../student/getPaperDetail.do",
        type: 'post',
        data:{
            pkPaper:pkPaper
        },
        success: function (data) {
            if (data.status === 10) {
                window.parent.location.href = "../../login.html";
            } else if (data.status === 1) {
                swal("", data.msg, "error");
            } else {
                document.getElementById('content-header').innerHTML = template('template', data);
            }
        },
        error: function () {
            window.location.href = "../other/error500.html";
        }
    });
}

//提交试卷
function submitPaper() {

    var pkTests = $("input[name=pkTest]");
    var answers = $("select[name=answer]");
    var scores = $(".score");


}