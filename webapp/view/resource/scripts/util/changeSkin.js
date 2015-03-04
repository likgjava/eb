/*
++++++++
+ 
+ 改变主题
+ 
+ date: 2010-3-22
+ by:Anson,E-mail:chents@gpcsoft.com
++++++++*/

function changeSkin(styleName) {

    $('link[@rel*=style][title]').each(function(i) {
        this.disabled = true;
        if (this.getAttribute('title') == styleName) this.disabled = false;
    });
    createCookie('epsStyle', styleName, 365);

	//使用JSON存取数据
	$.getJSON($('#initPath').val()+'/view/resource/skin/skin.txt', function(jsonSource){
		$.each(jsonSource.themes,
		function(i, j) {
			if (j.name == styleName) {
				$('#sysContainer').data('menuPosition', j.menuSub);
//				$.addMenu({
//					url: $('#initPath').val()+'/view/data/menu.txt'
//				});
				if (j.menuSub == 'top') {
					sysTools_hideSub();
					
				} else { //增加非下拉副导航判断 OTiger 10.04.30
					sysTools_showSub();
				}
				var hashd = $("#refreshBtn").data('hash') || location.hash;
				if (hashd.length > 4) {
					loadPage_callback(hashd);
				}
				if (j.autoHeight == 'true') {
					setTimeout(function() {
						$('#sysContainer').data('autoHeight', 'true')
						fitHeight();
					},
					100)
				} else {
					setTimeout(function() {
						$('#sysContainer').data('autoHeight', 'false')
						fitHeight();
					},
					100)
				}
			}
	
		})
	});

}

/*++++++++
+ 
+ 自适应高度设置
+ 
++++++++*/
function fitHeight() {
    //清除firefox bug
//    if ($('#sysContainer').data('autoHeight') == 'true') {
//        $('#contentSub').css('height', 'auto');
//        $('#contentMain').css('height', 'auto');
//        $('#conBody').css('height', 'auto');
//        $('body').removeAttr('scroll')
//        return false;
//    }
    $('body').attr('scroll', 'no')
    $.autoHeight({
        callback: function(i) { 
        	$('#contentMain').height(i - exHeight($('#contentMain'))-4);
            $('#conBody').height($('#contentMain').height() - $('#conTitle').height() - exHeight($('#conTitle')) - exHeight($('#conBody')));
            $('#contentSub').height($('#contentMain').height());
            return false;}
    })

}

function changeSkinLogin(styleName) {

    $('link[@rel*=style][title]').each(function(i) {
        this.disabled = true;
        if (this.getAttribute('title') == styleName) this.disabled = false;
    });
    createCookie('epsStyle', styleName, 365);

}
//添加cookie
function createCookie(name, value, days) {
    if (days) {
        var date = new Date();
        date.setTime(date.getTime() + (days * 24 * 60 * 60 * 1000));
        var expires = "; expires=" + date.toGMTString();
    }
    else var expires = "";
    document.cookie = name + "=" + escape(value) + expires + "; path=/";

}
//读cookie
function readCookie(name) {
    var nameEQ = name + "=";
    var ca = document.cookie.split(';');
    for (var i = 0; i < ca.length; i++) {
        var c = ca[i];
        while (c.charAt(0) == ' ') c = c.substring(1, c.length);
        if (c.indexOf(nameEQ) == 0) return unescape(c.substring(nameEQ.length, c.length));
    }
    return null;
}
//删除cookie
function eraseCookie(name) {
    createCookie(name, "", -1);
}