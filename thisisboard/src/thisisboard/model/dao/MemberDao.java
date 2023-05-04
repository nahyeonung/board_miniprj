package thisisboard.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import thisisboard.model.BoardDataSource;
import thisisboard.model.idao.IMemberDAO;
import thisisboard.model.vo.MemberVo;

public class MemberDao implements IMemberDAO{

	@Override
	public int deleteMember(String memberid) {
		int deletedRow = 0;
		String sql = "delel from users where userid=?";
		Connection con = null;
		try {
			con = BoardDataSource.getConnection();
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, memberid);
			deletedRow = stmt.executeUpdate();
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			BoardDataSource.closeConnection(con);
		}
		return deletedRow;
	}


	@Override
	public ArrayList<MemberVo> getAllMembers() {
		// TODO Auto-generated method stub
		ArrayList<MemberVo> memberList = new ArrayList<MemberVo>();
		String sql = "select * from users";
		Connection con = null;

		try {
			con = BoardDataSource.getConnection();
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				MemberVo member = new MemberVo();
				member.setUserid(rs.getString("userid"));
				member.setUsername(rs.getString("username"));
				member.setUserpassword(rs.getString("userpassword"));
				memberList.add(member);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			BoardDataSource.closeConnection(con);
		}
		return memberList;
	}


	@Override
	public int insertMember(MemberVo vo) {
		// TODO Auto-generated method stub
		int count = 0;
		String sql = "insert into users (userid, username, userpassowrd)" + 
				" values (?, ?, ?);";
		Connection con = null;
		try {
			con = BoardDataSource.getConnection();
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, vo.getUserid());
			stmt.setString(2, vo.getUsername());
			stmt.setString(3, vo.getUserpassword());
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			BoardDataSource.closeConnection(con);
		}
		return count;
	}

	@Override
	public int updateMember(MemberVo vo) {
		// TODO Auto-generated method stub
		int count=0;
		// userid를 입력하면 username과 userpassword를 바꿈
		String sql = "update users set username=?, userpassword where userid=?";
		Connection con = null;
		try {
			con = BoardDataSource.getConnection();
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, vo.getUsername());
			stmt.setString(2, vo.getUserpassword());
			stmt.setString(3, vo.getUserid());
			count = stmt.executeUpdate();
		} catch(SQLException e) {
			throw new RuntimeException(e);
		} finally {
			BoardDataSource.closeConnection(con);
		}
		return count;
	}

}
