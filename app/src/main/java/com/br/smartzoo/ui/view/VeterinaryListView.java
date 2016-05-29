package com.br.smartzoo.ui.view;

import com.br.smartzoo.model.entity.Veterinary;

import java.util.List;

/**
 * Created by adenilson on 29/05/16.
 */
public interface VeterinaryListView {

    void onLoadVeterinaryList(List<Veterinary> veterinaries);

}
