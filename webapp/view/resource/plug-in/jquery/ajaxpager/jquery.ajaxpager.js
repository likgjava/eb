/*
jQuery AjaxPager Plugin
Version 0.8 (05/01/2010)

Usage: $("someSelector").ajaxPager();
Options: startPage (Default: 1)
         PagesCount (Default: 10)
		 fnCallbackDraw (Default: null)
*/
(function($) {
     $.fn.extend({
	     ajaxPager: function(options) {
		     var defaults = {
			     startPage: 1,
				 PagesCount: 10,
				 fnCallbackDraw: null,
				 ShowFirstLast: false,
				 ShowGoBack : true
			 };
			 var options = $.extend(defaults, options);
			 options.data.page=options.startPage;
			 options.data.rp=options.rp
			 var thiszz=this;
			 /*by wangsw*/
			$.getJSON(options.url,options.data,function(json){
				var total=parseInt(json.total);	    
				options.PagesCount=Math.ceil(json.total!=0?json.total/options.data.rp:0);
				options.total=Math.ceil(json.total);
				options.startPage= parseInt(json.page)+1;
				 return thiszz.each(function() {
				     var obj = $(this); 
//					 if(options.PagesCount > 1) {
					     obj.empty().append(CalculatePages(parseInt(options.startPage)-1, parseInt(options.PagesCount), options.ShowGoBack, options.ShowFirstLast, options.total));
					     if($.isFunction(options.fnCallbackDraw)) {    
						     options.fnCallbackDraw(json, 1);
				         }
					     var eventHandler = function() {
					         $("li.pageButton").not("li.totalPage").click(function(event) {
					             event.preventDefault();
					             var activePage = $(this).find("a").attr("rel");
						         if($.isFunction(options.fnCallbackDraw)) {
							         obj.empty().append(CalculatePages(parseInt(activePage), parseInt(options.PagesCount),options.ShowGoBack, options.ShowFirstLast, options.total));
							         eventHandler();
							         options.data.page=activePage;
									 $.getJSON(options.url+'&loadTime=notFirst',options.data,function(json){
										 options.fnCallbackDraw(json,activePage);
									 });
						         }
					         });
					     }
					 
					     eventHandler();
//					 }
//					 else {		
//					     if($.isFunction(options.fnCallbackDraw)) {    
//						     options.fnCallbackDraw(options.startPage);
//				         }
//					 }
				 });
			});

		 }
     });
	 
	 function CalculatePages(pageIndex, pageCount, ShowGoBack, ShowFirstLast, total) {
		 if(pageCount <= 0){ //by likg
			 return ;
		 }
		 
	     var startPoint = pageIndex - 2;
		 var endPoint = pageIndex + 2;
		 
		 if(pageIndex < 5) {
		     startPoint = 1;
			 if(pageCount > 5) {
			     endPoint = 5;
			 }
			 else {
			     endPoint = pageCount;
			 }
		 }
		 
		 if(endPoint > pageCount) {
		     var minus = endPoint - pageCount;
			 startPoint = startPoint - minus;
			 endPoint = endPoint - minus;
		 }
		 
		 var $pager = $('<ul></ul>');
		 
		 if(pageIndex > 1) {
		     if(ShowFirstLast) {
		         $('<li class="pageButton firstButton"><a rel="1">Erste</a></li>').appendTo($pager);
			 }
			 if(ShowGoBack) {
		         var backButton = $('<li class="pageButton backButton"><a rel="' + (pageIndex - 1) + '">上一页</a></li>');
			     backButton.appendTo($pager);
			 }
		 }
		 
		 for(var page = startPoint; page <= endPoint; page++) {
		     if(page != pageIndex) {
		         var currentButton = $('<li class="pageButton"><a rel="' + page + '">' 
			                           + page + '</a></li>');
			 }
			 else {
			     var currentButton = $('<li class="activeButton"><a rel="' + page + '">'
				                       + page + '</a></li>');
			 }
			 currentButton.appendTo($pager);
		 }
 		 if(pageIndex != pageCount&&pageCount!=1) {
		     if(ShowGoBack) {
		         var goButton = $('<li class="pageButton goButton"><a rel="' + (pageIndex + 1) + '">下一页</a></li>');
			     goButton.appendTo($pager);
			 }
			 if(ShowFirstLast) {
			     $('<li class="pageButton lastButton"><a rel="' + pageCount + '">Letzte</a></li>').appendTo($pager);
			 }
		 }
 		 if(pageCount!=0)
		 $pager.append('<li class="pageButton totalPage"><span>共'+pageCount+'</span>页/'+total+'条记录</li>');
		 return $('<div class="pages"></div>').append($pager);/*by wangsw  原来是这个$pager*/
	 }
})(jQuery);