package cn.itcast.dao;

import cn.itcast.domain.Member;
import org.apache.ibatis.annotations.Select;

public interface MemberDao {

    @Select("select *from member where id = #{id} ")
    public Member findOne(String id);
}
