package com.example.mybasebinderadapter;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<SimpleData> list = new ArrayList<>();
    private List<SimpleData.ChildData> childList1 = new ArrayList<>();
    private List<SimpleData.ChildData> childList2 = new ArrayList<>();
    private RvAdapter mRvAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.rv_list);
        initRv();
    }

    private void initRv() {
        childList1.add(new SimpleData.ChildData("a"));
        childList1.add(new SimpleData.ChildData("b"));
        childList1.add(new SimpleData.ChildData("c"));
        childList1.add(new SimpleData.ChildData("d"));
        childList1.add(new SimpleData.ChildData("e"));
        list.add(new SimpleData("A", childList1));

        childList2.add(new SimpleData.ChildData("f"));
        childList2.add(new SimpleData.ChildData("j"));
        childList2.add(new SimpleData.ChildData("h"));
        childList2.add(new SimpleData.ChildData("i"));
        childList2.add(new SimpleData.ChildData("j"));
        list.add(new SimpleData("B", childList2));


        mRvAdapter = new RvAdapter(this, list);
        recyclerView.setAdapter(mRvAdapter);
    }


    /**
     * 设置一个public方法,供adapter点击事件调用
     *
     * @param position 为第一层recycleview位置
     * @param tag      为第二层recycleview位置
     */
    public void OnClickListener(int position, int tag) {
        final List<SimpleData> datasBeans = list;
        for (int i = 0; i < datasBeans.size(); i++) {
            if (i == position) {
                List<SimpleData.ChildData> option = datasBeans.get(i).list;
                for (int j = 0; j < option.size(); j++) {
                    if (j == tag) {
                        option.get(j).select = (true);
                    } else {
                        option.get(j).select = (false);
                    }
                }
                Toast.makeText(MainActivity.this,
                        datasBeans.get(position).typeName + "-" + option.get(tag).childName,
                        Toast.LENGTH_SHORT).show();

            } else {
                //这里让之前选中的效果还原成未选中
                List<SimpleData.ChildData> option = datasBeans.get(i).list;
                for (int j = 0; j < option.size(); j++) {
                    option.get(j).select = (false);
                }
            }
        }
        mRvAdapter.notifyDataSetChanged();
    }

}
