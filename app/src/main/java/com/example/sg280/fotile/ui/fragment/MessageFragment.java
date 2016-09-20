package com.example.sg280.fotile.ui.fragment;

import android.content.Context;
import android.os.Handler;
import android.view.Gravity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sg280.fotile.R;
import com.example.sg280.fotile.adapter.ChatMsgListAdapter;
import com.example.sg280.fotile.app.Constants;
import com.example.sg280.fotile.model.bean.ChatEntity;
import com.example.sg280.fotile.model.bean.MySelfInfo;
import com.example.sg280.fotile.presents.Interface.IIMContacts;
import com.example.sg280.fotile.presents.MessagePresent;
import com.example.sg280.fotile.ui.activity.LiveActivity;
import com.example.sg280.fotile.utils.LogUtil;
import com.example.sg280.fotile.utils.LoginUtil;
import com.example.sg280.fotile.utils.ToastUtil;
import com.example.sg280.fotile.widget.CommentSettingPopupWin;
import com.tencent.TIMMessage;
import com.tencent.TIMTextElem;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by sg280 on 2016-08-09.
 */
public class MessageFragment extends BaseFragment implements View.OnClickListener,LiveActivity.LiveListener,IIMContacts.View{
    @Bind(R.id.img_setting)
    ImageView img_setting;
    @Bind(R.id.et_input)
    EditText et_input;
    @Bind(R.id.tv_sendMsg)
    TextView tv_sendMsg;
   @Bind(R.id.ll_send)
    LinearLayout ll_send;
    @Bind(R.id.lsv_msg)
    ListView mListViewMsgItems;
    private CommentSettingPopupWin popup_setting;
    private int mtype=0;
    private InputMethodManager imm;
    private MessagePresent present;
    private static final String TAG = "MessageFragment";
    private ArrayList<ChatEntity> mTmpChatList = new ArrayList<ChatEntity>();//缓冲队列
    private boolean mBoolRefreshLock = false;
    private boolean mBoolNeedRefresh = false;
    private static final int MINFRESHINTERVAL = 500;
    private ArrayList<ChatEntity> mArrayListChatEntity;
    private TimerTask mTimerTask;
    private static final int REFRESH_LISTVIEW = 5;
    private ChatMsgListAdapter mChatMsgListAdapter;
    private final Timer mTimer = new Timer();
    private boolean isInitIM=false;
    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_live_message;
    }

    @Override
    protected void onInitView() {
        if (mtype==1) {
            ll_send.setVisibility(View.VISIBLE);
        }else {
            ll_send.setVisibility(View.GONE);
        }
        imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        mArrayListChatEntity = new ArrayList<ChatEntity>();
        mChatMsgListAdapter = new ChatMsgListAdapter(getActivity(), mListViewMsgItems, mArrayListChatEntity);
        mListViewMsgItems.setAdapter(mChatMsgListAdapter);
             present=new MessagePresent(getActivity(),this);
        if (MySelfInfo.getInstance().islogin()&&mtype==1) {
            present.joinIMChatRoom(Constants.AV_CHATROOM_ID);
            isInitIM=true;
        }

    }
//点击设置，弹出popupwindow
    @OnClick(R.id.img_setting)
        void setting(){
      popup_setting=new CommentSettingPopupWin(getActivity(),this);
        popup_setting.showAtLocation(ll_send, Gravity.BOTTOM,0,0);
        }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rb1://弹出popupwindow显示全部消息


            break;
            case R.id.rb2://只显示官方消息

                break;
        }

    }
@OnClick(R.id.tv_sendMsg)
 public void sendMsg(){
    LoginUtil.checkLogin(getActivity(), new LoginUtil.LoginCallback() {
        @Override
        public void onLogin() {
            if(et_input.getText().length()>0){
                if (isInitIM) {
                    present.joinIMChatRoom(Constants.AV_CHATROOM_ID);
                }
                sendText(et_input.getText().toString());
                imm.showSoftInput(et_input, InputMethodManager.SHOW_FORCED);
                imm.hideSoftInputFromWindow(et_input.getWindowToken(), 0);
            }else {
                ToastUtil.showShort(getActivity(),"输入消息不能为空");
                return;
            }
        }
    });

}
    private void sendText(String msg) {
        if (msg.length() == 0)
            return;
        try {
            byte[] byte_num = msg.getBytes("utf8");
            if (byte_num.length > 160) {
                Toast.makeText(getActivity(), "输入消息太长", Toast.LENGTH_SHORT).show();
                return;
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return;
        }
        TIMMessage Nmsg = new TIMMessage();
        TIMTextElem elem = new TIMTextElem();
        elem.setText(msg);
        if (Nmsg.addElement(elem) != 0) {
            return;
        }
        present.sendGroupText(Nmsg);
    }
    @Override
    public void setActivityType(int type) {
        this.mtype=type;
    }

    @Override
    public void quitIM() {
        present.perpareQuitRoom(true);
    }

    @Override
    public void refreshText(String text, String name) {
        if (text != null) {
            refreshTextListView(name, text, Constants.TEXT_TYPE);
        }
    }
    /**
     * 消息刷新显示
     *
     * @param name    发送者
     * @param context 内容
     * @param type    类型 （上线线消息和 聊天消息）
     */
    public void refreshTextListView(String name, String context, int type) {
        ChatEntity entity = new ChatEntity();
        entity.setSenderName(name);
        entity.setContext(context);
        entity.setType(type);
        //mArrayListChatEntity.add(entity);
        notifyRefreshListView(entity);
        //mChatMsgListAdapter.notifyDataSetChanged();

        mListViewMsgItems.setVisibility(View.VISIBLE);
        LogUtil.d(TAG, "refreshTextListView height " + mListViewMsgItems.getHeight());

        if (mListViewMsgItems.getCount() > 1) {
            if (true)
                mListViewMsgItems.setSelection(0);
            else
                mListViewMsgItems.setSelection(mListViewMsgItems.getCount() - 1);
        }
    }
    /**
     * 通知刷新消息ListView
     */
    private void notifyRefreshListView(ChatEntity entity) {
        mBoolNeedRefresh = true;
        mTmpChatList.add(entity);
        if (mBoolRefreshLock) {
            return;
        } else {
            doRefreshListView();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        present.ondestory();
        present=null;
        mHandler.removeCallbacksAndMessages(null);
    }

    /**
     * 刷新ListView并重置状态
     */
    private void doRefreshListView() {
        if (mBoolNeedRefresh) {
            mBoolRefreshLock = true;
            mBoolNeedRefresh = false;
            mArrayListChatEntity.addAll(mTmpChatList);
            mTmpChatList.clear();
            mChatMsgListAdapter.notifyDataSetChanged();
             et_input.setText("");//输入框置空
            if (null != mTimerTask) {
                mTimerTask.cancel();
            }
            mTimerTask = new TimerTask() {
                @Override
                public void run() {
                    LogUtil.v(TAG, "doRefreshListView->task enter with need:" + mBoolNeedRefresh);
                    mHandler.sendEmptyMessage(REFRESH_LISTVIEW);
                }
            };
            //mTimer.cancel();
            mTimer.schedule(mTimerTask, MINFRESHINTERVAL);
        } else {
            mBoolRefreshLock = false;
        }
    }

    private Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(android.os.Message msg) {
            switch (msg.what) {

                case REFRESH_LISTVIEW:
                    doRefreshListView();
                    break;

            }
            return false;
        }
    });
}
