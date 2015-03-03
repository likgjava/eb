<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>

<div class="cols">
	<h2>品目分类 </h2>
	<div class="industryClassBox">
		<c:forEach var="pc1" items="${purCategoryList}">
		<div class="categoryItem">
			<h4><a href="javascript:void(0);" onclick="ShowSupplierIndex.toSupplierList('${pc1.categoryCode}');">${pc1.categoryName }</a></h4>
			<div class="categoryContent">
				<ul class="subcategoryList">
					<c:forEach var="pc2" items="${pc1.children}">
					<li>
						<h5><a href="javascript:void(0);" onclick="ShowSupplierIndex.toSupplierList('${pc2.categoryCode}');">${pc2.categoryName }</a></h5>
						<div>
							<c:forEach var="pc3" items="${pc2.children}">
							<a href="javascript:void(0);" onclick="ShowSupplierIndex.toSupplierList('${pc3.categoryCode}');">${pc3.categoryName }</a>
							</c:forEach>
						</div>
					</li>
					</c:forEach>
                </ul>
              </div>
		</div>
		</c:forEach>
	</div>
</div>