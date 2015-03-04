

//获得选中项的索引 
jQuery.fn.getSelectedIndex = function() { 
	return jQuery(this).get(0).selectedIndex; 
} 

//获得当前选中项的文本 
jQuery.fn.getSelectedText = function() { 
	if(this.size() == 0) { 
		return ""; 
	} 
	else { 
		var index = this.getSelectedIndex(); 
		return jQuery(this).get(0).options[index].text; 
	} 
} 

//获得当前选中项的值 
jQuery.fn.getSelectedValue = function() { 
	if(this.size() == 0){ 
		return ""; 
	} 
	else { 
		return jQuery(this).val(); 
	} 
} 

//判断select项中是否存在值为value的项 
jQuery.fn.isExistItem = function(value) { 
	var isExist = false; 
	var count = this.size(); 
	for(var i=0;i<count;i++){ 
		if(jQuery(this).get(0).options[i].value == value) { 
			isExist = true; 
			break; 
		} 
	} 
	return isExist; 
} 

//删除select中值为value的项，如果该项不存在，则提示 
jQuery.fn.removeItem = function(value) { 
	if(this.isExistItem(value)){ 
		var count = this.size(); 
		for(var i=0;i<count;i++){ 
			if(jQuery(this).get(0).options[i].value == value) { 
				jQuery(this).get(0).remove(i); 
				break; 
			} 
		} 
	}
} 
