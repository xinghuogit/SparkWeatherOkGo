package com.library.common.base;


import android.support.v7.widget.RecyclerView;
import android.text.SpannableStringBuilder;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liuhc on 2016/12/13.
 * Version:1.0
 */
public abstract class QuickAdapter<T> extends RecyclerView.Adapter<QuickAdapter.VH> {
    private static final String TAG = "QuickAdapter";

    public List<T> list;

    public QuickAdapter(List<T> datas) {
        this.list = datas;
    }

    protected IItemClickedCallback callback;
    protected IItemLongClickCallback iItemLongClickCallback;
    private boolean loadMore = false;

    public abstract int getLayoutId(int viewType);

    public void setItemClickedCallback(IItemClickedCallback callback) {
        this.callback = callback;
    }

    public void setItemLongClickCallback(IItemLongClickCallback callback) {
        this.iItemLongClickCallback = callback;
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        return VH.get(parent, getLayoutId(viewType));
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {
        convert(holder, list.get(position), position);
    }

    /**
     * 移除一条数据
     */
    public void removeItem(int position) {
        if (position > list.size() - 1) {
            return;
        }
        list.remove(position);
        notifyItemRemoved(position);
    }

    public void removeAllItems() {
        if (list != null) {
            list.clear();
            notifyDataSetChanged();
        }
    }

    public void addItems(List<T> datas) {
        if (list == null) {
            list = new ArrayList<>();
        }
        list.addAll(datas);
        notifyDataSetChanged();
    }

    public void updateItems(List<T> datas) {
        if (list != null) {
            list.clear();
        }
        if (list == null) {
            list = new ArrayList<>();
        }
        this.list.addAll(datas);
        notifyDataSetChanged();
    }

    /**
     * 刷新数据
     */
    public void refreshDate(List<T> items) {
        if (items == null) {
            updateItems(null);
        } else {
            if (loadMore) {
                addItems(items);
            } else {
                updateItems(items);
            }
        }
    }

    public List<T> getData() {
        return list;
    }

    public T getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    public abstract void convert(VH holder, T data, int position);

    protected static class VH extends RecyclerView.ViewHolder {
        private SparseArray<View> mViews;
        private View mConvertView;

        private VH(View v) {
            super(v);
            mConvertView = v;
            mViews = new SparseArray<>();
        }

        public static VH get(ViewGroup parent, int layoutId) {
            View convertView = LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false);
            return new VH(convertView);
        }

        public <T extends View> T getView(int id) {
            View v = mViews.get(id);
            if (v == null) {
                v = mConvertView.findViewById(id);
                mViews.put(id, v);
            }
            return (T) v;
        }

        public ImageView getImageView(int id) {
            View v = mViews.get(id);
            if (v == null) {
                v = mConvertView.findViewById(id);
                mViews.put(id, v);
            }
            return (ImageView) v;
        }

        public TextView getTextView(int id) {
            View v = mViews.get(id);
            if (v == null) {
                v = mConvertView.findViewById(id);
                mViews.put(id, v);
            }
            return (TextView) v;
        }

        public Button getButton(int id) {
            View v = mViews.get(id);
            if (v == null) {
                v = mConvertView.findViewById(id);
                mViews.put(id, v);
            }
            return (Button) v;
        }

        public TextView setText(int id, String value) {
            TextView view = getView(id);
            view.setText(value);
            return view;
        }

        public TextView setText(int id, SpannableStringBuilder value) {
            TextView view = getView(id);
            view.setText(value);
            return view;
        }
    }

    public boolean isLoadMore() {
        return loadMore;
    }

    public void setLoadMore(boolean loadMore) {
        this.loadMore = loadMore;
    }
}
