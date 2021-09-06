package project.spring.ilchooL.controllers;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import project.spring.ilchooL.helper.WebHelper;
import project.spring.ilchooL.model.Kosdaq;
import project.spring.ilchooL.model.Kospi;
import project.spring.ilchooL.model.Members;
import project.spring.ilchooL.service.KosdaqService;

@Controller
public class FinanceController {
	@Autowired
	SqlSession sqlSession;
	
	@Autowired
	KosdaqService kosdaqService;
	
	@Autowired
	WebHelper webHelper;
	
	/** "/프로젝트이름" 에 해당하는 ContextPath 변수 주입 */
	@Value("#{servletContext.contextPath}")
	String contextPath;

	@RequestMapping(value = "/contents/contents_finance.do", method = RequestMethod.GET)
	public ModelAndView Finance(Model model, HttpServletRequest request) throws IOException {
		
		HttpSession session = request.getSession();		
		Members loginSession = (Members) session.getAttribute("member");
		
		if(loginSession == null) { 
			return webHelper.redirect("/ilchooL/account/login.do", "로그인 후 이용해주세요.");
		}
		
		String url = "https://finance.naver.com/main/main.nhn";
		Document doc = Jsoup.connect(url).get();

		/** 해외 증시 */
		// 숫자 (앞: 순위), (뒤: 1종목명, 2현재가, 3전일대비)
		Elements elestock11 = doc.select("div.aside_stock table.tbl_home tbody tr:nth-child(1) th:nth-child(1)");
		Elements elestock12 = doc.select("div.aside_stock table.tbl_home tbody tr:nth-child(1) td:nth-child(2)");
		Elements elestock13 = doc.select("div.aside_stock table.tbl_home tbody tr:nth-child(1) td:nth-child(3)");
		
		Elements elestock21 = doc.select("div.aside_stock table.tbl_home tbody tr:nth-child(2) th:nth-child(1)");
		Elements elestock22 = doc.select("div.aside_stock table.tbl_home tbody tr:nth-child(2) td:nth-child(2)");
		Elements elestock23 = doc.select("div.aside_stock table.tbl_home tbody tr:nth-child(2) td:nth-child(3)");
		
		Elements elestock31 = doc.select("div.aside_stock table.tbl_home tbody tr:nth-child(3) th:nth-child(1)");
		Elements elestock32 = doc.select("div.aside_stock table.tbl_home tbody tr:nth-child(3) td:nth-child(2)");
		Elements elestock33 = doc.select("div.aside_stock table.tbl_home tbody tr:nth-child(3) td:nth-child(3)");
		
		Elements elestock41 = doc.select("div.aside_stock table.tbl_home tbody tr:nth-child(4) th:nth-child(1)");
		Elements elestock42 = doc.select("div.aside_stock table.tbl_home tbody tr:nth-child(4) td:nth-child(2)");
		Elements elestock43 = doc.select("div.aside_stock table.tbl_home tbody tr:nth-child(4) td:nth-child(3)");
		
		Elements elestock51 = doc.select("div.aside_stock table.tbl_home tbody tr:nth-child(5) th:nth-child(1)");
		Elements elestock52 = doc.select("div.aside_stock table.tbl_home tbody tr:nth-child(5) td:nth-child(2)");
		Elements elestock53 = doc.select("div.aside_stock table.tbl_home tbody tr:nth-child(5) td:nth-child(3)");
		
		String[] f_name = new String[5];
		String[] f_amount = new String[5];
		String[] f_updown = new String[5];
		String[] f_prev = new String[5];
		
		f_name[0] = elestock11.text();
		f_amount[0] = elestock12.text();
		f_prev[0] = elestock13.text().substring(2);
		f_updown[0] = elestock13.text().substring(0,2);
		
		f_name[1] = elestock21.text();
		f_amount[1] = elestock22.text();
		f_prev[1] = elestock23.text().substring(2);
		f_updown[1] = elestock23.text().substring(0,2);

		f_name[2] = elestock31.text();
		f_amount[2] = elestock32.text();
		f_prev[2] = elestock33.text().substring(2);
		f_updown[2] = elestock33.text().substring(0,2);

		f_name[3] = elestock41.text();
		f_amount[3] = elestock42.text();
		f_prev[3] = elestock43.text().substring(2);
		f_updown[3] = elestock43.text().substring(0,2);

		f_name[4] = elestock51.text();
		f_amount[4] = elestock52.text();
		f_prev[4] = elestock53.text().substring(2);
		f_updown[4] = elestock53.text().substring(0,2);


		/** 인기 검색 종목 (인기 종목) */
		// 숫자 (앞: 순위), (뒤: 뒤: 1종목명, 2현재가, 3전일대비)
		Elements elepopular11 = doc.select("div.aside_popular table.tbl_home tbody tr:nth-child(1) th:nth-child(1)");
		Elements elepopular12 = doc.select("div.aside_popular table.tbl_home tbody tr:nth-child(1) td:nth-child(2)");
		Elements elepopular13 = doc.select("div.aside_popular table.tbl_home tbody tr:nth-child(1) td:nth-child(3)");
		Elements elepopularup1 = doc.select("div.aside_popular table.tbl_home tbody tr:nth-child(1).up .blind");
		Elements elepopulardown1 = doc.select("div.aside_popular table.tbl_home tbody tr:nth-child(1).down .blind");

		Elements elepopular21 = doc.select("div.aside_popular table.tbl_home tbody tr:nth-child(2) th:nth-child(1)");
		Elements elepopular22 = doc.select("div.aside_popular table.tbl_home tbody tr:nth-child(2) td:nth-child(2)");
		Elements elepopular23 = doc.select("div.aside_popular table.tbl_home tbody tr:nth-child(2) td:nth-child(3)");
		Elements elepopularup2 = doc.select("div.aside_popular table.tbl_home tbody tr:nth-child(2).up .blind");
		Elements elepopulardown2 = doc.select("div.aside_popular table.tbl_home tbody tr:nth-child(2).down .blind");

		Elements elepopular31 = doc.select("div.aside_popular table.tbl_home tbody tr:nth-child(3) th:nth-child(1)");
		Elements elepopular32 = doc.select("div.aside_popular table.tbl_home tbody tr:nth-child(3) td:nth-child(2)");
		Elements elepopular33 = doc.select("div.aside_popular table.tbl_home tbody tr:nth-child(3) td:nth-child(3)");
		Elements elepopularup3 = doc.select("div.aside_popular table.tbl_home tbody tr:nth-child(3).up .blind");
		Elements elepopulardown3 = doc.select("div.aside_popular table.tbl_home tbody tr:nth-child(3).down .blind");

		Elements elepopular41 = doc.select("div.aside_popular table.tbl_home tbody tr:nth-child(4) th:nth-child(1)");
		Elements elepopular42 = doc.select("div.aside_popular table.tbl_home tbody tr:nth-child(4) td:nth-child(2)");
		Elements elepopular43 = doc.select("div.aside_popular table.tbl_home tbody tr:nth-child(4) td:nth-child(3)");
		Elements elepopularup4 = doc.select("div.aside_popular table.tbl_home tbody tr:nth-child(4).up .blind");
		Elements elepopulardown4 = doc.select("div.aside_popular table.tbl_home tbody tr:nth-child(4).down .blind");

		Elements elepopular51 = doc.select("div.aside_popular table.tbl_home tbody tr:nth-child(5) th:nth-child(1)");
		Elements elepopular52 = doc.select("div.aside_popular table.tbl_home tbody tr:nth-child(5) td:nth-child(2)");
		Elements elepopular53 = doc.select("div.aside_popular table.tbl_home tbody tr:nth-child(5) td:nth-child(3)");
		Elements elepopularup5 = doc.select("div.aside_popular table.tbl_home tbody tr:nth-child(5).up .blind");
		Elements elepopulardown5 = doc.select("div.aside_popular table.tbl_home tbody tr:nth-child(5).down .blind");

		String[] p_name = new String[5];
		String[] p_amount = new String[5];
		String[] p_up = new String[5];
		String[] p_down = new String[5];
		String[] p_prev = new String[5];
		
		p_name[0] = elepopular11.text();
		p_amount[0] = elepopular12.text();
		p_prev[0] = elepopular13.text();
		p_up[0] = elepopularup1.text();
		p_down[0] = elepopulardown1.text();

		p_name[1] = elepopular21.text();
		p_amount[1] = elepopular22.text();
		p_prev[1] = elepopular23.text();
		p_up[1] = elepopularup2.text();
		p_down[1] = elepopulardown2.text();

		p_name[2] = elepopular31.text();
		p_amount[2] = elepopular32.text();
		p_prev[2] = elepopular33.text();
		p_up[2] = elepopularup3.text();
		p_down[2] = elepopulardown3.text();

		p_name[3] = elepopular41.text();
		p_amount[3] = elepopular42.text();
		p_prev[3] = elepopular43.text();
		p_up[3] = elepopularup4.text();
		p_down[3] = elepopulardown4.text();

		p_name[4] = elepopular51.text();
		p_amount[4] = elepopular52.text();
		p_prev[4] = elepopular53.text();
		p_up[4] = elepopularup5.text();
		p_down[4] = elepopulardown5.text();


		/** TOP 종목 (거래 상위) */
		// 숫자 (앞: 순위), (뒤: 1종목명, 2현재가, 3전일대비, 4등락률)
		Elements eletop11 = doc.select("table.tbl_home tbody#_topItems1 tr:nth-child(1) th:nth-child(1)");
		Elements eletop12 = doc.select("table.tbl_home tbody#_topItems1 tr:nth-child(1) td:nth-child(2)");
		Elements eletop13 = doc.select("table.tbl_home tbody#_topItems1 tr:nth-child(1) td:nth-child(3)");
		Elements eletop14 = doc.select("table.tbl_home tbody#_topItems1 tr:nth-child(1) td:nth-child(4)");

		Elements eletop21 = doc.select("table.tbl_home tbody#_topItems1 tr:nth-child(2) th:nth-child(1)");
		Elements eletop22 = doc.select("table.tbl_home tbody#_topItems1 tr:nth-child(2) td:nth-child(2)");
		Elements eletop23 = doc.select("table.tbl_home tbody#_topItems1 tr:nth-child(2) td:nth-child(3)");
		Elements eletop24 = doc.select("table.tbl_home tbody#_topItems1 tr:nth-child(2) td:nth-child(4)");

		Elements eletop31 = doc.select("table.tbl_home tbody#_topItems1 tr:nth-child(3) th:nth-child(1)");
		Elements eletop32 = doc.select("table.tbl_home tbody#_topItems1 tr:nth-child(3) td:nth-child(2)");
		Elements eletop33 = doc.select("table.tbl_home tbody#_topItems1 tr:nth-child(3) td:nth-child(3)");
		Elements eletop34 = doc.select("table.tbl_home tbody#_topItems1 tr:nth-child(3) td:nth-child(4)");

		Elements eletop41 = doc.select("table.tbl_home tbody#_topItems1 tr:nth-child(4) th:nth-child(1)");
		Elements eletop42 = doc.select("table.tbl_home tbody#_topItems1 tr:nth-child(4) td:nth-child(2)");
		Elements eletop43 = doc.select("table.tbl_home tbody#_topItems1 tr:nth-child(4) td:nth-child(3)");
		Elements eletop44 = doc.select("table.tbl_home tbody#_topItems1 tr:nth-child(4) td:nth-child(4)");

		Elements eletop51 = doc.select("table.tbl_home tbody#_topItems1 tr:nth-child(5) th:nth-child(1)");
		Elements eletop52 = doc.select("table.tbl_home tbody#_topItems1 tr:nth-child(5) td:nth-child(2)");
		Elements eletop53 = doc.select("table.tbl_home tbody#_topItems1 tr:nth-child(5) td:nth-child(3)");
		Elements eletop54 = doc.select("table.tbl_home tbody#_topItems1 tr:nth-child(5) td:nth-child(4)");

		String[] t_name = new String[5];
		String[] t_amount  = new String[5];
		String[] t_updown = new String[5];
		String[] t_prev = new String[5];
		String[] t_rate = new String[5];
		
		t_name[0] = eletop11.text();
		t_amount[0] = eletop12.text();
		t_prev[0] = eletop13.text().substring(2);
		t_rate[0] = eletop14.text();
		t_updown[0] = eletop13.text().substring(0,2);

		t_name[1] = eletop21.text();
		t_amount[1] = eletop22.text();
		t_prev[1] = eletop23.text().substring(2);
		t_rate[1] = eletop24.text();
		t_updown[1] = eletop23.text().substring(0,2);

		t_name[2] = eletop31.text();
		t_amount[2] = eletop32.text();
		t_prev[2] = eletop33.text().substring(2);
		t_rate[2] = eletop34.text();
		t_updown[2] = eletop33.text().substring(0,2);

		t_name[3] = eletop41.text();
		t_amount[3] = eletop42.text();
		t_prev[3] = eletop43.text().substring(2);
		t_rate[3] = eletop44.text();
		t_updown[3] = eletop43.text().substring(0,2);

		t_name[4] = eletop51.text();
		t_amount[4] = eletop52.text();
		t_prev[4] = eletop53.text().substring(2);
		t_rate[4] = eletop54.text();
		t_updown[4] = eletop53.text().substring(0,2);


		
		/** View 처리 */
		// 해외증시
		model.addAttribute("strstock11", f_name[0]);
		model.addAttribute("strstock12", f_amount[0]);
		model.addAttribute("strstock13", f_prev[0]);
		model.addAttribute("strstock21", f_name[1]);
		model.addAttribute("strstock22", f_amount[1]);
		model.addAttribute("strstock23", f_prev[1]);
		model.addAttribute("strstock31", f_name[2]);
		model.addAttribute("strstock32", f_amount[2]);
		model.addAttribute("strstock33", f_prev[2]);
		model.addAttribute("strstock41", f_name[3]);
		model.addAttribute("strstock42", f_amount[3]);
		model.addAttribute("strstock43", f_prev[3]);
		model.addAttribute("strstock51", f_name[4]);
		model.addAttribute("strstock52", f_amount[4]);
		model.addAttribute("strstock53", f_prev[4]);
		model.addAttribute("stockud1", f_updown[0]);
		model.addAttribute("stockud2", f_updown[1]);
		model.addAttribute("stockud3", f_updown[2]);
		model.addAttribute("stockud4", f_updown[3]);
		model.addAttribute("stockud5", f_updown[4]);
		
		// 인기종목
		model.addAttribute("strpopular11", p_name[0]);
		model.addAttribute("strpopular12", p_amount[0]);
		model.addAttribute("strpopular13", p_prev[0]);
		model.addAttribute("strpopular21", p_name[1]);
		model.addAttribute("strpopular22", p_amount[1]);
		model.addAttribute("strpopular23",  p_prev[1]);
		model.addAttribute("strpopular31", p_name[2]);
		model.addAttribute("strpopular32", p_amount[2]);
		model.addAttribute("strpopular33",  p_prev[2]);
		model.addAttribute("strpopular41", p_name[3]);
		model.addAttribute("strpopular42", p_amount[3]);
		model.addAttribute("strpopular43",  p_prev[3]);
		model.addAttribute("strpopular51", p_name[4]);
		model.addAttribute("strpopular52", p_amount[4]);
		model.addAttribute("strpopular53",  p_prev[4]);
		model.addAttribute("popularup1", p_up[0]);
		model.addAttribute("popularup2", p_up[1]);
		model.addAttribute("popularup3", p_up[2]);
		model.addAttribute("popularup4", p_up[3]);
		model.addAttribute("popularup5", p_up[4]);
		model.addAttribute("populardown1", p_down[0]);
		model.addAttribute("populardown2", p_down[1]);
		model.addAttribute("populardown3", p_down[2]);
		model.addAttribute("populardown4", p_down[3]);
		model.addAttribute("populardown5", p_down[4]);
		
		// 거래상위
		model.addAttribute("strtop11", t_name[0]);
		model.addAttribute("strtop12", t_amount[0]);
		model.addAttribute("strtop13", t_prev[0]);
		model.addAttribute("strtop14", t_rate[0]);
		model.addAttribute("strtop21", t_name[1]);
		model.addAttribute("strtop22", t_amount[1]);
		model.addAttribute("strtop23", t_prev[1]);
		model.addAttribute("strtop24", t_rate[1]);
		model.addAttribute("strtop31", t_name[2]);
		model.addAttribute("strtop32", t_amount[2]);
		model.addAttribute("strtop33", t_prev[2]);
		model.addAttribute("strtop34", t_rate[2]);
		model.addAttribute("strtop41", t_name[3]);
		model.addAttribute("strtop42", t_amount[3]);
		model.addAttribute("strtop43", t_prev[3]);
		model.addAttribute("strtop44", t_rate[3]);
		model.addAttribute("strtop51", t_name[4]);
		model.addAttribute("strtop52", t_amount[4]);
		model.addAttribute("strtop53", t_prev[4]);
		model.addAttribute("strtop54", t_rate[4]);
		model.addAttribute("topud1", t_updown[0]);
		model.addAttribute("topud2", t_updown[1]);
		model.addAttribute("topud3", t_updown[2]);
		model.addAttribute("topud4", t_updown[3]);
		model.addAttribute("topud5", t_updown[4]);
		
		
		
		/** 코스닥, 코스피 데이터 */
		
		List<Kosdaq> kosdaq = null;
		List<Kospi> kospi = null;
		
		try {
			kosdaq = kosdaqService.getKosdaqList();
			kospi = kosdaqService.getKospiList();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		SimpleDateFormat new_format = new SimpleDateFormat("yyyy-MM-dd");
		
		for(int i=0; i<kospi.size(); i++) {
			//kospi.get(i).setDate(new_format.format(kospi.get(i).getDate()));
			kospi.get(i).setDate(kospi.get(i).getDate().substring(0, 10));
		}
		
		for(int i=0; i<kosdaq.size(); i++) {
			//kosdaq.get(i).setDate(new_format.format(kosdaq.get(i).getDate()));
			kosdaq.get(i).setDate(kosdaq.get(i).getDate().substring(0, 10));
		}
		
		
		JSONArray kd_jsonArray = JSONArray.fromObject(kosdaq);
		JSONArray kp_jsonArray = JSONArray.fromObject(kospi);
		
		Map<String, Object> kd_map = new HashMap<String, Object>();
		Map<String, Object> kp_map = new HashMap<String, Object>();
		kd_map.put("kosdaq", kd_jsonArray);
		kp_map.put("kospi", kp_jsonArray);
		
		JSONObject kd_jsonObject = JSONObject.fromObject(kd_map);
		JSONObject kp_jsonObject = JSONObject.fromObject(kp_map);
		
		
		
		model.addAttribute("kosdaq", kd_jsonObject);
		model.addAttribute("kospi", kp_jsonObject);
		model.addAttribute("kd_size", kosdaq);
		model.addAttribute("kp_size", kospi);
		
		
		
		
		return new ModelAndView("contents/contents_finance");

	}
}
