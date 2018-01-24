var selectedTr = null;

//加载全部通知
function loadMajor() {
    selectedTr = null;
    $.ajax({
        url: '../../manage/queryMajor.do',
        type: 'post',
        success: function (data) {
            if (data.status === 10){
                window.location.href = "../../login.html";
            }
            var res = template('template', data);
            document.getElementById('tab').innerHTML = res;
            showPaging(data);
        },
        error:function () {
            window.location.href = "../other/error500.html";
        }
    });
}