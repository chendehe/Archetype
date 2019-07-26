package com.chendehe.controller;

import com.chendehe.service.UserService;
import com.chendehe.utils.ResultUtils;
import com.chendehe.vo.Page;
import com.chendehe.vo.UserVO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/users")
public final class UserController {

  private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

  private final UserService service;

  public UserController(UserService service) {
    this.service = service;
  }

  /**
   * 测试.
   */
  @GetMapping("/hello")
  public String hello() {
    return "Hello World";
  }

  /**
   * 查找列表. 成功返回200.
   */
  @GetMapping
  public ResponseEntity listUserByPage(@Valid Page page) {
    LOGGER.info("[UserController] id is:{}", page);
    return ResultUtils.success(service.listUserByPage(page), HttpStatus.OK);
  }

  /**
   * 查找详情. 成功返回200.
   */
  @GetMapping("/{id}")
  public ResponseEntity getUserById(@PathVariable String id) {
    LOGGER.info("[UserController] id is:{}", id);
    return ResultUtils.success(service.getUserById(id), HttpStatus.OK);
  }

  /**
   * 新建. 成功返回201.
   */
  @PostMapping
  public ResponseEntity save(@RequestBody @Valid UserVO userVo) {
    LOGGER.info("[UserController] user is:{}", userVo);
    return ResultUtils.success(service.save(userVo), HttpStatus.CREATED);
  }

  /**
   * 更新. 成功返回201.
   */
  @PutMapping("/{id}")
  public ResponseEntity update(@RequestBody @Valid UserVO userVo, @PathVariable String id) {
    LOGGER.info("[UserController] user is:{}, id is:{}", userVo, id);
    userVo.setId(id);
    return ResultUtils.success(service.update(userVo), HttpStatus.CREATED);
  }

  /**
   * 删除. 成功返回204.
   */
  @DeleteMapping("/{id}")
  public ResponseEntity remove(@PathVariable String id) {
    LOGGER.info("[UserController] id is:{}", id);
    service.remove(id);
    return ResultUtils.success(HttpStatus.NO_CONTENT);
  }

  /**
   * Excel上传. 成功返回201.
   */
  @PostMapping("/upLoad")
  public ResponseEntity upLoad(@RequestParam("file") MultipartFile file) {
    LOGGER.info("[UserController] file path:{}", file.isEmpty());

    service.upload(file);

    ObjectNode node = new ObjectMapper().createObjectNode();
    node.put("status", "success");
    return ResultUtils.success(node.toString(), HttpStatus.CREATED);

  }

  /**
   * Excel下载. 成功返回200.
   *
   * @param id   id
   * @param path 下载文件本地存放路径
   * @return 状态
   */
  @GetMapping("/downLoad")
  public ResponseEntity downLoad(@RequestParam String id, @RequestParam String path) {
    LOGGER.info("[UserController] id:{},{}", id, path);
    service.downLoad(id, path);
    ObjectNode node = new ObjectMapper().createObjectNode();
    node.put("status", "success");
    return ResultUtils.success(node.toString(), HttpStatus.NO_CONTENT);
  }

}