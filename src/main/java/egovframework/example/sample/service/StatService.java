package egovframework.example.sample.service;

import java.util.List;

public interface StatService {
	
	// 해당 지역의 평점+후기 높은 TOP 5 병원을 조회한다.
	List<?> statisticsRate(String province, String city) throws Exception;
	
	// 해당 지역의 재방문율 높은 TOP 5 병원을 조회한다.
	List<?> statisticsRetention(String province, String city) throws Exception;
}