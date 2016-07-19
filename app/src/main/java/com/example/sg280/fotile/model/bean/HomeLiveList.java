package com.example.sg280.fotile.model.bean;

import java.util.ArrayList;

/**
 * Created by sg280 on 2016-07-18.
 */
public class HomeLiveList {


    private  String ID; //分类ID,
    private String  PID; //上级分类ID,
    private String ClassType;// 视频类型,    1：直播；2点播
    private String ClassName;//"分类名称",
    private  String ClassCode;//"英文代码",
    private ArrayList<VedioBean>LiveList; //视频列表

    }
