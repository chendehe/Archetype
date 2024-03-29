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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 服务层实现类.
 *
 * @author CDH
 * @since 2019/7/27 16:10
 */
@Service
public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserDao userDao;

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

        result.setTotalCount(count);

        List<UserPO> userList = userDao.listByPage(page);

        result.setList(userList.stream().map(this::convertEntityToVo).collect(Collectors.toList()));
        result.setPageSize(page.getPageSize());
        result.setCurrentPage(page.getCurrentPage());
        return result;
    }

    @Override
    public UserVO getUserById(String id) {
        return convertEntityToVo(
                userDao.getById(id).orElseThrow(() -> new ValidationException("object.not.exist", id)));
    }

    @Transactional(rollbackFor = Exception.class)
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
    public void remove(String id) {
        userDao.remove(id);
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
