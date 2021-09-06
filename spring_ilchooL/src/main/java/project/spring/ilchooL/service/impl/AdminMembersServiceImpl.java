package project.spring.ilchooL.service.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import project.spring.ilchooL.model.Members;
import project.spring.ilchooL.service.AdminMembersService;

@Slf4j
@Service

public class AdminMembersServiceImpl implements AdminMembersService {
	
	@Autowired
	SqlSession sqlSession;

	@Override
	public Members getMembersItem(Members input) throws Exception {
        Members result = null;

        try {
            result = sqlSession.selectOne("MembersMapper.adminSelectItem", input);

            if (result == null) {
                throw new NullPointerException("result=null");
            }
        } catch (NullPointerException e) {
            log.error(e.getLocalizedMessage());
            throw new Exception("조회된 데이터가 없습니다.");
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            throw new Exception("데이터 조회에 실패했습니다.");
        }

        return result;
	}

	@Override
	public List<Members> getMembersList(Members input) throws Exception {
        List<Members> result = null;

        try {
            result = sqlSession.selectList("MembersMapper.adminSelectList", input);

            if (result == null) {
                throw new NullPointerException("result=null");
            }
        } catch (NullPointerException e) {
            log.error(e.getLocalizedMessage());
            throw new Exception("조회된 데이터가 없습니다.");
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            throw new Exception("데이터 조회에 실패했습니다.");
        }

        return result;
	}

	@Override
	public int geMembersCount(Members input) throws Exception {
        int result = 0;
        
        try {
            result = sqlSession.selectOne("MembersMapper.adminSelectCountAll", input);
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            throw new Exception("데이터 조회에 실패했습니다.");
        }
        
        return result;
	}

	@Override
	public int editMembers(Members input) throws Exception {
        int result = 0;

        try {
            result = sqlSession.update("MembersMapper.adminUpdateItem", input);

            if (result == 0) {
                throw new NullPointerException("result=0");
            }
        } catch (NullPointerException e) {
            log.error(e.getLocalizedMessage());
            throw new Exception("수정된 데이터가 없습니다.");
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            throw new Exception("데이터 수정에 실패했습니다.");
        }

        return result;
	}

	@Override
	public int deleteMembers(Members input) throws Exception {
        int result = 0;

        try {
            result = sqlSession.delete("MembersMapper.deleteMembers", input);

            if (result == 0) {
                throw new NullPointerException("result=0");
            }
        } catch (NullPointerException e) {
            log.error(e.getLocalizedMessage());
            throw new Exception("삭제된 데이터가 없습니다.");
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            throw new Exception("데이터 삭제에 실패했습니다.");
        }

        return result;
	}

	@Override
	public int adminGenderCount(Members input) throws Exception {
		int result = 0;
		
		try {
			result = sqlSession.selectOne("MembersMapper.dashboardCF", input);
			
			if (result == 0) {
                throw new NullPointerException("result=0");
            }
        } catch (NullPointerException e) {
            log.error(e.getLocalizedMessage());
            throw new Exception("데이터가 없습니다.");
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            throw new Exception("데이터 조회를 실패했습니다.");
		}
		
		return result;
	}

	@Override
	public int adminaddr(Members input) throws Exception {
		int result = 0;
		
		try {
			result = sqlSession.selectOne("MembersMapper.dashboardaddr1", input);
			
			if (result == 0) {
                throw new NullPointerException("result=0");
            }
        } catch (NullPointerException e) {
            log.error(e.getLocalizedMessage());
            throw new Exception("데이터가 없습니다.");
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            throw new Exception("데이터 조회를 실패했습니다.");
			
		}
		
		return result;
	}





	@Override
	public int adminOld20() throws Exception {
		int result = 0;
		
		try {
	         result = sqlSession.selectOne("MembersMapper.dashboardOld20");
	        
	        } catch (NullPointerException e) {
	            log.error(e.getLocalizedMessage());
	            throw new Exception("조회된 데이터가 없습니다.");
	        } catch (Exception e) {
	            log.error(e.getLocalizedMessage());
	            throw new Exception("데이터 조회에 실패했습니다.");
	        }
		
		return result;
	}

	@Override
	public int adminOld25() throws Exception {
int result = 0;
		
		try {
	         result = sqlSession.selectOne("MembersMapper.dashboardOld25");
	         
	        } catch (NullPointerException e) {
	            log.error(e.getLocalizedMessage());
	            throw new Exception("조회된 데이터가 없습니다.");
	        } catch (Exception e) {
	            log.error(e.getLocalizedMessage());
	            throw new Exception("데이터 조회에 실패했습니다.");
	        }
		return result;
	}

	@Override
	public int adminOld30() throws Exception {
int result = 0;
		
		try {
	         result = sqlSession.selectOne("MembersMapper.dashboardOld30");
	        
	        } catch (NullPointerException e) {
	            log.error(e.getLocalizedMessage());
	            throw new Exception("조회된 데이터가 없습니다.");
	        } catch (Exception e) {
	            log.error(e.getLocalizedMessage());
	            throw new Exception("데이터 조회에 실패했습니다.");
	        }
		return result;
	}
	
}
