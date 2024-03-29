package com.chendehe.controller;

import com.chendehe.service.UserService;
import com.chendehe.utils.ResultUtils;
import com.chendehe.vo.Page;
import com.chendehe.vo.UserVO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

/**
 * UserController.
 *
 * @author CDH
 * @since 2019/7/27 16:10
 */
@RestController
@RequestMapping("/users")
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
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
        return ResultUtils.success(userService.listUserByPage(page), HttpStatus.OK);
    }

    /**
     * 查找详情. 成功返回200.
     */
    @GetMapping("/{id}")
    public ResponseEntity getUserById(@PathVariable String id) {
        LOGGER.info("[UserController] id is:{}", id);
        return ResultUtils.success(userService.getUserById(id), HttpStatus.OK);
    }

    /**
     * 新建. 成功返回201.
     */
    @PostMapping
    public ResponseEntity save(@RequestBody @Valid UserVO userVo) {
        LOGGER.info("[UserController] user is:{}", userVo);
        return ResultUtils.success(userService.save(userVo), HttpStatus.CREATED);
    }

    /**
     * 更新. 成功返回201.
     */
    @PutMapping("/{id}")
    public ResponseEntity update(@RequestBody @Valid UserVO userVo, @PathVariable String id) {
        LOGGER.info("[UserController] user is:{}, id is:{}", userVo, id);
        userVo.setId(id);
        return ResultUtils.success(userService.update(userVo), HttpStatus.CREATED);
    }

    /**
     * 删除. 成功返回204.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity remove(@PathVariable String id) {
        LOGGER.info("[UserController] id is:{}", id);
        userService.remove(id);
        return ResultUtils.success(HttpStatus.NO_CONTENT);
    }

    /**
     * Excel上传. 成功返回201.
     */
    @PostMapping("/upLoad")
    public ResponseEntity upLoad(@RequestParam("file") MultipartFile file) {
        LOGGER.info("[UserController] file path:{}", file.isEmpty());

        userService.upload(file);

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
        userService.downLoad(id, path);
        ObjectNode node = new ObjectMapper().createObjectNode();
        node.put("status", "success");
        return ResultUtils.success(node.toString(), HttpStatus.NO_CONTENT);
    }

}