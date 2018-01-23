//分页
function showPaging(data) {
    var page = data.data.pages,
        pageNum = data.data.pageNum,
        pageNumP = pageNum - 1,
        pageNumN = pageNum + 1,
        str = "",
        i;
    if (page < 5) {
        str += "<ul>\n" +
            "<li class=\"disabled\"><a onclick=\"paging("+pageNumP+")\">上一页</a></li>\n";
        for (i = 1; i <= page; i++) {
            str += "<li><a onclick=\"paging(" + i + ")\">" + i + "</a></li>\n";
        }
        str += "<li><a onclick=\"paging("+pageNumN+")\">下一页</a></li>\n" +
            "</ul>";
    } else {
        if (pageNum <= 3) {
            str += "<ul>\n" +
                "<li class=\"disabled\"><a onclick=\"paging("+pageNumP+")\">上一页</a></li>\n";
            for (i = 1; i <= 5; i++) {
                str += "<li><a onclick=\"paging(" + i + ")\">" + i + "</a></li>\n";
            }
            str += "<li><a onclick=\"paging("+pageNumN+")\">下一页</a></li>\n" +
                "</ul>";
        } else {
            var j = page - pageNum;
            if (j < 2) {
                j = page;
            } else {
                j = pageNum + 2;
            }
            str += "<ul>\n" +
                "<li class=\"disabled\"><a onclick=\"paging("+pageNumP+")\">上一页</a></li>\n";
            for (i = j - 5; i <= j; i++) {
                str += "<li><a onclick=\"paging(" + i + ")\">" + i + "</a></li>\n";
            }
            str += "<li><a onclick=\"paging("+pageNumN+")\">下一页</a></li>\n" +
                "</ul>";
        }
    }
    $(".pagination").html(str);
    var _hasPreviousPage = data.data.hasPreviousPage,
        _hasNextPage = data.data.hasNextPage;
    if (_hasPreviousPage === false) {
        $(".pagination li:first").addClass("disabled");
    } else {
        $(".pagination li:first").removeClass("disabled");
    }
    if (_hasNextPage === false) {
        $(".pagination li:last").addClass("disabled");
    } else {
        $(".pagination li:last").removeClass("disabled");
    }

    $(".pagination a").each(function () {
        var _this = $(this);
        var _thisVal = this.innerHTML;
        if (_thisVal == pageNum) {
            _this.parent().addClass("active");
        } else {
            _this.parent().removeClass("active");
        }
    })
}

//提示信息
function showDialog(title, msg) {
    var _myModal = $("#myModal");
    _myModal.find(".modal-header").html(title);
    _myModal.attr('class', 'modal');
    _myModal.find(".modal-body").html(msg);
    setTimeout(function () {
        _myModal.attr('class', 'modal hide');
    }, 2000);
}

//表格渲染
function selectTr() {
    $('tbody').on('click', 'tr', function () {
        this.style.backgroundColor = "#b4b4bb";
        if (selectedTr !== null)
            selectedTr.style.backgroundColor = "#f9f9f9";
        if (selectedTr === this)
        //加上此句，以控制点击变白，再点击反灰
            selectedTr = null;
        else
            selectedTr = this;
    });
}

//返回
function back() {
    art.dialog.close();
}

//查询框显示隐藏
function queryOpen() {
    $("#queryBox").toggle();
}


$(function () {
    /*字数限制*/
    $("#noticeContent").on("input propertychange", function () {
        var $this = $(this),
            _val = $this.val(),
            count;
        if (_val.length > 175) {
            $this.val(_val.substring(0, 175));
        }
        count = $this.val().length;
        $("#text-count").text(count);
    });
});
