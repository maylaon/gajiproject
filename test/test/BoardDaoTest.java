package test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import util.JDBCUtil;

public class BoardDaoTest {

	private JDBCUtil jdbc = JDBCUtil.getInstance();

	// 홈화면 & 전체 조회(페이징)
	
	public void selectBoardListTest() {
		int pageNo = 1;

		String sql = "SELECT aa.*" + 
				" FROM (SELECT ROWNUM rn, a.*" + 
				"        FROM( SELECT * " + 
				"                FROM board_select) a) aa" + 
				" WHERE rn BETWEEN (:pageNo - 1) * 10 AND :pageNo * 10";
		
		List<Object> param = new ArrayList();
		param.add(pageNo);
		param.add(pageNo);

		jdbc.selectList(sql, param);

	}

	//
	
	  
	  public void selectCategorySearchListTest() { 
		  int ctNo = 1; 
		  String sql = "SELECT b.board_no, b.title, b.contents, b.user_name, b.price, b.status, b.jjim_cnt, b.chat_cnt, b.region_name"
				  		+ " FROM board_select b" 
				  		+ " WHERE ct_no = ?";
	  
		  List<Object> param = new ArrayList(); 
		  param.add(ctNo);
	  
		  jdbc.selectList(sql, param);
	  
	  }
	 

	
		//키워드 검색 
	  public void selectKeyWordSearchListTest() { 
		  String keyWord = "스크래쳐";
		  String sql = "SELECT b.board_no, b.title, b.contents, b.user_name, b.price, b.status, b.jjim_cnt, b.chat_cnt, b.region_name"
				  		+ " FROM board_select b" 
				  		+ " WHERE b.title LIKE '%' || ? ||'%' OR b.contents LIKE '%' || ? ||'%'"; 
		  
		  List<Object> param = new ArrayList(); 
		  param.add(keyWord); 
		  param.add(keyWord);
		  
		  jdbc.selectList(sql, param); 
	  }
		  
	  	//판매내역
	  public void selectSalesListTest() { 
		  String userId = "maylaon"; 
		  String sql = "SELECT b.board_no, b.title, b.contents, b.user_name, b.price, b.status, b.jjim_cnt, b.chat_cnt, b.region_name"
				  		+ " FROM board_select b" + " WHERE b.seller_id = ?"; 
		  List<Object> param = new ArrayList(); 
		  param.add(userId);
		  
		  jdbc.selectList(sql, param);
		  
		  }
		  
		  
		  //구매내역		  
	  public void selectPurchaseListTest() { 
		  String userId = "maylaon";
		  String sql = "SELECT b.board_no, b.title, b.contents, b.user_name, b.price, b.status, b.jjim_cnt, b.chat_cnt, b.region_name"
				  		+ " FROM board_select b" 
				  		+ " WHERE b.buyer_id = ?"; 
		  List<Object> param = new ArrayList(); 
		  param.add(userId);
		  
		  jdbc.selectList(sql, param);
		  
		  }
		  
		  //나의 찜목록
	  public void selectWishListTest() { 
		  String userId = "maylaon"; 
		  String sql1 = "SELECT board_no FROM jjim WHERE jjim_id = ?"; 
		  List<Object> param = new ArrayList(); 
		  param.add(userId); 
		  List<Map<String, Object>> result = new ArrayList(); 
		  result = jdbc.selectList(sql1, param);
		 
		  List<Map<String, Object>> jjim_result = new ArrayList(); 
		  List<Object> p_boardNo = new ArrayList();
		  for(int i = 0; i < result.size(); i++) {
			  p_boardNo = new ArrayList();
			  p_boardNo.add(result.get(i).get("BOARD_NO"));
			  String sql = "SELECT b.board_no, b.title, b.contents, b.user_name, b.price, b.status, b.jjim_cnt, b.chat_cnt, b.region_name"
					  		+ " FROM board_select b" 
					  		+ " WHERE b.board_no = ?"; 
			  jjim_result = jdbc.selectList(sql, p_boardNo); 
			  System.out.println(jjim_result.get(0).get("TITLE"));
			  }
	  	}
	  
	  	//게시글 번호로 글 조회
	  @Test
	  public void selectBoardViewTest() { 
		  //글번호 / 판매자이메일 / 카테고리번호 / 제목 / 내용 / 닉네임 / 가격  / 작성일 / 판매상태 / 지역 / 관심수 / 채팅수
		  
		  int boardNo = 1;
		  
		  String sql = "SELECT b.board_no, b.title, b.contents, b.user_name, b.price, b.status, b.jjim_cnt, b.chat_cnt, b.region_name"
			  		+ " FROM board_select b" 
			  		+ " WHERE b.board_no = ?"; 
		  
		  List<Object> param = new ArrayList(); 
		  param.add(boardNo); 
		  jdbc.selectList(sql, param); 
		  		  
	  }
	  
	  
	  
	  
	  
	  
		 
	 

}