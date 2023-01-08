package action;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import svc.OrderService;
import vo.ActionForward;
import vo.OrderBean;

public class OrderPayProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("===========================");
		System.out.println("OrderPayProAction 진입");
		ActionForward forward=null;

		try {
			String jsonStr = request.getParameter("cartJson");
			JSONParser jsonParser = new JSONParser();
			JSONArray cart = (JSONArray)jsonParser.parse(jsonStr);

			OrderBean order = new OrderBean();
			////orderInfo에 들어갈 데이터 (1회만 저장하면됨)
			order.setMember_id((String)request.getSession().getAttribute("sId"));
			order.setOrder_total_pay(Integer.parseInt(request.getParameter("totalPay")));
			order.setOrder_payment(request.getParameter("payment"));
			order.setOrder_point(Integer.parseInt(request.getParameter("usePoint"))*(-1));
			order.setOrder_imp_uid(request.getParameter("imp_uid"));
			order.setOrder_merchant_uid(request.getParameter("merchant_uid"));
			order.setOrder_name(request.getParameter("name"));
			order.setOrder_address(request.getParameter("postcode") + " " + request.getParameter("roadAddress") + " " + request.getParameter("addressDetail"));
			order.setOrder_phone(request.getParameter("phone1")+"-"+request.getParameter("phone2")+"-"+request.getParameter("phone3"));

			////orderProd에 들어갈 데이터 (상품별로 배열에 저장 필요)
			ArrayList<String> idx = new ArrayList<>();
			ArrayList<String> opt = new ArrayList<>();
			ArrayList<Integer> price = new ArrayList<>();
			ArrayList<Integer> cnt = new ArrayList<>();

			for(int i=0; i<cart.size(); i++) {

				JSONArray prodIdx = (JSONArray)cart.get(i);
				String order_prod = (String)prodIdx.get(0);
				idx.add(order_prod.substring(0, order_prod.indexOf("_opt_")));
				JSONArray prod = (JSONArray)prodIdx.get(1);

				JSONArray prodInfo = (JSONArray)prod.get(0);
				opt.add((String)prodInfo.get(1)==null?"-":(String)prodInfo.get(1));
				prodInfo = (JSONArray)prod.get(4);
				cnt.add(Integer.parseInt(String.valueOf(prodInfo.get(1))));
				prodInfo = (JSONArray)prod.get(1);
				price.add(Integer.parseInt(String.valueOf(prodInfo.get(1))));
			}

			order.setOrder_prod_idx(idx);
			order.setOrder_prod_opt(opt);
			order.setOrder_prod_cnt(cnt);
			order.setOrder_prod_price(price);


			///주문내역 저장하러가기
			OrderService service = new OrderService();
			Boolean isSuccessInsert = service.setProductList(order);

			//장바구니 비우기 및 페이지 이동
			try {
				if(isSuccessInsert) {
					forward = new ActionForward();
					forward.setPath("order/pay_end.jsp");
					forward.setRedirect(false);
				} else {
					response.setContentType("text/html; charset=UTF-8");
					PrintWriter out;
					out = response.getWriter();
					out.println("<script>");
					out.println("alert('주문 실패!')");
					out.println("history.back()");
					out.println("</script>");
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

		} catch (ParseException e) {
			e.printStackTrace();
		}


		return forward;
	}

}
