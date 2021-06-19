package com.kqtlt.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

//普通文件--- 一个普通文件中存在多个新闻
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain=true)
public class NormalFile {
    private Integer fileId;
    private String fileName;
    private Long fileSize;
    private String fileAddress;
    private String fileContent;
}
