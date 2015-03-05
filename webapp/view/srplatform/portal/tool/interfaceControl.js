// JavaScript Document
//本文件服务器调用页面
$(document).ready(function() {
    //$('#changeThemeZone').html

    var html = '';
	$.getJSON($('#initPath').val()+'/view/resource/skin/skin.txt', function(jsonSource){
		$.each(jsonSource.themes,
		function(i, j) {
			//var iconPath = j.path.match(/(.*\/)*css\//)[1] + 'skinIcon.gif';
			var iconPath = j.path.match(/(.*\/)*css\//)[1].replace('..',$('#initPath').val()+'/view')+'skinIcon.gif';
			html += ' <li title="' + j.title + '" name="' + j.name + '"><img src="' + iconPath + '" alt="' + j.title + '"/><span>' + j.desc + '</span></li>';
	
		})
		$('#changeThemeZone').html(html).find('li').click(function() {
	
			changeSkin(this.getAttribute("name"));
	
		});
	});

    $('#closeEpsDialogBtn').click(function() {
        //$('#changeSkinPanel').find('.epsDialogClose').trigger('click');
    	$('#epsDialogClose').click();
    })
    $('#screenStandard').click(function() {
        sysTools_defaultLayout();
        fitHeight();
    })
    $('#screenFull').click(function() {
        sysTools_fullScreen();
        fitHeight();
    })
    $('#navSubHide').click(function() {
        sysTools_hideSub();
    })
    $('#navSubShow').click(function() {
        sysTools_showSub();
    })
    $('#fontBase').click(function() {
        $('body').css('fontSize', '12px');
    })
    $('#fontLarge').click(function() {
        //var fontSize = (getNum($('#sysContainer').css('fontSize')) + 1);
        //if (fontSize < 20) $('body').css('fontSize', fontSize + 'px');
				$('body').css('fontSize', '16px');
    })
    $('#changeMenu').click(function() {
        $.epsDialog({
            title: '菜单调整',
            id: 'changeEpsMenuDialog',
            url: 'ecpboxes/epsMenuBuilder.htm',
            width: 800,
            height: 460,
            maxWin: false,
            //最大化窗口
            dragAndResize: true,
            //拖动和改变大小受isTips限制
            afterLoad: function() {
                $('#changeEpsMenuDialog .epsDialogText').append('<div class="btnBoxCol" id="menuBtnBox"><button id="sureEpsMenuDialogBtn">确定</button><button id="defaultEpsMenuDialogBtn">默认</button><button id="cancelEpsMenuDialogBtn">关闭</button></div>');
                $('#buildDiv').hide();
                $('#cancelEpsMenuDialogBtn').click(function() {
                    $('#changeEpsMenuDialog').find('.epsDialogClose').trigger('click');
                });
                $('#sureEpsMenuDialogBtn').click(function() {

                    $('#updateMenu').trigger('click');
                    $('#changeEpsMenuDialog').find('.epsDialogClose').trigger('click');
                });
                $('#defaultEpsMenuDialogBtn').click(function() {

                    $('#oldMenu').trigger('click');
                    $('#changeEpsMenuDialog').find('.epsDialogClose').trigger('click');
                });

            },
            //加载完url后调用
            onClose: function() {} //关闭后调用
        })

    })
});