package com.srx.discussion.Mappers;

import com.srx.discussion.Entities.base.Pyq;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserToPyqMapper {
    /**
     * 插入一个朋友圈
     * @param pyq
     * @return
     */
    boolean insertPyq(Pyq pyq);

    //不提供更改朋友圈的功能

    /**
     * 这里由于并没有设置动态的主键id，所以对动态的删除主要是靠他的用户id和发布时间，
     * 理论上如果没有受到攻击的话，同一个用户不可能出现在同一时间发布多条动态，所以可以确定动态的唯一性
     * 依旧使用软删除
     * @param pyq
     * @return
     */
    boolean deletePyq(Pyq pyq);

    /**
     * 通过用户id对一个用户的所有动态进行查询，返回一个list
     * @param userId
     * @return
     */
    List<Pyq> queryPyqListById(@Param(value = "userId") int userId);


}
