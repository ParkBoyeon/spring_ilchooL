package project.spring.ilchooL.schedulers;

import java.io.IOException;

import org.apache.ibatis.session.SqlSession;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import lombok.extern.slf4j.Slf4j;
import project.spring.ilchooL.model.Finance;
import project.spring.ilchooL.model.FinancePopular;
import project.spring.ilchooL.model.FinanceTop;
import project.spring.ilchooL.service.FinanceService;

@Slf4j
@Controller
public class FinanceScheduler {
	
	@Autowired
	FinanceService financeService;
	
	@Autowired
	SqlSession sqlSession;
	
	public void finance() throws IOException {
		
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
		
		
		/** 해외증시 테이블 DB 저장 */
		Finance input = new Finance();
		
		// 한번 실행 후 주석 달아놓기
		/**
		  input.setF_name0(f_name[0]); input.setF_amount0(f_amount[0]);
		  input.setF_prev0(f_prev[0]); input.setF_updown0(f_updown[0]);
		  sqlSession.insert("financeMapper.insertFinance", input);
		  
		  input.setF_name0(f_name[1]); input.setF_amount0(f_amount[1]);
		  input.setF_prev0(f_prev[1]); input.setF_updown0(f_updown[1]);
		  sqlSession.insert("financeMapper.insertFinance", input);
		  
		  input.setF_name0(f_name[2]); input.setF_amount0(f_amount[2]);
		  input.setF_prev0(f_prev[2]); input.setF_updown0(f_updown[2]);
		  sqlSession.insert("financeMapper.insertFinance", input);
		  
		  input.setF_name0(f_name[3]); input.setF_amount0(f_amount[3]);
		  input.setF_prev0(f_prev[3]); input.setF_updown0(f_updown[3]);
		  sqlSession.insert("financeMapper.insertFinance", input);
		  
		  input.setF_name0(f_name[4]); input.setF_amount0(f_amount[4]);
		  input.setF_prev0(f_prev[4]); input.setF_updown0(f_updown[4]);
		  sqlSession.insert("financeMapper.insertFinance", input);
		  
		  
		  try { log.debug("finance 테이블 데이터 저장 >> " + financeService.addFinance()); }
		  catch (Exception e) { log.debug("데이터 저장에 실패했습니다."); e.printStackTrace(); }
		 */
		


		// 한번 실행 후 여기 주석 풀기
		
		
		input.setF_name0(f_name[0]);
		input.setF_amount0(f_amount[0]);
		input.setF_prev0(f_prev[0]);
		input.setF_updown0(f_updown[0]);
		sqlSession.update("financeMapper.updateFinance1", input);
		
		input.setF_name0(f_name[1]);
		input.setF_amount0(f_amount[1]);
		input.setF_prev0(f_prev[1]);
		input.setF_updown0(f_updown[1]);
		sqlSession.update("financeMapper.updateFinance2", input);
		
		input.setF_name0(f_name[2]);
		input.setF_amount0(f_amount[2]);
		input.setF_prev0(f_prev[2]);
		input.setF_updown0(f_updown[2]);
		sqlSession.update("financeMapper.updateFinance3", input);
		
		input.setF_name0(f_name[3]);
		input.setF_amount0(f_amount[3]);
		input.setF_prev0(f_prev[3]);
		input.setF_updown0(f_updown[3]);
		sqlSession.update("financeMapper.updateFinance4", input);
		
		input.setF_name0(f_name[4]);
		input.setF_amount0(f_amount[4]);
		input.setF_prev0(f_prev[4]);
		input.setF_updown0(f_updown[4]);
		sqlSession.update("financeMapper.updateFinance5", input);
		
		try {
			log.debug("finance 테이블 데이터 갱신 >> " + financeService.updateFinance());
		} catch (Exception e) {
			log.debug("데이터 갱신에 실패했습니다.");
			e.printStackTrace();
		}
		
		
		
		/** 인기종목 테이블 DB 저장 */
		FinancePopular input_p = new FinancePopular();
		
		
		// 한번 실행 후 주석 달아놓기
		/**
		input_p.setP_name0(p_name[0]);
		input_p.setP_amount0(p_amount[0]);
		input_p.setP_prev0(p_prev[0]);
		if (p_down[0] == null || p_down[0] == "") {
			input_p.setP_updown0("상승");
		} else {
			input_p.setP_updown0("하락");
		}
		sqlSession.insert("financePopularMapper.insertFinancePopular", input_p);
		
		input_p.setP_name0(p_name[1]);
		input_p.setP_amount0(p_amount[1]);
		input_p.setP_prev0(p_prev[1]);
		if (p_down[1] == null || p_down[1] == "") {
			input_p.setP_updown0("상승");
		} else {
			input_p.setP_updown0("하락");
		}
		sqlSession.insert("financePopularMapper.insertFinancePopular", input_p);
		
		input_p.setP_name0(p_name[2]);
		input_p.setP_amount0(p_amount[2]);
		input_p.setP_prev0(p_prev[2]);
		if (p_down[2] == null || p_down[2] == "") {
			input_p.setP_updown0("상승");
		} else {
			input_p.setP_updown0("하락");
		}
		sqlSession.insert("financePopularMapper.insertFinancePopular", input_p);
		
		input_p.setP_name0(p_name[3]);
		input_p.setP_amount0(p_amount[3]);
		input_p.setP_prev0(p_prev[3]);
		if (p_down[3] == null || p_down[3] == "") {
			input_p.setP_updown0("상승");
		} else {
			input_p.setP_updown0("하락");
		}
		sqlSession.insert("financePopularMapper.insertFinancePopular", input_p);
		
		input_p.setP_name0(p_name[4]);
		input_p.setP_amount0(p_amount[4]);
		input_p.setP_prev0(p_prev[4]);
		if (p_down[4] == null || p_down[4] ==  "") {
			input_p.setP_updown0("상승");
		} else {
			input_p.setP_updown0("하락");
		}
		sqlSession.insert("financePopularMapper.insertFinancePopular", input_p);
		
		
		try {
			log.debug("finance_popular 테이블 데이터 저장 >> " + financeService.addFinancePopular());
		} catch (Exception e) {
			log.debug("데이터 저장에 실패했습니다.");
			e.printStackTrace();
		}
		
		*/
		
		
		
		// 한번 실행 후 여기 주석 풀기 
		
		input_p.setP_name0(p_name[0]);
		input_p.setP_amount0(p_amount[0]);
		input_p.setP_prev0(p_prev[0]);
		if (p_down[0] == null || p_down[0] == "") {
			input_p.setP_updown0("상승");
		} else {
			input_p.setP_updown0("하락");
		}
		sqlSession.update("financePopularMapper.updateFinancePopular1", input_p);
		
		input_p.setP_name0(p_name[1]);
		input_p.setP_amount0(p_amount[1]);
		input_p.setP_prev0(p_prev[1]);
		if (p_down[1] == null || p_down[1] == "") {
			input_p.setP_updown0("상승");
		} else {
			input_p.setP_updown0("하락");
		}
		sqlSession.update("financePopularMapper.updateFinancePopular2", input_p);
		
		input_p.setP_name0(p_name[2]);
		input_p.setP_amount0(p_amount[2]);
		input_p.setP_prev0(p_prev[2]);
		if (p_down[2] == null || p_down[2] == "") {
			input_p.setP_updown0("상승");
		} else {
			input_p.setP_updown0("하락");
		}
		sqlSession.update("financePopularMapper.updateFinancePopular3", input_p);
		
		input_p.setP_name0(p_name[3]);
		input_p.setP_amount0(p_amount[3]);
		input_p.setP_prev0(p_prev[3]);
		if (p_down[3] == null || p_down[3] == "") {
			input_p.setP_updown0("상승");
		} else {
			input_p.setP_updown0("하락");
		}
		sqlSession.update("financePopularMapper.updateFinancePopular4", input_p);
		
		input_p.setP_name0(p_name[4]);
		input_p.setP_amount0(p_amount[4]);
		input_p.setP_prev0(p_prev[4]);
		if (p_down[4] == null || p_down[4] ==  "") {
			input_p.setP_updown0("상승");
		} else {
			input_p.setP_updown0("하락");
		}
		sqlSession.update("financePopularMapper.updateFinancePopular5", input_p);
		
		try {
			log.debug("finance_popular 테이블 데이터 갱신 >> " + financeService.updateFinancePopular());
		} catch (Exception e) {
			log.debug("데이터 갱신에 실패했습니다.");
			e.printStackTrace();
		}
		
		
		
		
		
		/** 거래상위 테이블 DB 저장 */
		FinanceTop input_t = new FinanceTop();
		
		
		// 한번 실행 후 주석 달아놓기
		/**
		input_t.setT_name0(t_name[0]);
		input_t.setT_amount0(t_amount[0]);
		input_t.setT_prev0(t_prev[0]);
		input_t.setT_updown0(t_updown[0]);
		input_t.setT_rate0(t_rate[0]);
		sqlSession.insert("financeTopMapper.insertFinanceTop", input_t);
		
		input_t.setT_name0(t_name[1]);
		input_t.setT_amount0(t_amount[1]);
		input_t.setT_prev0(t_prev[1]);
		input_t.setT_updown0(t_updown[1]);
		input_t.setT_rate0(t_rate[1]);
		sqlSession.insert("financeTopMapper.insertFinanceTop", input_t);
		
		input_t.setT_name0(t_name[2]);
		input_t.setT_amount0(t_amount[2]);
		input_t.setT_prev0(t_prev[2]);
		input_t.setT_updown0(t_updown[2]);
		input_t.setT_rate0(t_rate[2]);
		sqlSession.insert("financeTopMapper.insertFinanceTop", input_t);
		
		input_t.setT_name0(t_name[3]);
		input_t.setT_amount0(t_amount[3]);
		input_t.setT_prev0(t_prev[3]);
		input_t.setT_updown0(t_updown[3]);
		input_t.setT_rate0(t_rate[3]);
		sqlSession.insert("financeTopMapper.insertFinanceTop", input_t);
		
		input_t.setT_name0(t_name[4]);
		input_t.setT_amount0(t_amount[4]);
		input_t.setT_prev0(t_prev[4]);
		input_t.setT_updown0(t_updown[4]);
		input_t.setT_rate0(t_rate[4]);
		sqlSession.insert("financeTopMapper.insertFinanceTop", input_t);
		
		try {
			log.debug("finance_top 테이블 데이터 저장 >> " + financeService.addFinanceTop());
		} catch (Exception e) {
			log.debug("데이터 저장에 실패했습니다.");
			e.printStackTrace();
		}
		*/
		
		// 한번 실행 후 여기 주석 풀기
		
		input_t.setT_name0(t_name[0]);
		input_t.setT_amount0(t_amount[0]);
		input_t.setT_prev0(t_prev[0]);
		input_t.setT_updown0(t_updown[0]);
		input_t.setT_rate0(t_rate[0]);
		sqlSession.update("financeTopMapper.updateFinanceTop1", input_t);
		
		input_t.setT_name0(t_name[1]);
		input_t.setT_amount0(t_amount[1]);
		input_t.setT_prev0(t_prev[1]);
		input_t.setT_updown0(t_updown[1]);
		input_t.setT_rate0(t_rate[1]);
		sqlSession.update("financeTopMapper.updateFinanceTop2", input_t);
		
		input_t.setT_name0(t_name[2]);
		input_t.setT_amount0(t_amount[2]);
		input_t.setT_prev0(t_prev[2]);
		input_t.setT_updown0(t_updown[2]);
		input_t.setT_rate0(t_rate[2]);
		sqlSession.update("financeTopMapper.updateFinanceTop3", input_t);
		
		input_t.setT_name0(t_name[3]);
		input_t.setT_amount0(t_amount[3]);
		input_t.setT_prev0(t_prev[3]);
		input_t.setT_updown0(t_updown[3]);
		input_t.setT_rate0(t_rate[3]);
		sqlSession.update("financeTopMapper.updateFinanceTop4", input_t);
		
		input_t.setT_name0(t_name[4]);
		input_t.setT_amount0(t_amount[4]);
		input_t.setT_prev0(t_prev[4]);
		input_t.setT_updown0(t_updown[4]);
		input_t.setT_rate0(t_rate[4]);
		sqlSession.update("financeTopMapper.updateFinanceTop5", input_t);
		
		try {
			log.debug("finance_top 테이블 데이터 갱신 >> " + financeService.updateFinanceTop());
		} catch (Exception e) {
			log.debug("데이터 갱신에 실패했습니다.");
			e.printStackTrace();
		}
		
		

	}

}
