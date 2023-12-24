package com.example.myapplication3;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BlankFragment2 extends Fragment {
    private RecyclerView recyclerView;
    private Myadapter myadapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_blank_fragment2, container, false);
        recyclerView=view.findViewById(R.id.recycleview);
        //创建数据
        String[] names={"王牌部队" ,"冰雨火","长歌行","小别离","宁安如梦","脱轨","长相思","我的ID是江南美人"};
        int[] images={R.drawable.no1, R.drawable.no2, R.drawable.no3, R.drawable.no4,R.drawable.no5,
                R.drawable.no6,R.drawable.no7,R.drawable.no8};
        String[] composer={"肖战","王一博","吴磊","胡先煦","张凌赫", "林一","檀健次","车银优"};
        String[] album={"军旅","刑侦","古装","都市","爱情","剧情","古装", "爱情"};
        String[] tags={"歌手、演员","演员、歌手、职业赛车手","演员","演员","演员",
                "演员","演员、歌手","演员、歌手"};
        List<Map<String,Object>> items=new ArrayList<Map<String,Object>>();
        for(int i=0;i<names.length;i++){
            Map<String,Object> item=new HashMap<String, Object>();
            item.put("i_name",names[i]);
            item.put("i_image",images[i]);
            item.put("i_songer",composer[i]);
            item.put("i_tv",album[i]);
            item.put("i_tag",tags[i]);
            items.add(item);
        }
        //创建RecycleView实例和设置Adapter
        Context context=getContext();
        myadapter=new Myadapter(items,context);
        LinearLayoutManager manager=new LinearLayoutManager(context);
        manager.setOrientation(recyclerView.VERTICAL);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(myadapter);

        //实现拖拽和左滑删除效果
        ItemTouchHelper itemTouchHelper=new ItemTouchHelper(new ItemTouchHelper.Callback() {
            @Override
            public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
                int swiped=ItemTouchHelper.LEFT;
                int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
                return makeMovementFlags(dragFlags,swiped);
            }

            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                int oldPosition = viewHolder.getAdapterPosition();
                int newPosition = target.getAdapterPosition();
                if (oldPosition < newPosition) {
                    for (int i = oldPosition; i < newPosition; i++) {
                        // 改变数据集
                        Collections.swap(items, i, i +1);
                    }
                } else {
                    for (int i = oldPosition; i > newPosition; i--) {
                        // 改变数据集
                        Collections.swap(items, i, i - 1);
                    }
                }
                myadapter.notifyItemMoved(oldPosition, newPosition);
                return true;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                items.remove(position);
                myadapter.notifyItemRemoved(position);
            }
            @Override
            public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
                if (actionState!= ItemTouchHelper.ACTION_STATE_IDLE){
                    viewHolder.itemView.setBackgroundColor(Color.parseColor("#04BE02"));
                }
            }
            @Override
            public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                super.clearView(recyclerView, viewHolder);
                viewHolder.itemView.setBackgroundColor(Color.TRANSPARENT);
            }
        });
        //关联recycleView
        itemTouchHelper.attachToRecyclerView(recyclerView);
        return view;
    }
    }

