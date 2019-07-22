package com.company.service;

import com.company.vo.Page;
import com.company.vo.PageResult;
import com.company.vo.UserVo;
import java.io.IOException;
import org.springframework.web.multipart.MultipartFile;

public interface UserService {

  /**
   * 查找列表.
   */
  PageResult<UserVo> findAll(Page page);

  /**
   * 查找详情.
   */
  UserVo findOne(String id);

  /**
   * 新建.
   */
  UserVo save(UserVo vo);

  /**
   * 更新.
   */
  UserVo update(UserVo vo);

  /**
   * 删除.
   */
  void delete(String id);

  /**
   * 删除.
   */
  void upload(MultipartFile file) throws IOException;

  /**
   * 删除.
   */
  void downLoad(String id, String path);

}
