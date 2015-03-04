 /*
 * name     :  epsDialog
 * desc     :  弹出层/提示层 调用参数说明参阅 epsDialog.setting
 * author	:  Anson  E-mail:chents@gpcsoft.com
 * version  :  1.4(2010.03.18)
 * depends  :  jquery.dragAndResize.js
               jquery.bgiframe.js
               loadPage.js
 
 */

(function($) {

$.epsDialog = function(settings){

	var options = jQuery.extend({
									title:null, //标题,为空时将不出现标题栏
									id:null,
									content:'数据加载中......'+'<img src='+$('#initPath').val()+'/view/resource/images/load.gif />',//epsDialog内容,使用url或isLocalhost为false时,无效
									url:null, //epsDialog中显示的内容
									width: 800,//默认宽度
									height: 500,//默认高度
									top:false,//绝对定位上边距
									left:false,//绝对定位左边距
									hasBg:true,//遮挡层
									mutiWin:true,//允许多个
									isLocalhost:true, //是否本地地址,默认是
									isTips:false, //是否右下角提示,默认否
									newWin:false,//新窗口中打开
									maxWin:true,//最大化窗口
									dragAndResize:true,//拖动和改变大小
									timeOut:0, //自动关闭时间,默认0不关闭
									onOpen: function(){}, //打开epsDialog时调
									afterLoad: function(){}, //加载完url后调用
									onClose: function(){}, //关闭后调用
									isReload:true,   //点击"X"或"关闭"常规关闭后是否引用onClose自定义的方法刷新页面，默认为是
									params:{}
								}, settings),
		self = this,
		o = options,
		epsDialogTextHeight = parseInt(o.height),
		epsDialogTextWidth = parseInt(o.width),
		dragAndResizeOK = true;
		
	var epsDialog = {
		height : function() {if ($.browser.msie && $.browser.version < 7) {
						  var scrollHeight = Math.max(
						   document.documentElement.scrollHeight,
						   document.body.scrollHeight
						  );
						  var offsetHeight = Math.max(
						   document.documentElement.offsetHeight,
						   document.body.offsetHeight
						  );
						 
						  if (scrollHeight < offsetHeight) {
						   return $(window).height() + 'px';
						  } else {
						   return scrollHeight + 'px';
						  }
						 } else {
						  return $(document).height() + 'px';
						 }
				},
		 width : function() {
				  // handle IE 6
				  if ($.browser.msie && $.browser.version < 7) {
				  var scrollWidth = Math.max(
				  document.documentElement.scrollWidth,
				  document.body.scrollWidth
				  );
				  var offsetWidth = Math.max(
				  document.documentElement.offsetWidth,
				  document.body.offsetWidth
				  );
				  
				  if (scrollWidth < offsetWidth) {
				  return $(window).width() + "px";
				  } else {
				  return scrollWidth + "px";
				  }
				  // handle "good" browsers
				  } else {
				  return $(document).width() + "px";
				  }
		  },
		 scollHeight: function() {
    
			  var scrollTop=0;
			  if(document.documentElement&&document.documentElement.scrollTop)
			  {
			   scrollTop=document.documentElement.scrollTop;
			  }
			  else if(document.body)
			  {
			   scrollTop=document.body.scrollTop;
			  }
			  return scrollTop;
	
		 }, 
		center:function(E){//居中
			var elWidth = E.width();
			var elHeight = E.height(); 
			var winWidth = $(window).width();
			var winHeight = $(window).height();	
			E.css({
				 "top" :  (winHeight / 2) - (elHeight / 2) + epsDialog.scollHeight(),
				 "left":  (winWidth / 2) - (elWidth / 2)
				 });
			if($.fn.bgIframe) E.bgiframe();
			},
		maxMinWin:function(id){//最大化-最小化
		var thisObj = $('#'+id);
			var oh = thisObj.find('.epsDialogContent').height()-thisObj.find('.epsDialogText').height();
			  if (thisObj.find('.epsDialogMax').hasClass("min")){
					  //最小化
						thisObj.find('.epsDialogMax').removeClass("min").attr('title','最大化'); 
						thisObj.find('.epsDialogText').height( epsDialogTextHeight );
						thisObj.css({
								width: epsDialogTextWidth,
								height: epsDialogTextHeight + oh
						});
						epsDialog.center(thisObj);
						dragAndResizeOK = true;
						if(o.dragAndResize && $.fn.dragAndResize) $('.epsDialogResize').show();
				  }else{
				 	 //最大化
						thisObj.find('.epsDialogMax').addClass("min").attr('title','最小化');
						thisObj.css({
								  marginTop:'0px',
								  marginLeft:'0px',
								  left:'0px',
								  top:epsDialog.scollHeight()+'px',
								  width:$(window).width(),
								  height:$(window).height()
							  });
						if($.fn.bgIframe) thisObj.bgiframe();
						thisObj.find('.epsDialogText').height(thisObj.height()-oh);
						dragAndResizeOK = false;
						if(o.dragAndResize) thisObj.find('.epsDialogResize').hide();
					
				 }
			},
		position:function(id){
			var thisObj = $('#'+id);
				if(o.top&&o.left){
					thisObj.css({
						top: parseInt(o.top) +'px',
						left:parseInt(o.left) +'px'
						});
					if($.fn.bgIframe) thisObj.bgiframe();
				}else{
					if(eval(o.isTips)){
						//右下角提示 
						if ($.browser.msie && $.browser.version < 7) { //IE6
							thisObj.css({
								right:'0px',
								bottom:'0px'
							})
						}
						else{ //IE7以上或其他浏览器
							thisObj.css({
								right:'0px',
								bottom:'0px',
								position:'fixed'
							})
						}
						if($.fn.bgIframe) thisObj.bgiframe();
						}else{
						//居中显示
							 epsDialog.center(thisObj);
				
							}
				}
						
						$('#'+id+'_BG').css('height',epsDialog.height());
						$('#'+id+'_BG').css('width',$(document).width());
						if(!o.newWin){thisObj.find('.epsDialogNewWin').hide();};
						if(!o.maxWin){thisObj.find('.epsDialogMax').hide();thisObj.find('.epsDialogNewWin').css('right','24px')};
						if(o.title){}else{thisObj.find('.epsDialogIcon').addClass('epsDialogIconBg')};
						if(!o.hasBg){$('#'+id+'_BG').remove();$('#epsDialogIe6Bg').remove();};
						o.onOpen();
			},
		scrollDiv :function(id){
			var thisObj = $('#'+id);
			//如果是绝对定位，不滚动
			if($(thisObj).css("position") == "fixed") return;
			var thisObjOT = thisObj.offset().top;
			$(window).scroll(function (){
				var thisObjOTM = thisObj.find('.epsDialogMax').hasClass("min") ? 0 : (document.documentElement.clientHeight-thisObj.height())/2;
				var offsetTop = thisObjOTM + $(window).scrollTop() +"px";
				thisObj.animate({top : offsetTop },{ duration:600 , queue:false });
				});
			
			},
		dnr:function(id){//拖动和改变大小
		var thisObj = $('#'+id);
				if(o.dragAndResize && $.fn.dragAndResize){
					thisObj.find('.epsDialogResize').show();
					var oh = thisObj.height()- thisObj.find('.epsDialogText').height();
					 thisObj.dragAndResize({
						 handle:{'.epsDialogContent>h3':'m',	
								 '.epsDialogResizeSW':'sw',
								 '.epsDialogResizeSE':'se',
								 '.epsDialogResizeNW':'nw',
								 '.epsDialogResizeW':'w',
								 '.epsDialogResizeE':'e',
								 '.epsDialogResizeS':'s',
								 '.epsDialogResizeN':'n'
								 },
						minHeight: 180,
						minWidth: 260,
						ghost:true,
						stopCallback:function(w,h){
							if(!dragAndResizeOK){ 
								thisObj.css({left:'0px',top:'0px'});
								$('body').css('cursor', 'auto');
								return false;
							}
							thisObj.find('.epsDialogText').height(h-oh);
							}
					 })
					}
				},
		buildId:function(){//生成id
					var now= new Date(); 
					var number = now.getSeconds(); 
					var minute = now.getMinutes();
					return number+'DialogID'+minute;},
		close:function(id){//关闭
				$('#'+id).remove();
				$('#'+id+'_BG').remove();
				$('#epsDialogIe6Bg').remove();
				$(document).unbind("keydown");
				if(o.isReload){
					o.onClose();
				}
				},
		closeReload:function(id){//关闭层并刷新自定义的请求
			$('#'+id).remove();
			$('#'+id+'_BG').remove();
			$('#epsDialogIe6Bg').remove();
			$(document).unbind("keydown");
			o.onClose();
			},
		closeNoReload:function(id){//关闭后不刷新自定义的请求
			$('#'+id).remove();
			$('#'+id+'_BG').remove();
			$('#epsDialogIe6Bg').remove();
			$(document).unbind("keydown");
			},
		autoClose:function(id){//自动关闭
					if(o.timeOut>0){self.timeOver = setTimeout(function(){epsDialog.close(id)},parseInt(o.timeOut)*1000);}
					},
		escClose:function(id){//按Esc关闭
					$(document).unbind("keydown").bind("keydown", function(event){
												//点Esc键关闭
												if (event.keyCode == 27) {
													epsDialog.close(id);
													return false;}
													});
					},
		getMaxIndex:function(id){
			var a =  parseInt($('#'+id).css('zIndex'));
			$('.epsDialog').each(function(){
										  
										 var b = parseInt($(this).css('zIndex'));
										 if(b>a || b==a){ a = b + 1}
										  })
			$('#'+id).css('zIndex',a)
			},
		addDialog:function(id){
			if(!o.mutiWin) $('div[eps=epsDialog]').remove();
			var CID = id||epsDialog.buildId();
			var winWidth = $(document).width();
            var winHeight = $(document).height();
					$('<div class="epsDialog" eps="epsDialog" id="'+CID+'">'+
					'<div class="epsDialogContent">'+
						'<div class="epsDialogIcon">'+
							'<a href="'+o.url+'" target="_blank" class="epsDialogNewWin" title="新窗口打开" >新窗口打开</a>'+
							'<a href="#" class="epsDialogMax" title="最大化" >最大化</a>'+
							'<a href="#" id="epsDialogCloseNoReload" style="display:none">不刷新</a>'+
							'<a href="#" id="epsDialogCloseReload" style="display:none">刷新</a>'+
							'<a href="#" id="epsDialogClose" class="epsDialogClose" title="Esc/点击 关闭">关闭</a>'+
						'</div>'+
						'<h3>'+o.title +'</h3>'+
						'<div class="epsDialogText" url="'+o.url+'">'+o.content+'</div>'+
						'<div class="epsDialogResize epsDialogResizeSE"></div>'+
						'<div class="epsDialogResize epsDialogResizeSW"></div>'+
						'<div class="epsDialogResize epsDialogResizeNW"></div>'+
						'<div class="epsDialogResize epsDialogResizeE"></div>'+
						'<div class="epsDialogResize epsDialogResizeW"></div>'+
						'<div class="epsDialogResize epsDialogResizeS"></div>'+
						'<div class="epsDialogResize epsDialogResizeN"></div>'+
					'</div>'+
			  '</div>'+
			  '<div class="epsDialogBg"  eps="epsDialog" id="'+CID+'_BG"></div>'+
			  '<!--[if IE 6]><iframe id="epsDialogIe6Bg" class="epsDialogIe6Bg" /*src="javascript:false;" 去掉*/ scrolling="no" frameborder="0" style="width:'+winWidth+'px; height:'+winHeight+'px"></iframe><![endif]-->')
					.find('.epsDialogClose').click(function(){epsDialog.close(CID);return false;}).end()
					.find('#epsDialogCloseNoReload').click(function(){epsDialog.closeNoReload(CID);return false;}).end()
					.find('#epsDialogCloseReload').click(function(){epsDialog.closeReload(CID);return false;}).end()
					.find('.epsDialogMax').click(function(){epsDialog.maxMinWin(CID); return false;}).end()
					.find('.epsDialogContent>h3').bind('dblclick',function(){
																		   if(o.dragAndResize&&o.maxWin) E.find('.epsDialogMax').trigger('click')
																		   }).end()
					.find('.epsDialogText').each(function(){
														$(this).height(epsDialogTextHeight);
														if(eval(o.isLocalhost)){
															//本地通过div加载
															//if(o.url){$(this).loadPage(o.url,o.afterLoad())};
															if(o.url){
																//alert("o.url="+o.url);增加一参数识别当前请求是否来源于弹出层 liuy
																var flag ='?fromDiv=yes';
																if(o.url.indexOf('?')>-1)flag='&fromDiv=yes';
																$(this).loadPage(o.url+flag,o.params,function(){o.afterLoad()});
				
																};
														}
														else{
															//异地通过iframe加载
															$(this).loadPage_frame(o.url,o.params,function(){o.afterLoad()});
															}
														 }).end()
					.appendTo('body');
					E = $('#'+CID);
					EBG = $('#'+CID+'_BG');
					E.css("width",epsDialogTextWidth+"px");
					
					//定位
					epsDialog.position(CID);
						
					//自动关闭
					epsDialog.autoClose(CID);
						
					//拖动和改变大小
					epsDialog.dnr(CID);
					
					//最上面显示
					epsDialog.getMaxIndex(CID);
					//滚动
					epsDialog.scrollDiv(CID);
					
					E.bind('mousedown',function(){
									 epsDialog.getMaxIndex(CID);
									 })
					//Esc键关闭
					epsDialog.escClose(CID);
			}
		
		}
	
	if(self.timeOver){ clearTimeout(self.timeOver);}

	o.onOpen = o.onOpen || function() {};
	o.afterLoad = o.afterLoad || function() {};
	o.onClose = o.onClose || function() {};
	
	if(o.isTips){
		o.dragAndResize = false;
		o.maxWin = false;
		o.hasBg = false;
		}
	
	epsDialog.addDialog(o.id);

	return this;
	};
	
})(jQuery);



