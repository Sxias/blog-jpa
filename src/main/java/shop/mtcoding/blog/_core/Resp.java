package shop.mtcoding.blog._core;


import lombok.Data;

@Data
public class Resp<T> {
    private Integer status;
    private String msg;
    private T body;

    private Resp(Integer status, String msg, T body) {
        this.status = status;
        this.msg = msg;
        this.body = body;
    }

    public static <T> Resp<?> ok(T body) {
        return new Resp<>(200, "성공", body);
    }

    public static Resp<?> fail(Integer status, String msg) {
        return new Resp(status, msg, null);
    }
}