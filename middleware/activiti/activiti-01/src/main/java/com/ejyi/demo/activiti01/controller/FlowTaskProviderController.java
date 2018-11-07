package com.ejyi.demo.activiti01.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 余海
 * @version 1.0
 * @description 描述
 * @create 2018-11-07 2:25 PM
 */
@RestController
@Api(value = "任务控制类")
@RequestMapping({"/flowTask"})
public class FlowTaskProviderController {

//    /***
//     * 查询待办任务
//     * @param queryWaitTaskReq
//     * @return
//     */
//
//    @PostMapping(value = "/queryWaitTask")
//    @ApiOperation(value = "查询待办任务", notes = "查询待办任务")
//    public String queryWaitTask() {
//
//        return "true";
//    }
//
//
//    /***
//     * 查询用户任务列表
//     * @return
//     */
//
//    @PostMapping(value = "/queryTask")
//    @ApiOperation(value = "查询任务", notes = "查询任务")
//    public String queryTask() {
//
//        return "true";
//    }
//
//
//    /***
//     * 审核任务
//     * @param completeTaskReq
//     * @return
//     */
//    @PostMapping(value = "/completeTask")
//    @ApiOperation(value = "审核任务", notes = "审核任务")
//    public String completeTask() {
//
//        return "true";
//    }
//
//    /****
//     * 删除任务
//     * @param deleteTaskReq
//     * @return
//     */
//
//    @PostMapping(value = "/deleteTask")
//    @ApiOperation(value = "删除任务", notes = "删除任务")
//    public String deleteTask() {
//        return "true";
//    }
//
//    /***
//     * 任务指派
//     * @return
//     */
//    @ApiOperation(value = "任务指派", notes = "任务指派")
//    @PostMapping(value = "/claimTask")
//    public String claimTask() {
//        return "true";
//    }
//
//
//    /****
//     * 查询任务历史记录
//     * @return
//     */
//    @ApiOperation(value = "查询任务历史记录", notes = "查询任务历史记录")
//    @PostMapping(value = "/queryTaskHistory")
//    public String queryTaskHistory() {
//        return "true";
//    }

}
