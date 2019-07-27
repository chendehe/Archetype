package com.chendehe.service.impl;

import com.chendehe.dao.UserDao;
import com.chendehe.exception.ValidationException;
import com.chendehe.po.UserPO;
import com.chendehe.service.UserService;
import com.chendehe.utils.IdGenerator;
import com.chendehe.utils.TimeUtils;
import com.chendehe.vo.Page;
import com.chendehe.vo.PageResult;
import com.chendehe.vo.UserVO;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UserServiceImpl implements UserService {

  private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

  private UserDao userDao;

  public UserServiceImpl(UserDao userDao) {
    this.userDao = userDao;
  }

  @Override
  public PageResult<UserVO> listUserByPage(Page page) {

    PageResult<UserVO> result = new PageResult<>();

    int count = userDao.count();
    if (count < 1) {
      return result.defaultPage();
    }

    result.setTotalNum(count);

    List<UserPO> userList = userDao.listByPage(page);

    result.setList(userList.stream().map(this::convertEntityToVo).collect(Collectors.toList()));
    result.setPageSize(page.getPageSize());
    result.setCurrentPage(page.getCurrentPage());
    return result;
  }

  @Override
  public UserVO getUserById(String id) {
    return convertEntityToVo(userDao.getById(id)
        .orElseThrow(() -> new ValidationException("object.not.exist", id)));
  }

  @Transactional
  @Override
  public UserVO save(UserVO vo) {
    vo.setId(IdGenerator.get());

    UserPO entity = convertVoToEntitySave(vo);
    userDao.save(entity);

    // 批量插入验证
//    UserPO entity1 = convertVoToEntitySave(vo);
//    entity1.setId(IdGenerator.get());
//    UserPO entity2 = convertVoToEntitySave(vo);
//    entity2.setId(IdGenerator.get());
//    List<UserPO> list = new ArrayList<>();
//    list.add(entity1);
//    list.add(entity2);
//    userDao.saveBatch(list);

    return vo;
  }

  @Override
  public UserVO update(UserVO vo) {

    UserPO user = convertVoToEntityUpdate(vo);

    userDao.update(user);
    return vo;
  }

  @Override
  public void remove(String id) {
    userDao.remove(id);
    // 批量删除验证
//    Set<String> ids = new HashSet<>();
//    ids.add("25e4b39ec3f9413d80b8d1ba8f298a5e");
//    ids.add("2acc75b330d74c4a83dd5e4c3a292640");
//    userDao.removeByIds(ids);
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
    userVo.setBirthday(TimeUtils.parseString(user.getBirthday()));
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
    user.setBirthday(TimeUtils.parseLocalDateTime(vo.getBirthday()));
    user.setAddress(vo.getAddress());
  }

}
