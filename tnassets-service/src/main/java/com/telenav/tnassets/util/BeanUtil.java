package com.telenav.tnassets.util;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.FatalBeanException;
import org.springframework.util.ClassUtils;

public class BeanUtil extends BeanUtils {
	public interface ModificationListener {
		public boolean modifying(PropertyDescriptor pd, Object srcVal, Object dstVal);
	}

	public static <T> List<T> convertList(Collection<?> sources, Class<T> clazz) {
		ArrayList<T> targets = new ArrayList<T>();
		try {
			for (Object source : sources) {
				T target = convert(source, clazz);
				targets.add(target);
			}
		} catch (Exception ex) {
			// do nothing and return empty array
		}
		return targets;
	}

	public static <T> T convert(Object source, Class<T> clazz) throws ReflectiveOperationException {
		if (source != null) {
			try {
				// try to use the constructor first
				Constructor<T> c = clazz.getDeclaredConstructor(source.getClass());
				T target = c.newInstance(source);
				return target;
			} catch (NoSuchMethodException nsmex) {
				// otherwise, use the default constructor with bean copiers
				T target = clazz.newInstance();
				copyProperties(source, target);
				return target;
			}
		} 
		
		throw new ReflectiveOperationException();
	}
	
	public static int copyProperties(Object source, Object target, ModificationListener listener)
			throws BeansException {
		return copyProperties(listener, source, target, null, (String[]) null);
	}

	public static int copyProperties(Object source, Object target, ModificationListener listener, String... ignoreProperties)
			throws BeansException {
		return copyProperties(listener, source, target, null, ignoreProperties);
	}

	/**
	 * Returns number of bean attributes copied. 
	 * @param listener
	 * @param source
	 * @param target
	 * @param editable
	 * @param ignoreProperties
	 * @return
	 * @throws BeansException
	 */
	public static int copyProperties(ModificationListener listener, Object source, Object target, Class<?> editable,
			String... ignoreProperties) throws BeansException {

		int count = 0;
		
		Class<?> actualEditable = target.getClass();
		if (editable != null) {
			if (!editable.isInstance(target)) {
				throw new IllegalArgumentException("Target class [" + target.getClass().getName()
						+ "] not assignable to Editable class [" + editable.getName() + "]");
			}
			actualEditable = editable;
		}
		PropertyDescriptor[] targetPds = getPropertyDescriptors(actualEditable);
		List<String> ignoreList = (ignoreProperties != null) ? Arrays.asList(ignoreProperties) : null;

		for (PropertyDescriptor targetPd : targetPds) {
			Method writeMethod = targetPd.getWriteMethod();
			if (writeMethod != null && (ignoreProperties == null || (!ignoreList.contains(targetPd.getName())))) {
				PropertyDescriptor sourcePd = getPropertyDescriptor(source.getClass(), targetPd.getName());
				if (sourcePd != null) {
					Method srcReadMethod = sourcePd.getReadMethod();
					if (srcReadMethod != null
							&& ClassUtils.isAssignable(writeMethod.getParameterTypes()[0],
									srcReadMethod.getReturnType())) {
						try {
							if (!Modifier.isPublic(srcReadMethod.getDeclaringClass().getModifiers())) {
								srcReadMethod.setAccessible(true);
							}
							Object srcValue = srcReadMethod.invoke(source);
							if (!Modifier.isPublic(writeMethod.getDeclaringClass().getModifiers())) {
								writeMethod.setAccessible(true);
							}

							// Now retrieve the original value
							Method dstReadMethod = targetPd.getReadMethod();
							if (!Modifier.isPublic(dstReadMethod.getDeclaringClass().getModifiers())) {
								dstReadMethod.setAccessible(true);
							}

							Object dstValue = dstReadMethod.invoke(target);

							if (!isEqual(srcValue, dstValue)) {
								boolean bModify = true;
								if (listener != null) {
									bModify = listener.modifying(sourcePd, srcValue, dstValue);
								}
								if (bModify) {
									// Write value here
									writeMethod.invoke(target, srcValue);
									count++;
								}
							}
						} catch (Throwable ex) {
							ex.printStackTrace();
							throw new FatalBeanException("Could not copy property '" + targetPd.getName()
									+ "' from source to target", ex);
						}
					}
				}
			}
		}
		return count;
	}

	public static void fillNullValues(Object target) throws BeansException {
		PropertyDescriptor[] pds = getPropertyDescriptors(target.getClass());
		for (PropertyDescriptor pd : pds) {
			try {
				Method rm = pd.getReadMethod();
				Object src = rm.invoke(target);
				if (src == null) {
					Method wm = pd.getWriteMethod();
					if (wm != null) {
						if (pd.getPropertyType() == String.class) {
							wm.invoke(target, "");
						}
					}
				}
			} catch (Throwable ex) {
				ex.printStackTrace();
				throw new FatalBeanException("Could not set property '" + pd.getName() + "'", ex);
			}
		}
	}

	private static boolean isEqual(Object o1, Object o2) {
		if (o1 == o2) {
			return true;
		}

		if (o1 != null) {
			return o1.equals(o2);
		}

		// o1 is null and o2 is not null here.
		return false;
	}
}