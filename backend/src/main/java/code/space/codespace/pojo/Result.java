package code.space.codespace.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Result {
    private Integer code; // 响应码
    private String msg; // 响应信息
    private Object data; // 响应数据
    public static Result success() { // 成功响应
        return new Result(1, "success", null);
    }
    public static Result success(Object data) { // 成功响应
        return new Result(1, "success", data);
    }
    public static Result error(String msg) { // 失败响应
        return new Result(0, msg, null);
    }
}
