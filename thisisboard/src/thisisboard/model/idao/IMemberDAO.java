package thisisboard.model.idao;

import java.util.ArrayList;

import thisisboard.model.vo.MemberVo;

public interface IMemberDAO {
	public int deleteMember(String memberid);
	public ArrayList<MemberVo> getAllMembers();
	public int insertMember(MemberVo vo);
	public int updateMember(MemberVo vo);
	
}
