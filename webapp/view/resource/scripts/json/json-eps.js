/**
* put the jsonObject`s element to the form
*/
function jsonObjectToForm(form, jsonObject,hasNull){
	form=formTrim(form);
	for(i = 0, max = form.elements.length; i < max; i++) {
	  try{//解决 xhtml 标准解析 对象不正确问题
		var e = form.elements[i];
		var eName = e.name;
		if(eName.indexOf('.') > 0){
			dotIndex = eName.indexOf('.');
			parentName = eName.substring(0, dotIndex);
			childName = eName.substring(dotIndex+1);
			//handle the eName repeatly, pack it into the jsonObject
			if(jsonObject[parentName])
				eValue = iterValueFromJsonObject(jsonObject, parentName, childName);
			else
				eValue = jsonObject[eName]
		}else{
			eValue = jsonObject[eName];
		}
		
		if(!hasNull && !eValue) {
			eValue = "";
		}
		
		if((typeof(eValue) == 'boolean') || (eValue && eValue != "undefined" && eValue != "null")){
			switch(e.type){
				case 'checkbox': 
					var eValueArray=eValue.split(',');
					if($.inArray(e.value,eValueArray)>-1)
						e.checked=true;
					break;
				case 'radio': 
					if(e.value == eValue){
						e.checked = true;
					}
					break;
				case 'hidden': 
				case 'password': 
				case 'textarea':
				case 'text': 
					e.value = epsSetValuesForInput(e,eValue,hasNull);
					break;
				case 'select-one':
				case 'select-multiple':
					for(j = 0; j < e.options.length; j++){
						op = e.options[j];
						//alert("eName : " + eName + "; op value : " + op.value + "; eValue : " + eValue);
						if(op.value == String(eValue)){
							op.selected = true;
						}
					}
					break;
				case 'button': 
				case 'file': 
				case 'image': 
				case 'reset': 
				case 'submit': 
				default:  
			}
		}
	  }catch(e){}
	}
}
function json2Object(objectId,json,hasNull){
	$("#"+objectId).find("span[id],textarea[id]").each(function(i,n){
		try{
			epsSetValues(n,eval('(json.' + n.id + ')'),hasNull)
		}catch(e){
			try{
				epsSetValues(n,eval("(json['" + n.id + "'])"),hasNull)
			}catch(ce) {$(n).html("&nbsp;");  }
		}
	})
}
function json2ObjectSpan(objectId,json,hasNull){
	$("#"+objectId).find("span[id]").each(function(i,n){
		try{
			epsSetValues(n,eval('(json.' + n.id + ')'),hasNull)
		}catch(e){
			$(n).html("&nbsp;");
		}
	})
}
function json2ObjectDiv(objectId,json,hasNull){
	$("#"+objectId).find("div[id]").each(function(i,n){
		try{
			epsSetValues(n,eval('(json.' + n.id + ')'),hasNull)
		}catch(e){
			$(n).html("&nbsp;");
		}
	})
}
//add by zhukw
//把json的key值当做ID来查找页面对象，然后根据tagName的类型来赋值
function json2ObjectByJsonKey(objectId,json){
	$.each(json,function(n){
		var obj = $("#"+objectId).find("#"+n);
		if(obj!=null){
			var tagName = $(obj).attr("tagName");
			switch(tagName){
				case "SPAN" :
					$(obj).html(json[n])
					break;
				case "H1" :	
					$(obj).text(json[n])
					break;
				case "P" :
					$(obj).text(json[n])
					break;
				case "INPUT" :
					$(obj).val(json[n])
					break;
				default :
					break;
			}
		}
	});
}
function epsSetValues(o,v,hasNull){
	if(!hasNull) {
		if(!v)return;
	}
	switch(String($(o).attr("tabType")).toLocaleLowerCase()){
		case "input":
		    $(o).find("input").val(v);
		    break;
		case "double":
		    $(o).html(new Number(v).toFixed(2));
		    break;
		case "datesimple":
		    $(o).html(v.substring(0,10));
		    break;
		case "datetime":
		    $(o).html(v.substring(0,19));
		    break;
		case "textarea":
		    $(o).find("textarea").val(v);
		    break;
		case "amount":
			$(o).html(formatAmount(v));
			break;
		case "money":
			$(o).html(formatAmount(v,2));
			break;
		default:  
			$(o).html(v.toString().replace(/\n/g,"<br>").replace(/\\n/g,"<br>").replace(/\s/g,"&nbsp;&nbsp;"));
	}
}
function epsSetValuesForInput(o,v,hasNull){
	if(!hasNull) {
		if(!v)return;
	}
	switch(String($(o).attr("tabType")).toLocaleLowerCase()){
	case "double":
	    return new Number(v).toFixed(2);
	    break;
	case "datesimple":
	    return v.substring(0,10);
	    break;
	case "datetime":
	    return v.substring(0,19);
	    break;
	default:  
		return v;
	}
}

//form验证和字段转换
function formTrim(form){
	var formIn=form;
	var jsonObject = {};
	if(typeof(form)=='string'){
		form=document.getElementById(form);
	}
	if((typeof(form)==undefined || form==null)){
		var notFoundFormStr='form的ID未找到. 请正确填写jsp页面的form的Id.\n 当前转换的form的传入id是'+formIn+',  但以下是页面中定义的form\n';	
		var jspForm='';
		$('form').each(function(i,n){
			jspForm+='<form id='+this.id+' name='+this.name+'/>\n';
		});
		alert(notFoundFormStr+jspForm);
	}
	return form
}
/**
* invoke json data :
* 1: a.bs[0].id
* 2: a["bs"][0]["id"]
* translate form into json
*/
function formToJsonObject(form,type){
	var jsonObject = {};
	form=formTrim(form);
	for(i = 0, max = form.elements.length; i < max; i++) {
		var e = form.elements[i];
		var em = new Array();
		if(e.type == 'select-multiple'){
			for(j = 0; j < e.options.length; j++){
				op = e.options[j];
				if(op.selected){
					em[em.length] = op.value;
				}
			}
		}
		switch(e.type){
			case 'checkbox': 
			case 'radio': 
				if (!e.checked) { break; }
			case 'hidden': 
			case 'password': 
			case 'select-one':
			case 'select-multiple':
			case 'textarea':
			case 'text': 
				eName = e.name;
				if(eName==""){
					break;
				}
				if(e.type == 'select-multiple'){
					eValue = em;
				}else{	 
					eValue = e.value;//新平台不需要处理编码问题
				}
				//if eName has attributes
				if(eName.indexOf('.') > 0){
					dotIndex = eName.indexOf('.');
					parentName = eName.substring(0, dotIndex);
					childName = eName.substring(dotIndex+1);
					//handle eName`s attributes, pack them into jsonObject
					if(type)	iterJsonObject(jsonObject, parentName, childName, eValue);
					else	jsonObject[eName] = eValue;
				}else{
					jsonObject[eName] = eValue;
				}
				break; 
			case 'button': 
			case 'file': 
			case 'image': 
			case 'reset': 
			case 'submit': 
			default:  
		}
	}
	if(!type){
		//如果集合中没有objId的自动补上
		var array1 = new Array(); //必须包含objId的数组
		var array2 = new Array(); //当前包含objId的数组
		for (var one in jsonObject){
			var index = one.indexOf("].");
			if(-1 !== index)
				array1.push(one.substring(0,index+1) + ".objId");
			var indexObjId = one.indexOf("].objId");
			if(-1 != indexObjId)
				array2.push(one);
	    }
		array1 = removeDuplElem(array1);//去除数组中重复的元素
		
		for(var i=0; i<array1.length;i++){
			var flag = false; //标识当前数组中是否包含必须objId元素
			for(var j=0; j<array2.length;j++){
				if(array1[i]==array2[j]){
					flag = true;
					break;
				}
			}
			if(flag == false)
				jsonObject[array1[i]] = "";
		}
	}
    return jsonObject;
}

//删除数组中重复数据
function removeDuplElem(array){ 
	 for(var i=0; i<array.length; i++){
		 for(var j=i+1; j<array.length;j++){
		  if(array[i]==array[j]){
		        array = removeElement(j,array);//删除指定下标的元素
		      i=-1;
		      break;
		     }
		 }
	 }
	return array;  
} 
//删除数组 用到的函数
function removeElement(index,array){
	 if(index>=0 && index<array.length){
	    for(var i=index; i<array.length; i++){
	      array[i] = array[i+1];
	    }
	    array.length = array.length-1;
	  }
	  return array;
}

/**
* translate form element to json data repeatly
*/
function iterJsonObject(jsonObject, parentName, childName, eValue){
	//pArrayIndex : is Array
	pArrayIndex = parentName.indexOf('[');
	//is Collection, else just attribute
	if(pArrayIndex < 0){
		var child = jsonObject[parentName];
		if(!child){
			jsonObject[parentName] = {};
		}
		dotIndex = childName.indexOf('.');
		if(dotIndex > 0){
			iterJsonObject(jsonObject[parentName], childName.substring(0, dotIndex), childName.substring(dotIndex+1), eValue);
		}else{
			jsonObject[parentName][childName] = eValue;
		}
	}else{
		pArray = jsonObject[parentName.substring(0, pArrayIndex)];
		//if it isn`t exist a js Array, then init a Array
		if(!pArray){
			jsonObject[parentName.substring(0, pArrayIndex)] = new Array();
		}
		//get the Array index, and judget whether js object exist
		arrayIndex = parentName.substring(pArrayIndex+1, parentName.length-1);
		var c = jsonObject[parentName.substring(0, pArrayIndex)][arrayIndex];
		if(!c){
			jsonObject[parentName.substring(0, pArrayIndex)][arrayIndex] = {};
		}
		dotIndex = childName.indexOf('.');
		if(dotIndex > 0){
			iterJsonObject(jsonObject[parentName.substring(0, pArrayIndex)][arrayIndex], childName.substring(0, dotIndex), childName.substring(dotIndex+1), eValue);
		}else{
			jsonObject[parentName.substring(0, pArrayIndex)][arrayIndex][childName] = eValue;
		}
	}
}

function valueReplace(v){
	v=v.toString().replace(new RegExp('(["\"])', 'g'),"\\\"");
	return v;
}

/**
* settle json data to the form repeatly
*/
function iterValueFromJsonObject(jsonObject, parentName, childName){
	//pArrayIndex : is Array
	pArrayIndex = parentName.indexOf('[');
	//is Array, else is attribute
	try{
		if(pArrayIndex < 0){
			dotIndex = childName.indexOf('.');
			if(dotIndex > 0){
				return iterValueFromJsonObject(jsonObject[parentName], childName.substring(0, dotIndex), childName.substring(dotIndex+1));
			}else{
				
					return jsonObject[parentName][childName]
				
			}
		}else{
			pArray = jsonObject[parentName.substring(0, pArrayIndex)];
			//get the Array index, and judget whether js object exist
			arrayIndex = parentName.substring(pArrayIndex+1, parentName.length-1);
			var c = jsonObject[parentName.substring(0, pArrayIndex)][arrayIndex];
			dotIndex = childName.indexOf('.');
			if(dotIndex > 0){
				return iterValueFromJsonObject(jsonObject[parentName.substring(0, pArrayIndex)][arrayIndex], childName.substring(0, dotIndex), childName.substring(dotIndex+1));
			}else{
				return jsonObject[parentName.substring(0, pArrayIndex)][arrayIndex][childName]
			}
		}
	}catch(e){
		return "";
	}
}

/**
 *  ??form ??name??"_Arr"??????????????????????????????JSON??????
 * Param??JSON??????FORM ????
 * Author??ZhangHy
 * Since: 2008-12-13
 **/
 function ArrToJsonObject(jsonObject,form){
	var max = form.elements.length;
	var nameArr = new Array();
	var index = 0;
	var flag = true;
	for(var i=0;i<max ;i++){
		e = form.elements[i];
		var name = e.name;
		var subname = name.substr(name.length-4);
		for(var k=0 ;k<nameArr.length; k++){
			if(name == nameArr[k]){
				flag = false;
			}
		}
		if(subname == "_Arr" && flag == true){
			var Arr = document.getElementsByName(name);
			var jsonArr = [];
			for(var j=0;j<Arr.length;j++){
				jsonArr[j]=Arr[j].value;
			}
			jsonObject[name] = jsonArr;
			nameArr[index] = name;
			index+=1;
		}
	}
}

//reconstruct the json util javascript

if (!this.JsonUtils) {
    JsonUtils = {};
}

(function (){

	/**
	* put the jsonObject`s element to the form
	*/
	JsonUtils.jsonObjectToForm = function(form, jsonObject){
		for(i = 0, max = form.elements.length; i < max; i++) {
			e = form.elements[i];
			eName = e.name;
			if(eName.indexOf('.') > 0){
				dotIndex = eName.indexOf('.');
				parentName = eName.substring(0, dotIndex);
				childName = eName.substring(dotIndex+1);
				//handle the eName repeatly, pack it into the jsonObject
				eValue = iterValueFromJsonObject(jsonObject, parentName, childName);
			}else{
				eValue = jsonObject[eName];
			}
			if((typeof(eValue) == 'boolean') || (eValue && eValue != "undefined" && eValue != "null")){
				switch(e.type){
					case 'checkbox': 
					case 'radio': 
						if(e.value == eValue){
							e.checked = true;
						}
						break;
					case 'hidden': 
					case 'password': 
					case 'textarea':
					case 'text': 
						e.value = eValue;
						break;
					case 'select-one':
					case 'select-multiple':
						for(j = 0; j < e.options.length; j++){
							op = e.options[j];
							//alert("eName : " + eName + "; op value : " + op.value + "; eValue : " + eValue);
							if(op.value == String(eValue)){
								op.selected = true;
							}
						}
						break;
					case 'button': 
					case 'file': 
					case 'image': 
					case 'reset': 
					case 'submit': 
					default:  
				}
			}
		}
	}

	/**
	* invoke json data :
	* 1: a.bs[0].id
	* 2: a["bs"][0]["id"]
	* translate form into json
	*/
	JsonUtils.formToJsonObject = function(form){
		var jsonObject = {};
		for(i = 0, max = form.elements.length; i < max; i++) {
			e = form.elements[i];
			em = new Array();
			if(e.type == 'select-multiple'){
				for(j = 0; j < e.options.length; j++){
					op = e.options[j];
					if(op.selected){
						em[em.length] = op.value;
					}
				}
			}
			switch(e.type){
				case 'checkbox': 
				case 'radio': 
					if (!e.checked) { break; } 
				case 'hidden': 
				case 'password': 
				case 'select-one':
				case 'select-multiple':
				case 'textarea':
				case 'text': 
					eName = e.name;
					if(e.type == 'select-multiple'){
						eValue = em;
					}else{
						eValue = e.value;//.replace(new RegExp('(["\\\\])', 'g'), 'hjui$1');
					}
					//if eName has attributes
					if(eName.indexOf('.') > 0){
						dotIndex = eName.indexOf('.');
						parentName = eName.substring(0, dotIndex);
						childName = eName.substring(dotIndex+1);
						//handle eName`s attributes, pack them into jsonObject
						iterJsonObject(jsonObject, parentName, childName, eValue);
					}else{
						jsonObject[eName] = eValue;
					}
					break; 
				case 'button': 
				case 'file': 
				case 'image': 
				case 'reset': 
				case 'submit': 
				default:  
			}
		}
		return jsonObject;
	}

	JsonUtils.appendValue = function(jsonObject, eName, eValue){
		if(jsonObject){
			//if eName has attributes
			if(eName.indexOf('.') > 0){
				dotIndex = eName.indexOf('.');
				parentName = eName.substring(0, dotIndex);
				childName = eName.substring(dotIndex+1);
				//handle eName`s attributes, pack them into jsonObject
				iterJsonObject(jsonObject, parentName, childName, eValue);
			}else{
			//alert(" evalue : " + eValue);
				jsonObject[eName] = eValue;
			}
			//alert(JSON.stringify(jsonObject));
		}
	}
})();