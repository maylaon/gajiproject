package dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import util.JDBCUtil;

public class BoardDao {
	
	// 싱글톤 패턴
	private static BoardDao instance;
	private BoardDao(){}
	public static BoardDao getInstance(){
		if(instance == null){
			instance = new BoardDao();
		}
		return instance;
	}
	
	private JDBCUtil jdbc = JDBCUtil.getInstance();
	
	
	/*
	 * selectBoardList()			: List 		: 최신글 목록
	 * selectCategorySearchList()	: List 		: 카테고리 검색결과
	 * selectKeywordSearchList()	: List 		: 키워드 검색결과
	 * selectSalesList()			: List 		: 판매내역
	 * selectPurchaseList()			: List 		: 구매 내역
	 * selectWishList()				: List 		: 관심목록
	 * selectBoardView()			: Map 		: 글 조회
	 * insertBoard()				: int 		: 글 등록
	 * updateTitle()				: int 		: 제목 수정
	 * updateContents()				: int 		: 내용 수정
	 * updateCategory()				: int 		: 카테고리 수정
	 * updatePrice()				: int 		: 가격 수정
	 * updateSellState()			: int 		: 판매상태 수정
	 * updateBuyerId()				: int 		: 구매자이메일 수정
	 * deleteBoard()				: int 		: 글 삭제
	 * deleteBoard()				: int 		: 글 삭제(관리자용)
	 */


	
	public List<Map<String, Object>> selectBoardList(int pageNo){
		/*
		 * 최신글 목록
		 * 글번호 / 제목 / 지역/ 작성일/ 가격  / 판매상태 / 관심수 / 채팅수 
		 * 최신순으로 정렬
		 * 한페이지 10개씩 보여줌
		 */
		
		String sql = "SELECT aa.*" + 
				" FROM (SELECT ROWNUM rn, a.*" + 
				"        FROM( SELECT *" + 
				"                FROM board_select) a) aa" + 
				"WHERE rn BETWEEN (? - 1) * 11 AND ? * 11";
		
		List<Object> param = new ArrayList();
		param.add(pageNo);
		return jdbc.selectList(sql,param);
	}
	
	public List<Map<String, Object>> selectCategorySearchList(int pageNo, String ctNo){
		/*
		 * 카테고리 검색 결과
		 * 글번호 / 카테고리번호 / 제목 / 내용 / 닉네임 / 가격  / 작성일 / 판매상태 / 관심수 / 채팅수 / 지역 / 전체글수 / 현재페이지번호
		 * 최신순으로 정렬
		 * 한페이지 10개씩 보여줌
		 */
		
		String sql = "";
		
		return jdbc.selectList(sql);
	}
	
	public List<Map<String, Object>> selectKeywordSearchList(int pageNo, String keyword){
		/*
		 * 키워드 검색 결과
		 * 글번호 / 카테고리번호 / 제목 / 내용 / 닉네임 / 가격  / 작성일 / 판매상태 / 관심수 / 채팅수 / 지역 / 전체글수 / 현재페이지번호
		 * 최신순으로 정렬
		 * 한페이지 10개씩 보여줌
		 */
		
		String sql = "";
		
		return jdbc.selectList(sql);
	}

	public List<Map<String, Object>> selectSalesList(int pageNo, String userId){
		/*
		 * 판매내역
		 * 글번호 / 카테고리번호 / 제목 / 내용 / 닉네임 / 가격  / 작성일 / 판매상태 / 관심수 / 채팅수 / 지역 / 전체글수 / 현재페이지번호
		 * 최신순으로 정렬
		 * 한페이지 10개씩 보여줌
		 */
		
		String sql = "";
		
		return jdbc.selectList(sql);
	}
	
	public List<Map<String, Object>> selectPurchaseList(int pageNo, String userId) {
		/*
		 * 구매내역
		 * 글번호 / 카테고리번호 / 제목 / 내용 / 닉네임 / 가격  / 작성일 / 판매상태 / 관심수 / 채팅수 / 지역 / 전체글수 / 현재페이지번호
		 * 최신순으로 정렬
		 * 한페이지 10개씩 보여줌
		 */
		
		String sql = "";
		
		return jdbc.selectList(sql);
	}
	
	public List<Map<String, Object>> selectWishList(int pageNo, String userId){
		/*
		 * 관심목록
		 * 글번호 / 카테고리번호 / 제목 / 내용 / 닉네임 / 가격  / 작성일 / 판매상태 / 관심수 / 채팅수 / 지역 / 전체글수 / 현재페이지번호
		 * 최신순으로 정렬
		 * 한페이지 10개씩 보여줌
		 */
		String sql = "";

		return jdbc.selectList(sql);
	}
	
	public Map<String, Object> selectBoardView(int boardNo) {
		/*
		 * 글 조회
		 * 글번호 / 판매자이메일 / 카테고리번호 / 제목 / 내용 / 닉네임 / 가격  / 작성일 / 판매상태 / 지역 / 관심수 / 채팅수
		 */

		String sql = "";

		return jdbc.selectOne(sql);
	}
	
	public int insertBoard(String title, String contents, int ctNo, int price, String userId) {
		/*
		 * 글 등록
		 * 글번호 / 판매자이메일 / 카테고리번호 / 제목 / 내용 / 가격 / 작성일 / 판매상태 / [/ 구매자이메일]
		 */
		
		String sql = "INSERT INTO boord (board_no, seller_id, ct_no,"
				+ " title, contents, price, reg_date, sell_gb, buyer_id)"
				+ " VALUES (seq_board_no.nextval, ?, ?, ?, ?, ?, SYSDATE, 1, null)";

		List<Object> param = new ArrayList<>();
		param.add(userId);
		param.add(ctNo);
		param.add(title);
		param.add(contents);
		param.add(price);
		
		return jdbc.update(sql, param);
	}
	
	public int updateTitle(String title, String userId, int boardNo) {
		/*
		 * 글 수정
		 * 제목
		 */
		
		String sql = "UPDATE board SET title = ? WHERE seller_id = ? AND board_no = ?";

		List<Object> param = new ArrayList<>();
		param.add(title);
		param.add(userId);
		param.add(boardNo);
		
		return jdbc.update(sql, param);
	}
	
	public int updateContents(String contents, String userId, int boardNo) {
		/*
		 * 글 수정
		 * 내용
		 */
		
		String sql = "UPDATE board SET contents = ? WHERE seller_id = ? AND board_no = ?";

		List<Object> param = new ArrayList<>();
		param.add(contents);
		param.add(userId);
		param.add(boardNo);
		
		return jdbc.update(sql, param);
	}
	
	public int updateCategory(int ctNo, String userId, int boardNo) {
		/*
		 * 글 수정
		 * 카테고리
		 */
		
		String sql = "UPDATE board SET ct_no = ? WHERE seller_id = ? AND board_no = ?";

		List<Object> param = new ArrayList<>();
		param.add(ctNo);
		param.add(userId);
		param.add(boardNo);
		
		return jdbc.update(sql, param);
	}
	
	public int updatePrice(int price, String userId, int boardNo) {
		/*
		 * 글 수정
		 * 가격
		 */
		
		String sql = "UPDATE board SET price = ? WHERE seller_id = ? AND board_no = ?";

		List<Object> param = new ArrayList<>();
		param.add(price);
		param.add(userId);
		param.add(boardNo);
		
		return jdbc.update(sql, param);
	}
	
	public int updateSellState(int sellState, String userId, int boardNo) {
		/*
		 * 글 수정
		 * 판매상태
		 */
		
		String sql = "UPDATE board SET sell_gb = ? WHERE seller_id = ? AND board_no = ?";

		List<Object> param = new ArrayList<>();
		param.add(sellState);
		param.add(userId);
		param.add(boardNo);
		
		return jdbc.update(sql, param);
	}
	
	public int updateBuyerId(int buyerId, String userId, int boardNo) {
		/*
		 * 글 수정
		 * 구매자이메일
		 */
		
		String sql = "UPDATE board SET buyer_id = ? WHERE seller_id = ? AND board_no = ?";

		List<Object> param = new ArrayList<>();
		param.add(buyerId);
		param.add(userId);
		param.add(boardNo);
		
		return jdbc.update(sql, param);
	}
	
	public int deleteBoard(String userId, int boardNo) {
		/*
		 * 글 삭제
		 */
		
		String sql = "DELETE FROM board WHERE seller_id = ? AND board_no = ?";

		List<Object> param = new ArrayList<>();
		param.add(userId);
		param.add(boardNo);
		
		return jdbc.update(sql, param);
	}
	
	public int deleteBoard(int boardNo) {
		/*
		 * 글 삭제 (관리자용)
		 */
		
		String sql = "DELETE FROM board WHERE board_no = ?";

		List<Object> param = new ArrayList<>();
		param.add(boardNo);
		
		return jdbc.update(sql, param);
	}

}
