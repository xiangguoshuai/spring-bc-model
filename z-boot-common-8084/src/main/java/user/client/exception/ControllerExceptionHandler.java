package user.client.exception;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/***
 * 异常统一处理类
 * 
 * @author XiangGuoShuai
 * @Date 2020-05-18
 */
@ControllerAdvice
@ResponseBody
public class ControllerExceptionHandler extends HashMap<String, Object> implements WebMvcConfigurer {

    private static final long   serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(ControllerExceptionHandler.class);

    @ExceptionHandler(value = Exception.class)
    public Map<String, Object> errorHandler(Exception ex) {
        if (ex instanceof BIZException) {
            BIZException exception = (BIZException) ex;
            this.put("msg", exception.getMsg());
            this.put("code", exception.getCode() == 0 ? 403 : exception.getCode());
            log.error("异常信息：", ex);
            return this;
        }
       
        if (ex instanceof HttpMessageNotReadableException) {
            this.put("code", 504);
            this.put("msg", "参数错误");
            log.error("异常信息：", ex);
            return this;
        }
        this.put("code", 500);
        this.put("msg", "系统异常，请联系系统管理员！");
        log.error("异常信息：", ex);
        return this;

    }

}
