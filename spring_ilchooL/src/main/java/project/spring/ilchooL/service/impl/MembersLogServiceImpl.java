package project.spring.ilchooL.service.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import project.spring.ilchooL.model.MembersLog;
import project.spring.ilchooL.service.MembersLogService;

@Slf4j
@Service

public class MembersLogServiceImpl implements MembersLogService{

    /** MyBatis */
    // --> import org.apache.ibatis.session.SqlSession
    @Autowired
    SqlSession sqlSession;
	
	@Override
	public MembersLog getMembersLogItem(MembersLog input) throws Exception {
        MembersLog result = null;

        try {
            result = sqlSession.selectOne("MembersLogMapper.selectItem", input);

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
	public List<MembersLog> getMembersLogList(MembersLog input) throws Exception {
        List<MembersLog> result = null;

        try {
            result = sqlSession.selectList("MembersLogMapper.selectList", input);
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

}
