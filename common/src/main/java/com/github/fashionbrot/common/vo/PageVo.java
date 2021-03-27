package com.github.fashionbrot.common.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Collection;

@Data
@Builder
@AllArgsConstructor
public class PageVo {

    private Collection<?> rows;

    private long total;

}
