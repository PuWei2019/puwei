package com.cy.store.mapper;

import com.cy.store.entity.Address;
import java.util.Date;
import java.util.List;


//@Mapper 不要每一个Mapper接口都添加注解  在启动类直接指定
public interface AddressMapper {
    /**
     * 插入用户地址
     * @param address 用户地址
     * @return 插入用户地址条数
     */
  Integer InsertAddress(Address address);

    /**
     * 查询用户地址条数
     * @param uid 用户 ID
     * @return 地址条数
     */
  Integer CountByUid(Integer uid);

    /**
     * 根据用户 ID 查询用户地址
     * @param uid 用户 ID
     * @return 用户所有地址
     */
  List<Address> findByUid(Integer uid);

  /**
   * 查询地址是否存在
   * @param aid 地址 ID
   * @return 收货地址
   */
  Address findByAid(Integer aid);


  /**
   * 根据用户 ID 把地址设置为非默认
   * @param uid 用户 ID
   * @return 受影响行数
   */
  Integer updateNonDefault(Integer uid);

  /**
   * 根据地址 ID 设置地址为默认
   * @param aid 地址 ID
   * @return 受影响行数
   */
  Integer updateDefaultByAid(Integer aid,String modifiedUser,Date modifiedTime);
  /**
   * 根据地址 AID 删除地址
   * @param aid 地址 ID
   * @return  删除条数
   */
  Integer deleteByAid(Integer aid);

  /**
   * 用户 uid 查询最后修改的地址数据
   * @param uid 用户 ID
   * @return 收货地址
   */
  Address findLastModified(Integer uid);
}
