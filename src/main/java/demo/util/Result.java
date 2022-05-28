package demo.util;

import lombok.Data;

@Data
public class Result {
    //描述统一格式中的数据
    private Object data;
    //描述统一格式中的编码
    private Integer code;
    //描述统一格式中的消息
    private String msg;

    public Result() {
    }

    public Result(Integer code, Object data) {
        this.data = data;
        this.code = code;
    }

    public Result(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Result(Integer code, Object data, String msg) {
        this.data = data;
        this.code = code;
        this.msg = msg;
    }
}
