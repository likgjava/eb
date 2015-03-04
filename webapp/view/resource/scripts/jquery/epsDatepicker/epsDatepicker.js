/*
 * epsDatepicker
 *
 * 防Ext日期选择(加入时间选择)
 * 
 * by:Anson,E-mail:chents@gpcsoft.com
 * 
 * Depends:
 * 
 * date:2010-2-1
 * version:1.1
 * 
 */
(function($){
	var userAgent=window.navigator.userAgent.toLowerCase();
	$.browser.msie8=$.browser.msie&&/msie 8\.0/i.test(userAgent);
	$.browser.msie7=$.browser.msie&&/msie 7\.0/i.test(userAgent);
	$.browser.msie6=!$.browser.msie8&&!$.browser.msie7&&$.browser.msie&&/msie 6\.0/i.test(userAgent);
	Date.prototype.Format=function(format){
		var o={"M+":this.getMonth()+1,
				"d+":this.getDate(),
				"h+":this.getHours(),
				"H+":this.getHours(),
				"m+":this.getMinutes(),
				"s+":this.getSeconds(),
				"q+":Math.floor((this.getMonth()+3)/3),
				"w":"0123456".indexOf(this.getDay()),
				"S":this.getMilliseconds()
				};
		if(/(y+)/.test(format)){
			format=format.replace(RegExp.$1,(this.getFullYear()+"").substr(4-RegExp.$1.length));
		}
		for(var k in o){
			if(new RegExp("("+k+")").test(format))
				format=format.replace(RegExp.$1,RegExp.$1.length==1?o[k]:("00"+o[k]).substr((""+o[k]).length));
		}
		return format;
	};
	
	function DateAdd(interval,number,idate){
		number=parseInt(number);
		var date;
		if(typeof(idate)=="string"){
			date=idate.split(/\D/);
			eval("var date = new Date("+date.join(",")+")");
		}
		if(typeof(idate)=="object"){
			date=new Date(idate.toString());
		}
		switch(interval){
			case"y":date.setFullYear(date.getFullYear()+number);break;
			case"m":date.setMonth(date.getMonth()+number);break;
			case"d":date.setDate(date.getDate()+number);break;
			case"w":date.setDate(date.getDate()+7*number);break;
			case"h":date.setHours(date.getHours()+number);break;
			case"n":date.setMinutes(date.getMinutes()+number);break;
			case"s":date.setSeconds(date.getSeconds()+number);break;
			case"l":date.setMilliseconds(date.getMilliseconds()+number);break;
		}
		return date;
	};
$.fn.epsDatepicker=function(o){
	var def={
			weekStart:0,
			weekName:["日","一","二","三","四","五","六"],
			monthName:["1","2","3","4","5","6","7","8","9","10","11","12"],
			monthp:"月",
			yearStr:"年",
			Year:new Date().getFullYear(),
			Month:new Date().getMonth()+1,
			Day:new Date().getDate(),
			today:new Date(),
			btnOk:" 确定 ",
			btnCancel:" 取消 ",
			btnToday:"今天",
			btnClear:"清空",
			timeAM:"上午",
			timePM:"下午",
			timeShow:false,
			inputDate:null,
			inputTime:'00:00:00',
			onReturn:false,
			version:"1.1",
			applyRule:false,
			picker:null
	};
	$.extend(def,o);
	var cp=$("#epsDatepickerDiv");
	if(cp.length==0){
		
		//画控件面板
		var cpHA=[];
		cpHA.push("<div id='epsDatepickerDiv' eps='epsDatepicker' class='bbit-dp' style='width:175px;z-index:999;'>");
		if($.browser.msie6){
			cpHA.push('<iframe style="position:absolute;z-index:-1;width:100%;height:205px;top:0;left:0;scrolling:no;" frameborder="0" src="about:blank"></iframe>');
		}
		cpHA.push("<table class='dp-maintable' cellspacing='0' cellpadding='0' style='width:175px;'><tbody><tr><td>");
		cpHA.push("<table class='bbit-dp-top' cellspacing='0'><tr><td class='bbit-dp-top-left'> <a id='BBIT_DP_LEFTBTN' href='javascript:void(0);' title='向前一个月'>&nbsp;</a></td><td class='bbit-dp-top-center' align='center'><em><button id='BBIT_DP_YMBTN_' title='点击修改'>九月 2009</button></em>&nbsp;&nbsp;<em><button id='BBIT_DP_YMBTN'>选择年月</button></em></td><td class='bbit-dp-top-right'><a id='BBIT_DP_RIGHTBTN' href='javascript:void(0);' title='向后一个月'>&nbsp;</a></td></tr></table>");
		cpHA.push("</td></tr>");
		cpHA.push("<tr><td>");
		cpHA.push("<table id='BBIT_DP_INNER' class='bbit-dp-inner' cellspacing='0'><thead><tr>");
		for(var i=def.weekStart,j=0;j<7;j++){
			cpHA.push("<th><span>",def.weekName[i],"</span></th>");
			if(i==6){i=0;}
			else{i++;}
		}
		cpHA.push("</tr></thead>");
		cpHA.push("<tbody></tbody></table>");
		cpHA.push("</td></tr>");
		cpHA.push("<tr><td class='bbit-dp-bottom' align='center'><button class='timeSelect' id='BBIT_DP_TimeBTN'>",def.inputTime,"</button><button id='BBIT-DP-TODAY'>",def.btnToday,"</button><button id='BBIT-DP-CLEAR'>",def.btnClear,"</button><button class='timeSelect' id='BBIT-DP-OKBtn'>",def.btnOk,"</button></td></tr>");
		cpHA.push("</tbody></table>");
		cpHA.push("<div id='BBIT-DP-MP' class='bbit-dp-mp'  style='z-index:auto;'><table id='BBIT-DP-T' style='width: 175px; height: 193px' border='0' cellspacing='0'><tbody>");cpHA.push("<tr>");cpHA.push("<td class='bbit-dp-mp-ybtn' align='middle'><a id='BBIT-DP-MP-PREV' class='bbit-dp-mp-prev'></a></td><td class='bbit-dp-mp-ybtn  bbit-dp-mp-sep' align='middle'><a id='BBIT-DP-MP-NEXT' class='bbit-dp-mp-next'></a></td><td class='bbit-dp-mp-month' xmonth='0'><a href='javascript:void(0);'>",def.monthName[0],"</a></td><td class='bbit-dp-mp-month' xmonth='6'><a href='javascript:void(0);'>",def.monthName[6],"</a></td>");
		cpHA.push("</tr>");
		cpHA.push("<tr>");
		cpHA.push("<td class='bbit-dp-mp-year'><a href='javascript:void(0);'></a></td><td class='bbit-dp-mp-year bbit-dp-mp-sep'><a href='javascript:void(0);'></a></td><td class='bbit-dp-mp-month' xmonth='1'><a href='javascript:void(0);'>",def.monthName[1],"</a></td><td class='bbit-dp-mp-month' xmonth='7'><a href='javascript:void(0);'>",def.monthName[7],"</a></td>");
		cpHA.push("</tr>");
		cpHA.push("<tr>");
		cpHA.push("<td class='bbit-dp-mp-year'><a href='javascript:void(0);'></a></td><td class='bbit-dp-mp-year bbit-dp-mp-sep'><a href='javascript:void(0);'></a></td><td class='bbit-dp-mp-month' xmonth='2'><a href='javascript:void(0);'>",def.monthName[2],"</a></td><td class='bbit-dp-mp-month' xmonth='8'><a href='javascript:void(0);'>",def.monthName[8],"</a></td>");
		cpHA.push("</tr>");
		cpHA.push("<tr>");
		cpHA.push("<td class='bbit-dp-mp-year'><a href='javascript:void(0);'></a></td><td class='bbit-dp-mp-year bbit-dp-mp-sep'><a href='javascript:void(0);'></a></td><td class='bbit-dp-mp-month' xmonth='3'><a href='javascript:void(0);'>",def.monthName[3],"</a></td><td class='bbit-dp-mp-month' xmonth='9'><a href='javascript:void(0);'>",def.monthName[9],"</a></td>");
		cpHA.push("</tr>");
		cpHA.push("<tr>");
		cpHA.push("<td class='bbit-dp-mp-year'><a href='javascript:void(0);'></a></td><td class='bbit-dp-mp-year bbit-dp-mp-sep'><a href='javascript:void(0);'></a></td><td class='bbit-dp-mp-month' xmonth='4'><a href='javascript:void(0);'>",def.monthName[4],"</a></td><td class='bbit-dp-mp-month' xmonth='10'><a href='javascript:void(0);'>",def.monthName[10],"</a></td>");
		cpHA.push("</tr>");
		cpHA.push("<tr>");
		cpHA.push("<td class='bbit-dp-mp-year'><a href='javascript:void(0);'></a></td><td class='bbit-dp-mp-year bbit-dp-mp-sep'><a href='javascript:void(0);'></a></td><td class='bbit-dp-mp-month' xmonth='5'><a href='javascript:void(0);'>",def.monthName[5],"</a></td><td class='bbit-dp-mp-month' xmonth='11'><a href='javascript:void(0);'>",def.monthName[11],"</a></td>");
		cpHA.push("</tr>");
		cpHA.push("<tr class='bbit-dp-mp-btns'>");
		cpHA.push("<td colspan='4'><button id='BBIT-DP-MP-OKBTN' class='bbit-dp-mp-ok'>",def.btnOk,"</button><button id='BBIT-DP-MP-CANCELBTN' class='bbit-dp-mp-cancel'>",def.btnCancel,"</button></td>");
		cpHA.push("</tr>");
		cpHA.push("</tbody></table>");
		cpHA.push("</div>");
		cpHA.push("<div id='BBIT-DP-MP-Time' class='bbit-dp-mp' style='z-index:auto'><table id='BBIT-DP-Time-T' style='width: 175px; height: 193x' border='0' cellspacing='0'><tbody>");
		cpHA.push("<tr><td id='BBIT-DP-MP-TimeTd' style='text-align:center;'><div id='BBIT-DP-MP-TimeShow' class='MPTimeShowDiv'><span class='hour'>00</span>:<span class='minute'>00</span></div>");
		cpHA.push("<table border='0' cellspacing='0' cellpadding='0' class='timeSettingT' align='center'>");
		cpHA.push("<tr><td  class='leftTD'><span id='hourSettingUp' class='timeSettingSpan'>▲</span></td><td style='width:6px'></td><td class='rightTD'><span id='minSettingUp' class='timeSettingSpan'>▲</span></td><td>&nbsp;</td></tr>");
		cpHA.push("<tr><td><div class='leftTD'><input id='timeHourInput' type='text' size='1' maxlength='2' value='00' style='ime-mode:Disabled' /></div></td>");
		cpHA.push("<td><div class='centerTD'>:</div></td>");
		cpHA.push("<td><div class='rightTD'><input id='timeMinInput' type='text' maxlength='2' size='1' value='00' tabindex='0' style='ime-mode:Disabled'/></div></td>");
		cpHA.push("<td><div class='btnTD'><button id='timeAmBtn'>",def.timeAM,"</button></div></td></tr>");
		cpHA.push("<tr><td ><span id='hourSettingDown' class='timeSettingSpan'>▼</span></td><td></td><td ><span id='minSettingDown'  class='timeSettingSpan'>▼</span></td><td >&nbsp;</td></tr>");
		cpHA.push("</table>");
		cpHA.push("</td></tr>");
		cpHA.push("<tr class='bbit-dp-mp-btns'><td><button id='BBIT-DP-MP-Time-OKBTN' class='bbit-dp-mp-ok'>",def.btnOk,"</button><button id='BBIT-DP-MP-Time-CANCELBTN' class='bbit-dp-mp-cancel'>",def.btnCancel,"</button></td>");
		cpHA.push("</tr>");
		cpHA.push("</tbody></table>");
		cpHA.push("</div>");
		cpHA.push("</div>");
		
		var s=cpHA.join("");
		
		$(document.body).append(s);
		cp=$("#epsDatepickerDiv");
		initevents();
	}
	
	//初始化事件
	function initevents(){
		$("#BBIT-DP-TODAY").click(returntoday);
		$("#BBIT-DP-CLEAR").click(returnclear);
		cp.click(returnfalse);
		$("#BBIT_DP_INNER tbody").click(tbhandler);
		$("#BBIT_DP_LEFTBTN").click(prevm);
		$("#BBIT_DP_RIGHTBTN").click(nextm);
		$("#BBIT_DP_YMBTN").click(showym);
		$('#BBIT_DP_TimeBTN').click(showTime);
		$('#BBIT-DP-MP-Time-OKBTN').click(showTimeOk);
		$("#BBIT-DP-MP-Time-CANCELBTN").click(showTimeCancel);
		$('#timeAmBtn').click(selectTimeBtn);
		$('#timeHourInput').blur(selectTimeH);
		$('#timeMinInput').blur(selectTimeM);
		$('#timeHourInput,#timeMinInput').keydown(onlyNum);
		$('#hourSettingUp').click(hourAdd);
		$('#hourSettingDown').click(hourLess);
		$('#minSettingUp').click(minuteAdd);
		$('#minSettingDown').click(minuteLess);
		$('.timeSettingSpan').hover(timeSettingOver,timeSettingOut);
		$('#BBIT-DP-OKBtn').click(returnSure);
		$("#BBIT-DP-MP").click(mpclick).dblclick(mpdblclick);
		$("#BBIT-DP-MP-PREV").click(mpprevy);
		$("#BBIT-DP-MP-NEXT").click(mpnexty);
		$("#BBIT-DP-MP-OKBTN").click(mpok);
		$("#BBIT-DP-MP-CANCELBTN").click(mpcancel);
	}

	function onlyNum(){
		var k=window.event.keyCode;
		if((k==46)||(k==8)||(k==9)||(k>=48&&k<=57)||(k>=96&&k<=105)||(k>=37&&k<=40)){}
		else if(k==13){
			window.event.keyCode=9;
		}
		else{window.event.returnValue=false;}
	}
	
	function returnSure(){
		closeObj();
	}
	
	function timeSettingOver(){
		$(this).addClass('hover')
	}
	function timeSettingOut() {
	    $(this).removeClass('hover')
	}
	function minuteAdd() {
	    var mv = Math.round(document.getElementById('timeMinInput').value);
	    if (mv < 59) {
	        document.getElementById('timeMinInput').value = mv + 1;
	    }
	    else {
	        document.getElementById('timeMinInput').value = 0;
	    }
	    changeMinute();
	}
	function minuteLess() {
	    var mv = Math.round(document.getElementById('timeMinInput').value);
	    if (mv > 0) {
	        document.getElementById('timeMinInput').value = mv - 1;
	    }
	    else {
	        document.getElementById('timeMinInput').value = 59;
	    }
	    changeMinute();
	}
	function hourLess() {
	    var hv = Math.round(document.getElementById('timeHourInput').value);
	    if (hv > 0) {
	        document.getElementById('timeHourInput').value = hv - 1;
	    }
	    else {
	        document.getElementById('timeHourInput').value = 23
	    }
	    changeHour();
	}

	function hourAdd() {
	    var hv = Math.round(document.getElementById('timeHourInput').value);
	    if (hv < 23) {
	        document.getElementById('timeHourInput').value = hv + 1;
	    }
	    else {
	        document.getElementById('timeHourInput').value = 0
	    }
	    changeHour();
	}
	function changeHour() {
	    var hv = Math.round(document.getElementById('timeHourInput').value);
	    if (hv < 12) {
	        $('#timeAmBtn').text(def.timeAM);
	    } else {
	        $('#timeAmBtn').text(def.timePM);
	    }
	    var sHv = hv > 9 ? hv: ('0' + hv);
	    document.getElementById('timeHourInput').value = sHv;
	    $('#BBIT-DP-MP-TimeShow span.hour').text(sHv);
	}
	function changeMinute() {
	    var mv = Math.round(document.getElementById('timeMinInput').value);
	    var sMv = mv > 9 ? mv: ('0' + mv);
	    document.getElementById('timeMinInput').value = sMv;
	    $('#BBIT-DP-MP-TimeShow span.minute').text(sMv);
	}
	function selectTimeH() {
	    if (this.value > 23) {
	        this.value = '00';
	        $(this).select();
	        return false;
	    }
	    changeHour();
	}
	function selectTimeM() {
	    if (this.value > 60) {
	        this.value = '00';
	        $(this).select();
	        return false;
	    }
	    changeMinute();
	}
	function selectTimeBtn() {
	    var hv = Math.round(document.getElementById('timeHourInput').value);
	    if (hv < 24) {
	        if (hv < 12) {
	            $('#timeHourInput').val(hv += 12);
	        } else {
	            $('#timeHourInput').val(hv - 12);
	        }
	    }
	    changeHour();
	}
	function showTimeCancel() {
	    $("#BBIT-DP-MP-Time").animate({
	        top: -193
	    },
	    {
	        duration: 200,
	        complete: function() {
	            $("#BBIT-DP-MP-Time").hide();
	        }
	    });
	    return false;
	}

	function showTime() {
	    var mp = $('#BBIT-DP-MP-Time');
	    var sHour = $(this).text().split(':')[0];
	    var sMin = $(this).text().split(':')[1];
	    document.getElementById('timeHourInput').value = sHour;
	    changeHour();
	    document.getElementById('timeMinInput').value = sMin;
	    changeMinute();
	    mp.css("top", -193).show().animate({
	        top: 0
	    },
	    {
	        duration: 200
	    });
	    $('#timeHourInput').select();
	}
	function mpcancel() {
	    $("#BBIT-DP-MP").animate({
	        top: -193
	    },
	    {
	        duration: 200,
	        complete: function() {
	            $("#BBIT-DP-MP").hide();
	        }
	    });
	    return false;
	}
	function mpok() {
	    def.Year = def.cy;
	    def.Month = def.cm + 1;
	    def.Day = 1;
	    $("#BBIT-DP-MP").animate({
	        top: -193
	    },
	    {
	        duration: 200,
	        complete: function() {
	            $("#BBIT-DP-MP").hide();
	        }
	    });
	    writecb();
	    return false;
	}
	function mpprevy() {
	    var y = def.ty - 10
	    def.ty = y;
	    rryear(y);
	    return false;
	}
	function mpnexty() {
	    var y = def.ty + 10
	    def.ty = y;
	    rryear(y);
	    return false;
	}
	function rryear(y) {
	    var s = y - 4;
	    var ar = [];
	    for (var i = 0; i < 5; i++) {
	        ar.push(s + i);
	        ar.push(s + i + 5);
	    }
	    $("#BBIT-DP-MP td.bbit-dp-mp-year").each(function(i) {
	        if (def.Year == ar[i]) {
	            $(this).addClass("bbit-dp-mp-sel");
	        }
	        else {
	            $(this).removeClass("bbit-dp-mp-sel");
	        }
	        $(this).html("<a href='javascript:void(0);'>" + ar[i] + "</a>").attr("xyear", ar[i]);
	    });
	}
	function mpdblclick(e) {
	    var et = e.target || e.srcElement;
	    var td = getTd(et);
	    if (td == null) {
	        return false;
	    }
	    if ($(td).hasClass("bbit-dp-mp-month") || $(td).hasClass("bbit-dp-mp-year")) {
	        mpok(e);
	    }
	    return false;
	}
	function mpclick(e) {
	    var panel = $(this);
	    var et = e.target || e.srcElement;
	    var td = getTd(et);
	    if (td == null) {
	        return false;
	    }
	    if ($(td).hasClass("bbit-dp-mp-month")) {
	        if (!$(td).hasClass("bbit-dp-mp-sel")) {
	            var ctd = panel.find("td.bbit-dp-mp-month.bbit-dp-mp-sel");
	            if (ctd.length > 0) {
	                ctd.removeClass("bbit-dp-mp-sel");
	            }
	            $(td).addClass("bbit-dp-mp-sel")
	            def.cm = parseInt($(td).attr("xmonth"));
	        }
	    }
	    if ($(td).hasClass("bbit-dp-mp-year")) {
	        if (!$(td).hasClass("bbit-dp-mp-sel")) {
	            var ctd = panel.find("td.bbit-dp-mp-year.bbit-dp-mp-sel");
	            if (ctd.length > 0) {
	                ctd.removeClass("bbit-dp-mp-sel");
	            }
	            $(td).addClass("bbit-dp-mp-sel")
	            def.cy = parseInt($(td).attr("xyear"));
	        }
	    }
	    return false;
	}
	function showym() {
	    var mp = $("#BBIT-DP-MP");
	    var y = def.Year;
	    def.cy = def.ty = y;
	    var m = def.Month - 1;
	    def.cm = m;
	    var ms = $("#BBIT-DP-MP td.bbit-dp-mp-month");
	    for (var i = ms.length - 1; i >= 0; i--) {
	        var ch = $(ms[i]).attr("xmonth");
	        if (ch == m) {
	            $(ms[i]).addClass("bbit-dp-mp-sel");
	        }
	        else {
	            $(ms[i]).removeClass("bbit-dp-mp-sel");
	        }
	    }
	    rryear(y);
	    mp.css("top", -193).show().animate({
	        top: 0
	    },
	    {
	        duration: 200
	    });
	}
	function getTd(elm) {
	    if (elm.tagName.toUpperCase() == "TD") {
	        return elm;
	    }
	    else if (elm.tagName.toUpperCase() == "BODY") {
	        return null;
	    }
	    else {
	        var p = $(elm).parent();
	        if (p.length > 0) {
	            if (p[0].tagName.toUpperCase() != "TD") {
	                return getTd(p[0]);
	            }
	            else {
	                return p[0];
	            }
	        }
	    }
	    return null;
	}
	function tbhandler(e) {
	    var et = e.target || e.srcElement;
	    var td = getTd(et);
	    if (td == null) {
	        return false;
	    }
	    var $td = $(td);
	    if (!$(td).hasClass("bbit-dp-disabled")) {
	        $('.bbit-dp-click').removeClass('bbit-dp-click');
	        $td.addClass('bbit-dp-click');
	        var s = $td.attr("xdate");
	        var arrs = s.split("-");
	        cp.data("indata", new Date(arrs[0], parseInt(arrs[1], 10) - 1, arrs[2]));
	        returndate();
	    }
	    return false;
	}
	function returnfalse() {
	    return false;
	}
	function prevm() {
	    if (def.Month == 1) {
	        def.Year--;
	        def.Month = 12;
	    }
	    else {
	        def.Month--
	    }
	    writecb();
	    return false;
	}
	function nextm() {
	    if (def.Month == 12) {
	        def.Year++;
	        def.Month = 1;
	    }
	    else {
	        def.Month++
	    }
	    writecb();
	    return false;
	}
	function returntoday() {
	    $('.bbit-dp-click').removeClass('bbit-dp-click');
	    cp.data("indata", new Date());
	    def.Year = new Date().getFullYear();
	    def.Month = new Date().getMonth() + 1;
	    def.Day = new Date().getDate();
	    writecb();
	    returndate();
	}
	function returnclear() {
		 var ct = cp.data("ctarget");
		 ct.val("");
	    closeObj();
	}
	function returndate() {
	    var ct = cp.data("ctarget");
	    var ck = cp.data("cpk");
	    var re = cp.data("onReturn");
	    var ndate = cp.data("indata")
	    var ads = cp.data("ads");
	    var ade = cp.data("ade");
	    var dis = false;
	    if (ads && ndate < ads) {
	        dis = true;
	    }
	    if (ade && ndate > ade) {
	        dis = true;
	    }
	    if (dis) {
	        return;
	    }
	    if (re && jQuery.isFunction(re)) {
	        re.call(ct[0], cp.data("indata"));
	    }
	    else {
	        if ($('#BBIT_DP_TimeBTN').is(':visible')) {
	            ct.val(cp.data("indata").Format("yyyy-MM-dd") + ' ' + $('#BBIT_DP_TimeBTN').text());
	        }
	        else {
	            ct.val(cp.data("indata").Format("yyyy-MM-dd"));
	            closeObj();
	        }
	    }
	}

	var dateReg=/^(\d{1,4})(-|\/|.)(\d{1,2})\2(\d{1,2})$/;
	function showTimeOk(){
		$('#BBIT_DP_TimeBTN').text($('#timeHourInput').val()+':'+$('#timeMinInput').val()+':00');
		var ct=cp.data("ctarget");
		if(ct.val()!=""){
			var tdate=ct.val().split(' ')[0];
			v=tdate.match(dateReg);
			if(v==null||v==""){ct.val('');}
			else{
				ct.val(tdate+' '+$('#BBIT_DP_TimeBTN').text());
			}
		}
		showTimeCancel();
	}
	
	//关闭面板
	function closeObj() {
	    var ct = cp.data("ctarget");
	    var ck = cp.data("cpk");
	    ck.attr("isshow", "0");
	    cp.removeData("ctarget").removeData("cpk").removeData("indata").removeData("onReturn").removeData("ads").removeData("ade");
	    cp.css("visibility", "hidden");
	    ct = ck = null;
	}
	function writecb() {
	    var tb = $("#BBIT_DP_INNER tbody");
	    $("#BBIT_DP_YMBTN_").html(def.Year + def.yearStr + " " + def.monthName[def.Month - 1] + def.monthp);
	    var firstdate = new Date(def.Year, def.Month - 1, 1);
	    var diffday = def.weekStart - firstdate.getDay();
	    var showmonth = def.Month - 1;
	    if (diffday > 0) {
	        diffday -= 7;
	    }
	    var startdate = DateAdd("d", diffday, firstdate);
	    var enddate = DateAdd("d", 42, startdate);
	    var ads = cp.data("ads");
	    var ade = cp.data("ade");
	    var bhm = [];
	    var tds = def.today.Format("yyyy-MM-dd");
	    var indata = cp.data("indata");
	    var ins = indata != null ? indata.Format("yyyy-MM-dd") : "";
	    for (var i = 1; i <= 42; i++) {
	        if (i % 7 == 1) {
	            bhm.push("<tr>");
	        }
	        var ndate = DateAdd("d", i - 1, startdate);
	        var tdc = [];
	        var dis = false;
	        if (ads && ndate < ads) {
	            dis = true;
	        }
	        if (ade && ndate > ade) {
	            dis = true;
	        }
	        if (ndate.getMonth() < showmonth) {
	            tdc.push("bbit-dp-prevday");
	        }
	        else if (ndate.getMonth() > showmonth) {
	            tdc.push("bbit-dp-nextday");
	        }
	        if (dis) {
	            tdc.push("bbit-dp-disabled");
	        }
	        else {
	            tdc.push("bbit-dp-active");
	        }
	        var s = ndate.Format("yyyy-MM-dd");
	        if (s == tds) {
	            tdc.push("bbit-dp-today");
	        }
	        if (s == ins) {
	            tdc.push("bbit-dp-selected");
	        }
	        bhm.push("<td class='", tdc.join(" "), "' title='", ndate.Format("yyyy-MM-dd"), "' xdate='", ndate.Format("yyyy-M-d"), "'><a href='javascript:void(0);'><em><span>", ndate.getDate(), "</span></em></a></td>");
	        if (i % 7 == 0) {
	            bhm.push("</tr>");
	        }
	    }
	    tb.html(bhm.join(""));
	}
	return $(this).each(function(){
		var obj=$(this).addClass("sysicon").addClass("siDate").attr('readonly','readonly');
		var picker = (def.picker) ? $(def.picker).insertAfter(obj) : obj;
		picker.click(function(e) {
		    var isshow = $(this).attr("isshow");
		    var me = $(this);
		    if (cp.css("visibility") == "visible") {
		        cp.css(" visibility", "hidden");
		    }
		    if (isshow == "1") {
		        me.attr("isshow", "0");
		        cp.removeData("ctarget").removeData("cpk").removeData("indata").removeData("onReturn");
		        return false;
		    }
		    var v = obj.val();
		    var vSp = v.split(' ')[0];
		    var vSpT = v.split(' ')[1];
		    if (v != "") {
		        if (def.timeShow) {
		            v = vSp
		        }
		        v = v.match(dateReg);
		    }
		    if (v == null || v == "") {
		        def.Year = new Date().getFullYear();
		        def.Month = new Date().getMonth() + 1;
		        def.Day = new Date().getDate();
		        var h = new Date().getHours();
		        var m = new Date().getMinutes();
		        h = h > 9 ? h: ('0' + h);
		        m = m > 9 ? m: ('0' + m)
		        $('#BBIT_DP_TimeBTN').text(h + ':' + m + ':00');
		        def.inputDate = null
		    }
		    else {
		        def.Year = parseInt(v[1], 10);
		        def.Month = parseInt(v[3], 10);
		        def.Day = parseInt(v[4], 10);
		        def.inputDate = new Date(def.Year, def.Month - 1, def.Day);
		    }
		    cp.data("ctarget", obj).data("cpk", me).data("indata", def.inputDate).data("onReturn", def.onReturn);
		    if (def.applyRule && $.isFunction(def.applyRule)) {
		        var rule = def.applyRule.call(obj, obj[0].id);
		        if (rule) {
		            if (rule.startdate) {
		                cp.data("ads", rule.startdate);
		            }
		            else {
		                cp.removeData("ads");
		            }
		            if (rule.enddate) {
		                cp.data("ade", rule.enddate);
		            }
		            else {
		                cp.removeData("ade");
		            }
		        }
		    }
		    else {
		        cp.removeData("ads").removeData("ade")
		    }
		    writecb();
		    $('#BBIT_DP_TimeBTN').text(vSpT);
		    if (def.timeShow) {
		        $('.timeSelect').show()
		    } else {
		        $('.timeSelect').hide()
		    }
		    $("#BBIT-DP-T,#BBIT-DP-Time-T").height(cp.height());
		    $('#BBIT-DP-MP-TimeTd').height(cp.height() - 38);
		    var t = obj;
		    var pos = t.offset();
		    var height = t.outerHeight();
		    var newpos = {
		        left: pos.left,
		        top: pos.top + height
		    };
		    var w = cp.width();
		    var h = cp.height();
		    var bw = document.documentElement.clientWidth;
		    var bh = document.documentElement.clientHeight;
		    if ((newpos.left + w) >= bw) {
		        newpos.left = bw - w - 2;
		    }
		    if ((newpos.top + h) >= bh) {
		        newpos.top = pos.top - h - 2;
		    }
		    if (newpos.left < 0) {
		        newpos.left = 10;
		    }
		    if (newpos.top < 0) {
		        newpos.top = 10;
		    }
		    $("#BBIT-DP-MP").hide();
		    newpos.visibility = "visible";
		    cp.css(newpos);
		    $(this).attr("isshow", "1");
		    $(document).one("click", 
		    function(e) {
		        me.attr("isshow", "0");
		        cp.removeData("ctarget").removeData("cpk").removeData("indata");
		        cp.css("visibility", "hidden");
		    });
		    return false;
		});
	});
};
})(jQuery);