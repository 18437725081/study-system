var selectedTr = null;

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


function exam(pkPaper) {
    window.location.href = "Examination.html?"+pkPaper;
}