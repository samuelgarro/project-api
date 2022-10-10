package com.unvime.projectapi.utils;

import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

public class MapperUtils {
    private static final ModelMapper mapper = new ModelMapper();

    public static <T, D> D map(T object, Class<D> clazz) {
        return (D) mapper.map(object, clazz);
    }

    public static <S, T> List<T> mapList(List<S> source, Class<T> targetClass) {
        return source
                .stream()
                .map(element -> map(element, targetClass))
                .collect(Collectors.toList());
    }
}
