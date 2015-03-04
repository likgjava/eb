/*
 *
 ********
 * $.fn.loadPage
 * 加载页面所有ajax页面都要经过此方法(url,参数,回调)
 * 
 * by:Anson,E-mail:chents@gpcsoft.com
 *
 * 2010-4-8
 ********本js还包含方法 ********
 *
 * 		$.fn.loadPage_call
 * 		通过load加载并运行页面call标签包含函数(url,回调)
 *
 * 		$.fn.loadPage_frame(url,iframe的id,回调)
 * 		用iframe加载页面
 *
 * 		loadPage_includeJs
 * 		非jquery方法，加载完js并回调(url,回调)
 *
 * 		loadPage_getParaValue
 * 		非jquery方法，取url参数
 *
 * 		loadPage_history
 * 		非jquery方法,用前进后退加载页面
 *
 * 		loadPage_saveRecord
 * 		非jquery方法,保存记录
 *
 * 		clearJsBug
 * 		非jquery方法,清除由js引起的bug
 *
 * 		loadFramePage
 * 		非jquery方法,用iframe读页面(url,id)
 *
 ******** ******** ********
 *
 * by:Anson,E-mail:chents@gpcsoft.com
 * 
 * Depends:
 * 
 *  
 * 	
 * 
 */

(function($) {

//loadPage 所有ajax页面都要经过此方法
jQuery.fn.extend({
	/**
	 * zhanghuihua 20111105
	 */
	isTag:function(tn) { 
		if(!tn) return false; 
		return $(this)[0].tagName.toLowerCase() == tn?true:false; 
	},
	/**
	 * zhanghuihua 20111105
	 */
	layoutH:function($refBox){
		return this.each(function(){
			var $this = $(this);
			if (! $refBox) $refBox = $(window);
			var iRefH = $refBox.height();
			var iLayoutH = parseInt($this.attr("layoutH"));
			var iH = iRefH - iLayoutH > 50 ? iRefH - iLayoutH : 50;
			
			if ($this.isTag("table")) {
				$this.removeAttr("layoutH").wrap('<div layoutH="'+iLayoutH+'" style="overflow:auto;height:'+iH+'px"></div>');
			} else {
				$this.height(iH).css("overflow","auto");
			}
		});
	},
	loadPage: function( url, params, callback ) {
		var off = url.indexOf(" ");
		if ( off >= 0 ) {
			var selector = url.slice(off, url.length);
			url = url.slice(0, off);
		}
		var type = "POST";
		if ( params ) {
			if ( jQuery.isFunction( params ) ) {
				callback = params;
				params = {isAJAX:false};

			} else if( typeof params === "object" ) {
				params = jQuery.param( params );
				type = "POST";
				params.isAJAX = false;  //增加同步请求
			}
		} else {
			params = {isAJAX:false};
		}
		
		var self = this;
		//载入页面时显示动画
		self.html('<div><img src="'+$('#initPath').val()+'/view/resource/images/ajax-loader.gif"/></div>');
		jQuery.ajax({
			url: url,
			type: type,
			dataType: "html",
			data: params,
			complete: function(res, status){
				if ( status == "success" || status == "notmodified" )
					self.html( selector ? jQuery("<div/>").append(res.responseText.replace(/<script(.|\s)*?\/script>/g, "")) .find(selector) : res.responseText );
				
				$("[layoutH]", self).layoutH(); //zhanghuihua 20111105
				
				if( callback )
					callback();
			}
		});
		//yucy说可以去掉注释return this,   by zhouzhanghe at 2011.3.3 17:27
		return this; //by yucy href="javascript:void(0);" onclick="function();"用法 
	}
});
})(jQuery);

//加载完js并回调
function loadPage_includeJs(file,callback) {

	callback = callback || function() {};
    var _doc = document.getElementsByTagName('head')[0];
    var js = document.createElement('script');
    js.setAttribute('type', 'text/javascript');
    js.setAttribute('src', file);
    _doc.appendChild(js);

    if (!/*@cc_on!@*/0) { //if not IE
        //Firefox2、Firefox3、Safari3.1+、Opera9.6+ support js.onload
        js.onload = function () {
           // alert('Firefox2、Firefox3、Safari3.1+、Opera9.6+ support js.onload');
		   callback();
        }
    } else {
        //IE6、IE7 support js.onreadystatechange
        js.onreadystatechange = function () {
            if (js.readyState == 'loaded' || js.readyState == 'complete') {
              // alert('IE6、IE7 support js.onreadystatechange');
			  callback();
			 //$('.mainTip').hide().html('');
            }//else{alert('loading...')}
        }
    }

    return false;
}


function loadPage_openModelWindow(url,width){
	if (navigator.appName == "Netscape") {
		window.showModalDialog(url,'esky','dialogWidth:'+width+'px;dialogHeight:630px;resizable:no;status:no');
		//var result= window.open(url, self,"modal=1,dialog=1,scrollbars=1,status=0,resizable=0,width="+width+"px,height=600px" );
		//result.focus();
	} else {
		window.showModalDialog(url,'esky','dialogWidth:'+width+'px;dialogHeight:630px;resizable:no;status:no');
	}
}
