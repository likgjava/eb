package com.gpcsoft.epp.eval.dao.hibernate;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.gpcsoft.core.dao.hibernate.BaseGenericDaoHibernate;
import com.gpcsoft.epp.eval.dao.CongruousFactorDao;
import com.gpcsoft.epp.eval.domain.CongruousFactor;

@Repository
public class CongruousFactorDaoHibernate extends BaseGenericDaoHibernate<CongruousFactor> implements CongruousFactorDao {

	/**
	 * @Description: 获取指标分值总和
	 * @param factorTypeId 分类ID
	 * @return Double
	 * @throws Exception
	 * @Create Date 2010-9-19 下午02:31:55 By wanghz
	 */
	@SuppressWarnings("unchecked")  
	public Double getScoreSum(final String factorTypeId){
		return (Double)getHibernateTemplate().execute(new HibernateCallback(){
			public Double doInHibernate(Session session)throws HibernateException, SQLException {
				String hql = " select sum(factor.score) from CongruousFactor as factor where factor.factorType.objId=:factorTypeId ";
				Double scoreSum = (Double) session.createQuery(hql).setString("factorTypeId", factorTypeId).list().get(0);
				return scoreSum!=null?scoreSum:0;
			}});
	}
	/**
	 * FuncName: getCongruousFactorList
	 * Description :  获取指标集合(电子评标系统接口)
	 * @param 
	 * @return List<CongruousFactor>
	 * @author: shenjz
	 * @Create Date:2011-3-24  下午01:35:07
	 * @Modifier: shenjz
	 * @Modified Date:2011-3-24  下午01:35:07
	 */
	public List<CongruousFactor> getCongruousFactorList(String projectCode,String packCode){
		StringBuffer sb = new StringBuffer();
		sb.append("select c1 from CongruousFactor c1,FactypeMfactor c2 where c1.objId = c2.factor.objId and c1.factorType.projId=(select p.objId from Project p where p.projCode='"+projectCode+"')");
		if(packCode!=null&&!"".equals(packCode)){
			sb.append(" and c2.projId = (select p.objId from Project p where p.projCode='"+packCode+"' and p.parentId=(select p.objId from Project p where p.projCode='"+projectCode+"'))");
		}
		return this.findbyHql(sb.toString());
	}
	
	/**
	 * FuncName: getCongruousFactorList
	 * Description :  获取所有指标
	 * @param 
	 * @return List<CongruousFactor>
	 * @author: yangyc
	 * @Create Date:2011-11-29  下午05:35:07
	 * @Modifier: yangyc
	 * @Modified Date:2011-11-29  下午05:35:07
	 */
	public List<CongruousFactor> getAllFactorByProjectId(String projectId){
		StringBuffer sb = new StringBuffer();
		sb.append("select c1 from CongruousFactor c1,FactypeMfactor c2 where c1.objId = c2.factor.objId and c2.projId='"+projectId+"'");
		return this.findbyHql(sb.toString());
	}
	
	
	
	
	/**
	 * FuncName: saveCongruousFactor
	 * Description :  保存指标数据
	 * @param 
	 * @return List<CongruousFactor>
	 * @author: zhouzhanghe
	 * @Create Date:2011-7-6 17:27
	 */
	@SuppressWarnings("unchecked")
	public void saveCongruousFactor(final List<Map> congruousFactorList) {
		getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
			String projId = (String) congruousFactorList.get(0).get("projId");
			
			/*删除原来的指标相关数据*/
			String del_ECP_CONGRUOUS_FACTOR = "delete from ECP_CONGRUOUS_FACTOR t1 where t1.con_fac_id in( select t2.con_fac_id from ECP_FACTYPE_M_FACTOR t2,ECP_CONGRUOUS_FACTOR_TYPE t3 where t2.con_fac_type_id = t3.con_fac_type_id and t3.tender_id='"+projId+"')";
			String del_ECP_FACTYPE_M_FACTOR = "delete from ECP_FACTYPE_M_FACTOR t2 where t2.con_fac_type_id in(select t3.con_fac_type_id from ECP_CONGRUOUS_FACTOR_TYPE t3 where t3.tender_id='"+projId+"')";
			String del_ECP_CONGRUOUS_FACTOR_TYPE = "delete from ECP_CONGRUOUS_FACTOR_TYPE where tender_id='"+projId+"'";
			session.createSQLQuery(del_ECP_CONGRUOUS_FACTOR).executeUpdate();	
			session.createSQLQuery(del_ECP_FACTYPE_M_FACTOR).executeUpdate();	
			session.createSQLQuery(del_ECP_CONGRUOUS_FACTOR_TYPE).executeUpdate();	
			
			/*插入新的指标数据*/
			String insert_ECP_CONGRUOUS_FACTOR_TYPE = "insert into ECP_CONGRUOUS_FACTOR_TYPE(Con_Fac_Type_Id,Tender_Id,Audit_Type,Type_Code,Type_Name,Show_No,Is_Leaf,Weight_Coefficient,Audit_Method,Remark,PARENT_ID,TREE_LEVEL) ";
			String insert_ECP_CONGRUOUS_FACTOR = "insert into ECP_CONGRUOUS_FACTOR(CON_FAC_ID,Con_Fac_Type_Id,Factor_Name,Score,Proj_Ids,Proj_Names,Audit_Method,Remark,SCORE_MAX,SCORE_MIN,FACTOR_CODE,IS_NEED,AUDIT_TYPE) ";
			String insert_ECP_FACTYPE_M_FACTOR = "insert into ECP_FACTYPE_M_FACTOR(TYPE_M_FAC_ID,CON_FAC_TYPE_ID,CON_FAC_ID,SUBTENDERID) ";

			Map<String,String> packIdMap = new  HashMap<String,String>();
			Map<String,String> packNameMap = new  HashMap<String,String>();
			for(Map map : congruousFactorList){
				String packId = (String) map.get("packId");
				String name = (String)map.get("name");
				String score = (String)map.get("score");
				String packName = (String)map.get("packName");
				String isLeaf = (String)map.get("isLeaf");
				String nameScore = name+score;
				if("Y".equals(isLeaf)){
					
					if(packIdMap.get(nameScore)==null){
						packIdMap.put(nameScore, packId);
					}else{
						String packs =  packIdMap.get(nameScore)+","+packId;
						packIdMap.put(nameScore, packs);
					}
					
					if(packNameMap.get(nameScore)==null){
						packNameMap.put(nameScore, packName);
					}else{
						String packs =  packNameMap.get(nameScore)+","+packName;
						packNameMap.put(nameScore, packs);
					}
				}
			}
			Map<String,String> factoryTypeMap = new  HashMap<String,String>();
			Map<String,Integer> factoryTypeLevelMap = new  HashMap<String,Integer>();  //指标分类级别map
			for(Map map : congruousFactorList){
				String packId = (String) map.get("packId");
				if(packId==null){
					packId = "";
				}
				String name = (String)map.get("name");
				if(name==null){
					name = "";
				}
				String code = (String)map.get("code");
				if(code==null){
					code = "";
				}
				String auditType = (String)map.get("auditType");
				if(auditType==null){
					auditType = "";
				}
				String auditMethod = (String)map.get("auditMethod");
				if(auditMethod==null){
					auditMethod = "";
				}
				String score = (String)map.get("score");
				if(score==null){
					score = "";
				}
				String scoreMin = (String)map.get("scoreMin");
				if(scoreMin==null){
					scoreMin = "";
				}
				String scoreMax = (String)map.get("scoreMax");
				if(scoreMax==null){
					scoreMax = "";
				}
				String rate = (String)map.get("rate");
				if(rate==null){
					rate = "";
				}
				String isNeed = (String)map.get("isNeed");
				if(isNeed==null){
					isNeed = "";
				}
				String remark = (String)map.get("remark");
				if(remark==null){
					remark = "";
				}
				String id = (String)map.get("id");
				if(id==null){
					id = "";
				}
				String parentId = (String)map.get("parentId");
				if(parentId==null){
					parentId = "";
				}
				String isLeaf = (String)map.get("isLeaf");
				if(isLeaf==null){
					isLeaf = "";
				}
				String sortNo = (String)map.get("sortNo");
				if(sortNo==null){
					sortNo = "";
				}
				String nameScore = name+score;
				if("N".equals(isLeaf)){//保存指标类型
					if(factoryTypeMap.get(nameScore)==null){ //如果该指标分类未录入
						int level = 0;
						if("".equals(parentId)){  //表示1级指标
							level = 1;
						}else if(factoryTypeLevelMap.get(parentId)!=null){
							level = (Integer)factoryTypeLevelMap.get(parentId)+1;
						}
						factoryTypeLevelMap.put(id, level);
						session.createSQLQuery(insert_ECP_CONGRUOUS_FACTOR_TYPE + " values('"+id+"','"+projId+"','"+auditType+"','"+code+"','"+name+"','"+sortNo+"','0','"+rate+"','"+auditMethod+"','"+remark+"','"+((factoryTypeMap.get(parentId)!=null)?factoryTypeMap.get(parentId):parentId)+"','"+level+"')").executeUpdate();
						factoryTypeMap.put(nameScore, id);
					}else{ //将下一个指标分类的id替换成第一个同名指标分类的id
						factoryTypeMap.put(id, factoryTypeMap.get(nameScore));
					}
				}else{//保存指标
					if(factoryTypeMap.get(nameScore)==null){//如果该指标未录入
						session.createSQLQuery(insert_ECP_CONGRUOUS_FACTOR + " values('"+id+"'," +
								"'"+((factoryTypeMap.get(parentId)!=null)?factoryTypeMap.get(parentId):parentId)+"" +
								"','"+name+"','"+score+"','"+packIdMap.get(nameScore)+"','"+packNameMap.get(nameScore)+"','"+auditMethod+"','"+remark +"','"+scoreMax+"','"+scoreMin+"','"+code+"',"+("".equals(isNeed) ? "'01'" : "'"+isNeed+"'")+",'"+auditType+"')").executeUpdate();
						factoryTypeMap.put(nameScore, id);
					}else{  //将下一个指标的id替换成第一个同名指标的id
						factoryTypeMap.put(id, factoryTypeMap.get(nameScore));
					}
					session.createSQLQuery(insert_ECP_FACTYPE_M_FACTOR + " values('"+UUID.randomUUID()+"','"+((factoryTypeMap.get(parentId)!=null)?factoryTypeMap.get(parentId):parentId)+"','"+((factoryTypeMap.get(id)!=null)?factoryTypeMap.get(id):id)+"','"+packId+"')").executeUpdate();
				}
			}
			return 1;
			}
		});
	}
}
