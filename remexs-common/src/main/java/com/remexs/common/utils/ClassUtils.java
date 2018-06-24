package com.remexs.common.utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.remexs.common.dto.Dto;
import com.remexs.common.dto.Dtos;
import com.remexs.common.exception.ServiceException;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtField;
import javassist.CtMethod;
import javassist.NotFoundException;
import javassist.bytecode.FieldInfo;

public class ClassUtils extends org.apache.commons.lang3.ClassUtils{
	public static ClassPool pool = ClassPool.getDefault();

	private static final Logger log = LoggerFactory.getLogger(ClassUtils.class);

	/**
	 * 
	 * @param supperClass 	父类
	 * @param className 	类名
	 * @param classAnnos 	类注解
	 * @param fields 		属性列表（包含属性注解列表）
	 * @param methods 		方法列表 （包含方法注解列表）
	 * @return
	 */
	@SuppressWarnings({ "unused", "unchecked" })
	private static Class<?> markClass(String supperClass, String className, List<Dto> classAnnos, List<Dto> fields, List<Dto> methods) {
		CtClass ctClass = pool.makeClass(className);
		try {
			if (!ObjectUtils.isEmpty(supperClass)) {
				ctClass.setSuperclass(pool.get(supperClass));
			}
			if (!ObjectUtils.isEmpty(classAnnos)) {
				// 创建类注解
				for (Dto classAnno : classAnnos) {

				}
			}
			if (!ObjectUtils.isEmpty(fields)) {
				// 创建属性
				for (Dto field : fields) {
					String scope = field.getString("scope");
					String type = field.getString("type");
					String name = field.getString("name");
					markField(scope, type, name, ctClass);

					List<Dto> fieldAnnos = (List<Dto>) field.getList("fieldAnnos");
					if (!ObjectUtils.isEmpty(fieldAnnos)) {
						// 创建属性注解
						for (Dto fieldAnno : fieldAnnos) {

						}
					}
				}
			}
			if (!ObjectUtils.isEmpty(methods)) {
				// 创建方法
				for (Dto method : methods) {
					String scope = method.getString("scope");
					String type = method.getString("type");
					String name = method.getString("name");
					List<Dto> params = (List<Dto>) method.getList("params");
					String methodBody = method.getString("methodBody");

					markMethod(scope, type, name, params, methodBody, ctClass);
					List<Dto> methodAnnos = (List<Dto>) method.getList("methodAnnos");

					if (!ObjectUtils.isEmpty(methodAnnos)) {
						// 创建方法注解
						for (Dto fieldAnno : methodAnnos) {

						}
					}
				}
			}
			return ctClass.toClass();
		} catch (CannotCompileException e) {
			String error = StringUtils.merge("创建类失败,supperClass:{0},className:{1},classAnnos:{2},classAnnos:{3},fields:{4},methods:{5}", supperClass, className, classAnnos.toString(), fields.toString(), methods.toString());
			log.error(error);
			throw new ServiceException(error);
		} catch (NotFoundException e) {
			String error = StringUtils.merge("创建类失败,supperClass:{0},className:{1},classAnnos:{2},classAnnos:{3},fields:{4},methods:{5}", supperClass, className, classAnnos.toString(), fields.toString(), methods.toString());
			log.error(error);
			throw new ServiceException(error);
		}
	}
	/**
	 * 
	 * @param clazz 待添加属性的类名
	 * @param scope	属性作用域
	 * @param type  属性类型
	 * @param name	属性名
	 */
	public static Class<?> removeField(Class<?> clazz, String name) {
		try {
			if (!existField(clazz, name)) return clazz;
			CtClass ctClass = pool.get(clazz.getName());
			if (ctClass.isFrozen()) ctClass.defrost();
			if (!existField(ctClass, name)) return clazz;
			ctClass.removeField(ctClass.getField(name));
			Class<?> returnClass=ctClass.toClass();
			return returnClass;
		} catch (NotFoundException e) {
			String error = StringUtils.merge("移除属性失败,class:{0},name:{1}", clazz, name);
			log.error(error);
			throw new ServiceException(error);
		} catch (CannotCompileException e) {
			String error = StringUtils.merge("移除属性失败,class:{0},name:{1}", clazz,  name);
			log.error(error);
			throw new ServiceException(error);
		}
	}
	/**
	 * 
	 * @param clazz 待添加属性的类名
	 * @param scope	属性作用域
	 * @param type  属性类型
	 * @param name	属性名
	 */
	public static Class<?> addField(Class<?> clazz, String scope, String type, String name) {
		try {
			if (existField(clazz, name)) return clazz;
			CtClass ctClass = pool.get(clazz.getName());
			if (ctClass.isFrozen()) ctClass.defrost();
			if (existField(ctClass, name)) return clazz;
			markField(scope, type, name, ctClass);
			Class<?> returnClass=ctClass.toClass();
			return returnClass;
		} catch (NotFoundException e) {
			String error = StringUtils.merge("添加属性失败,class:{0},scope:{1},type:{2},name:{3}", clazz, scope, type, name);
			log.error(error);
			throw new ServiceException(error);
		} catch (CannotCompileException e) {
			String error = StringUtils.merge("添加属性失败,class:{0},scope:{1},type:{2},name:{3}", clazz, scope, type, name);
			log.error(error);
			throw new ServiceException(error);
		}
	}
	/**
	 * 
	 * @param scope 作用域
	 * @param type 属性类型
	 * @param name 属性名
	 * @param ctClass
	 * @throws ServiceException
	 */
	private static void markField(String scope, String type, String name, CtClass ctClass) throws ServiceException {
		try {
			if (ctClass.isFrozen()) ctClass.defrost();
			CtField field = CtField.make(scope + " " + type + " " + name + ";", ctClass);
			ctClass.addField(field);
			markMethod("public", type, "get" + StringUtils.firstToUpperCase(name), null, "\n return this." + name + ";", ctClass);
			List<Dto> setParams = new ArrayList<>();
			Dto setParam = Dtos.newDto();
			setParam.put("type", type);
			setParam.put("name", name);
			setParams.add(setParam);
			markMethod("public", "void", "set" + StringUtils.firstToUpperCase(name), setParams, "\n this." + name + "=" + name + ";", ctClass);
		} catch (CannotCompileException e) {
			e.printStackTrace();
			String error = StringUtils.merge("创建属性失败,scope:{0},type:{1},name:{2}", scope, type, name);
			log.error(error);
			throw new ServiceException(error);
		}
	}
	/**
	 * 
	 * @param scope 		方法作用域
	 * @param type			返回类型
	 * @param name			方法名
	 * @param params		入参
	 * @param methodBody	方法体
	 * @param ctClass
	 * @return
	 * @throws ServiceException
	 */
	private static void markMethod(String scope, String type, String name, List<Dto> params, String methodBody, CtClass ctClass) throws ServiceException {
		try {
			if (ctClass.isFrozen()) ctClass.defrost();
			StringBuffer buffer = new StringBuffer();
			buffer.append(scope).append(" ").append(type).append(" ").append(name).append("(");
			if (!ObjectUtils.isEmpty(params)) {
				for (Dto param : params) {
					buffer.append(param.getString("type")).append(" ").append(param.getString("name"));
				}
			}
			buffer.append("){\n").append(methodBody).append("\n}");

			CtMethod method = CtMethod.make(buffer.toString(), ctClass);
			ctClass.addMethod(method);
		} catch (CannotCompileException e) {
			e.printStackTrace();
			String error = StringUtils.merge("创建方法失败,scope:{0},type:{1},name:{2},params:{3},methedBody:{4}", scope, type, name, params.toString(), methodBody);
			log.error(error);
			throw new ServiceException(error);
		}
	}

	/**
	 * 判断类属性是否存在
	 * @param clazz	类
	 * @param fieldName 属性名
	 * @return
	 */
	public static boolean existField(Class clazz,String fieldName){
		Field[] fields=clazz.getDeclaredFields();
		for(int i=0;i<fields.length;i++) {
			if(fields[i].getName().equals(fieldName)) return true;
		}
		return false;
	}
	/**
	 * 判断类属性是否存在
	 * @param clazz	类
	 * @param fieldName 属性名
	 * @return
	 */
	public static boolean existField(CtClass ctClass,String fieldName){
		List<FieldInfo>fieldInfos=ctClass.getClassFile().getFields();
		for (FieldInfo fieldInfo : fieldInfos) {
			if(fieldInfo.getName().equals(fieldName))return true;
		}
		return false;
	}
	/**
	 * 构造类的实例
	 * @param clazz
	 * @return
	 */
	public static Object newInstance(Class<?> clazz) {
		try {
			return clazz.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
			String error = StringUtils.merge("构造类的实例失败,class:{0}", clazz.getName());
			log.error(error);
			throw new ServiceException(error);
		}
	}
	public static void main(String[] args) throws NotFoundException, CannotCompileException {
		String error = StringUtils.merge("构造类的实例失败,class:{0},{1}", "123", "456");
		System.out.println(error);
		List<Dto> fields = new ArrayList<>();
		Dto field = Dtos.newDto();
		field.put("scope", "private");
		field.put("type", "String");
		field.put("name", "id");
		fields.add(field);
		//Class clazz = markClass(null, "com.remexs.entity.Test", null, fields, null);
		
		CtClass ctClass = pool.get("com.remexs.common.vo.DataVO");
		ctClass.defrost();
		System.out.println(ctClass.getField("id"));
		ctClass.removeField(ctClass.getField("id"));
		ctClass.toClass();
//		List<FieldInfo>  fieldInfos = ctClass.getClassFile().getFields();
//		ctClass.getf
//		for (FieldInfo fieldInfo : fieldInfos) {
//			System.out.println(fieldInfo.getName());
//			CtField ctField=CtField.make("private String id;", ctClass);
//			ctClass.rem
//		}
	}
}
