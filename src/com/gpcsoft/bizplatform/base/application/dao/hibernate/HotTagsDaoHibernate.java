package com.gpcsoft.bizplatform.base.application.dao.hibernate;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.gpcsoft.bizplatform.base.application.dao.HotTagsDao;
import com.gpcsoft.bizplatform.base.application.domain.HotTags;
import com.gpcsoft.core.dao.hibernate.BaseGenericDaoHibernate;
import com.gpcsoft.core.utils.HqlResultConvertUtils;
import com.gpcsoft.core.utils.StringUtils;

@Repository
public class HotTagsDaoHibernate extends BaseGenericDaoHibernate<HotTags> implements HotTagsDao {

	/** 
	 * Description :  取得热门标签
	 * Create Date: 2010-10-7下午05:29:16 by yucy  Modified Date: 2010-10-7下午05:29:16 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public List<HotTags> getHotTagsList(final Map<String, Object> params)throws Exception {
		return (List<HotTags>) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
					String sql = " select tag.tags_id,"+
						       " tag.tags_object_id,"+
						       " tag.tags_object_name,"+
						       " tag.tags_object_dscr"+
						       " from ECP_PUB_TAGS tag"+
						       " where tag.tags_object_type = '"+ (String)params.get("tagsType") +"' ";
					
					//排序方式
					if(StringUtils.hasLength((String)params.get("order"))) {
						String[] order = params.get("order").toString().split("_");
						sql += " order by tag." + order[0] + " " + order[1];
					}
					sql = sql.replace("tagsId", "tags_object_id");
					Query query = session.createSQLQuery(sql);
					String[] queryColum = new String[]{"objId","tagsId","tagsName","tagsDscr"};  //由于内置对象无法转，所以暂时使用remark存放上级id
					if(null!=params.get("maxResults")){
						query.setMaxResults((Integer)params.get("maxResults"));//设置最大记录数
					}
					return HqlResultConvertUtils.hqlResultConvert(query.list(),queryColum,HotTags.class);
				}
		});
	}
}
