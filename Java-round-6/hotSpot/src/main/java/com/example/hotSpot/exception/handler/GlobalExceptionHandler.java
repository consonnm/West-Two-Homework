package com.example.hotSpot.exception.handler;

import com.example.hotSpot.exception.ControllerException;
import com.example.hotSpot.exception.ServiceException;
import com.example.hotSpot.response.ResultVo;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

//全局异常处理类
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

	@ExceptionHandler(value = { ControllerException.class, ServiceException.class,UnauthorizedException.class, UnauthenticatedException.class})
	public ResponseEntity<ResultVo> controllerExceptionHandler(Exception ex) {
		if (ex instanceof UnauthenticatedException) {
			return new ResponseEntity<ResultVo>(new ResultVo(400, ex.getMessage(), null),
					HttpStatus.BAD_REQUEST);
		} else if (ex instanceof UnauthorizedException) {
			return new ResponseEntity<ResultVo>(new ResultVo(401, ex.getMessage(), null),
					HttpStatus.BAD_REQUEST);
		}
		else return new ResponseEntity<ResultVo>(new ResultVo(402, ex.getMessage(), null),
					HttpStatus.BAD_REQUEST);

	}
}
