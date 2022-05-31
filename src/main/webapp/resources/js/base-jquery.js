$.fn.fileUpload = function (opt) {

    let formData = new FormData();

    if ( opt.description !== undefined ){
        formData.append("description", opt.description);
    }

    $(this).find(':file').each(function (){
        let key = $(this).attr("name");
        if ( key == undefined) {
            return;
        }

        $.each($(this)[0].files, function(index, file){
            formData.append(key, file);
        });
    });

    $.ajax({
        url: opt.contextPath,
        type: 'post',
        processData: false,
        contentType: false,
        data: formData,
        dataType: "json",
        beforeSend: function (xhr, options) {
            xhr.setRequestHeader('AJAX', true);
        },
        xhr: function () {
            let myXhr = $.ajaxSettings.xhr();
            return myXhr;
        },
        error: function (jqXHR, statusCode, errorThrown) {
            console.log("====================== err =========================");
            console.log(jqXHR.status);
            console.log(statusCode, errorThrown);
            console.log(errorThrown);
            console.log("====================== err =========================");
        },
        success: function (data, statusCode, jqXHR) {
            console.log("jqXHR.status = ",jqXHR.status);
            console.log("data = ", JSON.stringify(data));
            console.log("data.fileInfo = ", data.fileInfo);

            opt.addTr(data.fileInfo);
        }
    });
}

$.fileDownload = function (_contextPath, fileType, fileName) {
    $.ajax({
        url: _contextPath + '/fileDownload',
        type:'POST',
        data: {
            "fileType": fileType,
            "fileName": fileName
        },
        success: function (data, textStatus, jqXHR){

        },
        error: function (jqXHR, textStatus, errorThrown){
            console.log(" ************ error *************** ");
            console.log(jqXHR.status);
            console.log(textStatus);
            console.log(errorThrown);
            console.log(" ************ error *************** ");
        }
    })
}

$.fileDelete = function (opt) {
    let ret = false;

    $.ajax({
        url: opt.contextPath + '/fileDelete',
        type:'POST',
        contentType: "application/json",
        data: opt.data,
        dataType:'json',
        async: false,
        success: function (data, textStatus, jqXHR){
            ret = data.ret;
        },
        error: function (jqXHR, textStatus, errorThrown){
            opt.alarm(jqXHR.status);
            // console.log(jqXHR.status);
            // console.log(textStatus);
            // console.log(errorThrown);
        }
    });

    return ret;
}

$.ajaxJT = function (url, data, succCallback, failCallBack) {
    if (typeof url === 'undefined') {
        return;
    }
    ;

    $.ajax({
        url: url,
        type: 'POST',
        contentType: "application/json",
        dataType: 'text',
        data: JSON.stringify(data),
        success: function (ret) {
            succCallback(ret);
        },
        error: function (xhr, statusCode, err) {
            failCallBack(err);
        }
    });
};

$.ajaxJJ = function (url, data, succCallback, failCallBack) {
    if (typeof url === 'undefined') {
        return;
    }
    ;

    $.ajax({
        url: url,
        type: 'POST',
        contentType: "application/json",
        dataType: 'json',
        data: JSON.stringify(data),
        success: function (ret) {
            succCallback(ret);
        },
        error: function (xhr, statusCode, err) {
            failCallBack(err);
        }
    });
};


// 동기식 Ajax 사용
$.ajaxSync = function (url, reqData) {
    if (typeof url === 'undefined') {
        return;
    }

    if (typeof reqData === 'undefined') {
        return;
    }

    let result = {};

    $.ajax({
        url: url,
        type: 'post',
        contentType: "application/json; charset=UTF-8",
        dataType: 'json',
        data: JSON.stringify(reqData),
        async: false,
        success: function (data) {
            result = data;
        }
    });

    alert('result = ' + JSON.stringify(result));
    return result;
};

// 비동기식 Ajax 사용
$.ajaxASync = function (url, reqData, callback) {
    if (typeof url === 'undefined') {
        return;
    }

    if (typeof reqData === 'undefined') {
        return;
    }

    $.ajax({
        url: url,
        type: 'post',
        contentType: "application/json; charset=UTF-8",
        data: JSON.stringify(reqData),
        dataType: 'json',
        // async: true,            // 기본값은 true 임
        success: function (data) {
            if (callback !== undefined)
                callback(data);
        }
    });

};

// contextPath 구하기
$.getContextPath = function () {
    let hostIndex = location.href.indexOf(location.host) + location.host.length;
    return location.href.substring(hostIndex, location.href.indexOf('/', hostIndex + 1));
}

// ready is deprecated ( jquery over 3.0 )
$(() => {
    // Toggle the side navigation
    const sidebarToggle = document.body.querySelector('#sidebarToggle');
    if (sidebarToggle) {
        // Uncomment Below to persist sidebar toggle between refreshes
        if (localStorage.getItem('sb-sidenav-toggle') === 'true') {
            document.body.classList.toggle('sb-sidenav-toggled');
        }
        sidebarToggle.addEventListener('click', event => {
            event.preventDefault();
            document.body.classList.toggle('sb-sidenav-toggled');
            localStorage.setItem('sb-sidenav-toggle', document.body.classList.contains('sb-sidenav-toggled'));
        });
    }

    // table for simpleDatatables class
    const datatablesSimple = document.getElementById('datatablesSimple');
    if (datatablesSimple) {
        new simpleDatatables.DataTable(datatablesSimple);
    }

    // table for simpleDatatables class
    const datatablesSimple2 = document.getElementById('datatablesSimple2');
    if (datatablesSimple2) {
        new simpleDatatables.DataTable(datatablesSimple2);
    }

});