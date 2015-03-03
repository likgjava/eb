package com.gpcsoft.epp.taskplan.dao.hibernate;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.gpcsoft.core.dao.hibernate.BaseGenericDaoHibernate;
import com.gpcsoft.core.dao.hibernate.query.QueryObject;
import com.gpcsoft.core.dao.hibernate.query.QueryParam;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.epp.taskplan.dao.RecordFormDao;
import com.gpcsoft.epp.taskplan.domain.RecordForm;

@Repository
@SuppressWarnings(value={"unchecked"})
public class RecordFormDaoHibernate extends BaseGenericDaoHibernate<RecordForm> implements RecordFormDao {
	
	/**
	 * FuncName: getRecordFormByPrjId
	 * Description :  根据项目主键获取备案书对象
	 * @param 
	 * @param projectId
	 * @return RecordForm
	 * @author: shenjz
	 * @Create Date:2011-5-23  下午01:50:29
	 * @Modifier: shenjz
	 * @Modified Date:2011-5-23  下午01:50:29
	 */
	public RecordForm getRecordFormByPrjId(String projectId){
		StringBuffer sb = new StringBuffer();
		sb.append("from RecordForm r");
		sb.append(" where r.project.objId = '"+projectId+"' ");
		List<RecordForm> recordFormList = this.findbyHql(sb.toString());
		if(recordFormList.size()>0){
			return recordFormList.get(0);
		}
		return null;
	}

	/**
	 * FuncName:  toTrackRecordFormList
	 * Description :  根据查询条件获得相关的备案书信息
	 * @param queryObject:组装的查询条件对象,page:分页对象
	 * @return Page<RecordForm>
	 * @author caojz
	 * @Create Date: 2011-8-30上午10:30:42   
	 * @Modifier caojz
	 * @Modified Date: 2011-8-30上午10:30:42 
	 */
	public Page<RecordForm> toTrackRecordFormList(QueryObject queryObject,
			Page<RecordForm> page) {
		StringBuffer hql = new StringBuffer();
		hql.append("from RecordForm r where 1=1 ");
		List<QueryParam> queryList = queryObject.getQueryParam().getAndParams();
		if(queryList != null && !queryList.isEmpty()){//若查询对象“与”操作有数据，遍历查询对象
		   for(int i=0;i<queryList.size();i++){
			   QueryParam queryParam = (QueryParam)queryList.get(i);
			   if ("projCode".equals(queryParam.getName())&&(String)queryParam.getValue()!=null&&!"".equals((String)queryParam.getValue())) {
					hql.append(" and r.project.projCode like '%"+(String)queryParam.getValue()+"%' ");
				}
			   if ("engineeringName".equals(queryParam.getName())&&(String)queryParam.getValue()!=null&&!"".equals((String)queryParam.getValue())) {
					hql.append(" and r.engineeringName like '%"+(String)queryParam.getValue()+"%' ");
				}
			   if ("recFormOrgName".equals(queryParam.getName())&&(String)queryParam.getValue()!=null&&!"".equals((String)queryParam.getValue())) {
					hql.append(" and r.recFormOrgName like '%"+(String)queryParam.getValue()+"%' ");
				}
			   if ("projManager".equals(queryParam.getName())&&(String)queryParam.getValue()!=null&&!"".equals((String)queryParam.getValue())) {
					hql.append(" and r.projManager like '%"+(String)queryParam.getValue()+"%' ");
				}
		   }
		}
		hql.append(" order by createTime desc");
		Page<RecordForm> pages = this.pageQuery(hql.toString(), page.getStart(), page.getPageSize(),RecordForm.class);
		return pages;
	}
		
}
