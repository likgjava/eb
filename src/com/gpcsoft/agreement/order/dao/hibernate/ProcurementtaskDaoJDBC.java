package com.gpcsoft.agreement.order.dao.hibernate;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.gpcsoft.agreement.order.dao.ProcurementtaskDao;
import com.gpcsoft.agreement.order.domain.Order;
import com.gpcsoft.agreement.order.domain.Procurementtask;
import com.gpcsoft.core.dao.jdbc.BaseDaoJDBC;
import com.gpcsoft.core.dao.jdbc.SplitPageResultSetExtractor;
import com.gpcsoft.core.utils.Page;

@Repository
public class ProcurementtaskDaoJDBC extends BaseDaoJDBC<Procurementtask> implements ProcurementtaskDao {

	@SuppressWarnings("unchecked")
	public Page<Order> listOrderBySql(Page page, HttpServletRequest request) {
		final String  protaskId = request.getParameter("protaskId");
		String sql="Select distinct ao.* From Eps_Agreement_Procurementtask ap, " +
								" Eps_Agreement_Protask_Item pi, " +
								" Eps_Agreement_Order ao, " +
								" Eps_Agreement_Order_Item oi," +
								" Eps_Agree_Order_Protask op " +
								" Where op.Order_Dtl_Id = oi.Order_Dtl_Id " +
								" And ao.Order_Id = oi.Order_Id " +
								" And ap.Task_Id = pi.Task_Id " +
								" And op.Task_Item_Id = pi.Task_Item_Id " +
								" And op.Order_Dtl_Id = oi.Order_Dtl_Id " +
								" And  ap.Task_Id = ?";
		//goodsList是分页查到的数据
		List<Order> orderList=(List<Order>) this.getJdbcTemplate().query(sql, 
				
					new PreparedStatementSetter(){
						public void setValues(PreparedStatement ps) throws SQLException {
						ps.setString(1, protaskId);
						}}, 
					new SplitPageResultSetExtractor(page.getStart(), 
					page.getPageSize(),
					new RowMapper<Order>() {
					public Order mapRow(ResultSet rs, int i) throws SQLException {
						Order order = new Order();
						order.setObjId(rs.getString(""));
						return order;
					}
			}));
		//totalRow是总的页数		count(g.goods_id)  注意进行groud by   多表查询join的之后使用disctict去掉重复的ID
					int totalRow=( this.getJdbcTemplate()).queryForInt(
					"Select count(distinct ao.*) From Eps_Agreement_Procurementtask ap, " +
									" Eps_Agreement_Protask_Item pi, " +
									" Eps_Agreement_Order ao, " +
									" Eps_Agreement_Order_Item oi," +
									" Eps_Agree_Order_Protask op " +
									" Where op.Order_Dtl_Id = oi.Order_Dtl_Id " +
									" And ao.Order_Id = oi.Order_Id " +
									" And ap.Task_Id = pi.Task_Id " +
									" And op.Task_Item_Id = pi.Task_Item_Id " +
									" And op.Order_Dtl_Id = oi.Order_Dtl_Id " +
									" And  ap.Task_Id = ?"
				,
				new Object[]{protaskId});
		Page<Order> pageData = new Page<Order>(page.getStart(), totalRow, page.getPageSize(), orderList);
		return pageData;
		
	}

}
