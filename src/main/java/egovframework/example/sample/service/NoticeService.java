package egovframework.example.sample.service;

import java.util.List;

/**
 * 
 *		NoticeService.java
 *	
 */

public interface NoticeService {
	
	// 공지 글 목록을 조회한다.
	List<?> selectNoticeList(SampleDefaultVO searchVO) throws Exception;
	
	// 공지 글 총 갯수를 조회한다.
	int selectNoticeListTotCnt(SampleDefaultVO searchVO);

	// 공지 글을 등록한다.
	void insertNotice(NoticeVO vo) throws Exception;

	// 공지 글을 수정한다.
	void updateNotice(NoticeVO vo) throws Exception;

	// 공지 글을 삭제한다.
	void deleteNotice(NoticeVO vo) throws Exception;

	// 공지 글 관련 정보를 넘겨 준다.
	NoticeVO selectNotice(String id) throws Exception;

	
	
	/*************앱***************/
	
	// JSON 형태로 공지사항의 목록을 조회한다.
	List<?> noticeList() throws Exception;
}