package com.ed.v1.common.viewholder;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.view.View;

import com.ed.v1.common.Log;

import java.lang.reflect.Field;

/**
 * Created by Haijun.Wang on 15/6/24.
 */
public class AutomaticViewHolderUtil {

    /**
     * 帮助开发者查找所有在ViewHolder中拥有Res注释的View(会向上递归查找，直到Activity或Fragment,Object,
     * 或是AutomaticViewHolder时停止）.
     *
     * <p>
     * 推荐使用AutomaticViewHolder，以提高Fragment或Fragment的可维护性。
     *
     * @param viewHolder
     *            用于保持View引用的对象
     * @param rootView
     *            Holder中所有View的共同根View
     */
    public static void findAllViews(Object viewHolder, View rootView) {
        if (rootView == null) {
            throw new NullPointerException("参数rootView不能为空.");
        }

        Class<?> clazz = viewHolder.getClass();

        while (clazz != Activity.class && clazz != Fragment.class && clazz != AutomaticViewHolder.class && clazz != Object.class && clazz != Dialog.class) {
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                Res res = field.getAnnotation(Res.class);
                if (res != null) {
                    if (View.class.isAssignableFrom(field.getType())) {

                        try {
                            int id = res.value();

                            View view = findViewById(rootView, id);
                            if (view == null) {
                                throw new IllegalViewTypeException("该layout中不存在id:'" + res.value() + "'(" + field.getName() + ")，请检查layout中的id.");
                            }

                            field.setAccessible(true);
                            try {
                                field.set(viewHolder, view);
                            } catch (IllegalArgumentException e) {
                                throw new IllegalViewTypeException("字段类型不匹配 :" + field.getName() + "  字段声明类型：" + field.getType().getName() + ",  layout定义类型："
                                        + view.getClass());
                            }
                        } catch (IllegalArgumentException e) {
                            Log.logStackTrace(e);
                        } catch (IllegalAccessException e) {
                            Log.logStackTrace(e);
                            throw new IllegalViewTypeException("请勿将以Res注释的字段声明为final.");
                        }
                    } else {
                        throw new IllegalViewTypeException();
                    }
                }
            }
            clazz = clazz.getSuperclass();
        }
    }

    @SuppressWarnings("unchecked")
    public static <V> V findViewById(View parent, int id) {
        return (V) parent.findViewById(id);
    }

    public static Class<?> getR_ID_Class(Context context) {
        try {
            return Class.forName(context.getPackageName() + ".R$id");
        } catch (ClassNotFoundException e) {
            Log.logStackTrace(e);
        }
        return null;
    }
}
