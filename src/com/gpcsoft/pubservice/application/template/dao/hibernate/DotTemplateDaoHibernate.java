package com.gpcsoft.pubservice.application.template.dao.hibernate;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.gpcsoft.core.dao.hibernate.BaseGenericDaoHibernate;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.epp.common.domain.CommonEnum;
import com.gpcsoft.pubservice.application.template.dao.DotTemplateDao;
import com.gpcsoft.pubservice.application.template.domain.DotTemplate;

@Repository
public class DotTemplateDaoHibernate extends BaseGenericDaoHibernate<DotTemplate> implements DotTemplateDao {

	/** 
	 * Description :  根据参数获取范本列表集合
	 * Create Date: 2011-8-3上午08:34:58 by likg  Modified Date: 2011-8-3上午08:34:58 by likg
	 * @param   categoryCode:品目编号  districtCode:区域Code  templateType:范本类型  orgInfoId:机构Id
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public List<DotTemplate> getTemplateListByParam(final Map<String, Object> param) throws Exception {
		return (List<DotTemplate>) getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				String categoryCode = (String) param.get("categoryCode"); //品目编号
				String districtCode = (String) param.get("districtCode"); //区域Code
				String templateType = (String) param.get("templateType"); //范本类型
				String orgInfoId = (String) param.get("orgInfoId"); //机构Id
				String keyWord = (String) param.get("keyWord"); //关键字
				String objIds = (String) param.get("objIds"); //范本Id，以逗号分割
				Boolean isShare = (Boolean) param.get("isShare"); //是否共享
				
				StringBuilder hql = new StringBuilder();
				hql.append(" from DotTemplate t where 1=1");
				hql.append(" and t.useStatus = '").append(CommonEnum.USER_STATUS_FORMAL).append("' ");
				
				//品目过滤
				if(StringUtils.hasLength(categoryCode)) {
					String[] codes = categoryCode.split(",");
					hql.append(" and ( 1=2 ");
					for(String code : codes) {
						hql.append(" or t.categoryCode like '%").append(code).append("%' ");
					}
					hql.append(" )");
				}
				//区域过滤
				if(StringUtils.hasLength(districtCode)) {
					String[] dCodes = districtCode.split(",");
					hql.append(" and ( 1=2 ");
					for(String dCode : dCodes) {
						String tempCode = dCode;
						if(dCode.endsWith("0000")) {
							tempCode = dCode.substring(0, 2);
						} else if(dCode.endsWith("00")) {
							tempCode = dCode.substring(0, 4);
						}
						hql.append(" or t.districtCode like '").append(tempCode).append("%' or t.districtCode like '%,").append(tempCode).append("%' ");
					}
					hql.append(" )");
				}
				//范本类型过滤
				if(StringUtils.hasLength(templateType)) {
					hql.append(" and t.templateType = '").append(templateType).append("' ");
				}
				//机构过滤
				if(StringUtils.hasLength(orgInfoId)) {
					hql.append(" and t.org.objId = '").append(orgInfoId).append("' ");
				}
				//关键字过滤
				if(StringUtils.hasLength(keyWord)) {
					hql.append(" and (t.templateName like '%").append(keyWord).append("%' or t.templateDesc like '%").append(keyWord).append("%' ) ");
				}
				//主键过滤
				if(StringUtils.hasLength(objIds)) {
					hql.append(" and t.objId in (:objIds)");
				}
				//是否共享过滤
				if(isShare != null) {
					hql.append(" and t.isShare = ").append(isShare ? "1" : "0").append(" ");
				}
				
				Query query = session.createQuery(hql.toString());
				if(StringUtils.hasLength(objIds)) {
					query.setParameterList("objIds", objIds.split(","));
				}
				return query.list();
			}
		});
	}

	/** 
	 * Description :  获取范本列表数据为前台展示使用
	 * Create Date: 2011-8-3下午01:04:52 by likg  Modified Date: 2011-8-3下午01:04:52 by likg
	 * @param   page:分页数据  param(categoryCode:品目编号  districtCode:区域Code  templateType:范本类型)
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public Page<DotTemplate> getTemplateListForShow(final Page<DotTemplate> page, final Map<String, Object> param) throws Exception {
		return (Page<DotTemplate>) getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				String categoryCode = (String) param.get("categoryCode"); //品目编号
				String districtCode = (String) param.get("districtCode"); //区域Code
				String templateType = (String) param.get("templateType"); //范本类型
				String orgInfoId = (String) param.get("orgInfoId"); //机构Id
				String keyWord = (String) param.get("keyWord"); //关键字
				String isShare = (String) param.get("isShare"); //是否共享
				String createTimeSort = (String) param.get("createTimeSort"); //创建时间排序
				String favoriteNumSort = (String) param.get("favoriteNumSort"); //收藏次数排序
				String downNumSort = (String) param.get("downNumSort"); //下载次数排序
				String usedNumSort = (String) param.get("usedNumSort"); //使用次数排序
				
				String queryHql = "from DotTemplate t ";
				String queryCountHql = "select count(t.objId) from DotTemplate t ";
				StringBuilder hql = new StringBuilder();
				hql.append(" where t.useStatus = '").append(CommonEnum.USER_STATUS_FORMAL).append("' ");
				
				//品目过滤
				if(StringUtils.hasLength(categoryCode)) {
					hql.append(" and t.categoryCode like '%").append(categoryCode).append("%' ");
				}
				//区域过滤
				if(StringUtils.hasLength(districtCode)) {
					String tempCode = districtCode;
					if(districtCode.endsWith("0000")) {
						tempCode = districtCode.substring(0, 2);
					} else if(districtCode.endsWith("00")) {
						tempCode = districtCode.substring(0, 4);
					}
					hql.append(" and (t.districtCode like '").append(tempCode).append("%' or t.districtCode like '%,").append(tempCode).append("%') ");
				}
				//范本类型过滤
				if(StringUtils.hasLength(templateType)) {
					hql.append(" and t.templateType = '").append(templateType).append("' ");
				}
				//机构过滤
				if(StringUtils.hasLength(orgInfoId)) {
					hql.append(" and t.org.objId = '").append(orgInfoId).append("' ");
				}
				//关键字过滤
				if(StringUtils.hasLength(keyWord)) {
					hql.append(" and (t.templateName like '%").append(keyWord).append("%' or t.templateDesc like '%").append(keyWord).append("%' ) ");
				}
				//是否共享过滤
				if(StringUtils.hasLength(isShare)) {
					hql.append(" and t.isShare = ").append(isShare).append(" ");
				}
				
				//排序条件
				StringBuilder orderHql = new StringBuilder();
				if(StringUtils.hasLength(createTimeSort) || StringUtils.hasLength(favoriteNumSort) || StringUtils.hasLength(downNumSort) || StringUtils.hasLength(usedNumSort)) {
					orderHql.append(" order by ");
					if(StringUtils.hasLength(createTimeSort)) {
						orderHql.append("t.createTime ").append(createTimeSort).append(",");
					}
					if(StringUtils.hasLength(favoriteNumSort)) {
						orderHql.append("t.favoriteNum ").append(favoriteNumSort).append(",");
					}
					if(StringUtils.hasLength(downNumSort)) {
						orderHql.append("t.downNum ").append(downNumSort).append(",");
					}
					if(StringUtils.hasLength(usedNumSort)) {
						orderHql.append("t.usedNum ").append(usedNumSort).append(",");
					}
					
					if(orderHql.toString().endsWith(",")) {
						orderHql.delete(orderHql.length()-1, orderHql.length());
					}
				} else {
					orderHql.append(" order by t.createTime desc ");
				}
				
				Query query = session.createQuery(queryHql + hql + orderHql);
				query.setFirstResult(page.getStart()).setMaxResults(page.getPageSize());
				page.setData(query.list());
				query = session.createQuery(queryCountHql + hql);
				page.setTotalRowNum((Long) query.list().get(0));
				
				return page;
			}
		});
	}

}
