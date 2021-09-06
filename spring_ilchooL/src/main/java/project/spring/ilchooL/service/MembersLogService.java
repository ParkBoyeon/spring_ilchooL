package project.spring.ilchooL.service;

import java.util.List;
import project.spring.ilchooL.model.MembersLog;

public interface MembersLogService {

    /**
     * 회원 데이터 상세 조회
     * @param input 조회할 데이터의 일련번호(PK)를 담고 있는 Beans
     * @return 조회된 데이터가 저장된 Beans
     * @throws Exception
     */
    public MembersLog getMembersLogItem(MembersLog input) throws Exception;

    /**
     * 회원 데이터 목록 조회
     * @param input 검색조건과 페이지 구현 정보를 담고 있는 Beans
     * @return 조회 결과에 대한 컬렉션
     * @throws Exception
     */
    public List<MembersLog> getMembersLogList(MembersLog input) throws Exception;

}
