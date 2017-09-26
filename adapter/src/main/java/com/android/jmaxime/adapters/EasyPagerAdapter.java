package com.android.jmaxime.adapters;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.android.jmaxime.factory.ViewHolderFactory;
import com.android.jmaxime.interfaces.IBaseCommunication;
import com.android.jmaxime.viewholder.JRecyclerViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Maxime Jallu
 * @since 26/09/2017
 * <p>
 * Use this Class for : <br/>
 * {DOCUMENTATION}
 */
public class EasyPagerAdapter<T> extends PagerAdapter {

    private final List<T> mItems;
    private final ViewHolderFactory<T> mFactory;

    public EasyPagerAdapter(Class<? extends JRecyclerViewHolder<T>> viewHolder) {
        this(new ArrayList<>(), viewHolder);
    }

    public EasyPagerAdapter(List<T> items, Class<? extends JRecyclerViewHolder<T>> viewHolder) {
        this(items, viewHolder, null);
    }

    public EasyPagerAdapter(List<T> items, Class<? extends JRecyclerViewHolder<T>> viewHolder, IBaseCommunication<T> callback) {
        mItems = items;
        mFactory = new ViewHolderFactory<>(viewHolder, callback);
    }

    @Override public Object instantiateItem(ViewGroup container, int position) {
        JRecyclerViewHolder<T> vh = mFactory.createVH(container, 0);
        vh.bind(mItems.get(position));
        container.addView(vh.itemView);
        return vh.itemView;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override public int getCount() {
        return mItems.size();
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    public void addAll(List<T> medias) {
        mItems.addAll(medias);
        notifyDataSetChanged();
    }
}