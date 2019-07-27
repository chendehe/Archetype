package com.chendehe.service;

import org.springframework.web.multipart.MultipartFile;

import com.chendehe.vo.Page;
import com.chendehe.vo.PageResult;
import com.chendehe.vo.UserVO;

/**
 * 服务层接口.
 * 
 * @author CDH
 * @since 2019/7/27 16:10
 */
public interface UserService {

    /**
     * 查找列表.
     * @param page 分页
     * @return 用户列表
     */
    PageResult<UserVO> listUserByPage(Page page);

    /**
     * 查找详情.
     * @param id 用户 id
     * @return 用户
     */
    UserVO getUserById(String id);

    /**
     * 新建.
     * @param vo 用户视图
     * @return 用户视图
     */
    UserVO save(UserVO vo);

    /**
     * 更新.
     * @param vo 用户视图
     * @return 用户视图
     */
    UserVO update(UserVO vo);

    /**
     * 删除.
     * @param id 用户 id
     */
    void remove(String id);

    /**
     * 上传.
     * @param file 文件
     */
    void upload(MultipartFile file);

    /**
     * 下载.
     * @param id 文件 id
     * @param path 文件路径
     */
    void downLoad(String id, String path);

}
