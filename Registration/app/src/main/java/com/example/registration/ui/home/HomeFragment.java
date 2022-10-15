package com.example.registration.ui.home;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.registration.R;
import com.example.registration.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private ImageButton facebook;
    private ImageButton whatsapp;
    private ImageButton instagram;

    String urlFacebook= "https://www.facebook.com/";
    String urlWhatsapp= "https://web.whatsapp.com/";
    String urlInstagram= "https://www.instagram.com/";
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

            facebook = root.findViewById(R.id.facebookBtn);
            facebook.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Uri facebookLink = Uri.parse(urlFacebook);
                    Intent i = new Intent(Intent.ACTION_VIEW,facebookLink);
                    startActivity(i);
                }
            });
        whatsapp = root.findViewById(R.id.whapBtn);
        whatsapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri whatLink = Uri.parse(urlWhatsapp);
                Intent i = new Intent(Intent.ACTION_VIEW,whatLink);
                startActivity(i);
            }
        });

        instagram = root.findViewById(R.id.instagramBtn);
        instagram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri instaLink = Uri.parse(urlInstagram);
                Intent i = new Intent(Intent.ACTION_VIEW,instaLink);
                startActivity(i);
            }
        });
        return root;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}