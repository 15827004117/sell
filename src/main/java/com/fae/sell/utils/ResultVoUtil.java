package com.fae.sell.utils;

import com.fae.sell.VO.ResultVO;
import lombok.Getter;

/**
 * 功能描述: ResultVo返回封装
 *
 * @作者 lj
 * @日期 2018/12/5 0005 21:14
 */
public class ResultVoUtil {

    public static ResultVO success(Object object) {
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(0);
        resultVO.setMsg("成功");
        resultVO.setData(object);
        return resultVO;
    }

    public static ResultVO success() {
        return success(null);
    }

    public static ResultVO error(Integer code, String msg) {
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(code);
        resultVO.setMsg(msg);
        return  resultVO;
    }

}
