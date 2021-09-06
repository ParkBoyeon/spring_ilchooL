package project.spring.ilchooL.controllers;

import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;
import project.spring.ilchooL.helper.RetrofitHelper;
import project.spring.ilchooL.helper.WebHelper;
import project.spring.ilchooL.model.Covid19Item;
import project.spring.ilchooL.model.CovidItem;
import project.spring.ilchooL.model.DustItem;
import project.spring.ilchooL.model.Members;
import project.spring.ilchooL.model.Witem;
import project.spring.ilchooL.model.locItem;
import project.spring.ilchooL.schedulers.DustScheduler;
import project.spring.ilchooL.schedulers.WeatherScheduler;
import project.spring.ilchooL.service.CovidService;
import project.spring.ilchooL.service.DustService;
import project.spring.ilchooL.service.LocService;
import project.spring.ilchooL.service.MembersService;
import project.spring.ilchooL.service.WeatherService;

/**
 * 메인 페이지 컨트롤러
 */

@Slf4j
@Controller
public class MainController {
	@Autowired
	WebHelper webHelper;
	@Autowired
    MembersService membersService;
	@Autowired
	RetrofitHelper retrofitHelper;
	@Autowired
	CovidService covidService;
	@Autowired
	WeatherService weatherService;
	@Autowired
	DustService dustService;
	
	
	@Autowired LocService locService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model, HttpServletRequest request) {
		
		Witem witem = new Witem();
		witem.setNx("61");
		witem.setNy("126");
		
		DustItem ditem = new DustItem();
		ditem.setStationName("강남구");
		String loc = null;
		
		HttpSession session = request.getSession();		
		Members loginSession = (Members) session.getAttribute("member");
		
		if(loginSession != null) { 
			
			String[] tokens = loginSession.getAddr1().split(" ");
			
			if(!tokens[0].equals("서울")) {
				loc = "강남구";
			} else {
				loc = tokens[1];
			}
			
			locItem litem = new locItem();
			
			litem.setLoc(loc);
			
			locItem s_locItem = null;
			
			try {
				s_locItem = locService.searchLocItem(litem);
			} catch (Exception e) {
				e.printStackTrace();
			}	// end try
			
			witem.setNx(Integer.toString(s_locItem.getLoc_x()));
			witem.setNy(Integer.toString(s_locItem.getLoc_y()));
			
			ditem.setStationName(s_locItem.getLoc());
			
			model.addAttribute("loc", s_locItem.getLoc());
			
		}
		
		/**
		 * 캐러셀 - 날씨,미세먼지 데이터 조회
		 * @author 박수인
		 */

		List<Witem> w_item_list = null;
		List<DustItem> d_item_list = null;

		try {
			w_item_list = weatherService.getItemList(witem);
			d_item_list = dustService.getItemList(ditem);
			
		} catch (Exception e) {
			log.error(e.getLocalizedMessage());
			e.printStackTrace();
		}

		//미세먼지 데이터
		String dt = null;
		int pm10 = 0;
		int pm25 = 0;

		Calendar date = Calendar.getInstance();
		String d_time = String.format("%04d-%02d-%02d %02d:00", date.get(Calendar.YEAR), date.get(Calendar.MONTH) + 1,
				date.get(Calendar.DAY_OF_MONTH), date.get(Calendar.HOUR_OF_DAY)-1);

		for (int i = 0; i < d_item_list.size(); i++) {
			dt = d_item_list.get(i).getDataTime();

			// 'pm10' 값 조회
			if (dt.equals(d_time)) {
				if (d_item_list.get(i).getPm10Value().equals("-")){
					pm10 = 0;
				} else {
					pm10 = Integer.parseInt(d_item_list.get(i).getPm10Value());
				}
			}

			// 'pm25' 값 조회
			if (dt.equals(d_time)) {
				if (d_item_list.get(i).getPm25Value().equals("-")) {
					pm25 = 0;
				} else {
					pm25 = Integer.parseInt(d_item_list.get(i).getPm25Value());
				}
			}
		}

		model.addAttribute("pm10", pm10);
		model.addAttribute("pm25", pm25);

		
		//날씨 데이터
		String ca = null;
		String ti = null;
		String da = null;
		int sky = 0;
		int tmp = 0;
		int tmn = 0;
		int tmx = 0;
		int pty = 0;

		String fcst_time = String.format("%02d00", date.get(Calendar.HOUR_OF_DAY));
		String fcst_date = String.format("%04d%02d%02d", date.get(Calendar.YEAR), date.get(Calendar.MONTH) + 1,
				date.get(Calendar.DAY_OF_MONTH));

		for (int i = 0; i < w_item_list.size(); i++) {
			ca = w_item_list.get(i).getCategory();
			ti = w_item_list.get(i).getFcst_time();
			da = w_item_list.get(i).getFcst_date();
			
			if (da.equals(fcst_date) && ti.equals(fcst_time)) {
				if (ca.equals("SKY")) {
					sky = Integer.parseInt(w_item_list.get(i).getFcst_value());
				} else if (ca.equals("PTY")) {
					pty = Integer.parseInt(w_item_list.get(i).getFcst_value());
				} else if (ca.equals("TMP")) {
					tmp = Integer.parseInt(w_item_list.get(i).getFcst_value());
				} else if (ca.equals("TMN")) {
					tmn = Integer.parseInt(w_item_list.get(i).getFcst_value());
				} else if (ca.equals("TMX")) {
					tmx = Integer.parseInt(w_item_list.get(i).getFcst_value());
				} // end if
			} else {
				if (ca.equals("SKY")) {
					sky = Integer.parseInt(w_item_list.get(i).getFcst_value());
				} else if (ca.equals("PTY")) {
					pty = Integer.parseInt(w_item_list.get(i).getFcst_value());
				} else if (ca.equals("TMP")) {
					tmp = Integer.parseInt(w_item_list.get(i).getFcst_value());
				} else if (ca.equals("TMN")) {
					tmn = Integer.parseInt(w_item_list.get(i).getFcst_value());
				} else if (ca.equals("TMX")) {
					tmx = Integer.parseInt(w_item_list.get(i).getFcst_value());
				} // end if
			}	// end if
		} // end for

		model.addAttribute("sky", sky);
		model.addAttribute("pty", pty);
		model.addAttribute("tmp", tmp);
		model.addAttribute("tmn", tmn);
		model.addAttribute("tmx", tmx);

		
		/**
		 *  시각화 구현을 위한 데이터 조회 
		 */
		
		List<Witem> chart_item_list = null;
		List<Witem> chart_tmx_list = null;
		List<Witem> chart_tmn_list = null;
		List<DustItem> chart_pm_list = null;
		
		try {
			chart_item_list = weatherService.getChartItemList(witem);
			chart_tmx_list = weatherService.getTmxItemList(witem);
			chart_tmn_list = weatherService.getTmnItemList(witem);
			chart_pm_list = dustService.getPmList(ditem);
			
		} catch (Exception e) {
			log.error(e.getLocalizedMessage());
			e.printStackTrace();
			}
		
		// pm10, pm25 시각화 데이터 요청
		// 'pm10': ~30:좋음, ~80:보통, ~150:나쁨, ~:매우나쁨
		// 'pm25': ~15:좋음, ~35:보통, ~75:나쁨, ~:매우나쁨
		int pm10_great =0;
		int pm10_good =0;
		int pm10_bad =0;
		int pm10_worst =0;
		int pm25_great =0;
		int pm25_good =0;
		int pm25_bad =0;
		int pm25_worst =0;
		
		for (int i = 0; i < chart_pm_list.size(); i++) {
			String pm10_data = chart_pm_list.get(i).getPm10Value();
			String pm25_data = chart_pm_list.get(i).getPm25Value();
			String bar = "-";
			
			  if(!pm10_data.equals(bar)) { 
				  int pm10value = Integer.parseInt(pm10_data);
				  System.out.println(pm10value);
			  
				  if(pm10value <= 15) {
					  pm10_great += 1; 
				  }else if(pm10value >15 && pm10value <=35) {
					  pm10_good += 1; 
				  }else if(pm10value >35 && pm10value <=75) {
					  pm10_bad += 1; 
				  }else {
					  pm10_worst += 1; 
				  } 
			  }
			
			  if(!pm25_data.equals(bar)) { 
				  int pm25value = Integer.parseInt(pm25_data);
				  System.out.println(pm25value);
			  
				  if(pm25value <= 30) {
					  pm25_great += 1; 
				  }else if(pm25value >30 && pm25value <=80) {
					  pm25_good += 1; 
				  }else if(pm25value >80 && pm25value <=150) {
					  pm25_bad += 1; 
				  }else {
					  pm25_worst += 1; 
				  } 
			  }
		}
		
			model.addAttribute("pm10_great",pm10_great );
			model.addAttribute("pm10_good",pm10_good );
			model.addAttribute("pm10_bad",pm10_bad );
			model.addAttribute("pm10_worst",pm10_worst );
			model.addAttribute("pm25_great",pm25_great );
			model.addAttribute("pm25_good",pm25_good );
			model.addAttribute("pm25_bad",pm25_bad );
			model.addAttribute("pm25_worst",pm25_worst );

		// PTY 시각화 데이터 요청
		int sunny_0 = 0;
		int rainy_1 = 0;
		int sleet_2 = 0;
		int snow_3 = 0;
		int shower_4 = 0;
		int value = 0;

		for (int i = 0; i < chart_item_list.size(); i++) {

				value = Integer.parseInt(chart_item_list.get(i).getFcst_value());

				if (value == 0) {
					sunny_0 += 1;
				} else if (value == 1) {
					rainy_1 += 1;
				} else if (value == 2) {
					sleet_2 += 1;
				} else if (value == 3) {
					snow_3 += 1;
				} else if (value == 4) {
					shower_4 += 1;
				}
		}

		model.addAttribute("sunny", sunny_0);
		model.addAttribute("rainy", rainy_1);
		model.addAttribute("sleet", sleet_2);
		model.addAttribute("snow", snow_3);
		model.addAttribute("shower", shower_4);
		
		
		// TMX, TMN 시각화 데이터 요청
		int tmn_data = 0;
		int tmx_data = 0;
		int day_dt = 0;
		String day_data = null;
		
		for (int i = 0; i < chart_tmx_list.size(); i++) {
				tmx_data = Integer.parseInt(chart_tmx_list.get(i).getFcst_value());
				model.addAttribute("tmx_data"+i, tmx_data);
		}
		
		for (int i = 0; i < chart_tmn_list.size(); i++) {
				tmn_data = Integer.parseInt(chart_tmn_list.get(i).getFcst_value());
				day_dt = Integer.parseInt(chart_tmn_list.get(i).getFcst_date().substring(6));
				day_data = "'"+ day_dt + "일'";

				model.addAttribute("tmn_data"+i, tmn_data);
				model.addAttribute("day_data"+i, day_data);
		}

		/**
		 * 코로나 데이터 조회
		 * @author 박준영
		 */

		// pojo 클래스 선언
		List<CovidItem> covid_output = null;
		Covid19Item cI = new Covid19Item();

		try {
			// 데이터 받는 부분
			covid_output = covidService.getCovidList();
		} catch (Exception e) {
			e.printStackTrace();
		}

		int con_item = 0;
		String date_item = null;
		int death_item = 0;
		int released_item = 0;
		int active_item = 0;

		for (int i = 0; i < covid_output.size(); i++) {
			con_item = covid_output.get(i).getConfirmed();
			date_item = covid_output.get(i).getDate().substring(4);
			date_item = Integer.parseInt(date_item.substring(0, 2)) + "월 " + Integer.parseInt(date_item.substring(2))
					+ "일";
			death_item = covid_output.get(i).getDeath();
			released_item = covid_output.get(i).getReleased();
			active_item = covid_output.get(i).getActive();

			model.addAttribute("date_" + i, date_item);
			model.addAttribute("confirmed_" + i, con_item);
			model.addAttribute("death_" + i, death_item);
			model.addAttribute("released_" + i, released_item);
			model.addAttribute("active_" + i, active_item);
		}

		String datetime = null;
		datetime = cI.getDatetime();

		model.addAttribute("datetime", datetime);
		model.addAttribute("confirmed_acc", covid_output.get(0).getConfirmed_acc());
		model.addAttribute("date", covid_output.get(0).getDate());
		model.addAttribute("death_acc", covid_output.get(0).getDeath_acc());
		model.addAttribute("released_acc", covid_output.get(0).getReleased_acc());
		
		// View 처리
		return "main/main";
	}

	/** 교통 페이지 이동 컨트롤러 */
	@RequestMapping(value = "/contents/contents_transport.do", method = RequestMethod.GET)
	public ModelAndView transport(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();		
		Members loginSession = (Members) session.getAttribute("member");
		
		if(loginSession == null) { 
			return webHelper.redirect("/ilchooL/account/login.do", "로그인 후 이용해주세요.");
		}
		return new ModelAndView("contents/contents_transport");
	}
}