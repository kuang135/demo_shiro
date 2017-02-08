$.ajaxSetup({
    complete : function(XMLHttpRequest, textStatus) {
        var sessionStatus = XMLHttpRequest.getResponseHeader('sessionStatus');
        if (sessionStatus === 'timeOut') {
            top.location.href = '/admin/login.do';
        }
    }
});