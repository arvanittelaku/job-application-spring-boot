package com.example.demo1.infrastructure;

public interface SimpleMapper <TEntity, TDto> {
    TDto toDto(TEntity entity);
    TEntity toEntity(TDto dto);
}
