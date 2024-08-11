package org.example.feettool.info;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Result<T> {
    private int code;
    private String message = "";
    private T data;

    public static <T> Result<T> success() {
        return new Result<>(200, "success", null);
    }

    public static <T> Result<T> fail() {
        return new Result<>(500, "fail", null);
    }

    public static <T> Result<T> fail(int code) {
        return new Result<>(code, "fail", null);
    }

    public static <T> Result<T> success(T data) {
        return new Result<>(200, "success", data);
    }
}
