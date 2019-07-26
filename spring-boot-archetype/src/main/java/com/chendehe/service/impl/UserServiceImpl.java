package com.chendehe.service.impl;

import com.chendehe.dao.UserDao;
import com.chendehe.exception.ValidationException;
import com.chendehe.po.UserPO;
import com.chendehe.service.UserService;
import com.chendehe.util.IdGenerator;
import com.chendehe.vo.Page;
import com.chendehe.vo.PageResult;
import com.chendehe.vo.UserVO;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UserServiceImpl implements UserService {

  private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

  private UserDao userDao;

  public UserServiceImpl(UserDao userDao) {
    this.userDao = userDao;
  }

  @Override
  public PageResult<UserVO> findAll(Page page) {

    PageResult<UserVO> result = new PageResult<>();
    List<UserPO> userList = userDao.findAll(page);
    List<UserVO> userVoList = new ArrayList<>();

    for (UserPO user : userList) {
      userVoList.add(convertEntityToVo(user));
    }
    result.setList(userVoList);
    result.setTotalNum(userDao.totalNum());
    result.setPageSize(page.getPageSize());
    result.setCurrentPage(page.getCurrentPage());
    return result;
  }

  @Override
  public UserVO findOne(String id) {
    return convertEntityToVo(userDao.findOne(id)
        .orElseThrow(() -> new ValidationException("object.not.exist", id)));
  }

  @Override
  public UserVO save(UserVO vo) {
    vo.setId(IdGenerator.get());

    UserPO entity = convertVoToEntitySave(vo);
    userDao.save(entity);

    return vo;
  }

  @Override
  public UserVO update(UserVO vo) {

    UserPO user = convertVoToEntityUpdate(vo);

    userDao.update(user);
    return vo;
  }

  @Override
  public void delete(String id) {
    userDao.delete(id);
  }

  @Override
  public void upload(MultipartFile file) {
    long start = System.currentTimeMillis();
    LOGGER.info("[UserServiceImpl] start:{}", start);

    long end = System.currentTimeMillis();
    LOGGER.info("[UserServiceImpl] Total end:{}", end - start);
  }

  @Override
  public void downLoad(String id, String path) {

    long start = System.currentTimeMillis();
    LOGGER.info("[UserServiceImpl] start:{}", start);


    long end = System.currentTimeMillis();
    LOGGER.info("[UserServiceImpl] Total end:{}", end - start);
  }

  /**
   * entity 转为 vo.
   *
   * @param user entity
   * @return vo
   */
  private UserVO convertEntityToVo(UserPO user) {
    UserVO userVo = new UserVO();
    userVo.setId(user.getId());
    userVo.setName(user.getName());
    userVo.setGender(user.getGender());
    userVo.setBirthday(user.getBirthday());
    userVo.setAddress(user.getAddress());
    return userVo;
  }

  /**
   * vo 转为 entity.
   *
   * @param vo UserVO
   * @return UserPO
   */
  private UserPO convertVoToEntitySave(UserVO vo) {
    UserPO user = new UserPO();

    convertVoToEntity(vo, user);

    user.setCreateTime(LocalDateTime.now());
    return user;
  }

  /**
   * vo 转为更新后的 entity.
   *
   * @param vo UserVO
   * @return UserPO
   */
  private UserPO convertVoToEntityUpdate(UserVO vo) {
    UserPO user = new UserPO();

    convertVoToEntity(vo, user);

    user.setUpdateTime(LocalDateTime.now());
    return user;
  }

  private void convertVoToEntity(UserVO vo, UserPO user) {
    user.setId(vo.getId());
    user.setName(vo.getName());
    user.setGender(vo.getGender());
    user.setBirthday(vo.getBirthday());
    user.setAddress(vo.getAddress());
  }

}
