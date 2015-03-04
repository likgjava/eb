/*
++++++++
+ 
+ 取当前时间日期
+ hello: 问候语
+ 
++++++++*/
function date_nowTime(gpcsoftDate,type){	
	s = s+1;    
	ss = parseInt(s);   
	if (ss>59){ 
		ss = 0;   
	    s = 0;   
	    m++;   
	}   
	if(m>59){   
		m = 0;   
	    h++;   
	}   
	d = gpcsoftDate.getDate();   
	M = gpcsoftDate.getMonth();   
	if(h>23){   
	    h=0;   
	    d++;   
	}   
	gpcsoftDate.setDate(d);   
	dd = gpcsoftDate.getDate();   
	if (dd<d){   
		gpcsoftDate.setMonth(M+1);   
	}   
//	MM = gpcsoftDate.getMonth(); 
	MM = gpcsoftDate.getMonth()+1; 
	YYYY = gpcsoftDate.getFullYear(); 
	hh=h;   
	mm=m;   
	
	//问候语设定
	if (hh >= 0 && hh <= 6) hello = "辛苦了！";
	if (hh >= 6 && hh <= 8) hello = "您真早！";
	if (hh > 8 && hh <= 12) hello = "上午好！";
	if (hh > 12 && hh <= 18) hello = "下午好！";
	if (hh > 18 && hh <= 22) hello = "晚上好！";
	if (hh > 22 && hh <= 24) hello = "注意休息！";

	if (hh==0){hh=12} 
	(mm <=9) ? (mm="0"+mm) : mm;
	(ss <=9) ? (ss="0"+ss) : ss;
	
	if(!type || type == '0') {
		$(".nowTime").text(' '+hh+':'+ mm + ':'+ ss +' ');
		$(".nowDate").text(' '+YYYY+'.'+MM+'.'+dd+' ');
		$(".hello").text(' '+hello+' ');
	}else if(type == '1') {
		return YYYY+'-'+MM+'-'+dd+' '+hh+':'+ mm + ':'+ ss;
	}
//	$('#nowTime').text(' '+YYYY+'年'+MM+'月'+dd+'日  '+hh+':'+ mm + ':'+ ss);
}