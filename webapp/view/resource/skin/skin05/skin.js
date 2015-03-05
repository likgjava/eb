/*
++++++++
+ 
+ 皮肤主题数据
+ 
++++++++*/

$(document).ready(function() {

    /*
	++++++++
	+ 
	+ 定义框架
	+ 
	++++++++*/
    //结构
    var $sysContainer = $("#sysContainer"); //页面容器
    var $sysBranding = $('#sysBranding'); //系统标识
    var $sysContent = $("#sysContent"); //系统容器
    var $contentSub = $("#contentSub"); //辅容器
    var $navMain = $("#navMain"); //主菜单
    var $contentMain = $("#contentMain"); //主容器
    var $contentSupp = $("#contentSupp"); //补充容器
    var $sysInfo = $("#sysInfo"); //系统信息

    /*++++++++
	+ 
	+ 窗口工具条
	+ 
	++++++++*/
    /*返回桌面*/
    $('#myDesktop').click(function() {
        loadPage_history('ecpboxes/myDesk.htm&title=我的桌面&target=#conBody&alinkId=ecpboxes_ecpboxes1_ecpboxes11');
    });
    /*退出系统*/
    $('#exitBtn').click(function() {
        if (confirm('确定退出系统?!'))
        //closeSystem();
        self.location = 'login.htm';
    });
    /*帮助中心*/
    $('#helpBtn').click(function() {
        $.epsDialog({
            title: '帮助中心',
            id: 'sysHelpPanel',
            content: '此页面将链接到帮助中心...',
            width: 455,
            height: 380,
            maxWin: false,
            //最大化窗口
            dragAndResize: true,
            //拖动和改变大小受isTips限制
            afterLoad: function() {},
            //加载完url后调用
            onClose: function() {} //关闭后调用
        })
    });
    /*历史记录*/
    loadPage_includeJs('../resource/scripts/jquery/optionHistory.js',
    function() {
        $('#optionHistory').optionHistory();
    });
    /*窗口快捷键*/
    loadPage_includeJs('../resource/skin/plug-in/sysTools.js',
    function() {
        $.sysTools({
            hideSubID: 'navSubControl',
            //隐藏左栏ID
            fullScreenID: 'screenControl' //全屏ID
        });
    });
    /*刷新*/
    $('#refreshBtn').click(function() {
        loadPage_history($(this).data('hash'));
    });

    /*++++++++
	+ 
	+ 时间日期控件
	+ 
	++++++++*/
    loadPage_includeJs('../resource/scripts/util/date.js',
    function() {
        date_nowTime();
    });

    /*++++++++
	+ 
	+ 自动高度
	+ 
	++++++++*/
    loadPage_includeJs('../resource/plug-in/jquery/jquery.autoHeight.js');

    /*++++++++
	+ 
	+ 界面控制弹出层设置
	+ 
	++++++++*/
    loadPage_includeJs('../resource/scripts/changeSkin.js',
    function() {

        $('#interfaceControl').click(function() {
            $.epsDialog({
                title: '界面控制设定',
                id: 'changeSkinPanel',
                //服务器用路径
                //url:'../resource/skin/interfaceControl.htm', 
                //静态页面用
                url: 'common/interfaceControl.htm',
                width: 615,
                height: 400,
                maxWin: false,
                //最大化窗口
                dragAndResize: true,
                //拖动和改变大小受isTips限制
                afterLoad: function() {},
                //加载完url后调用
                onClose: function() {} //关闭后调用
            })
        });

        var c = readCookie('epsStyle');
        if (c) {
            changeSkin(c)
        }
        else {
			//使用JSON存取数据,skinDbUrl在changeSkin.js中定义。
			$.getJSON(skinDbUrl, function(jsonSource){
				$.each(jsonSource.themes,
				function(i, j) {
					if (j.defaultTheme == 'true') changeSkin(j.name);
				})
			});
        }

    })

  /*++++++++
	+ 
	+ html菜单处理
	+ 
	++++++++*/
    loadPage_includeJs('../resource/scripts/jquery/menu.js',
    function() {
        $.addMenu({
            url: 'data/menu.txt',
            //菜单url
            target: '#conBody',
            //菜单target
            callback: function() { //加载完成后执行
                loadPage_includeJs('../resource/scripts/jquery/history.js',
                function() {
                    $.historyInit(loadPage_history);
                })
            }
        });
    });

  /*
	++++++++
	+ 
	+ 改变浏览器大小时改变高度
	+ 
	++++++++*/

    loadPage_includeJs('../resource/plug-in/jquery/jquery.wresize.js',
    function() {

        fitHeight();
        $(window).wresize(fitHeight);
        return false
    })
});

/*++++++++
+ 
+ 自适应高度设置
+ 
++++++++*/
function fitHeight() {

    //清除firefox bug
    if (!$('#sysContainer').data('autoHeight')) {
        var c = readCookie('epsStyle');
        if (c) {
            changeSkin(c)
        }
        else {
			//使用JSON存取数据,skinDbUrl在changeSkin.js中定义。
			$.getJSON(skinDbUrl, function(jsonSource){
				$.each(jsonSource.themes,
				function(i, j) {
					if (j.defaultTheme == 'true') changeSkin(j.name);
				})
			});
        }
    }

    if ($('#sysContainer').data('autoHeight') != 'true') {
        $('#contentSub').css('height', 'auto');
        $('#contentMain').css('height', 'auto');
        $('#conBody').css('height', 'auto');
        $('body').removeAttr('scroll')
        return false;
    }
    $('body').attr('scroll', 'no')
    $.autoHeight({
        callback: function(i) {
            $('#contentMain').height(i - exHeight($('#contentMain')));
            $('#conBody').height($('#contentMain').height() - $('#conTitle').height() - exHeight($('#conTitle')) - exHeight($('#conBody')));
            $('#contentSub').height(i - exHeight($('#contentSub')));
						//IE6Bug
						var browser_ver = $.browser.version;
						var accurate_value = browser_ver.substr(0,1);
						if($.browser.msie && accurate_value == '6' ){
							$('#conBody').height($('#contentMain').height() - $('#conTitle').height() - exHeight($('#conTitle')) - exHeight($('#conBody')) - $('#sysInfo').height() - exHeight($('#sysInfo')));
						}
						//end IE6Bug
            return false;
        }
    })

}
/*++++++++
+ 
+ 关闭系统
+ 
++++++++*/
function closeSystem() {
    window.opener = null;
    window.open("", "_self");
    window.close();
}