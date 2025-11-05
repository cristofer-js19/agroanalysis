package com.tech.agroanalysis.infrastructure.gateway.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ListUtil {
    public static <T> List<T> equalizeListDimension(List<T> list, int dimension) {
        if (Objects.isNull(list) || list.isEmpty()) {
            return new ArrayList<>();
        }

        int size = list.size();
        int fromIndex = Math.max(0, size - dimension);
        return list.subList(fromIndex, size);
    }
}
