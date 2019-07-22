package com.company.service.impl;

import com.company.common.Gender;
import com.company.dao.UserDao;
import com.company.exception.ErrorCode;
import com.company.exception.ValidationException;
import com.company.po.UserPo;
import com.company.service.UserService;
import com.company.util.DataCheck;
import com.company.util.IdGenerator;
import com.company.vo.Page;
import com.company.vo.PageResult;
import com.company.vo.UserVo;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UserServiceImpl implements UserService {

  private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

  private UserDao userDao;

  @Autowired
  public UserServiceImpl(UserDao userDao) {
    this.userDao = userDao;
  }

  @Override
  public PageResult<UserVo> findAll(Page page) {

    PageResult<UserVo> result = new PageResult<>();
    List<UserPo> userList = userDao.findAll(page);
    List<UserVo> userVoList = new ArrayList<>();

    for (UserPo user : userList) {
      userVoList.add(convertEntityToVo(user));
    }
    result.setList(userVoList);
    result.setTotalNum(userDao.totalNum());
    result.setPageSize(page.getPageSize());
    result.setCurrentPage(page.getCurrentPage());
    return result;
  }

  @Override
  public UserVo findOne(String id) {
    DataCheck.checkTrimStrEmpty(id, ErrorCode.PARAM_EMPTY, "id");
    return convertEntityToVo(userDao.findOne(id)
        .orElseThrow(() -> new ValidationException("object.not.exist", id)));
  }

  @Override
  public UserVo save(UserVo vo) {
    vo.setId(IdGenerator.get());

    checkInputData(vo);

    UserPo entity = convertVoToEntitySave(vo);
    userDao.save(entity);

    return vo;
  }

  @Override
  public UserVo update(UserVo vo) {

    checkInputData(vo);

    UserPo user = convertVoToEntityUpdate(vo);

    userDao.update(user);
    return vo;
  }

  @Override
  public void delete(String id) {
//    DataCheck.checkTrimStrEmpty(id, ErrorCode.PARAM_EMPTY, "id");
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
  private UserVo convertEntityToVo(UserPo user) {
    UserVo userVo = new UserVo();
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
   * @param vo UserVo
   * @return UserPo
   */
  private UserPo convertVoToEntitySave(UserVo vo) {
    UserPo user = new UserPo();

    convertVoToEntity(vo, user);

    user.setCreateTime(LocalDateTime.now());
    return user;
  }

  /**
   * vo 转为更新后的 entity.
   *
   * @param vo UserVo
   * @return UserPo
   */
  private UserPo convertVoToEntityUpdate(UserVo vo) {
    UserPo user = new UserPo();

    convertVoToEntity(vo, user);

    user.setUpdateTime(LocalDateTime.now());
    return user;
  }

  private void convertVoToEntity(UserVo vo, UserPo user) {
    user.setId(vo.getId());
    user.setName(vo.getName());
    user.setGender(vo.getGender());
    user.setBirthday(vo.getBirthday());
    user.setAddress(vo.getAddress());
  }

  private void checkInputData(UserVo vo) {
    DataCheck.checkTrimStrEmpty(vo.getAddress(), ErrorCode.PARAM_EMPTY, "address");
    DataCheck.checkTrimStrEmpty(vo.getId(), ErrorCode.PARAM_EMPTY, "id");
    DataCheck.checkTrimStrEmpty(vo.getName(), ErrorCode.PARAM_EMPTY, "name");
    DataCheck.checkNull(vo.getBirthday(), ErrorCode.PARAM_EMPTY, "birthday");
    DataCheck.checkNull(vo.getGender(), ErrorCode.PARAM_EMPTY, "gender");
    DataCheck.checkEnum(Gender.class, vo.getGender(), ErrorCode.PARAM_TYPE_ERROR, "gender");
  }

}
