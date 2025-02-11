package org.automation.framework.utils;

import org.automation.framework.models.DataObjectToDTO;

import java.util.List;
import java.util.stream.Collectors;

public class Mapper {

    public static <T> List<T> mapToDTOList(List<? extends DataObjectToDTO<T>> list) {
        return list.stream()
                .map(DataObjectToDTO::toDTO)
                .collect(Collectors.toList());
    }
}
