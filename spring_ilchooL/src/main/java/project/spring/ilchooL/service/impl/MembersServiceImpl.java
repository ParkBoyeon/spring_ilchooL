package project.spring.ilchooL.service.impl;


import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import project.spring.ilchooL.model.Members;


/** 회원 데이터 관리 기능을 제공하기 위한 Service 계층에 대한 구현체 */
@Slf4j
@Service
public class MembersServiceImpl implements project.spring.ilchooL.service.MembersService {

    /** MyBatis */
    // --> import org.apache.ibatis.session.SqlSession
    @Autowired
    SqlSession sqlSession;
    
    


    /**
     * 회원 데이터 상세 조회
     * @param input 조회할 데이터의 일련번호(PK)를 담고 있는 Beans
     * @return 조회된 데이터가 저장된 Beans
     * @throws Exception
     */
    @Override
    public Members getMembersItem(Members input) throws Exception {
        Members result = null;

        try {
            result = sqlSession.selectOne("MembersMapper.selectMember", input);

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

    /**
     * 회원 데이터 목록 조회
     * @param input 검색조건을 담고 있는 Beans
     * @return 조회 결과에 대한 컬렉션
     * @throws Exception
     */
    @Override
    public List<Members> getMembersList(Members input) throws Exception {
        List<Members> result = null;

        try {
            result = sqlSession.selectList("MembersMapper.selectMembers", input);
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

    /**
     * 회원 데이터가 저장되어 있는 갯수 조회
     * @param input 검색조건을 담고 있는 Beans
     * @return int
     * @throws Exception
     */
    @Override
    public int getMembersCount(Members input) throws Exception {
        int result = 0;

        try {
            result = sqlSession.selectOne("MembersMapper.selectCountAll", input);
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            throw new Exception("데이터 조회에 실패했습니다.");
        }

        return result;
    }

    /**
     * 회원가입
     * @param input 저장할 정보를 담고 있는 Beans
     * @return int
     * @throws Exception
     */
    @Override
    public int addMembers(Members input) throws Exception {
        int result = 0;

        // 중복검사 기능을 먼저 호출한다. --> 예외가 발생할 경우 이 메서드가 정의하는 throws문에 의해 컨트롤러로 예외가 전달된다.
        this.idUniqueCheck(input);
        

        try {
            result = sqlSession.insert("MembersMapper.insertMember", input);

            if (result == 0) {
                throw new NullPointerException("result=0");
            }
        } catch (NullPointerException e) {
            log.error(e.getLocalizedMessage());
            throw new Exception("회원가입이 완료되지 않았습니다. 다시 수행해 주세요.");
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            throw new Exception("회원가입에 실패했습니다.");
        }

        return result;
    }

    /**
     * 회원 (마이페이지) 수정하기
     * @param input 수정할 정보를 담고 있는 Beans
     * @return int
     * @throws Exception
     */
    @Override
    public int editMembers(Members input) throws Exception {
        int result = 0;

        try {
            result = sqlSession.update("MembersMapper.updateMember", input);
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
    
    /**
     * 회원 (마이페이지) 수정하기
     * @param input 수정할 정보를 담고 있는 Beans
     * @return int
     * @throws Exception
     */
    @Override
    public int editMembersPw(Members input) throws Exception {
        int result = 0;

        try {
            result = sqlSession.update("MembersMapper.updateMemberPw", input);
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
    

    /**
     * 로그인
     * @param input
     * @throws Exception
     */
    @Override
    public Members login(Members input) throws Exception {
    	Members result = null;
    	
    	try {
    		result = sqlSession.selectOne("MembersMapper.login", input);
    		
    		if (result == null) {
    			throw new NullPointerException("result=null");
    		}
    		
    		// 조회에 성공하면 result에 저장되어 있는 PK를 활용하여 로그인 시간을 갱신한다.
    		sqlSession.update("MembersMapper.updateLoginDate", result);
    	} catch (NullPointerException e) {
    		log.error(e.getLocalizedMessage());
    		throw new Exception("아이디나 비밀번호가 잘못되었습니다.");
    	} catch (Exception e) {
    		log.error(e.getLocalizedMessage());
    		throw new Exception("데이터 조회에 실패했습니다.");
    	}
    	
    	return result;
    }
    
    /**
     * 회원 데이터 삭제하기
     * @param input 삭제할 데이터의 일련번호(PK)를 담고 있는 Beans
     * @return int
     * @throws Exception
     */
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

    /**
     * 아이디 중복검사
     * @param input
     * @throws Exception
     */
    @Override
    public void idUniqueCheck(Members input) throws Exception {
        int result = 0;

        try {
            result = sqlSession.selectOne("MembersMapper.idUniqueCheck", input);
            if (result > 0) {
                throw new NullPointerException("result=" + result);
            }
        } catch (NullPointerException e) {
            log.error(e.getLocalizedMessage());
            throw new Exception("이미 사용중인 아이디 입니다.");
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            throw new Exception("아이디 중복검사에 실패했습니다.");
        }
    }
    
    /**
     * 이메일 중복검사
     * @param input
     * @throws Exception
     */
    @Override
    public void emailUniqueCheck(Members input) throws Exception {
        int result = 0;

        try {
            result = sqlSession.selectOne("MembersMapper.emailUniqueCheck", input);
            if (result > 0) {
                throw new NullPointerException("result=" + result);
            }
        } catch (NullPointerException e) {
            log.error(e.getLocalizedMessage());
            throw new Exception("이미 사용중인 이메일 입니다.");
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            throw new Exception("이메일 중복검사에 실패했습니다.");
        }
    }

    /**
     * 아이디 찾기
     */
    @Override
	public Members findId(Members input) throws Exception {
	    Members result = null;
	    try {
	      result = (Members)sqlSession.selectOne("MembersMapper.findId", input);
	      if (result == null)
	        throw new NullPointerException("result=null"); 
	    } catch (NullPointerException e) {
	      log.error(e.getLocalizedMessage());
	      throw new Exception("이름 또는 이메일이 잘못되었습니다.");
	    } catch (Exception e) {
	      log.error(e.getLocalizedMessage());
	      throw new Exception("데이터 조회에 실패했습니다.");
	    } 
	    return result;
	  }
    
    /**
     * 비밀번호 찾기
     */
    @Override
    public Members findPw(Members input) throws Exception {
    	Members result = null;
        try {
          result = (Members)sqlSession.selectOne("MembersMapper.findPw", input);
          if (result == null)
            throw new NullPointerException("result=null"); 
        } catch (NullPointerException e) {
          log.error(e.getLocalizedMessage());
          throw new Exception("입력하신 정보가 잘못되었습니다.");
        } catch (Exception e) {
          log.error(e.getLocalizedMessage());
          throw new Exception("데이터 조회에 실패했습니다.");
        } 
        return result;
      }
    
    /**
     * 회원 탈퇴(유지)
     */
    public void OutMembers(Members input) throws Exception {
        try {
          sqlSession.selectOne("MembersMapper.selectMember", input);
          sqlSession.update("MembersMapper.userOut", input);

        } catch (NullPointerException e) {
          log.error(e.getLocalizedMessage());
          throw new Exception("삭제된 데이터가 없습니다.");
        } catch (Exception e) {
          log.error(e.getLocalizedMessage());
          throw new Exception("데이터 삭제에 실패했습니다.");
        } 
    }

}


