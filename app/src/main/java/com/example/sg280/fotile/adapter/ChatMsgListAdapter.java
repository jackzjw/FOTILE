package com.example.sg280.fotile.adapter;


import android.content.Context;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.example.sg280.fotile.R;
import com.example.sg280.fotile.model.bean.ChatEntity;
import com.example.sg280.fotile.utils.LogUtil;
import com.example.sg280.fotile.widget.CustomTextView;

import java.util.ArrayList;
import java.util.List;


/**
 * 消息列表的Adapter
 */
public class ChatMsgListAdapter extends BaseAdapter implements AbsListView.OnScrollListener {

    private static String TAG = ChatMsgListAdapter.class.getSimpleName();
    private static final int ITEMCOUNT = 7;
    private List<ChatEntity> listMessage = null;
    private LayoutInflater inflater;
    private LinearLayout layout;
    public static final int TYPE_TEXT_SEND = 0;
    public static final int TYPE_TEXT_RECV = 1;
    private Context context;
    private ListView mListView;
    private ArrayList<ChatEntity> myArray = new ArrayList<ChatEntity>();


    private static final int MAXANIMATORCOUNT = 8;
    private static final int ANIMATORDURING = 8000;
    private static final int MAXITEMCOUNT = 50;

    private boolean mScrolling = false;
    private boolean mCreateAnimator = false;

    public ChatMsgListAdapter(Context context, ListView listview, List<ChatEntity> objects) {
        this.context = context;
        mListView = listview;
        inflater = LayoutInflater.from(context);
        this.listMessage = objects;


        mListView.setOnScrollListener(this);
    }
    class AnimatorInfo {
        long createTime;

        public AnimatorInfo(long uTime) {
            createTime = uTime;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }
    }

    @Override
    public int getCount() {
        return listMessage.size();
    }

    @Override
    public Object getItem(int position) {
        return listMessage.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }



    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        SpannableString spanString;

        if (convertView == null) {
            holder = new ViewHolder();
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            convertView = layoutInflater.inflate(R.layout.item_chatmsg, null);
            holder.textItem = (LinearLayout) convertView.findViewById(R.id.text_item);
            holder.sendContext = (CustomTextView) convertView.findViewById(R.id.sendcontext);
            convertView.setTag(R.id.tag_first, holder);
        } else {
            holder = (ViewHolder) convertView.getTag(R.id.tag_first);
        }

        ChatEntity item = listMessage.get(position);

       /* if (mCreateAnimator && MySelfInfo.getInstance().isbLiveAnimator()) {
            playViewAnimator(convertView, position, item);
        }*/


        spanString = new SpannableString(item.getSenderName() + "：" + item.getContext());
        spanString.setSpan(new ForegroundColorSpan(context.getResources().getColor(R.color.theme_gray)),
                0, item.getSenderName().length()+1, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        holder.sendContext.setText(spanString);
        // 设置控件实际宽度以便计算列表项实际高度
        holder.sendContext.fixViewWidth(mListView.getWidth());
        return convertView;
    }


    static class ViewHolder {
        public LinearLayout textItem;
        public CustomTextView sendContext;

    }




    /**
     * 删除超过上限(MAXITEMCOUNT)的列表项
     */
    private void clearFinishItem() {
        // 删除超过的列表项
      /*  while (listMessage.size() > MAXITEMCOUNT) {
            listMessage.remove(0);
            if (mAnimatorInfoList.size() > 0) {
                mAnimatorInfoList.remove(0);
            }
        }*/

        // 缓存列表延迟删除
        while (myArray.size() > (MAXITEMCOUNT << 1)) {
            myArray.remove(0);
        }

     /*   while (mAnimatorInfoList.size() >= listMessage.size()) {
            SxbLog.e(TAG, "clearFinishItem->error size: " + mAnimatorInfoList.size() + "/" + listMessage.size());
            if (mAnimatorInfoList.size() > 0) {
                mAnimatorInfoList.remove(0);
            } else {
                break;
            }
        }*/
    }

    /**
     * 重新计算ITEMCOUNT条记录的高度，并动态调整ListView的高度
     */
    private void redrawListViewHeight() {
        int totalHeight = 0;
        int start = 0, lineCount = 0;

        if (listMessage.size() <= 0) {
            return;
        }

        // 计算底部ITEMCOUNT条记录的高度
        mCreateAnimator = false;    // 计算高度时不播放属性动画
        for (int i = listMessage.size() - 1; i >= start && lineCount < ITEMCOUNT; i--, lineCount++) {
            View listItem = getView(i, null, mListView);
            listItem.measure(0, 0);
            // add item height
            totalHeight = totalHeight + listItem.getMeasuredHeight();
        }
        mCreateAnimator = true;

        // 调整ListView高度
        ViewGroup.LayoutParams params = mListView.getLayoutParams();
        params.height = totalHeight + (mListView.getDividerHeight() * (lineCount - 1));
        mListView.setLayoutParams(params);
    }


    /**
     * 重置透明度
     */
    private void resetAlpha() {
        for (int i = 0; i < mListView.getChildCount(); i++) {
            View view = mListView.getChildAt(i);
            view.setAlpha(1f);
        }
    }


    /**
     * 重载notifyDataSetChanged方法实现渐消动画并动态调整ListView高度
     */
    @Override
    public void notifyDataSetChanged() {
        LogUtil.v(TAG, "notifyDataSetChanged->scroll: " + mScrolling);
        if (mScrolling) {
            // 滑动过程中不刷新
            super.notifyDataSetChanged();
            return;
        }

        // 删除多余项
        clearFinishItem();

        super.notifyDataSetChanged();

        // 重置ListView高度
        redrawListViewHeight();



        // 自动滚动到底部
        mListView.post(new Runnable() {
            @Override
            public void run() {
                mListView.setSelection(mListView.getCount() - 1);
            }
        });
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        switch (scrollState) {
            case SCROLL_STATE_FLING:
                break;
            case SCROLL_STATE_TOUCH_SCROLL:
           /*     if (MySelfInfo.getInstance().isbLiveAnimator()) {
                    // 开始滚动时停止所有属性动画
                    stopAnimator();
                    resetAlpha();
                }*/
                mScrolling = true;
                break;
            case SCROLL_STATE_IDLE:
                mScrolling = false;
              /*  if (MySelfInfo.getInstance().isbLiveAnimator()) {
                    // 停止滚动时播放渐消动画
                    playDisappearAnimator();
                }*/
                break;
            default:
                break;
        }

    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

    }
}
