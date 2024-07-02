package com.json.fastjson.analysis;

import lombok.ToString;

/**
  * 递归沉底状态
  * @author EinIce
  * @description TODO 检测递归是否沉底
  * @date 2022/7/8
  */
@ToString
public class SinkBottom {

    public enum LastStatus{
        out(),
        into(),
        init()
    }

    public SinkBottom() {
        this.lastStatus = LastStatus.init;
    }

    private LastStatus lastStatus;

    public void upIntoStatus() {
        lastStatus = LastStatus.into;
    }
    public void upOutStatus() {
        lastStatus = LastStatus.out;
    }

    public Boolean isIntoStatus() {
        return lastStatus == LastStatus.into;
    }
}