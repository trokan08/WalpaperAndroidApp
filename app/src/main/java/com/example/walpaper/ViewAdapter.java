package com.example.walpaper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class ViewAdapter extends ArrayAdapter<Integer> {
    private  Context context;
    private int resource;
    private int categoryName;

    public ViewAdapter(@NonNull Context context, int resource, @NonNull Integer[] objects, int a) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.categoryName = a;
    }

    private static class ViewHolder {
        ImageView image;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final ViewHolder holder;

        if (convertView == null ){
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView =inflater.inflate(resource, parent, false);
            holder = new ViewHolder();
            holder.image = (ImageView) convertView.findViewById(R.id.imageView);
            convertView.setTag(holder);
        }else{
            holder =(ViewHolder) convertView.getTag();
        }


            if(categoryName == 1 ){
                System.out.println("sadece");
                holder.image.setTag(Image.imageId[position]);
                holder.image.setImageResource(Image.imageId[position]);}
            else if(categoryName == 2 ){
                System.out.println("hello");
                holder.image.setTag(Image.flower[position]);
                holder.image.setImageResource(Image.flower[position]);}
            else if(categoryName == 3 ){
                System.out.println("hello");
                holder.image.setTag(Image.anime[position]);
                holder.image.setImageResource(Image.anime[position]);}
            else if(categoryName == 4 ){
                System.out.println("hello");
                holder.image.setTag(Image.cat[position]);
                holder.image.setImageResource(Image.cat[position]);}

        return convertView;
    }
}
