package com.kqtlt.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

//新闻
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain=true)
public class NewsFile {
    private Integer newsId;
    private String newsContent;
    private String newsCategoryRight;
    private String newsCategoryAnalysis;
    private Integer fileId;
    private String newsRate;
}















