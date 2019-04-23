package com.kobold.models;

import org.apache.commons.lang3.StringUtils;

import java.util.*;

public class MethodInfo {

	/**
	 * 方法名称
	 */
	private String methodName;
	/**
	 * 可访问性
	 */
	private String accessibility;
	/**
	 * 是否静态方法
	 */
	private boolean isStatic;
	/**
	 * 方法类型名称
	 */
	private String methodTypeName;

	/**
	 * 参数列表
	 * */
	private Map<Class<?>,String> arguments= new HashMap<>();

	/**
	 * 注释
	 */
	private String comment;

	public String getAccessibility() {
		return accessibility;
	}

	public void setAccessibility(String accessibility) {
		this.accessibility = accessibility;
	}

	public boolean isStatic() {
		return isStatic;
	}

	public void setStatic(boolean aStatic) {
		isStatic = aStatic;
	}

	public String getMethodTypeName() {
		return methodTypeName;
	}

	public void setMethodTypeName(String methodTypeName) {
		this.methodTypeName = methodTypeName;
	}

	public Map<Class<?>, String> getArguments() {
		return arguments;
	}

	public String getArgumentsList(){
		List<String> args=new ArrayList<>();
		for(Class<?> key :this.arguments.keySet()){
			var str=key.getSimpleName()+"\t"+this.arguments.get(key);
			args.add(str);
		}
		return StringUtils.join(args,',');
	}

	public void addArguments(Class<?> clazz,String arg) {
		this.arguments.put(clazz,arg);
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}
}
