package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JDBCUtil {

	//싱글톤 패턴 : 인스턴스의 생성을 제한하여 하나의 인스턴스만 사용하는 디자인 패턴
	
	private JDBCUtil(){
		
	}
	
	//인스턴스를 보관할 변수
	private static JDBCUtil instance;
	
	//인스턴스를 빌려주는 메서드
	public static JDBCUtil getInstance(){
		if(instance == null){
			instance = new JDBCUtil();
		}
		return instance;
	}
	
	//데이터베이스 접속정보
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String user = "gaji";
	String password = "java";

	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	/*
	 * Map<String, Object> selectOne(String sql)
	 * Map<String, Object> selectOne(String sql, List<Object> param)
	 * List<Map<String, Object>> selectList(String sql)
	 * List<Map<String, Object>> selectList(String sql, List<Object> param)
	 * int update(String sql)
	 * int update(String sql, List<Object> param)
	 */
	
	
	public Map<String, Object> selectOne(String sql) {
		
		HashMap<String, Object> map = new HashMap<>();
		
		try {
			
			con = DriverManager.getConnection(url, user, password);
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();

			ResultSetMetaData md = rs.getMetaData(); // 메타데이터 : 데이터에 대한 데이터

			int columnCount = md.getColumnCount(); // 컬럼 수

			while (rs.next()) {
				for (int i = 1; i <= columnCount; i++) {
					String key = md.getColumnLabel(i);
					Object value = rs.getObject(i);
					map.put(key, value);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			if (rs != null) try { rs.close(); } catch(Exception e) {}
			if (ps != null) try { ps.close(); } catch(Exception e) {}
			if (con != null) try { con.close(); } catch(Exception e) {}
		}
		
		return map;
	}
	
	public Map<String, Object> selectOne(String sql, List<Object> param) {
		
		HashMap<String, Object> map = new HashMap<>();
		
		try {
			
			con = DriverManager.getConnection(url, user, password);
			ps = con.prepareStatement(sql);
			
			for (int i = 0; i < param.size(); i++) {
				ps.setObject(i+1, param.get(i));
			}

			rs = ps.executeQuery();

			ResultSetMetaData md = rs.getMetaData(); // 메타데이터 : 데이터에 대한 데이터

			int columnCount = md.getColumnCount(); // 컬럼 수

			while (rs.next()) {
				for (int i = 1; i <= columnCount; i++) {
					String key = md.getColumnLabel(i);
					Object value = rs.getObject(i);
					map.put(key, value);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			if (rs != null) try { rs.close(); } catch(Exception e) {}
			if (ps != null) try { ps.close(); } catch(Exception e) {}
			if (con != null) try { con.close(); } catch(Exception e) {}
		}
		
		return map;
	}
	
	public List<Map<String, Object>> selectList(String sql) {
		
		ArrayList<Map<String, Object>> list = new ArrayList<>();
		
		try {
			
			con = DriverManager.getConnection(url, user, password);
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			
			ResultSetMetaData md = rs.getMetaData(); // 메타데이터 : 데이터에 대한 데이터

			int columnCount = md.getColumnCount(); // 컬럼 수

			while (rs.next()) {
				HashMap<String, Object> map = new HashMap<>();
				for (int i = 1; i <= columnCount; i++) {
					String key = md.getColumnLabel(i);
					Object value = rs.getObject(i);
					map.put(key, value);
				}
				list.add(map);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			if (rs != null) try { rs.close(); } catch(Exception e) {}
			if (ps != null) try { ps.close(); } catch(Exception e) {}
			if (con != null) try { con.close(); } catch(Exception e) {}
		}
		
		return list;
	}
	
	public List<Map<String, Object>> selectList(String sql, List<Object> param) {
		
		ArrayList<Map<String, Object>> list = new ArrayList<>();
		
		try {
			
			con = DriverManager.getConnection(url, user, password);
			ps = con.prepareStatement(sql);
			
			for (int i = 0; i < param.size(); i++) {
				ps.setObject(i+1, param.get(i));
			}
			
			rs = ps.executeQuery();

			ResultSetMetaData md = rs.getMetaData(); // 메타데이터 : 데이터에 대한 데이터

			int columnCount = md.getColumnCount(); // 컬럼 수

			while (rs.next()) {
				HashMap<String, Object> map = new HashMap<>();
				for (int i = 1; i <= columnCount; i++) {
					String key = md.getColumnLabel(i);
					Object value = rs.getObject(i);
					map.put(key, value);
				}
				list.add(map);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			if (rs != null) try { rs.close(); } catch(Exception e) {}
			if (ps != null) try { ps.close(); } catch(Exception e) {}
			if (con != null) try { con.close(); } catch(Exception e) {}
		}
		
		return list;
	}
	
	public int update(String sql) {
		
		int succes = 0;
		
		try {
			
			con = DriverManager.getConnection(url, user, password);
			ps = con.prepareStatement(sql);
			succes = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			if (rs != null) try { rs.close(); } catch(Exception e) {}
			if (ps != null) try { ps.close(); } catch(Exception e) {}
			if (con != null) try { con.close(); } catch(Exception e) {}
		}
		
		return succes;
	}
	
	public int update(String sql, List<Object> param) {
		
		int succes = 0;
		
		try {
			
			con = DriverManager.getConnection(url, user, password);
			ps = con.prepareStatement(sql);
			
			for (int i = 0; i < param.size(); i++) {
				ps.setObject(i+1, param.get(i));
			}
			
			succes = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			if (rs != null) try { rs.close(); } catch(Exception e) {}
			if (ps != null) try { ps.close(); } catch(Exception e) {}
			if (con != null) try { con.close(); } catch(Exception e) {}
		}
		
		return succes;
	}
}
