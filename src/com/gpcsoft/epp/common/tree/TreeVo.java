package com.gpcsoft.epp.common.tree;

import java.util.HashSet;
import java.util.Set;

import com.gpcsoft.core.tree.BaseTree;

/** 
 *  Comments: <strong>TreeVo</strong>            		
 *	 <br/>		        																							
 *  <br/>CopyRright (c)2011-xxxx:   珠海政采软件技术有限公司    		
 *  <br/>Project:   zh-epp                    					          
 *  <br/>Module ID:     		
 *  <br/>Create Date：2011-10-9 上午10:16:25 by chenhongjun    					                            
 *  <br/>Modified Date:  2011-10-9 上午10:16:25 by chenhongjun                                   
 *<p>@since 0.5
 *   @version: 0.5 
 */
@SuppressWarnings("unchecked")
public class TreeVo implements BaseTree {
	
	private boolean isLeaf=false;//是否有子结点
	private Set<TreeVo> children=new HashSet<TreeVo>();
	private String treeId;//树ID
	private String treeName;//树名称
	private String target;//目标连接
	private String parentId;  //父结点
	private String level;//层级
	
	
	
	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public void setLeaf(boolean isLeaf) {
		this.isLeaf = isLeaf;
	}

	/* Description :
	 * @param   
	 * @return  
	 * @Exception   
	 *  Create Date: 2011-10-9上午10:16:43 by chenhongjun  
	 *  Modified Date: 2011-10-9上午10:16:43 by chenhongjun 
	 * @see com.gpcsoft.core.tree.BaseTree#getChildren()
	 *
	 */
	public Set<TreeVo> getChildren() {
		return children;
	}

	/* Description :
	 * @param   
	 * @return  
	 * @Exception   
	 *  Create Date: 2011-10-9上午10:16:43 by chenhongjun  
	 *  Modified Date: 2011-10-9上午10:16:43 by chenhongjun 
	 * @see com.gpcsoft.core.tree.BaseTree#getIsLeaf()
	 *
	 */
	public Boolean getIsLeaf() {
		return isLeaf;
	}
 
 
	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public String getTreeId() {
		return treeId;
	}

	public void setTreeId(String treeId) {
		this.treeId = treeId;
	}

	public String getTreeName() {
		return treeName;
	}

	public void setTreeName(String treeName) {
		this.treeName = treeName;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}
	
}
