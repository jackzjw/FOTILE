
package com.example.sg280.fotile.app;

import java.io.File;

/**
 * Created by sg280 on 2016-07-14.
 */
public class Constants {
    public static final String BASE_URL = "http://fotile.efotile.com/api/";
    public static final String PATH_DATA = FTApplication.getInstance().getCacheDir().getAbsolutePath() + File.separator + "data";
    public static final String PATH_CACHE = PATH_DATA + "/NetCache";
    public static final String NET_ERROR="网络连接异常";
    public static final String SWITCH_FRAGMENT_LIVE="live";
    public static final String SWITCH_FRAGMENT_VEDIO="vedio";
    public static final String SWITCH_FRAGMENT_CART="cart";
    public static final String USER_INFO = "user_info";

    public static final String USER_ID = "user_id";

    public static final String USER_SIG = "user_sig";
    public static final String USER_IDENTIFY="user_identify";
    public static final String USER_NICK = "user_nick";
    public static final String USER_CART_NUM="cartnum";
    public static final String USER_SIGN = "user_sign";

    public static final String USER_AVATAR = "user_avatar";

    public static final String USER_ROOM_NUM = "user_room_num";
    public static final String ISLOGIN="islogin";
    public static final int SDK_APPID=1400012491;

    public static final int ACCOUNT_TYPE=6481;
    public static final String APP_VERSION="1.0";
    public static final int TEXT_TYPE=0;
    // @TGS#3Z2X6GBEI
    public static final String AV_CHATROOM_ID="3Z2X6GBEI";
    public static final String BD_EXIT_APP = "bd_fotile_exit";
    public static final String APPLY_CHATROOM = "申请加入";
    public static final String CMD_KEY = "userAction";
    public static final String CMD_PARAM = "actionParam";
    public static final int AVIMCMD_ExitLive =1;
    public static final String NEW_LIVE_NAME="最新直播";
    public static final String PUBLIC_VEDIO_NAME="宣传视频";
    public static final String FOTILE_VEDIO_NAME="方太企业视频";
    public static final String PRODUCT_VEDIO_NAME="产品视频";
    public static final int FROM_ADAPTER=1;

    public static final int FROM_SELF=0;

    public static final String ADD = "add";
    public static final String MODIFY = "modify";
    public static final String ADD_ONLY = "add_only";
    public static final String USER_PWD = "user_pwd";

    public static final String USER_PHONE = "user_phone";


}
