package com.example.wlsxo.checkmate;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ListViewAdapter extends BaseAdapter {

    private ArrayList<ListViewItem> listVO = new ArrayList<ListViewItem>() ;
    public ListViewAdapter() {

    }

    @Override
    public int getCount() {
        return listVO.size() ;
    }

    //이 부분에서 리스트뷰에 데이터를 넣어줌
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //postion = ListView의 위치      /   첫번째면 position = 0
        final int pos = position;
        final Context context = parent.getContext();


        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.roomlist, parent, false);
        }

        ImageView image = (ImageView) convertView.findViewById(R.id.image_sex) ;
        TextView title = (TextView) convertView.findViewById(R.id.rmlist_title) ;
        TextView numofperson = (TextView) convertView.findViewById(R.id.numofperson) ;


        ListViewItem listViewItem = listVO.get(position);

        // 아이템 내 각 위젯에 데이터 반영
        image.setImageDrawable(listViewItem.getImg());
        title.setText(listViewItem.getTitle());
        numofperson.setText(listViewItem.getnumofperson());


        //리스트뷰 클릭 이벤트
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, (pos+1)+"번 방이 클릭되었습니다.", Toast.LENGTH_SHORT).show();
            }
        });


        return convertView;
    }


    @Override
    public long getItemId(int position) {
        return position ;
    }


    @Override
    public Object getItem(int position) {
        return listVO.get(position) ;
    }

    // 데이터값 넣어줌
    public void addVO(Drawable room_sex, String title, String numofperson ) {
        ListViewItem item = new ListViewItem();

        item.setImg(room_sex);
        item.setTitle(title);
        item.setnumofperson(numofperson);
        listVO.add(item);
    }
}