package com.example.capstone2.ui.gallery;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.capstone2.R;

public class GalleryFragment extends Fragment {

    private GalleryViewModel galleryViewModel;
    ImageView iv;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel =
                new ViewModelProvider(this).get(GalleryViewModel.class);
        View root = inflater.inflate(R.layout.activity_useful, container, false);
//        iv = (ImageView)findViewById(R.id.imageView13);
//        iv.setOnClickListener(new View.OnClickListener(){
//            public void onClick(View view){
//                Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
//                startActivity(intent);
//            }
//        });
        return root;
    }
}