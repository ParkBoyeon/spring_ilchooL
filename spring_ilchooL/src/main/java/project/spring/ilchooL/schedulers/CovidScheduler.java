package project.spring.ilchooL.schedulers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import lombok.extern.slf4j.Slf4j;
import project.spring.ilchooL.helper.RetrofitHelper;
import project.spring.ilchooL.model.Covid19Item;
import project.spring.ilchooL.model.CovidItem;
import project.spring.ilchooL.service.CovidService;
import project.spring.ilchooL.service.PhpCovidService;
import retrofit2.Call;
import retrofit2.Retrofit;

@Slf4j
@Controller
public class CovidScheduler {
	
	@Autowired RetrofitHelper retrofitHelper;
	@Autowired CovidService covidService;
	
	/**
	 * covid19 데이터 수집 메서드
	 */
	public void collecCovid() {
		Retrofit retrofit = retrofitHelper.getRetrofit(PhpCovidService.BASE_URL);
		
		PhpCovidService apiCovidService = retrofit.create(PhpCovidService.class);
		
		Call<Covid19Item> call = apiCovidService.getCovidCount();
		Covid19Item cCount = null;
		
		try {
			cCount = call.execute().body();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		String[] active = null;
		String[] confirmed = null;
		String[] confirmed_acc = null;
		String[] date = null;
		String[] death = null;
		String[] death_acc = null;
		String[] released = null;
		String[] released_acc = null;
		if (cCount != null) {
			active = cCount.getData_covid().getSeoul().getPhp_active();
			confirmed = cCount.getData_covid().getSeoul().getPhp_confirmed();
			confirmed_acc = cCount.getData_covid().getSeoul().getPhp_confirmed_acc();
			date = cCount.getData_covid().getSeoul().getPhp_date();
			death = cCount.getData_covid().getSeoul().getPhp_death();
			death_acc = cCount.getData_covid().getSeoul().getPhp_death_acc();
			released = cCount.getData_covid().getSeoul().getPhp_released();
			released_acc = cCount.getData_covid().getSeoul().getPhp_released_acc();
		}

		CovidItem ci = new CovidItem();

		for (int i = date.length - 7; i < date.length; i++) {
			ci.setDate(date[i]);
			ci.setActive(Integer.parseInt(active[i]));
			ci.setConfirmed(Integer.parseInt(confirmed[i]));
			ci.setConfirmed_acc(Integer.parseInt(confirmed_acc[i]));
			ci.setDeath(Integer.parseInt(death[i]));
			ci.setDeath_acc(Integer.parseInt(death_acc[i]));
			ci.setReleased(Integer.parseInt(released[i]));
			ci.setReleased_acc(Integer.parseInt(released_acc[i]));
			try {
				covidService.collectCovid(ci);
			} catch (Exception e) {
				log.error(e.getLocalizedMessage());
				e.printStackTrace();
			}	// end try
		}	// end for
	}	// end collecCovid()
}
