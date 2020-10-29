package com.example.livedatabeta;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.example.livedatabeta.databinding.FragmentEntrenadorBinding;

public class EntrenadorFragment extends Fragment {
    private FragmentEntrenadorBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return (binding = FragmentEntrenadorBinding.inflate(inflater, container, false)).getRoot();
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        com.example.livedatabeta.EntrenadorViewModel entrenadorViewModel = new ViewModelProvider(this).get(com.example.livedatabeta.EntrenadorViewModel.class);

        entrenadorViewModel.obtenerEjercicio().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer ejercicio) {
                Glide.with(com.example.livedatabeta.EntrenadorFragment.this).load(ejercicio).into(binding.ejercicio);
            }
        });

        entrenadorViewModel.obtenerRepeticion().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String repeticion) {
                if(repeticion.equals("CAMBIO")){
                    binding.cambio.setVisibility(View.VISIBLE);
                } else {
                    binding.cambio.setVisibility(View.GONE);
                }
                binding.repeticion.setText(repeticion);
            }
        });
    }
}

