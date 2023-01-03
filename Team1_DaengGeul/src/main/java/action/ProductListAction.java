package action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.ProductListService;
import vo.ActionForward;
import vo.PageInfo;
import vo.ProductBean;

public class ProductListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("===========================");
		System.out.println("ProductListAction 진입");
		ActionForward forward=new ActionForward();
		ProductListService service = new ProductListService();
	
		////책인지 굿즈인지 구분 필요함
		String type = request.getParameter("type");
		String[] typeArr= type.split("_");
		String product_type = typeArr[0]; ///첫글자는 책인지 굿즈인지 구분
		String order = typeArr.length>1 ? typeArr[1]:"new"; //혹시 type에 한글자만 들어왔을 경우 defulat로 new
		String keyword=request.getParameter("keyword"); //장르, 검색시 들어옴
		String sort = "";

		//장르, 검색일때만 keyword 있고 이때만 type split이 3개로 됨
		if(keyword!=null) {
			sort=typeArr[2];
		} else if(type.equals("B_recomm")) {
			sort="recomm";
		} else if(type.equals("B_disc")) {
			sort="disc";
		}
		//장르, 검색할때만 갯수가 변하니까 sort로 지정
		int totalProduct = service.getProductCount(product_type, sort, keyword); 
		
		///갯수가 0개면 후속 작업 없이 바로 리턴
		if(totalProduct==0) {
			forward.setPath("order/product_list.jsp");
			forward.setRedirect(false);
			return forward;
		}
		
		////페이징 처리
		int pageNum = 1;
		if(request.getParameter("pageNum")!=null) {
			pageNum = Integer.parseInt(request.getParameter("pageNum"));
		}
		
		int pageListLimit = 3;
		int startPage = (int)((pageNum-1)/pageListLimit)*pageListLimit+1;
		int pageProductCount = 12; ///1페이지당 출력할 상품갯수
		int maxPage = (totalProduct-1)/pageProductCount+1;
		int pageStartRow = (pageNum-1) * pageProductCount;
		
		PageInfo pageInfo = new PageInfo();
		pageInfo.setPageListLimit(pageListLimit); //화면 밑에 보여줄 페이지 번호 갯수
		pageInfo.setStartPage(startPage);  //보여줄 페이지 번호 첫번호
		pageInfo.setEndPage(startPage+pageListLimit-1>maxPage? maxPage : startPage+pageListLimit-1); //보여줄 페이지 번호 끝번호
		pageInfo.setMaxPage(maxPage);
		
		
		List<ProductBean> productList = service.getProductList(product_type, order+"_"+sort, keyword, pageStartRow,pageProductCount);

		request.setAttribute("productList", productList);
		request.setAttribute("pageInfo", pageInfo);
		
		String ListPath = "order/product_list.jsp?type=" + request.getParameter("type") + "&pageNum=" + pageNum;
		
	
		forward.setPath(ListPath);
		forward.setRedirect(false);
		
		return forward;
	}

}
