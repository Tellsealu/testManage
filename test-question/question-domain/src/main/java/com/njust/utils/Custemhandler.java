package com.njust.utils;
import com.alibaba.excel.metadata.CellData;
import com.alibaba.excel.metadata.Head;
import com.alibaba.excel.write.metadata.holder.WriteSheetHolder;
import com.alibaba.excel.write.style.column.AbstractColumnWidthStyleStrategy;
import org.apache.poi.ss.usermodel.Cell;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class Custemhandler extends AbstractColumnWidthStyleStrategy {
    private static final int MAX_COLUMN_WIDTH = 255;
    //the maximum column width in Excel is 255 characters

    public Custemhandler() {
    }

    @Override
    protected void setColumnWidth(WriteSheetHolder writeSheetHolder, List<CellData> cellDataList, Cell cell, Head head, Integer relativeRowIndex, Boolean isHead) {
        if (isHead && cell.getRowIndex() != 0) {
            int columnWidth = cell.getStringCellValue().getBytes().length;
            if (columnWidth > MAX_COLUMN_WIDTH) {
                columnWidth = MAX_COLUMN_WIDTH;
            } else {
                columnWidth = columnWidth + 3;
            }
            writeSheetHolder.getSheet().setColumnWidth(cell.getColumnIndex(), columnWidth * 256);

        }
    }


    private final static Logger logger = LoggerFactory.getLogger(Custemhandler.class);
}