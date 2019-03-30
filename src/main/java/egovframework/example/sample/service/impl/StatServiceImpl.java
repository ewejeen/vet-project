package egovframework.example.sample.service.impl;

import java.util.List;

import egovframework.example.sample.service.StatService;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


@Service("statService")
public class StatServiceImpl extends EgovAbstractServiceImpl implements StatService {

	private static final Logger LOGGER = LoggerFactory.getLogger(StatServiceImpl.class);

	@Resource(name="statMapper")
	private StatMapper statDAO;

	// 평점, 후기 통계
	@Override
	public List<?> statisticsRate(String province, String city) throws Exception {
		return statDAO.statisticsRate(province, city);
	}
	
	// 재방문율 높은 병원
	@Override
	public List<?> statisticsRetention(String province, String city) throws Exception {
		return statDAO.statisticsRetention(province, city);
	}
}
