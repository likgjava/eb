//约束时间规则 开始
function endRule(id) {
if(!$("#"+ id).next("input.siDate") || !$("#"+ id).next("input.siDate").val()) return null;

var v = $("#"+ id).next("input.siDate").val().split(' ')[0];

if (v == "") {return null;}
    else {
        var d = v.match(/^(\d{1,4})(-|\/|.)(\d{1,2})\2(\d{1,2})$/);
        if (d != null) {
            var nd = new Date(parseInt(d[1], 10), parseInt(d[3], 10) - 1, parseInt(d[4], 10));
            return { enddate: nd };
        }
        else {return null;}
    }
}
function startRule(id) {
	if(!$("#"+ id).prev("input.siDate") || !$("#"+ id).prev("input.siDate").val()) return null;

	var v = $("#"+ id).prev("input.siDate").val().split(' ')[0];

	if (v == "") {return null;}
	    else {
	        var d = v.match(/^(\d{1,4})(-|\/|.)(\d{1,2})\2(\d{1,2})$/);
	        if (d != null) {
	            var nd = new Date(parseInt(d[1], 10), parseInt(d[3], 10) - 1, parseInt(d[4], 10));
	            return { startdate: nd };
	        }
	        else {return null;}
	    }
	}
//约束时间规则 结束

//获得折扣率
function getDscuRate(marktPrice,prtcPrice){
	return formatAmount(Number(prtcPrice.replace(/,/g,''))*100/Number(marktPrice.replace(/,/g,'')),2);
}

//获得协议价
function getPrtcPrice(marktPrice,dscuRate){
	return formatAmount(Number(marktPrice.replace(/,/g,''))*(Number(dscuRate)/100),2);
}

//验证唯一性方法  其中objId指在修改的时候排除本身记录
function uniqueHandler (domain,param,value,objId){
	var jsonObj={};
	jsonObj["className"]=domain;
	jsonObj["property"]=param;
	jsonObj["value"]=value;
	if(objId) jsonObj["objId"]=objId;
	var isUnique = false;
	$.ajax({
		url:$('#initPath').val()+"/UniqueController.do?method=isUnique",
		type:"POST",
		async:false,
		data:jsonObj,
		success:function(result){
			alert(result);
			isUnique=eval(result);
		}
	});
	alert(isUnique);
	return isUnique;
}





//循环表单元素显示历史值
function displayHistoryObject(jsonObj,formId) { 
	var history = jsonObj.historyObject;  //历史对象
	$('#'+formId).find('span').each(function(i,n){
		if($(n).attr('id') && $(n).text() != "") {
			var key = $(n).attr('id');
			if(history[key] && history[key] != $(n).text()) {
				$(n).addClass("modifier").attr('title','变更前[' + history[key] + ']');
			}
		}
	})
} 

//检测表单元素变化
function checkFormChange(oldJsonObj,formId) { 
	var returnValue = false;
	//循环表单元素
	$('#'+formId).find('input,textarea,select').each(function(i,n){
		if($(n).attr('name')) {
			var key = $(n).attr('name');
			var type = $(n).attr('type');
			var formValue = $(n).val();
			if(key.indexOf('_1') != -1){
				key = key.substring(0,key.length-2);
				formValue = $('#'+key).val();
			}
			var objectValue = "";
			if(type != 'hidden'){
				if(type.indexOf('select') != -1){
					formValue = $(n).getSelectedValue();
					key = $(n).attr('id');
				}
				if(key.indexOf('.') != -1){
					objectValue = eval("oldJsonObj."+key)==undefined?"":eval("oldJsonObj."+key);
				}else{
					objectValue = oldJsonObj[key]==undefined?"":oldJsonObj[key];
				}
				if(objectValue != formValue.replace(/\s+/g,"")) {
					returnValue = true;
				}
			}
		}
	})
	return returnValue;
} 

//图片预览方法
function preViewPic(obj) { 
    if(obj)    { 
        //ie 
        if (window.navigator.userAgent.indexOf("MSIE")>=1) { 
            obj.select(); 
            return document.selection.createRange().text; 
        } 
        //firefox 
        else if(window.navigator.userAgent.indexOf("Firefox")>=1) 
        { 
            if(obj.files) 
            { 
                return obj.files.item(0).getAsDataURL(); 
            } 
            return obj.value; 
        } 
        return obj.value; 
    } 
}

//验证唯一性方法  提供url以及jsonObj参数
function bizUniqueHandler (url,jsonObj){
	$.ajax({
		url:url,
		type:"POST",
		async:false,
		data:jsonObj,
		success:function(result){
			isUnique=eval(result);
		}
	});
	return isUnique;
}

//验证唯一性方法  其中objId指在修改的时候排除本身记录
function uniqueHandler (domain,param,value,objId){
	var jsonObj={};
	jsonObj["className"]=domain;
	jsonObj["property"]=param;
	jsonObj["value"]=value;
	if(objId) jsonObj["objId"]=objId;
	var isUnique = false;
	$.ajax({
		url:$('#initPath').val()+"/UniqueController.do?method=isUnique",
		type:"POST",
		async:false,
		data:jsonObj,
		success:function(result){
			isUnique=eval(result);
		}
	});
	return isUnique;
}
//验证唯一性方法  提供url以及jsonObj参数
function bizUniqueHandler (url,jsonObj){
	$.ajax({
		url:url,
		type:"POST",
		async:false,
		data:jsonObj,
		success:function(result){
			isUnique=eval(result);
		}
	});
	return isUnique;
}

//禁止输入非法数据options:{reg:'',type:''}	reg正则表达式,type(int,float)类型,  默认为    float, 优先计算reg表达式为准.		by wangsw
$.fn.inputFillter=function(options){
	var reg = /^[\+\-]?\d*?\.?\d*?$/;
	
	//设定传入的正则表达式
	if(options.reg) reg = options.reg;
	else {
		if(options.type == "int") reg = /^\d+$/;
	}
	$(this).keyup(function(e){
		if(!this.value.match(reg))
			this.value="";
	});
};

//循环表单元素显示历史值
function displayHistoryObject(jsonObj,formId) { 
	var history = jsonObj.historyObject;  //历史对象
	$('#'+formId).find('span').each(function(i,n){
		if($(n).attr('id') && $(n).text() != "") {
			var key = $(n).attr('id');
			if(history[key] && history[key] != $(n).text()) {
				$(n).addClass("modifier").attr('title','变更前[' + history[key] + ']');
			}
		}
	})
} 

//js代码触发点击等事件，兼容IE和火狐 .      参数： 元素ID , 事件名
function fireEvent(controlID, eventName){
  if (document.all)    // For IE.
  {
      eval("document.getElementById(\"" + controlID + "\")." + eventName + "();");
  }else    // For Nescape
  {
      var e = document.createEvent('HTMLEvents');
      e.initEvent(eventName, false, false);
      document.getElementById(controlID).dispatchEvent(e);
  }
}

//格式化金额 增加千分位 1,234,567.00 
function formatAmount(nAmt, length){
	if(!length)	length=0;
	var isPartten = /^-?(?:\d+|\d{1,3}(?:,\d{3})+)(?:\.\d+)?$/.test(nAmt);
	if(!isPartten || !nAmt || nAmt=='' ){
		return parseFloat('0').toFixed(length);
	}
	nAmt = nAmt.toString().replace(/,/g,"");

	var strAmt = parseFloat(nAmt).toFixed(length)+"";
	return strAmt.replace(/(\d{1,3})(?=(\d{3})+(?:$|\D))/g,"$1,")
}

//格式化单行溢出，省略显示
function formatOmission(nAmt, length){
	if(!length)	length=0;
	if(nAmt.length > length){
		nAmt = nAmt.toString().substring(0,length)+"...";
	}
	return nAmt;
}

//金额大写转换
function DX(currencyDigits) {
	// Constants:
	var MAXIMUM_NUMBER = 99999999999.99;
	// Predefine the radix characters and currency symbols for output:
	var CN_ZERO = "零";
	var CN_ONE = "壹";
	var CN_TWO = "贰";
	var CN_THREE = "叁";
	var CN_FOUR = "肆";
	var CN_FIVE = "伍";
	var CN_SIX = "陆";
	var CN_SEVEN = "柒";
	var CN_EIGHT = "捌";
	var CN_NINE = "玖";
	var CN_TEN = "拾";
	var CN_HUNDRED = "佰";
	var CN_THOUSAND = "仟";
	var CN_TEN_THOUSAND = "万";
	var CN_HUNDRED_MILLION = "亿";
	//var CN_SYMBOL = "￥:";
	var CN_SYMBOL="";
	var CN_DOLLAR = "元";
	var CN_TEN_CENT = "角";
	var CN_CENT = "分";
	var CN_INTEGER = "整";

	// Variables:
	var integral;	// Represent integral part of digit number.
	var decimal;	// Represent decimal part of digit number.
	var outputCharacters;	// The output result.
	var parts;
	var digits, radices, bigRadices, decimals;
	var zeroCount;
	var i, p, d;
	var quotient, modulus;

	// Validate input string:
	currencyDigits = currencyDigits.toString();
	if (currencyDigits == "") {
		//alert("请输入要转换的数字!");
		return "";
	}
	if (currencyDigits.match(/[^,.\d]/) != null) {
		//alert("数字中含有非法字符!");
		return "";
	}
	if ((currencyDigits).match(/^((\d{1,3}(,\d{3})*(.((\d{3},)*\d{1,3}))?)|(\d+(.\d+)?))$/) == null) {
		//alert("错误的数字格式!");
		return "";
	}

	// Normalize the format of input digits:
	currencyDigits = currencyDigits.replace(/,/g, "");	// Remove comma delimiters.
	currencyDigits = currencyDigits.replace(/^0+/, "");	// Trim zeros at the beginning.
	// Assert the number is not greater than the maximum number.
	if (Number(currencyDigits) > MAXIMUM_NUMBER) {
		//alert("超出转换最大范围!");
		return "";
	}

	// Process the coversion from currency digits to characters:
	// Separate integral and decimal parts before processing coversion:
	parts = currencyDigits.split(".");
	if (parts.length > 1) {
		integral = parts[0];
		decimal = parts[1];
		// Cut down redundant decimal digits that are after the second.
		decimal = decimal.substr(0, 2);
	}
	else {
		integral = parts[0];
		decimal = "";
	}
	// Prepare the characters corresponding to the digits:
	digits = new Array(CN_ZERO, CN_ONE, CN_TWO, CN_THREE, CN_FOUR, CN_FIVE, CN_SIX, CN_SEVEN, CN_EIGHT, CN_NINE);
	radices = new Array("", CN_TEN, CN_HUNDRED, CN_THOUSAND);
	bigRadices = new Array("", CN_TEN_THOUSAND, CN_HUNDRED_MILLION);
	decimals = new Array(CN_TEN_CENT, CN_CENT);
	// Start processing:
	outputCharacters = "";
	// Process integral part if it is larger than 0:
	if (Number(integral) > 0) {
		zeroCount = 0;
		for (i = 0; i < integral.length; i++) {
			p = integral.length - i - 1;
			d = integral.substr(i, 1);
			quotient = p / 4;
			modulus = p % 4;
			if (d == "0") {
				zeroCount++;
			}
			else {
				if (zeroCount > 0)
				{
					outputCharacters += digits[0];
				}
				zeroCount = 0;
				outputCharacters += digits[Number(d)] + radices[modulus];
			}
			if (modulus == 0 && zeroCount < 4) {
				outputCharacters += bigRadices[quotient];
			}
		}
		outputCharacters += CN_DOLLAR;
	}
	// Process decimal part if there is:
	if (decimal != "") {
		for (i = 0; i < decimal.length; i++) {
			d = decimal.substr(i, 1);
			if (d != "0") {
				outputCharacters += digits[Number(d)] + decimals[i];
			}
		}
	}
	// Confirm and return the final output string:
	if (outputCharacters == "") {
		outputCharacters = CN_ZERO + CN_DOLLAR;
	}
	if (decimal == "") {
		outputCharacters += CN_INTEGER;
	}
	outputCharacters = CN_SYMBOL + outputCharacters;
	return outputCharacters;
}

//调整页面布局
//调整搜索栏的位置
function fnAdjustSearch(){
	$("div.search").addClass("search-other");
}
//隐藏左边栏
function fnHiddenSub(){
	fnHiddenSubAndSupp();
	$("#contentMain").removeClass("full");  //移除中间的样式
	$("#contentSupp").removeClass("hidden").addClass("index2paR"); 
}

//隐藏左边栏(不删除)
function fnHiddenSubNoRemove(){
	fnHiddenSubAndSuppNoRemove();
	$("#contentMain").removeClass().addClass("index2paL");  //移除中间的样式
	$("#contentSupp").removeClass("hidden").addClass("index2paR"); 
}
//隐藏右边栏(不删除)
function fnHiddenSuppNoRemove(){
	fnHiddenSubAndSuppNoRemove();
	$("#contentMain").removeClass("full");   //移除中间的样式
	$("#contentSub").removeClass("hidden"); 
}

//显示左边菜单
function fnShowSub(){
	fnHiddenSubAndSupp();
	$("#contentMain").removeClass("full");  //移除中间的样式
	$("#contentSub").removeClass("hidden").addClass("index3pa");
	$("#conTitle .navCurrent").removeClass("hidden");
}
//隐藏右边栏
function fnHiddenSupp(){
	fnHiddenSubAndSupp();
	$("#contentMain").removeClass("full");  //移除中间的样式
	$("#contentSub").removeClass("hidden"); 
}
//隐藏左右栏
function fnHiddenSubAndSupp(){
	fnRemoveOtherMain();
	$("#contentSub").removeClass().empty().addClass("hidden");  //隐藏左边
	$("#contentSupp").removeClass().empty().addClass("hidden");  //隐藏右边
	$("#contentMain").removeClass().addClass("index3pa").addClass("full");  //移除中间的样式
	$("conBody").empty();//移除主内容
	$("#conTitle .navCurrent").addClass("hidden").empty();  //隐藏导航栏
}

//隐藏左右栏(不删除)
function fnHiddenSubAndSuppNoRemove(){
	fnRemoveOtherMain();
	$("#contentSub").removeClass().addClass("hidden");  //隐藏左边
	$("#contentSupp").removeClass().addClass("hidden");  //隐藏右边
	$("#contentMain").removeClass().addClass("full");  //移除中间的样式
	$("conBody").empty();//移除主内容
}

//去掉首页多余的sysContent以及广告位
function fnRemoveOtherMain(){
	$('div[id=sysContent]:gt(0)').remove();
	$('div.container:gt(0)').remove();
	$('.advertising').remove();
} 

//显示配件、选配
var goodsAPI={//参数:oSettings、数量变量名、价格变量名、配件变量名、配件变量名、选配变量名、每行总金额名
		'fnSumTotal':function(oSettings,goodsTotal){
			if(!goodsTotal) goodsTotal="goodsTotal";
			var aoData=oSettings.aoData;
			var total = 0;
			for(var i=0; i<aoData.length; i++){
				//*1可以将字符串转换成数字
				total = total*1 + aoData[i]._aData[goodsTotal]*1;
			}
			return formatAmount(total,2);
		},
		'fnCaculateGoodsAmount':function(oSettings,qty,price,additional,option,amount,marketPriceStr){	 
			var totalAll=0.00;//总金额
			var totalAllSave=0.00;//总金额
			var totoalGoods=0;
			$('#'+oSettings.sInstance+' tr[type=goods]').each(function(i,nTr){	
				var total=0;
				//计算商品的金额
				var qtyDom=$(this).find('td[name='+qty+']');    
				var quantity=parseInt((qtyDom.find('input').length==0?qtyDom.text():qtyDom.find('input').val()).replace(/,/g,''));
				var priceDom=$(this).find('td[name='+price+']');   
				var agreementPrice=parseFloat((priceDom.find('input').length==0?priceDom.text():priceDom.find('input').val()).replace(/,/g,''));
				var marketPrice=0.0;
				if(marketPriceStr)
					marketPrice=parseFloat($(this).find('td[name='+marketPriceStr+']').html().replace(/,/g,''));
				var goodsPrice=quantity*agreementPrice;	
				totoalGoods+=quantity;
				totalAllSave+=quantity*(marketPrice-agreementPrice);//计算商品节省的
				  
				//累计选配的金额
				var optionPrice=0;//商品ID->配件
				$('#'+oSettings.sInstance).find('tr[goodsitemid='+$(nTr).attr('objId')+'][type='+option+']').each(function(i, n){
					var qtyDom=$(this).find('td[name='+qty+']');    
					var quantity=parseInt((qtyDom.find('input').length==0?qtyDom.text():qtyDom.find('input').val()).replace(/,/g,''));
					var marketPrice=parseFloat($(this).find('td[name='+marketPriceStr+']').html().replace(/,/g,''));
					
					var priceDom=$(this).find('td[name='+price+']');   
					var agreementPrice=parseFloat((priceDom.find('input').length==0?priceDom.text():priceDom.find('input').val()).replace(/,/g,''));
					optionPrice+=quantity*agreementPrice;	
					totalAllSave+=quantity*marketPrice-optionPrice;//计算节省的选配
				});
				goodsPrice+=optionPrice;
				totalAll+=goodsPrice;
				goodsPrice=formatAmount(goodsPrice,2);
				var rowPrice=$(this).find('td[name='+amount+']');
				rowPrice.html(goodsPrice);
				if(!rowPrice.attr('value'))	rowPrice.attr('value',goodsPrice);
			}); 
			return [formatAmount(totalAll,2),formatAmount(totalAllSave,2),formatAmount(totoalGoods)];
		},
		'fnOpenClose':function(oSettings){//构造+-图标以及显示商品的选配、配件收缩事件注册	参数：oSettings
 
			var nCloneTh = document.createElement( 'th' );
			nCloneTh.className='empty';
			$('#'+oSettings.sInstance+' thead tr').each( function () {
				if($('#'+oSettings.sInstance+' thead tr th[className=empty]').length==0){	
					if(oSettings.checkbox)//如果有复选框
						$(this).find('th:eq(0)').after(nCloneTh.cloneNode(true));
					else
						$(nCloneTh.cloneNode(true)).insertBefore($(this).find('th:eq(0)'));
				}
			});
//			加入展开/收缩图标
			var nCloneTd = document.createElement( 'td' );
			nCloneTd.innerHTML = '<img src='+$('#initPath').val()+'/view/resource/plug-in/jquery/dataTables/images/details_close.png'+' />';
			nCloneTd.className = "center img";
			$('#'+oSettings.sInstance+' tbody tr').each(function(i,n){
				if(oSettings.checkbox)	$(this).find('td:eq(0)').after(nCloneTd.cloneNode(true));
				else	$(nCloneTd.cloneNode(true)).insertBefore($(this).find('td:eq(0)'));
			});
			
			$('#'+oSettings.sInstance+' tbody td img').each( function () {
				var nTr = this.parentNode.parentNode;
				$(this).click(function(){
					var goodsid=$(nTr).attr('objId');	
					if( this.src.match('details_close') ){//已经关闭则变为可以打开
						this.src = $('#initPath').val()+'/view/resource/plug-in/jquery/dataTables/images/details_open.png';
						$('#'+oSettings.sInstance).find('tr[type=additional][goodsid='+goodsid+'],tr[type=option][goodsid='+goodsid+']').hide();//收起选配、配件
						$(this).parent().parent().find('td').attr('rowspan',1);
					}else{//已经打开则变为可以关闭
						this.src = $('#initPath').val()+'/view/resource/plug-in/jquery/dataTables/images/details_close.png';
						//收起选配、配件	  nTrs是一行商品的选配、配件
						var nTrs=$('#'+oSettings.sInstance).find('tr[type=additional][goodsid='+goodsid+'],tr[type=option][goodsid='+goodsid+']').show();
						var rowspan=$(nTr).attr('rowspanNum');//要跨越的行数
						if(oSettings.checkbox)
							$(nTr).find('td:eq(0)').attr('rowspan', rowspan);//复选框位置补偿
						$(this).parent().attr('rowspan', rowspan);//图片收缩/展开位置补偿
						$(this).parent().parent().find('td[rowspanNum='+rowspan+']').attr('rowspan', rowspan);
					}
				});
			});
		},
		'fnDisplayAdditionalAndOption':function(oSettings, additional, option, optionName, rowColspan, appendNumTr){//参数：oSettings
			//显示配件、选配
			$('#'+oSettings.sInstance+' tr[type=goods]').each(function(i,nRow){
				var additionalList=oSettings.aoData[i]._aData.goods.goodsParamSet;//配件数组
				var optionList=eval('oSettings.aoData['+i+']._aData.'+optionName);//选配数组
				var arrayLength=option?option.length:0;
				var rowspan=1;//一行要跨越几列？
				var html='';
				$.each(additional,function(j, k){
					$(nRow).find('td[name='+k.name+']').attr('width',k.width);
				});
				//显示可选配件
				if(additionalList){}//colspan='+(option.length+1)+'
				if(optionList && optionList.length>0){		
					rowspan=optionList.length+1;
					html+='<tr class='+$(nRow).attr('class')+' goodsitemid='+oSettings.aoData[i]._aData.objId+' goodsid='+oSettings.aoData[i]._aData.goods.objId+'>';	  
					html+='<td colspan='+(arrayLength+1)+' valign=top rowspan='+(optionList.length+1)+' ><table class=goodsList2 goodsid='+$(nRow).attr('objId')+'>';
					html+='<tr><td colspan='+(arrayLength+1)+'>'+(option[0].preText?option[0].preText:'')+'</td></tr>';
					$.each(optionList,function(ii,nn){
						html+='<tr type=option objId='+nn.objId+' goodsid='+oSettings.aoData[i]._aData.goods.objId+' optionitemid='+nn.objId+' goodsitemid='+$(nRow).dtGetRowIdByTr()+'>';
						$.each(option,function(iii, nnn){
							var width=$('#'+oSettings.sInstance+' thead th[abbr='+nnn.name+']').width();
							if(iii!=0){
								if(nnn.type&&nnn.type=='text'){	
										html+='<td width='+width+' name='+nnn.name+'><input name='+nn[nnn.name]+' type=text value='+nn[nnn.property]+' size='+(nnn.size?nnn.size:'');
										if(nnn.event)	html+= ' eventName='+nnn.event.name+' action='+nnn.event.action;
										html+= ' /></td>';
								}else
									html+='<td width='+width+' name='+nnn.name+'>'+nn[nnn.property]+'</td>';
							}else
								html+='<td colspan=2 width='+width+' name='+nnn.name+'>'+nn.option.optionContent+'</td>';
							
						});
						html+='</tr>';
					});
				}
				
				html+='</table></td><td rowspan=2></td><td rowspan=2></td></tr>';
				for(var j=0;j<appendNumTr;j++)//补右边框后面的tr
					html+='<tr></tr>';
				$(html).insertAfter(nRow);
				$('#'+oSettings.sInstance+' tbody table:last tr input:text').each(function(i,n){
					if($(this).attr('eventName')!=undefined){
                        var objId=$(this).parent().parent().attr("objId");	
                        var type=$(this).parent().parent().attr("type");
						var eventStr='eval("'+$(this).attr('action')+'(this,\''+objId+'\','+type+')")';	
						eval('$(this).'+$(this).attr("eventName")+'(function(){'+eventStr+'});');
					}
				});
				$(nRow).attr('rowspanNum',rowspan);//记录要跨越的行数
				$(nRow).find('td input:checkbox').parent().attr('rowspan', rowspan);//复选框位置补偿
				$.each(rowColspan,function(i,n){
					$(nRow).find('td[name='+n.name+']').attr('rowspan', rowspan).attr('rowspanNum', rowspan);//列跨行位置补偿
				});
				
			});
		}

}

if (!this.JSON) {
    JSON = {};
}
(function () {

    function f(n) {
        // Format integers to have at least two digits.
        return n < 10 ? '0' + n : n;
    }

    if (typeof Date.prototype.toJSON !== 'function') {

        Date.prototype.toJSON = function (key) {

            return this.getUTCFullYear()   + '-' +
                 f(this.getUTCMonth() + 1) + '-' +
                 f(this.getUTCDate())      + 'T' +
                 f(this.getUTCHours())     + ':' +
                 f(this.getUTCMinutes())   + ':' +
                 f(this.getUTCSeconds())   + 'Z';
        };

        String.prototype.toJSON =
        Number.prototype.toJSON =
        Boolean.prototype.toJSON = function (key) {
            return this.valueOf();
        };
    }

    var cx = /[\u0000\u00ad\u0600-\u0604\u070f\u17b4\u17b5\u200c-\u200f\u2028-\u202f\u2060-\u206f\ufeff\ufff0-\uffff]/g,
        escapeable = /[\\\"\x00-\x1f\x7f-\x9f\u00ad\u0600-\u0604\u070f\u17b4\u17b5\u200c-\u200f\u2028-\u202f\u2060-\u206f\ufeff\ufff0-\uffff]/g,
        gap,
        indent,
        meta = {    // table of character substitutions
            '\b': '\\b',
            '\t': '\\t',
            '\n': '\\n',
            '\f': '\\f',
            '\r': '\\r',
            '"' : '\\"',
            '\\': '\\\\'
        },
        rep;


    function quote(string) {

// If the string contains no control characters, no quote characters, and no
// backslash characters, then we can safely slap some quotes around it.
// Otherwise we must also replace the offending characters with safe escape
// sequences.

        escapeable.lastIndex = 0;
        return escapeable.test(string) ?
            '"' + string.replace(escapeable, function (a) {
                var c = meta[a];
                if (typeof c === 'string') {
                    return c;
                }
                return '\\u' + ('0000' + a.charCodeAt(0).toString(16)).slice(-4);
            }) + '"' :
            '"' + string + '"';
    }


    function str(key, holder) {

// Produce a string from holder[key].

        var i,          // The loop counter.
            k,          // The member key.
            v,          // The member value.
            length,
            mind = gap,
            partial,
            value = holder[key];

// If the value has a toJSON method, call it to obtain a replacement value.

        if (value && typeof value === 'object' &&
                typeof value.toJSON === 'function') {
			//modified by zhongyq 20080919 去除对象toJSON方法的调用
            //value = value.toJSON(key);
        }

// If we were called with a replacer function, then call the replacer to
// obtain a replacement value.

        if (typeof rep === 'function') {
            value = rep.call(holder, key, value);
        }

// What happens next depends on the value's type.

        switch (typeof value) {
        case 'string':
            return quote(value);

        case 'number':

// JSON numbers must be finite. Encode non-finite numbers as null.

            return isFinite(value) ? String(value) : 'null';

        case 'boolean':
        case 'null':

// If the value is a boolean or null, convert it to a string. Note:
// typeof null does not produce 'null'. The case is included here in
// the remote chance that this gets fixed someday.

            return String(value);

// If the type is 'object', we might be dealing with an object or an array or
// null.

        case 'object':

// Due to a specification blunder in ECMAScript, typeof null is 'object',
// so watch out for that case.

            if (!value) {
                return 'null';
            }

// Make an array to hold the partial results of stringifying this object value.

            gap += indent;
            partial = [];

// If the object has a dontEnum length property, we'll treat it as an array.

            if (typeof value.length === 'number' &&
                    !value.propertyIsEnumerable('length')) {

// The object is an array. Stringify every element. Use null as a placeholder
// for non-JSON values.

                length = value.length;
                for (i = 0; i < length; i += 1) {
                    partial[i] = str(i, value) || 'null';
                }

// Join all of the elements together, separated with commas, and wrap them in
// brackets.

                v = partial.length === 0 ? '[]' :
                    gap ? '[\n' + gap +
                            partial.join(',\n' + gap) + '\n' +
                                mind + ']' :
                          '[' + partial.join(',') + ']';
                gap = mind;
                return v;
            }

// If the replacer is an array, use it to select the members to be stringified.

            if (rep && typeof rep === 'object') {
                length = rep.length;
                for (i = 0; i < length; i += 1) {
                    k = rep[i];
                    if (typeof k === 'string') {
                        v = str(k, value);
                        if (v) {
                            partial.push(quote(k) + (gap ? ': ' : ':') + v);
                        }
                    }
                }
            } else {

// Otherwise, iterate through all of the keys in the object.

                for (k in value) {
                    if (Object.hasOwnProperty.call(value, k)) {
                        v = str(k, value);
                        if (v) {
                            partial.push(quote(k) + (gap ? ': ' : ':') + v);
                        }
                    }
                }
            }

// Join all of the member texts together, separated with commas,
// and wrap them in braces.

            v = partial.length === 0 ? '{}' :
                gap ? '{\n' + gap + partial.join(',\n' + gap) + '\n' +
                        mind + '}' : '{' + partial.join(',') + '}';
            gap = mind;
            return v;
        }
    }

// If the JSON object does not yet have a stringify method, give it one.

    if (typeof JSON.stringify !== 'function') {
        JSON.stringify = function (value, replacer, space) {

// The stringify method takes a value and an optional replacer, and an optional
// space parameter, and returns a JSON text. The replacer can be a function
// that can replace values, or an array of strings that will select the keys.
// A default replacer method can be provided. Use of the space parameter can
// produce text that is more easily readable.

            var i;
            gap = '';
            indent = '';

// If the space parameter is a number, make an indent string containing that
// many spaces.

            if (typeof space === 'number') {
                for (i = 0; i < space; i += 1) {
                    indent += ' ';
                }

// If the space parameter is a string, it will be used as the indent string.

            } else if (typeof space === 'string') {
                indent = space;
            }

// If there is a replacer, it must be a function or an array.
// Otherwise, throw an error.

            rep = replacer;
            if (replacer && typeof replacer !== 'function' &&
                    (typeof replacer !== 'object' ||
                     typeof replacer.length !== 'number')) {
                throw new Error('JSON.stringify');
            }

// Make a fake root object containing our value under the key of ''.
// Return the result of stringifying the value.

            return str('', {'': value});
        };
    }


// If the JSON object does not yet have a parse method, give it one.

    if (typeof JSON.parse !== 'function') {
        JSON.parse = function (text, reviver) {

// The parse method takes a text and an optional reviver function, and returns
// a JavaScript value if the text is a valid JSON text.

            var j;

            function walk(holder, key) {

// The walk method is used to recursively walk the resulting structure so
// that modifications can be made.

                var k, v, value = holder[key];
                if (value && typeof value === 'object') {
                    for (k in value) {
                        if (Object.hasOwnProperty.call(value, k)) {
                            v = walk(value, k);
                            if (v !== undefined) {
                                value[k] = v;
                            } else {
                                delete value[k];
                            }
                        }
                    }
                }
                return reviver.call(holder, key, value);
            }


// Parsing happens in four stages. In the first stage, we replace certain
// Unicode characters with escape sequences. JavaScript handles many characters
// incorrectly, either silently deleting them, or treating them as line endings.

            cx.lastIndex = 0;
            if (cx.test(text)) {
                text = text.replace(cx, function (a) {
                    return '\\u' +
                        ('0000' + a.charCodeAt(0).toString(16)).slice(-4);
                });
            }

// In the second stage, we run the text against regular expressions that look
// for non-JSON patterns. We are especially concerned with '()' and 'new'
// because they can cause invocation, and '=' because it can cause mutation.
// But just to be safe, we want to reject all unexpected forms.

// We split the second stage into 4 regexp operations in order to work around
// crippling inefficiencies in IE's and Safari's regexp engines. First we
// replace the JSON backslash pairs with '@' (a non-JSON character). Second, we
// replace all simple value tokens with ']' characters. Third, we delete all
// open brackets that follow a colon or comma or that begin the text. Finally,
// we look to see that the remaining characters are only whitespace or ']' or
// ',' or ':' or '{' or '}'. If that is so, then the text is safe for eval.

            if (/^[\],:{}\s]*$/.
test(text.replace(/\\(?:["\\\/bfnrt]|u[0-9a-fA-F]{4})/g, '@').
replace(/"[^"\\\n\r]*"|true|false|null|-?\d+(?:\.\d*)?(?:[eE][+\-]?\d+)?/g, ']').
replace(/(?:^|:|,)(?:\s*\[)+/g, ''))) {

// In the third stage we use the eval function to compile the text into a
// JavaScript structure. The '{' operator is subject to a syntactic ambiguity
// in JavaScript: it can begin a block or an object literal. We wrap the text
// in parens to eliminate the ambiguity.

                j = eval('(' + text + ')');

// In the optional fourth stage, we recursively walk the new structure, passing
// each name/value pair to a reviver function for possible transformation.

                return typeof reviver === 'function' ?
                    walk({'': j}, '') : j;
            }

// If the text is not JSON parseable, then a SyntaxError is thrown.

            throw new SyntaxError('JSON.parse');
        };
    }
})();



$(document).ready(function(){
	// 绑定验证触发事件
	$('.maxInput').live('keyup',function(){
		validateElementInputMax($(this));
	})
	$('.maxInput').live('click',function(){
		validateElementInputMax($(this));
	})
	
	$('.digits').live('keyup',function(){
		initElementDefaultValue($(this));
	})
	$('.digits').live('click',function(){
		initElementDefaultValue($(this));
	})
	$('.money').live('keyup',function(){
		initElementDefaultValue($(this));
	})
	$('.money').live('click',function(){
		initElementDefaultValue($(this));
	})
	
	// 加载页签插件 Add date 2010-08-16 By wanghz
	jQuery.fn.extend({
		tabs1:function(){
			// 初始化页签样式
			var obj = $(this);
			obj.attr('style','overflow: auto;width:100%;').addClass('page');
			var nextDiv = $(obj).find('div:first');
			if(undefined == nextDiv || null == nextDiv){
				alert("HTML 元素结构不正确,请检查..");
				return false;
			}
			nextDiv.attr('style','margin-bottom: 5px;').addClass('tabs');
			var preFirstDiv = $(nextDiv).find('div:first');
			if(undefined == preFirstDiv || null == preFirstDiv){
				alert("HTML 元素结构不正确,请检查..");
				return false;
			}
			preFirstDiv.addClass('tabsHeader');
			var preTwoDiv = $(nextDiv).find('div').eq(1);
			if(undefined == preTwoDiv || null == preTwoDiv){
				alert("HTML 元素结构不正确,请检查..");
				return false;
			}
			preTwoDiv.addClass('tabsHeaderContent');
			$(preTwoDiv).find('ul>li:not first').removeClass('selected');

			// 触发第一个页签单击事件
			$(preTwoDiv).find('ul>li:first').addClass('selected').find('a').click();
			$(preTwoDiv).find('ul>li>a').each(function(i,n){
				$(n).click(function(){
					// 页签 URL HREF 不为空,则进行加载连接
					$(this).parent().parent().find('li').removeClass('selected');
					$(this).parent().addClass('selected');
					$(this).click(function(){
						var divId = $(this).attr('divId');
						var url = $(this).attr('url');
						if(undefined != divId && undefined != url && null != divId && null != url){
							$(divId).empty().loadPage($("#initPath").val()+url);
						}
					})
				})
			})
			// 第一个页签加载连接
			var firstAelement = $(preTwoDiv).find('ul>li:first').find('a');
			var divId = $(firstAelement).attr('divId');
			var url = $(firstAelement).attr('url');
			if(undefined != divId && undefined != url && null != divId && null != url){
				$(divId).empty().loadPage($("#initPath").val()+url);
			}
		}
	})
	
	
})

/**
 * @Description 文本框、文本域 添加输入长度实时验证
 * @param 需要增加验证的元素 添加样式为 maxInput
 * @param 徐涛增加验证的元素 添加属性为 maxlength="" (最大输入长度)
 * @Create date 2010-08-03 By wanghz
 */
validateElementInputMax = function(eventObj){
	var maxInputLength = $(eventObj).attr("maxlength")*1;					// 可输入总长度
	var statNum = 0;														// 已输入总长度
	var thisValue = $(eventObj).val().toString();
	var chinese = thisValue.replace(/[^\u4E00-\u9FA5]/g,'');				// 已输入中文,一个中文占两个字符
	if(chinese.length>0){
		for(var i=0;i<chinese.length;i++){
			var char = chinese.substring(i,(i+1));
			thisValue = thisValue.replace(char,"");
		}
		statNum = thisValue.length*1 + chinese.length*1;
	}else{
		statNum = thisValue.length*1;
	}
	var spare = maxInputLength - statNum;									// 剩余总长度
	var showMessage = "";
	if(spare >= 0){
		showMessage = "可输入"+spare+"字";
	}
	if(spare <0){
		showMessage = "已超出"+spare.toString().replace("-","")+"字";
	}
	var errorSpan = $("#ERROR");
	if(errorSpan.length > 0){
		if(spare >= 0){
			$(errorSpan).removeClass().addClass("greenHighlight");
		}
		if(spare <0){
			$(errorSpan).removeClass().addClass("eleWarning");
		}
		$(errorSpan).empty().append(showMessage).show();
		return false;
	}
	var thisNextSpan = $(eventObj).next('span');
	if(undefined != thisNextSpan && thisNextSpan.length>0){
		if(spare >= 0){
			$(thisNextSpan).removeClass().addClass("greenHighlight");
		}
		if(spare <0){
			$(thisNextSpan).removeClass().addClass("eleWarning");
		}
		$(thisNextSpan).empty().append(showMessage).show();
		return false;
	}
}


//元转万元
yToWany = function(money){
	var moneyStr = "";
	money = parseFloat(money/10000);
	if(money%1 == 0){
		moneyStr = "" + money + ".00";
	}else if (money*10%1 == 0){
		moneyStr = "" +　money + "0";
	} else {
		moneyStr = "" + money;
	}
	return moneyStr;
}



/**
 * 初始化输入框值为空
 */
initElementDefaultValue = function(thisObj){
	var value = $(thisObj).val();
	$(thisObj).change(function(){
		value = $(this).val();
		if('' == value){
			$(this).val('0');
		}
	})
}

/**
 * @Description: 获取待办任务
 * @param paramtypeCode 任务类别
 * @param bizId 业务ID
 * @param parentBizId 顶级业务ID
 * @return waitprocWorkTaskId
 */
var getWaitprocWorkTaskIdIsError = true;
getWaitprocWorkTaskId = function(paramtypeCode,bizId,parentBizId){
	getWaitprocWorkTaskIdIsError = true;
	var workTaskId = "";
	$.ajax({
		url:$("#initPath").val()+"/WaitprocWorkTaskController.do?method=getWaitprocWorkTask",
		type:"POST",
		data:{"paramtypeCode":paramtypeCode,"bizId":bizId,"parentBizId":parentBizId},
		async:false,
		success:function(msg){
			if(null != msg && "[]" != msg){
				try {
					msg = eval('('+msg+')');
					if(undefined != msg.workTask && null != msg.workTask && "" != msg.workTask.objId){
						workTaskId = msg.workTask.objId;
						getWaitprocWorkTaskIdIsError = false;
					}
				} catch (e) {
					alert('[paramtypeCode='+paramtypeCode+'][bizId='+bizId+'][parentBizId='+parentBizId+']获取待办任务异常！');
				}
			}
		},error:function(e){
			alert('[paramtypeCode='+paramtypeCode+'][bizId='+bizId+'][parentBizId='+parentBizId+']获取待办任务异常！');
		}
	})
	return workTaskId;
}
/**
 * 返回 获取待办任务ID是否发生错误
 */
validateGetWorkTaskIsError = function(){
	return getWaitprocWorkTaskIdIsError;
}
 /**
  * 加载项目信息
  */
ProjectInfoReload = function(projectId){
	if(undefined == projectId || null == projectId || '' == projectId){
		alert('projectId['+projectId+']不能为null');
		return false;
	}
	$('#conBody').empty().loadPage($('#initPath').val()+'/ProjectController.do?method=toProjectInfo&objId='+projectId);
	//$('#conBody').empty().loadPage($('#initPath').val()+'/ProjectController.do?method=toProjectInfoForTab&objId='+projectId);
}
/*
* 
* 图片放大预览
*/
eval(function(p,a,c,k,e,r){e=function(c){return(c<62?'':e(parseInt(c/62)))+((c=c%62)>35?String.fromCharCode(c+29):c.toString(36))};if('0'.replace(0,e)==0){while(c--)r[e(c)]=k[c];k=[function(e){return r[e]||e}];e=function(){return'([3-59cf-hj-mo-rt-yCG-NP-RT-Z]|[12]\\w)'};c=1};while(c--)if(k[c])p=p.replace(new RegExp('\\b'+e(c)+'\\b','g'),k[c]);return p}('5 $$,$$B,$$A,$$F,$$D,$$E,$$CE,$$S;(3(){5 O,B,A,F,D,E,CE,S;O=3(id){4"1V"==1B id?M.getElementById(id):id};O.emptyFunction=3(){};O.extend=3(I,12,1n){9(1n===1W)1n=13;J(5 N K 12){9(1n||!(N K I)){I[N]=12[N]}}4 I};O.deepextend=3(I,12){J(5 N K 12){5 1f=12[N];9(I===1f)continue;9(1B 1f==="c"){I[N]=C.callee(I[N]||{},1f)}P{I[N]=1f}}4 I};O.wrapper=3(me,1Y){5 1C=3(){me.Q(14,C)};5 1D=3(){};1D.15=1Y.15;1C.15=new 1D;4 1C};B=(3(R){5 b={17:/17/.G(R)&&!/1E/.G(R),1E:/1E/.G(R),1Z:/webkit/.G(R)&&!/1F/.G(R),20:/20/.G(R),1F:/1F/.G(R)};5 1o="";J(5 i K b){9(b[i]){1o="1Z"==i?"1g":i;1G}}b.1g=1o&&1H("(?:"+1o+")[\\\\/: ]([\\\\d.]+)").G(R)?1H.$1:"0";b.ie=b.17;b.21=b.17&&1J(b.1g,10)==6;b.ie7=b.17&&1J(b.1g,10)==7;b.22=b.17&&1J(b.1g,10)==8;4 b})(1K.navigator.userAgent.toLowerCase());A=3(){5 l={isArray:3(23){4 Object.15.toString.1h(23)==="[c 1L]"},1p:3(w,V,f){9(w.1p){4 1q(f)?w.1p(V):w.1p(V,f)}P{5 T=w.1i;f=1q(f)?0:f<0?1r.24(f)+T:1r.25(f);J(;f<T;f++){9(w[f]===V)4 f}4-1}},1s:3(w,V,f){9(w.1s){4 1q(f)?w.1s(V):w.1s(V,f)}P{5 T=w.1i;f=1q(f)||f>=T-1?T-1:f<0?1r.24(f)+T:1r.25(f);J(;f>-1;f--){9(w[f]===V)4 f}4-1}}};3 Z(c,t){9(1W===c.1i){J(5 k K c){9(x===t(c[k],k,c))1G}}P{J(5 i=0,T=c.1i;i<T;i++){9(i K c){9(x===t(c[i],i,c))1G}}}};Z({26:3(c,t,r){Z(c,3(){t.Q(r,C)})},map:3(c,t,r){5 l=[];Z(c,3(){l.27(t.Q(r,C))});4 l},1t:3(c,t,r){5 l=[];Z(c,3(28){t.Q(r,C)&&l.27(28)});4 l},every:3(c,t,r){5 l=13;Z(c,3(){9(!t.Q(r,C)){l=x;4 x}});4 l},some:3(c,t,r){5 l=x;Z(c,3(){9(t.Q(r,C)){l=13;4 x}});4 l}},3(29,k){l[k]=3(c,t,r){9(c[k]){4 c[k](t,r)}P{4 29(c,t,r)}}});4 l}();F=(3(){5 18=1L.15.18;4{bind:3(1u,r){5 19=18.1h(C,2);4 3(){4 1u.Q(r,19.2a(18.1h(C)))}},bindAsEventListener:3(1u,r){5 19=18.1h(C,2);4 3(g){4 1u.Q(r,[E.1j(g)].2a(19))}}}})();D={1v:3(m){5 1a=m?m.2b:M;4 1a.2c.2d||1a.2e.2d},1w:3(m){5 1a=m?m.2b:M;4 1a.2c.2f||1a.2e.2f},2g:M.1k?3(a,b){4!!(a.compareDocumentPosition(b)&16)}:3(a,b){4 a!=b&&a.2g(b)},v:3(m){5 o=0,U=0,W=0,X=0;9(!m.2h||B.22){5 n=m;while(n){o+=n.offsetLeft,U+=n.offsetTop;n=n.offsetParent};W=o+m.offsetWidth;X=U+m.offsetHeight}P{5 v=m.2h();o=W=D.1w(m);U=X=D.1v(m);o+=v.o;W+=v.W;U+=v.U;X+=v.X};4{"o":o,"U":U,"W":W,"X":X}},clientRect:3(m){5 v=D.v(m),1M=D.1w(m),1N=D.1v(m);v.o-=1M;v.W-=1M;v.U-=1N;v.X-=1N;4 v},curStyle:M.1k?3(p){4 M.1k.2i(p,2j)}:3(p){4 p.1x},getStyle:M.1k?3(p,k){5 h=M.1k.2i(p,2j);4 k K h?h[k]:h.getPropertyValue(k)}:3(p,k){5 h=p.1x;9(k=="11"){9(/1O\\(11=(.*)\\)/i.G(h.1t)){5 11=parseFloat(1H.$1);4 11?11/2k:0}4 1};9(k=="2l"){k="2m"}5 l=h[k]||h[S.1P(k)];9(!/^\\-?\\d+(px)?$/i.G(l)&&/^\\-?\\d/.G(l)){h=p.h,o=h.o,2o=p.1Q.o;p.1Q.o=p.1x.o;h.o=l||0;l=h.pixelLeft+"px";h.o=o;p.1Q.o=2o}4 l},setStyle:3(1l,h,1b){9(!1l.1i){1l=[1l]}9(1B h=="1V"){5 s=h;h={};h[s]=1b}A.26(1l,3(p){J(5 k K h){5 1b=h[k];9(k=="11"&&B.ie){p.h.1t=(p.1x.1t||"").2p(/1O\\([^)]*\\)/,"")+"1O(11="+1b*2k+")"}P 9(k=="2l"){p.h[B.ie?"2m":"cssFloat"]=1b}P{p.h[S.1P(k)]=1b}}})}};E=(3(){5 1c,1d,y=1;9(1K.2q){1c=3(u,j,q){u.2q(j,q,x)};1d=3(u,j,q){u.removeEventListener(j,q,x)}}P{1c=3(u,j,q){9(!q.$$y)q.$$y=y++;9(!u.Y)u.Y={};5 H=u.Y[j];9(!H){H=u.Y[j]={};9(u["on"+j]){H[0]=u["on"+j]}}H[q.$$y]=q;u["on"+j]=1y};1d=3(u,j,q){9(u.Y&&u.Y[j]){2r u.Y[j][q.$$y]}};3 1y(){5 1z=13,g=1j();5 H=14.Y[g.j];J(5 i K H){14.$$1y=H[i];9(14.$$1y(g)===x){1z=x}}4 1z}}3 1j(g){9(g)4 g;g=1K.g;g.pageX=g.clientX+D.1w(g.1S);g.pageY=g.clientY+D.1v(g.1S);g.target=g.1S;g.1T=1T;g.1U=1U;5 1A={"mouseout":g.toElement,"mouseover":g.fromElement}[g.j];9(1A){g.1A=1A}4 g};3 1T(){14.cancelBubble=13};3 1U(){14.1z=x};4{"1c":1c,"1d":1d,"1j":1j}})();CE=(3(){5 y=1;4{1c:3(c,j,q){9(!q.$$$y)q.$$$y=y++;9(!c.L)c.L={};9(!c.L[j])c.L[j]={};c.L[j][q.$$$y]=q},1d:3(c,j,q){9(c.L&&c.L[j]){2r c.L[j][q.$$$y]}},fireEvent:3(c,j){9(!c.L)4;5 19=1L.15.18.1h(C,2),H=c.L[j];J(5 i K H){H[i].Q(c,19)}}}})();S={1P:3(s){4 s.2p(/-([a-z])/ig,3(all,2s){4 2s.toUpperCase()})}};9(B.21){try{M.execCommand("BackgroundImageCache",x,13)}catch(e){}};$$=O;$$B=B;$$A=A;$$F=F;$$D=D;$$E=E;$$CE=CE;$$S=S})();',[],153,'|||function|return|var||||if|||object|||from|event|style||type|name|ret|node||left|elem|handler|thisp||callback|element|rect|array|false|guid||||arguments||||test|handlers|destination|for|in|cusevents|document|property||else|apply|ua||len|top|elt|right|bottom|events|each||opacity|source|true|this|prototype||msie|slice|args|doc|value|addEvent|removeEvent||copy|version|call|length|fixEvent|defaultView|elems||override|vMark|indexOf|isNaN|Math|lastIndexOf|filter|fun|getScrollTop|getScrollLeft|currentStyle|handleEvent|returnValue|relatedTarget|typeof|ins|subclass|opera|chrome|break|RegExp||parseInt|window|Array|sLeft|sTop|alpha|camelize|runtimeStyle||srcElement|stopPropagation|preventDefault|string|undefined||parent|safari|firefox|ie6|ie8|obj|ceil|floor|forEach|push|item|method|concat|ownerDocument|documentElement|scrollTop|body|scrollLeft|contains|getBoundingClientRect|getComputedStyle|null|100|float|styleFloat||rsLeft|replace|addEventListener|delete|letter'.split('|'),0,{}))
var ImageZoom = function(image, viewer, options) {
	this._initialize( image, viewer, options );
	this._initLoad();
};

ImageZoom.prototype = {
 //初始化程序
 _initialize: function(image, viewer, options) {
	this._image = $$(image);//原图
	this._zoom = document.createElement("img");//显示图
	this._viewer = $$(viewer);//显示框
	this._viewerWidth = 0;//显示框宽
	this._viewerHeight = 0;//显示框高
	this._preload = new Image();//预载对象
	this._rect = null;//原图坐标
	this._repairLeft = 0;//显示图x坐标修正
	this._repairTop = 0;//显示图y坐标修正
	this._rangeWidth = 0;//显示范围宽度
	this._rangeHeight = 0;//显示范围高度
	this._timer = null;//计时器
	this._loaded = false;//是否加载
	this._substitute = false;//是否替换
	
	var opt = this._setOptions(options);
	
	this._scale = opt.scale;
	this._max = opt.max;
	this._min = opt.min;
	this._originPic = opt.originPic;
	this._zoomPic = opt.zoomPic;
	this._rangeWidth = opt.rangeWidth;
	this._rangeHeight = opt.rangeHeight;
	
	this.delay = opt.delay;
	this.autoHide = opt.autoHide;
	this.mouse = opt.mouse;
	this.rate = opt.rate;
	
	this.onLoad = opt.onLoad;
	this.onStart = opt.onStart;
	this.onMove = opt.onMove;
	this.onEnd = opt.onEnd;
	
	var oThis = this, END = function(){ oThis._end(); };
	this._END = function(){ oThis._timer = setTimeout( END, oThis.delay ); };
	this._START = $$F.bindAsEventListener( this._start, this );
	this._MOVE = $$F.bindAsEventListener( this._move, this );
	this._MOUSE = $$F.bindAsEventListener( this._mouse, this );
	this._OUT = $$F.bindAsEventListener( function(e){
			if ( !e.relatedTarget ) this._END();
		}, this );
	
	$$CE.fireEvent( this, "init" );
 },
 //设置默认属性
 _setOptions: function(options) {
   this.options = {//默认值
		scale:		0,//比例(大图/原图)
		max:		10,//最大比例
		min:		1.5,//最小比例
		originPic:	"",//原图地址
		zoomPic:	"",//大图地址
		rangeWidth:	0,//显示范围宽度
		rangeHeight:0,//显示范围高度
		delay:		20,//延迟结束时间
		autoHide:	true,//是否自动隐藏
		mouse:		false,//鼠标缩放
		rate:		.2,//鼠标缩放比率
		onLoad:		$$.emptyFunction,//加载完成时执行
		onStart:	$$.emptyFunction,//开始放大时执行
		onMove:		$$.emptyFunction,//放大移动时执行
		onEnd:		$$.emptyFunction//放大结束时执行
   };
   return $$.extend(this.options, options || {});
 },
 //初始化加载
 _initLoad: function() {
	var image = this._image, originPic = this._originPic,
		useOrigin = !this._zoomPic && this._scale,
		loadImage = $$F.bind( useOrigin ? this._loadOriginImage : this._loadImage, this );
	//设置自动隐藏
	this.autoHide && this._hide();
	//先加载原图
	if ( originPic && originPic != image.src ) {//使用自定义地址
		image.onload = loadImage;
		image.src = originPic;
	} else if ( image.src ) {//使用元素地址
		if ( !image.complete ) {//未载入完
			image.onload = loadImage;
		} else {//已经载入
			loadImage();
		}
	} else {
		return;//没有原图地址
	}
	//加载大图
	if ( !useOrigin ) {
		var preload = this._preload, zoomPic = this._zoomPic || image.src,
			loadPreload = $$F.bind( this._loadPreload, this );
		if ( zoomPic != preload.src ) {//新地址重新加载
			preload.onload = loadPreload;
			preload.src = zoomPic;
		} else {//正在加载
			if ( !preload.complete ) {//未载入完
				preload.onload = loadPreload;
			} else {//已经载入
				this._loadPreload();
			}
		}
	}
 },
 //原图放大加载程序
 _loadOriginImage: function() {
	this._image.onload = null;
	this._zoom.src = this._image.src;
	this._initLoaded();
 },
 //原图加载程序
 _loadImage: function() {
	this._image.onload = null;
	if ( this._loaded ) {//大图已经加载
		this._initLoaded();
	} else {
		this._loaded = true;
		if ( this._scale ) {//有自定义比例才用原图放大替换大图
			this._substitute = true;
			this._zoom.src = this._image.src;
			this._initLoaded();
		}
	}
 },
 //大图预载程序
 _loadPreload: function() {
	this._preload.onload = null;
	this._zoom.src = this._preload.src;
	if ( this._loaded ) {//原图已经加载
		//没有使用替换
		if ( !this._substitute ) { this._initLoaded(); }
	} else {
		this._loaded = true;
	}
 },
 //初始化加载设置
 _initLoaded: function(src) {
	//初始化显示图
	this._initSize();
	//初始化显示框
	this._initViewer();
	//初始化数据
	this._initData();
	//开始执行
	$$CE.fireEvent( this, "load" );
	this.onLoad();
	this.start();
 },
 //初始化显示图尺寸
 _initSize: function() {
	var zoom = this._zoom, image = this._image, scale = this._scale;
	if ( !scale ) { scale = this._preload.width / image.width; }
	this._scale = scale = Math.min( Math.max( this._min, scale ), this._max );
	//按比例设置显示图大小
	zoom.width = Math.ceil( image.width * scale );
	zoom.height = Math.ceil( image.height * scale );
 },
 //初始化显示框
 _initViewer: function() {
	var zoom = this._zoom, viewer = this._viewer;
	//设置样式
	var styles = { padding: 0, overflow: "hidden" }, p = $$D.getStyle( viewer, "position" );
	if ( p != "relative" && p != "absolute" ){ styles.position = "relative"; };
	$$D.setStyle( viewer, styles );
	zoom.style.position = "absolute";
	//插入显示图
	if ( !$$D.contains( viewer, zoom ) ){ viewer.appendChild( zoom ); }
 },
 //初始化数据
 _initData: function() {
	var zoom = this._zoom, image = this._image, viewer = this._viewer,
		scale = this._scale, rangeWidth = this._rangeWidth, rangeHeight = this._rangeHeight;
	//原图坐标
	this._rect = $$D.rect( image );
	//修正参数
	this._repairLeft = image.clientLeft + parseInt($$D.getStyle( image, "padding-left" ));
	this._repairTop = image.clientTop + parseInt($$D.getStyle( image, "padding-top" ));
	//设置范围参数和显示框大小
	if ( rangeWidth > 0 && rangeHeight > 0 ) {
		rangeWidth = Math.ceil( rangeWidth );
		rangeHeight = Math.ceil( rangeHeight );
		this._viewerWidth = Math.ceil( rangeWidth * scale );
		this._viewerHeight = Math.ceil( rangeHeight * scale );
		$$D.setStyle( viewer, {
			width: this._viewerWidth + "px",
			height: this._viewerHeight + "px"
		});
	} else {
		var styles;
		if ( !viewer.clientWidth ) {//隐藏
			var style = viewer.style;
			styles = {
				display: style.display,
				position: style.position,
				visibility: style.visibility
			};
			$$D.setStyle( viewer, {
				display: "block", position: "absolute", visibility: "hidden"
			});
		}
		this._viewerWidth = viewer.clientWidth;
		this._viewerHeight = viewer.clientHeight;
		if ( styles ) { $$D.setStyle( viewer, styles ); }
		
		rangeWidth = Math.ceil( this._viewerWidth / scale );
		rangeHeight = Math.ceil( this._viewerHeight / scale );
	}
	this._rangeWidth = rangeWidth;
	this._rangeHeight = rangeHeight;
 },
 //开始
 _start: function() {
	clearTimeout( this._timer );
	var viewer = this._viewer, image = this._image, scale = this._scale;
	viewer.style.display = "block";
	$$CE.fireEvent( this, "start" );
	this.onStart();
	$$E.removeEvent( image, "mouseover", this._START );
	$$E.removeEvent( image, "mousemove", this._START );
	$$E.addEvent( document, "mousemove", this._MOVE );
	$$E.addEvent( document, "mouseout", this._OUT );
	this.mouse && $$E.addEvent( document, $$B.firefox ? "DOMMouseScroll" : "mousewheel", this._MOUSE );
 },
 //移动
 _move: function(e) {
	clearTimeout( this._timer );
	var x = e.pageX, y = e.pageY, rect = this._rect;
	if ( x < rect.left || x > rect.right || y < rect.top || y > rect.bottom ) {
		this._END();//移出原图范围
	} else {
		var pos = {}, scale = this._scale, zoom = this._zoom,
			viewerWidth = this._viewerWidth,
			viewerHeight = this._viewerHeight;
		//修正坐标
		pos.left = viewerWidth / 2 - ( x - rect.left - this._repairLeft ) * scale;
		pos.top = viewerHeight / 2 - ( y - rect.top - this._repairTop ) * scale;
		
		$$CE.fireEvent( this, "repair", e, pos );
		//范围限制
		x = Math.ceil(Math.min(Math.max( pos.left, viewerWidth - zoom.width ), 0));
		y = Math.ceil(Math.min(Math.max( pos.top, viewerHeight - zoom.height ), 0));
		//设置定位
		zoom.style.left = x + "px";
		zoom.style.top = y + "px";
		
		$$CE.fireEvent( this, "move", e, x, y );
		this.onMove();
	}
 },
 //结束
 _end: function() {
	$$CE.fireEvent( this, "end" );
	this.onEnd();
	this.autoHide && this._hide();
	this.stop();
	this.start();
 },
 //隐藏
 _hide: function() {
	this._viewer.style.display = "none";
 },
 //鼠标缩放
 _mouse: function(e) {
	this._scale += ( e.wheelDelta ? e.wheelDelta / (-120) : (e.detail || 0) / 3 ) * this.rate;
	
	var opt = this.options;
	this._rangeWidth = opt.rangeWidth;
	this._rangeHeight = opt.rangeHeight;
	
	this._initSize();
	this._initData();
	this._move(e);
	e.preventDefault();
 },
 //开始
 start: function() {
	if ( this._viewerWidth && this._viewerHeight ) {
		var image = this._image, START = this._START;
		$$E.addEvent( image, "mouseover", START );
		$$E.addEvent( image, "mousemove", START );
	}
 },
 //停止
 stop: function() {
	clearTimeout( this._timer );
	$$E.removeEvent( this._image, "mouseover", this._START );
	$$E.removeEvent( this._image, "mousemove", this._START );
	$$E.removeEvent( document, "mousemove", this._MOVE );
	$$E.removeEvent( document, "mouseout", this._OUT );
	$$E.removeEvent( document, $$B.firefox ? "DOMMouseScroll" : "mousewheel", this._MOUSE );
 },
 //修改设置
 reset: function(options) {
	this.stop();
	
	var viewer = this._viewer, zoom = this._zoom;
	if ( $$D.contains( viewer, zoom ) ) { viewer.removeChild( zoom ); }
	
	var opt = $$.extend( this.options, options || {} );
	this._scale = opt.scale;
	this._max = opt.max;
	this._min = opt.min;
	this._originPic = opt.originPic;
	this._zoomPic = opt.zoomPic;
	this._rangeWidth = opt.rangeWidth;
	this._rangeHeight = opt.rangeHeight;
	
	//重置属性
	this._loaded = this._substitute = false;
	this._rect = null;
	this._repairLeft = this._repairTop = 
	this._viewerWidth = this._viewerHeight = 0;
	
	this._initLoad();
 },
 //销毁程序
 dispose: function() {
	$$CE.fireEvent( this, "dispose" );
	this.stop();
	if ( $$D.contains( this._viewer, this._zoom ) ) {
		this._viewer.removeChild( this._zoom );
	}
	this._image.onload = this._preload.onload =
		this._image = this._preload = this._zoom = this._viewer =
		this.onLoad = this.onStart = this.onMove = this.onEnd =
		this._START = this._MOVE = this._END = this._OUT = null
 }
}


ImageZoom._MODE = {
	//跟随
	"follow": {
		methods: {
			init: function() {
				this._stylesFollow = null;//备份样式
				this._repairFollowLeft = 0;//修正坐标left
				this._repairFollowTop = 0;//修正坐标top
			},
			load: function() {
				var viewer = this._viewer, style = viewer.style, styles;
				this._stylesFollow = {
					left: style.left, top: style.top, position: style.position
				};
				viewer.style.position = "absolute";
				//获取修正参数
				if ( !viewer.offsetWidth ) {//隐藏
					styles = { display: style.display, visibility: style.visibility };
					$$D.setStyle( viewer, { display: "block", visibility: "hidden" });
				}
				//修正中心位置
				this._repairFollowLeft = viewer.offsetWidth / 2;
				this._repairFollowTop = viewer.offsetHeight / 2;
				//修正offsetParent位置
				if ( !/BODY|HTML/.test( viewer.offsetParent.nodeName ) ) {
					var parent = viewer.offsetParent, rect = $$D.rect( parent );
					this._repairFollowLeft += rect.left + parent.clientLeft;
					this._repairFollowTop += rect.top + parent.clientTop;
				}
				if ( styles ) { $$D.setStyle( viewer, styles ); }
			},
			repair: function(e, pos) {
				var zoom = this._zoom,
					viewerWidth = this._viewerWidth,
					viewerHeight = this._viewerHeight;
				pos.left = ( viewerWidth / 2 - pos.left ) * ( viewerWidth / zoom.width - 1 );
				pos.top = ( viewerHeight / 2 - pos.top ) * ( viewerHeight / zoom.height - 1 );
			},
			move: function(e) {
				var style = this._viewer.style;
				style.left = e.pageX - this._repairFollowLeft + "px";
				style.top = e.pageY - this._repairFollowTop + "px";
			},
			dispose: function() {
				$$D.setStyle( this._viewer, this._stylesFollow );
			}
		}
	},
	//拖柄
	"handle": {
		options: {//默认值
			handle:		""//拖柄对象
   	},
		methods: {
			init: function() {
				var handle = $$( this.options.handle );
				if ( !handle ) {//没有定义的话用复制显示框代替
					var body = document.body;
					handle = body.insertBefore(this._viewer.cloneNode(false), body.childNodes[0]);
					handle.id = "";
					handle["_createbyhandle"] = true;//生成标识用于移除
				} else {
					var style = handle.style;
					this._stylesHandle = {
						left: style.left, top: style.top, position: style.position,
						display: style.display, visibility: style.visibility,
						padding: style.padding, margin: style.margin,
						width: style.width, height: style.height
					};
				}
				$$D.setStyle( handle, { padding: 0, margin: 0, display: "none" } );
				
				this._handle = handle;
				this._repairHandleLeft = 0;//修正坐标left
				this._repairHandleTop = 0;//修正坐标top
			},
			load: function() {
				var handle = this._handle, rect = this._rect;
				$$D.setStyle( handle, {
					position: "absolute",
					width: this._rangeWidth + "px",
					height: this._rangeHeight + "px",
					display: "block",
					visibility: "hidden"
				});
				//获取修正参数
				this._repairHandleLeft = rect.left + this._repairLeft - handle.clientLeft;
				this._repairHandleTop = rect.top + this._repairTop - handle.clientTop;
				//修正offsetParent位置
				if ( !/BODY|HTML/.test( handle.offsetParent.nodeName ) ) {
					var parent = handle.offsetParent, rect = $$D.rect( parent );
					this._repairHandleLeft -= rect.left + parent.clientLeft;
					this._repairHandleTop -= rect.top + parent.clientTop;
				}
				//隐藏
				$$D.setStyle( handle, { display: "none", visibility: "visible" });
			},
			start: function() {
				this._handle.style.display = "block";
			},
			move: function(e, x, y) {
				var style = this._handle.style, scale = this._scale;
				style.left = Math.ceil( this._repairHandleLeft - x / scale ) + "px";
				style.top = Math.ceil( this._repairHandleTop - y / scale )  + "px";
			},
			end: function() {
				this._handle.style.display = "none";
			},
			dispose: function() {
				if( "_createbyhandle" in this._handle ){
					document.body.removeChild( this._handle );
				} else {
					$$D.setStyle( this._handle, this._stylesHandle );
				}
				this._handle = null;
			}
		}
	},
	//切割
	"cropper": {
		options: {//默认值
			opacity:	.5//透明度
   	},
		methods: {
			init: function() {
				var body = document.body,
					cropper = body.insertBefore(document.createElement("img"), body.childNodes[0]);
				cropper.style.display = "none";
				
				this._cropper = cropper;
				this.opacity = this.options.opacity;
			},
			load: function() {
				var cropper = this._cropper, image = this._image, rect = this._rect;
				cropper.src = image.src;
				cropper.width = image.width;
				cropper.height = image.height;
				$$D.setStyle( cropper, {
					position: "absolute",
					left: rect.left + this._repairLeft + "px",
					top: rect.top + this._repairTop + "px"
				});
			},
			start: function() {
				this._cropper.style.display = "block";
				$$D.setStyle( this._image, "opacity", this.opacity );
			},
			move: function(e, x, y) {
				var w = this._rangeWidth, h = this._rangeHeight, scale = this._scale;
				x = Math.ceil( -x / scale ); y = Math.ceil( -y / scale );
				this._cropper.style.clip = "rect(" + y + "px " + (x + w) + "px " + (y + h) + "px " + x + "px)";
			},
			end: function() {
				$$D.setStyle( this._image, "opacity", 1 );
				this._cropper.style.display = "none";
			},
			dispose: function() {
				$$D.setStyle( this._image, "opacity", 1 );
				document.body.removeChild( this._cropper );
				this._cropper = null;
			}
		}
	}
}



ImageZoom.prototype._initialize = (function(){
	var init = ImageZoom.prototype._initialize,
		mode = ImageZoom._MODE,
		modes = {
			"follow": [ mode.follow ],
			"handle": [ mode.handle ],
			"cropper": [ mode.cropper ],
			"handle-cropper": [ mode.handle, mode.cropper ]
		};
	return function(){
		var options = arguments[2];
		if ( options && options.mode && modes[ options.mode ] ) {
			$$A.forEach( modes[ options.mode ], function( mode ){
				//扩展options
				$$.extend( options, mode.options, false );
				//扩展钩子
				$$A.forEach( mode.methods, function( method, name ){
					$$CE.addEvent( this, name, method );
				}, this );
			}, this );
		}
		init.apply( this, arguments );
	}
})();

/*
* 
* 图片放大结束
*/