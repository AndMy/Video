package com.home.quhong.quhong.TV;

import com.home.quhong.quhong.TV.entity.home.SeriesBean;

import java.util.Comparator;

/**
 * Created by aserbao on 2017/3/13.
 * e_mail:abybxc@163.com
 * weixin:aserbao
 */

public class SortCmparator implements Comparator {
    @Override
    public int compare(Object o1, Object o2) {
        SeriesBean bean1 = (SeriesBean) o1;
        SeriesBean bean2 = (SeriesBean) o2;
        return (bean1.getTitle().compareTo(bean2.getTitle()));
    }
}
