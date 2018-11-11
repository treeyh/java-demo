/*
 *
 *  * ProjectName: springboot-parent
 *  * Author: tree.yu
 *  * Description:
 *  * Version: 1.0
 *  * Date: 18-5-9 上午9:39
 *  * LastModified: 18-5-9 上午9:39
 *
 */

package com.ejyi.demo.springboot.server.web.controller;

import com.ejyi.demo.springboot.server.constants.AppConstants;
import com.ejyi.demo.springboot.server.exception.Validation;
import com.ejyi.demo.springboot.server.facade.DemoFacade;
import com.ejyi.demo.springboot.server.model.DemoModel;
import com.ejyi.demo.springboot.server.model.IpModel;
import com.ejyi.demo.springboot.server.model.enums.ResultEnum;
import com.ejyi.demo.springboot.server.model.result.CallResult;
import com.ejyi.demo.springboot.server.model.result.ReturnPageResult;
import com.ejyi.demo.springboot.server.model.result.ReturnResult;
import com.ejyi.demo.springboot.server.service.DemoService;
import com.ejyi.demo.springboot.server.utils.LogModel;
import com.ejyi.demo.springboot.server.context.HttpContext;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController extends BaseController implements DemoFacade {

    @Autowired
    private DemoService demoService;

    @Override
    public ReturnResult<DemoModel> createDemo(@RequestBody DemoModel demoModel){

        LogModel lm = LogModel.newLogModel("DemoController.createDemo")
                .addMetaDataTraceId(HttpContext.getTraceId())
                .addMetaData("method", "POST")
                .addMetaData("demoModel", demoModel);

        logger.info(lm.toJson(false));

        Validation.newValidation()
                .addError((null == demoModel), ResultEnum.PARAM_ERROR_NAME, "DemoModel")
                .addError((StringUtils.isEmpty(demoModel.getCode())), ResultEnum.PARAM_ERROR_NAME, "code")
                .isValidThrowException();

        CallResult<DemoModel> callResult = demoService.createDemo(demoModel);
        lm.addMetaData("callResult", callResult);

        if(!callResult.isSuccess()){
            logger.info(lm.toJson());

            return new ReturnResult<DemoModel>(callResult.getResultEnum());
        }

        ReturnResult<DemoModel> returnResult = new ReturnResult<DemoModel>(
                ResultEnum.SUCCESS, callResult.getBusinessResult());

        lm.addMetaDataResult(returnResult);
        logger.info(lm.toJson());

        return returnResult;
    }

    @Override
    public ReturnResult<DemoModel> queryById(@PathVariable("id") Long id){
        LogModel lm = LogModel.newLogModel("DemoController.queryById")
                                .addMetaDataTraceId(HttpContext.getTraceId())
                                .addMetaData("method", "GET")
                                .addMetaData("id", id);
        logger.info(lm.toJson(false));

        Validation.newValidation()
                .addError((null == id), ResultEnum.PARAM_ERROR_NAME, "id")
                .isValidThrowException();

        CallResult<DemoModel> callResult = demoService.queryById(id);
        lm.addMetaData("callResult", callResult);

        if(!callResult.isSuccess()){
            logger.info(lm.toJson());

            return new ReturnResult<DemoModel>(callResult.getResultEnum());
        }

        ReturnResult<DemoModel> returnResult = new ReturnResult<DemoModel>(
                ResultEnum.SUCCESS, callResult.getBusinessResult());

        lm.addMetaDataResult(returnResult);
        logger.info(lm.toJson());

        return returnResult;
    }


    @Override
    public ReturnResult deleteById(@PathVariable("id") Long id) {
        LogModel lm = LogModel.newLogModel("DemoController.deleteById")
                            .addMetaDataTraceId(HttpContext.getTraceId())
                            .addMetaData("method", "GET")
                            .addMetaData("id", id);

        logger.info(lm.toJson(false));


        Validation.newValidation()
                .addError((null == id), ResultEnum.PARAM_ERROR_NAME, "id")
                .isValidThrowException();

        CallResult<Integer> callResult = demoService.deleteById(id);
        lm.addMetaData("callResult", callResult);

        if(!callResult.isSuccess()){
            logger.info(lm.toJson());
            return new ReturnResult<DemoModel>(callResult.getResultEnum());
        }

        ReturnResult returnResult = new ReturnResult(
                ResultEnum.SUCCESS);

        lm.addMetaDataResult(returnResult);
        logger.info(lm.toJson());

        return returnResult;
    }

    @Override
    public ReturnResult updateById(@PathVariable("id") Long id, @RequestBody DemoModel demoModel) {
        LogModel lm = LogModel.newLogModel("DemoController.updateById")
                .addMetaDataTraceId(HttpContext.getTraceId())
                .addMetaData("method", "PUT")
                .addMetaData("id", id)
                .addMetaData("demoModel", demoModel);

        logger.info(lm.toJson(false));


        Validation.newValidation()
                .addError((null == id), ResultEnum.PARAM_ERROR_NAME, "id")
                .addError((null == demoModel), ResultEnum.PARAM_ERROR_NAME, "demoModel")
                .addError((StringUtils.isEmpty(demoModel.getCode())), ResultEnum.PARAM_ERROR_NAME, "code")
                .isValidThrowException();

        CallResult<Integer> callResult = demoService.updateById(id, demoModel);
        lm.addMetaData("callResult", callResult);

        if(!callResult.isSuccess()){
            logger.info(lm.toJson());
            return new ReturnResult<DemoModel>(callResult.getResultEnum());
        }

        ReturnResult returnResult = new ReturnResult(
                ResultEnum.SUCCESS);

        lm.addMetaDataResult(returnResult);
        logger.info(lm.toJson());

        return returnResult;
    }


    @Override
    public ReturnResult<ReturnPageResult<DemoModel>> queryByPage(@RequestParam(name = "code", required = false) String code,
                                                                 @RequestParam(name = "status", required = false) Integer status,
                                                                 @RequestParam("pageNum") Long pageNum,
                                                                 @RequestParam("pageSize") Long pageSize) {
        LogModel lm = LogModel.newLogModel("DemoController.queryByPage")
                .addMetaDataTraceId(HttpContext.getTraceId())
                .addMetaData("method", "GET")
                .addMetaData("code", code)
                .addMetaData("status", status)
                .addMetaData("pageNum", pageNum)
                .addMetaData("pageSize", pageSize);

        logger.info(lm.toJson(false));


        Validation.newValidation()
                    .addError((null == pageNum || 0 >= pageNum) , ResultEnum.PARAM_ERROR_NAME_MSG, "pageNum", "正整数")
                    .addError((null == pageSize || 0 >= pageSize || AppConstants.MAX_PAGE_SIZE < pageSize),
                                ResultEnum.PARAM_RANGE_ERROR, "pageSize", "1", AppConstants.MAX_PAGE_SIZE.toString())
                    .isValidThrowException();

        CallResult<ReturnPageResult<DemoModel>> callResult = demoService.queryByPage(code, status, pageNum, pageSize);

        if(!callResult.isSuccess()){
            logger.info(lm.toJson());
            return new ReturnResult<>(callResult.getResultEnum());
        }

        ReturnResult<ReturnPageResult<DemoModel>> returnResult = new ReturnResult<>(
                callResult.getResultEnum(), callResult.getBusinessResult());

        lm.addMetaDataResult(returnResult);
        logger.info(lm.toJson());

        return returnResult;
    }

    @Override
    public ReturnResult<IpModel> queryByIp(@RequestParam("ip") String ip) {

        LogModel lm = LogModel.newLogModel("DemoController.queryByIp")
                .addMetaDataTraceId(HttpContext.getTraceId())
                .addMetaData("method", "GET")
                .addMetaData("ip", ip);

        logger.info(lm.toJson(false));


        Validation.newValidation()
                    .addError(StringUtils.isEmpty(ip), ResultEnum.PARAM_ERROR_NAME, "ip")
                    .isValidThrowException();

        CallResult<IpModel> callResult = demoService.queryByIp(ip);

        if(!callResult.isSuccess()){
            logger.info(lm.toJson());
            return new ReturnResult<>(callResult.getResultEnum());
        }

        ReturnResult<IpModel> returnResult = new ReturnResult<>(callResult.getResultEnum(), callResult.getBusinessResult());
        lm.addMetaDataResult(returnResult);
        logger.info(lm.toJson());

        return returnResult;

    }
}