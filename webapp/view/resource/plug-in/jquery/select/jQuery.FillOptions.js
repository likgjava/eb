/*
* jQuery FillOptions
*
* Author: luq885
* http://blog.csdn.net/luq885 (chinese) 
*
* Licensed like jQuery, see http://docs.jquery.com/License
*
* 作者：天天无用
* blog: http://blog.csdn.net/luq885
*/

var text;
var value;
var type;
var selected;
var keep;

jQuery.fn.FillOptions = function(url,option,callback){
    if(url.length == 0) throw "request is required";        
    text = option.textfield || "name";
    value = option.valuefiled || "objId";    
    isfirstselect = option.isfirstselect || "yes";
    type = "json";
    if(option.datatype) type = option.datatype.toLowerCase();
    if(type != "xml")type="json";
    keep = option.keepold?true:false;
    selected = option.selectedindex || 0;
    
    var select;
    this.each(function(){
        if(this.tagName == "SELECT")
        {
            select = this;
        }
    });
    
    var datas;
    if(type == "xml")
    {
        $.get(url,function(xml){
        	datas=xml;
        	
        	if(datas == undefined)
        		return;
        	
        	if(select.tagName == "SELECT")
            {
                if(!keep)$(select).html("");
                addOptions(select,datas,isfirstselect);
                
                //回调函数
                if(typeof callback == "function"){
                	callback();
                }
                
                return datas;//by wangsw
            }
        });            
    }
    else
    {
        $.getJSON(url,function(json){
        	datas=json;
        	
        	if(datas == undefined)
        		return;
        	if(select.tagName == "SELECT")
            {
                if(!keep)$(select).html("");
                addOptions(select,datas,isfirstselect);
                
                //回调函数
                if(typeof callback == "function"){
                	callback(datas);
                }
                
                return datas;//by wangsw
            }
        });
    }
}


function addOptions(select,datas,isfirstselect)
{        
    var options;
    var datas;
    if(type == "xml")
    {
        $(text,datas).each(function(i){            
            option = new Option($(this).text(),$($(value,datas)[i]).text());
            //if(i==selected)option.selected=true;
            select.options.add(option);
        });
    }
    else
    {
    	var firstOption=new Option('请选择','');
    	if(isfirstselect=="yes"){
	    	//firstOption.selected=true;
	    	select.options.add(firstOption);
    	}
    	var data = datas.result;
    	if(!data)
    		data = datas.list;
        $.each(data,function(i,n){
            option = new Option(eval("n."+text),eval("n."+value));
            //by sunl
            if(eval("n."+text)==undefined) {
            	option = new Option(n[1],n[0]);
            }
            select.options.add(option);
        });
    }
}