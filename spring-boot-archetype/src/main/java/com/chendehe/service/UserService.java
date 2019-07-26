package com.chendehe.service;

import com.chendehe.vo.Page;
import com.chendehe.vo.PageResult;
import com.chendehe.vo.UserVO;
import org.springframework.web.multipart.MultipartFile;

public interface UserService {

  /**
   * 查找列表.
   */
  PageResult<UserVO> listUserByPage(Page page);

  /**
   * 查找详情.
   */
  UserVO getUserById(String id);

  /**
   * 新建.
   */
  UserVO save(UserVO vo);

  /**
   * 更新.
   */
  UserVO update(UserVO vo);

  /**
   * 删除.
   */
  void remove(String id);

  /**
   * 删除.
   */
  void upload(MultipartFile file);

  /**
   * 删除.
   */
  void downLoad(String id, String path);

}
