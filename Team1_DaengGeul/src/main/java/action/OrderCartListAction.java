package action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import svc.MemberInfoService;
import vo.ActionForward;
import vo.MemberBean;

public class OrderCartListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("===========================");
		System.out.println("OrderPayFormAction 진입");
		ActionForward forward=new ActionForward();

		try {
			String jsonStr = request.getParameter("cartJson");
			JSONParser jsonParser = new JSONParser();
			JSONArray cart = (JSONArray)jsonParser.parse(jsonStr);
			
			//장바구니 불러오기
			ArrayList<String> idx = new ArrayList<>();
			ArrayList<String> opt = new ArrayList<>();
			ArrayList<Integer> price = new ArrayList<>();
			
			for(int i=0; i<cart.size(); i++) {
				
				JSONArray prodIdx = (JSONArray)cart.get(i);
				String order_prod = (String)prodIdx.get(0);
				idx.add(order_prod.substring(0, order_prod.indexOf("_opt_")));
				JSONArray prod = (JSONArray)prodIdx.get(1);
				
				JSONArray prodInfo = (JSONArray)prod.get(0);
				opt.add((String)prodInfo.get(1)==null?"-":(String)prodInfo.get(1));
				
				System.out.println("prodIdx"+prodIdx);
				System.out.println("order_prod"+order_prod);
				System.out.println("prod"+prod);
				System.out.println("prodInfo"+prodInfo);
			}
			
			//품절 및 현재가격 불러오기
			
			
			
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		

		forward.setPath("order/cart_form.jsp");
		forward.setRedirect(false);

		return forward;
	}

}
